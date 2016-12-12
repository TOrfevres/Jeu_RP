package com.environment.Objets;

import com.environment.Pieces;
import com.main.Main;

import java.lang.reflect.Type;

/**
 * Created by user on 12/12/2016.
 */
public abstract class Objets {
    private String nom;
    private Types type;
    private String description;
    private Pieces piece;

    public Objets(String nom, String type, String description, String piece){
        this.nom = nom;
        if(type == "I") {
            this.type = Types.Inforamatif;
        } else if(type == "O") {
            this.type = Types.Obtenable;
        } else if(type == "C") {
            this.type = Types.Clés;
        }
        this.description = description;
        this.piece = Main.trouverPieceParNom(piece);
    }

    public String getNom(){
        return this.nom;
    }

    public Types getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public Pieces getPiece(){
        return this.piece;
    }
}

