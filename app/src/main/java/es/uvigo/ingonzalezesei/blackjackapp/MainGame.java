package es.uvigo.ingonzalezesei.blackjackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainGame extends AppCompatActivity {
    private int puntJugador; //puntuacion total de jugador en esta ronda
    private int puntMaquina;
    private int dinGanado; //dinero total ganado por el jugador
    private int dinApostado; //dinero apostado en esta ronda

    public int getDinApostado() {
        return dinApostado;
    }

    public int getDinGanado() {
        return dinGanado;
    }

    public int getPuntJugador() {
        return puntJugador;
    }

    public int getPuntMaquina() {
        return puntMaquina;
    }

    public void setDinApostado(int dinApostado) {
        this.dinApostado = dinApostado;
    }

    public void setDinGanado(int dinGanado) {
        this.dinGanado = dinGanado;
    }

    public void setPuntJugador(int puntJugador) {
        this.puntJugador = puntJugador;
    }

    public void setPuntMaquina(int puntMaquina) {
        this.puntMaquina = puntMaquina;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
    }
}