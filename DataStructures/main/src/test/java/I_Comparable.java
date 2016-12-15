import org.junit.Test;

import java.util.*;

/**
 * Created by alexeysoshin on 14/12/2016.
 */
public class I_Comparable {

    @Test
    public void testComparable() {

        // Maps players to their awards
        final Map<LolPlayer, List<String>> players = new TreeMap<>();

        players.put(new LolPlayer("Smeb", 21), new ArrayList<>(Arrays.asList("2015 - Finals")));
        players.put(new LolPlayer("Faker", 20), new ArrayList<>(Arrays.asList("2013 - Champion", "2015 - Champion")));
        players.put(new LolPlayer("Deft", 19), new ArrayList<>(Arrays.asList("2014 - Champion", "2015 - Quarterfinals")));

        System.out.println(players.toString());
    }
}


class LolPlayer implements Comparable<LolPlayer> {

    private final String name;
    private final Integer age;

    LolPlayer(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(final LolPlayer o) {

        //(this.age - o.age)  * -1

        if (this.age > o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "\n{" +
                "name='" + this.name + '\'' +
                ", age=" + this.age +
                "}";
    }
}