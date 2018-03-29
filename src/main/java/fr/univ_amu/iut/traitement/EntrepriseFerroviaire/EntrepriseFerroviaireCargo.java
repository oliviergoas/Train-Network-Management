package fr.univ_amu.iut.traitement.EntrepriseFerroviaire;

public class EntrepriseFerroviaireCargo extends EntrepriseFerroviaire {
    /**
     * Constructeur par default de la classe EntrepriseFerroviaireCargo
     */
    public EntrepriseFerroviaireCargo () {super();}

    /**
     * Construit une EntrepriseFerroviaireCargo avec un nom et un numero SIREN.
     *
     * @param nom nom du train sous forme de string
     * @param numSIREN numero SIREN du train sous forme d un int
     */

    public EntrepriseFerroviaireCargo(String nom, int numSIREN) {
        super(nom, numSIREN);
    }
}
