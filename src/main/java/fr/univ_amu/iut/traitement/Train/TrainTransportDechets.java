package fr.univ_amu.iut.traitement.Train;

import fr.univ_amu.iut.traitement.EntrepriseFerroviaire.*;
import fr.univ_amu.iut.traitement.Facturation.Visitable;
import fr.univ_amu.iut.traitement.Facturation.FacturationTrain;
import fr.univ_amu.iut.traitement.Wagon.Wagon;

import java.util.ArrayList;
import java.util.List;

public class TrainTransportDechets extends Train implements Visitable {

    /**
     * Construit un train de passagers en utilisant un builder.
     *
     * @param builder Builder servant a la creation.
     */
    private TrainTransportDechets(Builder builder) {
        super.setHauteur(builder.hauteur);
        super.setLargeur(builder.largeur);
        super.setLongueur(builder.longueur);
        super.setVitesseMax(builder.vitesseMax);
        super.setWagons(builder.wagons);
        //super.setProprietaire(builder.proprietaire);
        //proprietaire.aquerirTrain(this);
    }

    /**
     * Permet d'appliquer une facturation sur les trains.
     *
     * @param visiteur Type de facturation a appliquer.
     */
    public void accepter(FacturationTrain visiteur)
    {
        visiteur.visiter(this);
    }

    public double getCout() {
        return 0;
    }

    public int getVitesseMax() { return super.getVitesseMax(); }

    public List<Wagon> getWagons() {
        return super.getWagons();
    }

    /**
     * Classe interne static qui permet une creatoin flexible de train de marchandise.
     */
    public static class Builder {
        private int hauteur;
        private int largeur;
        private int longueur;
        private int vitesseMax;
        private EntrepriseFerroviaire proprietaire;
        private List<Wagon> wagons = new ArrayList<>();

        /**
         * Builder de la classe TrainTransportDechets
         *
         * @param entrepriseFerroviaire entreprise qui detient le train
         */
        public Builder(EntrepriseFerroviaire entrepriseFerroviaire) {
            this.proprietaire = entrepriseFerroviaire;
        }

        public Builder addHauteur(int hauteur) {
            this.hauteur = hauteur;
            return this;
        }

        public Builder addLargeur(int largeur) {
            this.largeur = largeur;
            return this;
        }

        public Builder addLongueur(int longueur) {
            this.longueur = longueur;
            return this;
        }

        public Builder addVitesseMax(int vitesseMax) {
            this.vitesseMax = vitesseMax;
            return this;
        }

        public Builder addProprietaire(EntrepriseFerroviaire proprietaire) {
            this.proprietaire = proprietaire;
            return this;
        }

        public Builder addWagons(List<Wagon> wagons) {
            this.wagons = wagons;
            return this;
        }

        public TrainTransportDechets build() {
            return new TrainTransportDechets(this);
        }
    }
}
