package ub.edu.model;

import ub.edu.model.Valoracions.CorValoracio;
import ub.edu.model.Valoracions.EstrellasValoracio;
import ub.edu.view.RegisterObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacadeRegistre {
    // Atributos
    private volatile static FacadeRegistre uniqueInstance;
    private final Registre registre;

    /**
     * Metodo constructor de FacadeRegistre apliocando el Patrón Singleton
     */
    private FacadeRegistre(){ this.registre = new ub.edu.model.Registre(); }

    public static FacadeRegistre getInstance() {
        if (uniqueInstance == null) {
            synchronized (FacadeClients.class) {
                if (uniqueInstance == null) uniqueInstance = new FacadeRegistre();
            }
        }
        return uniqueInstance;
    }


    //////////////////////////////////////////
    /*       METODOS PARA INICIALIZAR       */
    //////////////////////////////////////////

    /**
     * Metodo para inicializar el Registre
     * @param allPreferencias lista de todas las Preferencias
     * @param corValoracions lista de todas las Valoraciones con Corazon
     * @param estrellasValoracions lista de todas las Valoraciones con Estrellas
     * @param allVisualitzacions lista de todas las Visualizaciones
     */
    public void init(Map<String, ArrayList<Preferencia>> allPreferencias, Map<String, ArrayList<CorValoracio>> corValoracions, Map<String, ArrayList<EstrellasValoracio>> estrellasValoracions, Map<String, ArrayList<ub.edu.model.Visualitzacio>> allVisualitzacions){ registre.init(allPreferencias, corValoracions, estrellasValoracions, allVisualitzacions); }



    //////////////////////////////////////////
    /*  Métodos sobre My List (PREFERENCIA) */
    //////////////////////////////////////////

    /**
     * Método para listar la lista MyList de un usuario de un cliente.
     * Reordena las listar de modo que las lista en orden de inserción.
     * @param idUser  ID Usuari
     * @return Iterable de títulos de las series de MyList del usuario.
     */
    public Iterable<String> listMyList(String idUser) throws Exception {
        List<String> myList = registre.listPreferenciasById(idUser);
        if (myList.isEmpty()) throw new Exception("No hi ha sèries preferides.");
        return myList;
    }

    /**
     * Método para añadir una serie en la lista MyList de un usuario de un cliente.
     * @param idClient nombre del Cliente
     * @param idUser nombre del Usuario
     * @param idSerie Título de la Serie a añadir a MyList.
     * @throws Exception Serie ya en la Lista
     */
    public void addSerieToMyList(int id, String idClient, String idUser, String idSerie) throws Exception {
        if (registre.findPreferencia(idUser, idSerie) != null) throw new Exception("Sèrie '" + idSerie + "' ja afegida a MyList.");
        registre.addPreferencia(id, idClient, idUser, idSerie);
    }

    /**
     * Método para quitar una serie a la lista MyList de un usuario de un cliente.
     * @param idUser nombre del Usuario
     * @param idSerie Título de la Serie a quitar de MyList.
     * @throws Exception Serie no está en la Lista
     */
    public void removeSerieFromMyList( String idUser, String idSerie) throws Exception {
        if (registre.findPreferencia(idUser, idSerie) == null) throw new Exception("Sèrie '" + idSerie + "' no consta en MyList.");
        registre.removePreferencia(idUser, idSerie);
    }



    ///////////////////////////////////////
    /*    Métodos sobre Watched List     */
    ///////////////////////////////////////

    /**
     * Método para listar la lista WatchedList de un usuario de un cliente.
     * @param idUser  ID Usuari
     * @return Iterable de títulos de las series de WatchedList del usuario.
     */
    public List<String> listWatchedList(String idUser) throws Exception {
        List<Visualitzacio> visualitzacions = registre.listVisualitzacions(idUser);
        List<String> titols = new ArrayList<>();
        for (Visualitzacio repr: visualitzacions) {
            if(repr.getEstat().equals("Watched")) titols.add(repr.getNomSerie());

        }
        return titols;
    }


    //////////////////////////////////////////
    /*  Métodos sobre ContinueWatching List */
    //////////////////////////////////////////

    /**
     * Método para listar la lista ContinueWatchingList de un usuario de un cliente.
     * @param idUser  ID Usuari
     * @return Iterable de títulos de las series de ContinueWatchingList del usuario.
     */
    public List<String> listContinueWatchingList(String idUser) throws Exception{
        List<Visualitzacio> visualitzacions = registre.listVisualitzacions(idUser);
        List<String> titols = new ArrayList<>();
        for (Visualitzacio repr: visualitzacions) {
            if(repr.getEstat().equals("Watching") && !titols.contains(repr.getNomSerie())) titols.add(repr.getNomSerie());
        }
        //if (titols.isEmpty()) throw new Exception("No hi ha cap sèrie començada.");
        return titols;
    }


    //////////////////////////////////////
    /*    METODOS SOBRE VISUALITZACIO   */
    //////////////////////////////////////

    /**
     * Método para añadir una serie a la lista WatchingList de un usuario de un cliente.
     * @param idClient nombre del Cliente
     * @param idUser nombre del Usuario
     * @return true si se ha añadido, false sino.
     */
    public int visualitzarEpisodi(int id, String idClient, String idUser, String nomSerie, int numTemporada, int idEpisodi, String data, int segonsRestants) {
        Visualitzacio visualitzacio = registre.findVisualitzacio(idUser, nomSerie, numTemporada, idEpisodi);
        if (visualitzacio == null) {
            registre.addVisualitzacio(id, idClient, idUser, nomSerie, numTemporada, idEpisodi, data, segonsRestants);
            return 0;
        }
        registre.updateVisualitzacio(idUser, nomSerie, numTemporada, idEpisodi, data, segonsRestants);
        return 1;
    }

    /**
     * Metodo para saber el tiempo que ha visualizado un usuario de un episodio en concreto
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param numTemporada Numero de Temporada
     * @param numEpisodi ID del Episodio
     * @return int de segundos visualizados
     */
    public int getDuracioVisualitzada(String idUser, String idSerie, int numTemporada, int numEpisodi, int duracio) {
        Visualitzacio visualitzacio = registre.findVisualitzacio(idUser, idSerie, numTemporada, numEpisodi);

        int duracioRestant = 0;
        if(visualitzacio != null) duracioRestant = visualitzacio.getSegonsRestants();
        return duracio - duracioRestant;
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
        Visualitzacio visualitzacio = registre.findVisualitzacio(idUsuari, nomSerie, numTemporada, idEpisodi);
        if (visualitzacio != null) return visualitzacio.getEstat().equals("Watched") || visualitzacio.getSegonsRestants() == 0;
        return false;
    }


    ////////////////////////////////////////
    /*  Métodos sobre Valorar con Corazon */
    ////////////////////////////////////////

    /**
     * Método para valorar una serie del catalogo con Estrellas
     * @param id ID de laValoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemporada Id de la Temporada
     * @param idEpisodi ID del Episodio
     * @param data fecha de la valoracion del Episodio
     * @return valor del resultado de valorar:
     *           - 4 : "Valoracion Eliminada"
     *           - 5 : "Valoracion añadida Correctamente"
     * */
    public int valorarEpisodiCor (int id, String idClient, String nomUsuari, String nomSerie, int idEpisodi, int idTemporada, String data) {
        CorValoracio val = registre.findCorValoracio(nomUsuari, nomSerie, idTemporada, idEpisodi);
        if (val != null) {
            registre.removeCorValoracio(nomUsuari, nomSerie, idTemporada, idEpisodi);
            return 1;
        }
        registre.addCorValoracio(id, idClient, nomUsuari, nomSerie, idTemporada, idEpisodi, data);
        return 0;
    }

    ////////////////////////////////////////////
    /*    METODOS SOBRE ESTRELLAS VALORACIO   */
    ////////////////////////////////////////////

    /**
     * Método para valorar una serie del catalogo con Estrellas
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param nomSerie Nombre de la Serie
     * @param idTemporada Id de la Temporada
     * @param idEpisodi ID del Episodio
     * @param estrelles puntuacion en estrellas
     * @param data fecha de la valoracion
     * @return valor del resultado de valorar:
     *           - 4 : "Debe introducir una valoración entre 0 y 5 estrellas"
     *           - 5 : "Modificación de la Valoración realizada Correctamente"
     *           - 6 : "Valoracion Eliminada"
     *           - 7 : "Valoracion añadida Correctamente"
     * */
    public int valorarEpisodiEstrellas (int id, String idClient, String nomUsuari, String nomSerie, int idTemporada, int idEpisodi, int estrelles, String data) {
        if (estrelles > 5 || estrelles < 0) return 1;

        EstrellasValoracio val = registre.findEstrellasValoracio(nomUsuari, nomSerie, idTemporada, idEpisodi);
        if (val != null) {
            if (val.getEstrellas() != estrelles) {
                registre.updateEstrellaValoracio(nomUsuari, nomSerie, idTemporada, idEpisodi, estrelles, data);
                return 2;
            }
            registre.removeEstrellaValoracio(nomUsuari, nomSerie, idTemporada, idEpisodi);
            return 3;
        }
        registre.addEstrellaValoracio(id, idClient, nomUsuari, nomSerie, idTemporada, idEpisodi, estrelles, data);
        return 0;
    }


    ////////////////////////////////////////
    /*    METODOS SOBRE PATRON OBSERVER   */
    ////////////////////////////////////////

    /**
     * Método para registrar un Observador
     * @param observer Observador que se quiere subscribir
     */
    public void registerObserver(RegisterObserver observer) { registre.registerObserver(observer); }



}
