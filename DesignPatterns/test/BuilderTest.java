import builder.Builder;
import builder.Person;
import org.junit.Test;
import org.omg.PortableInterceptor.Interceptor;

/**
 * See more: http://www.tutorialspoint.com/design_pattern/builder_pattern.htm
 */
public class BuilderTest {

    @Test
    public void testPersonBuilder() {

        final Builder builder = new Builder();

        final Person person = builder.setAge(31).setName("Alexey").
                setLastName("Soshin").build();

        System.out.println(person);
    }

}