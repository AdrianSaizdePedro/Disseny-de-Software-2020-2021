package ub.edu.resources.dao.MOCK;

import ub.edu.model.Artista;
import ub.edu.model.Serie;
import ub.edu.resources.dao.DAOArtista;

import java.util.*;

public class DAOArtistaMOCK implements DAOArtista {
    private final Map<String, ArrayList<Artista>> listArtistes = new HashMap<>();

    public DAOArtistaMOCK() {
        //Artistas para la Serie "Breaking Bad"
        ArrayList<Artista> artistas_1 = new ArrayList<>();
        artistas_1.add(new Artista("Bryan Cranston", "Walter White", "Breaking Bad"));
        artistas_1.add(new Artista("Anna Gunn", "Skyler White", "Breaking Bad"));
        artistas_1.add(new Artista("Aaron Paul", "Jesse Pinkman", "Breaking Bad"));
        listArtistes.put("Breaking Bad", artistas_1);

        //Artistas para la Serie "Game of Thrones"
        ArrayList<Artista> artistas_2 = new ArrayList<>();
        artistas_2.add(new Artista("Maisie Williams", "Arya Stark", "Game of Thrones"));
        artistas_2.add(new Artista("Tyrion Lannister", "Peter Dinklage", "Game of Thrones"));
        artistas_2.add(new Artista("Eddard Stark", "Sean Bean", "Game of Thrones"));
        artistas_2.add(new Artista("Daenerys Targaryen", "Emilia Clarke", "Game of Thrones"));
        listArtistes.put("Game of Thrones", artistas_2);

        //Artistas para la Serie "Friends"
        ArrayList<Artista> artistas_3 = new ArrayList<>();
        artistas_3.add(new Artista("Jennifer Aniston","Rachel Green", "Friends"));
        artistas_3.add(new Artista("Courteney Cox","Monica Geller", "Friends"));
        listArtistes.put("Friends", artistas_3);
    }

    @Override
    public Optional<Artista> getById(String nomPersonatge) {
        for (Map.Entry<String, ArrayList<Artista>> entry: listArtistes.entrySet()) {
            List<Artista> values = entry.getValue();
            for (Artista v:values) {
                if (v.getNom_personatje().equals(nomPersonatge)) return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Artista> getAll() {
        List<Artista> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Artista>> entry: listArtistes.entrySet()){
            List<Artista> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(Artista artista) {
        if (listArtistes.containsKey(artista.getSerieId())) {
            ArrayList<Artista> llista = listArtistes.get(artista.getSerieId());
            for (Artista a:llista) {
                if (a.getNom_personatje().equals(artista.getNom_personatje())) return false;
            }
            llista.add(artista);
        }
        else {
            ArrayList<Artista> artistas_x = new ArrayList<>();
            artistas_x.add(artista);
            listArtistes.put(artista.getSerieId(), artistas_x);
        }
        return true;
    }

    @Override
    public boolean update(Artista artista, String[] params) {
        if (listArtistes.containsKey(artista.getSerieId())) {
            ArrayList<Artista> llista = listArtistes.get(artista.getSerieId());
            for (Artista a : llista) {
                if (a.getNom_actor().equals(artista.getNom_personatje())) {
                    a.setNom_actor(Objects.requireNonNull( params[0], "Name cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Artista artista) throws Exception {
        throw new Exception();
    }

    @Override
    public List<Artista> getArtistesForSerie(Serie serie) {
        if (serie == null) throw new ClassCastException();
        if (listArtistes.containsKey(serie.getTitol())) return (listArtistes.get(serie.getTitol()));

        return null;
    }
}
