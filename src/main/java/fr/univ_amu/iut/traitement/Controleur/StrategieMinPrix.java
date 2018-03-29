package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Trajet;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Olivier GOASAMPIS
 */
public class StrategieMinPrix implements StrategieControleur {
    /**
     * Définition de la méthode appliquerStrategie() pour la stratégie StrategieMinPrix,
     * qui optimise le trajet pour un cout minimum.
     *
     * @param trajet trajet avant optimisation.
     * @param sillons Liste de sillons a reserve
     */
    @Override
    public void appliquerStrategie(Trajet trajet, Set<Sillon> sillons)/* throws GareNonDesservieException*/ {
        Set<Sillon> sillonSetPrix = new TreeSet<>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon o1, Sillon o2) {
                if(o1.getCoutUtilisation() < o2.getCoutUtilisation()) return -1;
                else if(o1.getCoutUtilisation() > o2.getCoutUtilisation()) return 1;
                return 0;
            }
        });

        sillonSetPrix.addAll(sillons);
        
        StrategieControleur strategieControleur = new StrategieGlouton();
        strategieControleur.appliquerStrategie(trajet, sillonSetPrix);
    }
}
