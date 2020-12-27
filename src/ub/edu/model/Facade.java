package ub.edu.model;

import ub.edu.controller.ControladorGUI;
import ub.edu.resources.service.DataService;
import ub.edu.view.Observer;

import java.util.List;

public class Facade{
    // Atributos
    private volatile static Facade uniqueInstance;

    private final DataService dataService;
    private final FacadeSeries facadeSeries;
    private final ub.edu.model.FacadeClients facadeClients;
    private final FacadeRegistre facadeRegistre;

    /**
     * Método contructor de Facades
     * Patrón Singleton
     * */
    private Facade (DataService dataService) {
        this.dataService = dataService;
        this.facadeSeries = FacadeSeries.getInstance();
        this.facadeClients = FacadeClients.getInstance();
        this.facadeRegistre = FacadeRegistre.getInstance();
    }

    public static Facade getInstance(DataService dataService) {
        if (uniqueInstance == null) {
            synchronized (Facade.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Facade(dataService);
                }
            }
        }
        return uniqueInstance;
    }

    //////////////////////////////////////////
    /*       METODOS PARA INICIALIZAR       */
    //////////////////////////////////////////

    public void init() throws Exception {
        facadeSeries.init(dataService.getAllSeries(), dataService.getAllTemporades(), dataService.getAllEpisodis());
        facadeClients.init(dataService.getAllClients(), dataService.getAllUsuaris(),dataService.getAllFollowers(), dataService.getAllFollowings());
        facadeRegistre.init(dataService.getAllPreferencias(), dataService.getAllCorValoracions(), dataService.getAllEstrellaValoracions(), dataService.getAllVisualitzacions());
    }


    //////////////////////////////////////////
    /*     METODOS HACIA FACADECLIENTS      */
    //////////////////////////////////////////

    //             SOBRE CLIENTES           //

    /**
     * Método para validar la contraseña de un Cliente
     * @param password contraseña del Cliente
     * @return True si se valida correctamente, False sino
     * */
    public boolean validatePassword(String password) { return facadeClients.validatePassword(password); }

    /**
     * Método para validar el DNI de un Cliente
     * @param dni DNI del Cliente
     * @return True si el DNI es correcto, False sino
     * */
    public boolean validarDNI(String dni) {
        return facadeClients.validarDNI(dni);
    }

    /**
     * Método para validad si el nombre del Cliente existe en la Cartera de Clientes
     * @param clientName nombre del Cliente
     * @return True si existe ya el cliente, False si no está cogido
     */
    public boolean isValidNameClient(String clientName) { return facadeClients.isValidNameClient(clientName); }


    /**
     * Método par añadir un Cliente nuevo a la Cartera de Clientes
     * @param nomClient nombre del Cliente
     * @param psw contraseña del Cliente
     * @param dni DNI del Cliente
     * @param adress dirección del Cliente
     * @param vip si es o no VIP el Cliente
     * @return valor del resultado de crearlo:
     *              - 0 : "Client created"
     *              - 1 : "Taken Username"
     *              - 2 : "Invalid DNI"
     *              - 3 : "Password not secure enough"
     * @throws Exception cualquier error en DataService ("Client not created")
     */
    public int addClient(String nomClient, String psw, String dni, String adress, boolean vip) throws Exception {
        if (isValidNameClient(nomClient)) return 1;
        if (!validarDNI(dni)) return 2;
        if (!validatePassword(psw)) return 3;

        facadeClients.addClient(nomClient, psw, dni, adress, vip);
        dataService.addClient(facadeClients.findClient(nomClient));
        return 0;
    }

    /**
     * Método para validar Cliente: nombre y contraseña.
     * @param username nombre del Cliente
     * @param password contraseña del Cliente
     * @return True si existe el Cliente y su contraseña coincide con la introducida.
     */
    public boolean validateLoginClient(String username, String password) { return dataService.getClientByUsuariAndPassword(username, password) != null; }



    //             SOBRE USUARIOS           //


    /**
     * Método para validar si el nombre de un usuario existe en un Cliente
     * @param nomClient nombre del Cliente
     * @param nomUsuari nombre del Usuario
     * @return True si existe el nombre de usuario, False sino
     */
    public boolean existsNameUser(String nomClient, String nomUsuari) throws Exception { return facadeClients.existNameUser(nomClient, nomUsuari); }

    /**
     * Método para comprobar si se puede añadir un nuevo Usuario a un Cliente
     * @param nomClient nombre del Cliente al que queremos añadir un nuevo Usuario
     * @return True si sí se puede añadir, False si no se puede
     */
    public boolean canAddUserToClient(String nomClient) throws Exception{ return facadeClients.canAddUserToClient(nomClient); }

    /**
     * Método para añadir un nuevo Usuario a un Cliente
     * @param nomClient nombre del Cliente al que añadiremos el Usuario
     * @param nomUsuari nombre del Usuario a añadir
     * @return valor del resultado de crearlo:
     *                 - 0 : "User created"
     *                 - 1 : "Client does not exist"
     *                 - 2 : "Too many Users"
     *                 - 3 : "User alredy created"
     * @throws Exception cualquier error en DataService
     */
    public int addUser(String nomClient, String nomUsuari) throws Exception {
        if (!isValidNameClient(nomClient)) return 1;
        if (!canAddUserToClient(nomClient)) return 2;
        if (existsNameUser(nomClient, nomUsuari)) return 3;

        facadeClients.addUser(nomClient, nomUsuari);
        dataService.addUser(facadeClients.findClient(nomClient).findUserByName(nomUsuari));
        return 0;
    }

    /**
     * Metodo para obtener la lista de Usuarios de un CLiente
     * @param nomClient id del Cliente
     * @return Iterable de nombres de Usuario
     */
    public Iterable<String> listUsuaris(String nomClient) {
        return facadeClients.listUsuaris(nomClient);
    }

    //        SOBRE FOLLOWERS/FOLLOWINGS       //

    /**
     * Método para hacer una lista de los seguidores de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followers
     * @return string con los nombres de los followers del Usuario
     * @throws Exception cuando no existe el Cliente y/o el Usuario
     */
    public Iterable<String> llistarFollowers(String nomClient, String nomUsuari) throws Exception { return facadeClients.listFollowers(nomClient, nomUsuari); }

    /**
     * Método para hacer una lista de los seguidos de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followings
     * @return string con los nombres de los Followings del Usuario
     * @throws Exception cuando no existe el Cliente y/o el Usuario
     */
    public Iterable<String> llistarFollowings(String nomClient, String nomUsuari) throws Exception { return facadeClients.listFollowings(nomClient, nomUsuari); }


    /**
     * Método para seguir a un Usuario
     * @param userFollowed Usuario al que se quiere seguir
     * @param userWhoFollows Usuario que quiere hacer follow
     * @return Entero que codifica un mensaje para el controlador:
     *          - 0: "L'usuari 'userWhoFollows' correctament afegit a la llista de Followers de 'userFollowed'."
     *          - 1: "L'usuari 'userWhoFollows' ja hi es a la llista de Followers de 'userFollowed'."
     * @throws  Exception fallo de DataService o si no existe alguno de los Usuarios
     */
    public int addFollowerToUser(String userFollowed, String userWhoFollows) throws Exception {
        if (facadeClients.ferFollow(userFollowed, userWhoFollows)) {
            dataService.addFollower(userFollowed, userWhoFollows);
            dataService.addFollowing(userWhoFollows, userFollowed);
            return 0;
        }
        return 1;
    }

    /**
     * Método para dejar de seguir a un Usuario
     * @param userUnfollower Usuario que desea dejar de seguir a otro Usuario
     * @param userUnfollowed Usuario dejado de seguir
     * @return Entero que codifica un mensaje para el controlador:
     *          - 0: "L'usuari 'userUnfollowed' correctament eliminat de la llista de Followers de 'userUnfollower'."
     *          - 1: "L'usuari 'userUnfollowed' no hi es a la llista de Followers de 'userUnfollower'."
     * @throws  Exception fallo de DataService o si no existe alguno de los Usuarios
     */
    public int removeFollowerFromUser(String userUnfollower, String userUnfollowed) throws Exception {
        if (facadeClients.ferUnfollow(userUnfollower, userUnfollowed)) {
            dataService.removeFollower(userUnfollower, userUnfollowed);
            dataService.removeFollowing(userUnfollowed, userUnfollower);
            return 0;
        }
        return 1;
    }



    //      SOBRE PERFIL        //

    /**
     * Método para modidifcar el nombre de un Usuario de un cliente en su Perfil
     * @param nomClient nombre del Cliente al que pertenece el Usuario
     * @param nomUsuariAntic nombre antiguo que deseamos cambiar
     * @param nomUsuariNou nombre nuevo que deseamos poner
     * @return valor del resultado de crearlo:
     *                 - 0 : "Se ha cambiado el nombre correctamente a: nomUsuariNou"
     *                 - 1 : "User alredy has this name"
     *                 - 2 : "El nombre del usuario nuevo no debe ser vacío"
     * @throws Exception cualquier fallo de DataService, el cliente no existe o el usuario inicial no existe
     */
    public int modificarPerfil(String nomClient, String nomUsuariAntic, String nomUsuariNou) throws Exception {
        if (existsNameUser(nomClient, nomUsuariNou)) return 1;
        if (nomUsuariNou.isEmpty()) return 2;
        facadeClients.modificarPerfil(nomClient, nomUsuariAntic, nomUsuariNou);
        return 0;
    }

    /**
     * Método para pedir ver los datos del Usuario
     * @param nomClient Nombre del Cliente
     * @param nomUsuari Nombre del Usuario
     * @return String de los datos del Usuario
     * @throws  Exception cuando no existe el Cliente y/o el Usuario
     * */
    public String veurePerfil(String nomClient, String nomUsuari) throws Exception{ return facadeClients.veurePerfil(nomClient, nomUsuari); }




    //        SOBRE SERIES        //

    /**
     * Método para conseguir la lista de Series del Catálogo del Series
     * @return listado de las series del Catálogo de Series
     * @throws Exception si no hay Series
     */
    public List<String> llistarCatalegSeries() throws Exception { return facadeSeries.llistarCatalegSeries(); }

    /**
     * Método para pedir el listado de temporadas de una serie
     * @param nomSerie Nombre de la Serie
     * @return catalogo de temporadas de una serie
     * */
    public List<String> getTemporades(String nomSerie){
        return facadeSeries.getTemporades(nomSerie);
    }

    /**
     * Método para pedir el listado de episodios de una temporada de una serie
     * @param nomSerie Nombre de la Serie
     * @param temporada numero de la temporada
     * @return listado de episodios
     * */
    public List<Episodi> getEpisodis(String nomSerie, int temporada) {
        return facadeSeries.getEpisodis(nomSerie,temporada);
    }

    /**
     * Método para listar las series de la lista Watching List de un Usuario.
     * @param idClient nombre del Cliente
     * @param nameUser nombre del Usuario
     * @return Iterable con los títulos de las series de Watching List, ordenados según su fecha de visualización.
     * @throws Exception si Cliente o Usuario no existen
     */
    public Iterable<String> listWatchingList(String idClient, String nameUser) throws Exception {
        if (!isValidNameClient(idClient)) throw new Exception("El client '" + idClient + "' no existeix.");
        if (!existsNameUser(idClient, nameUser)) throw new Exception("L'usuari '" + nameUser + "' del client '"+ idClient + "' no existeix.");
        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nameUser).getIdUser();
        return facadeRegistre.listWatchingList(idUser);
    }

    /**
     * Método para listar las series de la lista MyList de un Usuario.
     * @param idClient nombre Cliente
     * @param nameUser nombre Usuario
     * @return Iterable con los títulos de las series de MyList, ordenados según su fecha de adición.
     * @throws Exception si Cliente o Usuario no existen
     */
    public Iterable<String> listMyList(String idClient, String nameUser) throws Exception {
        if (!isValidNameClient(idClient)) throw new Exception("El client '" + idClient + "' no existeix.");
        if (!existsNameUser(idClient, nameUser)) throw new Exception("L'usuari '" + nameUser + "' del client '"+ idClient + "' no existeix.");
        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nameUser).getIdUser();
        return facadeRegistre.listMyList(idUser);
    }

    /**
     * Método para mostrar los detalles de uns Serie
     * @param nomSerie título de la Serie
     * @return string con el tirulo y la descripcion de la Serie
     * @throws Exception si no se dispone de la Serie
     */
    public String mostrarDetallsSerie(String nomSerie) throws Exception { return facadeSeries.mostrarDetallsSerie(nomSerie); }

    /**
     * Método para añadir una serie a la lista MyList de un Usuario.
     * @param idClient ID del Cliente
     * @param nameUser nombre del Usuario
     * @param titol título de la Serie
     * @return boolean conforme si se ha añadido o no se ha completado la operación.
     * @throws Exception fallo de DataService
     */
    public boolean addSerieToMyList(int id, String idClient, String nameUser, String titol) throws Exception {
        if (!isValidNameClient(idClient)) throw new Exception("El client '" + idClient + "' no existeix.");
        if (!existsNameUser(idClient, nameUser)) throw new Exception("L'usuari '" + nameUser + "' del client '"+ idClient + "' no existeix.");
        if (!existsSerieWithThisTitle(titol)) throw new Exception("La sèrie '" + titol + "' no existeix.");

        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nameUser).getIdUser();
        if (facadeRegistre.addSerieToMyList(id, idClient, idUser, titol)) {
            dataService.addPreferencia(new Preferencia(id, idClient, idUser, titol));
            return true;
        }
        return false;
    }


    /**
     * Método para eliminar una serie de la lista MyList de un Usuario.
     * @param idClient ID del Cliente
     * @param nameUser nombre Usuario
     * @param titol título de la Serie
     * @return boolean conforme si se ha eliminado, o no se ha completado la operación.
     * @throws Exception fallo de DataService
     */
    public boolean removeSerieFromMyList(int id, String idClient, String nameUser, String titol) throws Exception {
        if (!isValidNameClient(idClient)) throw new Exception("El client '" + idClient + "' no existeix.");
        if (!existsNameUser(idClient, nameUser)) throw new Exception("L'usuari '" + nameUser + "' del client '"+ idClient + "' no existeix.");
        if (!existsSerie(titol)) throw new Exception("La sèrie '" + titol + "' no existeix.");

        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nameUser).getIdUser();
        if (facadeRegistre.removeSerieFromMyList(id, idClient, idUser, titol)) {
            dataService.removePreferencia(new Preferencia(id, idClient, idUser, titol));
            return true;
        }
        return false;
    }


    /**
     * Metodo para saber si existe un Episodio de una Temporada y Serie concretas
     * @param idSerie ID de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodio
     * @return True si existe el Episodio, False si no
     * @throws Exception si no existe la Temporada y/o la Serie
     */
    private boolean existsEpisodi(String idSerie, int idTemporada, int idEpisodi) throws Exception { return facadeSeries.existsEpisodi(idSerie, idTemporada,  idEpisodi); }

    /**
     * Metodo para saber si existe una Serie
     * @param idSerie ID de la Serie
     * @return True si existe, False si no
     */
    private boolean existsSerie(String idSerie){
        return facadeSeries.existsSerie(idSerie);
    }

    /**
     * Metodo para saber si existe una Serie con este titulo
     * @param titolSerie titulo de la Serie
     * @return True si existe, False si no
     */
    private boolean existsSerieWithThisTitle(String titolSerie){
        return facadeSeries.existsSerieWithThisTitle(titolSerie);
    }

    //         SOBRE VISUALIZACIONES         //

    /**
     * Permite guardar la visualizacion de un episodio
     * @param id ID de la Visualizacion
     * @param idClient ID del Cliente
     * @param nomUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param numTemporada Numero de Temporada
     * @param idEpisodi ID del Episodio
     * @param data Dataa
     * @param segonsRestants Segundos Restantes
     */
    public void visualitzarEpisodi(int id, String idClient, String nomUser, String idSerie, int numTemporada, int idEpisodi, String data, int segonsRestants) throws Exception {
        String nomSerie = facadeSeries.getNomSerieByID(idSerie);
        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nomUser).getIdUser();
        facadeRegistre.visualitzarEpisodi(id, idClient, idUser, nomSerie, numTemporada, idEpisodi, data, segonsRestants);
    }

    /**
     * Metodo para saber el tiempo que ha visualizado un usuario de un episodio en concreto
     * @param idClient ID del Cliente
     * @param nomUsuari ID del Usuario
     * @param idSerie ID de la Serie
     * @param numTemporada Numero de Temporada
     * @param numEpisodi ID del Episodio
     * @return int de segundos visualizados
     */
    public int getDuracioVisualitzada(String idClient, String nomUsuari, String idSerie, int numTemporada, int numEpisodi) {
        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nomUsuari).getIdUser();
        int duracioEpisodi = dataService.getEpisodiByTitolTemporadaIdEpisodi(idSerie, numTemporada, numEpisodi).getDuracio();
        return facadeRegistre.getDuracioVisualitzada(idClient, idUser, idSerie, numTemporada, numEpisodi, duracioEpisodi);
    }
    /**
     * Metodo para saber si un Episodio ha sido o no Visualizado
     * @param idSerie id de la serie
     * @param numTemporada numero de la temporada
     * @param idEpisodi id del episodio
     * @param currentClient ID CLiente
     * @param currentUsuari nomClient
     * @return true si se ha visualizado, false si no...
     */
    public boolean isEpisodiVisualitzat(String idSerie, int numTemporada, int idEpisodi, String currentClient, String currentUsuari) throws Exception {
        String idUser = dataService.getUsuariByIdClientAndUsername(currentClient, currentUsuari).getIdUser();
        String nomSerie = facadeSeries.getNomSerieByID(idSerie);
        return facadeRegistre.isEpisodiVisualitzat(nomSerie, numTemporada, idEpisodi, idUser);
    }
    //         SOBRE VALORACIONES          //

    /**
     * Método para valorar una Serie del Catalogo con Estrellas
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param idSerie ID de la Serie
     * @param idTemporada Id de laTemporada
     * @param idEpisodi Id del Episodio
     * @param estrelles puntuacion en estrellas
     * @param data fecha de la valoracion del Episodio
     * @return valor del resultado de valorar:
     *            - 1 : "El Cliente no existe"
     *            - 2 : "El Usuario no Existe"
     *            - 3 : "El Episodio no existe"
     * @throws Exception fallo de DataService
     * */
    public int valorarEpisodiEstrellas(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, int estrelles, String data) throws Exception {
        if (!isValidNameClient(idClient)) return 1;
        if (!existsNameUser(idClient, nomUsuari)) return 2;
        if (!existsEpisodi(idSerie, idTemporada, idEpisodi)) return 3;
        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nomUsuari).getIdUser();
        return facadeRegistre.valorarEpisodiEstrellas(id, idClient, idUser, idSerie, idEpisodi, idTemporada, estrelles, data);
    }

    /**
     * Método para valorar una Serie del Catalogo con un Corazon
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param idSerie ID de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodio
     * @param data fecha de la valoracion del episodio
     * @return valor del resultado de valorar:
     *           - 1 : "El Cliente no existe"
     *           - 2 : "El Usuario no Existe"
     *           - 3 : "El Episodio no existe"
     * @throws Exception fallo de DataService
     * */
    public int valorarEpisodiCor(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, String data) throws Exception {
        if (!isValidNameClient(idClient)) return 1;
        if (!existsNameUser(idClient, nomUsuari)) return 2;
        if (!existsEpisodi(idSerie, idTemporada, idEpisodi)) return 3;
        String idUser = dataService.getUsuariByIdClientAndUsername(idClient, nomUsuari).getIdUser();
        return facadeRegistre.valorarEpisodiCor(id, idClient, idUser, idSerie, idEpisodi, idTemporada, data);
    }





    public void registerObserver(Observer observer) {
        facadeRegistre.registerObserver(observer);
    }



}

