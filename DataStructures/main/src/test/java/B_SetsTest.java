import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import static junit.framework.Assert.assertEquals;

public class B_SetsTest {

    @Test
    public void testSetHoldOnlyUnique() {

        final Set<String> set = new HashSet<>();


        set.add("A");
        set.add("B");
        set.add("A");
        set.add("B");
        set.add("B");

        assertEquals("[A, B]", set.toString());
    }

    @Test
    public void testSetDoesntPreserveInsertionOrder() {
        final Set<String> set = new HashSet<>();

        set.add("B");
        set.add("A");
        set.add("A");
        set.add("D");
        set.add("D");
        set.add("D");
        set.add("C");

        assertEquals("[A, B, C, D]", set.toString());
    }

    @Test
    public void testSetDoesntPreserveAnyOrder() {

        final Set<Integer> set = new HashSet<>();


        set.add(100);
        set.add(200);
        set.add(300);
        set.add(400);
        set.add(400);
        set.add(400);

        assertEquals("[400, 100, 200, 300]", set.toString());
    }

    @Test
    public void testLinkedSetPreservesOrder() {
        final Set<String> set = new LinkedHashSet<>();

        set.add("B");
        set.add("A");
        set.add("D");
        set.add("C");
        set.add("D");
        set.add("D");

        assertEquals("[B, A, D, C]", set.toString());
    }


    @Test
    public void testTreeSetSorts() {

        final Set<Integer> set = new TreeSet<>();


        set.add(400);
        set.add(400);
        set.add(300);
        set.add(300);
        set.add(200);
        set.add(100);
        set.add(100);
        set.add(100);


        assertEquals("[100, 200, 300, 400]", set.toString());
    }
}
