package ub.edu.model;

import ub.edu.view.RegisterObserver;

public interface RegisterSubject {
    void registerObserver(RegisterObserver observer);
    void removeObserver (RegisterObserver observer);
    void notifyObservers();
}
