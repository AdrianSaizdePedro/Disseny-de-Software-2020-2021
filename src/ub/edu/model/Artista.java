package ub.edu.model;

public class Artista {
    // Atributos
    private String nom_actor;
    private final String nom_personatje;
    private final String serieId;

    /**
     * Método contructor de un Artista
     * @param nom_actor nombre del actor
     * @param nom_personatje nombre del personaje que interpreta el actor
     * @param serieId ID de la Serie
     * */
    public Artista(String nom_actor, String nom_personatje, String serieId) {
        this.nom_actor = nom_actor;
        this.nom_personatje = nom_personatje;
        this.serieId = serieId;
    }


    //////////////////////////////////////
    /*         SETTERS Y GETTERS        */
    //////////////////////////////////////

    /**
     * Método para devolver el nombre real del Artista
     * @return nombre del Actor
     */
    public String getNom_actor() { return nom_actor; }

    /**
     * Método para establecer el nombre real del Artista
     * @param nom_actor nombre del Actor
     */
    public void setNom_actor(String nom_actor) { this.nom_actor = nom_actor; }

    /**
     * Método para devolver el nombre del personaje del Artista
     * @return nombre del personaje
     */
    public String getNom_personatje() { return nom_personatje; }

    /**
     * Método para devolver el ID de la Serie en la que trabaja el Artista
     * @return nombre del Actor
     */
    public String getSerieId() { return serieId; }

}
