package com.entities;

import com.environment.Pieces;

/**
 * Created by user on 12/12/2016.
 */
public abstract class Personnages {
    private String name;
    private int maxVie;
    private int pointsVie;
    private int pointsAttaque;
    private int pointsDefense;
    private Pieces pieceActuelle;

    public Personnages(String name, int pointsVie, int pointsAttaque, int pointsDefense, Pieces pieceActuelle) {
        this.name = name;
        this.maxVie = pointsVie;
        this.pointsVie = pointsVie;
        this.pointsAttaque = pointsAttaque;
        this.pointsDefense = pointsDefense;
        this.pieceActuelle = pieceActuelle;
    }

    public String getName() {
        return this.name;
    }

    public int getPointsVie() {
        return this.pointsVie;
    }

    public int getPointsVieMax() {
        return this.maxVie;
    }

    public int getPointsAttaque() {
        return this.pointsAttaque;
    }

    public int getPointsDefense() {
        return this.pointsDefense;
    }

    public Pieces getPieceActuelle() {
        return this.pieceActuelle;
    }

    public void setPieceActuelle(Pieces piece) {
        this.pieceActuelle = piece;
    }

    public void setPointsVie(int pointsVie) {
        this.pointsVie = pointsVie;
    }
}
