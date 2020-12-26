package ub.edu.resources.service;

import ub.edu.model.*;
import ub.edu.model.Valoracions.CorValoracio;
import ub.edu.model.Valoracions.EstrellasValoracio;
import ub.edu.resources.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DataService {
    // Atributos
    private volatile static DataService uniqueInstance;

    private final DAOSerie serieDAO;
    private final DAOClient clientDAO;
    private final DAOUsuari usuariDAO;
    private final DAOTemporada temporadaDAO;
    private final DAOEpisodi episodiDAO;
    private final DAOArtista artistaDAO;
    private final DAOPreferencia preferenciaDAO;
    private final DAOVisualitzacio visualitzacioDAO;
    private final DAOValoracioEstrellas valoracioEstrellasDAO;
    private final DAOValoracioCor valoracioCorDAO;
    private final DAOFollowers followersDAO;
    private final DAOFollowings followingsDAO;


    /**
     * Método contructor del DataService
     * @param factory de la AbstractFactoryData
     * */
    private DataService(AbstractFactoryData factory){
        this.serieDAO = factory.createDAOSerie();
        this.clientDAO = factory.createDAOClient();
        this.usuariDAO = factory.createDAOUsuari();
        this.temporadaDAO = factory.createDAOTemporada();
        this.episodiDAO = factory.createDAOEpisodi();
        this.artistaDAO = factory.createDAOArtista();
        this.preferenciaDAO = factory.createDAOPreferencia();
        this.visualitzacioDAO = factory.createDAOVisualitzacio();
        this.valoracioEstrellasDAO = factory.createDAOValoracioEstrellas();
        this.valoracioCorDAO = factory.createDAOValoracioCor();
        this.followersDAO = factory.createDAOFollowers(usuariDAO);
        this.followingsDAO = factory.createDAOFollowings(usuariDAO);
        // TO DO: Crear els altres DAO de les altres estructures

    }

    public static DataService getInstance(AbstractFactoryData factory) {
        if (uniqueInstance == null) {
            synchronized (DataService.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new DataService(factory);
                }
            }
        }
        return uniqueInstance;
    }

    //////////////////////////
    /*      DAO CLIENT      */
    //////////////////////////
    /**
     * Método para conseguir un Cliente a partir de un Usuario suyo y su contraseña
     * @param idClient del Cliente que buscamos
     * @return cliente o null
     * */
    public Client getClientByIdClient(String idClient) {
        try { return clientDAO.getById(idClient).get(); }
        catch (Exception e){ return null; }
    }
    /**
     * Método para conseguir un Cliente a partir de un Usuario suyo y su contraseña
     * @param usuari del Cliente que buscamos
     * @param password del Cliente que buscamos
     * @return cliente o null
     * */
    public Client getClientByUsuariAndPassword(String usuari, String password) {
        try { return clientDAO.findClientByUserNameAndPassword(usuari, password); }
        catch (Exception e){ return null; }

    }

    /**
     * Método para conseguir todos los Cliente
     * @return lista de todos los clientes
     * @throws Exception si no hay clientes
     * */
    public List<Client> getAllClients() throws Exception { return clientDAO.getAll(); }

    /**
     * Método para añadir un Cliente
     * @param client a añadir
     * @throws Exception si no se puede añadir
     * */
    public void addClient(Client client) throws Exception { clientDAO.add(client); }



    //////////////////////////
    /*      DAO USUARI      */
    //////////////////////////

    /**
     * Método para conseguir todos los Usuarios
     * @return lista de todos los usuarios
     * @throws Exception si no hay usuarios
     * */
    public List<Usuari> getAllUsuaris() throws Exception { return usuariDAO.getAll(); }

    /**
     * Método para añadir un Usuario
     * @param usuari a añadir
     * @throws Exception si no se puede añadir
     * */
    public void addUser(Usuari usuari) throws Exception { usuariDAO.add(usuari); }

    /**
     * Método para conseguir los Usuarios de un Cliente
     * @param nomClient del Cliente que usamos para buscar
     * @return lista de todos los Usuarios del Cliente
     * @throws Exception si el cliente no tiene usuarios
     * */
    public List<Usuari> getUsuarisByClient(String nomClient) throws Exception {
        Optional<Client> c = clientDAO.getById(nomClient);
        return c.map(usuariDAO::getUsuarisForClient).orElse(null);
    }

    /**
     * Método para conseguir un Usuario a partir de su Id
     * @param idUsuari del Usuario que buscamos
     * @return usuari o null
     * */
    public Usuari getUsuariById(String idUsuari) {
        try { return usuariDAO.getById(idUsuari).get();
        } catch (Exception e) { return null; }
    }

    /**
     * Método para conseguir un Usuario a partir del nombre del Cliente y del Usuario.
     * @param nomClient nombre del Cliente
     * @param nom nombre del Usuario
     * @return usuari o null
     */
    public Usuari getUsuariByIdClientAndUsername(String nomClient, String nom){
        try {
            List<Usuari> listUsuari = getUsuarisByClient(nomClient);
            for (Usuari u : listUsuari) if (u.getName().equals(nom)) return u;
            return null;
        } catch (Exception e) { return null; }
    }

    /**
     * Método para cambiar el nombre del Usuario seleccionado
     * @param nomClient Nombre del Cliente
     * @param nomUsuariAntic Nombre del Usuario Antiguo
     * @param nomUsuariNou Nombre del Usuario Nuevo
     * @return String de confirmación del cambio
     * */
    public boolean modificarPerfil(String nomClient, String nomUsuariAntic, String nomUsuariNou) throws Exception {
        Usuari usuario = getUsuariByIdClientAndUsername(nomClient, nomUsuariAntic);
        return usuariDAO.update(usuario, nomUsuariNou.split(","));
    }



    //////////////////////////
    /*      DAO SERIE       */
    //////////////////////////

    /**
     * Método para conseguir todas las Series
     * @return lista de todos las Series
     * @throws Exception si no hay series
     * */
    public List<Serie> getAllSeries() throws Exception { return serieDAO.getAll(); }

    /**
     * Método para conseguir una Serie a partir de su título.
     * @param titol id de la Serie a obtener.
     * @return serie o null
     * */
    public Serie getSerieByTitol(String titol) {
        try { return serieDAO.getById(titol).get(); }
        catch (Exception e){ return null; }
    }


    //////////////////////////
    /*     DAO TEMPORADA    */
    //////////////////////////

    public List<Temporada> getAllTemporades(String idSerie) throws Exception {
        Optional<Serie> s = serieDAO.getById(idSerie);
        return s.map(temporadaDAO::getTemporadesForSerie).orElse(null);
    }

    /**
     * Metodo para devolver una lista de todas las Temporadas
     * @return lista de todas las Temporadas
     * @throws Exception cuando no hay Temporadas
     */
    public List<Temporada> getAllTemporades() throws Exception { return temporadaDAO.getAll(); }



    //////////////////////////
    /*      DAO EPISODI     */
    //////////////////////////

    // NO SE PARA QUE ES EL ID EPISODI :(
    public List<Episodi> getAllEpisodis(String idSerie, String idEpisodi) throws Exception {
        Optional<Temporada> t = temporadaDAO.getById(idSerie);
        return t.map(episodiDAO::getEpisodisForTemporada).orElse(null);
    }

    /**
     * Método para conseguir unEpisodio a partir del nombre de la serie, la temporada y el num episodio.
     * @param idSerie id de la Serie a obtener.
     * @param idTemporada id de la Temporada a obtener.
     * @param idEpisodi id del Episodio a obtener.
     * @return episodio o null
     * */
    public Episodi getEpisodiByTitolTemporadaIdEpisodi(String idSerie, int idTemporada, int idEpisodi) {
        try { return episodiDAO.getEpisodiBySerieTemporadaEpisodi(idSerie, idTemporada, idEpisodi); }
        catch (Exception e){ return null; }
    }

    /**
     * Metodo para devolver una lista de todos los Episodios
     * @return lista de todos los Episodios
     * @throws Exception cuando no hay Episodios
     */
    public List<Episodi> getAllEpisodis() throws Exception { return episodiDAO.getAll(); }



    //////////////////////////
    /*     DAO ARTISTA    */
    //////////////////////////
    public List<Artista> getAllArtistes( String idSerie) throws Exception {
        Optional<Serie> s = serieDAO.getById(idSerie);
        return s.map(artistaDAO::getArtistesForSerie).orElse(null);
    }


    //////////////////////////
    /*   DAO PREFERENCIA    */
    //////////////////////////

    /**
     * Método para añadir una Preferencia en base de datos.
     * @param pref Preferencia a añadir.
     */
    public void addPreferencia(Preferencia pref) throws Exception { preferenciaDAO.add(pref); }

    /**
     * Método para obtener todas las preferencias en base de datos
     * @return lista de todas las preferencias
     */
    public Map<String, ArrayList<Preferencia>> getAllPreferencias() { return preferenciaDAO.getMap(); }

    /**
     * Método para eliminar una preferencia en base de datos
     * @param pref preferencia a eliminar
     */
    public void removePreferencia(Preferencia pref) throws Exception{ preferenciaDAO.delete(pref); }

    /**
     * Método para obtener todas las asociaciones del tipo Preferencia de un Usuario determinado.
     * @param idUser id usuario
     * @return listado de objetos del tipo Preferencia
     */
    public List<Preferencia> getPreferredSeriesByIdUser(String idUser) { return preferenciaDAO.getPreferredSeriesByIdUser(idUser); }

    /**
     * Método para obtener una asociación del tipo Preferencia a partir de un Usuario y una Serie determinada.
     * @param idUser ID Usuario
     * @param titol título Serie
     * @return Preferencia si la encuentra, null sino.
     */
    public Preferencia getPreferenciaSerieByIdUserAndIdSerie(String idUser, String titol) {
        List<Preferencia> l = preferenciaDAO.getPreferredSeriesByIdUser(idUser);
        if (l != null) for(Preferencia pref: l) if (pref.getIdSerie().equals(titol)) return pref;
        return null;
    }


    //////////////////////////
    /*  DAO VISUALITZACIO   */
    //////////////////////////

    /**
     * Método para obtener todas las asociaciones del tipo Visualitzacio de un Usuario determinado.
     * @param idUser id usuario
     * @return listado de objetos del tipo Visualitzacio
     */
    public List<Visualitzacio> getVisualizedSeriesByIdUser(String idUser) { return visualitzacioDAO.getVisualizedSeriesByIdUser(idUser); }

    /**
     * Método para obtener una asociación del tipo Visualitzacio a partir de un Usuario y una Serie determinada.
     * @param idUser id usuario
     * @return listado de objetos del tipo Visualitzacio
     */
    public Visualitzacio getVisualitzacioSerieByIdUserAndSerie(String idUser, String titol) {
        List<Visualitzacio> l = visualitzacioDAO.getVisualizedSeriesByIdUser(idUser);
        if (l != null) for(Visualitzacio visual: l) if (visual.getIdSerie().equals(titol)) return visual;
        return null;
    }

    /**
     * Metodo para devolver el mapeo del DAO de Visualitzacio
     * @return mapeo del DAO de Visualitzacio
     */
    public Map<String, ArrayList<Visualitzacio>> getAllVisualitzacions() { return visualitzacioDAO.getMap(); }



    //////////////////////////
    /*     DAO VALORACIO    */
    //////////////////////////

     /**
     * Método para conseguir los Usuarios de un Cliente
     * @param idUsuari idUsuario que usamos para buscar la Lista de Valoraciones de Estrella
     * @return lista de todos las Valoraciones de tipo estrella del Usuario
     * */
    public List<EstrellasValoracio> getListValEstrella(String idUsuari)  { return valoracioEstrellasDAO.getListEstrellasByIdUser(idUsuari); }

    /**
     * Metodo para devolver una Valoracion con Estrellas
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie Id de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Estrellas o null si no existe
     */
    public EstrellasValoracio getValoracioEstrella (String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi){
        try { return valoracioEstrellasDAO.getByCliUsuSerTempEpi(idClient, idUsuari, idSerie, idTemporada, idEpisodi); }
        catch (Exception e) { return null; }
    }

    /**
     * Metodo para devolver todas las Valoraciones con Corazon de un Usuario
     * @param idUsuari Id del Usuario
     * @return lista de Valoraciones con Corazon
     */
    public List<CorValoracio> getListValCor(String idUsuari) { return valoracioCorDAO.getListCorByIdUser(idUsuari); }

    /**
     * Metodo para devolver una Valoracion con Corazones
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie Id de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Corazones o null si no existe
     */
    public CorValoracio getValoracioCor (String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi) {
        try{ return valoracioCorDAO.getByCliUsuSerTempEpi(idClient, idUsuari, idSerie, idTemporada, idEpisodi); }
        catch (Exception e){ return null; }
    }

    /**
     * Metodo para establecer una Valoracion con Estrellas
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie Id de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi Id del Episodio
     */
    public void setValoracioEstrella( String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, EstrellasValoracio v, String data) throws Exception {
        valoracioEstrellasDAO.setValoracioEstrella(idClient, idUsuari, idSerie, idTemporada, idEpisodi, v, data);
    }

    /**
     * Metodo para establecer una Valoracion con Corazones
     * @param idClient ID del Cliente
     * @param idUsuari ID del Usuario
     * @param idSerie Id de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi Id del Episodio
     */
    public void setValoracioCor (String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, CorValoracio v, String data) throws Exception {
        valoracioCorDAO.setValoracioCor(idClient, idUsuari, idSerie, idTemporada, idEpisodi, v, data);
    }

    /**
     * Metodo para devolver el mapeo del DAO de Valoracion con Corazones
     * @return mapeo del DAO Valoracion con Corazones
     */
    public Map<String, ArrayList<CorValoracio>> getAllCorValoracions() { return valoracioCorDAO.getMap(); }

    /**
     * Metodo para devolver el mapeo del DAO de Valoracion con Estrellas
     * @return mapeo del DAO Valoracion con Estrellas
     */
    public Map<String, ArrayList<EstrellasValoracio>> getAllEstrellaValoracions() { return valoracioEstrellasDAO.getMap(); }



    //////////////////////////
    /*     DAO FOLLOWERS    */
    //////////////////////////

    /**
     * Metodo para devolver la lista de Followers de un Usuario
     * @param idUser ID del Usuario
     * @return lista de Followers
     */
    public List<Usuari> getFollowersByIdUser(String idUser) { return followersDAO.getFollowersByIdUser(idUser); }

    /**
     * Metodo para añadir un Follower a un Usuario
     * @param userFollowed nombre del Usuario seguido
     * @param userWhoFollows nombre del Usuario que sigue
     */
    public void addFollower(String userFollowed, String userWhoFollows) { followersDAO.addFollower(userWhoFollows, getUsuariById(userFollowed)); }

    /**
     * Metodo para eliminar un Follower a un Usuario
     * @param userUnfollowed nombre del Usuario al que deja de seguir
     * @param userUnfollower nombre del Usuario que quiere dejar de siguir
     */
    public void removeFollower(String userUnfollowed, String userUnfollower) { followersDAO.removeFollower(userUnfollowed, getUsuariById(userUnfollower)); }

    /**
     * Metodo para devolver el mapeo del DAO de Followers
     * @return mapeo del DAO de Followers
     */
    public Map<String, ArrayList<Usuari>> getAllFollowers(){ return followersDAO.getMap(); }



    //////////////////////////
    /*    DAO FOLLOWINGS    */
    //////////////////////////

    /**
     * Metodo para devolver la lista de Followings de un Usuario
     * @param idUser ID del Usuario
     * @return lista de Followings
     */
    public List<Usuari> getFollowingsByIdUser(String idUser){ return followingsDAO.getFollowingsByIdUser(idUser); }

    /**
     * Metodo para añadir un Following a un Usuario
     * @param userFollowed nombre del Usuario seguido
     * @param userWhoFollows nombre del Usuario que sigue
     */
    public void addFollowing(String userWhoFollows, String userFollowed) { followingsDAO.addFollowing(userWhoFollows, getUsuariById(userFollowed)); }

    /**
     * Metodo para eliminar un Following a un Usuario
     * @param userUnfollowed nombre del Usuario al que deja de seguir
     * @param userUnfollower nombre del Usuario que quiere dejar de siguir
     */
    public void removeFollowing(String userUnfollower, String userUnfollowed) { followingsDAO.removeFollowing(userUnfollower, getUsuariById(userUnfollowed)); }

    /**
     * Metodo para devolver el mapeo del DAO de Followings
     * @return mapeo del DAO de Followings
     */
    public Map<String, ArrayList<Usuari>> getAllFollowings() { return followingsDAO.getMap(); }







        /*  TO DO
    CAPÇALERES de les FUNCIONS que cal implementar en la pràctica 2 com a DataServices que consultaran dels DAOs corresponents
    les dades que es volen extreure

    ¡¡¡¡LAS FUNCIONES HECHAS PONEDLAS ABAJO EN DONE!!!!


    public List<Productora> getAllProductores (String idSerie)

    public List<Tematica> getAllTematiques (String idSerie)

    public List<Director> getAllDirectors (String idSerie)

    public void startWatching (String idClient, String idUsuari,  String idSerie, String idTemporada, String idEpisodi, Date data)

    public void pauseWatching (String idClient, String idUsuari,  String idSerie, String idTemporada, String idEpisodi, String numMinutes)

    public void finishWatching(String idClient, String idUsuari,  String idSerie, String idTemporada, String idEpisodi, Date data)

*/
    /* DONE
    public List<Serie> getAllSeries()

    public List<Temporada> getAllTemporades(String idSerie)

    public List<Episodi> getAllEpisodis(String idSerie, String idEpisodi)

    public List<Artista> getAllArtistes( String idSerie)

    public ValoracioEstrella getValoracioEstrella (String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi)

    public ValoracioThumb getValoracioThumb (String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi)

    public void setValoracioEstrella( String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi,
                                    ValoracioEstrella v, Date data)

    public void setValoracioCor (String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi,
                                    ValoracioThumb v, Date data);

    public boolean isFromMyList (String idClient, String idUsuari, String idSerie)

    public void markInMyList (String idClient, String idUsuari, String idSerie)

    * */

}
