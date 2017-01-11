import org.junit.Test;
import strategy.CatSound;
import strategy.DogSound;
import strategy.Legs;
import strategy.SoundStrategy;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class StrategyTest {

    @Test
    public void testSoundStrategy() {

        final Robot r = new Robot();
        r.setLegs(new FastLegs());
        r.setWeapon(new Rocket());

        r.shoot();

        r.move();
        r.shoot();


        r.setWeapon(new Rifle());

        r.shoot();

        r.shoot();

    }

    class Robot {
        private Weapon weapon;
        private Legs legs;

        void setWeapon(final Weapon w) {

            this.weapon = w;
        }

        void setLegs(final Legs l) {
            this.legs = l;
        }

        public void shoot() {
            this.weapon.shoot();
        }

        public void move() {
            this.legs.move();
        }
    }

    interface Weapon {
        void shoot();
    }

    class Rifle implements Weapon {

        @Override
        public void shoot() {
            System.out.println("Pow");
        }
    }


    class FastLegs implements Legs {

        @Override
        public void move() {
            System.out.println("Running");
        }
    }

    class SlowLegs implements Legs {

        @Override
        public void move() {
            System.out.println("Moving slowly");
        }
    }

    class Rocket implements Weapon {

        @Override
        public void shoot() {
            System.out.println("Boom");
        }
    }


}