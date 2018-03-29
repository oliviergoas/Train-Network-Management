package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviaire;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviaireCargo;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviairePassagers;
import fr.univ_amu.iut.traitement.Train.*;
import fr.univ_amu.iut.traitement.Wagon.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by g15010642 on 16/11/17.
 */
public class FacturationNombreWagonTest {


    @Test
    public void visiteur_TrainMarchandise_Set_Cout_A_500_Pour_Un_Train_De_4_Wagons_Et_PrixDuWagon_100() throws Exception {

        FacturationNombreWagon facturationNombreWagon = new FacturationNombreWagon(100);
        List<Wagon> maListeDeWagon = new ArrayList<Wagon>();
        maListeDeWagon.add(new WagonMarchandises());
        maListeDeWagon.add(new WagonMarchandises());
        maListeDeWagon.add(new WagonMarchandises());
        maListeDeWagon.add(new WagonMarchandises());

        EntrepriseFerroviaire entrepriseFerroviaire = new EntrepriseFerroviaireCargo();
        Train monTrain = new TrainMarchandises.Builder(entrepriseFerroviaire)
                .addHauteur(3)
                .addLargeur(4)
                .addLongueur(50)
                .addVitesseMax(80)
                .addWagons(maListeDeWagon)
                .build();
        monTrain.accepter(facturationNombreWagon);

        assertEquals(500, facturationNombreWagon.getCout(), 1e-1);
    }
    @Test
    public void visiteur_TrainPassager_Set_Cout_A_1250_Pour_Un_Train_De_4_Wagons_Et_PrixDuWagon_250() throws Exception {

        FacturationNombreWagon facturationNombreWagon = new FacturationNombreWagon(250);
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
        monTrain.accepter(facturationNombreWagon);

        assertEquals(1250, facturationNombreWagon.getCout(), 1e-1);

    }

    @Test
    public void visiteur_TrainTransportBetails_Set_Cout_A_1500_Pour_Un_Train_De_4_Wagons_Et_PrixDuWagon_300() throws Exception {

        FacturationNombreWagon facturationNombreWagon = new FacturationNombreWagon(300);
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
        monTrain.accepter(facturationNombreWagon);

        assertEquals(1500, facturationNombreWagon.getCout(), 1e-1);
    }

    @Test
    public void visiteur_TrainTransportDechets_Set_Cout_A_20000_Pour_Un_Train_De_4_Wagons_Et_PrixDuWagon_400() throws Exception {


        FacturationNombreWagon facturationNombreWagon = new FacturationNombreWagon(400);
        List<Wagon> maListeDeWagon = new ArrayList<Wagon>();
        maListeDeWagon.add(new WagonTransportDechets());
        maListeDeWagon.add(new WagonTransportDechets());
        maListeDeWagon.add(new WagonTransportDechets());
        maListeDeWagon.add(new WagonTransportBetails());

        EntrepriseFerroviaire entrepriseFerroviaire = new EntrepriseFerroviairePassagers();
        Train monTrain = new TrainTransportDechets.Builder(entrepriseFerroviaire)
                .addHauteur(3)
                .addLargeur(4)
                .addLongueur(50)
                .addVitesseMax(80)
                .addWagons(maListeDeWagon)
                .build();
        monTrain.accepter(facturationNombreWagon);

        assertEquals(2000, facturationNombreWagon.getCout(), 1e-1);
    }
}
