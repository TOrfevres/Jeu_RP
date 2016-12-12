package com.entities;

import com.environment.Objets;
import com.environment.Pieces;
import com.environment.Type;

/**
 * Created by user on 12/12/2016.
 */
public class Joueurs extends Personnages {
    private String metier;
    private int placesInventaire;

    public Joueurs(String name, int pointsVie, int pointsAttaque, int pointsDefense, Pieces pieceActuelle, String metier, int placesInventaire) {
        super(name, pointsVie, pointsAttaque, pointsDefense, pieceActuelle);

        this.metier = metier;
        this.placesInventaire = placesInventaire;
    }

    public void changerPiece(Pieces piece) {
        System.out.println("Vous arrivez dans la pièce suivante : " + piece.getNom());
        this.setPieceActuelle(piece);
    }

    public void description() {
        System.out.println(this.getName() + " - " + this.metier + " - " + this.getPointsVie() + " HP - Déf: " + this.getPointsDefense() + " / Atk: " + this.getPointsAttaque() + " - " + this.placesInventaire + "places d'objet");
    }

    public void utilisationObjet(Objets objet) {
        if(objet.getType() == Type.Inforamatif) {
            objet.getDescription();
        } else if(objet.getType() == Type.Obtenable) {
            //Interface objet avec boolean (on rammasse)
            if(objet instanceof Armes) {
                System.out.println("Vous ne pouvez pas utiliser cet objet !");
            } else if (objet instanceof Consommable) {
                //setPointsVie(this.getPointsVie() + objet.getValeurSoin());
                //objet.setNombre(objet.getNombre() - 1);
            }
        } else if(objet.getType() == Type.Clés) {

        }
    }
}
