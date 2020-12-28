package ub.edu.resources.dao.MOCK;
import ub.edu.model.Client;
import ub.edu.model.Usuari;
import ub.edu.resources.dao.DAOUsuari;

import java.util.*;

public class DAOUsuariMOCK implements DAOUsuari {
    private final Map<String, ArrayList<Usuari>> listUsuaris = new HashMap<>();

    public DAOUsuariMOCK(){
        // Usuaris pel client "ajaleo"
        ArrayList<Usuari> usuaris_1 = new ArrayList<>();
        usuaris_1.add(new Usuari("ajaleo", "Pol", "id1"));
        usuaris_1.add(new Usuari("ajaleo", "Manuel", "id2"));
        listUsuaris.put("ajaleo", usuaris_1);

        // Usuaris pel client "dtomacal"
        ArrayList<Usuari> usuaris_2 = new ArrayList<>();
        usuaris_2.add(new Usuari("dtomacal", "Marc", "id3"));
        usuaris_2.add(new Usuari("dtomacal", "Laura", "id4"));
        usuaris_2.add(new Usuari("dtomacal", "Marco", "id5"));
        usuaris_2.add(new Usuari("dtomacal", "Ignasi", "id6"));
        listUsuaris.put("dtomacal", usuaris_2);

        // Usuaris pel client "chachipistachi"
        ArrayList<Usuari> usuaris_3 = new ArrayList<>();
        usuaris_3.add(new Usuari("chachipistachi", "Marc", "id7"));
        usuaris_3.add(new Usuari("chachipistachi", "Laura", "id8"));
        usuaris_3.add(new Usuari("chachipistachi", "Marco", "id9"));
        usuaris_3.add(new Usuari("chachipistachi", "Ignasi", "id10"));
        usuaris_3.add(new Usuari("chachipistachi", "Arnau", "id11"));
        listUsuaris.put("chachipistachi", usuaris_3);

        // Usuaris pel client "ana"
        ArrayList<Usuari> usuaris_4 = new ArrayList<>();
        usuaris_4.add(new Usuari("ana", "Tomía", "id12"));
        usuaris_4.add(new Usuari("ana", "Liza", "id13"));
        listUsuaris.put("ana", usuaris_4);

        // Usuaris pel client ""
        ArrayList<Usuari> usuaris_5 = new ArrayList<>();
        usuaris_5.add(new Usuari("", "Marc", "id14"));
        usuaris_5.add(new Usuari("", "Laura", "id15"));
        usuaris_5.add(new Usuari("", "Marco", "id16"));
        usuaris_5.add(new Usuari("", "Ignasi", "id17"));
        listUsuaris.put("", usuaris_5);

    }

    @Override
    public List<Usuari> getAll() {
        List<Usuari> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Usuari>> entry: listUsuaris.entrySet()) {
            List<Usuari> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public Optional<Usuari> getById(String id) {
        for (Map.Entry<String, ArrayList<Usuari>> entry: listUsuaris.entrySet()) {
            List<Usuari> values = entry.getValue();
            for (Usuari v:values) {
                if (v.getIdUser().equals(id)) return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean add(Usuari usuari) {
        if (listUsuaris.containsKey(usuari.getNomClient())) {
           ArrayList<Usuari> llista = listUsuaris.get(usuari.getNomClient());
           for (Usuari u:llista) {
               if (u.getIdUser().equals(usuari.getIdUser())) return false;
           }
           llista.add(usuari);
        }
        else {
            ArrayList<Usuari> usuaris_3 = new ArrayList<>();
            usuaris_3.add(usuari);
            listUsuaris.put(usuari.getNomClient(), usuaris_3);
        }
        return true;
    }

    @Override
    public boolean update(final Usuari usuari, String[] params) {
        if (listUsuaris.containsKey(usuari.getNomClient())) {
            ArrayList<Usuari> llista = listUsuaris.get(usuari.getNomClient());
            for (Usuari u : llista) {
                if (u.getIdUser().equals(usuari.getIdUser())) {
                    u.setName(Objects.requireNonNull(
                            params[0], "Name cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Usuari usuari) throws Exception {
        throw new Exception();
    }

    public List<Usuari> getUsuarisForClient(Client c) {
        if (c == null) {
            throw new ClassCastException();
        }
        if (listUsuaris.containsKey(c.getName())) {
            return (listUsuaris.get(c.getName()));
        }
        return null;
    }

}
