package multisettester;

import java.util.Arrays;
import multiset.Multiset;

public class MultisetTester {

    public static void main(String[] args) {
        Multiset<String> multi = new Multiset<>();
        for (Integer i = 0; i < 4; i++) {
            for (Integer j = 0; j < i; j++) {
                multi.add(i + "");
            }
        }
        multi.forEach((i) -> {
            System.out.print(i + " ");
        });

        System.out.println();

        System.out.println(Arrays.toString(multi.toArray()));

        System.out.println(multi.setOfElementsAndCounters());

        System.out.println(multi.size());

        System.out.println(multi.contains("2"));

        System.out.println(multi.contains(2));
    }

}
