package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Trajet;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Olivier GOASAMPIS
 */
public class StrategieGlouton implements StrategieControleur {
    /**
     * Definition de la methode appliquerStrategie() pour la strategie StrategieGlouton,
     * qui reserve les Sillons de trajet sans contraintes specifique
     *
     * @param trajet liste de ligne ferroviaire qui definnisse le chemin suivi par le train
     *
     * @param sillons les differents sillons que l'on veut reserver
     */
    @Override
    public void appliquerStrategie(Trajet trajet, Set<Sillon> sillons) {
        Iterator<LigneFerroviaire> ferroviaireIterator = trajet.getLignes().iterator();
        Iterator<Sillon>           sillonIterator      = sillons.iterator();

        while(ferroviaireIterator.hasNext() && sillonIterator.hasNext()) {
            Controleur.attribuerSillon(ferroviaireIterator.next(), sillonIterator.next());
        }
    }
}
