package ub.edu.spec.UC07_0ValorarEpisodi;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.ControllerTESTS;

@RunWith(ConcordionRunner.class)
public class ferValoracioEstrella {
    private ControllerTESTS controlador;

    @BeforeExample
    private void init() {
        controlador = new ControllerTESTS();
    }

    public String valorarEpisodiEstrellas(int id, String idClient, String nomUsuari, String idSerie, int idTemporada, int idEpisodi, int estrelles, String data){ return controlador.valorarEpisodiEstrellas(id, idClient, nomUsuari, idSerie, idTemporada, idEpisodi, estrelles, data); }
}
