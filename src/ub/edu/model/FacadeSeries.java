package ub.edu.model;

import java.util.List;

public class FacadeSeries {
    // Atributos
    private final ub.edu.model.CatalegSeries catalegSeries;

    /**
     * Método constructor de FacadeSeries
     */
    public FacadeSeries() { this.catalegSeries = new ub.edu.model.CatalegSeries(); }

    /**
     * Metodo para inicializar el Catalogo de Series
     * @param allSeries lista de todas las Series
     * @param allTemporades lista de todas las Temporadas
     * @param allEpisodis lista de todos los Episodios
     */
    public void init(List<ub.edu.model.Serie> allSeries, List<ub.edu.model.Temporada> allTemporades, List<ub.edu.model.Episodi> allEpisodis){ catalegSeries.init(allSeries, allTemporades, allEpisodis); }

    /**
     * Método para conseguir la lista de Series del Catálogo del Series
     * @return listado de las series del Catálogo de Series
     * @throws Exception si no hay Series
     */
    public Iterable<String> llistarCatalegSeries() throws Exception { return catalegSeries.llistarCatalegSeries(); }

    /**
     * Método para mostrar los detalles de uns Serie
     * @param nomSerie título de la Serie
     * @return string con el tirulo y la descripcion de la Serie
     * @throws Exception si no se dispone de la Serie
     */
    public String mostrarDetallsSerie(String nomSerie) throws Exception { return catalegSeries.mostrarDetallsSerie(nomSerie); }

    /**
     * Metodo para saber si existe un Episodio de una Temporada y Serie concretas
     * @param idSerie ID de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodio
     * @return True si existe el Episodio, False si no
     * @throws Exception si no existe la Temporada y/o la Serie
     */
    public boolean existsEpisodi(String idSerie, int idTemporada, int idEpisodi) throws Exception { return catalegSeries.existsEpisodi(idSerie, idTemporada, idEpisodi); }

    /**
     * Metodo para saber si existe una Serie
     * @param titolSerie titol de la Serie
     * @return True si existe, False si no
     */
    public boolean existsSerieWithThisTitle(String titolSerie) {
        return catalegSeries.existsSerieWithThisTitle(titolSerie);
    }

    /**
     * Metodo para saber si existe una Serie
     * @param idSerie ID de la Serie
     * @return True si existe, False si no
     */
    public boolean existsSerie(String idSerie) {
        return catalegSeries.existsSerieWithThisTitle(idSerie);
    }


}


