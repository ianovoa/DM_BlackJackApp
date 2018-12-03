package es.uvigo.ingonzalezesei.blackjackapp;

import android.content.SharedPreferences;
import android.os.Handler;
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
    private TextView turno;
    private TextView apuesta;
    private TextView dinero;
    private TextView textPuntCasa;
    private TextView textPuntJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        this.btnRecibir=this.findViewById(R.id.recibir);
        this.btnPlantarse=this.findViewById(R.id.plantarse);
        this.btnApostar=this.findViewById(R.id.apostarBoton);

        this.imagenCarta=this.findViewById(R.id.imageView);

        this.turno=this.findViewById(R.id.turno);
        this.apuesta=this.findViewById(R.id.apostadoTotal);
        this.dinero=this.findViewById(R.id.dinero);
        this.textPuntCasa=this.findViewById(R.id.puntCasa);
        this.textPuntJugador=this.findViewById(R.id.puntPropia);

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
        this.setEstado(prefs.getInt("estado",this.CARTA1));
    }

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
        Handler handler = new Handler();
        this.setEstado(this.CASA);
        do{
            Carta carta=this.baraja.getCarta();
            this.setPuntMaquina(this.puntMaquina + carta.getValue());
            this.imagenCarta.setImageResource(this.getResources().getIdentifier(carta.getImage(),"drawable",getPackageName()));
            //handler.postDelayed(new Runnable(){public void run(){}},3000);
        }while (this.puntMaquina<21 && this.puntMaquina<=this.puntJugador);

        if(this.puntJugador<=21 && this.puntMaquina>21){ //jugador gana la partida
            this.setDinGanado(this.dinGanado+this.dinApostado*2);
        }
        else if(this.puntMaquina==this.puntJugador){ //jugador y maquina empatan
            this.setDinGanado(this.dinGanado+this.dinApostado);
        }

        this.setDinApostado(0);
        //handler.postDelayed(new Runnable(){public void run(){}},3000);

        this.setPuntJugador(0);
        this.setPuntMaquina(0);
        this.imagenCarta.setImageResource(R.drawable.tapas);
        this.baraja.finalRonda(); //reinicia la baraja una vez acabada la ronda
        this.setEstado(this.CARTA1);
    }

    private void setPuntJugador(int punt){ //cambia puntuacion jugador y lo muestra en la vista
        this.puntJugador=punt;
        this.textPuntJugador.setText("Puntuacion jugador: "+this.puntJugador);
    }

    private void setPuntMaquina(int punt){ //cambia puntuacion maquina y lo muestra en la vista
        this.puntMaquina=punt;
        this.textPuntCasa.setText("Puntuacion casa: "+this.puntMaquina);
    }

    private void setDinGanado(int din){ //cambia dinero total y lo muestra en la vista
        this.dinGanado=din;
        this.dinero.setText("Dinero total: "+this.dinGanado);
    }

    private void setDinApostado(int din){ //cambia apuesta y lo muestra en la vista
        this.dinApostado=din;
        this.setDinGanado(this.dinGanado-din); //din apostado = din perdido
        this.apuesta.setText("Apostado: "+this.dinApostado);
    }

    private void setEstado(int est){ //cambia turnos y habilita/deshabilita botones
        this.estado=est;
        switch (est){
            case CARTA1:
                this.turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(true);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(false);
                break;
            case CARTA2:
                this.turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(true);
                this.btnRecibir.setEnabled(true);
                this.btnPlantarse.setEnabled(true);
                break;
            case MAS21:
                this.turno.setText("Turno: Jugador");
                this.btnApostar.setEnabled(false);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(true);
                break;
            case CASA:
                this.turno.setText("Turno: Casa");
                this.btnApostar.setEnabled(false);
                this.btnRecibir.setEnabled(false);
                this.btnPlantarse.setEnabled(false);
                break;
        }
    }
}