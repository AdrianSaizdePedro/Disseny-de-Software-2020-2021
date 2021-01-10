package ub.edu.resources.dao;

import ub.edu.model.Preferencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOPreferencia extends DAO<Preferencia> {

    List<Preferencia> getPreferredSeriesByIdUser(String userID);

    Map<String, ArrayList<Preferencia>> getMap();

}
