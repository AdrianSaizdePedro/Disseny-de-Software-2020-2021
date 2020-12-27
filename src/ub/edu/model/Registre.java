package ub.edu.model;

import ub.edu.model.StrategyTOP.TOPValoracionsStrategy;
import ub.edu.model.Valoracions.CorValoracio;
import ub.edu.model.Valoracions.EstrellasValoracio;
import ub.edu.model.Valoracions.ValoracioFactory;
import ub.edu.view.RegisterObserver;

import java.util.Map.Entry;
import java.util.*;

public class Registre implements RegisterSubject{
    // Atributos
    private final List<RegisterObserver> observers;

    private Map<String, ArrayList<Preferencia>> preferencias;
    private Map<String, ArrayList<Visualitzacio>> visualitzacions;
    private Map<String, ArrayList<CorValoracio>> corsValoracio;
    private Map<String, ArrayList<EstrellasValoracio>> estrellasValoracio;


    /**
     * Metodo constructor de Registre
     */
    public Registre(){
        this.observers = new ArrayList<>();
        ValoracioFactory valoracioFactory = new ValoracioFactory();
        this.preferencias = new HashMap<>();
        this.visualitzacions = new HashMap<>();
        this.corsValoracio = new HashMap<>();
        this.estrellasValoracio = new HashMap<>();
    }

    /**
     *
     * @param allPreferencias lista de todas las Preferencias
     * @param corValoracions lista de todas las Valoraciones con Corazones
     * @param estrellasValoracions lista de todas las Valoraciones con Estrellas
     * @param allVisualitzacions lista de todas las Visualizaciones
     */
    public void init( Map<String, ArrayList<Preferencia>> allPreferencias, Map<String, ArrayList<CorValoracio>> corValoracions, Map<String, ArrayList<EstrellasValoracio>> estrellasValoracions, Map<String, ArrayList<ub.edu.model.Visualitzacio>> allVisualitzacions) {
        this.preferencias = allPreferencias;
        this.visualitzacions = allVisualitzacions;
        this.corsValoracio = corValoracions;
        this.estrellasValoracio = estrellasValoracions;
    }



    //////////////////////////////////////
    /*     METODOS SOBRE PREFERENCIA    */
    //////////////////////////////////////

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
     * Metodo para encontrar la Preferencia de Serie de un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return Preferencia encontrada o null si no existe
     */
    public Preferencia findPreferencia(String idUser, String idSerie) {
        if (preferencias.containsKey(idUser)){
            for (Preferencia pref: preferencias.get(idUser))
                if (pref.getIdSerie().equals(idSerie)) return pref;
        } return null;
    }

    /**
     * Metodo para añadir una Preferencia de Serie a un Usuario de un Cliente
     * @param id ID de la Preferencia
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return True si se ha añadido correctamente, False si no se ha podido realizar
     */
    public boolean addPreferencia(int id, String idClient, String idUser, String idSerie) {
        if (!preferencias.containsKey(idUser)) {
            preferencias.put(idUser, new ArrayList<>());
            return preferencias.get(idUser).add(new Preferencia(id, idClient, idUser, idSerie));
        }
        if (findPreferencia(idUser, idSerie) == null) return preferencias.get(idUser).add(new Preferencia(id, idClient, idUser, idSerie));
        return false;
    }

    /**
     * Metodo para eliminar una Preferencia de Serie de un Usuario de un Cliente
     * @param id ID de la Preferencia
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return True si se ha elimando correctamente, False si no se ha podido realizar
     */
    public boolean removePreferencia(int id, String idClient, String idUser, String idSerie) {
        if (findPreferencia(idUser, idSerie) != null) {
            return preferencias.get(idUser).remove(findPreferencia(idUser, idSerie));
        } return false;
    }



    //////////////////////////////////////
    /*    METODOS SOBRE VISUALITZACIO   */
    //////////////////////////////////////

    /**
     * Metodo para listar todas las Series que ha visto un Usuario
     * @param idUser ID del Usuario
     * @return lista con los titulos de las Series
     */
    public List<String> listVisualitzacionsById(String idUser) {
        List<String> titols = new ArrayList<>();
        if (!visualitzacions.containsKey(idUser)) return titols;
        for (ub.edu.model.Visualitzacio repr: visualitzacions.get(idUser)) titols.add(repr.getNomSerie());
        return titols;
    }

    /**
     * Metodo para encontrar una Visualitzacio de una Serie de un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return Visualitzacio si la encuentra o null si no existe
     */
    public ub.edu.model.Visualitzacio findVisualitzacio(String idUser, String idSerie, int idTemporade, int idEpisodi) {
        if (visualitzacions.containsKey(idUser)) {
            for (ub.edu.model.Visualitzacio repr : visualitzacions.get(idUser)) {
                if (repr.getNomSerie().equals(idSerie) && repr.getNumTemporada() == idTemporade && repr.getIdEpisodi() == idEpisodi) return repr;
            }
        } return null;
    }

