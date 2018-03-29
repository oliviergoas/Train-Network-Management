package fr.univ_amu.iut.traitement;

import fr.univ_amu.iut.traitement.Train.Train;
import javafx.scene.shape.Circle;

import java.util.List;

/**
 * Classe de definition des trajets. Ceux ci sont composes du train qui effectue ce trajet,
 * d une liste de ligne ferroviare (sur lesquelles passera le train), de la ligne
 * et de la ligne ferroviaire courante. Un attribut est diponible pour savoir si le train
 * est en ciculation ou arrrete a une gare.
 */
public class Trajet {
    private Train train;
    private Circle circleTrain; // JavaFX
    private List<LigneFerroviaire> lignes;
    private LigneFerroviaire ligneCourante;
    private boolean enCirculation = false;

    /**
     * Construit un trajet avec le train qui va effectuer le trajet et une liste
     * de ligne ferroviaire definissant le trajet.
     *
     * @param train Train effectuant le trajet.
     * @param lignes Lignes ferroviaire composant le trajet.
     */
    public Trajet(Train train, List<LigneFerroviaire> lignes) {
        this.train = train;
        this.lignes = lignes;
        ligneCourante = lignes.get(0);
    }

    /**
     * Permet à l utilisateur d actionner le systeme pour voir l avancement des differents trains.
     *
     * @return renvoie 0 si le trajet n est pas termine , 1 sinon.
     */
    public int actionner() {
        if(ligneCourante.deplacer() == 1) {
            if(lignes.indexOf(ligneCourante) < lignes.size() - 1) {
                ligneCourante = lignes.get(lignes.indexOf(ligneCourante) + 1);
                //System.out.println(ligneCourante);
            }
            else {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Procedure qui permet d afficher le deplacement d un train sur le trajet.
     */
    public void afficherDeplacement() {
        System.out.println();
        for (int pos = 0; pos < 8; ++pos) {
            if (pos == 0 || pos == 7) {
                System.out.print("[G]");
            } else if (pos == getLigneCourante().getPosition())
                System.out.print('>');
            else
                System.out.print("==");
        }
        System.out.println();
    }

    /**
     * Affiche les gares (et les villes) de depart des trains et leurs horaires de departs.
     */
    public void afficherPanneauDepart() {
            System.out.println("Départ : "
                    + ligneCourante.getGareDep().getVille() + ' '
                    + ligneCourante.getGareDep().getNom() + " -> "
                    + ligneCourante.getSillon().getHoraireDeb());
    }

    /**
     * Affiche les gares (et les villes) d arrivees des trains, leurs horaires d arrivees et leurs villes.
     */
    public void afficherPanneauArrivee() {
            System.out.println("Arrivée : "
                    + ligneCourante.getGareArr().getVille() + ' '
                    + ligneCourante.getGareArr().getNom() + " -> "
                    + ligneCourante.getSillon().getHoraireFin());
    }

    /**
     * Affiche si un train est accidente sur le trajet.
     */
    public void afficherPanneauAccident() {
        if(getTrain().getEstAccidente())
            System.out.println("Le train est accidenté sur ce trajet.");
        else
            System.out.println("Le train n'est pas accidenté sur ce trajet.");
    }

    /**
     * Affiche les panneaux d arrivees, de departs, d eventuels accidents sur le trajet.
     */
    public void afficher() {
        afficherDeplacement();
        if(getTrain().getEstAccidente())
            afficherPanneauAccident();
        else {
            afficherPanneauDepart();
            afficherPanneauArrivee();
        }
    }

    public Train getTrain() {
        return train;
    }

    public Circle getCircleTrain() {
        return circleTrain;
    }

    public void setCircleTrain(Circle circleTrain) {
        this.circleTrain = circleTrain;
    }

    public List<LigneFerroviaire> getLignes() {
        return lignes;
    }

    public LigneFerroviaire getLigneCourante() {
        return ligneCourante;
    }

    public LigneFerroviaire getLigneSuivante() {
        if(lignes.indexOf(ligneCourante) < lignes.size() - 1)
            return lignes.get(lignes.indexOf(ligneCourante) + 1);
        return null;
    }

    public boolean isEnCirculation() {
        return enCirculation;
    }

    public void setEnCirculation(boolean enCirculation) {
        this.enCirculation = enCirculation;
    }

    /**
     * Traduit sous forme d'une string les information liees aux trajets.
     *
     * @return les informations sur le trajet.
     */
    @Override
    public String toString() {
        return "Trajet{\n" +
                "\tTrain=" + train +
                "\n\tLigne courante=\n" + ligneCourante +
                '}';
    }
}
