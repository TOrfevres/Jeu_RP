package com.main;

import com.entities.Joueurs;
import com.entities.Monstres;
import com.environment.Pieces;
import com.environment.items.Clés;
import com.environment.items.Objets;
import com.environment.items.Types;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Collection<Pieces> niveauAlpha = new ArrayList<>();
    public static Collection<Clés> porteCles = new ArrayList<>();
    public static Collection<Objets> tousLesObjets = new ArrayList<>();
    public static Collection<Monstres> tousLesMonstres = new ArrayList<>();
    public static Map<String, String> options = new HashMap<>();

    static String csvFile = "";
    static Scanner sc;
    static String line = null;                                     //Peut importe la valeur
    static String cvsSplitBy = ";";                                // Separation des caracteres
    static boolean retry = true;
    static Joueurs joueur;

    public static void main(String[] args) {
        System.out.println("  << JMistery >>");
        System.out.println("Création d'un nouveau scénario...");

        selectionMonde();
        selectionPersonnage();
    }

    public static void  selectionPersonnage(){
        System.out.println();
        System.out.println("Saisir l'emplacement du fichier de description des personnages à charger pour ce scénario : ");
        while (retry) {
            try { // D:/Users/Kirian/Bureau/csvtest.csv
                BufferedReader br = new BufferedReader(new FileReader(csvFile));
                int numPerso = 0;
                int nbrPerso=-1;
                int annuler = 0;
                int persoAChoisir = 0;
                while (br.readLine() != null) {
                    nbrPerso++;
                }
                br.close();
                br = new BufferedReader(new FileReader(csvFile));
                System.out.println("-- Le Scénario comportera "+ nbrPerso +" Personnages." + '\n');

                System.out.println("Sélectionner le personnage du Scénario que vous souhaitez incarner. ");
                br.readLine();// call la premiere ligne pour la skip

                while ((line = br.readLine()) != null) {               //Tant que il y a des lignes...
                    numPerso++;
                    String lesPersos[] = line.split(cvsSplitBy);          // Decoupe chaque element entre ";" dans des cellules diff
                    System.out.println(numPerso+". " + lesPersos[0] + " - " + lesPersos[1] + " - " + lesPersos[2] + " HP - Def: " + lesPersos[3] + " / Atk " + lesPersos[4] + " - " + lesPersos[5] + " places d'objet ");

                    retry = false;
                }
                br.close();
                br = new BufferedReader(new FileReader(csvFile));
                annuler = numPerso+1;
                System.out.println(annuler +". Annuler");

                while (persoAChoisir < 1 || persoAChoisir >annuler || persoAChoisir == 0) {

                    try {
                        System.out.print(">");
                        sc = new Scanner(System.in);
                        persoAChoisir = sc.nextInt();
                    } catch (Exception e) {
                        persoAChoisir = 0;
                    }

                }

                if (persoAChoisir==annuler){
                    System.out.println("Vous avez annuler :( ");
                } else {
                    for (int k=0; k<persoAChoisir; k++) {
                        br.readLine(); // skip les ligne contenant les perso non désirée
                    }
                    String[] persoSelect = br.readLine().split(";");
                    System.out.println("Vous avez sélectionné : " + persoSelect[0]);
                    joueur = new Joueurs(persoSelect[0], Integer.decode(persoSelect[2]), Integer.decode(persoSelect[3]), null, persoSelect[1], Integer.decode(persoSelect[5]), Integer.decode(persoSelect[4]));
                }

            } catch (Exception FileNotFoundException) { //Rajouter des throw ????
                sc = new Scanner(System.in);
                System.out.print(">");
                csvFile = sc.next();
            }
        }
        retry=true;
    }

    public static Pieces trouverPieceParNom(String nomPiece) {
        Pieces pieceRecherche = null;
        for(Pieces piece : niveauAlpha) {
            if(piece.getNom().equals(nomPiece)) {
                pieceRecherche = piece;

            }
        }

        return pieceRecherche;
    }

    public static Clés trouverCleParPassage(String nomPassage) {
        Clés cleRecherchee = null;
        for(Clés cle : porteCles) {
            if(cle.getPassageAssocie().equals(nomPassage)) {
                cleRecherchee = cle;
            }
        }
        return cleRecherchee;
    }

    public static Objets trouverObjetParNom(String nomObjet) {
        Objets objetRecherche = null;
        for(Objets objet : tousLesObjets) {
            if(objet.getNom().equals(nomObjet)) {
                objetRecherche = objet;
            }
        }
        return objetRecherche;
    }

    public static Monstres trouverMonstreParNom(String nomMonstre) {
        Monstres monstreRecherche = null;
        for(Monstres monstre : tousLesMonstres) {
            if(monstre.getNom().equals(nomMonstre)) {
                monstreRecherche = monstre;
            }
        }
        return monstreRecherche;
    }

    public static void selectionMonde(){
        String cheminJeu = "";
        String nomJeu = "";
        String cheminCarte = "";
        String cheminObjet = "";
        String cheminMonstre = "";
        String descriptionJeu = "";
        String cheminSimple = "";

        //boolean retry = true;
        //Scanner sc;
        System.out.println("Saisissez l'emplacement du fichier de description du niveau pour ce Scénario : ");
        while (retry) { // D:/Users/Kirian/Bureau/niveau.txt
            try {
                BufferedReader br = new BufferedReader(new FileReader(cheminJeu));
                nomJeu = br.readLine();
                System.out.println();
                System.out.println(nomJeu);

                cheminCarte = br.readLine();
                cheminObjet = br.readLine();
                cheminMonstre = br.readLine();

                while ((descriptionJeu = br.readLine()) != null) {
                    System.out.println("  "+descriptionJeu);
                }

                cheminSimple = cheminJeu.substring(0, (cheminJeu.lastIndexOf("/") + 1));

                cheminCarte = cheminSimple + cheminCarte;
                cheminObjet = cheminSimple + cheminObjet;
                cheminMonstre = cheminSimple + cheminMonstre;


                chargementMonstre(cheminMonstre);
                chargementObjet(cheminObjet);
                chargementCarte(cheminCarte);
                chargementSortie(cheminCarte);

                retry = false;
            } catch (Exception FileNotFoundException) { // Rajouter des throw ????
                sc = new Scanner(System.in);
                System.out.print(">");
                cheminJeu = sc.next();
            }


        }
        retry =true;
        //Algo trouvant le nom de chaque salle
        //Créer une piece pour chaque nom avec son nom et sa description
        //Mettre la piece dans le niveau (ajouterPieceAuNiveau(Piece))

        //Algo trouvant la partie du fichier texte correspondant au nom de l'objet (boucle foreach objet dans le niveau)
        //Appliquer la méthode "setSorties()" à l'objet
    }

    public static void options() {
        int choix = 0;
        Collection<Objets> objetsDansSalle = new ArrayList<>();
        Collection<Monstres> monstresDansSalle = new ArrayList<>();

        for (Objets objet : tousLesObjets) {
            if (joueur.getPieceActuelle().equals(objet.getPiece()) && objet.getType() == Types.Obtenable){
                objetsDansSalle.add(objet);
            }
        }

        for (Objets objet : objetsDansSalle) {
            if(!joueur.estDansInventaire(objet)) {
                choix++;
                System.out.println(choix + ". Inspecter l'objet " + objet.getNom());
                options.put("Utiliser", objet.getNom());
            }
        }

        for (Monstres monstre : tousLesMonstres) {
            if (joueur.getPieceActuelle().equals(monstre.getPieceActuelle())){
                monstresDansSalle.add(monstre);
            }
        }

        for (Monstres monstre : monstresDansSalle) {
            if(monstre.getPointsVie() > 0) {
                choix++;
                System.out.println(choix + ". Attaquer le danger " + monstre.getNom());
                options.put("Attaquer", monstre.getNom());
            }
        }

        for (String nomPassage : joueur.getPieceActuelle().getSorties().keySet()){
            choix++;
            System.out.println(choix + ". Sortir de cette pièce et emprunter la direction " + nomPassage);
            options.put("Bouger", nomPassage);
        }

        if (joueur.getNombrePotionDansInventaire() > 0){
            choix++;
            System.out.println(choix + ". Utiliser une potion de l'inventaire (Vos HP: " + joueur.getPointsVie() + "/" + joueur.getPointsVieMax() + ")");
            options.put("Boire", null);
        }

        if(joueur.getNombreObjetsDansInventaire() > 0) {
            choix++;
            System.out.println(choix + ". Voir l'inventaire");
            options.put("Voir", null);
        }

        int optionChoisie = 0;
        while(optionChoisie < 1 || optionChoisie > choix) {
            try {
                sc = new Scanner(System.in);
                optionChoisie = sc.nextInt();
            }   catch (Exception e) {
                optionChoisie = 0;
            }
        }

        int i = 0;
        for(String typeOption : options.keySet()) {
            i++;
            if(optionChoisie == i) {
                if(typeOption.equals("Utiliser")) {
                    joueur.utilisationObjet(trouverObjetParNom(options.get(typeOption)));
                } else if(typeOption.equals("Attaquer")) {
                    joueur.attaquer(trouverMonstreParNom(options.get(typeOption)));
                } else if(typeOption.equals("Bouger")) {
                    joueur.changerPiece(trouverPieceParNom(options.get(typeOption)));
                } else if(typeOption.equals("Boire")) {
                    joueur.utilisationObjet(trouverObjetParNom(options.get(typeOption)));
                } else if(typeOption.equals("Voir")) {
                    joueur.voirInventaire();
                }
            }
        }
    }

    public static void chargementMonstre(String chemin){
        try {
            BufferedReader monstre = new BufferedReader(new FileReader(chemin));
        } catch (FileNotFoundException e) {
            System.out.println("Le nom du fichier pour les monstres spécifié dans votre texte Niveau est inchorect.");
        }
    }

    public static void chargementObjet(String chemin){
        try {
            BufferedReader objet = new BufferedReader(new FileReader(chemin));
        } catch (FileNotFoundException e) {
            System.out.println("Le nom du fichier pour les objets spécifié dans votre texte Niveau est inchorect.");
        }
    }

    public static void chargementCarte(String chemin){
        String ligne = "";
        try {
            BufferedReader carte = new BufferedReader(new FileReader(chemin));
            while ((ligne = carte.readLine()) != null) {
                if (ligne.equals("<")) {
                    String nom = carte.readLine();
                    nom = nom.substring(1, (nom.length() - 1));       // recup nom pieces
                    carte.readLine();   //saute une ligne

                    String desc = carte.readLine();                     //recup toutes la desc
                    while (!(ligne = carte.readLine()).equals("\\")) {
                        desc = desc + "\r\n" + ligne;
                    }
                    Pieces bjr = new Pieces(nom, desc);
                    niveauAlpha.add(bjr);              //creee l'entitee
                }
            }
        } catch (IOException e) {
            System.out.println("Le nom du fichier pour les cartes spécifié dans votre texte Niveau est inchorect.");
        }
    }

    public static void chargementSortie(String chemin){
        String ligne = "";
        try {
            BufferedReader carte = new BufferedReader(new FileReader(chemin));

            while ((ligne = carte.readLine()) != null) {
                if (ligne.equals("<")) {
                    String nom = carte.readLine();
                    nom = nom.substring(1, (nom.length() - 1));       // recup nom pieces
                    Pieces maPiece = trouverPieceParNom(nom);

                    //utiliser la method présente dans piece pour lier sortie a la piece
                    carte.readLine();   //saute une ligne

                    String desc = carte.readLine();                     //recup toutes la desc
                    while (!(ligne = carte.readLine()).equals("\\")) {}

                    while (!(ligne = carte.readLine()).equals(">")) {
                        String nomPorte = "";
                        String destPorte = "";
                        String presenceCle = "";

                        nomPorte = ligne.substring(1, nomPorte.indexOf("\""));
                        destPorte = ligne.substring(nomPorte.length() + 4, ligne.lastIndexOf("\""));

                        creationSorties(maPiece, nomPorte, destPorte);
                        if (presenceCle.substring(presenceCle.lastIndexOf("\"")+1, presenceCle.length()).equals("1")){
                            System.out.println("mes la");
                        } else {
                            System.out.println("non mais oh");
                        }
                        // detecter si il y a un 1 a la fin de la ligne
                        //Si oui, chercher si clé corresp existe
                        // else il manque une clé
                     }

                }
            }
        } catch (IOException e) {
            System.out.println("Il y a une erreur dans le chargement des sorties");
        }
    }

    public static void creationSorties(Pieces maPiece, String nomSortie, String destSortie) {
            maPiece.setSorties(nomSortie, Main.trouverPieceParNom(destSortie));
    }
}