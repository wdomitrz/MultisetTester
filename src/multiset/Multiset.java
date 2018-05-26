package multiset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Multiset gdzie wszystkich elementów w sumie jest nie więcej niż
 * Integer.MAX_VALUE
 */
public class Multiset<E> implements Collection<E> {

    private final Map<E, Integer> elements;
    private int size;

    public Multiset() {
        this.elements = new HashMap<>();
        this.size = 0;
    }

    public Set<Map.Entry<E, Integer>> setOfElementsAndCounters() {
        return elements.entrySet();
    }

    public int count(Object o) {
        if (!elements.containsKey(o)) {
            return 0;
        }
        return elements.get(o);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return elements.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MultisetIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        List<Object> outTab = new ArrayList<>();
        for (Map.Entry<E, Integer> elemAndCounter : setOfElementsAndCounters()) {
            for (int i = 0; i < elemAndCounter.getValue(); i++) {
                outTab.add(elemAndCounter.getKey());
            }
        }
        return outTab.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        List<T> outTab = new ArrayList<>(Arrays.asList(a));
        for (Map.Entry<E, Integer> elemAndCounter : setOfElementsAndCounters()) {
            for (int i = 0; i < elemAndCounter.getValue(); i++) {
                outTab.add((T) elemAndCounter.getKey());
            }
        }
        a = outTab.toArray(a);
        return a;
    }

    @Override
    public boolean add(E e) {
        return add(e, 1);
    }

    /*
     * Dodaje podaną liczbę danego elementu
     */
    private boolean add(E e, int counter) {
        if (elements.containsKey(e)) {
            elements.replace(e, elements.get(e) + counter);
        } else {
            elements.put(e, counter);
        }
        size += counter;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return remove(o, 1);
    }

    /*
     * Ususwa podaną liczbę danego elementu
     */
    private boolean remove(Object o, int counter) {
        if (!elements.containsKey(o)) {
            // Jeśli taki element nie istnieje, to go nie usuwamy
            return false;
        }
        // Liczba elementów
        int numberOfO = elements.get(o);
        if (numberOfO <= counter) {
            size -= numberOfO;
            elements.remove(o);
        } else {
            size -= counter;
            elements.replace((E) o, numberOfO - counter);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!elements.containsKey(o)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsAll(Multiset<?> c) {
        for (Map.Entry<?, Integer> elemAndCounter : c.setOfElementsAndCounters()) {
            if (this.count(elemAndCounter.getKey()) < elemAndCounter.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E e : c) {
            add(e);
        }
        return true;
    }

    public boolean addAll(Multiset<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (Map.Entry<? extends E, Integer> elemAndCounter : c.setOfElementsAndCounters()) {
            add(elemAndCounter.getKey(), elemAndCounter.getValue());
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean edited = false;
        for (Object o : c) {
            if (remove(o)) {
                edited = true;
            }
        }
        return edited;
    }

    public boolean removeAll(Multiset<?> c) {
        boolean edited = false;
        for (Map.Entry<?, Integer> elemAndCounter : c.setOfElementsAndCounters()) {
            if (remove(elemAndCounter.getKey(), elemAndCounter.getValue())) {
                edited = true;
            }
        }
        return edited;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<Map.Entry<Object, Integer>> toDelete = new ArrayList<>();
        for (Map.Entry<E, Integer> elemAndCounter : setOfElementsAndCounters()) {
            if (!c.contains(elemAndCounter.getKey())) {
                toDelete.add((Map.Entry<Object, Integer>) elemAndCounter);
            }
        }
        for (Map.Entry<Object, Integer> elemAndCounter : toDelete) {
            remove(elemAndCounter.getKey(), elemAndCounter.getValue());
        }
        return !toDelete.isEmpty();
    }

    public boolean retainAll(Multiset<?> c) {
        List<Map.Entry<Object, Integer>> toDelete = new ArrayList<>();
        for (Map.Entry<E, Integer> elemAndCounter : setOfElementsAndCounters()) {
            if (c.count(elemAndCounter.getKey()) < elemAndCounter.getValue()) {
                toDelete.add((Map.Entry<Object, Integer>) elemAndCounter);
            }
        }
        for (Map.Entry<Object, Integer> elemAndCounter : toDelete) {
            remove(elemAndCounter.getKey(), elemAndCounter.getValue() - c.count(elemAndCounter.getKey()));
        }
        return !toDelete.isEmpty();
    }

    @Override
    public void clear() {
        elements.clear();
        size = 0;
    }
}
