package fr.univ_amu.iut.traitement.Fabrique;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviaireAnimaux;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviaireCargo;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviairePassagers;
import fr.univ_amu.iut.traitement.Train.*;

public class FabriqueTrain implements FabriqueAbstraite<Train> {


    /**
     * Appel le builder de TrainTransportBetail
     *
     * @return Train Train de transport de betail
     */
    @Override
    public Train creerTransportBetail() {
        return new TrainTransportBetails.Builder(new EntrepriseFerroviaireAnimaux()).build();
    }


    /**
     * Appel le builder de TrainTransportMarchandise
     *
     * @return Train Train de transport de Marchandises
     */
    @Override
    public Train creerTransportMarchandises() {
        return new TrainMarchandises.Builder(new EntrepriseFerroviaireCargo()).build();
    }

    /**
     * Appel le builder de TrainTransportPassagers
     *
     * @return Train Train de transport de Passagers
     */

    @Override
    public Train creerTransportPassagers() {
        return new TrainPassagers.Builder(new EntrepriseFerroviairePassagers()).build();
    }

    /**
     * Appel le builder de TrainTransportDechets
     *
     * @return Train Train de transport de dechets
     */

    @Override
    public Train creerTransportDechets() {
        return new TrainTransportDechets.Builder(new EntrepriseFerroviaireCargo()).build();
    }
}
