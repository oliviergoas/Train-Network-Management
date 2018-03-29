package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.Gare;
import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;

public class ControleurTest {

    //@Test(expected = SillonDejaAttribueException.class)

    public void verifierDisponibiliteSillion_lever_SillonDejaAttribueException()/* throws SillonDejaAttribueException, GareNonDesservieException*/ {

        Gare gare1 = new Gare("Aix TGV", "Aix");
        Gare gare2 = new Gare("St Charles", "Marseille");

        gare1.addGareDesservie(gare2);

        LigneFerroviaire ligneFerroviaire = new LigneFerroviaire(gare1, gare2);

        Sillon sillon = new Sillon(LocalTime.of(9,0));

        Controleur.attribuerSillon(ligneFerroviaire, new Sillon(LocalTime.of(9, 0)));
        Controleur.attribuerSillon(ligneFerroviaire, new Sillon(LocalTime.of(9,0)));
    }

    @Test
    public void attribuerSillon_Ajoute_Un_Nouveau_Sillon_A_sillonDejaAttribue()/* throws GareNonDesservieException, SillonDejaAttribueException*/ {
        Gare gare1 = new Gare("Aix TGV", "Aix");
        Gare gare2 = new Gare("St Charles", "Marseille");

        gare1.addGareDesservie(gare2);

        LigneFerroviaire ligneFerroviaire = new LigneFerroviaire(gare1, gare2);

        Sillon sillon = new Sillon(LocalTime.of(9,0));

        Controleur.attribuerSillon(ligneFerroviaire, new Sillon(LocalTime.of(9,0)));

        assert(Controleur.getSillonsDejaAttribues().containsKey(ligneFerroviaire));
        assertEquals(Controleur.getSillonsDejaAttribues().get(ligneFerroviaire),LocalTime.of(9,0));
    }


}
