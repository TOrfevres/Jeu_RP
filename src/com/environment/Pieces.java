package com.environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 12/12/2016.
 */
public class Pieces {
    //CLASS definissant le fonctionnement d'une pièce
    private String nom;                                                     //Définit le nom de la pièce
    private String description;                                             //Définit la description de la pièce
    private Map<String,Pieces> sorties = new HashMap<>();     //Détermine quels sont les passages et sorties associés à cette pièce

    public Pieces(String nom, String description) {
        this.nom = nom;
        this.description = description;
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

    public void setSorties(String nomSortie, Pieces destSortie){
        sorties.put(nomSortie, destSortie);
    }
}
