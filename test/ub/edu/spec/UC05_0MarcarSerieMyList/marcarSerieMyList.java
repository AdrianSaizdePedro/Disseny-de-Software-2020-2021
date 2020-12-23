package ub.edu.spec.UC05_0MarcarSerieMyList;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class marcarSerieMyList {

    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public String addSerieInMyList(int id, String client, String user, String serie) { return controlador.addSerieToMyList(id, client, user, serie); }

    public String removeSerieMyList(int id, String client, String user, String serie) { return controlador.removeSerieFromMyList(id, client, user, serie); }
}
