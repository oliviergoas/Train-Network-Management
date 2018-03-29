package fr.univ_amu.iut.traitement.Fonctionnalite;

/**
 * Classe de definition du streaming video d HyperFrisette.
 */
public class FonctionnaliteStreamingVideo implements Fonctionnalite {

    /**
     * Permet d'utiliser le streaming video d HyperFrisette/
     */
    @Override
    public void utiliser() {
        System.out.println("Connecté au streaming vidéo d'HyperFrisette.");
    }
}
