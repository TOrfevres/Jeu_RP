package com.entities;

import com.environment.*;
import com.environment.items.*;
import com.main.Main;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by user on 12/12/2016.
 */
public class Joueurs extends Personnages {
    //CLASS définissant le fonctionnement d'un joueur selon ABSTRACT CLASS Personnages
    private String metier;                                                      //Définit le métier du joueur
    private int placesInventaire;                                               //Nombre de place libre initialement disponible dans l'inventaire
    private int pointsDefense;                                                  //Nombre de points de défense (servant à calculer la résistance du joueur à l'attaque d'un monstre)
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
    };     //Définit les objets actuellement dans l'inventaire

    public Joueurs(String name, int pointsVie, int pointsAttaque, Pieces pieceActuelle, String metier, int placesInventaire, int pointsDefense) {
        super(name, pointsVie, pointsAttaque, pieceActuelle);

        this.metier = metier;
        this.placesInventaire = placesInventaire;
        this.pointsDefense = pointsDefense;
    }

    public void changerPiece(Pieces piece) {
        if(this.getPieceActuelle().getSorties().containsValue(piece)) {
            Clés cleAssocie = null;
            for(Clés cle : Main.porteCles) {
                for(String nomPassage : this.getPieceActuelle().getSorties().keySet()) {
                    if(cle.getPassageAssocie().equals(nomPassage)) {
                        cleAssocie = cle;
                    }
                }
            }

            if(cleAssocie == null) {
                System.out.println("Vous arrivez dans la pièce suivante : " + piece.getNom());
                System.out.println("     " + piece.getDescription());
                this.setPieceActuelle(piece);
            } else if(cleAssocie != null && inventaire.contains(cleAssocie)) {
                System.out.println("Vous arrivez dans la pièce suivante : " + piece.getNom());
                System.out.println("     " + piece.getDescription());
                this.setPieceActuelle(piece);
            } else if(cleAssocie != null && !inventaire.contains(cleAssocie)) {
                System.out.println("Ce passage semble bloqué. Il doit y avoir une clé quelque part ...");
            }
        } else {
            System.out.println("Erreur : Cette pièce n'est pas liée à celle où vous êtes.");
        }
    }

    public int getPointsDefense() {
        return this.pointsDefense;
    }

    public void utilisationObjet(Objets objet) {
        //Si l'objet sélectionné est un objet du type informatif, on affiche sa description
        if(objet.getType() == Types.Inforamatif) {
            System.out.println(objet.getDescription());
        } else
            //Si l'objet sélectionné est récupérable et qu'il est dans l'inventaire, alors ...
            if(objet.getType() == Types.Obtenable && inventaire.contains(objet)) {
            //... Si c'est une arme on dit que ce n'est pas possible de l'utiliser
            if(objet instanceof Armes) {
                System.out.println("Vous ne pouvez pas utiliser cet objet !");
            } else
                //... Si c'est un consommable (une potion), on redonne tout ses points de vie au joueur
                if (objet instanceof Consommables) {
                System.out.println("Vous utilisez une potion et vous regagnez toute votre vie. (" + this.getPointsVie() + "->" + this.getPointsVieMax() + ")");
                setPointsVie(this.getPointsVieMax());
                inventaire.remove(objet);
            }
        } else
            //Si l'objet sélectionné est récupérable mais qu'il n'est pas dans l'inventaire, alors ...
            if(objet.getType() == Types.Obtenable && !inventaire.contains(objet)) {
            //... Si c'est une arme, on la ramasse
            if(objet instanceof Armes) {
                System.out.println("Vous avez ramassé une nouvelle arme : " + objet.getNom());
                ajouterObjetAInventaire(objet);
            } else
                //... Si c'est un consommable (une potion), on la ramasse
                if (objet instanceof Consommables) {
                System.out.println("Vous avez ramassé une potion !");
                ajouterObjetAInventaire(objet);
            }
        } else
            //Si l'objet sélectionné est une clé, alors on dit que ce n'est pas possible de l'utiliser
            if(objet.getType() == Types.Clés) {
                System.out.println("Vous ne pouvez pas utiliser cet objet !");
        }
    }

    public void ajouterObjetAInventaire(Objets objet) {
        if(objet.getType() == Types.Obtenable && inventaire.size() < placesInventaire) {
            inventaire.add(objet);
            System.out.println("Vous avez ramasser un(e): " + objet.getNom());
        } else if (objet.getType() != Types.Obtenable){
            System.out.println("Vous ne pouvez pas ramasser cet objet !");
        } else if(inventaire.size() >= placesInventaire){
            System.out.println("Votre inventaire est plein ...");
        }
    }

    public void enleverObjetAInventaire(Objets objet) {
        if(inventaire.contains(objet) && objet.getType() != Types.Clés) {
            System.out.println("Vous venez de détruire " + objet.getNom());
            inventaire.remove(objet);
        } else if(objet.getType() == Types.Clés) {
            System.out.println("Ceci vous sera utile, vous ne pouvez pas le détruire !");
        } else if(!inventaire.contains(objet)){
            System.out.println("Erreur : Cet objet n'est pas dans l'inventaire.");
        }
    }

    public boolean estDansInventaire(Objets objet) {
        return this.inventaire.contains(objet);
    }

    public void attaquer(Monstres monstre) {
        //Tant que les deux combattants sont encore en vie, le joueur attaque le monstre puis inversement
        while(super.getPointsVie() > 0 && monstre.getPointsVie() > 0) {
            System.out.println("Vous infligez " + this.getPointsAttaque() + " points de dégats à " + monstre.getNom());
            monstre.setPointsVie(monstre.getPointsVie() - this.getPointsAttaque());
            monstre.attaque(this);
        }
        //Le combat est terminé

        //Si le joueur est mort, alors on arrête le jeu
        if(super.getPointsVie() <= 0) {
            System.out.println("Quel cauchemar ! Vous avez perdu la vie ...");
            System.exit(0);
        } else
            //Sinon si le monstre est mort, alors on affiche le nombre de points de vie qu'il reste au joueur
            if(monstre.getPointsVie() <= 0) {
            System.out.println("Vous avez vaincu " + monstre.getNom() + " !");
            System.out.println("Il vous reste " + this.getPointsVie() + " HP.");
        }
    }

    public int getNombrePotionDansInventaire() {
        int nombreDePotion = 0;
        for (Objets objet : inventaire) {
            if (objet instanceof Consommables) {
                nombreDePotion++;
            }
        }
        return nombreDePotion;
    }

    public int getNombreObjetsDansInventaire() {
        int nombre = 0;
        for(Objets objet : inventaire) {
            nombre++;
        }
        return nombre;
    }

    @Override
    //OVERRIDE permettant d'ajouter les dégats de l'arme au dégats de base du joueur
    //NB: On utilise les dégats bonus de l'arme la plus forte étant dans l'inventaire
    public int getPointsAttaque () {
            int maxDegats = 0;
            for (Objets objet : inventaire) {
                if (objet instanceof Armes && ((Armes) objet).getDegats() > maxDegats) {
                    maxDegats = ((Armes) objet).getDegats();
                }
            }
            return super.getPointsAttaque() + maxDegats;
    }
}
