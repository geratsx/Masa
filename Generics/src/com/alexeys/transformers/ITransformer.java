package com.alexeys.transformers;

/**
 * Example of interface using two types
 */
public interface ITransformer<F, T> {

    T transform(F f);
}
