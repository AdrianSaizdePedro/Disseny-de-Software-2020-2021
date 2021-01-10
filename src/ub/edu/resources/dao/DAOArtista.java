package ub.edu.resources.dao;

import ub.edu.model.Artista;
import ub.edu.model.Serie;

import java.util.List;

public interface DAOArtista extends DAO<Artista> {

    List<Artista> getArtistesForSerie(Serie serie);

}
