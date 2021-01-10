package ub.edu.model;

import ub.edu.model.StrategyTOP.TOPValoracionsStrategy;
import ub.edu.model.StrategyTOP.TOPVisualitzacionsStrategy;
import ub.edu.model.Valoracions.CorValoracio;
import ub.edu.model.Valoracions.EstrellasValoracio;
import ub.edu.view.RegisterObserver;

import java.util.Map.Entry;
import java.util.*;

public class Registre implements RegisterSubject {
    // Atributos
    private final List<RegisterObserver> observers;
    private Map<String, ArrayList<Preferencia>> preferencias;
    private Map<String, ArrayList<Visualitzacio>> visualitzacions;
    private Map<String, ArrayList<CorValoracio>> corsValoracio;
    private Map<String, ArrayList<EstrellasValoracio>> estrellasValoracio;


    /**
     * Metodo constructor de Registre
     */
    public Registre() {
        this.observers = new ArrayList<>();
        this.preferencias = new HashMap<>();
        this.visualitzacions = new HashMap<>();
        this.corsValoracio = new HashMap<>();
        this.estrellasValoracio = new HashMap<>();
    }



    //////////////////////////////////////////
    /*       METODOS PARA INICIALIZAR       */
    //////////////////////////////////////////

    /**
     * Método pra inicializar las Preferencias, las Valoraciones y las Visualizaciones
     * @param allPreferencias lista de todas las Preferencias
     * @param corValoracions lista de todas las Valoraciones con Corazones
     * @param estrellasValoracions lista de todas las Valoraciones con Estrellas
     * @param allVisualitzacions lista de todas las Visualizaciones
     */
    public void init( Map<String, ArrayList<Preferencia>> allPreferencias, Map<String, ArrayList<CorValoracio>> corValoracions, Map<String, ArrayList<EstrellasValoracio>> estrellasValoracions, Map<String, ArrayList<Visualitzacio>> allVisualitzacions) {
        this.preferencias = allPreferencias;
        this.visualitzacions = allVisualitzacions;
        this.corsValoracio = corValoracions;
        this.estrellasValoracio = estrellasValoracions;
    }



    //////////////////////////////////////////
    /*  Métodos sobre My List (PREFERENCIA) */
    //////////////////////////////////////////

    /**
     * Metodo para Listar las Series preferidas de un Usuario
     * @param idUser ID del Usuario
     * @return lista de los titulos de las Series que prefiere un Usuario
     */
    public List<String> listPreferenciasById(String idUser) {
        List<String> titols = new ArrayList<>();
        if (!preferencias.containsKey(idUser)) return titols;
        for (Preferencia pref: preferencias.get(idUser)) titols.add(pref.getIdSerie());
        return titols;
    }

    /**
     * Metodo para añadir una Preferencia de Serie a un Usuario de un Cliente
     * @param id ID de la Preferencia
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     */
    public void addPreferencia(int id, String idClient, String idUser, String idSerie) {
        if (!preferencias.containsKey(idUser)) {
            preferencias.put(idUser, new ArrayList<>());
            preferencias.get(idUser).add(new Preferencia(id, idClient, idUser, idSerie));
        }
        else preferencias.get(idUser).add(new Preferencia(id, idClient, idUser, idSerie));
    }

    /**
     * Metodo para eliminar una Preferencia de Serie de un Usuario de un Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     */
    public void removePreferencia(String idUser, String idSerie) { preferencias.get(idUser).remove(findPreferencia(idUser, idSerie)); }

    /**
     * Metodo para encontrar la Preferencia de Serie de un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return Preferencia encontrada o null si no existe
     */
    public Preferencia findPreferencia(String idUser, String idSerie) {
        if (preferencias.containsKey(idUser)) {
            for (Preferencia pref: preferencias.get(idUser))
                if (pref.getIdSerie().equals(idSerie)) return pref;
        }
        return null;
    }



    ////////////////////////////////////////////////////////
    /*    Métodos sobre Watched/ContinueWatching List     */
    ////////////////////////////////////////////////////////

    /**
     * Metodo para listar todas las Series que ha visto un Usuario
     * @param idUser ID del Usuario
     * @return lista con los titulos de las Series
     */
    public List<Visualitzacio> listVisualitzacions(String idUser) {
        List<Visualitzacio> visual = new ArrayList<>();
        if (!this.visualitzacions.containsKey(idUser)) return visual;
        for (Visualitzacio v: visualitzacions.get(idUser)) visual.add(v);
        return visual;
    }


    //////////////////////////////////////
    /*    METODOS SOBRE VISUALITZACIO   */
    //////////////////////////////////////

