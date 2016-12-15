import org.junit.Test;

import java.util.*;

/**
 * Created by alexeysoshin on 14/12/2016.
 */
public class G_ListVsSetTest {

    @Test
    public void test() {
        final List<Integer> list = new ArrayList<>();
        final Set<Integer> set = new HashSet<>();

        populate(list);
        populate(set);

        long start = System.nanoTime();
        list.contains(70789);
        System.out.println(String.format("Took %s ms for %s", System.nanoTime() - start, "List"));

        start = System.nanoTime();
        set.contains(70789);
        System.out.println(String.format("Took %s ms for %s", System.nanoTime() - start, "Set"));
    }

    private void populate(final Collection<Integer> c) {

        for (int i = 0; i < 100_000; i++) {
            c.add(i);
        }
    }
}
