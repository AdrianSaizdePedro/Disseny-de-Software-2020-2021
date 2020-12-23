package ub.edu.spec.UC09_0VeurePerfil;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class veurePerfil {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public String veurePerfil(String nomClient, String nomUsuari){ return controlador.veurePerfil(nomClient, nomUsuari); }

}