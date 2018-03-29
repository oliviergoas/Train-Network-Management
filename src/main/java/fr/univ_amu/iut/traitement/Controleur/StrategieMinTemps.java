package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Trajet;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Olivier GOASAMPIS
 */
public class StrategieMinTemps implements StrategieControleur {
    /**
     * Définition de la méthode appliquerStrategie() pour la stratégie StrategieMinTemps,
     * optimise le trajet pour un temps de voyage minimum.
     *
     * @param trajet trajet avant optimisation
     * @param sillons Liste de sillons a reserver
     */
    @Override
    public void appliquerStrategie(Trajet trajet, Set<Sillon> sillons) {
        Iterator<LigneFerroviaire> ligneIterator  = trajet.getLignes().iterator();
        Iterator<Sillon>           sillonIterator = sillons.iterator();

        while(ligneIterator.hasNext() && sillonIterator.hasNext()) {
            LigneFerroviaire ligne  = ligneIterator.next();
            Sillon           sillon = sillonIterator.next();

            if (Controleur.verifierDisponibiliteSillon(ligne, sillon) == 0) {
                // Le sillon est disponible pour cette ligne
                Controleur.attribuerSillon(ligne, sillon);
            }
            else {
                // On doit décaler le sillon pour cette ligne
                for(LocalTime localTime = sillon.getHoraireDeb(); localTime.getHour() <= LocalTime.of(23, 0).getHour();
                      localTime = localTime.plusHours(1)) {
                    sillon.setHoraireDeb(localTime);
                    if (Controleur.verifierDisponibiliteSillon(ligne, sillon) == 0) {
                        ligne.attribuerSillon(sillon);
                        break;
                    }
                } // for
            } // else
        }
    } // appliquerStrategie()

} // StrategieMinTemps
