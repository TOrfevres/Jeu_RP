package com.entities;

import com.environment.Pieces;

/**
 * Created by user on 12/12/2016.
 */
public abstract class Personnages {
    //ABSTRACT CLASS définissant le concept du personnage (monstres, ennemis, joueurs ...)
    private String nom;             //Nom du personnage
    private int maxVie;             //Nombre maximum de point de vie
    private int pointsVie;          //Nombre courant de points de vie
    private int pointsAttaque;      //Nombre de points d'attaque (servant à déterminer la puissance de l'attaque du personnage)
    private Pieces pieceActuelle;   //Détermine la pièce où est actuellemnt le personnage

    public Personnages(String nom, int pointsVie, int pointsAttaque, Pieces pieceActuelle) {
        this.nom = nom;
        this.maxVie = pointsVie;
        this.pointsVie = pointsVie;
        this.pointsAttaque = pointsAttaque;
        this.pieceActuelle = pieceActuelle;
    }

    public String getNom() {
        return this.nom;
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
