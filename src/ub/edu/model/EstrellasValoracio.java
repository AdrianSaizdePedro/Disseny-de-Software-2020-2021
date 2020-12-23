package ub.edu.model;

public class EstrellasValoracio extends ub.edu.model.Valoracio {
    // Atributos
    private int estrellas;

    /**
     * Metodo constructor de Valoracion de Estrellas
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie ID de la Serie
     * @param temporada numero de la Temporada
     * @param idEpisodi numero del Episodi
     * @param puntuacion numero de la puntuacion
     * @param data fecha de la valoracion
     */
    public EstrellasValoracio(int id, String idClient, String idUsuari, String idSerie, int temporada, int idEpisodi, int puntuacion, String data) {
        super(id, idClient, idUsuari, idSerie, temporada, idEpisodi, data);
        this.estrellas = puntuacion;
    }



    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Método para devolver el numero de estrellas de la Valoración
     * @return int estrellas
     * */
    public int getEstrellas() {
        return estrellas;
    }
    /**
     * Método para establecer numero de estrellas de la Valoración
     * @param estrellas estrellas de la Valoración
     * */
    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }



    //////////////////////////////////////
    /*  METODOS PARA IMPRIMIR Y UPDATE  */
    //////////////////////////////////////

    /**
     * Método para cambiar numero de estrellas de la Valoración
     * @param estrellas estrellas de la Valoración
     * @param data Data del cambio
     * */
    public void updateRating(int estrellas, String data) {
        this.estrellas = estrellas;
        this.data = data;
    }

    /**
     * Metodo para imprimir los atributos de una Valoracion con Estrellas
     * @return string con los atributos
     */
    public String toString(){ return "Cliente: " + this.getIdClient() + "\nUsuario: " + this.getIdUsuari() + "\nSerie: " + this.getIdSerie() + "\nTemporada: " + this.getIdTemporada() + "\nEpisodio: " + this.getIdEpisodi(); }

}
