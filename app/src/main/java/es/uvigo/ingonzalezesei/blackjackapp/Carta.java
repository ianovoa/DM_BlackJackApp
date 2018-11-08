package es.uvigo.ingonzalezesei.blackjackapp;

import android.media.Image;

public class Carta {
    private String name;
    private Image image;
    private int value;

    public Carta(String name,Image image,int value){
        this.name=name;
        this.image=image;
        this.value=value;
    }

    public String getName(){
        return this.name;
    }

    public Image getImage(){
        return this.image;
    }

    public int getValue(){
        return this.value;
    }
}