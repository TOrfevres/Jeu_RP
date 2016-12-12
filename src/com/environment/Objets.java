package com.environment;

/**
 * Created by user on 12/12/2016.
 */
public abstract class Objets {
    private String nom;
    private Types types;
    private String description;
    private Pieces piece;

    public Objets(String nom, Types type, String description, Pieces piece){
        this.nom = nom;
        this.types = type;
        this.description = description;
        this.piece = piece;
    }

    public String getNom(){
        return this.nom;
    }

    public Types getTypes(){
        return this.types;
    }

    public String getDescription(){
        return this.description;
    }

    public Pieces getPiece(){
        return this.piece;
    }
}

