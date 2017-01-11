package factory;

import factory.fruit.Apple;
import factory.fruit.Banana;
import factory.fruit.Fruit;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class FruitFactory extends Factory {
    public Fruit createFruit(String fruitName) {


        switch (fruitName) {
            case "Apple":
                return new Apple();
            case "Banana":
                return new Banana();
        }

        throw new RuntimeException("No such fruit: " + fruitName);
    }
}
