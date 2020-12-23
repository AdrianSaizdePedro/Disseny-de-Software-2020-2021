package ub.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FacadeClients {
    // Atributos
    private final CarteraClients carteraClients;

    /**
     * Metodo constructor de FacadeClients
     */
    public FacadeClients() { this.carteraClients = new CarteraClients(); }

    /**
     * Metodo para inicializar la Cartera de Clientes
     * @param allClients lista de todos los Clientes
     * @param allUsuaris lista de todos los Usuarios
     * @param allFollowers lista de todos los Followers
     * @param allFollowings lista de todos los Followings
     */
    public void init(List<Client> allClients, List<Usuari> allUsuaris, Map<String, ArrayList<Usuari>> allFollowers, Map<String, ArrayList<Usuari>> allFollowings) { carteraClients.init(allClients, allUsuaris, allFollowers, allFollowings); }



    //////////////////////////////////////
    /*      METODOS SOBRE CLIENTES      */
    //////////////////////////////////////

    /**
     * Método para validar la contraseña de un Cliente
     * @param password contraseña del Cliente
     * @return True si es valida, False si no lo es
     * */
    public boolean validatePassword(String password) {
        if (password == null)  return false;
        return Pattern.compile("^(?=.*[a-z])(?=." + "*[A-Z])(?=.*\\d).+$").matcher(password).matches();
    }

    /**
     * Método para validar el DNI de un Cliente
     * @param dni DNI del Cliente
     * @return True si es valido, False si no lo es
     * */
    public boolean validarDNI(String dni) {
        if (dni == null || dni.length() != 9) return false;
        if (Pattern.compile( "[0-9]{8}[A-Z]" ).matcher(dni).matches()) return validateDNILetter(dni);
        else return false;
    }

    /**
     * Método para validar la letra del DNI de un Cliente
     * @param dni DNI del Cliente
     * @return True, False
     * */
    private boolean validateDNILetter(String dni) { return ("TRWAGMYFPDXBNJZSQVHLCKE").charAt(Integer.parseInt(dni.substring(0,8)) % 23) == dni.charAt(8); }


    /**
     * Método para validar si el nombre del Cliente existe en la Cartera de Clientes
     * @param clientName nombre del Cliente
     * @return True si existe, False si no.
     */
    public boolean isValidNameClient(String clientName) { return (carteraClients.find(clientName) != null); }

    /**
     * Método par añadir un Cliente nuevo a la Cartera de Clientes
     * @param idClient nombre del Cliente
     * @param psw contraseña del Cliente
     * @param dni DNI del Cliente
     * @param adress dirección del Cliente
     * @param vip si es o no VIP el Cliente
     */
    public void addClient(String idClient, String psw, String dni, String adress, boolean vip) { carteraClients.addClient(idClient, psw, dni, adress, vip); }


    /**
     * Metodo para encontrar un cliente por su nombre
     * @param nomClient nombre del Cliente
     * @return Cliente encontrado
     * @throws Exception si no exist un Cliente con ese nombre
     */
    public Client findClient(String nomClient) throws Exception {
        if (carteraClients.find(nomClient) == null) throw new Exception("Client does not exist");
        return carteraClients.find(nomClient);
    }



    //////////////////////////////////////
    /*      METODOS SOBRE USUARIOS      */
    //////////////////////////////////////

    /**
     * Método para validar si el nombre de un usuario existe en un Cliente
     * @param nomClient nombre del Cliente
     * @param nomUsuari nombre del Usuario
     * @return True si existe el nombre de usuario, False sino
     * @throws Exception si el Cliente no existe
     */
    public boolean existNameUser(String nomClient, String nomUsuari) throws Exception { return carteraClients.existNameUser(nomClient, nomUsuari); }

    /**
     * Método para comprobar si se puede añadir un nuevo Usuario a un Cliente
     * @param nomClient nombre del Cliente al que queremos añadir un nuevo Usuario
     * @return True si sí se puede añadir, False si no se puede
     * @throws Exception si el Cliente no existe
     */
    public boolean canAddUserToClient(String nomClient) throws Exception{ return carteraClients.canAddUserToClient(nomClient); }

    /**
     * Método para añadir un nuevo Usuario a un Cliente
     * @param nomClient nombre del Cliente al que añadiremos el Usuario
     * @param nom nombre del Usuario a añadir
     */
    public void addUser(String nomClient, String nom) {
        carteraClients.addUser(nomClient, nom);
    }



    //////////////////////////////////////
    /*       METODOS SOBRE PERFIL       */
    //////////////////////////////////////

    /**
     * Método para cambiar el nombre del Usuario seleccionado
     * @param nomClient Nombre del Cliente
     * @param nomUsuariAntic Nombre del Usuario Antiguo
     * @param nomUsuariNou Nombre del Usuario Nuevo
     * @throws Exception si el Cliente o el Usuario no existen
     * */
    public void modificarPerfil(String nomClient, String nomUsuariAntic, String nomUsuariNou) throws Exception { carteraClients.modificarPerfil(nomClient, nomUsuariAntic, nomUsuariNou); }

    /**
     * Método para pedir ver los datos del Usuario
     * @param nomClient Nombre del Cliente
     * @param nomUsuari Nombre del Usuario
     * @return String de los datos del Usuario
     * */
    public String veurePerfil(String nomClient, String nomUsuari) throws Exception { return carteraClients.veurePerfil(nomClient, nomUsuari); }



    //////////////////////////////////////////////////
    /*      METODOS SOBRE FOLLOWERS/FOLLOWINGS      */
    //////////////////////////////////////////////////

    /**
     * Método para hacer una lista de los seguidores de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followers
     * @return string con los nombres de los followers del Usuario
     * @throws Exception cuando no existe el Cliente y/o el Usuario
     */
    public Iterable<String> listFollowers(String nomClient, String nomUsuari) throws Exception { return carteraClients.listFollowers(nomClient, nomUsuari); }

    /**
     * Método para hacer una lista de los seguidos de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followings
     * @return string con los nombres de los Followings del Usuario
     * @throws Exception cuando no existe el Cliente y/o el Usuario
     */
    public Iterable<String> listFollowings(String nomClient, String nomUsuari) throws Exception { return carteraClients.listFollowings(nomClient, nomUsuari); }

    /**
     * Método para seguir a un Usuario
     * @param userFollowed Usuario al que se quiere seguir
     * @param userWhoFollows Usuario que quiere hacer follow
     * @return True si se ha podido hacer Follow, False si no se ha llevado a cabo la operacion
     * @throws  Exception si no existe alguno de los Usuarios
     */
    public boolean ferFollow(String userFollowed, String userWhoFollows) throws Exception { return carteraClients.ferFollow(userFollowed, userWhoFollows); }

    /**
     * Método para dejar de seguir a un Usuario
     * @param userUnfollower Usuario que desea dejar de seguir a otro Usuario
     * @param userUnfollowed Usuario dejado de seguir
     * @return True si se ha podido hacer Unfollow, False si no se ha llevado a cabo la operacion
     * @throws  Exception si no existe alguno de los Usuarios
     */
    public boolean ferUnfollow(String userUnfollowed, String userUnfollower) throws Exception { return carteraClients.ferUnfollow(userUnfollowed, userUnfollower); }

}
