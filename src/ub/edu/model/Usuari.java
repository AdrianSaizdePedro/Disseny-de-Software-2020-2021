package ub.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Usuari {
    // Atributos
    private String nomClient;
    private String idUser;
    private String nom;
    private List<Usuari> followers;
    private List<Usuari> followings;

    /**
     * Método contructor de un Usuario
     * @param nomClient nombre del Cliente
     * @param nom nombre del Usuario
     * @param idUsuari ID del Usuario
     * */
    public Usuari(String nomClient, String nom, String idUsuari) {
        this.nomClient = nomClient;
        this.nom = nom;
        this.idUser = idUsuari;
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
    }



    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Método para devolver el nombre del Cliente al que pertenece un Usuario
     * @return nombre del Cliente
     * */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * Método para establecer el nombre del Cliente
     * @param nomClient nombre del Cliente
     * */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     * Método para devolver el nombre de un Usuario
     * @return nombre Usuario
     * */
    public String getName() {
        return nom;
    }

    /**
     * Método para establecer el nombre de un Usuario
     * @param nom nombre del Usuario
     * */
    public void setName(String nom) { this.nom = nom; }

    /**
     * Método para devolver el ID de un Usuario
     * @return ID
     * */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Método para establecer el ID de un Usuario
     * @param idUser ID del Usuario
     * */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Método para devolver la lista de followers del Usuario
     * @return lista de seguidores del Usuario
     */
    public List<Usuari> getFollowers() {
        return followers;
    }

    /**
     * Método para establecer la lista de followers del Usuario
     * @param followers lista de seguidores del Usuario actualizada
     */
    public void setFollowers(List<Usuari> followers) {
        this.followers = followers;
    }

    /**
     * Método para devolver la lista de followings del Usuario
     * @return lista de seguidos del Usuario
     */
    public List<Usuari> getFollowings() {
        return followings;
    }

    /**
     * Método para establecer la lista de followings del Usuario
     * @param followings lista de seguidores del Usuario actualizada
     */
    public void setFollowings(List<Usuari> followings) {
        this.followings = followings;
    }



    //////////////////////////////////////////////////
    /*      METODOS SOBRE FOLLOWERS/FOLLOWINGS      */
    //////////////////////////////////////////////////

    /**
     * Método para hacer una lista de los seguidores de un Usuario
     * @return string con los nombres de los followers del Usuario
     * @throws Exception cuando el  Usuario no tiene Followers
     */
    public Iterable<String> listFollowers() throws Exception {
        if (followers.isEmpty()) throw new Exception("Llista de Followers buida");
        List<String> listFollowers = new ArrayList<>();
        for (Usuari u : followers) listFollowers.add(u.getName());
        return listFollowers;
    }

    /**
     * Método para hacer una lista de los seguidos de un Usuario
     * @return string con los nombres de los Followings del Usuario
     * @throws Exception cuando el Usuario no tiene Followings
     */
    public Iterable<String> listFollowings() throws Exception {
        if (followings.isEmpty()) throw new Exception("Llista de Followings buida");
        List<String> listFollowings = new ArrayList<>();
        for (Usuari u : followings) listFollowings.add(u.getName());
        return listFollowings;
    }

    /**
     * Metodo para añadir un Follower al Usuario
     * @param userWhoFollows Usuario que quiere hacer follow
     * @return True si se ha podido añadir, False si no se ha llevado a cabo la operacion
     */
    public boolean addFollower(Usuari userWhoFollows) {
        if (!followers.contains(userWhoFollows)) return followers.add(userWhoFollows);
        return false;
    }

    /**
     * Metodo para añadir un Following al Usuario
     * @param userFollowed Usuario al que se quiere seguir
     * @return True si se ha podido añadir, False si no se ha llevado a cabo la operacion
     */
    public boolean addFollowing(Usuari userFollowed) {
        if (!followings.contains(userFollowed)) return followings.add(userFollowed);
        return false;
    }

    /**
     * Metodo para eliminar un Follower al Usuario
     * @param userUnfollower Usuario que quiere hacer unfollow
     * @return True si se ha podido eliminar, False si no se ha llevado a cabo la operacion
     */
    public boolean removeFollower(Usuari userUnfollower) {
        if (followers.contains(userUnfollower)) return followers.remove(userUnfollower);
        return false;
    }

    /**
     * Metodo para eliminar un Following al Usuario
     * @param userUnfollowed Usuario al que se quieredejar  seguir
     * @return True si se ha podido eliminar, False si no se ha llevado a cabo la operacion
     */
    public boolean removeFollowing(Usuari userUnfollowed) {
        if (followings.contains(userUnfollowed)) return followings.remove(userUnfollowed);
        return false;
    }

    //////////////////////////////////////
    /*      METODOS PARA IMPRIMIR       */
    //////////////////////////////////////

    /**
     * Metodo para concatenar atributos de Usuarios
     * @return string con el nombre del Cliente, el nombre del Usuario y su ID
     */
    public String toString() { return "Nom Client: " + this.nomClient + "\nNom Usuari: " + this.nom + "\nId Usuari: " + this.idUser; }

}
