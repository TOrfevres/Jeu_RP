package com.main;

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

    public static void main(String[] args) {
        String line = null;                                     //Peut importe la valeur
        String cvsSplitBy = ";";                                // Separation des caracteres
        FileReader fr;

        System.out.println("Saissir l'emplacement du fichier de description des personnages à charger pour ce scénario : ");
        String csvFile="";
        Scanner sc;
        boolean retry=true;

        while (retry) {
            try { // D:/Users/Kirian/Bureau/csvtest.csv
                BufferedReader br = new BufferedReader(new FileReader(csvFile)); // loliloll
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
                    System.out.println("Vous avez sélectionné : " +persoSelect[0]);
                    // Suffit de créée instance du style:
                    // Personnage perso = new Personnage(persoSelect[0], persoSelect[1],...)
                }

            } catch (Exception FileNotFoundException) { // Rajouter des throw ????
                sc = new Scanner(System.in);
                System.out.print(">");
                csvFile = sc.next();
            }
        }
    }

    //Il faut placer toutes les nouvelles pieces dans cette collection "niveau"
    public static void ajouterPieceAuNiveau(Pieces piece) {
        niveau.add(piece);
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
}
