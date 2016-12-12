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
}
