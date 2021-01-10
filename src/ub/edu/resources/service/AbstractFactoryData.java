package ub.edu.resources.service;

import ub.edu.resources.dao.*;

public interface AbstractFactoryData {
     DAOClient createDAOClient();
     DAOSerie createDAOSerie();
     DAOUsuari createDAOUsuari();
     DAOTemporada createDAOTemporada();
     DAOEpisodi createDAOEpisodi();
     DAOArtista createDAOArtista();
     DAOPreferencia createDAOPreferencia();
     DAOVisualitzacio createDAOVisualitzacio();
     DAOValoracioEstrellas createDAOValoracioEstrellas();
     DAOValoracioCor createDAOValoracioCor();
     DAOFollowers createDAOFollowers(DAOUsuari dao);
     DAOFollowings createDAOFollowings(DAOUsuari dao);
}
