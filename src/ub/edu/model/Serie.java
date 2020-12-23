package ub.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    // Atributos
    private String idSerie;
    private String titol;
    private String descripcio;
    private List<Temporada> temporades;
    private int idProductora;
    private List<Artista> artistas;

    /**
     * Método contructor de una Serie
     * @param titol titulo de la Serie
     * @param descripcio descripcion de la Serie
     * */
    public Serie(String idSerie, String titol, String descripcio, int idProductora) {
        this.idSerie = idSerie;
        this.titol = titol;
        this.descripcio = descripcio;
        this.temporades = new ArrayList<>();
        this.idProductora = idProductora;
        this.artistas = new ArrayList<>();
    }


    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Método para devolver el Id de una Serie
     * @return idSerie
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
     * Método para devolver el titulo de una Serie
     * @return titulo
     * */
    public String getTitol() {
        return titol;
    }

    /**
     * Método para establecer el titulo de una Serie
     * @param titol titulo de la Serie
     * */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     * Método para devolver la descripcion de una Serie
     * @return descripcion
     * */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Método para establecer la descripcion de una Serie
     * @param descripcio descripcion de la Serie
     * */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Método para devolver la lista de Temporadas de una Serie
     * @return lista de Temporadas
     * */
    public List<Temporada> getTemporades() { return temporades; }

    /**
     * Método para establecer la lista de Temporadas de una Serie
     * @param temporades lista de temporadas de la Serie
     * */
    public void setTemporades(List<Temporada> temporades) { this.temporades = temporades; }

    /**
     * Método para devolver el Id de una productora de una Serie
     * @return Id de la Productora de la Serie
     * */
    public int getIdProductora() {
        return idProductora;
    }

    /**
     * Método para establecer el Id de una productora de una Serie
     * @param idProductora Id de la productora de la Serie
     * */
    public void setIdProductora(int idProductora) {
        this.idProductora = idProductora;
    }

    /**
     * Método para devolver la lista de Artistas de una Serie
     * @return lista de Artistas
     * */
    public List<Artista> getArtistas() {
        return artistas;
    }

    /**
     * Método para establecer la lista de Artistas de una Serie
     * @param artistas lista de Artistas de la Serie
     * */
    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }



    //////////////////////////////////////
    /*     METODOS SOBRE TEMPORADAS     */
    //////////////////////////////////////

    /**
     * Metodo para encontrar una Temporada de una Serie
     * @param idTemporada ID de la Temporada
     * @return Temporada si la encuentra, sino null
     */
    public Temporada find(int idTemporada) {
        for (Temporada t: temporades) if (t.getIdTemporada() == idTemporada) return t;
        return null;
    }


    //////////////////////////////////////
    /*      METODOS SOBRE EPISODIOS     */
    //////////////////////////////////////

    /**
     * Metodo para saber si un Episodio existe o no en una Temporada concreta de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodio
     * @return True si esta dentro de la Temporada, False si no existe
     * @throws Exception si la Temporada no existe
     */
    public boolean existsEpisodi(int idTemporada, int idEpisodi) throws Exception {
        if (find(idTemporada) == null) throw new Exception("No se dispone de esta serie");
        return find(idTemporada).existsEpisodi(idEpisodi);
    }



    //////////////////////////////////////
    /*      METODOS PARA IMPRIMIR       */
    //////////////////////////////////////

    /**
     * Metodo para concatenar atributos de Series
     * @return string con el titulo y la descripcion de una Serie
     */
    public String toString(){ return "Titol: " + this.titol + "\nDescripcio: " + this.descripcio; }

}
