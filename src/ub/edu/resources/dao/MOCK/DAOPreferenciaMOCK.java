package ub.edu.resources.dao.MOCK;

import ub.edu.model.Preferencia;
import ub.edu.resources.dao.DAOPreferencia;

import java.util.*;

public class DAOPreferenciaMOCK implements DAOPreferencia {

    /**
     * Hashmap (array de identificadores únicos) de objetos tipo Preferencia,
     * con Key el Id del Usuari, Value un ArrayList de objetos del tipo Preferencia.
     * */
    private final Map<String, ArrayList<Preferencia>> preferredSeries = new HashMap<>();

    /**
     * Constructor DAOPreferenciaMOCK. Crea las distintas asociaciones del tipo Preferencia
     * de algunos usuarios MOCK.
     */
    public DAOPreferenciaMOCK() {
        // Preferencias del Usuario 'Pol', del Cliente 'ajaleo'
        ArrayList<Preferencia> preferencia_1 = new ArrayList<>();
        preferencia_1.add(new Preferencia(1, "ajaleo", "Pol", "Breaking Bad"));
        preferencia_1.add(new Preferencia(2, "ajaleo", "Pol", "Game of Thrones"));
        preferencia_1.add(new Preferencia(3, "ajaleo", "Pol", "Mr Robot" ));
        preferencia_1.add(new Preferencia(4, "ajaleo", "Pol", "Stranger Things"));
        preferredSeries.put("id1", preferencia_1);

        // Preferencias del Usuario 'Manuel', del Cliente 'ajaleo'
        ArrayList<Preferencia> preferencia_2 = new ArrayList<>();
        preferencia_2.add(new Preferencia(5, "ajaleo", "Manuel", "Mr Robot"));
        preferencia_2.add(new Preferencia(6, "ajaleo", "Manuel", "The Mandalorian"));
        preferredSeries.put("id2", preferencia_2);

        // Preferencias del Usuario 'Laura', del Cliente 'dtomacal'
        ArrayList<Preferencia> preferencia_3 = new ArrayList<>();
        preferencia_3.add(new Preferencia(7, "dtomacal", "Laura", "Stranger Things"));
        preferencia_3.add(new Preferencia(8, "dtomacal", "Laura", "Game of Thrones"));
        preferencia_3.add(new Preferencia(9, "dtomacal", "Laura", "Breaking Bad"));
        preferredSeries.put("id4", preferencia_3);
    }

    /**
     * Método para devolver todos las series preferidas de todos los Usuarios.
     * @return list de todos las preferencias (asociaciones Usuari - Serie).
     */
    @Override
    public List<Preferencia> getAll() {
        List<Preferencia> llistaCompleta = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Preferencia>> entry: preferredSeries.entrySet()) {
            llistaCompleta.addAll(entry.getValue());
        }
        return llistaCompleta;
    }

    /**
     * Método para añadir una preferencia a la base de datos de series preferidas.
     * @param s Preferencia, nueva asociación entre Usuari y Serie
     * @return True si se ha añadido correctamente, false sino.
     */
    @Override
    public boolean add(Preferencia s) { return getAll().add(s); }

    /**
     * Método para deletear cierta asociación del tipo Preferencia.
     * @param s Preferencia
     * @return True si se ha encontrado y eliminado la preferencia.
     */
    @Override
    public boolean delete(Preferencia s) { return getAll().remove(s); }

    /**
     * Método para obtener el listado de series favoritas de un Usuario
     * @param userID Id del usuario
     * @return Listado de associaciones del tipo Preferencia.
     */
    @Override
    public List<Preferencia> getPreferredSeriesByIdUser(String userID) {
        if (!preferredSeries.containsKey(userID)) return null;
        return preferredSeries.get(userID);
    }

    @Override
    public Map<String, ArrayList<Preferencia>> getMap() {
        return new HashMap<>(preferredSeries);
    }


    @Override
    public Optional<Preferencia> getById(String id) { return Optional.empty(); }

    @Override
    public boolean update(Preferencia preferencia, String[] params) { return false; }


}
