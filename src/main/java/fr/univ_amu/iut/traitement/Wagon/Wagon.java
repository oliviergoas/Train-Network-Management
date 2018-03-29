package fr.univ_amu.iut.traitement.Wagon;

/**
 * Classe de definition des wagons. Ceux ci sont compose d un numero et
 * d un chargement.
 */
public abstract class Wagon {
    protected int numero;
    protected String chargement;

    /**
     * Constructeur par défaut de wagon
     */
    public Wagon() {}

    /**
     * Méthode abstraite permettant d'ajouter un chargement dans un wagon
     *
     * @param chargement Chargement a ajouter.
     */
    public abstract void setChargement(String chargement);

    public void setNumero(int numero) { this.numero = numero; }
}
