package ub.edu.controller;

import ub.edu.model.*;
import ub.edu.resources.service.DataService;
import ub.edu.resources.service.FactoryMOCK;
import ub.edu.view.RegisterObserver;
import ub.edu.view.UBFLIXParty;

import java.util.ArrayList;
import java.util.List;

public class ControladorGUI implements IController {
    // Atributos
    private volatile static ControladorGUI uniqueInstance;
    private final Facade facana;
    private final UBFLIXParty view;

    /**
     * Método constructor del ControladorGUI aplicando el patrón Singleton
     */
    private ControladorGUI() {
        DataService dataService = DataService.getInstance(new FactoryMOCK());
        facana = Facade.getInstance(dataService);
        view = new UBFLIXParty(this);
    }

    public static ControladorGUI getInstance() {
        if (uniqueInstance == null) {
            synchronized (ControladorGUI.class) {
                if (uniqueInstance == null) uniqueInstance = new ControladorGUI();
            }
        }
        return uniqueInstance;
    }



    //////////////////////////////////////////
    /*       METODOS PARA INICIALIZAR       */
    //////////////////////////////////////////

    /**
     * Métod para inicializar la facana
     * @throws Exception Si hay un error fatal
     */
    public void init() throws Exception {
        facana.init();
        view.init();
    }



    //////////////////////////////////////
    /*      Métodos sobre Clientes      */
    //////////////////////////////////////

    /**
     * Método para añadir un Cliente a la cartera de Clientes y a la base de datos
     * @param idClient nombre  del Cliente
     * @param psw contraseña del Cliente
     * @param dni DNI del Cliente
     * @param adress direccion del Cliente
     * @param vip si el Cliente es o no VIP
     * @return frase conforme se ha creado o no el cliente
     * */
    public String addClient(String idClient, String psw, String dni, String adress, boolean vip) {
        try {
            int casosFacana = facana.addClient(idClient, psw, dni, adress, vip);
            switch (casosFacana){
                case 1: return "Taken Username";
                case 2: return "Invalid DNI";
                case 3: return "Password not secure enough";
                default: return "Client created";
            }
        } catch (Exception e) { return "Can't connect to DataBase: \n Client not created"; }

    }


    /**
     * Método para validar Login de un Cliente con su nombre y contraseña
     * @param username nombre del Cliente
     * @param password contraseña del Cliente
     * @return True si existe el Cliente con esa contraseña, False si no
     * */
    public boolean validateClient(String username, String password){ return facana.validateLoginClient(username, password); }

    /**
     * Metodo para obtener la lista de Usuarios de un CLiente
     * @param nomClient id del Cliente
     * @return Iterable de nombres de Usuario
     */
    public Iterable<String> listUsuaris(String nomClient) { return facana.listUsuaris(nomClient); }





    //////////////////////////////////////////
    /*        Metodos sobre Usuarios        */
    //////////////////////////////////////////


    /**
     * Método para comprobar si se puede añadir un nuevo usuario a un Cliente
     * @param nomClient nombre del Cliente
     * @return True si sí se puede añadir, False si no se puede
     * */
    public boolean canAddUserToClient(String nomClient) {
        try { return facana.canAddUserToClient(nomClient); }
        catch (Exception e) { return true; }
    }

    /**
     * Método para añadir un usuario a un Cliente
     * @param nomClient nombre del Cliente
     * @param nom nombre del Usuario
     * @return frase de si se ha creado el usuario o no y, en este caso, el porqué
     * */
    public String addUser(String nomClient, String nom) {
        try {
            int casosFacana = facana.addUser(nomClient, nom);
            switch (casosFacana) {
                case 1: return "Client does not exist";
                case 2: return "Too many Users";
                case 3: return "User alredy created";
                default: return "User created";
            }
        } catch (Exception exp){ return exp.getMessage(); }
    }



    ///////////////////////////////////////
    /*        Métodos sobre Series       */
    ///////////////////////////////////////

    /**
     * Método para pedir el listado de Series del Catalogo de Series
     * @return catalogo de series listado
     * */
    public List<String> llistarCatalegSeries() {
        try { return facana.llistarCatalegSeries();
        } catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }

    ///////////////////////////////////////
    /*     Métodos sobre Temporadas      */
    ///////////////////////////////////////

    /**
     * Método para pedir el listado de temporadas de una serie
     * @param nomSerie Nombre de la Serie
     * @return catalogo de temporadas de una serie
     * */
    public List<String> getTemporades(String nomSerie){
        return facana.getTemporades(nomSerie);
    }



    ///////////////////////////////////////
    /*       Métodos sobre Episodios     */
    ///////////////////////////////////////
    /**
     * Método para pedir el listado de episodios de una temporada de una serie
     * @param nomSerie Nombre de la Serie
     * @param temporada numero de la temporada
     * @return listado de episodios
     * */
    public List<Episodi> getEpisodis(String nomSerie, int temporada) {
        return facana.getEpisodis(nomSerie,temporada);
    }



    ///////////////////////////////////////
    /*       Métodos sobre My List       */
    ///////////////////////////////////////

    /**
     * Método para listar las series de la lista MyList de un Usuario.
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @return Iterable con los títulos de las series de MyList, ordenados según su fecha de adición.
     */
    public Iterable<String> listMyList(String client, String user) {
        try { return facana.listMyList(client, user);
        } catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }

