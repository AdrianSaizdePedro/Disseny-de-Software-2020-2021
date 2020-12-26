package ub.edu.model.Valoracions;

import ub.edu.model.Valoracions.Valoracio;

public class CorValoracio extends Valoracio {
    /**
     * Metodo constructor de la Valoracion con Corazones
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie ID de la Serie
     * @param temporada numero de la Temporada
     * @param idEpisodi numero del Episodi
     * @param data fecha de la valoracion
     */
    public CorValoracio(int id, String idClient, String idUsuari, String idSerie, int temporada, int idEpisodi, String data) {
        super(id, idClient, idUsuari, idSerie, temporada, idEpisodi, data);
    }
}
