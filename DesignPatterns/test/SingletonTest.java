import org.junit.Test;
import sun.management.jmxremote.SingleEntryRegistry;

/**
 * Created by alexeysoshin on 10/01/2017.
 */
public class SingletonTest {


    @Test
    public void testSingletonIsSingle() {

        //Singleton s = new Singleton();


        for (int i = 0; i < 10; i++) {
            System.out.println(i + ") " + Singleton.getInstance().getSomething());
        }


        for (int i = 0; i < 10; i++) {
            System.out.println(i + ") " + AnotherSingleton.getInstance().getSomething());
        }
    }
}


class Singleton {

    private static final Singleton self;
    private final long something;

    private Singleton() {

        this.something = System.nanoTime();
    }

    static {
        self = new Singleton();
    }


    public static Singleton getInstance() {

        return self;
    }

    public Long getSomething() {
        return this.something;
    }
}

class AnotherSingleton {

    private static AnotherSingleton self = null;
    private final long something;

    private AnotherSingleton() {
        this.something = System.nanoTime();
    }


    public synchronized static AnotherSingleton getInstance() {

        if (self == null) {
            self = new AnotherSingleton();
        }
        return self;
    }

    public Long getSomething() {
        return this.something;
    }
}