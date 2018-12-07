package es.uvigo.ingonzalezesei.blackjackapp;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MejoresPuntuaciones extends AppCompatActivity {
    private static String LogTag = MejoresPuntuaciones.class.getSimpleName();
    TextView textNumWinMax; //max numero de rondas ganadas seguidas
    TextView textDinGanadoMax; //max dinero total ganado por el jugador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mejores);
            this.textNumWinMax=this.findViewById(R.id.numVictoriasMax);
            this.textDinGanadoMax=this.findViewById(R.id.dinMaxGanado);
            textNumWinMax.setText(Integer.toString(MainGame.numWinMax));
            textDinGanadoMax.setText(Integer.toString(MainGame.dinGanadoMax));

            }

}
