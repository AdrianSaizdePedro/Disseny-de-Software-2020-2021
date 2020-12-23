package ub.edu.model;

public class Preferencia {
    // Atributos
    private int idPreferencia;
    private String idClient;
    private String nameUser;
    private String idSerie;

    /**
     * Metodo constructor de Preferencia
     * @param id ID de la Preferencia
     * @param client ID del Cliente
     * @param user nombre del Usuario
     * @param serie ID de la Serie
     */
    public Preferencia(int id, String client, String user, String serie) {
        this.idPreferencia = id;
        this.idClient = client;
        this.nameUser = user;
        this.idSerie = serie;
    }



    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Método para devolver la ID de la Preferencia
     * @return ID de Preferencia
     */
    public int getIdPreferir() { return idPreferencia; }

    /**
     * Metodo para establecer la ID de la Preferencia
     * @param idPreferir ID de la Preferencia
     */
    public void setIdPreferir(int idPreferir) { this.idPreferencia = idPreferir; }

    /**
     * Método para devolver la ID del Cliente
     * @return ID del Cliente
     */
    public String getIdClient() { return idClient; }

    /**
     * Metodo para establecer la ID del Cliente
     * @param idClient ID del Cliente
     */
    public void setIdClient(String idClient) { this.idClient = idClient; }

    /**
     * Metodo para devolver el nombre del Usuario
     * @return nombre del Usuario
     */
    public String getNameUser() { return nameUser; }

    /**
     * Metodo para establecer el nombre del Usuario
     * @param nameUser nombre del Usuario
     */
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }

    /**
     * Metodo para devolver el ID de la Serie
     * @return ID de la Serie
     */
    public String getIdSerie() { return idSerie; }

    /**
     * Metodo para establecer el ID de la Serie
     * @param idSerie ID de la Serie
     */
    public void setIdSerie(String idSerie) { this.idSerie = idSerie; }



    //////////////////////////////////////
    /*     METODOS SOBRE PREFERENCIA    */
    //////////////////////////////////////

    /**
     * Método equals para definir la igualdad entre dos instancias del tipo Preferencia.
     * @param obj Object
     * @return True si obj del tipo Preferencia y mismo identificador idPreferir.
     */
    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Preferencia) && this.idPreferencia == ((Preferencia)obj).idPreferencia);
    }

}