    /**
     * Metodo para añadir una Visualizacion de una Serie a un Usuario
     * @param id ID de la Visualizacion
     * @param idClient Id del Cliente
     * @param idUser ID del Usuario
     * @param nomSerie Id de la Serie
     * @param numTemporada ID Temporada
     * @param idEpisodi ID Episodi
     * @param data Data
     * @param segonsRestants Segudnos restantes en int
     */
    public void addVisualitzacio(int id, String idClient, String idUser, String nomSerie, int numTemporada, int idEpisodi, String data, int segonsRestants){
        if (!visualitzacions.containsKey(idUser)) {
            visualitzacions.put(idUser, new ArrayList<>());
            visualitzacions.get(idUser).add(new Visualitzacio(id, idClient, idUser, nomSerie, numTemporada, idEpisodi, data, segonsRestants));
        }
        else visualitzacions.get(idUser).add(new Visualitzacio(id, idClient, idUser, nomSerie, numTemporada, idEpisodi, data, segonsRestants));
        notifyObservers();
    }

    /**
     * Metodo para modificar una Visualizacion de una Serie a un Usuario
     * @param idUser ID del Usuario
     * @param nomSerie Id de la Serie
     * @param numTemporada ID Temporada
     * @param idEpisodi ID Episodi
     * @param data Data
     * @param segonsRestants Segudnos restantes en int
     */
    public void updateVisualitzacio(String idUser, String nomSerie, int numTemporada, int idEpisodi, String data, int segonsRestants) {
        Visualitzacio visual = findVisualitzacio(idUser, nomSerie, numTemporada, idEpisodi);
        visual.updateVisualitzacio(data, segonsRestants);
        notifyObservers();
    }

    /**
     * Metodo para encontrar una Visualitzacio de una Serie de un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return Visualitzacio si la encuentra o null si no existe
     */
    public Visualitzacio findVisualitzacio(String idUser, String idSerie, int idTemporade, int idEpisodi) {
        if (visualitzacions.containsKey(idUser)) {
            for (ub.edu.model.Visualitzacio repr : visualitzacions.get(idUser)) {
                if (repr.getNomSerie().equals(idSerie)
                    && repr.getNumTemporada() == idTemporade
                    && repr.getIdEpisodi() == idEpisodi) return repr;
            }
        } return null;
    }

    /**
     * Métodp que reordena las visualizaciones en función de la serie, no del Usuario
     * @return HasMap con:
     *          - Key: nombre de la serie
     *          - Value: listado de visualizaciones de sus episodios.
     */
    private Set<Entry<String, ArrayList<Visualitzacio>>> getVisualitzacionsBySerie() {
        Map<String, ArrayList<Visualitzacio>> visualitzacionsForSerie = new HashMap<>();
        for (ArrayList<Visualitzacio> visualitzacions: visualitzacions.values()) {
            for (Visualitzacio repr: visualitzacions) {
                String idSerie = repr.getNomSerie();
                if (!visualitzacionsForSerie.containsKey(idSerie)) visualitzacionsForSerie.put(idSerie, new ArrayList<>());
                visualitzacionsForSerie.get(idSerie).add(repr);
            }
        }
        return visualitzacionsForSerie.entrySet();
    }


    ////////////////////////////////////////
    /*  Métodos sobre Valorar con Corazon */
    ////////////////////////////////////////

    /**
     * Metodo para encontrar una Valoracion con Corazon de un Episosio hecha por un Usuario
     * @param idUser ID del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Corazon encontrada o null si no existe
     */
    public CorValoracio findCorValoracio (String idUser, String nomSerie, int idTemp, int idEpisodi) {
        if (corsValoracio.containsKey(idUser)) {
            for (CorValoracio corVal: corsValoracio.get(idUser)) {
                if (corVal.getNomSerie().equals(nomSerie)
                    && corVal.getIdTemporada() == idTemp
                    && corVal.getIdEpisodi() == idEpisodi) return corVal;
            }
        } return null;
    }

        /**
         * Metodo para valorar con un Corazon un Episodio por pàrte de un Usuario
         * @param id ID de la Valoracion con Corazon
         * @param idClient ID del Cliente
         * @param idUser ID del Usuario
         * @param nomSerie Nombre de la Serie
         * @param idTemp ID de la Temporada
         * @param idEpisodi Id del Episodio
         * @param data fecha de la valoracion
         */
    public void addCorValoracio (int id, String idClient, String idUser, String nomSerie, int idTemp, int idEpisodi, String data) {
        if (!corsValoracio.containsKey(idUser)) {
            corsValoracio.put(idUser, new ArrayList<>());
            corsValoracio.get(idUser).add(new CorValoracio(id, idClient, idUser, nomSerie, idTemp, idEpisodi, data));
        }
        else corsValoracio.get(idUser).add(new CorValoracio(id, idClient, idUser, nomSerie, idTemp, idEpisodi, data));
    }

