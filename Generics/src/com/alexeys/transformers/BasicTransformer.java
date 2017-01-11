package com.alexeys.transformers;

import com.alexeys.users.AdvancedUserImpl;
import com.alexeys.users.BasicUserImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Hi dear programmer,
 * Please specify two classes you want to operate on when working with me
 * First class can be anything
 * But second must be a subtype of the first
 * @param <F>
 * @param <T>
 */
public class BasicTransformer<F extends T, T> implements ITransformer<F, T> {

    /**
     * It's me again, dear programmer
     * I'll get some class as input, and will downcast it to its superclass, since it's totally ok
     * I'm sorry, but I don't know anything about those types, so only basic methods are allowed
     * @param f
     * @return
     */
    public T transform(F f) {

        System.out.println("Argument hashCode is " + f.hashCode() + " and that's all I know");
        return (T) f;
    }


}
