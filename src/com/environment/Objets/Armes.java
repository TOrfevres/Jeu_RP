package com.environment.Objets;

import com.main.Main;

/**
 * Created by user on 12/12/2016.
 */
public class Armes extends Objets { //penis
    private int degats;

    public Armes(String nom, String type, String description, String piece, int degats) {
        super(nom, type, description, piece);

        this.degats = degats;
    }

    public int getDegats() {
        return this.degats;
    }
}
