package ub.edu.resources.dao.MOCK;

import ub.edu.model.Valoracions.CorValoracio;
import ub.edu.resources.dao.DAOValoracioCor;

import java.util.*;

public class DAOValoracioCorMOCK implements DAOValoracioCor {
    private final Map<String, ArrayList<CorValoracio>> listValoracions = new HashMap<>();

    public DAOValoracioCorMOCK() {
        // Valoraciones con Corazon del Usuario 'id1', del Cliente 'ajaleo'
        ArrayList<CorValoracio> valoracio_1 = new ArrayList<>();
        valoracio_1.add(new CorValoracio(1, "ajaleo", "id1", "Breaking Bad", 1,  1,"13.11.2017"));
        valoracio_1.add(new CorValoracio(2, "ajaleo", "id1", "Breaking Bad", 1, 2,"13.11.2017"));
        valoracio_1.add(new CorValoracio(3, "ajaleo", "id1", "Game of Thrones", 1, 1,"17.12.2019"));
        valoracio_1.add(new CorValoracio(4, "ajaleo", "id1", "Game of Thrones", 2, 1,"20.02.2020"));
        listValoracions.put("id1", valoracio_1);

        // Valoraciones con Corazon del Usuario 'id2', del Cliente 'ajaleo'
        ArrayList<CorValoracio> valoracio_2 = new ArrayList<>();
        valoracio_2.add(new CorValoracio(1, "ajaleo", "id2", "Breaking Bad", 1, 1,"20.11.2017"));
        valoracio_2.add(new CorValoracio(2, "ajaleo", "id2", "Breaking Bad", 1, 2,"20.11.2017"));
        valoracio_2.add(new CorValoracio(3, "ajaleo", "id2", "Game of Thrones", 1, 1,"20.12.2019"));
        valoracio_2.add(new CorValoracio(4, "ajaleo", "id2", "Game of Thrones", 2, 1,"20.02.2020"));
        listValoracions.put("id2", valoracio_2);

        // Valoraciones con Corazon del Usuario 'id4', del Cliente 'dtomacal'
        ArrayList<CorValoracio> valoracio_3 = new ArrayList<>();
        valoracio_3.add(new CorValoracio(1, "dtomacal", "id4", "Breaking Bad", 1, 1,"11.04.2222"));
        valoracio_3.add(new CorValoracio(2, "dtomacal", "id4", "Game of Thrones", 1, 1,"11.04.2222"));
        valoracio_3.add(new CorValoracio(3, "dtomacal", "id4", "Game of Thrones", 2, 1,"11.04.2222"));
        listValoracions.put("id4", valoracio_3);

        // Valoraciones con Corazon del Usuario 'id7', del Cliente 'chachipistachi'
        ArrayList<CorValoracio> valoracio_4 = new ArrayList<>();
        valoracio_4.add(new CorValoracio(1, "chachipistachi", "id7", "Breaking Bad", 1, 1,"17.04.2002"));
        valoracio_4.add(new CorValoracio(2, "chachipistachi", "id7", "Game of Thrones", 1, 1,"17.04.2002"));
        valoracio_4.add(new CorValoracio(3, "chachipistachi", "id7", "Game of Thrones", 2, 1,"17.04.2002"));
        listValoracions.put("id7", valoracio_4);

    }


    @Override
    public Optional<CorValoracio> getById(String id) {
        for (Map.Entry<String, ArrayList<CorValoracio>> entry: listValoracions.entrySet()) {
            List<CorValoracio> values = entry.getValue();
            for (CorValoracio v : values) if (v.getIdUsuari().equals(id)) return Optional.of(v);
        }
        return Optional.empty();
    }

    @Override
    public List<CorValoracio> getAll() {
        List<CorValoracio> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<CorValoracio>> entry: listValoracions.entrySet()) {
            List<CorValoracio> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(CorValoracio corValoracio) {
        if (listValoracions.containsKey(corValoracio.getIdUsuari())) {
            ArrayList<CorValoracio> llista = listValoracions.get(corValoracio.getIdUsuari());
            for (CorValoracio v : llista) if (v.isSameValoration(corValoracio)) return false;
            llista.add(corValoracio);
        }
        else {
            ArrayList<CorValoracio> valoracio_5 = new ArrayList<>();
            valoracio_5.add(corValoracio);
            listValoracions.put(corValoracio.getIdUsuari(), valoracio_5);
        }
        return true;
    }

    @Override
    public boolean update(CorValoracio corValoracio, String[] params) { return false; }

    @Override
    public boolean delete(CorValoracio corValoracio) {
        if (listValoracions.containsKey(corValoracio.getIdUsuari())) {
            ArrayList<CorValoracio> llista = listValoracions.get(corValoracio.getIdUsuari());
            for (CorValoracio v : llista) {
                if (v.isSameValoration(corValoracio)){
                    listValoracions.get(corValoracio.getIdUsuari()).remove(corValoracio);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public CorValoracio getByCliUsuSerTempEpi(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi) {
        if (listValoracions.containsKey(idUsuari)){
            ArrayList<CorValoracio> llista = listValoracions.get(idUsuari);
            for (CorValoracio v : llista) {
                if (v.getIdClient().equals(idClient) && v.getIdUsuari().equals(idUsuari) && v.getNomSerie().equals(idSerie)
                    && v.getIdTemporada() == idTemporada && v.getIdEpisodi() == idEpisodi) return v;
            }
        }
        return null;
    }

    @Override
    public void setValoracioCor(String idClient, String idUsuari, String idSerie, int idTemporada, int idEpisodi, CorValoracio corValoracio, String data) {
        if (listValoracions.containsKey(idUsuari)){
            ArrayList<CorValoracio> llista = listValoracions.get(idUsuari);
            for (CorValoracio v : llista) {
                if (v.getIdClient().equals(idClient) && v.getIdUsuari().equals(idUsuari) && v.getNomSerie().equals(idSerie)
                    && v.getIdTemporada() == idTemporada && v.getIdEpisodi() == idEpisodi) delete(v);
                else add(corValoracio);
            }
        }
    }

    @Override
    public List<CorValoracio> getListCorByIdUser(String idUsuari) {
        List<CorValoracio> llistaCompleta = new ArrayList<>();
        for (Map.Entry<String, ArrayList<CorValoracio>> entry: listValoracions.entrySet()) {
            List<CorValoracio> values = entry.getValue();
            for(CorValoracio val: values)if(val.getIdUsuari().equals(idUsuari)) llistaCompleta.add(val);
        }
        return llistaCompleta;
    }

    @Override
    public Map<String, ArrayList<CorValoracio>> getMap() { return new HashMap<>(listValoracions); }

}
