package es.uvigo.ingonzalezesei.blackjackapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;



public class TurnoMaquina extends AppCompatActivity {
    TextView textGanador; //max numero de rondas ganadas seguidas
    TextView textPuntCasa; //max dinero total ganado por el jugador
    TextView textPuntJugador;
   // private RecyclerView listaCartas;

    ListViewAdapter adapter;

    //Hago un String donde meto lo que va a salir por pantalla

    String[] titulo = new String[]{
            "as de corazones",
            "as de picas",
            "as de rombos",
            "as de trevoles",
            "10 de corazones",
            "2 de corazones",
            "3 de corazones",
            "4 de corazones",
            "5 de corazones",
            "6 de corazones",
            "7 de corazones",
            "8 de corazones",
            "9 de corazones",
            "jack de corazones",
            "jack de picas",
            "jack de rombos",
            "jack de trevoles",
            "king de corazones",
            "king de picas",
            "king de rombos",
            "king de trevoles",
            "10 de picas",
            "2 de picas",
            "3 de picas",
            "4 de picas",
            "5 de picas",
            "6 de picas",
            "7 de picas",
            "8 de picas",
            "9 de picas",
            "queen de corazones",
            "queen de picas",
            "queen de rombos",
            "queen de trevoles",
            "10 de rombos",
            "2 de rombos",
            "3 de rombos",
            "4 de rombos",
            "5 de rombos",
            "6 de rombos",
            "7 de rombos",
            "8 de rombos",
            "9 de rombos",
            "10 de trevoles",
            "2 de trevoles",
            "3 de trevoles",
            "4 de trevoles",
            "5 de trevoles",
            "6 de trevoles",
            "7 de trevoles",
            "8 de trevoles",
            "9 de trevoles"
    };
    //Ordeno las imagenes en el mismo orden que los titulos de arriba
    int[] imagenes = {
            R.drawable.ac,
            R.drawable.ap,
            R.drawable.ar,
            R.drawable.at,
            R.drawable.c10,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4,
            R.drawable.c5,
            R.drawable.c6,
            R.drawable.c7,
            R.drawable.c8,
            R.drawable.c9,
            R.drawable.jc,
            R.drawable.jp,
            R.drawable.jr,
            R.drawable.jt,
            R.drawable.kc,
            R.drawable.kp,
            R.drawable.kr,
            R.drawable.kt,
            R.drawable.p10,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.qc,
            R.drawable.qp,
            R.drawable.qr,
            R.drawable.qt,
            R.drawable.r10,
            R.drawable.r2,
            R.drawable.r3,
            R.drawable.r4,
            R.drawable.r5,
            R.drawable.r6,
            R.drawable.r7,
            R.drawable.r8,
            R.drawable.r9,
            R.drawable.t10,
            R.drawable.t2,
            R.drawable.t3,
            R.drawable.t4,
            R.drawable.t5,
            R.drawable.t6,
            R.drawable.t7,
            R.drawable.t8,
            R.drawable.t9
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_maquina);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //listaCartas.setLayoutManager(layoutManager);

        //Aqui se recoge la informacion que le mando a turno_maquina pero que aun no pertenece al listView
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

        //Aqui empiza lo que pertenece al ListView
        final ListView lista = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Nombre de la Carta" + i, Toast.LENGTH_SHORT).show();
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Nooombre de la Caaarta" + (i+1), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}
