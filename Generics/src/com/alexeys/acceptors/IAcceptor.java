package com.alexeys.acceptors;

/**
 * Created by alexey on 03/02/16.
 */
public interface IAcceptor<AnyType> {

    void accept(AnyType t);
}
