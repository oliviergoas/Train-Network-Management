package fr.univ_amu.iut.traitement;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de definition des gares. Chaque gare est compose d un nom, de la ville
 * accueillant la gare est de la liste de chaques gares desservable depuis celle ci.
 */
public class Gare {
    private String nom;
    private String ville;
    private List<Gare> garesDesservies = new ArrayList<>();

    /**
     * Construit une gare avec le nom de gare, la ville, et la liste des
     * gares desservable.
     *
     * @param nom Nom de la gare.
     * @param ville Ville accueillant la gare.
     * @param gareDesservies Gares deservies depuis cette gare.
     */
    public Gare(String nom, String ville, List<Gare> gareDesservies) {
        this.nom = nom;
        this.ville = ville;
        this.garesDesservies = gareDesservies;
    }

    /**
     * Construit une gare avec seulement un nom de gare et la ville.
     *
     * @param nom Nom de la gare.
     * @param ville Ville accueillant la gare.
     */
    public Gare(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Gare> getGaresDesservies() {
        return garesDesservies;
    }

    /**
     * Permet d'ajouter une gare à la liste des gares desservies de la gare.
     *
     * @param gareDesservie Nouvelles gare desservable.
     */
    public void addGareDesservie(Gare gareDesservie) {
        this.garesDesservies.add(gareDesservie);
    }

    /**
     * Traduit sous forme d'une string les information liees aux gares.
     *
     * @return Les informations sur la gare.
     */
    @Override
    public String toString() {
        String string = "Gare : " + nom + '\n'
                + "\t\t ville : " + ville + '\n';
        return string;
    }
}
