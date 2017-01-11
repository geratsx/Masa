package com.alexeys.users;

/**
 * Created by alexey on 03/02/16.
 */
public class BasicUserImpl extends AbstractUser {

    private String name;


    public BasicUserImpl() {

    }

    public BasicUserImpl(String name) {
        this.name = name;
    }

    public void doBasicStuff() {
        System.out.println("I'm doing some basic stuff");
    }

    public String toString() {
        return super.toString() + " " + this.name;
    }
}
