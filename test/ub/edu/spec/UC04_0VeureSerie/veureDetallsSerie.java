package ub.edu.spec.UC04_0VeureSerie;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class veureDetallsSerie {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }


    public String mostrarDetallsSerie(String nomSerie){ return controlador.mostrarDetallsSerie(nomSerie); }

}
