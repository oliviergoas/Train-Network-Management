package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.Train.TrainMarchandises;
import fr.univ_amu.iut.traitement.Train.TrainPassagers;
import fr.univ_amu.iut.traitement.Train.TrainTransportBetails;
import fr.univ_amu.iut.traitement.Train.TrainTransportDechets;

/**
 * visiteur pour les différents types de facturation des différents trains, on l'implémente dans plusieurs classes en fonction du nombre de wagons, du type de train, de la vitesse
 */
public interface FacturationTrain {
    void visiter(TrainMarchandises trainMarchandises);
    void visiter(TrainPassagers trainPassagers);
    void visiter(TrainTransportBetails trainBetail);
    void visiter(TrainTransportDechets trainDechet);
}
