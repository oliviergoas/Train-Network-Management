package fr.univ_amu.iut.traitement.Wagon;

public class WagonTransportBetails extends Wagon {

    /**
     * Constructeur par defaut de WagonTransportBetails
     */
    public WagonTransportBetails() {
        super();
    }

    public void setNumero(int numero) {
        super.setNumero(numero);
    }

    public void setChargement(String chargement) {
        this.chargement = chargement;
    }
}
