package ub.edu.resources.dao;

import ub.edu.model.Visualitzacio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOVisualitzacio extends DAO<Visualitzacio>{

    List<Visualitzacio> getVisualizedSeriesByIdUser(String userID);

    Map<String, ArrayList<Visualitzacio>> getMap();
}
