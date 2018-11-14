package es.uvigo.ingonzalezesei.blackjackapp;

public class Carta {
    private String name;
    private String srcImage;
    private int value;

    public Carta(String name,String srcImage,int value){
        this.name=name;
        this.srcImage=srcImage;
        this.value=value;
    }

    public String getName(){
        return this.name;
    }

    public String getImage(){
        return this.srcImage;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}