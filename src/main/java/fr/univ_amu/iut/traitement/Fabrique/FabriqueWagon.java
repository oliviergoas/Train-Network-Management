package fr.univ_amu.iut.traitement.Fabrique;

import fr.univ_amu.iut.traitement.Wagon.*;

public class FabriqueWagon implements FabriqueAbstraite<Wagon> {

    /**
     * Appel le constructeur de wagonTransportBetails
     *
     * @return renvoie un wagon de transport de betails
     */
    @Override
    public Wagon creerTransportBetail() {
        return new WagonTransportBetails();
    }

    /**
     * Appel le constructeur de wagonTransportMarchandise
     *
     * @return renvoie un wagon de transport de marchandises
     */
    @Override
    public Wagon creerTransportMarchandises() {
        return new WagonMarchandises();
    }

    /**
     * Appel le constructeur de wagonTransportPassagers
     *
     * @return renvoie un wagon de transport de passagers
     */
    @Override
    public Wagon creerTransportPassagers() {
        return new WagonPassagers();
    }

    /**
     * Appel le constructeur de wagonTransportDechets
     *
     * @return renvoie un wagon de transport de dechets
     */
    @Override
    public Wagon creerTransportDechets() {
        return new WagonTransportDechets();
    }
}
