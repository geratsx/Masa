package builder;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class Builder {

    private String name;
    private Integer age = 0;
    private Person mother;
    private Person father;
    private String lastName;

    public Builder setName(final String name) {
        this.name = name;
        return this;
    }

    public Builder setAge(final Integer age) {
        this.age = age;
        return this;
    }

    public Builder setLastName(final String name) {
        this.lastName = name;
        return this;
    }

    public Person build() {
        return new Person(this.name, this.age, this.mother, this.father);
    }
}
