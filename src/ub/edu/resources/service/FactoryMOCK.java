package ub.edu.resources.service;

import ub.edu.resources.dao.*;
import ub.edu.resources.dao.MOCK.*;

public class FactoryMOCK implements AbstractFactoryData {

    /**
     * Método para crear el DAO de Client
     * @return DAOClientMOCK creado
     * */
    @Override
    public DAOClient createDAOClient() {
        return new DAOClientMOCK();
    }

    /**
     * Método para crear el DAO de Serie
     * @return DAOSerieMOCK creado
     * */
    @Override
    public DAOSerie createDAOSerie() {
        return new DAOSerieMOCK();
    }

    /**
     * Método para crear el DAO de Usuari
     * @return DAOUsuariMOCK creado
     * */
    @Override
    public DAOUsuari createDAOUsuari() { return new DAOUsuariMOCK(); }

    /**
     * Método para crear el DAO de Temporada
     * @return DAOTemporadaMOCK creado
     * */
    @Override
    public DAOTemporada createDAOTemporada() { return new DAOTemporadaMOCK(); }

    /**
     * Método para crear el DAO de Episodi
     * @return DAOEpisodiMOCK creado
     */
    @Override
    public DAOEpisodi createDAOEpisodi() { return new DAOEpisodiMOCK(); }

    /**
     * Método para crear el DAO de Artista
     * @return DAOArtistaMOCK creado
     */
    @Override
    public DAOArtista createDAOArtista() { return new DAOArtistaMOCK(); }

    /**
     * Método para crear el DAO de Preferencia
     * @return DAOPreferenciaMOCK creado
     */
    @Override
    public DAOPreferencia createDAOPreferencia() {
        return new DAOPreferenciaMOCK();
    }

    /**
     * Método para crear el DAO de Visualitzacio
     * @return DAVisualitzacioMOCK creado
     */
    @Override
    public DAOVisualitzacio createDAOVisualitzacio() {
        return new DAOVisualitzacioMOCK();
    }
    @Override
    public DAOValoracioEstrellas createDAOValoracioEstrellas() { return new DAOValoracioEstrellaMOCK(); }

    @Override
    public DAOValoracioCor createDAOValoracioCor() { return new DAOValoracioCorMOCK(); }

    @Override
    public DAOFollowers createDAOFollowers(DAOUsuari dao){
        return new DAOFollowersMOCK(dao);
    }

    @Override
    public DAOFollowings createDAOFollowings(DAOUsuari dao){
        return new DAOFollowingsMOCK(dao);
    }

    // TO DO crear els altres DAOs de les altres classes

}
