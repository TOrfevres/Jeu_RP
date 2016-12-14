package com.entities;

import com.environment.Pieces;

/**
 * Created by user on 12/12/2016.
 */
public class Monstres extends Personnages {
    //CLASS définissant le fonctionnement d'un monstre/ennemi/danger selon ABSTRACT CLASS Personnages
    public Monstres(String name, int pointsVie, int pointsAttaque, Pieces pieceActuelle) {
        super(name, pointsVie, pointsAttaque, pieceActuelle);
    }

    public void attaque(Joueurs joueur) {
        //Méthode permettant de faire attaquer le monstre
        if(joueur.getPieceActuelle().getNom() == this.getPieceActuelle().getNom() && this.getPointsVie() > 0) {
            int defenseAleatoire = Math.toIntExact(Math.round(Math.random() * joueur.getPointsDefense()));
            int degatsNouvelleAttaque = this.getPointsAttaque() - defenseAleatoire;
            if(degatsNouvelleAttaque <= 0) {
                System.out.println("Vous venez d'esquiver l'attaque venant de " + this.getNom() + " !");
            } else if (degatsNouvelleAttaque > 0) {
                joueur.setPointsVie(joueur.getPointsVie() - degatsNouvelleAttaque);
                System.out.println(this.getNom() + " vient de vous attaquer et de vous infliger " + this.getPointsAttaque() + " points de dégats.");
            }
        }
    }
}
