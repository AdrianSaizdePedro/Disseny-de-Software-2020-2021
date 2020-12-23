package ub.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    // Atributos
    private String nom;
    private String pwd;
    private String dni;
    private String adress;
    private boolean vip;
    private List<Usuari> usuaris;


    /**
     * Método contructor de un Cliente
     * @param nom nombre del Cliente
     * @param pwd contraseña del Cliente
     * @param dni DNI del Cliente
     * @param adress direccion del Cliente
     * @param vip si es VIP o no el Cliente
     * */
    public Client(String nom, String pwd, String dni, String adress, boolean vip) {
        this.nom = nom;
        this.pwd = pwd;
        this.dni = dni;
        this.adress = adress;
        this.vip = vip;
        this.usuaris = new ArrayList<>();
    }

    /**
     * Método para encontrar un Usuario en la Lista de Usuarios
     * @param nameUsuari nombre del Usuario
     * @return True si encuentra al Usuario, False si no lo encuentra
     * */
    public Usuari findUserByName(String nameUsuari){
        for(Usuari u: usuaris) {
            if (u.getName().equals(nameUsuari)) return u;
        }
        return null;
    }


    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////
    /**
     * Método para devolver la Lista de Usuarios del Cliente
     * @return lista de usuarios
     * */
    public List<Usuari> getUsuaris() {
        return usuaris;
    }

    /**
     * Método para actualizar la Lista de Usuarios del Cliente
     * @param llistaUsuaris  todos los usuarios del Cliente
     * */
    public void setUsuaris(List<Usuari> llistaUsuaris) { usuaris = llistaUsuaris; }

    /**
     * Método para devolver la contraseña del Cliente
     * @return contraseña
     * */
    public String getPwd() {
        return pwd;
    }

    /**
     * Método para establecer la contraseña del Cliente
     * @param pwd contraseña del Cliente
     * */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Método para devolver el nombre del Cliente
     * @return nombre
     * */
    public String getName() {
        return nom;
    }

    /**
     * Método para establecer el nombre del Cliente
     * @param nom nombre del Cliente
     * */
    public void setName(String nom) {
        this.nom = nom;
    }

    /**
     * Método para devolver el DNI del Cliente
     * @return DNI
     * */
    public String getDni() {
        return dni;
    }

    /**
     * Método para establecer el DNI del Cliente
     * @param dni DNI del Cliente
     * */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Método para devolver la dirección del Cliente
     * @return dirección
     * */
    public String getAdress() {
        return adress;
    }

    /**
     * Método para establecer la dirección del Cliente
     * @param adress direccion del Cliente
     * */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Método para saber si el Cliente es vip o no
     * @return True, False
     * */
    public boolean isVip() {
        return vip;
    }

    /**
     * Método para establecer el Cliente como VIP o no VIP
     * @param vip si es VIP o no el Cliente
     * */
    public void setVip(boolean vip) {
        this.vip = vip;
    }



    //////////////////////////////////////
    /*      METODOS SOBRE USUARIOS      */
    //////////////////////////////////////

    /**
     * Método para validar si el nombre de un usuario existe en un Cliente
     * @param nomUsuari nombre del Usuario
     * @return True si existe el nombre de usuario, False si no existe
     */
    public boolean existNameUser(String nomUsuari) { return findUserByName(nomUsuari) != null; }

    /**
     * Método para comprobar si se puede añadir un nuevo Usuario a un Cliente
     * @return True si sí se puede añadir, False si no se puede
     */
    public boolean canAddUser() { return this.getUsuaris().size() < 5; }

    /**
     * Método para crear y añadir un Usuario a la Lista de Usuarios de un Cliente
     * @param nom nombre del Usuario
     * @param id ID del Usuario
     */
    public void addUser(String nom, String id) {
        usuaris.add(new Usuari(this.nom, nom, id));
    }

    /**
     * Metodo para añadir un Usuario ya crteado a la Lista de Usuarios de un Cliente
     * @param u Usuario a añadir
     */
    public void addUser(Usuari u) { usuaris.add(u); }



    //////////////////////////////////////
    /*       METODOS SOBRE PERFIL       */
    //////////////////////////////////////

    /**
     * Método para cambiar el nombre del Usuario seleccionado
     * @param nomUsuariAntic Nombre del Usuario Antiguo
     * @param nomUsuariNou Nombre del Usuario Nuevo
     * @throws Exception si el Usuario no existe
     * */
    public void modificarPerfil(String nomUsuariAntic, String nomUsuariNou) throws Exception {
        if (findUserByName(nomUsuariAntic) == null) throw new Exception("No existe este Usuario en el Cliente: " + getName());
        findUserByName(nomUsuariAntic).setName(nomUsuariNou);
    }

    /**
     * Método para ver el perfil de un Usuario
     * @param nomUsuari Nombre del Usuario
     * @return String de la info del Usuario
     * @throws Exception si el Usuario no existe
     * */
    public String veurePerfil(String nomUsuari) throws Exception{
        if (findUserByName(nomUsuari) == null) throw new Exception("No existe este Usuario en el Cliente: " + getName());
        return findUserByName(nomUsuari).toString();
    }



    //////////////////////////////////////////////////
    /*      METODOS SOBRE FOLLOWERS/FOLLOWINGS      */
    //////////////////////////////////////////////////

    /**
     * Método para hacer una lista de los seguidores de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followers
     * @return string con los nombres de los followers del Usuario
     * @throws Exception cuando no existe el Usuario o no tiene Followers
     */
    public Iterable<String> listFollowers(String nomUsuari) throws Exception {
        if (findUserByName(nomUsuari) == null) throw new Exception("No existe este Usuario en el Cliente: " + getName());
        return findUserByName(nomUsuari).listFollowers();

    }

    /**
     * Método para hacer una lista de los seguidos de un Usuario
     * @param nomUsuari Identificador del Usuario del que queremos saber los Followings
     * @return string con los nombres de los Followings del Usuario
     * @throws Exception cuando no existe el Usuario o no tiene Followings
     */
    public Iterable<String> listFollowings(String nomUsuari) throws Exception {
        if (findUserByName(nomUsuari) == null) throw new Exception("No existe este Usuario en el Cliente: " + getName());
        return findUserByName(nomUsuari).listFollowings();
    }

    /**
     * Metodo para obtener la lista de Usuarios de un CLiente
     * @return Iterable de nombres de Usuario
     */
    public Iterable<String> listUsuaris() {
        List<String> usuaris = new ArrayList<>();
        for (Usuari u : this.usuaris) {
            usuaris.add(u.getName());
        }
        return usuaris;
    }
}
