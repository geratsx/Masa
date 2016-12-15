import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexeysoshin on 14/12/2016.
 */
public class H_HashCodeEquals {

    @Test
    public void testBrokenHash() {

        final DotaPlayerWithoutHash a = new DotaPlayerWithoutHash("Na`Vi.Dendi");
        final DotaPlayerWithoutHash b = new DotaPlayerWithoutHash("OG.s4");
        final DotaPlayerWithoutHash c = new DotaPlayerWithoutHash("Na`Vi.Dendi");

        final Set<DotaPlayerWithoutHash> players = new HashSet<>();

        players.add(a);
        players.add(b);
        players.add(c);

        System.out.println(players.size());
    }

    @Test
    public void testValidHash() {

        final DotaPlayerWithHash a = new DotaPlayerWithHash("Na`Vi.Dendi");
        final DotaPlayerWithHash b = new DotaPlayerWithHash("OG.s4");
        final DotaPlayerWithHash c = new DotaPlayerWithHash("Na`Vi.Dendi");

        final Set<DotaPlayerWithHash> players = new HashSet<>();

        players.add(a);
        players.add(b);
        players.add(c);

        System.out.println(players.size());
    }
}


class DotaPlayerWithoutHash {

    private final String name;

    DotaPlayerWithoutHash(final String name) {
        this.name = name;
    }
}

class DotaPlayerWithHash {

    private final String name;

    DotaPlayerWithHash(final String name) {
        this.name = name;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DotaPlayerWithHash that = (DotaPlayerWithHash) o;

        return this.name != null ? this.name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return this.name != null ? this.name.hashCode() : 0;
    }
}