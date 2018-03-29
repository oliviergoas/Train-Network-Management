package fr.univ_amu.iut.traitement.Wagon;

public class WagonMarchandises extends Wagon {

    /**
     * Constructeur par defaut de WagonMarchandises.
     */
    public WagonMarchandises() { super(); }

    public void setNumero(int numero) { super.setNumero(numero); }

    public void setChargement(String chargement) {
        this.chargement = chargement;
    }
}
