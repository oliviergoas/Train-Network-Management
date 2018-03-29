package fr.univ_amu.iut.traitement;

/**
 * Classe de definition d une ligne ferroviaire. Celles ci sont composees d une
 * gare de depart, d une gare d arrive, d un cout d utilisation de la ligne,
 * du cout cummule d utilisation de la ligne et du sillon, d un sillon et de
 * position pour pouvoir situer les trains.
 */
public class LigneFerroviaire {
    private Gare gareDep;
    private Gare gareArr;
    private double coutUtilisationLigne;
    private double coutUtilisationLigneEtSillon;
    private Sillon sillon;
    private int position = 1;

    /**
     * Construit une ligne ferroviaire avec seulement une gare de depart et une
     * gare d arrive.
     *
     * @param gareDep gare de depart de la ligne ferroviaire.
     * @param gareArr gare d arrive de la ligne ferroviaire.
     */
    public LigneFerroviaire(Gare gareDep, Gare gareArr) {
        this.gareDep = gareDep;
        this.gareArr = gareArr;
    }

    /**
     * Attribue le sillon à la ligne.
     *
     * @param sillon Sillon a attribuer.
     */
    public void attribuerSillon(Sillon sillon) {
        this.sillon = sillon;
    }

    /**
     * Permet d'incrementer la position du train sur la ligne pour simuler un deplacement.
     *
     * @return La nouvelle position.
     */
    public int deplacer() {
        if(position == 7) {
            position = 1;
            return 1;
        }
        else {
            ++position;
            return 0;
        }
    }

    /**
     * Permet de verifier si une gare peut desservur une autre gare (toutes les gares
     * ne sont pas relie entre elles).
     *
     * @param gareDepart Gare initiale.
     * @param gareArrivee Gare d arrive.
     *
     * @return renvoie true si la gare est desservable, false sinon.
     */
    public boolean verifierSiGareDesservable(Gare gareDepart, Gare gareArrivee) {
        for(Gare gare : gareDepart.getGaresDesservies()) {
            if (gare.getNom().equals(gareArrivee.getNom())) return true;
        }
        return false;
    }

    public Gare getGareDep() {
        return gareDep;
    }

    public Gare getGareArr() {
        return gareArr;
    }

    public double getCoutUtilisationLigne() { return coutUtilisationLigne; }

    public double getCoutUtilisationLigneEtSillon() {
        return coutUtilisationLigneEtSillon;
    }

    public Sillon getSillon() {
        return sillon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setCoutUtilisation(double coutUtilisationLigne) {
        this.coutUtilisationLigne = coutUtilisationLigne;
    }

    public void setCoutUtilisationLigneEtSillon() {
        coutUtilisationLigneEtSillon = coutUtilisationLigne + sillon.getCoutUtilisation();
    }

    public void setCoutUtilisationLigne(double coutUtilisationLigne) {
        this.coutUtilisationLigne = coutUtilisationLigne;
    }

    /**
     * Traduit sous forme d'une string les information liees aux lignes ferroviaire.
     *
     * @return les informations sur la ligne ferroviaire.
     */
    @Override
    public String toString() {
        return "LigneFerroviaire{\n" +
                "\tGare départ= " + gareDep +
                "\tGare arrivée=" + gareArr +
                '}';
    }
}
