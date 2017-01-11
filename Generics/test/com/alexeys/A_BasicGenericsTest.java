package com.alexeys;

import com.alexeys.users.AdvancedUserImpl;
import com.alexeys.users.BasicUserImpl;
import com.alexeys.users.MoreAdvancedUserImpl;
import org.junit.Test;

import java.util.*;

public class A_BasicGenericsTest {

    @Test
    public void beforeGenerics() {
        // List before generics
        final List listOfAnything = new ArrayList();

        // Can hold any object
        listOfAnything.add(Integer.valueOf(42));
        listOfAnything.add("Some string");
        listOfAnything.add(new ArrayList());


        // When you try to iterate, you get Object
        // Not very informative
        for (final Object o : listOfAnything) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void beforeGenericsSameObjects() {
        final List listOfStrings = new ArrayList();

        listOfStrings.add("a");
        listOfStrings.add("b");
        listOfStrings.add("c");

        // Still an object, although contains only strings
        final Object string = listOfStrings.get(0);

        System.out.println("String as object: " + string);

        // Need to implicitly cast
        final String properString = (String) listOfStrings.get(0);

        System.out.println("Proper string: " + properString);
    }

    /**
     * Generics on collections are neat, but don't make them too complicated
     */
    @Test
    public void confusingGenerics() {
        // This is a bit hard to read, but still possible
        final Map<String, Set<String>> usersPets = getUsersPets();

        System.out.println(usersPets);

        // This is even more confusing
        final Map<String, Map<String, String>> confusingMap = getPresidents();

        System.out.println(confusingMap);
    }

    private Map<String, Map<String, String>> getPresidents() {
        final Map<String, Map<String, String>> countries = new HashMap<>();

        final Map<String, String> venezuelaPresidents = new HashMap<>();
        venezuelaPresidents.put("José Antonio Páez", "1830-1835");
        venezuelaPresidents.put("Andrés Narvarte", "1835-1835");
        countries.put("Venezuela", venezuelaPresidents);

        final Map<String, String> colombiaPresidents = new HashMap<>();
        colombiaPresidents.put("Simón Bolívar y Palacios", "1819-1830");
        colombiaPresidents.put("Joaquín de Mosquera y Arboleda", "1830-1830");

        countries.put("Colombia", colombiaPresidents);

        return countries;
    }

    /**
     * Returns users and his pets
     *
     * @return
     */
    private Map<String, Set<String>> getUsersPets() {
        final HashMap<String, Set<String>> usersPets = new HashMap<>();

        HashSet<String> pets = new HashSet<>(Arrays.asList("Sharik", "Murzik"));
        usersPets.put("Tanya", pets);

        pets = new HashSet<>(Collections.singletonList("Barsik"));

        usersPets.put("Marina", pets);

        return usersPets;
    }

    @Test
    public void testWildcard() {
        // What's the difference between those two?
        final List<Object> listOfObjects = new ArrayList<>();
        final List<?> listOfUnknown = new ArrayList<>();

        // List of objects behaves like List of anything
        listOfObjects.add(Integer.valueOf(42));
        listOfObjects.add("Some string");
        listOfObjects.add(new ArrayList());
        listOfObjects.add(null);

        System.out.println(listOfObjects);

        // But list of unknown is different
        // Doesn't work, unknown is not a string
        //listOfUnknown.add("String");

        // Only nulls are ok
        listOfUnknown.add(null);
        listOfUnknown.add(null);

        System.out.println(listOfUnknown);
    }

    /*
    The Get and Put Principle:
    use an extends wildcard when you only get values out of a structure,
    use a super wildcard when you only put values into a structure,
    and don't use a wildcard when you both get and put.

    PECS, comes from “Producer Extends, Consumer Super”
     */

    @Test
    public void testWildcardSuper() {


        final List<? super AdvancedUserImpl> listSuper = getAdvancedUsersStrict();

        // This will not work
        //listSuper.add(new BasicUserImpl());

        // Each class is a super of itself
        listSuper.add(new AdvancedUserImpl());

        // This class extends super class required
        listSuper.add(new MoreAdvancedUserImpl());

        // You cannot replace it if functions returns wildcard
        //listSuper = getAdvancedUsers();

        // Can't say anything about this object
        final Object user = listSuper.get(0);

        // It's not AdvancedUserImpl

        // It's not MoreAdvancedUserImpl
    }

    @Test
    public void testWildcardExtends() {

        // This will not work, although MoreAdvancedUserImpl is a subtype of AdvancedUserImpl
        //List<AdvancedUserImpl> list = new ArrayList<MoreAdvancedUserImpl>();

        // But this will work
        List<? extends AdvancedUserImpl> list = getAdvancedUsers();

        // And this too
        list = getMoreAdvancedUsers();

        // But you can't add elements
        //list.add(new BasicUserImpl());
        //list.add(new AdvancedUserImpl());
        //list.add(new MoreAdvancedUserImpl());
        list.add(null);
        // I know that it's at least AdvancedUser, maybe even MoreAdvancedUser
        final AdvancedUserImpl user = list.get(0);
    }

    /*
        ? super T = T < ? < Object
        ? extends T = null < ? < T
     */

    /**
     * Let's look how assignments work
     */
    @Test
    public void testReplacement() {
        List<? extends BasicUserImpl> basicUsersExtends = new ArrayList<>();
        final List<? extends AdvancedUserImpl> advancedUsersExtends = getAdvancedUsers();
        final List<? super BasicUserImpl> basicUsersSuper = new ArrayList<>();
        List<? super AdvancedUserImpl> advancedUsersSuper = new ArrayList<>();

        basicUsersSuper.add(new BasicUserImpl("Alexey"));

        // Since BasicUser > AdvancedUser
        // null < AdvancedUser < BasicUser
        // AdvancedUser must extend BasicUser, so it's ok
        basicUsersExtends = advancedUsersExtends;

        // AdvancedUser < BasicUser < Object
        // Basic users are between AdvancedUser and Object,
        // so it's ok to put them in advanced users
        advancedUsersSuper = basicUsersSuper;

        System.out.println(advancedUsersSuper);
    }

    private List<? extends MoreAdvancedUserImpl> getMoreAdvancedUsers() {
        final List<MoreAdvancedUserImpl> users = new ArrayList<>();
        users.add(new MoreAdvancedUserImpl("Igor"));
        return users;
    }

    private List<AdvancedUserImpl> getAdvancedUsers() {
        final List<AdvancedUserImpl> users = new ArrayList<>();
        users.add(new AdvancedUserImpl("Alexey"));
        users.add(new AdvancedUserImpl("Polina"));
        return users;
    }

    private List<AdvancedUserImpl> getAdvancedUsersStrict() {
        return new ArrayList<>(getAdvancedUsers());
    }

}