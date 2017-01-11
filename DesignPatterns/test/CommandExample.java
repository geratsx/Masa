import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexeysoshin on 10/01/2017.
 */
public class CommandExample {


    @Test
    public void test() {

        // Bless
        // Heal
        // Berserk


        final List<Command> commands = new ArrayList<>();

        commands.add(new Bless());

        commands.add(new Bless());


        commands.add(new Berserk());

        for (final Command c : commands) {
            c.doCommand();
        }
    }

    interface Command {
        void doCommand();
    }

    class Bless implements Command {

        @Override
        public void doCommand() {
            System.out.println("Blessing");
        }
    }

    class Heal implements Command {

        @Override
        public void doCommand() {
            System.out.println("Healing");
        }
    }

    class Berserk implements Command {

        @Override
        public void doCommand() {
            System.out.println("Berserk!!!!");
        }
    }
}
