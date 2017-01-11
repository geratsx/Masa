package com.alexeys;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The only case that you can return ? and still use it
 */
public class ReverserTest {
    @Test
    public void testWildcardCapture() {
        final List<?> unknownList = getUnknownList();
        Reverser.reverse(unknownList);
        System.out.println(unknownList);
    }

    private List<?> getUnknownList() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        return list;
    }
}