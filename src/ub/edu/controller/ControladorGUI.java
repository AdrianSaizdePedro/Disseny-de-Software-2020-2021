package ub.edu.controller;

import ub.edu.model.*;
import ub.edu.resources.service.DataService;
import ub.edu.resources.service.FactoryMOCK;
import ub.edu.view.Observer;
import ub.edu.view.UBFLIXParty;

import java.util.ArrayList;
import java.util.List;

public class ControladorGUI implements IController{
    private volatile static ControladorGUI uniqueInstance;

    private DataService dataService;
    private Facade facana;

    private UBFLIXParty view;

    private ControladorGUI() {
        dataService = DataService.getInstance(new FactoryMOCK());
        facana = Facade.getInstance(dataService);
        view = new UBFLIXParty(this);
    }

    public static ControladorGUI getInstance(){
        if (uniqueInstance == null) {
            synchronized (ControladorGUI.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ControladorGUI();
                }
            }
        }
        return uniqueInstance;
    }

    public void init() throws Exception {
        facana.init();
        view.init();
    }

    //////////////////////////////////////
    /*      Métodos Test AltaClient     */
    //////////////////////////////////////


    /**
     * Método para pedir la validación de la contraseña de un Cliente
     * @param password contraseña del Cliente
     * @return True si se valida correctamente, False sino
     * */
    public boolean validatePassword(String password) {
        return facana.validatePassword(password);
    }

    /**
     * Método para pedir la validación del nombre de un Cliente
     * @param username nombre del Cliente
     * @return True si existe ya el cliente, False si no está cogido
     * */
    public boolean isTakenUsername(String username) { return facana.isValidNameClient(username); }

    /**
     * Método para pedir la validación del DNI de un Cliente
     * @param dni DNI del Cliente
     * @return True si el DNI es correcto, False sino
     * */
    public boolean validateDNI(String dni) { return facana.validarDNI(dni); }

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

    //////////////////////////////////////////
    /*      Métodos Test Login (Cliente)    */
    //////////////////////////////////////////

    /**
     * Método para validar Login de un Cliente con su nombre y contraseña
     * @param username nombre del Cliente
     * @param password contraseña del Cliente
     * @return True si existe el Cliente con esa contraseña, False si no
     * */
    public boolean validateClient(String username, String password) {
        return facana.validateLoginClient(username, password);
    }

    /**
     * Metodo para obtener la lista de Usuarios de un CLiente
     * @param nomClient id del Cliente
     * @return Iterable de nombres de Usuario
     */
    public Iterable<String> listUsuaris(String nomClient) {
        return facana.listUsuaris(nomClient);
    }

    /**
     * Método para validar la existencia del nombre de un Cliente en CarteraClients
     * @param username nombre del Cliente
     * @return frase conforme se ha validado o no el nombre del Cliente
     * */
    public String isValidNameClient(String username) {
        if (facana.isValidNameClient(username)) return "Valid Client";
        else return "Client Unknown";
    }



    //////////////////////////////////////////
    /*        Metodos Test AltaUsuari       */
    //////////////////////////////////////////

    /**
     * Método para comprobar que no se repitan nombres de Usuario en un Cliente
     * @param nomClient nombre del Cliente
     * @param nomUsuari nombre del Usuario
     * @return True si existe el nombre de usuario, False sino
     * */
    public boolean existsNameUser(String nomClient, String nomUsuari) {
        try { return facana.existsNameUser(nomClient, nomUsuari); }
        catch (Exception e) { return false; }
    }

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
    /*     Métodos Test llistaMyList     */
    ///////////////////////////////////////

    /**
     * Método para listar las series de la lista MyList de un Usuario.
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @return Iterable con los títulos de las series de MyList, ordenados según su fecha de adición.
     */
    public Iterable<String> listMyList(String client, String user) {
        try { return facana.listMyList(client, user); }
        catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }

    ////////////////////////////////////////
    /*   Métodos Test marcarSerieMyList   */
    ////////////////////////////////////////

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
            if (facana.addSerieToMyList(id, client, user, serie)) return "Sèrie '" + serie + "' correctament afegida a MyList.";
            return "Sèrie '" + serie + "' ja afegida a MyList.";
        } catch(Exception exp) { return exp.getMessage(); }
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
            if (facana.removeSerieFromMyList(id, client, user, serie))
                return "Sèrie '" + serie + "' correctament eliminada de MyList.";
            return "Sèrie '" + serie + "' no consta en MyList.";
        } catch(Exception exp) { return exp.getMessage(); }
    }

    ///////////////////////////////////////
    /*      Métodos Test llistaSeries    */
    ///////////////////////////////////////
    /**
     * Método para pedir el listado de Series del Catalogo de Series
     * @return catalogo de series listado
     * */
    public List<String> llistarCatalegSeries() {
        try { return facana.llistarCatalegSeries(); }
        catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }

    /**
     * Método para pedir el listado de temporadas de una serie
     * @param nomSerie Nombre de la Serie
     * @return catalogo de temporadas de una serie
     * */
    public List<String> getTemporades(String nomSerie){
        return facana.getTemporades(nomSerie);
    }

    /**
     * Método para pedir el listado de episodios de una temporada de una serie
     * @param nomSerie Nombre de la Serie
     * @param temporada numero de la temporada
     * @return listado de episodios
     * */
    public List<Episodi> getEpisodis(String nomSerie, int temporada) {
        return facana.getEpisodis(nomSerie,temporada);
    }



    //////////////////////////////////////
    /*   Métodos Test ferValoracioCor   */
    //////////////////////////////////////
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
                case 1: return "El Cliente no existe";
                case 2: return "El Usuario no Existe";
                case 3: return "El Episodio no existe";
                case 4: return "Valoracion Eliminada";
                default: return "Valoracion añadida Correctamente";
            }
        } catch (Exception e) { return e.getMessage(); }
    }


    ///////////////////////////////////////
    /* Métodos Test ferValoracioEstrella */
    ///////////////////////////////////////
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
                case 1: return "El Cliente no existe";
                case 2: return "El Usuario no Existe";
                case 3: return "El Episodio no existe";
                case 4: return "Debe introducir una valoración entre 0 y 5 estrellas";
                case 5: return "Modificación de la Valoración realizada Correctamente";
                case 6: return "Valoracion Eliminada";
                default: return "Valoracion añadida Correctamente";
            }
        } catch (Exception e) { return e.getMessage(); }
    }

    /////////////////////////////////
    /* Métodos Visualitzar Episodi */
    /////////////////////////////////

    /**
     * Permite guardar la visualizacion de un episodio
     * @param id ID de la Visualizacion
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param numTemporada Numero de Temporada
     * @param idEpisodi ID del Episodio
     * @param data Dataa
     * @param segonsRestants Segundos Restantes
     */
    public void visualitzarEpisodi(int id, String idClient, String idUser, String idSerie, int numTemporada,
                                   int idEpisodi, String data, int segonsRestants) {
        try {
            facana.visualitzarEpisodi(id, idClient, idUser, idSerie, numTemporada, idEpisodi, data, segonsRestants);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public int getDuracioVisualitzada(String idClient, String idUser, String idSerie, int numTemporada, int numEpisodi) {
        return facana.getDuracioVisualitzada(idClient, idUser, idSerie, numTemporada, numEpisodi);
    }



    public void registerObserver(Observer observer) {
        facana.registerObserver(observer);
    }
}