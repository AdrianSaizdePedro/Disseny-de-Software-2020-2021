package ub.edu.spec.UC09_0VeurePerfil;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class llistaFollowings {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public Iterable<String> llistarFollowings(String nomClient, String nomUsuari){ return controlador.llistarFollowings(nomClient, nomUsuari); }

}
