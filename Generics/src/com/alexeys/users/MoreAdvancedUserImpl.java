package com.alexeys.users;

/**
 * Created by alexey on 03/02/16.
 */
public class MoreAdvancedUserImpl extends AdvancedUserImpl {

    public MoreAdvancedUserImpl() {

    }

    public MoreAdvancedUserImpl(String name) {
        super(name);
    }

    public void doMoreAdvancedStuff() {
        System.out.println("I'm doing some more advanced stuff");
    }
}
