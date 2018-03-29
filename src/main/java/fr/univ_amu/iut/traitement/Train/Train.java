package fr.univ_amu.iut.traitement.Train;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.*;
import fr.univ_amu.iut.traitement.Facturation.Visitable;
import fr.univ_amu.iut.traitement.Wagon.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Olivier GOASAMPIS
 */
public abstract class Train implements Visitable {
    private EntrepriseFerroviaire entrepriseFerroviaire;
    private List<Wagon> wagons = new ArrayList<Wagon>();

    private int hauteur;
    private int largeur;
    private int longueur;
    private int vitesseMax;
    private boolean estAccidente;

    /**
     * Constructeur par défaut
     */
    public Train() {}

    /**
     * Constructeur Train
     * @param entrepriseFerroviaire
     */
    public Train(EntrepriseFerroviaire entrepriseFerroviaire) {
        this.entrepriseFerroviaire = entrepriseFerroviaire;
    }

    /**
     *
     * @param hauteur, la hauteur du train
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     *
     * @param largeur, la largeur du train
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     *
     * @param longueur, la longueur du train
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    /**
     *
     * @param vitesseMax, la vitesse maximum du train
     */
    public void setVitesseMax(int vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    /**
     *
     * @return wagons, les wagons du train
     */
    public List<Wagon> getWagons() { return wagons; }

    /**
     *
     * @param wagons
     */
    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    /**
     *
     * @return vitesseMax, la vitesse maximum du train
     */
    public int getVitesseMax() { return vitesseMax; }

    /**
     *
     * @return estAccidenté, true s'il l'est, false s'il ne l'est pas
     */
    public boolean getEstAccidente() {
        return estAccidente;
    }

    /**
     *
     * @param estAccidente
     */
    public void setEstAccidente(boolean estAccidente) {
        this.estAccidente = estAccidente;
    }

    /**
     * Fonction de saisie d'un entier entre les bornes en paramètre.
     * @param scanner
     * @param min
     * @param max
     * @return valeur saisie
     */
    public static int saisirNombre(Scanner scanner, int min, int max) {
        int nb = min - 1;
        while(nb < min || nb > max) {
            System.out.print("> ");
            nb = scanner.nextInt();
        }
        return nb;
    }

    /**
     * La fonction static initialiserTrain() permet à un utilisateur de choisir un type de train et de la creer en y rentrant l'entreprise qui a réservée le train , la liste de wagons, sa hauteur, longueur, largeur, vitesse
     * @param entrepriseFerroviaire est le paramètre qui permet de récuperer l'entreprise créee par creerEntreprise()
     */
    public static Train creerTrain(EntrepriseFerroviaire entrepriseFerroviaire) {
        System.out.println("1. Train de marchandises" + "\n"
                + "2. Train de passagers" + "\n"
                + "3. Train de transport de bétails" + "\n"
                + "4. Train de transport de déchets" + "\n");

        int typeTrain;
        Scanner sTypeEnt = new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro correspondant au type de train souhaité");
        typeTrain = saisirNombre(sTypeEnt, 1, 4);

        int hauteur;
        Scanner sHauteur = new Scanner(System.in);
        System.out.println("Veuillez entrer la hauteur du train");
        hauteur = saisirNombre(sHauteur, 1, 20);

        int largeur;
        Scanner sLargeur = new Scanner(System.in);
        System.out.println("Veuillez entrer la largeur du train");
        largeur = saisirNombre(sLargeur, 1, 10);

        int longueur;
        Scanner sLongueur = new Scanner(System.in);
        System.out.println("Veuillez entrer la longueur du train");
        longueur = saisirNombre(sLongueur, 1, 200);

        int vitesseMax;
        Scanner sVitesseMax = new Scanner(System.in);
        System.out.println("Veuillez entrer la vitesse du train");
        vitesseMax = saisirNombre(sVitesseMax, 1, 400);


        int nbWagons;
        List<Wagon> wagons = new ArrayList<>();
        Wagon wagon = null;

        Scanner sNbWagon = new Scanner(System.in);
        System.out.println("Veuillez entrer le nombre de wagons");
        nbWagons = saisirNombre(sNbWagon, 1, 6);

        Scanner sWagon = new Scanner(System.in);
        int typeWagon;
        for(int i = 1; i <= nbWagons; ++i) {
            System.out.println("1. Wagon de marchandises" + "\n"
                    + "2. Wagon de passagers" + "\n"
                    + "3. Wagon de transport de bétails" + "\n"
                    + "4. Wagon de transport de déchets" + "\n");
            System.out.println("Veuillez entrer le numéro correspondant au type de train souhaité");
            typeWagon = sTypeEnt.nextInt();
            switch (typeWagon) {
                case 1:
                    wagon = new WagonMarchandises();
                    wagon.setNumero(i);
                    wagons.add(wagon);
                    break;
                case 2:
                    wagon = new WagonPassagers();
                    wagon.setNumero(i);
                    wagons.add(wagon);
                    break;
                case 3:
                    wagon = new WagonTransportBetails();
                    wagon.setNumero(i);
                    wagons.add(wagon);
                    break;
                case 4:
                    wagon = new WagonTransportDechets();
                    wagon.setNumero(i);
                    wagons.add(wagon);
                    break;
            }
        }

        Train train = null;
        switch (typeTrain) {
            case 1:
                train = new TrainMarchandises.Builder(entrepriseFerroviaire).addWagons(wagons).addHauteur(hauteur)
                        .addLargeur(largeur).addLongueur(longueur)
                        .addVitesseMax(vitesseMax).build();
                break;
            case 2:
                train = new TrainPassagers.Builder(entrepriseFerroviaire).addWagons(wagons).addHauteur(hauteur)
                        .addLargeur(largeur).addLongueur(longueur)
                        .addVitesseMax(vitesseMax).build();
                break;
            case 3:
                train = new TrainTransportBetails.Builder(entrepriseFerroviaire).addWagons(wagons).addHauteur(hauteur)
                        .addLargeur(largeur).addLongueur(longueur)
                        .addVitesseMax(vitesseMax).build();
                break;
            case 4:
                train = new TrainTransportDechets.Builder(entrepriseFerroviaire).addWagons(wagons).addHauteur(hauteur)
                        .addLargeur(largeur).addLongueur(longueur)
                        .addVitesseMax(vitesseMax).build();
                break;
        }

        return train;
    }

    @Override
    public String toString() {
        return  " hauteur : " + hauteur + "m" + "\n" +
                "           largeur : " + largeur + "m" + "\n" +
                "           longueur : " + longueur + "m" + "\n" +
                "           vitesse max : " + vitesseMax + "km/h";
    }
}
