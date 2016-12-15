import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by alexeysoshin on 14/12/2016.
 */
public class E_ArraysTest {


    @Test
    public void testCreateListOneLiner() {

        final List<String> list = Arrays.asList("A", "B", "C");

        assertEquals("[A, B, C]", list.toString());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAsListReadOnlyAdd() {

        final List<String> list = Arrays.asList("A", "B", "C");
        list.add("D");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAsListReadOnlyRemove() {

        final List<String> list = Arrays.asList("A", "B", "C");
        list.remove("C");
    }

    @Test
    public void testListCopyConsructorOneLine() {
        final List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        assertEquals("[A, B, C]", list.toString());

        list.add("D");

        assertEquals("[A, B, C, D]", list.toString());
    }

    @Test
    public void testFill() {
        final Integer[] allSevens = new Integer[10];

        System.out.println(Arrays.toString(allSevens));

        Arrays.fill(allSevens, 7);

        System.out.println(Arrays.toString(allSevens));
    }

    @Test
    public void testSort() {
        final Integer[] randomNumbers = new Integer[]{89, 76, 45};

        // Happens in place
        Arrays.sort(randomNumbers);
        System.out.println(Arrays.toString(randomNumbers));
    }
}
