package ub.edu.resources.dao;

import ub.edu.model.EstrellasValoracio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOValoracioEstrellas extends DAO<EstrellasValoracio>{

    EstrellasValoracio getByCliUsuSerTempEpi(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi);

    void setValoracioEstrella(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, EstrellasValoracio v, String data) throws Exception;

    List<EstrellasValoracio> getListEstrellasByIdUser(String idUsuari);

    Map<String, ArrayList<EstrellasValoracio>> getMap();

}
