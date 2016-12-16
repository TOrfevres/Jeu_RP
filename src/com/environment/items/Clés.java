package com.environment.items;

public class Clés extends Objets {
    //CLASS definissant le fonctionnement d'une clé selon ABSTRACT CLASS Objets
    private String passageAssocie;      //Permet de savoir quel est le passage que cette clé ouvre

    public Clés(String nom, String type, String description, String piece, String passageAssocie){
        super(nom, type, description, piece);
        this.passageAssocie = passageAssocie;
    }

    public String getPassageAssocie(){
        return this.passageAssocie;
    }
}
