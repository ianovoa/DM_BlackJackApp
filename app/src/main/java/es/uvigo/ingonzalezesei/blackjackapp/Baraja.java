package es.uvigo.ingonzalezesei.blackjackapp;

import java.util.ArrayList;
import java.util.Random;

public class Baraja {
    private static Baraja baraja=null;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Carta> descartes = new ArrayList<>(); //conjunto de cartas ya usadas

    private Baraja(){
        this.cartas.add(new Carta("As de Corazones","ac",1));
        this.cartas.add(new Carta("2 de Corazones","c2",2));
        this.cartas.add(new Carta("3 de Corazones","c3",3));
        this.cartas.add(new Carta("4 de Corazones","c4",4));
        this.cartas.add(new Carta("5 de Corazones","c5",5));
        this.cartas.add(new Carta("6 de Corazones","c6",6));
        this.cartas.add(new Carta("7 de Corazones","c7",7));
        this.cartas.add(new Carta("8 de Corazones","c8",8));
        this.cartas.add(new Carta("9 de Corazones","c9",9));
        this.cartas.add(new Carta("10 de Corazones","c10",10));
        this.cartas.add(new Carta("Jack de Corazones","jc",10));
        this.cartas.add(new Carta("Reina de Corazones","qc",10));
        this.cartas.add(new Carta("Rey de Corazones","kc",10));

        this.cartas.add(new Carta("As de Picas","ap",1));
        this.cartas.add(new Carta("2 de Picas","p2",2));
        this.cartas.add(new Carta("3 de Picas","p3",3));
        this.cartas.add(new Carta("4 de Picas","p4",4));
        this.cartas.add(new Carta("5 de Picas","p5",5));
        this.cartas.add(new Carta("6 de Picas","p6",6));
        this.cartas.add(new Carta("7 de Picas","p7",7));
        this.cartas.add(new Carta("8 de Picas","p8",8));
        this.cartas.add(new Carta("9 de Picas","p9",9));
        this.cartas.add(new Carta("10 de Picas","p10",10));
        this.cartas.add(new Carta("Jack de Picas","jp",10));
        this.cartas.add(new Carta("Reina de Picas","qp",10));
        this.cartas.add(new Carta("Rey de Picas","kp",10));

        this.cartas.add(new Carta("As de Rombos","ar",1));
        this.cartas.add(new Carta("2 de Rombos","r2",2));
        this.cartas.add(new Carta("3 de Rombos","r3",3));
        this.cartas.add(new Carta("4 de Rombos","r4",4));
        this.cartas.add(new Carta("5 de Rombos","r5",5));
        this.cartas.add(new Carta("6 de Rombos","r6",6));
        this.cartas.add(new Carta("7 de Rombos","r7",7));
        this.cartas.add(new Carta("8 de Rombos","r8",8));
        this.cartas.add(new Carta("9 de Rombos","r9",9));
        this.cartas.add(new Carta("10 de Rombos","r10",10));
        this.cartas.add(new Carta("Jack de Rombos","jr",10));
        this.cartas.add(new Carta("Reina de Rombos","qr",10));
        this.cartas.add(new Carta("Rey de Rombos","kr",10));

        this.cartas.add(new Carta("As de Treboles","at",1));
        this.cartas.add(new Carta("2 de Treboles","t2",2));
        this.cartas.add(new Carta("3 de Treboles","t3",3));
        this.cartas.add(new Carta("4 de Treboles","t4",4));
        this.cartas.add(new Carta("5 de Treboles","t5",5));
        this.cartas.add(new Carta("6 de Treboles","t6",6));
        this.cartas.add(new Carta("7 de Treboles","t7",7));
        this.cartas.add(new Carta("8 de Treboles","t8",8));
        this.cartas.add(new Carta("9 de Treboles","t9",9));
        this.cartas.add(new Carta("10 de Treboles","t10",10));
        this.cartas.add(new Carta("Jack de Treboles","jt",10));
        this.cartas.add(new Carta("Reina de Treboles","qt",10));
        this.cartas.add(new Carta("Rey de Treboles","kt",10));

        this.barajar();
    }

    private void barajar(){
        ArrayList<Carta> aux=this.cartas;
        ArrayList<Carta> toret=new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        while(!aux.isEmpty()){
            random.setSeed(System.currentTimeMillis());
            int next=random.nextInt(aux.size());
            toret.add(aux.get(next));
            aux.remove(next);
        }
        this.cartas=toret;
    }

    public Carta getCarta(){
        Carta toret=this.cartas.get(0);
        this.descartes.add(toret);
        this.cartas.remove(toret); //una carta no puede aparecer dos veces en una misma ronda
        this.barajar();
        return toret;
    }

    public void finalRonda(){ //se recuperan todos los descartes
        for(Carta carta:this.descartes) this.cartas.add(carta);
        this.descartes=new ArrayList<>();
        this.barajar();
    }

    public static Baraja getBaraja(){
        if(baraja==null) baraja=new Baraja();
        return baraja;
    }
}
