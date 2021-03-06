package com.environment.items;

public class Armes extends Objets {
    //CLASS definissant le fonctionnement d'une arme selon ABSTRACT CLASS Objets.
    private int degats;         //Nombre de points bonus d'attaque que cette arme apporte

    public Armes(String nom, String type, String description, String piece, int degats) {
        super(nom, type, description, piece);
        this.degats = degats;
    }

    public int getDegats() {
        return this.degats;
    }
}
