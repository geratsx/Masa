import factory.FruitFactory;
import factory.abs.AbsFactory;
import factory.animal.Animal;
import factory.fruit.Apple;
import factory.fruit.Banana;
import factory.fruit.Fruit;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class FactoryTest {

    @Test
    public void testFactory() throws URISyntaxException {
        System.out.println("With Factory I can create similar objects from configuration file!");
        final FruitFactory factory = new FruitFactory();

        final List<Fruit> fruits = new ArrayList<>();


        final List<String> fruitNames = fileToLines("fruits.txt");

        for (final String name : fruitNames) {
            fruits.add(factory.createFruit(name));
        }
        for (final Fruit fruit : fruits) {
            System.out.println(fruit.getClass().getSimpleName());
        }

    }

    /**
     * Reads file line by line
     *
     * @param filename
     * @return
     * @throws URISyntaxException
     */
    private List<String> fileToLines(final String filename) throws URISyntaxException {
        final List<String> lines = new ArrayList<>();
        final Path filePath = Paths.get(ClassLoader.getSystemResource(filename).toURI()).toAbsolutePath();
        try (Stream<String> stream = Files.lines(filePath)) {

            stream.forEach((name) -> {
                lines.add(name);
            });

        } catch (final IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    @Test
    public void absFactoryTest() throws URISyntaxException {

        System.out.println("With Abstract Factory I can create very different objects from configuration file");
        final AbsFactory absFactory = new AbsFactory();

        final List<Animal> animals = new ArrayList<>();
        final List<Fruit> fruits = new ArrayList<>();

        final List<String> lines = fileToLines("fruits_and_animals.txt");

        for (final String l : lines) {
            final String[] line = l.split("=");
            final String type = line[0];
            final String name = line[1];
            switch (type) {
                case "Animal":
                    animals.add(absFactory.createAnimal(name));
                    break;

                case "Fruit":
                    fruits.add(absFactory.createFruit(name));
                    break;
                default:
                    System.out.println("Unknown type " + type);
            }
        }

        for (final Animal animal : animals) {
            System.out.println(animal.getClass().getSimpleName());
        }

        for (final Fruit fruit : fruits) {
            System.out.println(fruit.getClass().getSimpleName());
        }
    }
}