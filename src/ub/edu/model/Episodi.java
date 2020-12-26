package ub.edu.model;

public class Episodi {
    // Atributos
    private String idSerie;
    private int idTemporada;
    private int numEpisodi;
    private String titol;
    private int duracio;
    private String idioma;
    private String descripcio;
    private String data;

    /**
     * Método contructor de un Episodio de una Temporada dentro de una Serie
     * @param idSerie ID de la Serie a la que pertenece
     * @param idTemporada ID de la Temporada a la que pertenece
     * @param numEpisodi numerop de Episosio que es
     * @param titol titulo del Episodio
     * @param duracio tiempo que dura
     * @param idioma idioma en el que esta el audio
     * @param descripcio sinopsis del Episodio
     * @param data fecha de lanzamiento
     */
    public Episodi( String idSerie, int idTemporada, int numEpisodi, String titol, String duracio, String idioma, String descripcio, String data) {
        this.idSerie = idSerie;
        this.idTemporada = idTemporada;
        this.numEpisodi = numEpisodi;
        this.titol = titol;
        this.duracio = calcularDuracio(duracio);
        this.idioma = idioma;
        this.descripcio = descripcio;
        this.data = data;
    }

    private int calcularDuracio(String duracio) {

        try{
            String[] tiempo = duracio.split(":");
            String horas = tiempo[0];
            String minutos = tiempo[1];
            String segundos = tiempo[2];
            int resultado = Integer.parseInt(horas)*3600 + Integer.parseInt(minutos)*60 + Integer.parseInt(segundos);
            return resultado;
        } catch (NumberFormatException excepcion) {
            return 0;
        }
    }


    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     *  Método para coger el Id de la Serie a la que pertenece el Episodio
     * @return nombre de la Serie
     */
    public String getIdSerie() {
        return idSerie;
    }

    /**
     * Método para establecer el Id de la Serie a la que pertenece el Episodio
     * @param idSerie nombre de la Serie
     */
    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    /**
     *  Método para coger el número de la Temporada a la que pertenece el Episodio
     * @return número de la Temporada
     */
    public int getIdTemporada() {
        return idTemporada;
    }

    /**
     * Método para establecer el número de la Temporada a la que pertenece el Episodio
     * @param idTemporada número de la Temporada a la que pertenece
     */
    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    /**
     *  Método para coger el número del Episodio
     * @return número del Episodio
     */
    public int getNumEpisodi() {
        return numEpisodi;
    }

    /**
     * Método para establecer el número del Episodio
     * @param numEpisodi número del Episodio dentro de una Temporada
     */
    public void setNumEpisodi(int numEpisodi) {
        this.numEpisodi = numEpisodi;
    }

    /**
     *  Método para coger el nombre de la Serie a la que pertenece el Episodio
     * @return nombre de la Serie
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Método para establecer el titulo de la Serie a la que pertenece el Episodio
     * @param titol nombre de la Serie
     */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     *  Método para coger la duración del Episodio
     * @return duración del episodio
     */
    public int getDuracio() {
        return duracio;
    }

    /**
     * Método para establecer la duración Episodio
     * @param duracio duración del Episodio
     */
    public void setDuracio(int duracio) {
        this.duracio = duracio;
    }

    /**
     *  Método para coger el idioma del Episodio
     * @return idioma del episodio
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Método para establecer el idioma del Episodio
     * @param idioma idioma del Episodio
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     *  Método para coger la descripción del Episodio
     * @return descripción del episodio
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Método para establecer la descripción del Episodio
     * @param descripcio descripción del Episodio
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     *  Método para coger la data del Episodio
     * @return data del episodio
     */
    public String getData() {
        return data;
    }

    /**
     * Método para establecer la data del Episodio
     * @param data data del Episodio
     */
    public void setData(String data) {
        this.data = data;
    }


}
