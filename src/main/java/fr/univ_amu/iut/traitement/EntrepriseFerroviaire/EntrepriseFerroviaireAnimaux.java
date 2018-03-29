package fr.univ_amu.iut.traitement.EntrepriseFerroviaire;

public class EntrepriseFerroviaireAnimaux extends EntrepriseFerroviaire {

    /**
     * Constructeur par default de la classe EntrepriseFerroviaireAnimaux
     */
    public EntrepriseFerroviaireAnimaux() { super(); }

    /**
     * Construit une EntrepriseFerroviaireAnimaux avec un nom et un numero SIREN.
     *
     * @param nom nom du train sous forme de string
     * @param numSIREN numero SIREN du train sous forme d un int
     */

    public EntrepriseFerroviaireAnimaux(String nom, int numSIREN) {
        super(nom, numSIREN);
    }
}
