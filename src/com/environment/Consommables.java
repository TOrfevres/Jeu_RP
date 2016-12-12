package com.environment;

/**
 * Created by user on 12/12/2016.
 */
public class Consommables extends Objets{
    private int nombre;

    public Consommables(String nom, Types type, String description, Pieces piece) {
        super(nom, type, description, piece);
        this.nombre = 0;
    }
}
