package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.Gare;
import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Trajet;

import java.util.*;

/**
 * Classe
 * @author Olivier GOASAMPIS
 */
public class StrategieTrajetPlusRapide implements StrategieControleur {
    /**
     * Définition de la méthode appliquerStrategie() pour la stratégie StrategieTrajetPlusRapide,
     * optimisation du trajet en fonction du temps de voyage.
     *
     * @param trajet trajet avant optimisation
     * @param sillons sillon a reserver
     */
    public void appliquerStrategie(Trajet trajet, Set<Sillon> sillons)/* throws GareNonDesservieException*/ {
        ListIterator<LigneFerroviaire> iterateurLigne  = trajet.getLignes().listIterator();
        ListIterator<LigneFerroviaire> iterateurSuppr;
        Iterator<Sillon>               iterateurSillon = sillons.iterator();

        List<Gare>             garesAccessiblesCourantes;
        LigneFerroviaire       ligneCourante;
        LigneFerroviaire       ligneAUtiliser = null;

        int compteur = 0;

        while(iterateurLigne.hasNext() && iterateurSillon.hasNext()) {
            iterateurSuppr = null;
            ligneCourante  = iterateurLigne.next();

            garesAccessiblesCourantes = ligneCourante.getGareDep().getGaresDesservies();

            ListIterator<LigneFerroviaire> iterateurLigneLocal = iterateurLigne;
            boolean nouvelleGareTrouvee = false;

            while(iterateurLigneLocal.hasNext()) {
                LigneFerroviaire ligne = iterateurLigneLocal.next();
                if(garesAccessiblesCourantes.contains(ligne.getGareArr())) {
                    ligneAUtiliser = new LigneFerroviaire(ligneCourante.getGareDep(), ligne.getGareArr());
                    ligneAUtiliser.setCoutUtilisationLigne(ligneCourante.getCoutUtilisationLigne());
                    iterateurSuppr = iterateurLigneLocal;
                    nouvelleGareTrouvee = true;
                }
            }

            if(!nouvelleGareTrouvee)
                ligneAUtiliser = ligneCourante;
            else {
                trajet.getLignes().set(compteur, ligneAUtiliser);
                iterateurSuppr.previous();
                iterateurSuppr.previous();
                iterateurSuppr.remove();
            }

            Controleur.attribuerSillon(ligneAUtiliser, iterateurSillon.next());

            ++compteur;
        }
    } // appliquerStrategie()
} // StrategieTrajetPlusRapide
