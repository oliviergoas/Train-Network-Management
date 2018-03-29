package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.LigneFerroviaire;

/**
 * classe facturation qui dépend du sillon et de la ligne utilisés
 */
public class FacturationLigneSillon {

    private double cout;

    /**
     * Méthode permettant de fixer le cout à la valeur du cout de ligne ferroviaire, valeur dépendant du sillon horaire et de la ligne
     * @param ligneFerroviaire ligne du trajet dont on veut obtenir le cout
     */
    public void visiter(LigneFerroviaire ligneFerroviaire) {
        cout += ligneFerroviaire.getCoutUtilisationLigneEtSillon();
    }

    public double getCout() {return cout;}
}
