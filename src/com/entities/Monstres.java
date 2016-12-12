package com.entities;

import com.environment.Pieces;

/**
 * Created by user on 12/12/2016.
 */
public class Monstres extends Personnages {
    public Monstres(String name, int pointsVie, int pointsAttaque, int pointsDefense, Pieces pieceActuelle) {
        //Si le monstre n'est pas un boss, set les points de défense à 0, sinon set à une valeur proche à celle du joueur
        super(name, pointsVie, pointsAttaque, pointsDefense, pieceActuelle);
    }

    public void autoAttaque(Joueurs joueur) {
        if(joueur.getPieceActuelle().getNom() == this.getPieceActuelle().getNom() && this.getPointsVie() > 0) {
            int defenseAleatoire = Math.toIntExact(Math.round(Math.random() * joueur.getPointsDefense()));
            int degatsNouvelleAttaque = this.getPointsAttaque() - defenseAleatoire;
            if(degatsNouvelleAttaque <= 0) {
                System.out.println("Vous venez d'esquiver l'attaque venant de " + this.getName() + " !");
            } else if (degatsNouvelleAttaque > 0) {
                joueur.setPointsVie(joueur.getPointsVie() - degatsNouvelleAttaque);
                System.out.println(this.getName() + " vient de vous attaquer et de vous infliger " + this.getPointsAttaque() + " points de dégats.");
            }
        }
    }
}
