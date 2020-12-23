package ub.edu.resources.dao;

import ub.edu.model.Usuari;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOFollowers extends DAO<Usuari> {

    List<Usuari> getFollowersByIdUser(String id);

    boolean removeFollower(String idUser, Usuari follower);

    boolean addFollower(String idUser, Usuari follower);

    Map<String, ArrayList<Usuari>> getMap();

}
