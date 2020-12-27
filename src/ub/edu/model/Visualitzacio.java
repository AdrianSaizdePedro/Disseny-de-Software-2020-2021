package ub.edu.model;

public class Visualitzacio {
    // Atributos
    private int idVisualitzacio;
    private String idClient;
    private String idUser;
    private String nomSerie;
    private int numTemporada;
    private int idEpisodi;
    private String estat;
    private String data;
    private int segonsRestants;



    /**
     * Metodo constructor de Visualitzacio
     * @param id ID de la Visualizacion
     * @param client Id del Cliente
     * @param user nombre del Usuario
     * @param nomSerie ID de la Serie
     */
    public Visualitzacio(int id, String client, String user, String nomSerie, int numTemporada,
                         int idEpisodi, String data, int segonsRestants){
        this.idVisualitzacio = id;
        this.idClient = client;
        this.idUser = user;
        this.nomSerie = nomSerie;
        this.numTemporada = numTemporada;
        this.idEpisodi = idEpisodi;
        if(segonsRestants == 0) this.estat = "Watched";
        else this.estat = "Watching";
        this.data = data;
        this.segonsRestants = segonsRestants;
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
    public String getNomSerie() { return nomSerie; }

    /**
     * Metodo para establecer el ID de la Serie
     * @param nomSerie ID de la Serie
     */
    public void setNomSerie(String nomSerie) { this.nomSerie = nomSerie; }

    /**
     * Metodo para devolver el id de la Temporada
     * @return Id de la Temporada
     */
    public int getNumTemporada() {
        return numTemporada;
    }

    /**
     * Metodo para establecer el ID de la Temporada
     * @param numTemporada ID de la Temporada
     */
    public void setNumTemporada(int numTemporada) {
        this.numTemporada = numTemporada;
    }

    /**
     * Metodo para devolver el ID del Episodio
     * @return ID del episodio
     */
    public int getIdEpisodi() {
        return idEpisodi;
    }

    /**
     * Metodo para establecer el ID del Episodio
     * @param idEpisodi ID del Episodio
     */
    public void setIdEpisodi(int idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    /**
     * Metodo para devolver el estado de la Visualitzacio
     * @return Estado de la Visualitzacio
     */
    public String getEstat() {
        return estat;
    }

    /**
     * Metodo para establecer el Estado de la Visualitzacio
     * @param estat Estado de la Visualitzacio
     */
    public void setEstat(String estat) {
        this.estat = estat;
    }

    /**
     * Metodo para devolver la data de la Visualitzacio
     * @return Data de la Visualitzacio
     */
    public String getData() {
        return data;
    }

    /**
     * Metodo para establecer la Data de la Visualitzacio
     * @param data Data de la Visualitzacio
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Metodo para devolver los segundos restantes de la Visualitzacio
     * @return Segundos restantes de la Visualitzacio
     */
    public int getSegonsRestants() {
        return segonsRestants;
    }

    /**
     * Metodo para establecer los Segundos Restantes de la Visualitzacio
     * @param segonsRestants Segundos Restantes de la Visualitzacio
     */
    public void setSegonsRestants(int segonsRestants) {
        this.segonsRestants = segonsRestants;
    }


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


    public void updateVisualitzacio(String data, int segonsRestants) {
        this.setData(data);
        this.setSegonsRestants(segonsRestants);
    }
}
