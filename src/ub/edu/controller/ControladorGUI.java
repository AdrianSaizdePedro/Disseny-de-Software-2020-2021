package ub.edu.controller;

import ub.edu.model.*;
import ub.edu.resources.service.DataService;
import ub.edu.resources.service.FactoryMOCK;
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
}