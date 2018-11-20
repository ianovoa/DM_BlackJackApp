package es.uvigo.ingonzalezesei.blackjackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {
    private int puntJugador; //puntuacion total de jugador en esta ronda
    private int puntMaquina; //idem maquina
    private int dinGanado; //dinero total ganado por el jugador
    private int dinApostado; //dinero apostado en esta ronda
    private Baraja baraja=Baraja.getBaraja(); //baraja utilizada por el juego
    /*
    this.baraja.getCarta(); -> recibe objeto Carta de la baraja
    this.baraja.finalRonda(); -> reinicia la baraja una vez acabada una ronda
    */

    //variables botones interfaz
    private final Button btnRecibir=this.findViewById(R.id.recibir);
    private final Button btnPlantarse=this.findViewById(R.id.plantarse);
    private final Button btnApostar=this.findViewById(R.id.apostarBoton);

    //variables textos interfaz
    private final TextView turno=this.findViewById(R.id.turno);
    private final TextView dinero=this.findViewById(R.id.dinero);
    private final TextView puntJugador=this.findViewById(R.id.puntPropia);
    private final TextView puntCasa=this.findViewById(R.id.puntCasa);
    private final TextView apuesta=this.findViewById(R.id.apostadoTotal);

    private final ImageView imagenCarta=this.findViewById(R.id.imageView); //variable imagen interfaz

    private final EditText cantidadApostada=this.findViewById(R.id.cantidad); //variable EditText interfaz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
    }
}