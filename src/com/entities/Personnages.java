package com.entities;

import com.environment.Pieces;

/**
 * Created by user on 12/12/2016.
 */
public class Personnages {
    private String name;
    private int pointsVie;
    private int pointsAttaque;
    private int pointsDefense;
    private Pieces pieceActuelle;

    public Personnages(String name, int pointsVie, int pointsAttaque, int pointsDefense, Pieces pieceActuelle) {
        this.name = name;
        this.pointsVie = pointsVie;
        this.pointsAttaque = pointsAttaque;
        this.pointsDefense = pointsDefense;
        this.pieceActuelle = pieceActuelle;
    }
}
