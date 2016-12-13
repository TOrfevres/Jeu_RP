package com.entities;

import com.environment.*;
import com.environment.items.Armes;
import com.environment.items.Consommables;
import com.environment.items.Objets;
import com.environment.items.Types;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by user on 12/12/2016.
 */
public class Joueurs extends Personnages {
    private String metier;
    private int placesInventaire;
    private Collection<Objets> inventaire = new Collection<Objets>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Objets> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Objets objets) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Objets> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };

    public Joueurs(String name, int pointsVie, int pointsAttaque, int pointsDefense, Pieces pieceActuelle, String metier, int placesInventaire) {
        super(name, pointsVie, pointsAttaque, pointsDefense, pieceActuelle);

        this.metier = metier;
        this.placesInventaire = placesInventaire;
    }

    public void changerPiece(Pieces piece) {
        System.out.println("Vous arrivez dans la pièce suivante : " + piece.getNom());
        System.out.println("     " + piece.getDescription());
        this.setPieceActuelle(piece);
    }

    public void description() {
        System.out.println(this.getName() + " - " + this.metier + " - " + this.getPointsVie() + " HP - Déf: " + this.getPointsDefense() + " / Atk: " + this.getPointsAttaque() + " - " + this.placesInventaire + "places d'objet");
    }

    public void utilisationObjet(Objets objet) {
        if(objet.getType() == Types.Inforamatif) {
            objet.getDescription();
        } else if(objet.getType() == Types.Obtenable && inventaire.contains(objet)) {
            if(objet instanceof Armes) {
                System.out.println("Vous ne pouvez pas utiliser cet objet !");
            } else if (objet instanceof Consommables) {
                System.out.println("Vous utilisez une potion et vous regagnez toute votre vie. (" + this.getPointsVie() + "->" + this.getPointsVieMax() + ")");
                setPointsVie(this.getPointsVieMax());
                inventaire.remove(objet);
            }
        } else if(objet.getType() == Types.Clés) {
            System.out.println("Vous ne pouvez pas utiliser cet objet !");
        }
    }

    public void ajouterObjetAInventaire(Objets objet) {
        if(objet.getType() == Types.Obtenable) {
            inventaire.add(objet);
            System.out.println("Vous avez ramasser un(e): " + objet.getNom());
        } else {
            System.out.println("Vous ne pouvez pas ramasser cet objet !");
        }
    }

    @Override
    public int getPointsAttaque() {
        int maxDegats = 0;
        for(Objets objet : inventaire) {
            if(objet instanceof Armes && ((Armes) objet).getDegats() > maxDegats) {
                maxDegats = ((Armes) objet).getDegats();
            }
        }
        return super.getPointsAttaque() + maxDegats;
    }
}
