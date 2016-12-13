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
        //Main.ajouterPieceAuNiveau(this);
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, Pieces> getSorties() {
        return this.sorties;
    }

    /*A appeller une fois que toutes les pieces ont été crées et l'appeller UNE fois par piece lors du chargement du niveau.
    public void setSorties() {
        for(Chaque ligne décrivant une sortie) {
            sorties.put("" Le nom du passage, Main.trouverPieceParNom("" Le nom de la piece));
            //Si il y a un chiffre en fin de ligne on créer la clé associé à ce passage
        }
    }
    */
}
