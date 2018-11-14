package es.uvigo.ingonzalezesei.blackjackapp;

import java.util.ArrayList;
import java.util.Random;

public class Baraja {
    private static Baraja baraja=null;
    private ArrayList<Carta> cartas = new ArrayList<>();

    private Baraja(){
        this.cartas.add(new Carta("As de Corazones","@drawable/ac",1));
        this.cartas.add(new Carta("2 de Corazones","@drawable/c2",2));
        this.cartas.add(new Carta("3 de Corazones","@drawable/c3",3));
        this.cartas.add(new Carta("4 de Corazones","@drawable/c4",4));
        this.cartas.add(new Carta("5 de Corazones","@drawable/c5",5));
        this.cartas.add(new Carta("6 de Corazones","@drawable/c6",6));
        this.cartas.add(new Carta("7 de Corazones","@drawable/c7",7));
        this.cartas.add(new Carta("8 de Corazones","@drawable/c8",8));
        this.cartas.add(new Carta("9 de Corazones","@drawable/c9",9));
        this.cartas.add(new Carta("10 de Corazones","@drawable/c10",10));
        this.cartas.add(new Carta("Jack de Corazones","@drawable/jc",10));
        this.cartas.add(new Carta("Reina de Corazones","@drawable/qc",10));
        this.cartas.add(new Carta("Rey de Corazones","@drawable/kc",10));

        this.cartas.add(new Carta("As de Picas","@drawable/ap",1));
        this.cartas.add(new Carta("2 de Picas","@drawable/p2",2));
        this.cartas.add(new Carta("3 de Picas","@drawable/p3",3));
        this.cartas.add(new Carta("4 de Picas","@drawable/p4",4));
        this.cartas.add(new Carta("5 de Picas","@drawable/p5",5));
        this.cartas.add(new Carta("6 de Picas","@drawable/p6",6));
        this.cartas.add(new Carta("7 de Picas","@drawable/p7",7));
        this.cartas.add(new Carta("8 de Picas","@drawable/p8",8));
        this.cartas.add(new Carta("9 de Picas","@drawable/p9",9));
        this.cartas.add(new Carta("10 de Picas","@drawable/p10",10));
        this.cartas.add(new Carta("Jack de Picas","@drawable/jp",10));
        this.cartas.add(new Carta("Reina de Picas","@drawable/qp",10));
        this.cartas.add(new Carta("Rey de Picas","@drawable/kp",10));

        this.cartas.add(new Carta("As de Rombos","@drawable/ar",1));
        this.cartas.add(new Carta("2 de Rombos","@drawable/r2",2));
        this.cartas.add(new Carta("3 de Rombos","@drawable/r3",3));
        this.cartas.add(new Carta("4 de Rombos","@drawable/r4",4));
        this.cartas.add(new Carta("5 de Rombos","@drawable/r5",5));
        this.cartas.add(new Carta("6 de Rombos","@drawable/r6",6));
        this.cartas.add(new Carta("7 de Rombos","@drawable/r7",7));
        this.cartas.add(new Carta("8 de Rombos","@drawable/r8",8));
        this.cartas.add(new Carta("9 de Rombos","@drawable/r9",9));
        this.cartas.add(new Carta("10 de Rombos","@drawable/r10",10));
        this.cartas.add(new Carta("Jack de Rombos","@drawable/jr",10));
        this.cartas.add(new Carta("Reina de Rombos","@drawable/qr",10));
        this.cartas.add(new Carta("Rey de Rombos","@drawable/kr",10));

        this.cartas.add(new Carta("As de Treboles","@drawable/at",1));
        this.cartas.add(new Carta("2 de Treboles","@drawable/t2",2));
        this.cartas.add(new Carta("3 de Treboles","@drawable/t3",3));
        this.cartas.add(new Carta("4 de Treboles","@drawable/t4",4));
        this.cartas.add(new Carta("5 de Treboles","@drawable/t5",5));
        this.cartas.add(new Carta("6 de Treboles","@drawable/t6",6));
        this.cartas.add(new Carta("7 de Treboles","@drawable/t7",7));
        this.cartas.add(new Carta("8 de Treboles","@drawable/t8",8));
        this.cartas.add(new Carta("9 de Treboles","@drawable/t9",9));
        this.cartas.add(new Carta("10 de Treboles","@drawable/t10",10));
        this.cartas.add(new Carta("Jack de Treboles","@drawable/jt",10));
        this.cartas.add(new Carta("Reina de Treboles","@drawable/qt",10));
        this.cartas.add(new Carta("Rey de Treboles","@drawable/kt",10));

        barajar();
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
        this.barajar();
        return toret;
    }

    public static Baraja getBaraja(){
        if(baraja==null) baraja=new Baraja();
        return baraja;
    }
}
