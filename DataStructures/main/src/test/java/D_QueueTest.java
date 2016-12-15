import org.junit.Test;

import java.util.*;

/**
 * Created by alexeysoshin on 13/12/2016.
 */
public class D_QueueTest {

    @Test
    public void testQueueFIFO() {

        final Queue<String> q = new LinkedList<>();


        q.add("A");
        q.add("B");
        q.add("C");

        while (!q.isEmpty()) {

            // Pop the first element
            System.out.println(q.poll());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testQueueRemoveThrowsException() {

        final Queue<String> q = new LinkedList<>();

        System.out.println("Poll: " + q.poll());

        // Poping from empty queue throws an exception, unlike poll
        System.out.println("Remove: " + q.remove());
    }


    @Test
    public void testPeek() {
        // Peek is like poll, but doesn't remove the element

        final Queue<String> q = new LinkedList<>();

        q.add("A");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.peek());
        }
    }

    /**
     * Don't use, deprecated
     */
    @Test
    public void testStackLIFO() {
        final Stack<String> s = new Stack<>();

        s.add("A");
        s.add("B");
        s.add("C");

        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }

    }
}
