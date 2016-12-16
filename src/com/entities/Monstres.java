package com.entities;

import com.environment.Pieces;

public class Monstres extends Personnages {
    //CLASS définissant le fonctionnement d'un monstre/ennemi/danger selon ABSTRACT CLASS Personnages
    public Monstres(String name, int pointsVie, int pointsAttaque, Pieces pieceActuelle) {
        super(name, pointsVie, pointsAttaque, pieceActuelle);
    }

    public void attaque(Joueurs joueur) {
        //Méthode permettant de faire attaquer le monstre

        //Si le joueur est dans la même salle que le monstre et que celui-ci est en vie, alors le monstre attaque le joueur
        if(joueur.getPieceActuelle().getNom().equals(this.getPieceActuelle().getNom()) && this.getPointsVie() > 0) {
            int defenseAleatoire = Math.toIntExact(Math.round(Math.random() * joueur.getPointsDefense()));
            int degatsNouvelleAttaque = this.getPointsAttaque() - defenseAleatoire;
            if(degatsNouvelleAttaque <= 0) {
                System.out.println("Vous venez d'esquiver l'attaque venant de " + this.getNom() + " !");
            } else if (degatsNouvelleAttaque > 0) {
                joueur.setPointsVie(joueur.getPointsVie() - degatsNouvelleAttaque);
                System.out.println(this.getNom() + " vient de vous attaquer et de vous infliger " + degatsNouvelleAttaque + " points de dégats.");
            }
        } else if(!joueur.getPieceActuelle().getNom().equals(this.getPieceActuelle().getNom())) {
            System.out.println("Erreur : Ce monstre n'est pas dans la même salle que le joueur");
        } else if(this.getPointsVie() <= 0) {
            System.out.println("Erreur : Ce monstre est mort");
        }
    }
}
