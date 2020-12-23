package ub.edu.spec.UC03_0LlistarSeries;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class llistaSeries {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public Iterable<String> listCatalegSeries() {
        return controlador.llistarCatalegSeries();
    }

}

