package fr.univ_amu.iut.affichage;

import fr.univ_amu.iut.traitement.*;
import javafx.application.Application;
import fr.univ_amu.iut.traitement.Controleur.*;
import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.*;
import fr.univ_amu.iut.traitement.Train.Train;
import fr.univ_amu.iut.traitement.Train.TrainPassagers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class ApplicationHyperFrisette extends Application {
    // Train qui circulent
    private static List<Trajet> trajets = new ArrayList<>();

    // Quelle heure est-il ?
    private static LocalTime horloge = LocalTime.of(0, 0);

    /* France :
     * La Republique francaise est un Etat transcontinental souverain,
     * dont le territoire métropolitain est situe en Europe de l Ouest.
     */
    private static BorderPane paneFrance = new BorderPane();

    // Attributs globaux et constaux pour la position des villes, donc des gares
    private static final int X_LILLE = 468;
    private static final int Y_LILLE = 50;

    private static final int X_PARIS = 435;
    private static final int Y_PARIS = 250;

    private static final int X_LYON = 580;
    private static final int Y_LYON = 480;

    private static final int X_BORDEAUX = 245;
    private static final int Y_BORDEAUX = 530;

    private static final int X_MARSEILLE = 628;
    private static final int Y_MARSEILLE = 685;

    /**
     * Obtenir la liste des trajets
     *
     * @return trajets
     */
    public static List<Trajet> getTrajets() {
        return trajets;
    }

    /**
     * Fonction de saisie d'un entier avec un objet Scanner entre les bornes données.
     *
     * @param scanner
     * @param min borne minimum
     * @param max borne maximum
     * @return valeur saisie
     */
    public static int saisirNombre(Scanner scanner, int min, int max) {
        int nb = min - 1;
        while(nb < min || nb > max) {
            System.out.print("> ");
            nb = scanner.nextInt();
        }
        return nb;
    }

    /**
     * Fonction de saisie de texte avec un objet BufferedReader.
     *
     * @param buffer
     * @return texte saisie par l'utlisateur
     */
    public static String saisirTexte(BufferedReader buffer) {
        String texte = "";
        try {
            System.out.print("> ");
            texte = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texte;
    }

    /**
     * Initialisation des parametres JavaFX la fenetre de la vue reseau.
     *
     * @param stage Conteneur principal de la fenetre
     * @param pane Conteneur englobant la carte
     * @param menuBar Barre de menu
     */
    public void configurerFenetreVueReseau(Stage stage, BorderPane pane, MenuBar menuBar) {
        stage.setTitle("Application HyperFrisette");
        stage.setMinHeight(830);
        stage.setMinWidth(810);
        stage.setResizable(true);

        BackgroundImage image = new BackgroundImage(
                new Image("http://imageshack.com/a/img924/9233/dyyV8t.png", 800,800, true, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(image));

        menuBar.setUseSystemMenuBar(true);
        HBox hBoxTop = new HBox();
        hBoxTop.getChildren().add(menuBar);

        pane.setTop(hBoxTop);
        stage.setScene(new Scene(pane, 830, 810));
    }

    /**
     * Creation et affichagerTrajetConsole de tous les elements qui composent la fenetre de la vue du reseau.
     *
     *
     */
    public void remplirFenetreVueReseau(Stage stage) {
        MenuBar menuBar = creerMenuBar();

        // Bloc d'initialisation de l'application
        configurerFenetreVueReseau(stage, paneFrance, menuBar);
        Map<String, Gare> gares = creerGares();

        Trajet exempleTrajet = creerExempleTrajet(gares);
        trajets.add(exempleTrajet);

        // Bouton pour actionner le trajet
        Button boutonActionner = new Button("Actionner");

        // VBox qui contient l'heure et le bouton pour actionner le trajet
        VBox vBoxRight = new VBox();
        vBoxRight.setSpacing(10);
        vBoxRight.getChildren().addAll(boutonActionner, creerHorloge());
        paneFrance.setRight(vBoxRight);

        boutonActionner.setOnAction((EventHandler<ActionEvent>) event -> {
            for(Trajet t : trajets) {
                // Si l'heure correspond au sillon d'un train, il démarrre
                if(t.getLigneCourante().getSillon().getHoraireDeb().equals(horloge)) {
                    t.setEnCirculation(true);
                    Circle exempleCircleTrain = initialiserTrain(paneFrance, exempleTrajet);
                }

                // Si un train est en circulation, il avance
                if(t.isEnCirculation()) {
                    // On vérifie que la train puisse aller plus loin
                    if(t.actionner() == 1) {
                        t.setEnCirculation(false);
                        paneFrance.getChildren().remove(t.getCircleTrain());
                    } else {
                        deplacerTrain(paneFrance, t);
                    }
                }
            }

            // L'horloge tourne
            horloge = horloge.plusMinutes(10);
            if(horloge.getHour() == 24)
                horloge = LocalTime.of(0, 0);
            vBoxRight.getChildren().remove(1);
            vBoxRight.getChildren().add(creerHorloge());
        });

        for (String string : gares.keySet()) {
            switch (string) {
                case "Lille":
                    Circle circleLille = new Circle(X_LILLE, Y_LILLE, 10, Paint.valueOf("BLACK"));
                    paneFrance.getChildren().add(circleLille);
                    break;
                case "Paris":
                    Circle circleParis = new Circle(X_PARIS, Y_PARIS, 10, Paint.valueOf("BLACK"));
                    paneFrance.getChildren().add(circleParis);
                    break;
                case "Lyon":
                    Circle circleLyon = new Circle(X_LYON, Y_LYON, 10, Paint.valueOf("BLACK"));
                    paneFrance.getChildren().add(circleLyon);
                    break;
                case "Bordeaux":
                    Circle circleBordeaux = new Circle(X_BORDEAUX, Y_BORDEAUX, 10, Paint.valueOf("BLACK"));
                    paneFrance.getChildren().add(circleBordeaux);
                    break;
                case "Marseille":
                    paneFrance.getChildren().add(new Circle(X_MARSEILLE, Y_MARSEILLE, 10, Paint.valueOf("BLACK")));
                    break;
            }
        }

        if(gares.keySet().contains("Lille") && gares.keySet().contains("Paris")) {
            paneFrance.getChildren().add(new Line(X_LILLE, Y_LILLE, X_PARIS, Y_PARIS));
        }
        if(gares.keySet().contains("Paris") && gares.keySet().contains("Lyon")) {
            paneFrance.getChildren().add(new Line(X_PARIS, Y_PARIS, X_LYON,Y_LYON));
        }
        if(gares.keySet().contains("Paris") && gares.keySet().contains("Bordeaux")) {
            paneFrance.getChildren().add(new Line(X_PARIS, Y_PARIS, X_BORDEAUX, Y_BORDEAUX));
        }
        if(gares.keySet().contains("Lyon") && gares.keySet().contains("Bordeaux")) {
            paneFrance.getChildren().add(new Line(X_LYON, Y_LYON, X_BORDEAUX, Y_BORDEAUX));
        }
        if(gares.keySet().contains("Lyon") && gares.keySet().contains("Marseille")) {
            paneFrance.getChildren().add(new Line(X_LYON, Y_LYON, X_MARSEILLE, Y_MARSEILLE));
        }
        if(gares.keySet().contains("Bordeaux") && gares.keySet().contains("Marseille")) {
            paneFrance.getChildren().add(new Line(X_BORDEAUX, Y_BORDEAUX, X_MARSEILLE, Y_MARSEILLE));
        }
    }

    /**
     * Configure la fenetre VueDocumentation
     *
     */
    public void configurerFenetreVueDocumentation(Stage stage) {
        stage.setTitle("Documentation HyperFrisette");
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.setResizable(true);

        BorderPane pane = new BorderPane();

        Text text1 = new Text("Ceci est la documentation du projet HyperFrisette");
        Text text2 = new Text("Notre équipe est composée de :\n" +
                "- Romain Colonna D'istria\n" +
                "- Julie Fardeau\n" +
                "- Christophe Gaspard Dos Santos\n" +
                "- Axel Gatineau Philippe\n" +
                "- Olivier Goasampis");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(text1, text2);
        pane.setCenter(vBox);

        stage.setScene(new Scene(pane));
    }

    /**
     * Remplie le stage avec la vue configure par configurerFenetreVueDocumentation.
     *
     * @param stage
     */
    public void remplirFenetreVueDocumentation(Stage stage) {
        configurerFenetreVueDocumentation(stage);
    }

    /**
     * Generation de l horloge sous forme de texte.
     *
     * @return l horloge (texte) creee
     */
    public Text creerHorloge() {
        Text horlogeTexte = new Text(horloge.getHour() + "h " + horloge.getMinute() + "m");
        horlogeTexte.setFont(Font.font("Verdana", 20));
        return horlogeTexte;
    }

    /**
     * Creation des différentes gare constituant le reseau ferroviaire
     *
     * @return les gares creees
     */
    public static Map<String, Gare> creerGares() {
        Gare gareLille     = new Gare("Europe", "Lille");
        Gare gareParis     = new Gare("St-Lazare", "Paris");
        Gare gareLyon      = new Gare("St-Exupery", "Lyon");
        Gare gareBordeaux  = new Gare("St-Jean", "Bordeaux");
        Gare gareMarseille = new Gare("St-Charles", "Marseille");

        gareLille.addGareDesservie(gareParis);

        gareParis.addGareDesservie(gareLille);
        gareParis.addGareDesservie(gareBordeaux);
        gareParis.addGareDesservie(gareLyon);

        gareLyon.addGareDesservie(gareParis);
        gareLyon.addGareDesservie(gareBordeaux);
        gareLyon.addGareDesservie(gareMarseille);

        gareBordeaux.addGareDesservie(gareParis);
        gareBordeaux.addGareDesservie(gareLyon);
        gareBordeaux.addGareDesservie(gareMarseille);

        gareMarseille.addGareDesservie(gareLyon);
        gareMarseille.addGareDesservie(gareBordeaux);

        Map<String, Gare> gares = new HashMap<>();
        gares.put("Lille", gareLille);
        gares.put("Paris", gareParis);
        gares.put("Lyon", gareLyon);
        gares.put("Bordeaux", gareBordeaux);
        gares.put("Marseille", gareMarseille);

        return gares;
    }

    /**
     * Methode permettant de creer un trajet dans le but de pouvoir varier entre différents trajets sur l interface IHM.
     *
     * @param gares liste des gares qui seront desservies durant le trajet
     * @return trajet qui sera le nouveau trajet crée
     */

    public static Trajet creerTrajet(Map<String, Gare> gares) {
        EntrepriseFerroviaire entreprise = EntrepriseFerroviaire.creerEntreprise();
        Train train = Train.creerTrain(entreprise);

        List<LigneFerroviaire> lignes = new ArrayList<>();
        Set<Sillon> sillons = new TreeSet<>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon o1, Sillon o2) {
                return o1.getHoraireDeb().compareTo(o2.getHoraireDeb());
            }
        });

        String gareDepart;
        String gareArrivee;
        int heureDepart;

        do {
            System.out.println("Saisir une gare de départ");
            do {
                gareDepart = saisirTexte(new BufferedReader(new InputStreamReader(System.in)));
            } while (!gares.containsKey(gareDepart));

            System.out.println("Saisir une gare d'arrivée");
            do {
                gareArrivee = saisirTexte(new BufferedReader(new InputStreamReader(System.in)));
            } while (!gares.containsKey(gareArrivee));
            // Il faut que la gare d'arrivée soit accessible depuis la gare de départ
        } while (!gares.get(gareDepart).getGaresDesservies().contains(gares.get(gareArrivee)));

        System.out.println("Saisir une heure de départ");
        heureDepart = saisirNombre(new Scanner(System.in), 0, 23);

        LigneFerroviaire ligne = new LigneFerroviaire(gares.get(gareDepart), gares.get(gareArrivee));
        Sillon sillon = new Sillon(LocalTime.of(heureDepart, 0));

        lignes.add(ligne);
        sillons.add(sillon);

        Trajet trajet = new Trajet(train, lignes);

        System.out.println("Saisir le type de stratégie que vous souhaitez utiliser : " + '\n'
                + "1. Stratégie glouton" + '\n'
                + "2. Stratégie prix minimum" + '\n'
                + "3. Stratégie temps minimum" + '\n'
                + "4. Stratégie trajet le plus rapide" + '\n');

        int typeStrategie = saisirNombre(new Scanner(System.in), 1, 4);
        StrategieControleur strategieControleur = new StrategieGlouton();
        Controleur.setStrategie(strategieControleur);

        /*
        if(typeStrategie == 1) {
            //strategieControleur = new StrategieGlouton();
            Controleur.setStrategie(strategieControleur);
            Controleur.executerStrategie(trajet, sillons);
        }
        else if(typeStrategie == 2) {
            strategieControleur = new StrategieMinPrix();
            Controleur.setStrategie(strategieControleur);
            Controleur.executerStrategie(trajet, sillons);
        }
        else if(typeStrategie == 3) {
            strategieControleur = new StrategieMinTemps();
            Controleur.setStrategie(strategieControleur);
            Controleur.executerStrategie(trajet, sillons);
        }
        else if(typeStrategie == 4) {
            strategieControleur = new StrategieTrajetPlusRapide();
            Controleur.setStrategie(strategieControleur);
            Controleur.executerStrategie(trajet, sillons);
        }
        */

        return trajet;
    }

    /**
     * Creation de la barre de menu en haut de la fenetre
     *
     * @return l objet MenuBar cree
     */
    public MenuBar creerMenuBar() {
        final Menu application = new Menu("Application Hyperfrisette");
        final MenuItem quitter = new MenuItem("Quitter l'application");

        final Menu parametre = new Menu("Paramètres");
        final MenuItem nouveauTrajet = new MenuItem("Nouveau trajet");

        final Menu apropos = new Menu("À propos de");
        final MenuItem documentation = new MenuItem("Documentation");

        quitter.setOnAction((EventHandler<ActionEvent>) event -> {
            Platform.exit();
        });

        nouveauTrajet.setOnAction((EventHandler<ActionEvent>) event -> {
            Trajet trajet = creerTrajet(creerGares());
            trajets.add(trajet);
        });

        documentation.setOnAction((EventHandler<ActionEvent>) event -> {
            Stage stageDocumentation = new Stage();
            remplirFenetreVueDocumentation(stageDocumentation);
            stageDocumentation.show();
        });

        application.getItems().add(quitter);
        apropos.getItems().add(documentation);
        parametre.getItems().add(nouveauTrajet);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(application, parametre, apropos);

        return menuBar;
    }

    /**
     * Remplit le premier paramètre avec les coordonnées d une des deux gares de la ligne en paramètre
     *
     * @param point a paramettrer
     * @param ligne contenant l une des deux gares avec laquelle on va paramettrer les coordonnees du point
     * @param laquelle indique la gare qui nous intéresse : 1 pour depart, 2 pour arrivee
     * @return Coordonnees de la ville
     */
    public Point remplirPointCoordonees(Point point, LigneFerroviaire ligne, int laquelle) {
        String ville = "";
        if (laquelle == 1) {
            ville = ligne.getGareDep().getVille();
        }
        else if (laquelle == 2) {
            ville = ligne.getGareArr().getVille();
        }

        switch (ville) {
            case "Lille":
                point.x = X_LILLE;
                point.y = Y_LILLE;
                break;
            case "Paris":
                point.x = X_PARIS;
                point.y = Y_PARIS;
                break;
            case "Lyon":
                point.x = X_LYON;
                point.y = Y_LYON;
                break;
            case "Bordeaux":
                point.x = X_BORDEAUX;
                point.y = Y_BORDEAUX;
                break;
            case "Marseille":
                point.x = X_MARSEILLE;
                point.y = Y_MARSEILLE;
                break;
        }

        return point;
    }

    /**
     * Création d'un exemple de trajet
     *
     * @param gares map associant une gare a une string designant la ville ou est situe la gare
     * @return le trajet créé
     *
     */
    public static Trajet creerExempleTrajet(Map<String, Gare> gares) {
        Train train = new TrainPassagers.Builder(new EntrepriseFerroviairePassagers())
                .addHauteur(15).addLargeur(500).addLargeur(600)
                .addLongueur(800).addVitesseMax(200).addWagons(null).build();

        LigneFerroviaire ligne1 = new LigneFerroviaire(gares.get("Paris"), gares.get("Bordeaux"));
        LigneFerroviaire ligne2 = new LigneFerroviaire(gares.get("Bordeaux"), gares.get("Lyon"));
        LigneFerroviaire ligne3 = new LigneFerroviaire(gares.get("Lyon"), gares.get("Marseille"));

        List<LigneFerroviaire> lignes = new ArrayList<>();
        lignes.add(ligne1);
        lignes.add(ligne2);
        lignes.add(ligne3);

        Trajet trajet = new Trajet(train, lignes);

        Sillon sillon1 = new Sillon(LocalTime.of(1, 0));
        Sillon sillon2 = new Sillon(LocalTime.of(2, 0));
        Sillon sillon3 = new Sillon(LocalTime.of(3, 0));

        Set<Sillon> sillons = new TreeSet<Sillon>(new Comparator<Sillon>() {
            @Override
            public int compare(Sillon o1, Sillon o2) {
                return o1.getHoraireDeb().compareTo(o2.getHoraireDeb());
            }
        });
        sillons.add(sillon1);
        sillons.add(sillon2);
        sillons.add(sillon3);

        StrategieControleur strategieControleur = new StrategieGlouton();
        Controleur.setStrategie(strategieControleur);
        Controleur.executerStrategie(trajet, sillons);

        return trajet;
    }

    /**
     * Affiche le train d'un trajet donné pour la première fois sur sa gare de départ
     *
     * @param pane Conteneur ayant la carte de la France
     * @param trajet trajet que le train va parcourir
     * @return
     */
    public Circle initialiserTrain(BorderPane pane, Trajet trajet) {
        Point departPoint  = remplirPointCoordonees(new Point(), trajet.getLigneCourante(), 1);
        Point arriveePoint = remplirPointCoordonees(new Point(), trajet.getLigneCourante(), 2);

        Line line = new Line(departPoint.x, departPoint.y, arriveePoint.x, arriveePoint.y);
        line.setStroke(Paint.valueOf("YELLOW"));
        line.setStrokeWidth(2);
        pane.getChildren().add(line);

        Circle circleTrain = new Circle(departPoint.x, departPoint.y, 8, Paint.valueOf("RED"));
        trajet.setCircleTrain(circleTrain);
        pane.getChildren().add(circleTrain);

        return circleTrain;
    }

    /**
     * Déplace le train d'un trajet donné sur sa ligne, d'un sixième de la distance de la ligne (10 minutes)
     *
     * @param pane Conteneur ayant la carte de la France
     * @param trajet Trajet que le train va parcourir
     *
     */
    public void deplacerTrain(BorderPane pane, Trajet trajet) {
        Point departPoint  = remplirPointCoordonees(new Point(), trajet.getLigneCourante(), 1);
        Point arriveePoint = remplirPointCoordonees(new Point(), trajet.getLigneCourante(), 2);

        double deplacementX = (arriveePoint.x - departPoint.x) / 6;
        double deplacementY = (arriveePoint.y - departPoint.y) / 6;

        if(trajet.getLigneCourante().getPosition() > 6) {
            trajet.getCircleTrain().setCenterX(arriveePoint.x);
            trajet.getCircleTrain().setCenterY(arriveePoint.y);
        }
        else {
            trajet.getCircleTrain().setCenterX(trajet.getCircleTrain().getCenterX() + deplacementX);
            trajet.getCircleTrain().setCenterY(trajet.getCircleTrain().getCenterY() + deplacementY);
        }

        if(trajet.getLigneCourante().getPosition() > 6) {
            Line line = new Line(departPoint.x, departPoint.y, arriveePoint.x, arriveePoint.y);
            line.setStroke(Paint.valueOf("BLACK"));
            line.setStrokeWidth(1);
            pane.getChildren().add(line);

            if(trajet.getLigneSuivante() != null) {
                departPoint  = remplirPointCoordonees(new Point(), trajet.getLigneSuivante(), 1);
                arriveePoint = remplirPointCoordonees(new Point(), trajet.getLigneSuivante(), 2);

                line = new Line(departPoint.x, departPoint.y, arriveePoint.x, arriveePoint.y);
                line.setStroke(Paint.valueOf("YELLOW"));
                line.setStrokeWidth(2);
                pane.getChildren().add(line);
            }
        }
    }

    /**
     * Permet d'afficher le reseau sur la console avec un train parcourant un trajet
     *
     * @param trajet trajet que le train va parcourir
     */

    public static void afficherReseauConsole(Trajet trajet) {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int temp;

        System.out.println();

        for (int pos = 0; pos < 8; ++pos) {
            if (pos == 0 || pos == 7) {
                System.out.print("[G]");
            } else if (pos == trajet.getLigneCourante().getPosition())
                System.out.print('>');
            else
                System.out.print("==");
        }

        System.out.println();

        try {
            temp = buf.read();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    /**
     * Pour chaque ligneFerroviaires de trajet, affiche le nom et la ville de la gare de depart ainsi
     * que l'horaire de depart du sillon concerne.
     *
     * @param trajet trajet que le train va parcourir
     */

    public static void afficherPanneauDepartConsole(Trajet trajet) {
        for(LigneFerroviaire ligne : trajet.getLignes()) {
            System.out.println(ligne.getGareDep().getVille() + ' '
                    + ligne.getGareDep().getNom() + " -> "
                    + ligne.getSillon().getHoraireDeb());
        }
    }

    /**
     * Pour chaque ligneFerroviaires de trajet, affiche le nom et la ville de la gare de arrivee ainsi
     * que l'horaire de depart du sillon concerne.
     *
     * @param trajet trajet que le train va parcourir
     */

    public static void afficherPanneauArriveeConsole(Trajet trajet) {
        for(LigneFerroviaire ligne : trajet.getLignes()) {
            System.out.println(ligne.getGareArr().getVille() + ' '
                    + ligne.getGareArr().getNom() + " -> "
                    + ligne.getSillon().getHoraireFin());
        }
    }

    /**
     * Affiche un mesage d'avertissement comme quoi le train est accidente sur cette ligne
     *
     * @param trajet trajet que le train va parcourir
     */

    public static void afficherPanneauAccidentConsole(Trajet trajet) {
        if(trajet.getTrain().getEstAccidente())
            System.out.println("Le train est accidenté sur ce trajet.");
        else
            System.out.println("Le train n'est pas accidenté sur ce trajet.");
    }

    public static void affichagerTrajetConsole(Trajet trajet) {
        if(trajet.getTrain().getEstAccidente())
            afficherPanneauAccidentConsole(trajet);
        else {
            afficherPanneauDepartConsole(trajet);
            System.out.println();
            afficherPanneauArriveeConsole(trajet);
        }
    }

    /**
     * Lance l'application JavaFX
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // Affichage console d'un trajet
        /*BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            for(buffer.readLine(); true ;buffer.readLine()) {
                trajet.afficher();
                trajet.actionner();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Stage stageVueReseau = new Stage();
        remplirFenetreVueReseau(stageVueReseau);
        stageVueReseau.show();

        AffichagePerturbationsDansReseau perturbationsDansReseau = new AffichagePerturbationsDansReseau();
        perturbationsDansReseau.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
