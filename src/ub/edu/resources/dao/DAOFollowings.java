package ub.edu.resources.dao;

import ub.edu.model.Usuari;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAOFollowings extends DAO<Usuari> {

    List<Usuari> getFollowingsByIdUser(String idUser);

    void removeFollowing(String idUser, Usuari following);

    void addFollowing(String idUser, Usuari following);

    Map<String, ArrayList<Usuari>> getMap();
}
