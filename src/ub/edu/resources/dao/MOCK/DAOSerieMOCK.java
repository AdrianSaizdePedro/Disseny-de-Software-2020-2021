package ub.edu.resources.dao.MOCK;

import ub.edu.model.Serie;
import ub.edu.resources.dao.DAOSerie;

import java.util.*;

public class DAOSerieMOCK implements DAOSerie {

    private final Map<String, Serie> idToSerie = new HashMap<>();

    public DAOSerieMOCK() {
            idToSerie.put("Breaking Bad", new Serie("bbad","Breaking Bad", "Tras cumplir 50 años, Walter White...", 1 ));
            idToSerie.put("Game of Thrones", new Serie( "gofthrones","Game of Thrones", "La historia se desarrolla...", 2));
            idToSerie.put("Mr Robot", new Serie( "mrobot","Mr Robot", "Elliot Anderson es un joven...", 3));
            idToSerie.put("Stranger Things", new Serie("sthings", "Stranger Things", "Will has dissapeared. The boys...", 4));
            idToSerie.put("Rick y Morty", new Serie("RyM", "Rick y Morty", "Rick se ha convertido en pepino y ...", 5));
            idToSerie.put("The Mandalorian", new Serie("TMand", "The Mandalorian", "In a far galaxy away...", 6));
    }

    @Override
    public List<Serie> getAll() {
        return new ArrayList<>(idToSerie.values());
    }

    @Override
    public Optional<Serie> getById(String id) {
        return Optional.ofNullable(idToSerie.get(id));
    }

    @Override
    public boolean add(final Serie serie) {
        if (getById(serie.getTitol()).isPresent()) return false;
        idToSerie.put(serie.getTitol(), serie);
        return true;
    }

    @Override
    public boolean update(final Serie serie, String[] params) {
        serie.setTitol(Objects.requireNonNull(
                params[0], "Title cannot be null"));
        serie.setDescripcio(Objects.requireNonNull(
                params[1], "Description cannot be null"));
        return idToSerie.replace(serie.getTitol(), serie) != null;
    }

    @Override
    public boolean delete(final Serie serie) {
        return idToSerie.remove(serie.getTitol()) != null;
    }


}