    /**
     * Metodo para añadir una Visualizacion de una Serie a un Usuario
     * @param id ID de la Visualizacion
     * @param idClient Id del Cliente
     * @param idUser ID del Usuario
     * @param nomSerie Id de la Serie
     */
    public void addVisualitzacio(int id, String idClient, String idUser, String nomSerie, int numTemporada, int idEpisodi, String data, int segonsRestants){
        Visualitzacio visualitzacio = findVisualitzacio(idUser, nomSerie, numTemporada, idEpisodi);
        if (visualitzacio == null){
            if (!visualitzacions.containsKey(idUser)) {
                visualitzacions.put(idUser, new ArrayList<>());
            }
            visualitzacions.get(idUser).add(new Visualitzacio(id, idClient, idUser, nomSerie, numTemporada, idEpisodi, data, segonsRestants));
        } else{
            visualitzacio.updateVisualitzacio(data, segonsRestants);
        }
    }

    /**
     * Metodo para eliminar una Visualizacion de una Serie de un Usuario
     * @param id ID de la Visualizacion
     * @param idClient Id del Cliente
     * @param idUser ID del Usuario
     * @param idSerie Id de la Serie
     * @return True si se ha podido eliminar correctamente, False si ha fallado la operacion
     */
    public boolean removeVisualitzacio(int id, String idClient, String idUser, String idSerie, String nomSerie, int numTemporada, int idEpisodi, String data, int segonsRestants){
        if (findVisualitzacio(idUser, idSerie, numTemporada, idEpisodi) != null) {
            return visualitzacions.get(idUser).remove(findVisualitzacio(idUser, idSerie, numTemporada, idEpisodi));
        } return false;
    }

    /**
     * Metodo para saber el tiempo que ha visualizado un usuario de un episodio en concreto
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param numTemporada Numero de Temporada
     * @param numEpisodi ID del Episodio
     * @return int de segundos visualizados
     */
    public int getDuracioVisualitzada(String idClient, String idUser, String idSerie, int numTemporada, int numEpisodi, int duracio) {
        int duracioRestant = 0;
        if(findVisualitzacio(idUser, idSerie, numTemporada, numEpisodi) != null) duracioRestant = findVisualitzacio(idUser, idSerie, numTemporada, numEpisodi).getSegonsRestants();
        return duracio-duracioRestant;
    }

    /**
     * Metodo para saber si un Episodio ha sido o no Visualizado
     * @param nomSerie id de la serie
     * @param numTemporada numero de la temporada
     * @param idEpisodi id del episodio
     * @param idUsuari idClient
     * @return true si se ha visualizado, false si no...
     */
    public boolean isEpisodiVisualitzat(String nomSerie, int numTemporada, int idEpisodi, String idUsuari) {
        Visualitzacio visualitzacio = findVisualitzacio(idUsuari, nomSerie, numTemporada, idEpisodi);
        if(visualitzacio != null){
            return visualitzacio.getEstat().equals("Watched") || visualitzacio.getSegonsRestants() == 0;
        }
        return false;
    }




    //////////////////////////////////////
    /*    METODOS SOBRE VALORACIO       */
    //////////////////////////////////////


    //////////////////////////////////////
    /*    METODOS SOBRE COR VALORACIO   */
    //////////////////////////////////////

    /**
     * Metodo para encontrar una Valoracion con Corazon de un Episosio hecha por un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Corazon encontrada o null si no existe
     */
    public CorValoracio findCorValoracio(String idUser, String idSerie, int idTemp, int idEpisodi){
        if (corsValoracio.containsKey(idUser)) {
            for (CorValoracio corVal: corsValoracio.get(idUser)) {
                if (corVal.getIdSerie().equals(idSerie) && corVal.getIdTemporada() == idTemp
                        && corVal.getIdEpisodi() == idEpisodi) return corVal;
            }
        } return null;
    }

    /**
     * Metodo para valorar con un Corazon un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Corazon
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void addCorValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, String data){
        if (!corsValoracio.containsKey(idUser)){
            corsValoracio.put(idUser, new ArrayList<>());
            corsValoracio.get(idUser).add(new CorValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, data));
        }
        if (findCorValoracio(idUser, idSerie, idTemp, idEpisodi) == null) corsValoracio.get(idUser).add(new CorValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, data));

    }

    /**
     * Metodo para eliminar una valoracion con Corazon un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Corazon
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void removeCorValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, String data){
        if (findCorValoracio(idUser, idSerie, idTemp, idEpisodi) != null) corsValoracio.get(idUser).remove(findCorValoracio(idUser, idSerie, idTemp, idEpisodi));
    }



    ////////////////////////////////////////////
    /*    METODOS SOBRE ESTRELLAS VALORACIO   */
    ////////////////////////////////////////////

