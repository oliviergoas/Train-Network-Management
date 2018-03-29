package fr.univ_amu.iut.traitement.EntrepriseFerroviaire;

import fr.univ_amu.iut.traitement.Train.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static fr.univ_amu.iut.affichage.ApplicationHyperFrisette.saisirNombre;

/**
 * La classe abstraite EntrepriseFerroviaire détermine le comportement d'une entreprise
 * ferroviaire.
 *
 * @author Romain COLONNA D'ISTRIA
 */
public abstract class EntrepriseFerroviaire {
    private String nom;
    private int numSIREN;
    private List<Train> trainsPossedes = new ArrayList<>();

    private int fidelite = 0;

    public EntrepriseFerroviaire() {}

    public EntrepriseFerroviaire(String nom, int numSIREN) {
        this.nom = nom;
        this.numSIREN = numSIREN;
    }

    public void setNumSIREN(int numSIREN) {
        this.numSIREN = numSIREN;
    }

    /**
     * Permet à l'entreprise ferroviaire de demander l'autorisation d'effectuer un trajet
     * passant par zero ou plusieurs gares.
     *
     * @param trainUtilisePourTrajet trains utilisés lors du trajet.
     * @param garesDuTrajet gares traversées durant le trajet.
     * @param heureDepartPremiereGare heure de départ de la premiere gare.
     */
    /*
    public Trajet demanderTrajet(Train trainUtilisePourTrajet, List<Gare> garesDuTrajet, LocalTime heureDepartPremiereGare) {
        Trajet trajet = new Trajet(trainUtilisePourTrajet);
        Sillon sillon;
        for (int i = 0; i < garesDuTrajet.size() - 1; ++i) {
            trajet.addLigne(Controleur.attribuerSillon(new LigneFerroviaire(garesDuTrajet.get(i), garesDuTrajet.get(i+1)), heureDepartPremiereGare));
            heureDepartPremiereGare = heureDepartPremiereGare.plusHours(1);
        }
        return trajet;
    }
    */

    /**
     * Permet a l'entreprise ferroviaire de déclarer un incident sur le trajet en cours.
     */
    public void declarerIncident() {
        //TODO implémneter declarerIncident()
    }

    /**
     * Ajoute à la liste de trains possédés par l'entreprise un nouveau train.
     *
     * @param train le train à ajouter a la liste
     */
    public void aquerirTrain(Train train) {
        this.trainsPossedes.add(train);
    }

    /**
     * Retire un train de la liste de trains que possède l'entreprise.
     *
     * @param train train a retirer de la liste
     */
    public void retirerTrain(Train train) {
        this.trainsPossedes.remove(train);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * La fonction static creerEntreprise() permet à un utilisateur de choisir un type d'entreprise et de la creer en y rentrant son nom et son numéro de SIREN
     */
    public static EntrepriseFerroviaire creerEntreprise() {
        System.out.println("1. Entreprise Ferroviaire Animaux" + "\n"
                + "2. Entreprise Ferroviaire Cargo" + "\n"
                + "3. Entreprise Ferroviaire Passagers");

        int typeEnt;
        Scanner sTypeEnt = new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du type d'entreprise souhaité");
        typeEnt = saisirNombre(sTypeEnt, 1, 3);

        String nom;
        Scanner sNom = new Scanner(System.in);
        System.out.println("Veuillez entrer le nom d'une entreprise");
        nom = sNom.nextLine();

        int numSiren;
        Scanner sNum = new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro d'une entreprise");
        numSiren = saisirNombre(sNum, 1000000, 9999999);

        EntrepriseFerroviaire entrepriseFerroviaire = null;
        switch (typeEnt) {
            case 1:
                entrepriseFerroviaire = new EntrepriseFerroviaireAnimaux(nom, numSiren);
                break;
            case 2:
                entrepriseFerroviaire = new EntrepriseFerroviaireCargo(nom, numSiren);
                break;
            case 3:
                entrepriseFerroviaire = new EntrepriseFerroviairePassagers(nom, numSiren);
                break;
        }

        return entrepriseFerroviaire;
    }
}
