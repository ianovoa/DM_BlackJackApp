package es.uvigo.ingonzalezesei.blackjackapp;

import android.content.Intent;
import android.content.SharedPreferences;
//import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;


//import static es.uvigo.ingonzalezesei.blackjackapp.R.layout.mejores;


public class MainGame extends AppCompatActivity {

    private static String LogTag = MainGame.class.getSimpleName();

    private int puntJugador; //puntuacion total de jugador en esta ronda
    private int puntMaquina; //idem maquina
    private int dinGanado; //dinero total ganado por el jugador
    private int dinApostado; //dinero apostado en esta ronda

    private int numRondas; //guarda el numero de rondas jugadas
    private int numWin; //numero de rondas ganadas seguidas
    public static int numWinMax; //max numero de rondas ganadas seguidas
    public static int dinGanadoMax; //max dinero total ganado por el jugador

    private int estado;
    private final int CARTA1=1; //estado: primera carta jugador
    private final int CARTA2=2; //estado: resto cartas jugador
    private final int MAS21=3; //estado: el jugador se pasa de 21
    private final int CASA=4; //estado: turno maquina

    private Baraja baraja=Baraja.getBaraja(); //baraja utilizada por el juego
    /*
    this.baraja.getCarta(); -> recibe objeto Carta de la baraja
    this.baraja.finalRonda(); -> reinicia la baraja una vez acabada una ronda
    */

    //variables botones interfaz
    private Button btnRecibir;
    private Button btnPlantarse;
    private Button btnApostar;


    private ImageView imagenCarta; //variable imagen interfaz

    //variables textos
    //private TextView turno;
    private TextView apuesta;
    private TextView dinero;
    //private TextView textPuntCasa;
    private TextView textPuntJugador;
    private TextView textNumWinMax; //max numero de rondas ganadas seguidas
    private TextView textDinGanadoMax; //max dinero total ganado por el jugador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        //setContentView(R.layout.mejores);

        this.btnRecibir=this.findViewById(R.id.recibir);
        this.btnPlantarse=this.findViewById(R.id.plantarse);
        this.btnApostar=this.findViewById(R.id.apostarBoton);
        //this.btnMostrarMejoresPuntuaciones=this.findViewById(R.id.MejoresPuntuaciones);

        this.imagenCarta=this.findViewById(R.id.imageView);

        //this.turno=this.findViewById(R.id.turno);
        this.apuesta=this.findViewById(R.id.apostadoTotal);
        this.dinero=this.findViewById(R.id.dinero);
        //this.textPuntCasa=this.findViewById(R.id.puntCasa);
        this.textPuntJugador=this.findViewById(R.id.puntPropia);
       // this.textNumWinMax=this.findViewById(R.id.numVictoriasMax);
        //this.textDinGanadoMax=this.findViewById(R.id.dinMaxGanado);

        //variables inicializadas
        this.setPuntJugador(0);
        this.setPuntMaquina(0);
        this.setDinGanado(1000);
        this.setDinApostado(0);
        this.setEstado(this.CARTA1);
        this.numRondas=0;
        this.numWin=0;
        this.numWinMax=0;
        this.dinGanadoMax=1000;

        //botones a sus funciones
        this.btnApostar.setOnClickListener((v) -> apostar());
        this.btnRecibir.setOnClickListener((v) -> recibirCarta());
        this.btnPlantarse.setOnClickListener((v) -> turnoMaquina());
        //this.btnMostrarMejoresPuntuaciones.setOnClickListener((v) -> mostrarMejoresPuntuaciones());
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d( LogTag,"Guardando datos partida..." );

