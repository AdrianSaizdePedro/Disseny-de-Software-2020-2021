package ub.edu.resources.dao.MOCK;

import ub.edu.model.Usuari;
import ub.edu.resources.dao.DAOFollowers;
import ub.edu.resources.dao.DAOUsuari;

import java.util.*;

public class DAOFollowersMOCK implements DAOFollowers {

    /**
     * HashMap (idUser, List<Usuaris>) de Followers de un Usuario
     */
    private final Map<String, ArrayList<Usuari>> followers = new HashMap<>();

    public DAOFollowersMOCK(DAOUsuari daoUsers){

        try {
            // Followers de 'Pol' del Cliente 'ajaleo'
            ArrayList<Usuari> followers_1 = new ArrayList<>();
            followers_1.add(Objects.requireNonNull(daoUsers.getById("id3").get(), "User 'id3' is null"));
            followers_1.add(Objects.requireNonNull(daoUsers.getById("id6").get(), "User 'id6' is null"));
            followers.put("id1", followers_1);

            // Followers de 'Laura' del Cliente 'dtomacal'
            ArrayList<Usuari> followers_2 = new ArrayList<>();
            followers_2.add(Objects.requireNonNull(daoUsers.getById("id1").get(), "User 'id1' is null"));
            followers_2.add(Objects.requireNonNull(daoUsers.getById("id10").get(), "User 'id10' is null"));
            followers.put("id4", followers_2);

            // Followers de 'Laura' del Cliente 'chachipistachi'
            ArrayList<Usuari> followers_3 = new ArrayList<>();
            followers_3.add(Objects.requireNonNull(daoUsers.getById("id7").get(), "User 'id7' is null"));
            followers_3.add(Objects.requireNonNull(daoUsers.getById("id13").get(), "User 'id13' is null"));
            followers_3.add(Objects.requireNonNull(daoUsers.getById("id12").get(), "User 'id12' is null"));
            followers.put("id8", followers_3);

            // Followers de 'Tomía' del Cliente 'ana'
            ArrayList<Usuari> followers_4 = new ArrayList<>();
            followers_4.add(Objects.requireNonNull(daoUsers.getById("id9").get(), "User 'id9' is null"));
            followers_4.add(Objects.requireNonNull(daoUsers.getById("id4").get(), "User 'id4' is null"));
            followers_4.add(Objects.requireNonNull(daoUsers.getById("id11").get(), "User 'id11' is null"));
            followers_4.add(Objects.requireNonNull(daoUsers.getById("id2").get(), "User 'id2' is null"));
            followers.put("id12", followers_4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Usuari> getAll() {
        List<Usuari> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Usuari>> entry: followers.entrySet()) {
            List<Usuari> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public Optional<Usuari> getById(String id) {
        for (Map.Entry<String, ArrayList<Usuari>> entry: followers.entrySet()) {
            List<Usuari> values = entry.getValue();
            for (Usuari v:values) if (v.getIdUser().equals(id)) return Optional.of(v);
        }
        return Optional.empty();
    }

    @Override
    public void addFollower(String idUser, Usuari follower) {
        if (!followers.containsKey(idUser)) followers.put(idUser, new ArrayList<>());
        followers.get(idUser).add(follower);
    }

    @Override
    public Map<String, ArrayList<Usuari>> getMap() {
        return new HashMap<>(followers);
    }

    @Override
    public void removeFollower(String idUser, Usuari follower) {
        followers.get(idUser).remove(follower);
    }

    @Override
    public List<Usuari> getFollowersByIdUser(String idUser) {
        if (followers.containsKey(idUser)) return followers.get(idUser);
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
