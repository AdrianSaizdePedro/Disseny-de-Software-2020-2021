package ub.edu.spec.UC10_0FerFollow;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;


@RunWith(ConcordionRunner.class)
public class ferUnfollow {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() { controlador = new ControllerTESTS(); }

    public String ferUnfollow(String userUnfollower, String userUnfollowed)  { return controlador.ferUnfollow(userUnfollower, userUnfollowed);}

}