        final SharedPreferences.Editor editor = this.getPreferences( MODE_PRIVATE ).edit();
        editor.putInt("puntJugador",this.puntJugador);
        editor.putInt("puntMaquina",this.puntMaquina);
        editor.putInt("dinGanado",this.dinGanado);
        editor.putInt("dinApostado",this.dinApostado);
        editor.putInt("estado",this.estado);
        editor.putInt("numRondas",this.numRondas);
        editor.putInt("numWin",this.numWin);
        editor.putInt("numWinMax",this.numWinMax);
        editor.putInt("dinGanadoMax",this.dinGanadoMax);
        editor.apply();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d( LogTag,"Cargando datos partida..." );

        final SharedPreferences prefs = this.getPreferences( MODE_PRIVATE );
        this.setPuntJugador(prefs.getInt("puntJugador",0));

        this.setPuntMaquina(prefs.getInt("puntMaquina",0));
        this.setDinGanado(prefs.getInt("dinGanado",1000));
        this.setDinApostado(prefs.getInt("dinApostado",0));
        this.setEstado(prefs.getInt("estado",this.CARTA1));
        this.numRondas=prefs.getInt("numRondas",0);
        this.numWin=prefs.getInt("numWin",0);
        this.numWinMax=prefs.getInt("numWinMax",0);
        this.dinGanadoMax=prefs.getInt("dinGanadoMax",1000);
       // this.setNumWinMax(prefs.getInt("numWinMax",0));
    }
   /* public void ejecutar_info(View view){
        Intent i = new Intent(this, InfoClase.class);

        startActivity(i);


    }*/
    private void apostar(){
        EditText cantidadApostada=this.findViewById(R.id.cantidad);
        String apuesta=cantidadApostada.getText().toString();
        if(!apuesta.equals("") && Integer.parseInt(apuesta)>0 && Integer.parseInt(apuesta)<=this.dinGanado) {
            if (this.estado == this.CARTA1) this.setEstado(this.CARTA2);
            this.setDinApostado(this.dinApostado + Integer.parseInt(apuesta));
            this.recibirCarta();
        }
    }

    private void recibirCarta(){ //el jugador pide otra carta pero sin apostar
        Carta carta=this.baraja.getCarta();
        this.setPuntJugador(this.puntJugador + carta.getValue());
        this.imagenCarta.setImageResource(this.getResources().getIdentifier(carta.getImage(),"drawable",getPackageName()));
        if(this.puntJugador>21) this.setEstado(this.MAS21);
    }

    private void turnoMaquina(){ //la maquina intenta superar la puntuacion del jugador sin pasarse de 21
        //Handler handler = new Handler();
        final AlertDialog.Builder dlg = new AlertDialog.Builder( this ); //sol. cutre
        dlg.setPositiveButton( "Aceptar", null );
        dlg.setCancelable( false );
        this.setEstado(this.CASA);
        do{
            Carta carta=this.baraja.getCarta();
            this.setPuntMaquina(this.puntMaquina + carta.getValue());
            //this.imagenCarta.setImageResource(this.getResources().getIdentifier(carta.getImage(),"drawable",getPackageName()));
            //handler.postDelayed(null,500);
        }while (this.puntMaquina<21 && this.puntMaquina<=this.puntJugador);

        if(this.puntJugador<=21 && this.puntMaquina>21){ //jugador gana la partida
            this.setDinGanado(this.dinGanado+this.dinApostado*2);
            this.numWin++;
            if(this.numWin>this.numWinMax) this.numWinMax=this.numWin;
            dlg.setTitle("Ganas");
            dlg.setMessage( "El jugador gana la ronda\n\nPunt. jugador: "+this.puntJugador+"\nPunt. casa: "+this.puntMaquina);
        }
        else if(this.puntMaquina==this.puntJugador && this.puntJugador==21){ //jugador y maquina empatan
            this.setDinGanado(this.dinGanado+this.dinApostado);
            dlg.setTitle("Empate");
            dlg.setMessage( "El jugador empata con la casa\nEl jugador recupera el dinero apostado");
        }
        else{
            dlg.setTitle("Pierdes");
            dlg.setMessage( "El jugador pierde la ronda\n\nPunt. jugador: "+this.puntJugador+"\nPunt. casa: "+this.puntMaquina);
        }

        this.setDinApostado(0);
        //handler.postDelayed(null,3000);
        dlg.create().show();

        this.setPuntJugador(0);
        this.setPuntMaquina(0);
        this.imagenCarta.setImageResource(R.drawable.tapas);
        this.baraja.finalRonda(); //reinicia la baraja una vez acabada la ronda
        this.numRondas++;
        this.setEstado(this.CARTA1);
    }
    /*private void setNumWinMax(int numWin){ //cambia puntuacion jugador y lo muestra en la vista
        this.numWinMax=numWin;
        this.textNumWinMax.setText("Puntuacion jugador: "+this.numWinMax);
    }*/
    private void setPuntJugador(int punt){ //cambia puntuacion jugador y lo muestra en la vista
        this.puntJugador=punt;
        this.textPuntJugador.setText("Puntuacion jugador: "+this.puntJugador);
    }

    private void setPuntMaquina(int punt){ //cambia puntuacion maquina y lo muestra en la vista
        this.puntMaquina=punt;
        //this.textPuntCasa.setText("Puntuacion casa: "+this.puntMaquina);
    }

    private void setDinGanado(int din){ //cambia dinero total y lo muestra en la vista
        this.dinGanado=din;
        this.dinero.setText("Dinero total: "+this.dinGanado);
        if(this.dinGanado>this.dinGanadoMax) this.dinGanadoMax=this.dinGanado;
    }

    private void setDinApostado(int din){ //cambia apuesta y lo muestra en la vista
        this.dinApostado=din;
        this.setDinGanado(this.dinGanado-din); //din apostado = din perdido
        this.apuesta.setText("Apostado: "+this.dinApostado);
    }
    public int getPuntJugador()
    {
        return this.puntJugador;
    }

    private void setEstado(int est){ //cambia turnos y habilita/deshabilita botones
        this.estado=est;
        switch (est){
            case CARTA1:
                //this.turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(true);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(false);
                break;
            case CARTA2:
                //this.turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(true);
                this.btnRecibir.setEnabled(true);
                this.btnPlantarse.setEnabled(true);
                break;
            case MAS21:
                //this.turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(false);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(true);
                break;
            case CASA:
                //this.turno.setText("Turno: Casa");
                this.btnApostar.setEnabled(false);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(false);
                break;
        }
    }

    public void mostrarMejoresPuntuaciones(View v) {

       Intent about = new Intent(this, MejoresPuntuaciones.class);
       about.putExtra("numMaxWins",numWinMax);
        about.putExtra("maxDinGan",dinGanadoMax);
        startActivity(about);

        }
    /*
     public void onClickAceptar(View view){


    int aux_dinGanadoMax = dinGanadoMax.getInteger().toInteger();
    int aux_numWinMax = numWinMax.getInteger().toInteger();

    if(!aux_dinGanadoMax.matches("") && !aux_numWinMax.matches("")) {

        Intent i = new Intent(this, Gracias.class);
        i.putExtra("nombre", aux_nombre);
        i.putExtra("apellido", aux_apellido);
        startActivity(i);
    }
    else{
        Toast.makeText(getApplicationContext(), "Debe ingresar datos", Toast.LENGTH_SHORT).show();
    }
}
}*/
    @Override public boolean onCreateOptionsMenu(Menu mimenu){

        getMenuInflater().inflate(R.menu.main_menu,mimenu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id=opcion_menu.getItemId();
        //mejoresPuntuaciones.setOnClickListener()
        if(id==R.id.NuevaPartida){
            /*Intent about = new Intent(getApplicationContext(), NuevaPartidaMain.class);
            startActivity(about);*/
    return true;
        }
        if(id==R.id.MejoresPuntuaciones){
            mostrarMejoresPuntuaciones(null);
           // return true;

        }
return super.onOptionsItemSelected(opcion_menu);
    }
}