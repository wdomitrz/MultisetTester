package multiset;

import java.util.Iterator;
import java.util.Map;

// Testowane wraz z metodą iterate() z Multiset
public class MultisetIterator<E> implements Iterator<E> {

    int currentElemCounter;

    Map.Entry<E, Integer> currentElem;

    Iterator<Map.Entry<E, Integer>> toIterate;

    public MultisetIterator(Multiset<E> setToIterate) {
        this.toIterate = setToIterate.setOfElementsAndCounters().iterator();
        this.currentElemCounter = 0;
        this.currentElem = null;
    }

    @Override
    public boolean hasNext() {
        // Jeśli currentElem to null, to count będzie nadal zerem
        return currentElemCounter > 0 || toIterate.hasNext();
    }

    @Override
    public E next() {
        if (currentElemCounter <= 0) {
            currentElem = toIterate.next();
            currentElemCounter = currentElem.getValue();
        }
        currentElemCounter--;
        return currentElem.getKey();
    }

}
