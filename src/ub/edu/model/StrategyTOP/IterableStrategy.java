package ub.edu.model.StrategyTOP;

import org.jetbrains.annotations.NotNull;
import java.util.*;

/**
 * STRATEGY PATTERN: patró Strategy per implementar l'algorisme de llistar TOP Valoracions i 
 * llistar TOP Visualitzacions. Aplica una transformació dels elements d'un Iterable de classe E 
 * a classe V i, posteriorment, els ordena mitjançant un comparador injectat per paràmetre.
 * La classe és iterable i accessible amb el nou iterador que proveeix.
 * 
 * A partir d'aquesta classe abstracte i parametritzable, es dissenyaran dues estratègies concretes, 
 * que permetran llistar, des de la classe Registre del model, un llistat ordenat de les sèries amb 
 * millors valoracions i les sèries amb més visualitzacions. 
 *
 * @param <E> Tipus d'elements de l'Iterable a transformar (Iterable<E>).
 * @param <V> Tipus d'elements de l'Iterable transformat (Iterable<V>).
 */

public abstract class IterableStrategy<E, V> implements Iterable<V>{
    private final List<V> heap;

    protected IterableStrategy(Iterable<E> iterable, Comparator<V> comparator){
        heap = new ArrayList<>();
        for (E e: iterable) heap.add(transform(e));
        heap.sort(comparator);
    }

    protected abstract V transform(E e);

    @NotNull
    @Override
    public Iterator<V> iterator() {
        return heap.iterator();
    }
}
