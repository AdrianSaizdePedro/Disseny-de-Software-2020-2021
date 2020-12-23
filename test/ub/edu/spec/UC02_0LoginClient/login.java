package ub.edu.spec.UC02_0LoginClient;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class login {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public boolean isValidLogin(String username, String password) { return controlador.validateClient(username, password); }

    public String isValidClient(String username) {
        return controlador.isValidNameClient(username);
    }

}
