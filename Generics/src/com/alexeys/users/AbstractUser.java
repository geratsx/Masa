package com.alexeys.users;

/**
 * Created by alexey on 03/02/16.
 */
public class AbstractUser implements IUser {

    @Override
    public String toString() {
        return getTypeName();
    }

    @Override
    public String getTypeName() {
        return this.getClass().getName();
    }
}
