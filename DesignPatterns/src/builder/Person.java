package builder;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class Person {

    private Person mother = null;
    private Person father = null;
    private String name = "none";
    private Integer age = 0;

    @Override
    public String toString() {
        return "Person{" +
                "mother=" + mother +
                ", father=" + father +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public Person(String name, Integer age, Person mother, Person father) {
        this.name = name;
        this.age = age;
        this.mother = mother;
        this.father = father;
    }


    public String getName() {
        return name;
    }


    public Integer getAge() {
        return age;
    }

    public Person getMother() {
        return mother;
    }

    public Person getFather() {
        return father;
    }
}
