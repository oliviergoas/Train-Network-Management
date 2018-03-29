package fr.univ_amu.iut.traitement.Controleur;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.EntrepriseFerroviairePassagers;
import fr.univ_amu.iut.traitement.Gare;
import fr.univ_amu.iut.traitement.LigneFerroviaire;
import fr.univ_amu.iut.traitement.Sillon;
import fr.univ_amu.iut.traitement.Train.TrainPassagers;
import fr.univ_amu.iut.traitement.Trajet;

public class StrategieMinPrixTest {
   /*@Test
    public void appliquer() throws Exception, GareNonDesservieException {
        Train train = new TrainPassagers.Builder(new EntrepriseFerroviairePassagers())
                .addHauteur(15).addLargeur(500).addLargeur(600).addLongueur(800).addVitesseMax(200).addWagons(null).build();

        // Création des différentes gare constituant le réseau férroviaire
        Gare gareLille     = new Gare("Europe", "Lille");
        Gare gareParis     = new Gare("St-Lazare", "Paris");
        Gare gareLyon      = new Gare("St-Exupery", "Lyon");
        Gare gareBordeaux  = new Gare("St-Jean", "Bordeaux");
        Gare gareMarseille = new Gare("St-Charles", "Marseille");

        gareLyon.addGareDesservie(gareParis);
        gareLyon.addGareDesservie(gareBordeaux);
        gareLyon.addGareDesservie(gareMarseille);
        gareBordeaux.addGareDesservie(gareParis);
        gareBordeaux.addGareDesservie(gareLyon);
        gareBordeaux.addGareDesservie(gareMarseille);
        gareMarseille.addGareDesservie(gareLyon);
        gareMarseille.addGareDesservie(gareBordeaux);
        gareParis.addGareDesservie(gareBordeaux);
        gareLille.addGareDesservie(gareParis);



        LigneFerroviaire ligne1 = new LigneFerroviaire(gareParis, gareBordeaux);
        LigneFerroviaire ligne2 = new LigneFerroviaire(gareBordeaux, gareLyon);
        LigneFerroviaire ligne3 = new LigneFerroviaire(gareLyon, gareMarseille);

        List<LigneFerroviaire> lignes = new ArrayList<>();
        lignes.add(ligne1);
        lignes.add(ligne2);
        lignes.add(ligne3);


        Trajet trajet = new Trajet(train, lignes);

        Sillon sillon1 = new Sillon(LocalTime.of(8, 0));
        sillon1.setCoutUtilisation(500);
        Sillon sillon2 = new Sillon(LocalTime.of(9, 0));
        sillon2.setCoutUtilisation(400);
        Sillon sillon3 = new Sillon(LocalTime.of(10, 0));
        sillon3.setCoutUtilisation(350);


        Set<Sillon> sillons = new TreeSet<Sillon>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon o1, Sillon o2) {
                return o1.getHoraireDeb().compareTo(o2.getHoraireDeb());
            }
        });
        sillons.add(sillon1);
        sillons.add(sillon2);
        sillons.add(sillon3);


        StrategieControleur strategieControleur = new StrategieMinPrix();
        Controleur.setStrategie(strategieControleur);
        Controleur.executerStrategie(trajet, sillons);

        System.out.println(Controleur.getSillonsDejaAttribues());

        assertTrue(Controleur.getSillonsDejaAttribues().containsKey(ligne1));
        assertTrue(Controleur.getSillonsDejaAttribues().containsKey(ligne2));
        assertTrue(Controleur.getSillonsDejaAttribues().containsKey(ligne3));
        assertEquals(sillon1.getHoraireDeb(),Controleur.getSillonsDejaAttribues().get(ligne1));
        assertEquals(sillon2.getHoraireDeb(),Controleur.getSillonsDejaAttribues().get(ligne2));
        assertEquals(sillon3.getHoraireDeb(),Controleur.getSillonsDejaAttribues().get(ligne3));
    }*/
}