    /**
     * Método para añadir una serie a la lista MyList de un Usuario.
     * @param id id de la preferencia
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @param serie títol de la Serie
     * @return Mensaje conforme si se ha añadido correctamente o no se ha completado la operación.
     * */
    public String addSerieToMyList(int id, String client, String user, String serie) {
        try {
            facana.addSerieToMyList(id, client, user, serie);
            return "Sèrie '" + serie + "' correctament afegida a MyList.";
        } catch (Exception exp) { return exp.getMessage(); }
    }

    /**
     * Método para eliminar una serie a la lista MyList de un Usuario.
     * @param id de la valoracio
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @param serie títol de la Serie
     * @return Mensaje conforme si se ha eliminado, o no se ha completado la operación.
     * */
    public String removeSerieFromMyList(int id, String client, String user, String serie) {
        try {
            facana.removeSerieFromMyList(id, client, user, serie);
            return "Sèrie '" + serie + "' correctament eliminada de MyList.";
        } catch (Exception exp) { return exp.getMessage(); }
    }



    ///////////////////////////////////////
    /*    Métodos sobre Watched List     */
    ///////////////////////////////////////

    /**
     * Método para listar las series de la lista Watched de un Usuario.
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @return Iterable con los títulos de las series de Watched
     */
    public Iterable<String> listMyWatchedList(String client, String user) {
        try { return facana.listWatchedList(client, user);
        } catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }



    //////////////////////////////////////////
    /*  Métodos sobre ContinueWatching List */
    //////////////////////////////////////////

    /**
     * Método para listar las series de la lista ContinueWatching de un Usuario.
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @return Iterable con los títulos de las series de ContinueWatching
     */
    public Iterable<String> listMyContinueWatchingList(String client, String user) {
        try { return facana.listContinueWatchingList(client, user);
        } catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }

    //////////////////////////////////
    /*   Métodos sobre Visualizar   */
    //////////////////////////////////

    /**
     * Metodo que permite guardar la visualizacion de un episodio
     * @param id ID de la Visualizacion
     * @param idClient ID del Cliente
     * @param nomUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param numTemporada Numero de Temporada
     * @param idEpisodi ID del Episodio
     * @param data Fecha de visualizacion
     * @param segonsRestants Segundos restantes por ver
     */
    public String visualitzarEpisodi(int id, String idClient, String nomUser, String idSerie, int numTemporada, int idEpisodi, String data, int segonsRestants) {
        try {
            int casosFacana = facana.visualitzarEpisodi(id, idClient, nomUser, idSerie, numTemporada, idEpisodi, data, segonsRestants);
            switch (casosFacana) {
                case 0: return "Visualización añadida Correctamente";
                case 1: return "Modificación de la Visualización realizada Correctamente";
                default: return "Error: visualización fallida.";
            }
        } catch (Exception e) { return e.getMessage(); }
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
    public int getDuracioVisualitzada(String idClient, String idUser, String idSerie, int numTemporada, int numEpisodi, int duracioEpisodi) {
        try { return facana.getDuracioVisualitzada(idClient, idUser, idSerie, numTemporada, numEpisodi, duracioEpisodi);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
    public boolean isEpisodiVisualitzat(String idSerie, int numTemporada, int idEpisodi, String currentClient, String currentUsuari) {
        try { return facana.isEpisodiVisualitzat(idSerie, numTemporada, idEpisodi, currentClient, currentUsuari);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    ////////////////////////////////////////
    /*  Métodos sobre Valorar con Corazon */
    ////////////////////////////////////////

    /**
     * Método para valorar una serie del catalogo con un Corazon
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param idSerie ID de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodi
     * @param data fecha del Episodio
     * @return mensaje conforme se ha valorado o no correctamente
     * */
    public String valorarEpisodiCor(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, String data) {
        try {
            int casosFacana = facana.valorarEpisodiCor(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, data);
            switch (casosFacana) {
                case 0: return "Valoracion añadida Correctamente";
                case 1: return "Valoracion Eliminada";
                default: return "Error: valoración fallida";
            }
        } catch (Exception e) { return e.getMessage(); }
    }


    //////////////////////////////////////////
    /*  Métodos sobre Valorar con Estrellas */
    //////////////////////////////////////////
    /**
     * Método para valorar una serie del catalogo con Estrellas
     * @param id ID de la Valoracion
     * @param idClient ID del Cliente
     * @param nomUsuari nombre del Usuario
     * @param idSerie ID de la Serie
     * @param idTemporada ID de la Temporada
     * @param idEpisodi ID del Episodi
     * @param estrelles puntuacion en estrellas
     * @param data fecha del Episodio
     * @return mensaje conforme se ha valorado o no correctamente
     * */
    public String valorarEpisodiEstrellas(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, int estrelles, String data) {
        try {
            int casosFacana = facana.valorarEpisodiEstrellas(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, estrelles, data);
            switch (casosFacana) {
                case 1: return "Debe introducir una valoración entre 0 y 5 estrellas";
                case 2: return "Modificación de la Valoración realizada Correctamente";
                case 3: return "Valoracion Eliminada";
                default: return "Valoracion añadida Correctamente";
            }
        } catch (Exception e) { return e.getMessage(); }
    }



    ///////////////////////////////////
    /*    Métodos sobre Observers    */
    ///////////////////////////////////

    /**
     * Método para registrar un Observador
     * @param observer Observador que se quiere subscribir
     */
    public void registerObserver(RegisterObserver observer) { facana.registerObserver(observer); }

}