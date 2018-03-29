package fr.univ_amu.iut.traitement.EntrepriseFerroviaire;

public class EntrepriseFerroviairePassagers extends EntrepriseFerroviaire {

    /**
     * Constructeur par default de la classe EntrepriseFerroviairePassagers
     */
    public EntrepriseFerroviairePassagers() { super(); }

    /**
     * Construit une EntrepriseFerroviairePassagers avec un nom et un numero SIREN.
     *
     * @param nom nom du train sous forme de string
     * @param numSIREN numero SIREN du train sous forme d un int
     */

    public EntrepriseFerroviairePassagers(String nom, int numSIREN) {
        super(nom, numSIREN);
    }
}
