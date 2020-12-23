package ub.edu.resources.dao;

import ub.edu.model.CorValoracio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOValoracioCor extends DAO<CorValoracio> {

    CorValoracio getByCliUsuSerTempEpi(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi);

    void setValoracioCor(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, CorValoracio v, String data) throws Exception;

    List<CorValoracio> getListCorByIdUser(String idUsuari);

    Map<String, ArrayList<CorValoracio>> getMap();

}
