/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author witek
 */
public class MultisetTest {

    private Multiset<Integer> instance1;
    private Multiset<String> instance2;
    private Multiset<Object> instance3;

    private void initializeInstances() {
        instance1 = new Multiset<>();
        instance2 = new Multiset<>();
        instance3 = new Multiset<>();
        for (Integer i = 0; i < 4; i++) {
            for (Integer j = 0; j < i; j++) {
                instance1.add(i);
            }
        }

        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 6; j++) {
                instance2.add(j * i + "a");
            }
        }

        instance3.add("zxczxcxzc");
        instance3.add(false);
        instance3.add(.0);
        instance3.add(0);
        instance3.add(new HashSet<List<Collection>>());
    }

    /**
     * Test of setOfElementsAndCounters method, of class Multiset.
     */
    @Test
    public void testSetOfElementsAndCounters() {
        System.out.println("setOfElementsAndCounters");

        initializeInstances();

        assertEquals("[1=1, 2=2, 3=3]", instance1.setOfElementsAndCounters().toString());

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals("[1a=1, 0a=8, 10a=1, 8a=1, 6a=1, 5a=1, 4a=2, 3a=1, 2a=2]", instance2.setOfElementsAndCounters().toString());
        assertEquals("[0.0=1, 0=1, []=1, false=1, zxczxcxzc=1]", instance3.setOfElementsAndCounters().toString());
    }

    /**
     * Test of count method, of class Multiset.
     */
    @Test
    public void testCount() {
        System.out.println("count");

        initializeInstances();

        assertEquals(2, instance1.count(2));
        assertEquals(0, instance1.count("7"));
        assertEquals(0, instance1.count(5));
        assertEquals(0, instance1.count(new HashSet<Integer>()));
        assertEquals(1, instance1.count(1));

        assertEquals(0, instance2.count("zxc"));
        assertEquals(8, instance2.count("0a"));
        assertEquals(0, instance2.count(1));
        assertEquals(0, instance2.count("9a"));

        assertEquals(0, instance3.count(true));
        assertEquals(1, instance3.count(0.));
        assertEquals(1, instance3.count(0));
        assertEquals(1, instance3.count(new HashSet<List<Collection>>()));
        assertEquals(1, instance3.count(new HashSet<Integer>()));

    }

    /**
     * Test of size method, of class Multiset.
     */
    @Test
    public void testSize() {
        System.out.println("size");

        initializeInstances();
        Multiset<Iterable> instance4 = new Multiset<>();

        assertEquals(6, instance1.size());
        assertEquals(0, instance4.size());
        assertEquals(5, instance3.size());
        assertEquals(3 * 6, instance2.size());
    }

    /**
     * Test of isEmpty method, of class Multiset.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");

        initializeInstances();
        Multiset<Iterable> instance4 = new Multiset<>();

        assertEquals(false, instance2.isEmpty());
        assertEquals(true, instance4.isEmpty());
        assertEquals(false, instance3.isEmpty());
        assertEquals(false, instance1.isEmpty());
    }

    /**
     * Test of contains method, of class Multiset.
     */
    @Test
    public void testContains() {
        System.out.println("contains");

        initializeInstances();
        Multiset<Iterable> instance4 = new Multiset<>();

        assertEquals(false, instance2.contains(3));
        assertEquals(false, instance4.contains(3));
        assertEquals(false, instance3.contains(3));
        assertEquals(true, instance1.contains(3));

        assertEquals(true, instance1.contains(3));

        assertEquals(false, instance1.contains(5));

        assertEquals(true, instance2.contains("3a"));
        assertEquals(false, instance4.contains("3a"));
        assertEquals(false, instance3.contains("3a"));
        assertEquals(false, instance1.contains("3a"));

        assertEquals(false, instance2.contains("9a"));

        assertEquals(false, instance2.contains(new HashSet<List<Collection>>()));
        assertEquals(false, instance4.contains(new HashSet<List<Collection>>()));
        assertEquals(true, instance3.contains(new HashSet<List<Collection>>()));
        assertEquals(false, instance1.contains(new HashSet<List<Collection>>()));

        assertEquals(false, instance3.contains(new ArrayList<Boolean>()));

    }

    /**
     * Test of iterator method, of class Multiset.
     */
    @Test
    public void testIterator() {
        // Sortuję, żeby łatwiej porównywać
        System.out.println("iterator");

        initializeInstances();
        Multiset<Iterable> instance4 = new Multiset<>();

        List<Integer> out1 = new ArrayList<>();
        List<Integer> expectedOut1 = new ArrayList<>();
        for (Integer e : instance1) {
            out1.add(e);
        }
        expectedOut1.add(1);
        expectedOut1.add(2);
        expectedOut1.add(2);
        expectedOut1.add(3);
        expectedOut1.add(3);
        expectedOut1.add(3);
        Collections.sort(out1);
        Collections.sort(expectedOut1);

        assertEquals(out1, expectedOut1);

        List<String> out2 = new ArrayList<>();
        List<String> expectedOut2 = new ArrayList<>();
        for (String e : instance2) {
            out2.add(e);
        }
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 6; j++) {
                expectedOut2.add(j * i + "a");
            }
        }
        Collections.sort(out2);
        Collections.sort(expectedOut2);

        assertEquals(out2, expectedOut2);

        List<Object> out3 = new ArrayList<>();
        List<Object> expectedOut3 = new ArrayList<>();
        for (Object e : instance3) {
            out3.add(e);
        }
        expectedOut3.add(.0);
        expectedOut3.add(0);
        expectedOut3.add(new HashSet<List<Collection>>());
        expectedOut3.add(false);
        expectedOut3.add("zxczxcxzc");

        assertEquals(out3, expectedOut3);

    }

    /**
     * Test of toArray method, of class Multiset.
     */
    @Test
    public void testToArray_0args() {
        System.out.println("toArray");
        Object[] out, expected;

        initializeInstances();
        Multiset<Iterable> instance4 = new Multiset<>();

        List<Integer> expectedOut1 = new ArrayList<>();
        expectedOut1.add(1);
        expectedOut1.add(2);
        expectedOut1.add(2);
        expectedOut1.add(3);
        expectedOut1.add(3);
        expectedOut1.add(3);

        out = instance1.toArray();
        Arrays.sort(out);
        expected = expectedOut1.toArray();
        Arrays.sort(expected);
        Assert.assertArrayEquals(out, expected);

        List<String> expectedOut2 = new ArrayList<>();
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 6; j++) {
                expectedOut2.add(j * i + "a");
            }
        }
        out = instance2.toArray();
        Arrays.sort(out);
        expected = expectedOut2.toArray();
        Arrays.sort(expected);

        Assert.assertArrayEquals(out, expected);

        List<Object> out3 = new ArrayList<>();
        List<Object> expectedOut3 = new ArrayList<>();
        for (Object e : instance3) {
            out3.add(e);
        }
        expectedOut3.add(.0);
        expectedOut3.add(0);
        expectedOut3.add(new HashSet<List<Collection>>());
        expectedOut3.add(false);
        expectedOut3.add("zxczxcxzc");

        out = instance3.toArray();
        expected = expectedOut3.toArray();

        Assert.assertArrayEquals(out, expected);
    }

    /**
     * Test of toArray method, of class Multiset.
     */
    @Test
    public void testToArray_GenericType() {
        System.out.println("toArray");

        initializeInstances();
        Multiset<Iterable> instance4 = new Multiset<>();

        Integer[] out1, expected1;

        out1 = new Integer[0];
        expected1 = new Integer[0];

        List<Integer> expectedOut1 = new ArrayList<>();
        expectedOut1.add(1);
        expectedOut1.add(2);
        expectedOut1.add(2);
        expectedOut1.add(3);
        expectedOut1.add(3);
        expectedOut1.add(3);

        out1 = instance1.toArray(out1);
        Arrays.sort(out1);
        expected1 = expectedOut1.toArray(expected1);
        Arrays.sort(expected1);
        Assert.assertArrayEquals(out1, expected1);

        List<String> expectedOut2 = new ArrayList<>();
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 6; j++) {
                expectedOut2.add(j * i + "a");
            }
        }
        String[] out2, expected2;

        out2 = new String[0];
        expected2 = new String[0];

        out2 = instance2.toArray(out2);
        Arrays.sort(out2);
        expected2 = expectedOut2.toArray(expected2);
        Arrays.sort(expected2);

        Assert.assertArrayEquals(out2, expected2);

    }

    /**
     * Test of add method, of class Multiset.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        initializeInstances();

        assertEquals(true, instance1.contains(3));
        assertEquals(true, instance1.contains(3));
        assertEquals(true, instance1.contains(2));

        assertEquals(true, instance2.contains("3a"));
        assertEquals(true, instance2.contains("0a"));
        assertEquals(true, instance2.contains("8a"));

        assertEquals(true, instance3.contains(.0));
        assertEquals(true, instance3.contains(false));
        assertEquals(true, instance3.contains("zxczxcxzc"));
        assertEquals(true, instance3.contains(new HashSet<List<Collection>>()));

    }

    /**
     * Test of remove method, of class Multiset.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        initializeInstances();

        assertEquals(true, instance1.remove(1));
        assertEquals(false, instance1.remove(1));
        assertEquals(true, instance1.remove(2));

        assertEquals(true, instance2.remove("3a"));
        assertEquals(true, instance2.remove("0a"));
        assertEquals(false, instance2.remove("9a"));

        assertEquals(true, instance3.remove(.0));
        assertEquals(true, instance3.remove(false));
        assertEquals(false, instance3.remove(.0));
        assertEquals(false, instance3.remove(.0));

    }

    /**
     * Test of containsAll method, of class Multiset.
     */
    @Test
    public void testContainsAll_Collection() {
        System.out.println("containsAll");

        initializeInstances();

        List<Integer> test1 = new ArrayList<>();
        test1.add(1);
        test1.add(3);
        test1.add(3);

        assertEquals(true, instance1.containsAll(test1));

        List<String> test2 = new ArrayList<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(false, instance2.containsAll(test2));

        List<Integer> test3 = new ArrayList<>();
        test3.add(1);
        test3.add(3);
        test3.add(3);

        assertEquals(false, instance3.containsAll(test3));

    }

    /**
     * Test of containsAll method, of class Multiset.
     */
    @Test
    public void testContainsAll_Multiset() {
        System.out.println("containsAll");
        initializeInstances();

        Multiset<Integer> test1 = new Multiset<>();
        test1.add(2);
        test1.add(2);
        test1.add(2);

        assertEquals(false, instance1.containsAll(test1));

        Multiset<Object> test2 = new Multiset<>();
        test2.add("0a");
        test2.add("0a");
        test2.add("0a");

        assertEquals(true, instance2.containsAll(test2));

        Multiset<Collection<?>> test3 = new Multiset<>();
        test3.add(new HashSet<List<Collection>>());
        test3.add(new HashSet<List<Collection>>());
        test3.add(new HashSet<List<Collection>>());

        assertEquals(false, instance3.containsAll(test3));
    }

    /**
     * Test of addAll method, of class Multiset.
     */
    @Test
    public void testAddAll_Collection() {
        System.out.println("addAll");
        initializeInstances();

        List<Integer> test1 = new ArrayList<>();
        test1.add(1);
        test1.add(3);
        test1.add(3);

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals(true, instance1.addAll(test1));
        assertEquals("[1=2, 2=2, 3=5]", instance1.setOfElementsAndCounters().toString());

        List<String> test2 = new ArrayList<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(true, instance2.addAll(test2));
        assertEquals("[1a=1, 0a=9, 10a=1, 9a=1, 8a=2, 6a=1, 5a=1, 4a=2, 3a=1, 2a=2]", instance2.setOfElementsAndCounters().toString());

        List<Integer> test3 = new ArrayList<>();
        test3.add(1);
        test3.add(3);
        test3.add(3);

        assertEquals(true, instance3.addAll(test3));
        assertEquals("[0.0=1, 0=1, []=1, 1=1, 3=2, false=1, zxczxcxzc=1]", instance3.setOfElementsAndCounters().toString());

    }

    /**
     * Test of addAll method, of class Multiset.
     */
    @Test
    public void testAddAll_Multiset() {
        System.out.println("addAll");
        initializeInstances();

        Multiset<Integer> test1 = new Multiset<>();
        test1.add(1);
        test1.add(3);
        test1.add(3);

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals(true, instance1.addAll(test1));
        assertEquals("[1=2, 2=2, 3=5]", instance1.setOfElementsAndCounters().toString());

        Multiset<String> test2 = new Multiset<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(true, instance2.addAll(test2));
        assertEquals("[1a=1, 0a=9, 10a=1, 9a=1, 8a=2, 6a=1, 5a=1, 4a=2, 3a=1, 2a=2]", instance2.setOfElementsAndCounters().toString());

        Multiset<Integer> test3 = new Multiset<>();
        test3.add(1);
        test3.add(3);
        test3.add(3);

        assertEquals(true, instance3.addAll(test3));
        assertEquals("[0.0=1, 0=1, []=1, 1=1, 3=2, false=1, zxczxcxzc=1]", instance3.setOfElementsAndCounters().toString());

    }

    /**
     * Test of removeAll method, of class Multiset.
     */
    @Test
    public void testRemoveAll_Collection() {
        System.out.println("removeAll");
        initializeInstances();

        List<Integer> test1 = new ArrayList<>();
        test1.add(1);
        test1.add(3);
        test1.add(3);

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals(true, instance1.removeAll(test1));
        assertEquals("[2=2, 3=1]", instance1.setOfElementsAndCounters().toString());

        List<String> test2 = new ArrayList<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(true, instance2.removeAll(test2));
        assertEquals("[1a=1, 0a=7, 10a=1, 6a=1, 5a=1, 4a=2, 3a=1, 2a=2]", instance2.setOfElementsAndCounters().toString());

        List<Integer> test3 = new ArrayList<>();
        test3.add(1);
        test3.add(3);
        test3.add(3);

        assertEquals(false, instance3.removeAll(test3));
        assertEquals("[0.0=1, 0=1, []=1, false=1, zxczxcxzc=1]", instance3.setOfElementsAndCounters().toString());
    }

    /**
     * Test of removeAll method, of class Multiset.
     */
    @Test
    public void testRemoveAll_Multiset() {
        System.out.println("removeAll");
        initializeInstances();

        Multiset<Object> test1 = new Multiset<>();
        test1.add(1);
        test1.add(3);
        test1.add(3);

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals(true, instance1.removeAll(test1));
        assertEquals("[2=2, 3=1]", instance1.setOfElementsAndCounters().toString());

        Multiset<String> test2 = new Multiset<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(true, instance2.removeAll(test2));
        assertEquals("[1a=1, 0a=7, 10a=1, 6a=1, 5a=1, 4a=2, 3a=1, 2a=2]", instance2.setOfElementsAndCounters().toString());

        Multiset<Integer> test3 = new Multiset<>();
        test3.add(1);
        test3.add(3);
        test3.add(3);

        assertEquals(false, instance3.removeAll(test3));
        assertEquals("[0.0=1, 0=1, []=1, false=1, zxczxcxzc=1]", instance3.setOfElementsAndCounters().toString());
    }

    /**
     * Test of retainAll method, of class Multiset.
     */
    @Test
    public void testRetainAll_Collection() {
        System.out.println("retainAll");
        initializeInstances();

        List<Number> test1 = new ArrayList<>();
        test1.add(1);
        test1.add(2);
        test1.add(3);

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals(false, instance1.retainAll(test1));
        assertEquals("[1=1, 2=2, 3=3]", instance1.setOfElementsAndCounters().toString());

        List<String> test2 = new ArrayList<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(true, instance2.retainAll(test2));
        assertEquals("[0a=8, 8a=1]", instance2.setOfElementsAndCounters().toString());

        List<Integer> test3 = new ArrayList<>();
        test3.add(1);
        test3.add(3);
        test3.add(3);

        assertEquals(true, instance3.retainAll(test3));
        assertEquals("[]", instance3.setOfElementsAndCounters().toString());
    }

    /**
     * Test of retainAll method, of class Multiset.
     */
    @Test
    public void testRetainAll_Multiset() {
        System.out.println("retainAll");
        initializeInstances();

        Multiset<Number> test1 = new Multiset<>();
        test1.add(1);
        test1.add(2);
        test1.add(3);

        // Kolejność ustaliłem wrzucając elementy do mapy i sprawdzając w jakiej będą kolejności
        assertEquals(true, instance1.retainAll(test1));
        assertEquals("[1=1, 2=1, 3=1]", instance1.setOfElementsAndCounters().toString());

        Multiset<String> test2 = new Multiset<>();
        test2.add("9a");
        test2.add("8a");
        test2.add("0a");

        assertEquals(true, instance2.retainAll(test2));
        assertEquals("[0a=1, 8a=1]", instance2.setOfElementsAndCounters().toString());

        Multiset<Object> test3 = new Multiset<>();
        test3.add(false);
        test3.add(0);
        test3.add(new HashSet<List<Collection>>());
        test3.add(.0);
        test3.add("zxczxcxzc");

        assertEquals(false, instance3.retainAll(test3));
        assertEquals("[0.0=1, 0=1, []=1, false=1, zxczxcxzc=1]", instance3.setOfElementsAndCounters().toString());
    }

    /**
     * Test of clear method, of class Multiset.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        initializeInstances();

        instance1.clear();
        assertEquals(false, instance1.contains(1));
        assertEquals(false, instance1.contains("asd"));
        assertEquals(0, instance1.size());

        instance2.clear();
        assertEquals(false, instance2.contains(1));
        assertEquals(false, instance2.contains("asd"));
        assertEquals(0, instance2.size());

        instance3.clear();
        assertEquals(false, instance3.contains(1));
        assertEquals(false, instance3.contains("asd"));
        assertEquals(0, instance3.size());
    }

}
