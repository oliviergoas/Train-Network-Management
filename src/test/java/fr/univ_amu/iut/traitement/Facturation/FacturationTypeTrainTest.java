package fr.univ_amu.iut.traitement.Facturation;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviaire;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviairePassagers;
import fr.univ_amu.iut.traitement.Train.Train;
import fr.univ_amu.iut.traitement.Train.TrainMarchandises;
import fr.univ_amu.iut.traitement.Wagon.Wagon;
import fr.univ_amu.iut.traitement.Wagon.WagonMarchandises;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by g15010642 on 16/11/17.
 */
public class FacturationTypeTrainTest {
    @Test
    public void visiteur_TrainPassager_Set_Cout_A_5555() throws Exception {
        FacturationTypeTrain facturationTypeTrain = new FacturationTypeTrain();
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
        monTrain.accepter(facturationTypeTrain);

        assertEquals(5555,facturationTypeTrain.getCout(),1e-1);
    }

    @Test
    public void visiter1() throws Exception {
    }

    @Test
    public void visiter2() throws Exception {
    }

    @Test
    public void visiter3() throws Exception {
    }

}