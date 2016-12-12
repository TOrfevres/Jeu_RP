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

    public void setSorties() {

    }
}
