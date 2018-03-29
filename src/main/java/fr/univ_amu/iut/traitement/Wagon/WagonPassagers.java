package fr.univ_amu.iut.traitement.Wagon;

public class WagonPassagers extends Wagon {

    /**
     * Constructeur par defaut de WagonPassagers.
     */
    public WagonPassagers() {
        super();
    }

    public void setNumero(int numero) { super.setNumero(numero); }

    public void setChargement(String chargement) {
        this.chargement = chargement;
    }
}
