package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.Train.TrainMarchandises;
import fr.univ_amu.iut.traitement.Train.TrainPassagers;
import fr.univ_amu.iut.traitement.Train.TrainTransportBetails;
import fr.univ_amu.iut.traitement.Train.TrainTransportDechets;

public class FacturationTypeTrain implements FacturationTrain {
    private double cout;

    public FacturationTypeTrain() {
        cout = 0;
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet pour un train de marchandises
     *
     * @param trainMarchandises train de marchandises pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainMarchandises trainMarchandises) {
        cout += 5555;
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet pour un train de passagers
     *
     * @param trainPassagers train de passagers pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainPassagers trainPassagers) {
        cout += 7777;
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet pour un train de bétail
     *
     * @param trainBetail train de bétail pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainTransportBetails trainBetail) {
        cout += 9999;
    }

    /**
     * Méthode permettant de calculer le cout d'un trajet pour un train de déchets
     *
     * @param trainDechet train de déchets pour lequel on veut calculer le cout
     */
    @Override
    public void visiter(TrainTransportDechets trainDechet) {
        cout += 4444;
    }

    public double getCout() {
        return cout;
    }
}
