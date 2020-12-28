package ub.edu.model;

import java.util.*;

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
                    for (Episodi e: allEpisodis)
                        if (t.getIdTemporada() == e.getIdTemporada() && Objects.equals(t.getIdSerie(), e.getIdSerie()))
                            episodios.add(e);
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
    public List<String> llistarCatalegSeries() throws Exception {
        if (llistaSeries.isEmpty()) throw new Exception("Series not available");
        List<String> titols = new ArrayList<>();
        for (Serie s : llistaSeries) {
            titols.add(s.getTitol());
        }
        Collections.sort(titols);
        return titols;
    }

    /**
     * Método para pedir el listado de temporadas de una serie
     * @param nomSerie Nombre de la Serie
     * @return catalogo de temporadas de una serie
     * */
    public List<String> getTemporades(String nomSerie){
        List<Temporada> temporadas = this.findByTitle(nomSerie).getTemporades();
        List<String> listaNomTemp = new ArrayList();
        for(Temporada t: temporadas)
            listaNomTemp.add("Temporada " + t.getIdTemporada());
        return listaNomTemp;
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
     * Método para mostrar los detalles de uns Serie
     * @param idSerie título de la Serie
     * @return string con el tirulo y la descripcion de la Serie
     * @throws Exception si no se dispone de la Serie
     */
    public String getNomSerieByID(String idSerie) throws Exception {
        if (!existsSerie(idSerie)) throw new Exception("No se dispone de esta serie");
        return find(idSerie).getTitol();
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


    public List<Episodi> getEpisodis(String nomSerie, int temporada) {
        List<Temporada> temporadas= this.findByTitle(nomSerie).getTemporades();
        List<Episodi> llistaEpisodis = new ArrayList<>();
        for(Temporada t: temporadas){
            if(temporada == t.getIdTemporada()) {
                llistaEpisodis.addAll(t.getLlistaEpisodis());
            }
        }
        return llistaEpisodis;
    }

    public int getTotalEpisodisBySerie(String nomSerie) {
        List<Temporada> temporadas= this.findByTitle(nomSerie).getTemporades();
        int totalEpisodis = 0;
        for(Temporada t: temporadas){
            totalEpisodis += t.getLlistaEpisodis().size();
        }
        return totalEpisodis;
    }

}
