package com.environment;

import com.main.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 12/12/2016.
 */
public class Pieces {
    private String nom;
    private String description;
    private Map<String,Pieces> sorties = new HashMap<String, Pieces>();

    public Pieces(String nom, String description) {
        this.nom = nom;
        this.description = description;
        Main.ajouterPieceAuNiveau(this);
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }

    //A appeller une fois que toutes les pieces ont été crées et l'appeller UNE fois par piece lors du chargement du niveau.
    public void setSorties() {
        for(Pieces piece : Main.niveau) {
            for(/*Chaque ligne décrivant une sortie*/) {
                sorties.put("" /*Le nom du passage*/, Main.trouverPieceParNom("" /*Le nom de la piece*/));
                //COMMENT ON FAIT POUR DETERMINER SI UNE SORTIE NECESSITE UNE CLE ?
            }
        }
    }
}
