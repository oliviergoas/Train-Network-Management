package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.Train.TrainMarchandises;
import fr.univ_amu.iut.traitement.Train.TrainPassagers;
import fr.univ_amu.iut.traitement.Train.TrainTransportBetails;
import fr.univ_amu.iut.traitement.Train.TrainTransportDechets;

public class FacturationNombreWagon implements FacturationTrain {
    private double cout = 0;
    private double prixWagon;

    /**
     * Constructeur de FacturationNombreWagon
     * @param prixWagon
     */
    public FacturationNombreWagon(int prixWagon) {
        this.prixWagon = prixWagon;
    }

    /**
     * Méthode permettant de fixer le cout en fonction du nombre de wagons d'un train de marchandises
     *
     * @param trainMarchandises train de marchandises pour lequel on veut obtenir le cout du trajet
     */
    @Override
    public void visiter(TrainMarchandises trainMarchandises) {
        cout += prixWagon * (trainMarchandises.getWagons().size() + 1);
    }

    /**
     * Méthode permettant de fixer le cout en fonction du nombre de wagons d'un train de passagers
     *
     * @param trainPassagers train de passagers pour lequel on veut obtenir le cout du trajet
     */
    @Override
    public void visiter(TrainPassagers trainPassagers) {
        cout += prixWagon * (trainPassagers.getWagons().size() + 1);
    }

    /**
     * Méthode permettant de fixer le cout en fonction du nombre de wagons d'un train de bétail
     *
     * @param trainBetail train de bétail pour lequel on veut obtenir le cout du trajet
     */
    @Override
    public void visiter(TrainTransportBetails trainBetail) {
        cout += prixWagon * (trainBetail.getWagons().size() + 1);
    }

    /**
     * Méthode permettant de fixer le cout en fonction du nombre de wagons d'un train de déchets
     *
     * @param trainDechet tain de déchets pour lequel on veut obtenir le cout du trajet
     */
    @Override
    public void visiter(TrainTransportDechets trainDechet) {
        cout += prixWagon * (trainDechet.getWagons().size() + 1);
    }

    public double getCout() { return cout;}
}
