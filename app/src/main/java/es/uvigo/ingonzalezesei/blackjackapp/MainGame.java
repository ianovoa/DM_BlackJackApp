package es.uvigo.ingonzalezesei.blackjackapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {
    private static String LogTag = MainGame.class.getSimpleName();

    private int puntJugador; //puntuacion total de jugador en esta ronda
    private int puntMaquina; //idem maquina
    private int dinGanado; //dinero total ganado por el jugador
    private int dinApostado; //dinero apostado en esta ronda

    private byte estado;
    private final byte CARTA1=1; //estado: primera carta jugador
    private final byte CARTA2=2; //estado: resto cartas jugador
    private final byte MAS21=3; //estado: el jugador se pasa de 21
    private final byte CASA=4; //estado: turno maquina

    private Baraja baraja=Baraja.getBaraja(); //baraja utilizada por el juego
    /*
    this.baraja.getCarta(); -> recibe objeto Carta de la baraja
    this.baraja.finalRonda(); -> reinicia la baraja una vez acabada una ronda
    */

    //variables botones interfaz
    private final Button btnRecibir=this.findViewById(R.id.recibir);
    private final Button btnPlantarse=this.findViewById(R.id.plantarse);
    private final Button btnApostar=this.findViewById(R.id.apostarBoton);

    private final ImageView imagenCarta=this.findViewById(R.id.imageView); //variable imagen interfaz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        //variables inicializadas
        this.setPuntJugador(0);
        this.setPuntMaquina(0);
        this.setDinGanado(1000);
        this.setDinApostado(0);
        this.setEstado(this.CARTA1);

        this.btnApostar.setOnClickListener((v) -> apostar());
        this.btnRecibir.setOnClickListener((v) -> recibirCarta());
        this.btnPlantarse.setOnClickListener((v) -> turnoMaquina());
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
        this.setEstado(Byte.parseByte(Integer.toString(prefs.getInt("estado",this.CARTA1))));
    }

    private void apostar(){
        EditText cantidadApostada=this.findViewById(R.id.cantidad);
        String apuesta=cantidadApostada.getText().toString();
        if(!apuesta.equals("") && Integer.parseInt(apuesta)>0 && Integer.parseInt(apuesta)<=this.dinGanado) {
            if (this.estado == this.CARTA1) this.setEstado(this.CARTA2);
            this.setDinApostado(this.dinApostado + Integer.parseInt(apuesta));
            Carta carta = this.baraja.getCarta();
            this.setPuntJugador(this.puntJugador + carta.getValue());
            this.imagenCarta.setImageResource(this.getResources().getIdentifier("DM_BlackJackApp:"+carta.getImage(),null,null));
            if(this.puntJugador>21) this.setEstado(this.MAS21);
        }
    }

    private void recibirCarta(){ //el jugador pide otra carta pero sin apostar

    }

    private void turnoMaquina(){ //la maquina intenta superar la punt del jugador sin pasarse de 21
        this.setEstado(this.CASA);

    }

    private void setPuntJugador(int punt){ //cambia puntuacion jugador y lo muestra en la vista
        this.puntJugador=punt;
        TextView textPuntJugador=this.findViewById(R.id.puntPropia);
        textPuntJugador.setText("Puntuacion jugador: "+this.puntJugador);
    }

    private void setPuntMaquina(int punt){ //cambia puntuacion maquina y lo muestra en la vista
        this.puntMaquina=punt;
        TextView textPuntCasa=this.findViewById(R.id.puntCasa);
        textPuntCasa.setText("Puntuacion casa: "+this.puntMaquina);
    }

    private void setDinGanado(int din){ //cambia dinero total y lo muestra en la vista
        this.dinGanado=din;
        TextView dinero=this.findViewById(R.id.dinero);
        dinero.setText("Dinero total: "+this.dinGanado);
    }

    private void setDinApostado(int din){ //cambia apuesta y lo muestra en la vista
        this.dinApostado=din;
        TextView apuesta=this.findViewById(R.id.apostadoTotal);
        apuesta.setText("Apostado: "+this.dinGanado);
    }

    private void setEstado(byte est){ //cambia turnos y habilita/deshabilita botones
        this.estado=est;
        TextView turno=this.findViewById(R.id.turno);
        switch (est){
            case CARTA1:
                turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(true);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(false);
            case CARTA2:
                turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(true);
                this.btnRecibir.setEnabled(true);
                this.btnPlantarse.setEnabled(true);
            case MAS21:
                turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(false);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(true);
            case CASA:
                turno.setText("Turno: Casa");
                this.btnApostar.setEnabled(false);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(false);
        }
    }
}