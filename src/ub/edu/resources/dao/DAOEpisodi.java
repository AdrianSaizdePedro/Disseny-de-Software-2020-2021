package ub.edu.resources.dao;

import ub.edu.model.Episodi;
import ub.edu.model.Temporada;

import java.util.List;

public interface DAOEpisodi extends DAO<Episodi> {

    List<Episodi> getEpisodisForTemporada(Temporada t);

    Episodi getEpisodiBySerieTemporadaEpisodi(String idSerie, int idTemporada, int idEpisodi);

}
