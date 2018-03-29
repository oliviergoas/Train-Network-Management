package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Trajet;

import java.time.LocalTime;
import java.util.*;

/**
 * Classe Controleur
 */
public class Controleur {


    private static Map<LigneFerroviaire, LocalTime> sillonsDejaAttribues = new HashMap<>(); // heureDeb pour les lignes
    private static StrategieControleur strategie;

    /**
     * Méthode permettant de modifier la strategie du Controleur (setter).
     *
     * @param strategieControleur
     */
    public static void setStrategie(StrategieControleur strategieControleur) {
        strategie = strategieControleur;
    }

    /**
     * Méthode permettant l'execution de la stratégie, qui correspond à l'attribut strategie de l'objet.
     * Elle prend en paramètre un trajet, sur lequel on veut que le controleur travaille.
     * L'autre paramètre correspond aux sillons attribués. Les sillons sont rangés dans l'ordre croissant
     * des horaires à l'aide d'une PriorityQueue.
     *
     * @param trajet liste des trajets à gérer
     * @param sillons liste des sillons à attribuer, rangés dans l'ordre croissant de leurs horaires
     */
    public static void executerStrategie(Trajet trajet, Set<Sillon> sillons)/* throws GareNonDesservieException*/ {
        strategie.appliquerStrategie(trajet, sillons);
    }

    /**
     * Methode permettant l'attribution de Sillon pour une ligne donnée par rapport a une heure donnée.
     * Elle vérifie que le sillon n'est pas déja attribué et l'attribut s'il est libre.
     *
     * @param ligne ligne correspondant au sillon demandé.
     * @param heureDebut heure d'utilisation du sillon.
     *
     */
    public static void attribuerSillon(LigneFerroviaire ligne, Sillon sillon) {
        if (verifierDisponibiliteSillon(ligne, sillon) == 0) {
            sillonsDejaAttribues.put(ligne, sillon.getHoraireDeb());
            ligne.attribuerSillon(sillon);
        }
    }

    /**
     * Méthode permettant de verifier si un sillion est disponible pour une ligne ferroviaire
     *
     * @param ligneFerroviaire ligne ferroviaire sur laquelle on veut reserver un sillon
     * @param sillon sillon que l'on veut réserver
     * @return int renvoie -1 si le sillon est deja attribue et 0 sinon
     */
    public static int verifierDisponibiliteSillon(LigneFerroviaire ligneFerroviaire, Sillon sillon) {
        if(sillonsDejaAttribues.containsKey(ligneFerroviaire)
                && sillonsDejaAttribues.get(ligneFerroviaire).equals(sillon.getHoraireDeb())) return -1;
        return 0;
    }

    public static Map<LigneFerroviaire, LocalTime> getSillonsDejaAttribues() {
        return sillonsDejaAttribues;
    }
}
