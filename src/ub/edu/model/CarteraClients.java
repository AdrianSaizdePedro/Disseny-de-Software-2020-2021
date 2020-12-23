package ub.edu.model;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CarteraClients{
    // Atributos
    private final List<ub.edu.model.Client> llistaClientes;

    /**
     * Método contructor de CarteraClients
     * */
    public CarteraClients() {
        this.llistaClientes = new ArrayList<>();
    }

    /**
     * Metodo para inicializar la lista de Clientes con todos sus Usuarios, Followers y Followings respectivos
     * @param allClients lista de todos los Clientes
     * @param allUsuaris lista de todos los Usuarios
     * @param allFollowers lista de todos los Followers
     * @param allFollowings lista de todos los Followings
     */
    public void init(List<ub.edu.model.Client> allClients, List<Usuari> allUsuaris, Map<String, ArrayList<Usuari>> allFollowers, Map<String, ArrayList<Usuari>> allFollowings) {
        for (ub.edu.model.Client c : allClients) {
            for(Usuari u : allUsuaris) {
                if (allFollowers.containsKey(u.getIdUser())) u.setFollowers(allFollowers.get(u.getIdUser()));
                if (allFollowings.containsKey(u.getIdUser()))u.setFollowings(allFollowings.get(u.getIdUser()));
                if (u.getNomClient().equals(c.getName())) c.addUser(u);
            }
            llistaClientes.add(c);
        }
    }



    //////////////////////////////////////
    /*      METODOS SOBRE CLIENTES      */
    //////////////////////////////////////

    /**
     * Método para encontrar Clientes por su nombre
     * @param userName nombre del Cliente
     * @return Cliente si lo encuentra, null sino
     * */
    public ub.edu.model.Client find(String userName) {
        for (ub.edu.model.Client c: llistaClientes) {
            if (c.getName().equals(userName)) return c;
        }
        return null;
    }

    /**
     * Método para añadir un Cliente a la Lista de Clientes
     * @param idClient ID del Cliente
     * @param psw contraseña del Cliente
     * @param dni DNI del Cliente
     * @param adress dirección del Cliente
     * @param vip Saber si es VIP o no el Cliente
     * */
    public void addClient(String idClient, String psw, String dni, String adress, boolean vip) {
        llistaClientes.add(new ub.edu.model.Client(idClient, psw, dni, adress, vip));
    }



    //////////////////////////////////////
    /*      METODOS SOBRE USUARIOS      */
    //////////////////////////////////////

    /**
     * Método para validar si el nombre de un usuario existe en un Cliente
     * @param nomClient nombre del Cliente
     * @param nomUsuari nombre del Usuario
     * @return True si existe el nombre de usuario, False si no existe
     * @throws Exception si el Cliente no existe
     */
    public boolean existNameUser(String nomClient, String nomUsuari) throws Exception{
        if (find(nomClient) == null) throw new Exception("Client does not exist");
        return find(nomClient).existNameUser(nomUsuari);
    }

    /**
     * Método para comprobar si se puede añadir un nuevo Usuario a un Cliente
     * @param nomClient nombre del Cliente al que queremos añadir un nuevo Usuario
     * @return True si sí se puede añadir, False si no se puede
     * @throws Exception si el Cliente no existe
     */
    public boolean canAddUserToClient(String nomClient) throws Exception{
        if (find(nomClient) == null) throw new Exception("Client does not exist");
        return find(nomClient).canAddUser();
    }

    /**
     * Método para añadir un Usuario a un Cliente
     * @param nomClient nombre del Cliente
     * @param nom nombre del Usuario
     * */
    public void addUser(String nomClient, String nom){
        find(nomClient).addUser(nom, "id" + getIdUser());
    }

    /**
     * Método para calcular el número del ID de un Usuario
     * @return número del ID
     * */
    private int getIdUser(){
        int contador = 0;
        for (ub.edu.model.Client c: llistaClientes) {
            contador += c.getUsuaris().size();
        }
        return contador;
    }

    /**
     * Metodo para conseguir un Usuario mediente su ID
     * @param idUser ID del Usuario
     * @return usuario encontrado o null si no existe
     */
    public Usuari getUserById(String idUser) {
        for (ub.edu.model.Client c: llistaClientes){
            for (Usuari u: c.getUsuaris()){
                if (u.getIdUser().equals(idUser)) return u;
            }
        }
        return null;
    }



    //////////////////////////////////////
    /*       METODOS SOBRE PERFIL       */
    //////////////////////////////////////

    /**
     * Método para cambiar el nombre del Usuario seleccionado
     * @param nomClient Nombre del Cliente
     * @param nomUsuariAntic Nombre del Usuario Antiguo
     * @param nomUsuariNou Nombre del Usuario Nuevo */
    public void modificarPerfil(String nomClient, String nomUsuariAntic, String nomUsuariNou) throws Exception {
        if (find(nomClient) == null) throw new Exception("No existe este cliente");
        find(nomClient).modificarPerfil(nomUsuariAntic, nomUsuariNou);
    }

    /**
     * Método para poder ver los datos del Usuario
     * @param nomClient Nombre del Cliente
     * @param nomUsuari Nombre del Usuario
     * @return String de los datos del Usuario
     * @throws Exception si el Cliente o el Usuario no existen
     * */
    public String veurePerfil(String nomClient, String nomUsuari) throws Exception{
        if(find(nomClient) == null) throw new Exception("No existe este cliente");
        return find(nomClient).veurePerfil(nomUsuari);
    }



    //////////////////////////////////////////////////
    /*      METODOS SOBRE FOLLOWERS/FOLLOWINGS      */
    //////////////////////////////////////////////////

    /**
     * Método para hacer una lista de los seguidores de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followers
     * @return string con los nombres de los followers del Usuario
     * @throws Exception cuando no existe el Cliente y/o el Usuario
     */
    public Iterable<String> listFollowers(String nomClient, String nomUsuari) throws Exception {
        if (find(nomClient) == null) throw new Exception("No existe este cliente");
        return find(nomClient).listFollowers(nomUsuari);
    }

    /**
     * Método para hacer una lista de los seguidos de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followings
     * @return string con los nombres de los Followings del Usuario
     * @throws Exception cuando no existe el Cliente y/o el Usuario
     */
    public Iterable<String> listFollowings(String nomClient, String nomUsuari) throws Exception {
        if (find(nomClient) == null) throw new Exception("No existe este cliente");
        return find(nomClient).listFollowings(nomUsuari);
    }

    /**
     * Método para seguir a un Usuario
     * @param userFollowed Usuario al que se quiere seguir
     * @param userWhoFollows Usuario que quiere hacer follow
     * @return True si se ha podido hacer Follow, False si no se ha llevado a cabo la operacion
     * @throws Exception si no existe alguno de los Usuarios
     */
    public boolean ferFollow(String userFollowed, String userWhoFollows) throws Exception {
        if (getUserById(userWhoFollows) == null) throw new Exception("Usuari '" + userWhoFollows+ "' no existeix");
        if (getUserById(userFollowed) == null) throw new Exception("Usuari '" + userFollowed + "' no existeix");
        return getUserById(userFollowed).addFollower(getUserById(userWhoFollows))
               && getUserById(userWhoFollows).addFollowing(getUserById(userFollowed));
    }

    /**
     * Método para dejar de seguir a un Usuario
     * @param userUnfollower Usuario que desea dejar de seguir a otro Usuario
     * @param userUnfollowed Usuario dejado de seguir
     * @return True si se ha podido hacer Unfollow, False si no se ha llevado a cabo la operacion
     * @throws  Exception si no existe alguno de los Usuarios
     */
    public boolean ferUnfollow(String userUnfollowed, String userUnfollower) throws Exception {
        if (getUserById(userUnfollowed) == null) throw new Exception("Usuari '" + userUnfollowed + "' no existeix");
        if (getUserById(userUnfollower) == null) throw new Exception("Usuari '" + userUnfollower+ "' no existeix");
        return getUserById(userUnfollowed).removeFollower(getUserById(userUnfollower))
               && getUserById(userUnfollower).removeFollowing(getUserById(userUnfollowed));
    }


}
