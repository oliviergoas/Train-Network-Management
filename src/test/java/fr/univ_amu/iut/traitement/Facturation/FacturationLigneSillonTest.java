package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.Exception.GareNonDesservieException;
import fr.univ_amu.iut.traitement.Gare;
import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Christophe GASPAR DOS SANTOS
 */
public class FacturationLigneSillonTest {

    @Test
    public void visiter() throws Exception, GareNonDesservieException {
        FacturationLigneSillon facturationLigneSillon = new FacturationLigneSillon();

        Gare gap = new Gare("Gap","Gap");
        Gare sisteron = new Gare("Sisteron","Sisteron");

        Sillon sillon = new Sillon(LocalTime.of(8,0));
        sillon.setCoutUtilisation(500);

        gap.addGareDesservie(sisteron);
        sisteron.addGareDesservie(gap);

        LigneFerroviaire ligneFerroviaire = new LigneFerroviaire(gap,sisteron);
        ligneFerroviaire.attribuerSillon(sillon);

        ligneFerroviaire.setCoutUtilisationLigne(800);
        ligneFerroviaire.setCoutUtilisationLigneEtSillon();

        facturationLigneSillon.visiter(ligneFerroviaire);

        assertEquals(1300,facturationLigneSillon.getCout(),1e-1);
    }
}