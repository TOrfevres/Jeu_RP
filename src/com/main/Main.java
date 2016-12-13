package com.main;

import com.entities.Joueurs;
import com.environment.Pieces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Created by user on 12/12/2016.
 */
public class Main {
    public static Collection<Pieces> niveau = new Collection<Pieces>() {
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
        public Iterator<Pieces> iterator() {
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
        public boolean add(Pieces pieces) {
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
        public boolean addAll(Collection<? extends Pieces> c) {
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

    static String csvFile = "";
    static Scanner sc;
    static String line = null;                                     //Peut importe la valeur
    static String cvsSplitBy = ";";                                // Separation des caracteres
    static boolean retry = true;

    public static void main(String[] args) {

        FileReader fr;


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
                    Joueurs joueur = new Joueurs(persoSelect[0], Integer.decode(persoSelect[2]), Integer.decode(persoSelect[3]), null, persoSelect[1], Integer.decode(persoSelect[5]), Integer.decode(persoSelect[4]));
                }

            } catch (Exception FileNotFoundException) { //Rajouter des throw ????
                sc = new Scanner(System.in);
                System.out.print(">");
                csvFile = sc.next();
            }
        }
    }


    public static Pieces trouverPieceParNom(String nomPiece) {
        Pieces pieceRecherche = null;
        for(Pieces piece : niveau) {
            if(piece.getNom() == nomPiece) {
                pieceRecherche = piece;
            }
        }
        return pieceRecherche;
    }

    public static void constructionNiveau() {

    }


    public static void selectionMonde(){
            String cheminJeu = "";
            String nomJeu = "";
            String cheminCarte = "";
            String cheminObjet = "";
            String cheminMonstre = "";
            String descriptionJeu = "";
            String cheminSimple = "";
            String ligne = "";
            //boolean retry = true;
            //Scanner sc;
            System.out.println("Saisissez l'emplacement du fichier de description du niveau pour ce Scénario : ");
        while (retry) { // D:/Users/Kirian/Bureau/niveau.txt
            try {
                BufferedReader br = new BufferedReader(new FileReader(cheminJeu));
                nomJeu = br.readLine();
                System.out.println(nomJeu);

                cheminCarte = br.readLine();
                System.out.println(cheminCarte);

                cheminObjet = br.readLine();
                System.out.println(cheminObjet);

                cheminMonstre = br.readLine();
                System.out.println(cheminMonstre);

                while ((descriptionJeu = br.readLine()) != null) {
                    System.out.println(descriptionJeu);
                }
                cheminSimple = cheminJeu.substring(0, cheminJeu.lastIndexOf('/') + 1);
                cheminCarte = cheminSimple + cheminCarte;
                cheminObjet = cheminSimple + cheminObjet;
                cheminMonstre = cheminSimple + cheminMonstre;

                /*System.out.println(cheminMonstre);
                System.out.println(cheminObjet);
                System.out.println(cheminCarte);
                System.out.println(cheminSimple);*/

                BufferedReader monstre = new BufferedReader(new FileReader(cheminMonstre));
                BufferedReader objet = new BufferedReader(new FileReader(cheminObjet));
                BufferedReader carte = new BufferedReader(new FileReader(cheminCarte));


                while ((ligne = carte.readLine()) != null) {
                    if (ligne.equals("<")) {
                        String nom = carte.readLine();
                        nom = nom.substring(1, (nom.length() - 1));       // recup nom pieces

                        carte.readLine();   //saute une ligne

                        String desc = carte.readLine();                     //recup toutes la desc
                        while (!(ligne = carte.readLine()).equals("\\")) {
                            desc = desc + "\r\n" + ligne;
                        }
                        niveau.add(new Pieces(nom, desc));              //creee l'entitee
                    }
                }

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

}
