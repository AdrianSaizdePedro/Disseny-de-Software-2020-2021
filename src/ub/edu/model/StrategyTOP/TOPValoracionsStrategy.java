package ub.edu.model.StrategyTOP;

import com.vladsch.flexmark.util.collection.MapEntry;
import ub.edu.model.Valoracions.EstrellasValoracio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * STRATEGY CONCRETA per llistar les TOP Valoracions:
 * Donat un Iterable d'entrades de valoracions de series, calcula la mitja de valoracions de la sèrie i
 * retorna un Iterable d'entrades de la serie i la valoració mitjana.
 *
 * Necessari per implementar el patró Observer des de la Vista, classe UBFLIXParty (refreshTopValoracions()).
 */
public class TOPValoracionsStrategy extends
        IterableStrategy<Map.Entry<String, ArrayList<EstrellasValoracio>>, Map.Entry<String, Double>> {

    public TOPValoracionsStrategy(Iterable<Map.Entry<String, ArrayList<EstrellasValoracio>>> iterable) {
        super(iterable, Collections.reverseOrder(Map.Entry.comparingByValue()));
    }

    @Override
    protected Map.Entry<String, Double> transform(Map.Entry<String, ArrayList<EstrellasValoracio>> valoracionsSerie) {
        double valoracioMitjana = 0;
        for(EstrellasValoracio e: valoracionsSerie.getValue())
            valoracioMitjana += e.getEstrellas();
        valoracioMitjana /= valoracionsSerie.getValue().size();
        return new MapEntry<>(valoracionsSerie.getKey(), valoracioMitjana);
    }
}
