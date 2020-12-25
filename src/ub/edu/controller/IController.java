package ub.edu.controller;

import ub.edu.model.Episodi;

import java.util.List;

public interface IController {

    void init() throws Exception;

    String addClient(String idClient, String psw, String dni, String adress, boolean vip);

    String addUser(String currentClient, String text);

    Iterable<String> listUsuaris(String nomClient);

    boolean validateClient(String text, String valueOf);

    List<String> llistarCatalegSeries();

    List<String> getTemporades(String nomSerie);

    List<Episodi> getEpisodis(String nomSerie, int temporada);
}
