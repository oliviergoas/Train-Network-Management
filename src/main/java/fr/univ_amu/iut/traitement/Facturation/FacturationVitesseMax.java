package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.Train.TrainMarchandises;
import fr.univ_amu.iut.traitement.Train.TrainPassagers;
import fr.univ_amu.iut.traitement.Train.TrainTransportBetails;
import fr.univ_amu.iut.traitement.Train.TrainTransportDechets;

public class FacturationVitesseMax implements FacturationTrain {
    private double cout;
    private int prixKmH;

    /**
     * Constructeur de facturationVitesseMax
     * @param prixKmH
     */
    public FacturationVitesseMax(int prixKmH) {
        this.prixKmH = prixKmH;
        cout = 0;
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet en fonction de la vitesse d'un train de marchandises
     *
     * @param trainMarchandises train de marchandises pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainMarchandises trainMarchandises) {
        cout += prixKmH * trainMarchandises.getVitesseMax();
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet en fonction de la vitesse d'un train de passagers
     *
     * @param trainPassagers train de passagers pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainPassagers trainPassagers) {
        cout += prixKmH * trainPassagers.getVitesseMax();
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet en fonction de la vitesse d'un train de bétail
     *
     * @param trainBetail train de bétail pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainTransportBetails trainBetail) {
        cout += prixKmH * trainBetail.getVitesseMax();
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet en fonction de la vitesse d'un train de déchets
     *
     * @param trainDechet train de déchets pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainTransportDechets trainDechet) {
        cout += prixKmH * trainDechet.getVitesseMax();
    }

    public double getCout() {return cout;}
}
