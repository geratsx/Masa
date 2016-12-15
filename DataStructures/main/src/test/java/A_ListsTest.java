import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class A_ListsTest {


    @Test
    public void testListPreservesInsertionOrder() {


        final List<String> list = new ArrayList<>();

        list.add("B");
        list.add("A");
        list.add("B");


        assertEquals("[B, A, B]", list.toString());

    }

    @Test
    public void insertionSpeed() {
        final List<Integer> linkedList = new LinkedList<>();
        final List<Integer> arrayList = new ArrayList<>();

        long start = System.currentTimeMillis();

        populate(linkedList, 10_000_000);

        System.out.printf("%s %d ms\n", "LinkedList", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        populate(arrayList, 10_000_000);

        System.out.printf("%s %d ms\n", "ArrayList", System.currentTimeMillis() - start);

    }

    @Test
    public void linkedListRemoveFasterWithIterator() {
        final List<Integer> linkedList = populate(new LinkedList<Integer>(), 1_000_000);
        final List<Integer> arrayList = populate(new ArrayList<Integer>(), 1_000_000);


        long start = System.nanoTime();
        Iterator<Integer> i = linkedList.iterator();
        i.next();

        i.remove();

        System.out.printf("%s %d ns\n", "LinkedList", System.nanoTime() - start);


        start = System.nanoTime();
        arrayList.remove(0);

        System.out.printf("%s %d ns\n", "ArrayList", System.nanoTime() - start);

        start = System.nanoTime();
        i = arrayList.iterator();
        i.next();
        i.remove();


        System.out.printf("%s %d ns\n", "ArrayList second chance", System.nanoTime() - start);

    }

    @Test
    public void linkedListRemoveSlower() {
        final List<Integer> linkedList = populate(new LinkedList<Integer>(), 1_000_000);
        final List<Integer> arrayList = populate(new ArrayList<Integer>(), 1_000_000);


        long start = System.nanoTime();

        linkedList.remove(500_000);

        System.out.printf("%s %d ms\n", "LinkedList", System.nanoTime() - start);


        start = System.nanoTime();
        arrayList.remove(500_000);

        System.out.printf("%s %d ms\n", "ArrayList", System.nanoTime() - start);

    }

    private List<Integer> populate(final List<Integer> list, final int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        return list;
    }
}