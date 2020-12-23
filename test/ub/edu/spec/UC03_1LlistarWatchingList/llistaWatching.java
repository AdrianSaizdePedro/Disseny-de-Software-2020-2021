package ub.edu.spec.UC03_1LlistarWatchingList;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class llistaWatching {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public Iterable<String> listWatchingList(String idClient, String idUser) { return controlador.listWatchingList(idClient, idUser); }
}
