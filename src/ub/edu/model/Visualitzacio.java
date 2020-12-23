package ub.edu.model;

public class Visualitzacio {
    // Atributos
    private int idVisualitzacio;
    private String idClient;
    private String idUser;
    private String idSerie;

    /**
     * Metodo constructor de Visualitzacio
     * @param id ID de la Visualizacion
     * @param client Id del Cliente
     * @param user nombre del Usuario
     * @param serie ID de la Serie
     */
    public Visualitzacio(int id, String client, String user, String serie){
        this.idVisualitzacio = id;
        this.idClient = client;
        this.idUser = user;
        this.idSerie = serie;
    }



    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Metodo para devolver el ID de la Visualizacion
     * @return ID de la Visualizacion
     */
    public int getIdVisualitzacio() { return idVisualitzacio; }

    /**
     * Metodo para establecer el ID de la Visualizacion
     * @param idVisualitzacio ID de la Visualizacion
     */
    public void setIdVisualitzacio(int idVisualitzacio) { this.idVisualitzacio = idVisualitzacio; }

    /**
     * Metodo para devolver el ID del Cliente
     * @return ID del Cliente
     */
    public String getIdClient() { return idClient; }

    /**
     * Metodo para establecer el ID del Cliente
     * @param idClient el ID del Cliente
     */
    public void setIdClient(String idClient) { this.idClient = idClient; }

    /**
     * Metodo para devolver el ID del Usuario
     * @return ID del Usuario
     */
    public String getIdUser() { return idUser; }

    /**
     * Metodo para establecer el ID del Usuario
     * @param idUser ID del Usuario
     */
    public void setIdUser(String idUser) { this.idUser = idUser; }

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
    /*   METODOS SOBRE VISUALITZACIO    */
    //////////////////////////////////////

    /**
     * Método equals para definir la igualdad entre dos instancias del tipo Visualitzacio.
     * @param obj Object
     * @return True si obj del tipo Visualitzacio y mismo identificador idVisualitzacio.
     */
    @Override
    public boolean equals(Object obj) { return ((obj instanceof Visualitzacio) && this.idVisualitzacio == ((Visualitzacio)obj).idVisualitzacio); }
}
