package ub.edu.view;

import ub.edu.model.StrategyTOP.IterableStrategy;

public interface RegisterObserver {
    void refreshTopValoracions(IterableStrategy listaTopTen);
    void refreshTopVisualitzacions(IterableStrategy listaTopTen);
}
