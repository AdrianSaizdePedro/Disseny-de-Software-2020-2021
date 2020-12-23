package ub.edu.controller;

import ub.edu.model.Facade;
import ub.edu.resources.service.AbstractFactoryData;
import ub.edu.resources.service.DataService;
import ub.edu.resources.service.FactoryMOCK;

import java.util.ArrayList;

public class ControllerTESTS implements IController{
    // Atributos
    private Facade facana;

    /**
     * Método contructor del Controlador
     * */
    public ControllerTESTS() {
        AbstractFactoryData factory = new FactoryMOCK();
        try {
            DataService dataService = new DataService(factory);
            facana = new Facade(dataService);
            init();
        } catch (Exception e) {
            System.out.println("FATAL ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metodo para inicializar la façana madre
     * @throws Exception si no se inicializa correctamente
     */
    public void init() throws Exception { facana.init(); }



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
        } catch (Exception e) { return "Client not created"; }
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
    public boolean validateClient(String username, String password) { return facana.validateLoginClient(username, password);}

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



    //////////////////////////////////////////
    /*      Métodos Test Login (Usuario)    */
    //////////////////////////////////////////

    /**
     * Método para validar Login de un Usuario con el nombre de su Cliente y su nombre de Usuario
     * @param clientName nombre del Cliente
     * @param userName nombre del Usuario
     * @return True si existe el nombre se usuario , False si no existe aun
     * */
    public boolean validateLoginUser(String clientName, String userName) {
        try { return facana.existsNameUser(clientName, userName); }
        catch (Exception e) { return false; }
    }



    ///////////////////////////////////////
    /*      Métodos Test llistaSeries    */
    ///////////////////////////////////////
    /**
     * Método para pedir el listado de Series del Catalogo de Series
     * @return catalogo de series listado
     * */
    public Iterable<String> llistarCatalegSeries() {
        try { return facana.llistarCatalegSeries(); }
        catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
    }

    ///////////////////////////////////////
    /*    Métodos Test llistaWatching    */
    ///////////////////////////////////////

    /**
     * Método para listar las series de la lista Watching List de un Usuario.
     * @param client nombre del Cliente
     * @param user nombre del Usuario
     * @return Iterable con los títulos de las series de Watching List, ordenados
     * según su fecha de visualización.
     */
    public Iterable<String> listWatchingList(String client, String user) {
        try { return facana.listWatchingList(client, user); }
        catch (Exception exp) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(exp.getMessage());
            return exception;
        }
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
    /*   Métodos Test veureDetallsSerie   */
    ////////////////////////////////////////

    /**
     * Método para mostrar los detalles de una Serie
     * @param nomSerie título de la Serie
     * @return string con el titulo y la descripcion de la Serie
     */
    public String mostrarDetallsSerie(String nomSerie) {
        try { return facana.mostrarDetallsSerie(nomSerie); }
        catch (Exception e) { return e.getMessage(); }
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



    ////////////////////////////////////////
    /*   Métodos Test llistarFollowers    */
    ////////////////////////////////////////

    /**
     * Método para conseguir una lista de los Usuarios que siguen a un Usuario
     * @param nomUsuari nombre del Usuario
     * @param nomCLient nombre del Cliente
     * @return string con todos los nombres de los Usuarios que siguen al Usuario
     */
    public Iterable<String> llistarFollowers(String nomCLient, String nomUsuari) {
        try { return facana.llistarFollowers(nomCLient, nomUsuari); }
        catch (Exception e) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(e.getMessage());
            return exception;
        }
    }

    ////////////////////////////////////////
    /*   Métodos Test llistarFollowings   */
    ////////////////////////////////////////

    /**
     * Método para conseguir una lista de los Usuarios que sigue un Usuario
     * @param nomUsuari nombre del Usuario
     * @param nomCLient nombre del Cliente
     * @return string con todos los nombres de los Usuarios que sigue el Usuario
     */
    public Iterable<String> llistarFollowings(String nomCLient, String nomUsuari) {
        try { return facana.llistarFollowings(nomCLient, nomUsuari); }
        catch (Exception e) {
            ArrayList<String> exception = new ArrayList<>();
            exception.add(e.getMessage());
            return exception;
        }
    }


    //////////////////////////////////////
    /*  Métodos Test modificarNomPerfil */
    //////////////////////////////////////

    /**
     * Método para cambiar el nombre del Usuario seleccionado
     * @param nomClient Nombre del Cliente
     * @param nomUsuariAntic Nombre del Usuario Antiguo
     * @param nomUsuariNou Nombre del Usuario Nuevo
     * @return String conforme se ha realizado del cambio o no
     * */
    public String modificarPerfil(String nomClient, String nomUsuariAntic, String nomUsuariNou) {
        try {
            int casosModPerfil = facana.modificarPerfil(nomClient, nomUsuariAntic, nomUsuariNou);
            switch (casosModPerfil) {
                case 1: return "User alredy has this name";
                case 2: return "El nombre del usuario nuevo no debe ser vacío";
                default: return "Se ha cambiado el nombre correctamente a: " + nomUsuariNou;
            }

        } catch (Exception e) { return e.getMessage();}
    }



    //////////////////////////////////////
    /*      Métodos Test veurePerfil    */
    //////////////////////////////////////
    /**
     * Método para pedir ver los datos del Usuario
     * @param nomClient Nombre del Cliente
     * @param nomUsuari Nombre del Usuario
     * @return String de los datos del Usuario
     * */
    public String veurePerfil(String nomClient, String nomUsuari) {
        try { return facana.veurePerfil(nomClient, nomUsuari); }
        catch (Exception e) { return e.getMessage(); }
    }


    //////////////////////////////////////
    /*       Métodos Test FerFollow     */
    //////////////////////////////////////

    /**
     * Método para seguir a un Usuario
     * @param userWhoFollows Usuario que quiere comenzar a seguir a otro Usuario
     * @param userFollowed Usuario seguido
     * @return Mensaje conforme si se ha seguido correctamente o no
     */
    public String ferFollow(String userWhoFollows, String userFollowed) {
        try {
            int casosFacana = facana.addFollowerToUser(userFollowed, userWhoFollows);
            switch (casosFacana) {
                case 0: return "L'usuari '" + userWhoFollows + "' correctament afegit a la llista de Followers de '" + userFollowed + "'.";
                case 1: return "L'usuari '" + userWhoFollows + "' ja hi es a la llista de Followers de '" + userFollowed + "'.";
                default: return "";
            }
        } catch (Exception e) { return e.getMessage(); }
    }

    /**
     * Método para dejar de seguir a un Usuario
     * @param userUnfollower Usuario que quiere dejar de seguir a otro Usuario
     * @param userUnfollowed Usuario que deja de seguir
     * @return Mensaje conforme si se ha eliminado correctamente o no
     */
    public String ferUnfollow(String userUnfollower, String userUnfollowed) {
        try {
            int casosFacana = facana.removeFollowerFromUser(userUnfollower, userUnfollowed);
            switch (casosFacana) {
                case 0: return "L'usuari '" + userUnfollowed + "' correctament eliminat de la llista de Followers de '" + userUnfollower + "'.";
                case 1: return "L'usuari '" + userUnfollowed + "' no hi es a la llista de Followers de '" + userUnfollower + "'.";
                default: return "";
            }
        } catch (Exception e) { return e.getMessage(); }
    }



}
