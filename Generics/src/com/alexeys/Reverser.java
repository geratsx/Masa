package com.alexeys;

import java.util.ArrayList;
import java.util.List;

public class Reverser {

    // That's called wildcard capture
    // We say that T is some type known only to this function
    // Note that this is not UnusedType from class definition

    private static <T> void reverseInner(List<T> list) {
        List<T> tmp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size()-i-1));
        }
    }




    public static void reverse(List<?> list) {
        System.out.println("I got list of unknowns here, but reversInner will know what to do");
        reverseInner(list);
    }

    // This will not work, you can't do anything with ?
    /*
    public static void reverseInner(List<?> list) {
        List<Object> tmp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            // compile-time error
            list.set(i, tmp.get(list.size()-i-1));
        }
    }
    */

}
