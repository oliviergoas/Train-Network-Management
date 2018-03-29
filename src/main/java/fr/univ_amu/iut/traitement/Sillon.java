package fr.univ_amu.iut.traitement;

import java.time.LocalTime;

/**
 * Classe de définition des sillons. Ceux ci sont composés d un cout d utilisation, d une
 * heure de debut et d une heure de fin.
 */
public class Sillon {
    private double coutUtilisation;
    private LocalTime horaireDeb;
    private LocalTime horaireFin;

    /**
     * Construit un sillon avec seulement une heure de debut (suffisant pour initialiser
     * l heure de fin).
     *
     * @param horaireDeb Heure de debut.
     */
    public Sillon(LocalTime horaireDeb) {
        this.horaireDeb = horaireDeb;
        this.horaireFin = horaireDeb.plusHours(1);
    }

    public double getCoutUtilisation() {
        return coutUtilisation;
    }

    public LocalTime getHoraireDeb() {
        return horaireDeb;
    }

    public void setHoraireDeb(LocalTime horaireDeb) {
        this.horaireDeb = horaireDeb;
        horaireFin = horaireDeb.plusHours(1);
    }

    public LocalTime getHoraireFin() {
        return horaireFin;
    }

    public void setCoutUtilisation(double coutUtilisation) {
        this.coutUtilisation = coutUtilisation;
    }

    /**
     * Traduit sous forme d'une string les information liees aux sillons.
     *
     * @return les informations sur le sillons.
     */
    @Override
    public String toString() {
        return  "\theure de départ : " + horaireDeb + "\n" +
                "\t\theure d'arrivée : " + horaireFin + "\n";
    }
}
