package ub.edu.resources.dao.MOCK;

import ub.edu.model.Serie;
import ub.edu.model.Temporada;
import ub.edu.resources.dao.DAOTemporada;

import java.util.*;

public class DAOTemporadaMOCK implements DAOTemporada {
    private final Map<String, ArrayList<Temporada>> listTemporades = new HashMap<>();

    public DAOTemporadaMOCK() {

        // Temporadas para la Serie "Breaking Bad"
        ArrayList<Temporada> temporadas_Breaking_Bad = new ArrayList<>();
        temporadas_Breaking_Bad.add(new Temporada("bbad", 1 , 10));
        temporadas_Breaking_Bad.add(new Temporada("bbad", 2 , 10));
        temporadas_Breaking_Bad.add(new Temporada("bbad", 3 , 10));
        listTemporades.put("Breaking Bad", temporadas_Breaking_Bad);

        // Temporadas para la Serie "Game of Thrones"
        ArrayList<Temporada> temporadas_Game_of_thrones = new ArrayList<>();
        temporadas_Game_of_thrones.add(new Temporada("gofthrones", 1 , 10));
        temporadas_Game_of_thrones.add(new Temporada("gofthrones", 2 , 10));
        temporadas_Game_of_thrones.add(new Temporada("gofthrones", 3 , 10));
        listTemporades.put("Game of Thrones", temporadas_Game_of_thrones);

        // Temporadas para la Serie "Mr Robot"
        ArrayList<Temporada> temporadas_MrRobot = new ArrayList<>();
        temporadas_MrRobot.add(new Temporada("mrobot", 1 , 10));
        listTemporades.put("Mr Robot", temporadas_MrRobot);

    }

    @Override
    public Optional<Temporada> getById(String id) {
        for (Map.Entry<String, ArrayList<Temporada>> entry: listTemporades.entrySet()) {
            List<Temporada> values = entry.getValue();
            for (Temporada t:values) if (t.getIdSerie().equals(id)) return Optional.of(t);
        }
        return Optional.empty();
    }

    @Override
    public List<Temporada> getAll()  {
        List<Temporada> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Temporada>> entry: listTemporades.entrySet()) {
            //String key = entry.getKey();
            List<Temporada> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;

    }

    @Override
    public boolean add(final Temporada temporada)  {
        if (listTemporades.containsKey(temporada.getIdSerie())) {
            ArrayList<Temporada> llista = listTemporades.get(temporada.getIdSerie());
            for (Temporada t:llista) {
                if (t.getIdTemporada() == (temporada.getIdTemporada())) return false;
            }
            llista.add(temporada);
        }
        else {
            ArrayList<Temporada> temporadas_Breaking_Bad = new ArrayList<>();
            temporadas_Breaking_Bad.add(temporada);
            listTemporades.put(temporada.getIdSerie(), temporadas_Breaking_Bad);
        }
        return true;
    }


    // REVISAR
    @Override
    public boolean update(Temporada temporada, String[] params) {
        if (listTemporades.containsKey(temporada.getIdSerie())) {
            ArrayList<Temporada> llista = listTemporades.get(temporada.getIdSerie());
            for (Temporada t : llista) {
                if (t.getIdTemporada() == (temporada.getIdTemporada())) {
                    t.setIdSerie(Objects.requireNonNull(
                            params[0], "Series ID cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Temporada temporada) throws Exception {
        throw new Exception();
    }

    @Override
    public List<Temporada> getTemporadesForSerie(Serie s) {
        if (s == null) {
            throw new ClassCastException();
        }
        if (listTemporades.containsKey(s.getTitol())) {
            return (listTemporades.get(s.getTitol()));
        }
        return null;
    }
}
