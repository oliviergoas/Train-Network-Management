package fr.univ_amu.iut.affichage;

import fr.univ_amu.iut.affichage.ApplicationHyperFrisette;
import fr.univ_amu.iut.traitement.Trajet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalTime;

/**
 * AffichagePerturbationDansReseau sert a afficher les trains accidenté sur un
 * affichage semblable a ceux présent dans les gares.
 *
 * @author ROMAIN COLONNA D'ISTRIA
 *
 */
public class AffichagePerturbationsDansReseau extends Application{
    private BorderPane root = new BorderPane();
    private GridPane titres = new GridPane();

    private Label heure       = new Label("Heure");
    private Label gareDep     = new Label("Départ");
    private Label train       = new Label("Train");
    private Label destination = new Label("Destination");
    private Label accidente   = new Label("Accidenté");
    private Label retard      = new Label("Retard");

    /**
     * Methode principal de cette classe. Elle initialise
     * chaque element que contient la fenetre.
     *
     * @param primaryStage Conteneur accueillant chaque element de la fenetre.
     * @throws Exception
     *
     * @see #configStage(Stage)
     * @see #configBorderPanePrincipal()
     */
    public void start(Stage primaryStage){
        configStage(primaryStage);
        configBorderPanePrincipal();
    }

    /**
     * Configuration de la fenêtre principale. Y ajoute le titre, défini les
     * dimensions, ajoute le layout principal (ici un BorderPane), défini la fenêtre
     * comme non redimensionable et l'affiche.
     *
     * @param primaryStage Conteneur principal de la fenetre.
     */
    private void configStage(Stage primaryStage) {
        primaryStage.setTitle("Perturbations dans le réseau");
        root.setPrefSize(1000, 400);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Configure les informations affichees sur la fenetre. Configure
     * les titres et ajoute les informations sur chaque trains accidente.
     *
     * @see #configTitres()
     * @see #ajoutGridPaneTrains()
     */
    private void configBorderPanePrincipal(){
        configTitres();
        ajoutGridPaneTrains();
    }

    /**
     * Configure les titre contenue dans la page.
     *
     * @see #configLabels()
     */
    private void configTitres() {
        configLabels();

        ColumnConstraints columnConstraints = new ColumnConstraints(200);
        for (int i = 0; i < 5; ++i)
            titres.getColumnConstraints().add(columnConstraints);

        titres.add(heure, 0,0);
        titres.add(gareDep, 1, 0);
        titres.add(train, 2,0);
        titres.add(destination, 3,0);
        titres.add(accidente, 4,0);
        titres.add(retard, 5,0);

        root.setTop(titres);
    }

    /**
     * Configure les labels pour les titres. Ajoute un padding de 10 pixel
     * et augmente la taille de la police de 200%.
     */
    private void configLabels() {
        heure.setStyle("-fx-padding: 10;");       //ajoute une marge de 10 de chaque coté du GridPane
        titres.setStyle("-fx-font-size: 200%;");   //Augmente la taille des titres de 30%
    }

    /**
     * Récupère toutes les infos concernant les trains accidenté, les ajoute dans
     * un GridPane puis ajoute ce GridPane dans le conteneur principal.
     * Choisi un temps de retard aléatoire entre 10 et 120 minutes.
     */
    private void ajoutGridPaneTrains() {
        GridPane gridTemp = new GridPane();
        Label labelHeureTemp;
        Label labelGareDepTemp;
        //Label labelTrainTemp;
        Label labelDestinationTemp;
        Label labelAccidenteTemp;
        Label labelRetardTemp;

        int rand = (int) (Math.random() * (120 - 10));

        int i = 0;
        for (Trajet t : ApplicationHyperFrisette.getTrajets()) {
            if (t.getTrain().getEstAccidente()) {
                labelHeureTemp = new Label(t.getLigneCourante().getSillon().getHoraireDeb().toString());
                labelGareDepTemp = new Label(t.getLigneCourante().getGareDep().toString());
                //labelTrainTemp = new Label(t.getTrain().getId().toString());
                labelDestinationTemp = new Label(t.getLigneCourante().getGareArr().toString());
                labelAccidenteTemp = new Label("oui");
                labelRetardTemp = new Label(LocalTime.of(0, rand).toString());

                gridTemp.add(labelHeureTemp, i++, 0);
                gridTemp.add(labelGareDepTemp, i++, 0);
                //gridTemp.add(labelTrainTemp, i++, 0);
                gridTemp.add(labelDestinationTemp, i++, 0);
                gridTemp.add(labelAccidenteTemp, i++, 0);
                gridTemp.add(labelRetardTemp, i++, 0);
            }
        }
    }

    public void test() {
        ApplicationHyperFrisette.creerTrajet(ApplicationHyperFrisette.creerGares());
    }
}
