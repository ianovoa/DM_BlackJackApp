package es.uvigo.ingonzalezesei.blackjackapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TurnoMaquina extends AppCompatActivity {
    TextView textGanador; //max numero de rondas ganadas seguidas
    TextView textPuntCasa; //max dinero total ganado por el jugador
    TextView textPuntJugador;
    ListViewAdapter adapter;

    //DEFINO LA FLECHITA VOLVER:
    ImageView ivRegresar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_maquina);

        //Aqui se recoge la informacion que le mando a turno_maquina pero que aun no pertenece al listView
        Bundle extras = getIntent().getExtras(); //con blundle extras se recogen las variables (tambien se pueden poner las variables como public o protected)
        int puntMaquina = extras.getInt("puntMaquina");
        int puntJugador = extras.getInt("puntJugador");
        String ganador = extras.getString("ganador");

        this.textGanador = this.findViewById(R.id.Ganador);
        this.textPuntCasa=this.findViewById(R.id.PuntCasa);
        this.textPuntJugador=this.findViewById(R.id.PuntJugador);

        textPuntCasa.setText(Integer.toString(puntMaquina));
        textPuntJugador.setText(Integer.toString(puntJugador));
        textGanador.setText(ganador);

        //Aqui empiza lo que pertenece al ListView
        final ListView lista = findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this,MainGame.cartasCasa);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Nombre de la Carta " + (i+1), Toast.LENGTH_SHORT).show();
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                return false;
            }
        });

        //FLECHITA REGRESAR:
        ivRegresar = (ImageView) findViewById(R.id.ivRegresar);
        ivRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
