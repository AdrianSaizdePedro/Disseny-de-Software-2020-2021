package ub.edu.spec.UC03_2LlistarMyList;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class llistaMyList {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public Iterable<String> listMyList(String idClient, String nameUser) { return controlador.listMyList(idClient, nameUser); }
}
