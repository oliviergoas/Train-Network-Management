package fr.univ_amu.iut.traitement.Fabrique;

public interface FabriqueAbstraite<T> {
    public T creerTransportBetail();
    public T creerTransportMarchandises();
    public T creerTransportPassagers();
    public T creerTransportDechets();
}
