package com.environment.items;

import com.environment.Pieces;
import com.main.Main;

/**
 * Created by user on 12/12/2016.
 */
public abstract class Objets {
    //ABSTRACT CLASS définissant le concept d'un objet (clé, arme, potion ...)
    private String nom;                 //Définit le nom de l'objet
    private String description;         //Définit la description de l'objet
    public Pieces piece;                //Définit la pièce où se trouve l'objet
    public Types type;                  //Définit le type de l'objet (I, O ou C)

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

