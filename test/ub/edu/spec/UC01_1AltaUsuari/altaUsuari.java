package ub.edu.spec.UC01_1AltaUsuari;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class altaUsuari {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public boolean isValidUser(String nomClient, String nom){
        return !controlador.existsNameUser(nomClient, nom);
    }

    public boolean canAddUser(String nomClient){
        return controlador.canAddUserToClient(nomClient);
    }

    public String addUser(String nomClient, String nom) {
        return controlador.addUser(nomClient, nom);
    }

}
