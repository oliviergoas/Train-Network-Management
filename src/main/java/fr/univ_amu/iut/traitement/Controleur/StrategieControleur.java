package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Trajet;

import java.util.Set;

/**
 * @author Olivier GOASAMPIS
 */
public interface StrategieControleur {
    void appliquerStrategie(Trajet trajet, Set<Sillon> sillons) /*throws GareNonDesservieException*/;
}
