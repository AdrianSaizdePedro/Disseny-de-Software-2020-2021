package ub.edu.spec.UC01_0AltaClient;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class altaClient {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init(){
        controlador = new ControllerTESTS();
    }

    public boolean isValidPassword(String password){
        return controlador.validatePassword(password);
    }

    public boolean isTakenUsername(String username){ return controlador.isTakenUsername(username); }

    public boolean isValidDni(String dni){
        return controlador.validateDNI(dni);
    }

    public String addClient(String idClient, String psw, String dni, String adress, boolean vip){ return controlador.addClient(idClient,psw, dni,adress, vip); }

}
