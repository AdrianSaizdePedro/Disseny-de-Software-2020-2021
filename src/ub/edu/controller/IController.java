package ub.edu.controller;

import ub.edu.model.Episodi;
import ub.edu.view.Observer;

import java.util.List;

public interface IController {

    void init() throws Exception;

    String addClient(String idClient, String psw, String dni, String adress, boolean vip);

    String addUser(String currentClient, String text);

    Iterable<String> listUsuaris(String nomClient);

    boolean validateClient(String text, String valueOf);

    Iterable<String> listMyList(String client, String user);

    String addSerieToMyList(int id, String client, String user, String serie);

    String removeSerieFromMyList(int id, String client, String user, String serie);

    List<String> llistarCatalegSeries();

    List<String> getTemporades(String nomSerie);

    List<Episodi> getEpisodis(String nomSerie, int temporada);

    int getDuracioVisualitzada(String idClient, String idUser, String idSerie, int numTemporada, int numEpisodi);

    String valorarEpisodiCor(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, String data);

    String valorarEpisodiEstrellas(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, int estrelles, String data);

    public void registerObserver(Observer observer);

}
