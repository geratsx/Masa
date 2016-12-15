import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

/**
 * Created by alexeysoshin on 14/12/2016.
 */
public class F_CollectionsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testReadOnlyList() {

        final List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        final Collection<String> readOnlyList = Collections.unmodifiableCollection(list);

        // Can modify regular list
        list.add("D");

        assertEquals("[A, B, C, D]", list.toString());

        // Can't modify this one
        readOnlyList.add("D");
    }


    @Test
    public void testReverse() {
        final List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        System.out.println(list.toString());
        Collections.reverse(list);
        System.out.println(list.toString());
    }

    @Test
    public void testShuffle() {
        final List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        System.out.println(list.toString());
        Collections.shuffle(list);
        System.out.println(list.toString());
        Collections.shuffle(list);
        System.out.println(list.toString());
        Collections.shuffle(list);
        System.out.println(list.toString());
    }


    @Test
    public void testPolymorphism() {

        final List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        final Set<String> set = new TreeSet<>(Arrays.asList("D", "E", "F", "G"));

        // If you get collection, you don't care if it's list or set
        printCollection(list);
        printCollection(set);
    }

    private void printCollection(final Collection<String> c) {

        for (final String s : c) {
            System.out.println(s);
        }

        System.out.println();
    }

}

