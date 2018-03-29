package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviaire;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviairePassagers;
import fr.univ_amu.iut.traitement.Train.*;
import fr.univ_amu.iut.traitement.Wagon.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @author Christophe GASPAR DOS SANTOS
 */
public class FacturationVitesseMaxTest {


    @Test
    public void visiteur_TrainPassager_Set_Cout_A_3200_Pour_Vitesse_80_Et_PrixKmH_40() throws Exception {

        FacturationVitesseMax facturationVitesseMax = new FacturationVitesseMax(40);
        List<Wagon> maListeDeWagon = new ArrayList<Wagon>();
        maListeDeWagon.add(new WagonPassagers());
        maListeDeWagon.add(new WagonPassagers());
        maListeDeWagon.add(new WagonPassagers());
        maListeDeWagon.add(new WagonPassagers());

        EntrepriseFerroviaire entrepriseFerroviaire = new EntrepriseFerroviairePassagers();
        Train monTrain = new TrainPassagers.Builder(entrepriseFerroviaire)
                .addHauteur(3)
                .addLargeur(4)
                .addLongueur(50)
                .addVitesseMax(80)
                .addWagons(maListeDeWagon)
                .build();
        monTrain.accepter(facturationVitesseMax);

        assertEquals(3200, facturationVitesseMax.getCout(), 1e-1);

    }

    @Test
    public void visiteur_TrainMarchandise_Set_Cout_A_4400_Pour_Vitesse_80_Et_PrixKmH_50() throws Exception {

        FacturationVitesseMax facturationVitesseMax = new FacturationVitesseMax(55);
        List<Wagon> maListeDeWagon = new ArrayList<Wagon>();
        maListeDeWagon.add(new WagonMarchandises());
        maListeDeWagon.add(new WagonMarchandises());
        maListeDeWagon.add(new WagonMarchandises());
        maListeDeWagon.add(new WagonMarchandises());

        EntrepriseFerroviaire entrepriseFerroviaire = new EntrepriseFerroviairePassagers();
        Train monTrain = new TrainMarchandises.Builder(entrepriseFerroviaire)
                .addHauteur(3)
                .addLargeur(4)
                .addLongueur(50)
                .addVitesseMax(80)
                .addWagons(maListeDeWagon)
                .build();
        monTrain.accepter(facturationVitesseMax);

        assertEquals(4400, facturationVitesseMax.getCout(), 1e-1);

    }

    @Test
    public void visiteur_TrainBetail_Set_Cout_A_5520_Pour_Vitesse_80_Et_PrixKmH_69() throws Exception {

        FacturationVitesseMax facturationVitesseMax = new FacturationVitesseMax(69);
        List<Wagon> maListeDeWagon = new ArrayList<Wagon>();
        maListeDeWagon.add(new WagonTransportBetails());
        maListeDeWagon.add(new WagonTransportBetails());
        maListeDeWagon.add(new WagonTransportBetails());
        maListeDeWagon.add(new WagonTransportBetails());

        EntrepriseFerroviaire entrepriseFerroviaire = new EntrepriseFerroviairePassagers();
        Train monTrain = new TrainTransportBetails.Builder(entrepriseFerroviaire)
                .addHauteur(3)
                .addLargeur(4)
                .addLongueur(50)
                .addVitesseMax(80)
                .addWagons(maListeDeWagon)
                .build();
        monTrain.accepter(facturationVitesseMax);

        assertEquals(5520, facturationVitesseMax.getCout(), 1e-1);

    }

    @Test
    public void visiteur_TrainBetail_Set_Cout_A_6080_Pour_Vitesse_80_Et_PrixKmH_76() throws Exception {

        FacturationVitesseMax facturationVitesseMax = new FacturationVitesseMax(76);
        List<Wagon> maListeDeWagon = new ArrayList<Wagon>();
        maListeDeWagon.add(new WagonTransportDechets());
        maListeDeWagon.add(new WagonTransportDechets());
        maListeDeWagon.add(new WagonTransportDechets());
        maListeDeWagon.add(new WagonTransportDechets());

        EntrepriseFerroviaire entrepriseFerroviaire = new EntrepriseFerroviairePassagers();
        Train monTrain = new TrainTransportBetails.Builder(entrepriseFerroviaire)
                .addHauteur(3)
                .addLargeur(4)
                .addLongueur(50)
                .addVitesseMax(80)
                .addWagons(maListeDeWagon)
                .build();
        monTrain.accepter(facturationVitesseMax);

        assertEquals(6080, facturationVitesseMax.getCout(), 1e-1);

    }

}