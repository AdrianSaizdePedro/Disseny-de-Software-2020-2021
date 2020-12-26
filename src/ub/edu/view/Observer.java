package ub.edu.view;

import ub.edu.model.Subject;

import java.util.List;
import java.util.Map;

public interface Observer {
    void update(List<Map.Entry<String, Double>> listaTopTen);
}
