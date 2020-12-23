package ub.edu.model;

public class Valoracio {
    // Atributos
    private int idVal;
    private int idTemporada;
    private int idEpisodi;
    private String idClient;
    private String idUsuari;
    private String idSerie;
    protected String data;

    /**
     * Metodo constructor de Valoracion
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemporada numero de la Temporada
     * @param idEpisodi numero del Episodi
     * @param data fecha de la valoracion
     */
    public Valoracio(int id, String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, String data) {
        this.idVal = id;
        this.idClient = idClient;
        this.idUsuari = idUsuari;
        this.idSerie = idSerie;
        this.idTemporada = idTemporada;
        this.idEpisodi = idEpisodi;
        this.data = data;
    }



    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Método para devolver el Id de una Valoración
     * @return Id de la Valoración
     * */
    public int getIdVal() {
        return idVal;
    }

    /**
     * Método para establecer el Id de una Valoración
     * @param idVal Id de la Valoración
     * */
    public void setIdVal(int idVal) {
        this.idVal = idVal;
    }

    /**
     * Método para devolver el Id del Cliente de una Valoración
     * @return Id del Cliente
     * */
    public String getIdClient() {
        return idClient;
    }

    /**
     * Método para establecer el Id de un Cliente
     * @param idClient Id del Cliente
     * */
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    /**
     * Método para devolver el Id de un Usuario
     * @return Id del Usuario
     * */
    public String getIdUsuari() {
        return idUsuari;
    }

    /**
     * Método para establecer el Id de un Usuario
     * @param idUsuari Id del Usuario
     * */
    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }

    /**
     * Método para devolver el Id de una Serie
     * @return Id de la Serie
     * */
    public String getIdSerie() {
        return idSerie;
    }

    /**
     * Método para establecer el Id de una Serie
     * @param idSerie Id de la Serie
     * */
    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    /**
     * Método para devolver el Id de una Temporada
     * @return Id de una Temporada
     * */
    public int getIdTemporada() {
        return idTemporada;
    }

    /**
     * Método para establecer el Id de una Temporada
     * @param idTemporada Id de la Temporada
     * */
    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    /**
     * Método para devolver el Id del Episodio que Valoramos
     * @return Id del Episodio
     * */
    public int getIdEpisodi() {
        return idEpisodi;
    }

    /**
     * Método para establecer el Id del Episodio que se Valora
     * @param idEpisodi Id del Episodio
     * */
    public void setIdEpisodi(int idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    /**
     * Método para devolver la fecha de la valoracion
     * @return fecha de la valoracion
     * */
    public String getData() {
        return data;
    }

    /**
     * Método para establecer la fecha de la valoracion
     * @param data fecha de la valoracion
     * */
    public void setData(String data) {
        this.data = data;
    }



    //////////////////////////////////////
    /*       METODOS DE VALORACION      */
    //////////////////////////////////////

    /**
     * Metodo para saber si es la misma valoracion
     * @param v Valoracion
     * @return True si es la misma, False sino
     */
    public boolean isSameValoration(Valoracio v) { return idClient.equals(v.getIdClient()) && idUsuari.equals(v.getIdUsuari()) && idSerie.equals(v.getIdSerie())
            && idTemporada == v.getIdTemporada() && idEpisodi == v.getIdEpisodi(); }



}
