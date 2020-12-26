package ub.edu.resources.dao.MOCK;

import ub.edu.model.Valoracions.EstrellasValoracio;
import ub.edu.resources.dao.DAOValoracioEstrellas;

import java.util.*;

public class DAOValoracioEstrellaMOCK implements DAOValoracioEstrellas {
    private final Map<String, ArrayList<EstrellasValoracio>> listValoracions = new HashMap<>();

    public DAOValoracioEstrellaMOCK(){

        // Valoraciones con Estrellas del Usuario 'id1', del Cliente 'ajaleo'
        ArrayList<EstrellasValoracio> valoracio_1 = new ArrayList<>();
        valoracio_1.add(new EstrellasValoracio(1, "ajaleo", "id1", "bbad", 1, 1, 5,"13.11.2017"));
        valoracio_1.add(new EstrellasValoracio(2, "ajaleo", "id1", "bbad", 1, 2, 3,"13.11.2017"));
        valoracio_1.add(new EstrellasValoracio(3, "ajaleo", "id1", "gofthrones", 1, 1, 5,"17.12.2019"));
        valoracio_1.add(new EstrellasValoracio(4, "ajaleo", "id1", "gofthrones", 2, 1, 5,"20.02.2020"));
        listValoracions.put("id1", valoracio_1);

        // Valoraciones con Estrellas del Usuario 'id2', del Cliente 'ajaleo'
        ArrayList<EstrellasValoracio> valoracio_2 = new ArrayList<>();
        valoracio_2.add(new EstrellasValoracio(1, "ajaleo", "id2", "bbad", 1, 1, 1,"20.11.2017"));
        valoracio_2.add(new EstrellasValoracio(2, "ajaleo", "id2", "bbad", 1, 2, 2,"20.11.2017"));
        valoracio_2.add(new EstrellasValoracio(3, "ajaleo", "id2", "gofthrones", 1, 1, 3,"20.12.2019"));
        valoracio_2.add(new EstrellasValoracio(4, "ajaleo", "id2", "gofthrones", 2, 1, 3,"20.02.2020"));
        listValoracions.put("id2", valoracio_2);

        // Valoraciones con Estrellas del Usuario 'id4', del Cliente 'dtomacal'
        ArrayList<EstrellasValoracio> valoracio_3 = new ArrayList<>();
        valoracio_3.add(new EstrellasValoracio(1, "dtomacal", "id4", "bbad", 1, 1, 1,"11.04.2222"));
        valoracio_3.add(new EstrellasValoracio(2, "dtomacal", "id4", "gofthrones", 1, 1, 3,"11.04.2222"));
        valoracio_3.add(new EstrellasValoracio(3, "dtomacal", "id4", "gofthrones", 2, 1, 3,"11.04.2222"));
        listValoracions.put("id4", valoracio_3);

        // Valoraciones con Estrellas del Usuario 'id7', del Cliente 'chachipistachi'
        ArrayList<EstrellasValoracio> valoracio_4 = new ArrayList<>();
        valoracio_4.add(new EstrellasValoracio(1, "chachipistachi", "id7", "bbad", 1, 1, 1,"17.04.2002"));
        valoracio_4.add(new EstrellasValoracio(2, "chachipistachi", "id7", "gofthrones", 1, 1, 3,"17.04.2002"));
        valoracio_4.add(new EstrellasValoracio(3, "chachipistachi", "id7", "gofthrones", 2, 1, 3,"17.04.2002"));
        listValoracions.put("id7", valoracio_4);

    }

    @Override
    public Optional<EstrellasValoracio> getById(String id) {
        for (Map.Entry<String, ArrayList<EstrellasValoracio>> entry: listValoracions.entrySet()) {
            List<EstrellasValoracio> values = entry.getValue();
            for (EstrellasValoracio v : values) if (v.getIdUsuari().equals(id)) return Optional.of(v);
        }
        return Optional.empty();
    }

    @Override
    public List<EstrellasValoracio> getAll() {
        List<EstrellasValoracio> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<EstrellasValoracio>> entry: listValoracions.entrySet()) {
            List<EstrellasValoracio> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(EstrellasValoracio valoracio) {
        if (listValoracions.containsKey(valoracio.getIdUsuari())) {
            ArrayList<EstrellasValoracio> llista = listValoracions.get(valoracio.getIdUsuari());
            for (EstrellasValoracio v : llista) if (v.isSameValoration(valoracio)) return false;
            llista.add(valoracio);
        }
        else {
            ArrayList<EstrellasValoracio> valoracio_5 = new ArrayList<>();
            valoracio_5.add(valoracio);
            listValoracions.put(valoracio.getIdUsuari(), valoracio_5);
        }
        return true;
    }

    @Override
    public boolean update(EstrellasValoracio valoracio, String[] params) { return false; }

    @Override
    public boolean delete(EstrellasValoracio valoracio) {
        if (listValoracions.containsKey(valoracio.getIdUsuari())) {
            ArrayList<EstrellasValoracio> llista = listValoracions.get(valoracio.getIdUsuari());
            for (EstrellasValoracio v : llista) {
                if (v.isSameValoration(valoracio) && v.getEstrellas() == valoracio.getEstrellas()){
                    listValoracions.get(valoracio.getIdUsuari()).remove(valoracio);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public EstrellasValoracio getByCliUsuSerTempEpi(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi) {
        if (listValoracions.containsKey(idUsuari)) {
            ArrayList<EstrellasValoracio> llista = listValoracions.get(idUsuari);
            for (EstrellasValoracio v : llista) {
                if (v.getIdClient().equals(idClient) && v.getIdUsuari().equals(idUsuari) && v.getIdSerie().equals(idSerie)
                    && v.getIdTemporada() == idTemporada && v.getIdEpisodi() == idEpisodi) return v;
            }
        }
        return null;
    }

    @Override
    public void setValoracioEstrella(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, EstrellasValoracio estrellasValoracio, String data) {
        if (listValoracions.containsKey(idUsuari)){
            ArrayList<EstrellasValoracio> llista = listValoracions.get(idUsuari);
            for (EstrellasValoracio v : llista) {
                if (v.getIdClient().equals(idClient) && v.getIdUsuari().equals(idUsuari) && v.getIdSerie().equals(idSerie) && v.getIdTemporada() == idTemporada
                    && v.getIdEpisodi() == idEpisodi) v.updateRating(estrellasValoracio.getEstrellas(), estrellasValoracio.getData());
                else add(estrellasValoracio);
            }
        }
    }

    @Override
    public List<EstrellasValoracio> getListEstrellasByIdUser(String idUsuari) {
        List<EstrellasValoracio> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<EstrellasValoracio>> entry: listValoracions.entrySet()) {
            List<EstrellasValoracio> values = entry.getValue();
            for(EstrellasValoracio val : values)if(val.getIdUsuari().equals(idUsuari)) llistaCompleta.add(val);
        }
        return llistaCompleta;
    }

    @Override
    public Map<String, ArrayList<EstrellasValoracio>> getMap() {
        return new HashMap<>(listValoracions);
    }

}
