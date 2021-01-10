package ub.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
    // Atributos
    private String idSerie;
    private int idTemporada;
    private final int numEpisodis;
    private List<Episodi> llistaEpisodis;

    /**
     * Método contructor de una Temporada de una Serie
     * @param idSerie ID de la Serie a la que pertenece
     * @param numTemporada numero de la Temporada
     * @param numEpisodis numero de Episodios que contiene
     */
    public Temporada( String idSerie, int numTemporada, int numEpisodis) {
        this.idSerie = idSerie;
        this.idTemporada = numTemporada;
        this.numEpisodis = numEpisodis;
        this.llistaEpisodis = new ArrayList<>();

    }



    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     *  Método para coger el nombre de la Serie a la que pertenece la Temporada
     * @return nombre de la Serie
     */
    public String getIdSerie() { return idSerie; }

    /**
     * Método para establecer el nombre de la Serie a la que pertenece la Temporada
     * @param nomSerie nombre de la Serie
     */
    public void setIdSerie(String nomSerie) { this.idSerie = nomSerie; }

    /**
     * Método para coger el número de la Temporada de una Serie
     * @return número de la Temporada
     */
    public int getIdTemporada() { return idTemporada; }

    /**
     * Método para establecer el número de la Temporada de una Serie
     * @param numSeason número de la Temporada
     */
    public void setIdTemporada(int numSeason) { this.idTemporada = numSeason; }


    /**
     * Metodo para devolver la Lista de Episodios de uan Temporada
     * @return Lista de Episodios
     */
    public List<Episodi> getLlistaEpisodis() {
        return llistaEpisodis;
    }
    /**
     * Metodo para establecer la Lista de Episodios de uan Temporada
     * @param llistaEpisodis Lista de Episodios
     */
    public void setLlistaEpisodis(List<Episodi> llistaEpisodis) { this.llistaEpisodis = llistaEpisodis; }



    //////////////////////////////////////
    /*     METODOS SOBRE EPISODIOS      */
    //////////////////////////////////////

    /**
     * Metodo para encontrar un Episodio dentro de la Temporada
     * @param idEpisodi ID del Episodio
     * @return Episodio encontra o null si no existe
     */
    public Episodi find(int idEpisodi) {
        for (Episodi episodi: llistaEpisodis) if (episodi.getNumEpisodi() == idEpisodi) return episodi;
        return null;
    }

    /**
     * Metodo para verificar si existe un Episodio concreto dentro de la Temporada
     * @param idEpisodi ID del Episodio
     * @return True si existe, False si no existe
     */
    public boolean existsEpisodi(int idEpisodi) { return find(idEpisodi) != null; }


}
