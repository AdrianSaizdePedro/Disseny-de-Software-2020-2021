package ub.edu.spec.UC02_1LoginUsuari;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class login_usuari {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public boolean isValidLoginUser(String username, String password) { return controlador.validateLoginUser(username, password); }
}
