package ub.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class CatalegSeries {
    // Atributos
    private final List<Serie> llistaSeries;

    /**
     * Método contructor de CatalegSeries
     * */
    public CatalegSeries() {
        this.llistaSeries = new ArrayList<>();
    }

    /**
     * Metodo para inicializar la lista de Series, cada una con sus Temporadas y Episodios respectivos
     * @param allSeries lista de todas las Series
     * @param allTemporades lista de todas las Temporadas
     * @param allEpisodis lista de todos los Episodios
     */
    public void init(List<Serie> allSeries, List<Temporada> allTemporades, List<Episodi> allEpisodis) {
        for (Serie s : allSeries) {
            List<Temporada> temporadas = new ArrayList<>();

            for (Temporada t: allTemporades) {
                List<Episodi> episodios = new ArrayList<>();

                if (t.getIdSerie().equals(s.getIdSerie())) {
                    for (Episodi e: allEpisodis) if (t.getIdTemporada() == e.getIdTemporada()) episodios.add(e);
                    t.setLlistaEpisodis(episodios);
                    temporadas.add(t);
                }
            }
            s.setTemporades(temporadas);
            llistaSeries.add(s);
        }
    }


    /**
     * Método para hacer una lista de todas las Series del Catalogo de Series
     * @return listado de las Series del Catalogo
     * @throws Exception si no hay Series
     * */
    public Iterable<String> llistarCatalegSeries() throws Exception {
        if (llistaSeries.isEmpty()) throw new Exception("Series not available");
        SortedSet<String> titols = new TreeSet<>();
        for (Serie s : llistaSeries) {
            titols.add(s.getTitol());
        }
        return titols;
    }

    /**
     * Método para mostrar los detalles de uns Serie
     * @param nomSerie título de la Serie
     * @return string con el tirulo y la descripcion de la Serie
     * @throws Exception si no se dispone de la Serie
     */
    public String mostrarDetallsSerie(String nomSerie) throws Exception {
        if (findByTitle(nomSerie) == null) throw new Exception("No se dispone de esta serie");
        return findByTitle(nomSerie).toString();
    }

    /**
     * Metodo para saber si existe un Episodio de una Temporada y Serie concretas
     * @param idSerie ID de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodio
     * @return True si existe el Episodio, False si no
     * @throws Exception si no existe la Temporada y/o la Serie
     */
    public boolean existsEpisodi(String idSerie, int idTemporada, int idEpisodi) throws Exception {
        if (!existsSerie(idSerie)) throw new Exception("No se dispone de esta serie");
        return find(idSerie).existsEpisodi(idTemporada,  idEpisodi);

    }

    /**
     * Metodo para saber si existe una Serie
     * @param idSerie ID de la Serie
     * @return True si existe, False si no
     */
    public boolean existsSerie(String idSerie) {
        return find(idSerie) != null;
    }

    /**
     * Metodo para saber si existe una Serie copn este titulo
     * @param titolSerie titulo de la Serie
     * @return True si existe, False si no
     */
    public boolean existsSerieWithThisTitle(String titolSerie) {
        return findByTitle(titolSerie) != null;
    }

    /**
     * Método para encontrar Series por su ID
     * @param idSerie ID de la Serie
     * @return Serie si la encuentra, null sino
     * */
    public Serie find(String idSerie) {
        for (Serie s: llistaSeries) {
            if (s.getIdSerie().equals(idSerie)) return s;
        }
        return null;
    }
    /**
     * Método para encontrar Series por su titulo
     * @param titolSerie titulo de la Serie
     * @return Serie si la encuentra, null sino
     * */
    public Serie findByTitle(String titolSerie) {
        for (Serie s: llistaSeries) {
            if (s.getTitol().equals(titolSerie)) return s;
        }
        return null;
    }

}
