package ub.edu.model.StrategyTOP;

import com.vladsch.flexmark.util.collection.MapEntry;
import ub.edu.model.Visualitzacio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * STRATEGY CONCRETA per llistar les TOP Visualitzacions:
 * Donat un Iterable d'entrades de visualitzacions de series, calcula el total de visualitzacions completes
 * de la sèrie i retorna un Iterable d'entrades de la serie amb les visualitzacions totals, ordenades segons el TOP.
 *
 * Necessari per implementar el patró Observer des de la Vista, classe UBFLIXParty (refreshTopVisualitzacions()).
 */
public class TOPVisualitzacionsStrategy extends
        IterableStrategy<Map.Entry<String, ArrayList<Visualitzacio>>, Map.Entry<String, Integer>> {

    public TOPVisualitzacionsStrategy(Iterable<Map.Entry<String, ArrayList<Visualitzacio>>> iterable) {
        super(iterable, Collections.reverseOrder(Map.Entry.comparingByValue()));
    }

    @Override
    protected Map.Entry<String, Integer> transform(Map.Entry<String, ArrayList<Visualitzacio>> visualitzacionsSerie) {
        int visualitzacions = 0;
        for(Visualitzacio repr: visualitzacionsSerie.getValue())
            if (repr.getEstat().equals("Watched")) visualitzacions += 1;
        return new MapEntry<>(visualitzacionsSerie.getKey(), visualitzacions);
    }
}
