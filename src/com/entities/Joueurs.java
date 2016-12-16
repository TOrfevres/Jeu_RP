package com.entities;

import com.environment.*;
import com.environment.items.*;
import com.main.Main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by user on 12/12/2016.
 */
public class Joueurs extends Personnages {
    //CLASS définissant le fonctionnement d'un joueur selon ABSTRACT CLASS Personnages.
    private String metier;                                                      //Définit le métier du joueur
    private int placesInventaire;                                               //Nombre de place libre initialement disponible dans l'inventaire
    private int pointsDefense;                                                  //Nombre de points de défense (servant à calculer la résistance du joueur à l'attaque d'un monstre)
    private Collection<Objets> inventaire = new ArrayList<>();     //Définit les objets actuellement dans l'inventaire
    private Collection<Pieces> piecesVisite = new ArrayList<>();

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
                    if(cle.getPassageAssocie().equals(nomPassage) && this.getPieceActuelle().getSorties().get(nomPassage).getNom().equals(piece.getNom())) {
                        cleAssocie = cle;
                    }
                }
            }

            if(cleAssocie == null) {
                System.out.println("Vous arrivez dans la pièce suivante : " + piece.getNom());
                System.out.print(piece.getDescription());
                this.setPieceActuelle(piece);
                if (!piecesVisite.contains(piece)){
                    piecesVisite.add(piece);
                }
            } else if(cleAssocie != null && inventaire.contains(cleAssocie)) {
                System.out.println("Vous arrivez dans la pièce suivante : " + piece.getNom());
                System.out.print(piece.getDescription());
                this.setPieceActuelle(piece);
                if (!piecesVisite.contains(piece)){
                    piecesVisite.add(piece);
                }
            } else if(cleAssocie != null && !inventaire.contains(cleAssocie)) {
                System.out.println("Ce passage semble bloqué. Il doit y avoir une clé quelque part ...");
            }
        } else {
            System.out.println("Erreur : Cette pièce n'est pas liée à celle où vous êtes.");
        }
    }

    int getPointsDefense() {
        return this.pointsDefense;
    }

    public void utilisationObjet(Objets objet) {
        //Si l'objet sélectionné est un objet du type informatif, on affiche sa description
        if(objet.getType() == Types.Inforamatif) {
            System.out.println(objet.getDescription());
        } else if(objet.getType() == Types.Obtenable && inventaire.contains(objet)) {   //Si l'objet sélectionné est récupérable et qu'il est dans l'inventaire, alors ...
            if(objet instanceof Armes) {                                                //... Si c'est une arme on dit que ce n'est pas possible de l'utiliser
                System.out.println("Vous ne pouvez pas utiliser cet objet !");
            } else if (objet instanceof Consommables) {                                 //... Si c'est un consommable (une potion), on redonne tout ses points de vie au joueur
                System.out.println("Vous utilisez une potion et vous regagnez toute votre vie. (" + this.getPointsVie() + "->" + this.getPointsVieMax() + ")");
                this.setPointsVie(this.getPointsVieMax());
                inventaire.remove(objet);
            }
        } else if(objet.getType() == Types.Obtenable && !inventaire.contains(objet)) {  //Si l'objet sélectionné est récupérable mais qu'il n'est pas dans l'inventaire, alors ...
            if(objet instanceof Armes) {                                                //... Si c'est une arme, on la ramasse
                System.out.println("Vous avez ramassé une nouvelle arme : " + objet.getNom());
                ajouterObjetAInventaire(objet);
            } else if (objet instanceof Consommables) {                                 //... Si c'est un consommable (une potion), on la ramasse
                System.out.println("Vous avez ramassé une potion de Rêverie !");
                ajouterObjetAInventaire(objet);
            }
        } else if(objet.getType() == Types.Clés && !inventaire.contains(objet)) {                                      //Si l'objet sélectionné est une clé, alors on dit que ce n'est pas possible de l'utiliser
                System.out.println("Vous venez de ramasser une nouvelle clé !");
                ajouterObjetAInventaire(objet);
        }
    }

    private void ajouterObjetAInventaire(Objets objet) {
        if(objet.getType() != Types.Inforamatif && inventaire.size() < placesInventaire && !inventaire.contains(objet)) {
            inventaire.add(objet);
        } else if (objet.getType() == Types.Inforamatif){
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
        System.out.println("Vous rentrer en combat contre : "+ monstre.getNom());
        //Tant que les deux combattants sont encore en vie, le joueur attaque le monstre puis inversement
        while(super.getPointsVie() > 0 && monstre.getPointsVie() > 0) {
            System.out.println("Vous infligez " + this.getPointsAttaque() + " points de dégats à " + monstre.getNom());
            monstre.setPointsVie(monstre.getPointsVie() - this.getPointsAttaque());
            if(monstre.getPointsVie() > 0) {
                monstre.attaque(this);
            }
        }
        //Le combat est terminé
        //Si le joueur est mort, alors on arrête le jeu
        if(super.getPointsVie() <= 0) {
            System.out.println("Quel cauchemar ! Vous avez perdu la vie ...");
            Main.enJeu = false;
        } else
            //Sinon si le monstre est mort, alors on affiche le nombre de points de vie qu'il reste au joueur
            if(monstre.getPointsVie() <= 0) {
            System.out.println("Vous avez vaincu " + monstre.getNom() + " !");
            System.out.println("Il vous reste " + this.getPointsVie() + " HP.");
            System.out.println();
            System.out.print("Que voulez vous faire :");
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

    public Collection<Pieces> getPiecesVisite() {
        return this.piecesVisite;
    }

    public void voirInventaire() {
        if(inventaire.size() > 0) {
            System.out.println("Dans votre inventaire, vous avez : ");
            for (Objets objet : inventaire) {
                System.out.println("- " + objet.getNom());
            }
        }
    }

    public Objets trouverObjetParNomNearJoueur(String nomObjet) {
        Objets objetRecherche = null;
        for(Objets objet : Main.tousLesObjets) {
            if(objet.getNom().equals(nomObjet) && this.getPieceActuelle().getNom().equals(objet.getPiece().getNom())) {
                objetRecherche = objet;
            }
        }
        return objetRecherche;
    }

    public Monstres trouverMonstreParNomNearJoueur(String nomMonstre) {
        Monstres monstreRecherche = null;
        for(Monstres monstre : Main.tousLesMonstres) {
            if(monstre.getNom().equals(nomMonstre) && this.getPieceActuelle().getNom().equals(monstre.getPieceActuelle().getNom())) {
                monstreRecherche = monstre;
            }
        }
        return monstreRecherche;
    }

    public Objets trouverObjetDansInventaireParNom(String nomObjet) {
        Objets objetRecherche = null;
        for(Objets objet : inventaire) {
            if(objet.getNom().equals(nomObjet)) {
                objetRecherche = objet;
            }
        }
        return objetRecherche;
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
