package com.alexeys.users;

/**
 * Created by alexey on 03/02/16.
 */
public class AdvancedUserImpl extends BasicUserImpl {

    public AdvancedUserImpl() {

    }

    public AdvancedUserImpl(String name) {
        super(name);
    }

    public void doAdvancedStuff() {
        System.out.println("I'm doing some advanced stuff");
    }
}
