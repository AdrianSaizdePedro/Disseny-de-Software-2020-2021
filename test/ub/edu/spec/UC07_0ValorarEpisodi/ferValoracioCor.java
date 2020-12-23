package ub.edu.spec.UC07_0ValorarEpisodi;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class ferValoracioCor {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public String valorarEpisodiCor(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, String data){ return controlador.valorarEpisodiCor(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, data); }
}
