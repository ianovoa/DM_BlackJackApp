package es.uvigo.ingonzalezesei.blackjackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MejoresPuntuaciones extends AppCompatActivity {
    TextView textNumWinMax; //max numero de rondas ganadas seguidas
    TextView textDinGanadoMax; //max dinero total ganado por el jugador
    //DEFINO LA FLECHITA VOLVER:
    ImageView ivRegresar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mejores);

        Bundle extras = getIntent().getExtras(); //con blundle extras se recogen las variables (tambien se pueden poner las variables como public o protected)
        int numWinMax = extras.getInt("numWinMax");
        int dinGanadoMax = extras.getInt("dinGanadoMax");

        this.textNumWinMax=this.findViewById(R.id.numVictoriasMax);
        this.textDinGanadoMax=this.findViewById(R.id.dinMaxGanado);
        textNumWinMax.setText(Integer.toString(numWinMax));
        textDinGanadoMax.setText(Integer.toString(dinGanadoMax));

        //FLECHITA REGRESAR:
        ivRegresar2 = (ImageView) findViewById(R.id.ivRegresar2);
        ivRegresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
