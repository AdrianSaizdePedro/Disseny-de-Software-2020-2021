package ub.edu.resources.dao;

import ub.edu.model.Serie;
import ub.edu.model.Temporada;

import java.util.List;

public interface DAOTemporada extends DAO<Temporada> {

    List<Temporada> getTemporadesForSerie(Serie s);

}
