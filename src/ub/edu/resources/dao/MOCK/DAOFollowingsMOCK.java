package ub.edu.resources.dao.MOCK;

import ub.edu.model.Usuari;
import ub.edu.resources.dao.DAOFollowings;
import ub.edu.resources.dao.DAOUsuari;

import java.util.*;

public class DAOFollowingsMOCK implements DAOFollowings {

    /**
     * HashMap (idUser, List<Usuaris>) de Followings de un Usuario
     */
    private final Map<String, ArrayList<Usuari>> followings = new HashMap<>();

    public DAOFollowingsMOCK(DAOUsuari daoUsers) throws Exception {
        // Followings de 'Pol' del Cliente 'ajaleo'
        ArrayList<Usuari> followings_1 = new ArrayList<>();
        followings_1.add(Objects.requireNonNull(daoUsers.getById("id4").get(), "User 'id4' is null"));
        followings.put("id1", followings_1);

        // Followings de 'Manuel' del Cliente 'ajaleo'
        ArrayList<Usuari> followings_2 = new ArrayList<>();
        followings_2.add(Objects.requireNonNull(daoUsers.getById("id12").get(), "User 'id12' is null"));
        followings.put("id2", followings_2);

        // Followings de 'Marc' del Cliente 'dtomacal'
        ArrayList<Usuari> followings_3 = new ArrayList<>();
        followings_3.add(Objects.requireNonNull(daoUsers.getById("id1").get(), "User 'id1' is null"));
        followings.put("id3", followings_3);

        // Followings de 'Laura' del Cliente 'dtomacal'
        ArrayList<Usuari> followings_4 = new ArrayList<>();
        followings_4.add(Objects.requireNonNull(daoUsers.getById("id12").get(), "User 'id12' is null"));
        followings.put("id4", followings_4);

        // Followings de 'Ignasi' del Cliente 'dtomacal'
        ArrayList<Usuari> followings_6 = new ArrayList<>();
        followings_6.add(Objects.requireNonNull(daoUsers.getById("id1").get(), "User 'id1' is null"));
        followings.put("id6", followings_6);

        // Followings de 'Marc' del Cliente 'chachipistachi'
        ArrayList<Usuari> followings_7 = new ArrayList<>();
        followings_7.add(Objects.requireNonNull(daoUsers.getById("id8").get(), "User 'id8' is null"));
        followings.put("id7", followings_7);

        // Followings de 'Marco' del Cliente 'chachipistachi'
        ArrayList<Usuari> followings_8 = new ArrayList<>();
        followings_8.add(Objects.requireNonNull(daoUsers.getById("id12").get(), "User 'id12' is null"));
        followings.put("id9", followings_8);

        // Followings de 'Ignasi' del Cliente 'chachipistachi'
        ArrayList<Usuari> followings_10 = new ArrayList<>();
        followings_10.add(Objects.requireNonNull(daoUsers.getById("id4").get(), "User 'id4' is null"));
        followings.put("id10", followings_10);

        // Followings de 'Arnau' del Cliente 'chachipistachi'
        ArrayList<Usuari> followings_11 = new ArrayList<>();
        followings_11.add(Objects.requireNonNull(daoUsers.getById("id12").get(), "User 'id12' is null"));
        followings.put("id11", followings_11);

        // Followings de 'Tomía' del Cliente 'ana'
        ArrayList<Usuari> followings_12 = new ArrayList<>();
        followings_12.add(Objects.requireNonNull(daoUsers.getById("id8").get(), "User 'id8' is null"));
        followings.put("id12", followings_12);

        // Followings de 'Liza' del Cliente 'ana'
        ArrayList<Usuari> followings_13 = new ArrayList<>();
        followings_13.add(Objects.requireNonNull(daoUsers.getById("id8").get(), "User 'id8' is null"));
        followings.put("id13", followings_13);
    }

    @Override
    public List<Usuari> getAll() {
        List<Usuari> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Usuari>> entry: followings.entrySet()) {
            List<Usuari> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public Optional<Usuari> getById(String id) {
        for (Map.Entry<String, ArrayList<Usuari>> entry: followings.entrySet()) {
            List<Usuari> values = entry.getValue();
            for (Usuari v:values) if (v.getIdUser().equals(id)) return Optional.of(v);
        }
        return Optional.empty();
    }

    /**
     * Método para añadir un following a un Usuario concreto
     * @param idUser ID del Usuario seguido
     * @param following Usuario que sigue
     * @return true si se añade, false si no.
     */
    @Override
    public boolean addFollowing(String idUser, Usuari following) {
        if (!followings.containsKey(idUser)) followings.put(idUser, new ArrayList<>());
        return followings.get(idUser).add(following);
    }

    @Override
    public Map<String, ArrayList<Usuari>> getMap() {
        return new HashMap<>(followings);
    }

    /**
     * Método para añadir un following a un Usuario concreto
     * @param idUser ID del Usuario seguido
     * @param following Usuario que sigue
     * @return true si se añade, false si no.
     */
    @Override
    public boolean removeFollowing(String idUser, Usuari following) {
        return followings.get(idUser).remove(following);
    }

    /**
     * Método para obtener los followings de un Usuario concreto.
     * @param idUser ID de un Usuario
     * @return lista de followings del Usuario si tiene, null sinó
     */
    @Override
    public List<Usuari> getFollowingsByIdUser(String idUser) {
        if (followings.containsKey(idUser)) return followings.get(idUser);
        return null;
    }

    @Override
    public boolean delete(Usuari usuari){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Usuari usuari){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(final Usuari usuari, String[] params){
        throw new UnsupportedOperationException();
    }

}
