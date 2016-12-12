package com.environment.Objets;

/**
 * Created by Killian on 12/12/2016.
 */
public class Clés extends Objets {
    private String passageAssocie;

    public Clés(String nom, String type, String description, String piece, String passageAssocie){
        super(nom, type, description, piece);
        this.passageAssocie = passageAssocie;
    }

    public String getPassageAssocie(){
        return this.passageAssocie;
    }
}
