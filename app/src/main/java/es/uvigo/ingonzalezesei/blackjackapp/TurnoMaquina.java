package es.uvigo.ingonzalezesei.blackjackapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;



public class TurnoMaquina extends AppCompatActivity {
    TextView textGanador; //max numero de rondas ganadas seguidas
    TextView textPuntCasa; //max dinero total ganado por el jugador
    TextView textPuntJugador;
   // private RecyclerView listaCartas;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_maquina);
      //  LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //listaCartas.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras(); //con blundle extras se recogen las variables (tambien se pueden poner las variables como public o protected)
        int puntMaquina = extras.getInt("puntMaquina");
        int puntJugador = extras.getInt("puntJugador");
        String ganador = extras.getString("ganador");
        //ArrayList<Integer>  idCartas = (ArrayList<Integer>) getIntent().getSerializableExtra("idCartas");
       // listaCartas.setAdapter(new MyAdapter(idCartas));
        this.textGanador = this.findViewById(R.id.Ganador);
        this.textPuntCasa=this.findViewById(R.id.PuntCasa);
        this.textPuntJugador=this.findViewById(R.id.PuntJugador);
        //this.listaCartas=this.findViewById(R.id.list2);
       // MainGame.cartasCasa=this.findViewById(R.id.list2);
        textPuntCasa.setText(Integer.toString(puntMaquina));
        textPuntJugador.setText(Integer.toString(puntJugador));
        textGanador.setText(ganador);


    }
}
