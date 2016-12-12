package com.environment;

/**
 * Created by user on 12/12/2016.
 */
public abstract class Objets {
    private String nom;
    private Type type;
    private String description;
    private Pieces piece;

    public Objets(String nom, Type type, String description, Pieces piece){
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.piece = piece;
    }

    public String getNom(){
        return this.nom;
    }

    public Type getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public Pieces getPiece(){
        return this.piece;
    }
}