    /**
     * Metodo para eliminar una valoracion con Corazon un Episodio por pàrte de un Usuario
     * @param idUser ID del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     */
    public void removeCorValoracio (String idUser, String nomSerie, int idTemp, int idEpisodi){ corsValoracio.get(idUser).remove(findCorValoracio(idUser, nomSerie, idTemp, idEpisodi)); }



    ////////////////////////////////////////////
    /*    METODOS SOBRE ESTRELLAS VALORACIO   */
    ////////////////////////////////////////////

    /**
     * Metodo para encontrar una Valoracion con Estrellas de un Episosio hecha por un Usuario
     * @param idUser ID del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Estrellas encontrada o null si no existe
     */
    public EstrellasValoracio findEstrellasValoracio (String idUser, String nomSerie, int idTemp, int idEpisodi) {
        if (estrellasValoracio.containsKey(idUser)) {
            for (EstrellasValoracio starVal: estrellasValoracio.get(idUser)) {
                if (starVal.getNomSerie().equals(nomSerie)
                    && starVal.getIdTemporada() == idTemp
                    && starVal.getIdEpisodi() == idEpisodi) return starVal;
            }
        } return null;
    }

    /**
     * Metodo para valorar con Estrellas un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Estrellas
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void addEstrellaValoracio (int id, String idClient, String idUser, String nomSerie, int idTemp, int idEpisodi, int numEstrelles, String data) {
        if (!estrellasValoracio.containsKey(idUser)) {
            estrellasValoracio.put(idUser, new ArrayList<>());
            estrellasValoracio.get(idUser).add(new EstrellasValoracio(id, idClient, idUser, nomSerie, idTemp, idEpisodi, numEstrelles, data));
        }
        else estrellasValoracio.get(idUser).add(new EstrellasValoracio(id, idClient, idUser, nomSerie, idTemp, idEpisodi, numEstrelles, data));
        notifyObservers();
    }

    /**
     * Metodo para actualizar la Volaración de las Estrellas
     * * @param nomSerie Nombre de la Serie
     * @param nomUsuari ID del Usuario
     * @param nomSerie Nom de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param estrelles Numero de Estrellas
     * @param data fecha de la valoracion
     */
    public void updateEstrellaValoracio (String nomUsuari, String nomSerie, int idTemporada, int idEpisodi, int estrelles, String data) {
        findEstrellasValoracio(nomUsuari, nomSerie, idTemporada, idEpisodi).updateRating(estrelles, data);
        notifyObservers();
    }

    /**
     * Metodo para eliminar una valoracion con Estrellas un Episodio por pàrte de un Usuario
     * @param idUser ID del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     */
    public void removeEstrellaValoracio(String idUser, String nomSerie, int idTemp, int idEpisodi){
        estrellasValoracio.get(idUser).remove(findEstrellasValoracio(idUser, nomSerie, idTemp, idEpisodi));
        notifyObservers();
    }

    /**
     * Método que reordena las valoraciones en función de la serie, no del Usuario
     * @return HasMap con:
     *          - Key: nombre de la Serie y
     *          - Value: listado de valoraciones por estrellas
     *
     */
    private Set<Entry<String, ArrayList<EstrellasValoracio>>> getValoracionsBySerie() {
        Map<String, ArrayList<EstrellasValoracio>> valoracionsForSerie = new HashMap<>();
        for (ArrayList<EstrellasValoracio> valoracions: estrellasValoracio.values()) {
            for (EstrellasValoracio val: valoracions) {
                String idSerie = val.getNomSerie();
                if (!valoracionsForSerie.containsKey(idSerie)) valoracionsForSerie.put(idSerie, new ArrayList<>());
                valoracionsForSerie.get(idSerie).add(val);
            }
        }
        return valoracionsForSerie.entrySet();
    }

    ////////////////////////////////////////
    /*    METODOS SOBRE PATRON OBSERVER   */
    ////////////////////////////////////////

    /**
     * Método para registrar un Observador
     * @param observer Observador que se quiere subscribir
     */
    @Override
    public void registerObserver(RegisterObserver observer) {
        observers.add(observer);
        notifyObservers();
    }

    /**
     * Método para eliminar un Observador
     * @param observer Observador que se quiere desubscribir
     */
    @Override
    public void removeObserver(RegisterObserver observer) {
        observers.remove(observer);
    }

    /**
     * Método para notificar a un Observador de los cambios que se han  realñizado
     */
    @Override
    public void notifyObservers() {
        for (RegisterObserver observer : observers) {
            observer.refreshTopValoracions(new TOPValoracionsStrategy(getValoracionsBySerie()));
            observer.refreshTopVisualitzacions(new TOPVisualitzacionsStrategy(getVisualitzacionsBySerie()));
        }
    }

}
