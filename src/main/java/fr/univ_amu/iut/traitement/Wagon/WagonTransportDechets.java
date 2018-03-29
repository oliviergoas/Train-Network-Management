package fr.univ_amu.iut.traitement.Wagon;

public class WagonTransportDechets extends Wagon {

    /**
     * Constructeur par defaut de WagonTransportDechets
     */
    public WagonTransportDechets() {
        super();
    }

    public void setNumero(int numero) { super.setNumero(numero); }

    public void setChargement(String chargement) {
        this.chargement = chargement;
    }
}
