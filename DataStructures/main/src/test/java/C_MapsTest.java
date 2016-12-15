import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

/**
 * Created by alexeysoshin on 13/12/2016.
 */
public class C_MapsTest {


    @Test

    public void testMapContainsOnlyUniqueKeys() {

        final Map<String, String> map = new HashMap<>();

        map.put("A", "B");
        map.put("C", "D");
        map.put("A", "C");

        assertEquals("{A=C, C=D}", map.toString());
    }

    @Test
    public void testMapDoesntPreserveInsertionOrder() {
        final Map<String, String> map = new HashMap<>();

        map.put("C", "D");
        map.put("A", "C");

        assertEquals("{A=C, C=D}", map.toString());
    }

    @Test
    public void testMapDoesntPreserveAnyOrder() {
        final Map<Integer, String> map = new HashMap<>();


        map.put(400, "A");
        map.put(300, "B");
        map.put(200, "C");
        map.put(100, "D");

        assertEquals("{400=A, 100=D, 200=C, 300=B}", map.toString());
    }

    @Test
    public void testLinkedMapSortedByInsertionOrder() {
        final Map<Integer, String> map = new LinkedHashMap<>();


        map.put(400, "A");
        map.put(300, "B");
        map.put(200, "C");
        map.put(100, "D");

        assertEquals("{400=A, 300=B, 200=C, 100=D}", map.toString());
    }

    @Test
    public void testTreeMapSortedByKey() {
        final Map<Integer, String> map = new TreeMap<>();


        map.put(400, "A");
        map.put(300, "B");
        map.put(200, "C");
        map.put(100, "D");

        assertEquals("{100=D, 200=C, 300=B, 400=A}", map.toString());
    }

    @Test
    public void testGetMapKeys() {
        final Map<Integer, String> map = new HashMap<>();


        map.put(400, "A");
        map.put(300, "B");
        map.put(200, "C");
        map.put(100, "D");

        assertEquals("[400, 100, 200, 300]", map.keySet().toString());
    }

    @Test
    public void testGetMapValues() {
        final Map<Integer, String> map = new HashMap<>();


        map.put(400, "A");
        map.put(300, "B");
        map.put(200, "C");
        map.put(100, "D");


        assertEquals("[A, D, C, B]", map.values().toString());
    }

    @Test
    public void testGetPairs() {
        final Map<Integer, String> map = new HashMap<>();


        map.put(400, "A");
        map.put(300, "B");
        map.put(200, "C");
        map.put(100, "D");

        for (Map.Entry<Integer, String> pair : map.entrySet()) {
            System.out.printf("Key: %d Value %s \n", pair.getKey(), pair.getValue());
        }
    }
}
