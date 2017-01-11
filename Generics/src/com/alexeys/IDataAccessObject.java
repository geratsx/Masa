package com.alexeys;

import java.util.List;

/**
 * Examples of more advanced generics techniques
 */
public interface IDataAccessObject<T, Except extends RuntimeException> {

    // I'll return what you give me
    T create(T t);

    // I'll have batch of same objects as regular create
    // I'll return batch of exceptions
    List<Except> batchCreate(List<T> batch);

    // I don't want to get T, as others
    // So I'll define something else entirely, not known to my class
    // This is called wildcard capture
    // Note that U can be only String, as nothing can extend String
    <U extends T> Except combine(T t, U u);

    // My second parameter is something most methods of my class won't use
    // But it should be related to T that they use
    <L> Except merge(L l);
}