    /**
     * Metodo para encontrar una Valoracion con Estrellas de un Episosio hecha por un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Estrellas encontrada o null si no existe
     */
    public EstrellasValoracio findEstrellasValoracio(String idUser, String idSerie, int idTemp, int idEpisodi){
        if (estrellasValoracio.containsKey(idUser)) {
            for (EstrellasValoracio starVal: estrellasValoracio.get(idUser)) {
                if (starVal.getIdSerie().equals(idSerie)
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
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void addEstrellaValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, int numEstrelles, String data){
        if (!estrellasValoracio.containsKey(idUser)) {
            estrellasValoracio.put(idUser, new ArrayList<>());
            estrellasValoracio.get(idUser).add(new EstrellasValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, numEstrelles, data));
        }
        if (findEstrellasValoracio(idUser, idSerie, idTemp, idEpisodi) == null) estrellasValoracio.get(idUser).add(new EstrellasValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, numEstrelles, data));

        notifyObservers();
    }

    /**
     * Metodo para eliminar una valoracion con Estrellas un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Estrellas
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void removeEstrellaValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, int numEstrelles, String data){
        if (findEstrellasValoracio(idUser, idSerie, idTemp, idEpisodi) != null) estrellasValoracio.get(idUser).remove(findEstrellasValoracio(idUser, idSerie, idTemp, idEpisodi));
        notifyObservers();
    }

    ////////////////////////////////////////
    /*    METODOS SOBRE PATRON OBSERVER   */
    ////////////////////////////////////////
    @Override
    public void registerObserver(RegisterObserver observer) {
        observers.add(observer);
        notifyObservers();
    }

    @Override
    public void removeObserver(RegisterObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (RegisterObserver observer : observers) {
            observer.refreshTopValoracions(new TOPValoracionsStrategy(getValoracionsBySerie()));
            //observer.refreshTopVisualitzacions(new TOPVisualitzacionsStrategy(getVisualitzacionsBySerie()));
        }
    }

    /**
     * Reordena las valoraciones en función de la serie, no del Usuario
     * @return Devuelve un nuevo HasMap con Key el nombre de la serie y Value el listado de valoraciones
     * por estrellas (enteros)
     * */
    private Set<Entry<String, ArrayList<EstrellasValoracio>>> getValoracionsBySerie(){
        Map<String, ArrayList<EstrellasValoracio>> valoracionsForSerie = new HashMap<>();
        for(ArrayList<EstrellasValoracio> valoracions: estrellasValoracio.values()){
            for (EstrellasValoracio val: valoracions){
                String idSerie = val.getIdSerie();
                if(!valoracionsForSerie.containsKey(idSerie))
                    valoracionsForSerie.put(idSerie, new ArrayList<>());
                valoracionsForSerie.get(idSerie).add(val);
            }
        }
        return valoracionsForSerie.entrySet();
    }

    /*
    private List<Map.Entry<String, Double>> getTopTenValoracions(){
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        for(ArrayList<EstrellasValoracio> listaEstrellasValoracions: estrellasValoracio.values()){
            for (EstrellasValoracio estrellasValoracio: listaEstrellasValoracions){
                String idSerie = estrellasValoracio.getIdSerie();
                int estrellas = estrellasValoracio.getEstrellas();
                if(map.containsKey(idSerie)) map.get(idSerie).add(estrellas);
                else{
                    map.put(idSerie, new ArrayList<>());
                    map.get(idSerie).add(estrellas);
                }
            }
        }
        Map<String, Double> map2 = new HashMap<>();

        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            map2.put(entry.getKey(), getMedia(entry.getValue()));
        }

        List<Map.Entry<String, Double>> listaOrdenada = new ArrayList<>(map2.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue());

        List<Map.Entry<String, Double>> listaDefinitiva = new ArrayList<>();
        for(Map.Entry<String, Double> entry: listaOrdenada){
            if(listaDefinitiva.size() < 10) listaDefinitiva.add(entry);
        }

        Collections.reverse(listaDefinitiva);

        return listaDefinitiva;
    }

    private double getMedia(ArrayList<Integer> notas){
        int sumaTotal = 0;
        int sizeLista = notas.size();
        for (Integer nota : notas) {
            sumaTotal += nota;
        }
        return ((double)sumaTotal)/sizeLista;
    }
    */
}
