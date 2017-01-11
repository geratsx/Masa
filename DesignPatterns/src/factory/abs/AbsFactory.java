package factory.abs;

import factory.AnimalFactory;
import factory.FruitFactory;
import factory.animal.Animal;
import factory.fruit.Fruit;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class AbsFactory {

    AnimalFactory animalFactory = new AnimalFactory();
    FruitFactory fruitFactory = new FruitFactory();


    public Fruit createFruit(String fruitName) {
        return fruitFactory.createFruit(fruitName);
    }

    public Animal createAnimal(String animalName) {
        return animalFactory.createAnimal(animalName);
    }
}
