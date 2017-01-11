package com.alexeys;

/**
 * Created by alexeysoshin on 25/12/2016.
 */
public class EnumExample {

    private enum UserType {
        ADMIN,  //calls constructor with value 3
        REGULAR
    }


    class UserTypes {

        final static int ADMIN = 0;
        final static int REGULAR = 1;
    }


    public static void main(final String[] args) {
        System.out.println(UserType.REGULAR);
        System.out.println(UserType.ADMIN);

        System.out.println(UserType.REGULAR.name());

        System.out.println(UserType.REGULAR.ordinal());

        System.out.println(UserType.ADMIN.ordinal());
    }
}
