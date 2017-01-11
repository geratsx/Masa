package factory;

import factory.animal.Animal;
import factory.animal.Cat;
import factory.animal.Dog;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class AnimalFactory extends Factory {
    public Animal createAnimal(String animalName) {
        switch (animalName) {
            case "Cat":
                return new Cat();
            case "Dog":
                return new Dog();
        }

        throw new RuntimeException("Unsupported animal: " + animalName);
    }
}
