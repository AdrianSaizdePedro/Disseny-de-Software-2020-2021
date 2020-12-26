package ub.edu.model;

import ub.edu.model.Valoracions.CorValoracio;
import ub.edu.model.Valoracions.EstrellasValoracio;
import ub.edu.view.Observer;

import java.util.ArrayList;
import java.util.Map;

public class FacadeRegistre {
    // Atributos
    private volatile static FacadeRegistre uniqueInstance;

    private final ub.edu.model.Registre registre;

    /**
     * Metodo constructor de FacadeRegistre
     */
    private FacadeRegistre(){ this.registre = new ub.edu.model.Registre(); }

    public static FacadeRegistre getInstance() {
        if (uniqueInstance == null) {
            synchronized (FacadeClients.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new FacadeRegistre();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * Metodo para inicializar el Registre
     * @param allPreferencias lista de todas las Preferencias
     * @param corValoracions lista de todas las Valoraciones con Corazon
     * @param estrellasValoracions lista de todas las Valoraciones con Estrellas
     * @param allVisualitzacions lista de todas las Visualizaciones
     */
    public void init(Map<String, ArrayList<Preferencia>> allPreferencias, Map<String, ArrayList<CorValoracio>> corValoracions, Map<String, ArrayList<EstrellasValoracio>> estrellasValoracions, Map<String, ArrayList<ub.edu.model.Visualitzacio>> allVisualitzacions){
        registre.init(allPreferencias, corValoracions, estrellasValoracions, allVisualitzacions);
    }


    /**
     * Método para listar la lista WatchingList de un usuario de un cliente.
     * Reordena las listar de modo que las lista en orden de inserción.
     * @param idUser  ID Usuari
     * @return Iterable de títulos de las series de WatchingList del usuario.
     */
    public Iterable<String> listWatchingList(String idUser) throws Exception {
        if (registre.listVisualitzacionsById(idUser).isEmpty()) throw new Exception("No hi ha sèries visualitzades.");
        return registre.listVisualitzacionsById(idUser);
    }

    /**
     * Método para listar la lista MyList de un usuario de un cliente.
     * Reordena las listar de modo que las lista en orden de inserción.
     * @param idUser  ID Usuari
     * @return Iterable de títulos de las series de MyList del usuario.
     */
    public Iterable<String> listMyList(String idUser) throws Exception {
        if (registre.listPreferenciasById(idUser).isEmpty()) throw new Exception("No hi ha sèries preferides.");
        return registre.listPreferenciasById(idUser);
    }

    /**
     * Método para añadir una serie en la lista MyList de un usuario de un cliente.
     * @param idClient nombre del Cliente
     * @param nameUser nombre del Usuario
     * @param titol Título de la Serie a añadir a MyList.
     * @return true si se ha añadido, false sino.
     */
    public boolean addSerieToMyList(int id, String idClient, String nameUser, String titol) { return registre.addPreferencia(id, idClient, nameUser, titol); }

    /**
     * Método para quitar una serie a la lista MyList de un usuario de un cliente.
     * @param idClient nombre del Cliente
     * @param nameUser nombre del Usuario
     * @param titol Título de la Serie a quitar de MyList.
     * @return true si se ha eliminado, false sino.
     */
    public boolean removeSerieFromMyList(int id, String idClient, String nameUser, String titol) { return registre.removePreferencia(id, idClient, nameUser, titol); }

    /**
     * Método para añadir una serie a la lista WatchingList de un usuario de un cliente.
     * @param idClient nombre del Cliente
     * @param idUser nombre del Usuario
     * @return true si se ha añadido, false sino.
     */
    public boolean addSerieToWatchingList(int id, String idClient, String idUser, String title) { return registre.addVisualitzacio(id, idClient, idUser, title); }

    /**
     * Método para quitar una serie a la lista WatchingList de un usuario de un cliente.
     * @param idClient nombre del Cliente
     * @param nameUser nombre del Usuario
     * @return true si se ha eliminado, false sino.
     */
    public boolean removeSerieFromWatchingList(int id, String idClient, String nameUser, String title) { return registre.removeVisualitzacio(id, idClient, nameUser, title); }

    /**
     * Método para valorar una serie del catalogo con Estrellas
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param idSerie ID de la Serie
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
    public int valorarEpisodiEstrellas(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, int estrelles, String data) {
        if (estrelles > 5 || estrelles < 0) return 4;
        EstrellasValoracio val = registre.findEstrellasValoracio(nomUsuari, idSerie, idTemporada, idEpisodi);
        if (val != null) {
            if (val.getEstrellas() != estrelles) {
                val.updateRating(estrelles, data);
                registre.notifyObservers();
                return 5;
            }
            registre.removeEstrellaValoracio(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, estrelles, data);
            return 6;
        }
        registre.addEstrellaValoracio(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, estrelles, data);
        return 7;
    }

    /**
     * Método para valorar una serie del catalogo con Estrellas
     * @param id ID de laValoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param idSerie ID de la Serie
     * @param idTemporada Id de la Temporada
     * @param idEpisodi ID del Episodio
     * @param data fecha de la valoracion del Episodio
     * @return valor del resultado de valorar:
     *           - 4 : "Valoracion Eliminada"
     *           - 5 : "Valoracion añadida Correctamente"
     * */
    public int valorarEpisodiCor(int id, String idClient, String nomUsuari, String idSerie, int idEpisodi, int idTemporada, String data) {
        CorValoracio val = registre.findCorValoracio(nomUsuari, idSerie, idTemporada, idEpisodi);
        if (val != null) {
            registre.removeCorValoracio(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, data);
            return 4;
        }
        registre.addCorValoracio(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, data);
        return 5;
    }

    public void registerObserver(Observer observer) {
        registre.registerObserver(observer);
    }
}
