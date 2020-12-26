package ub.edu.model;

import ub.edu.view.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver (Observer observer);
    void notifyObservers();

}
