package net.tqxr.lib.stringfunctions;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class ArrayUtil<T> {

    public Collection<T> mergeArrays(
            Collection<T> collection, T[] array) {
        Collections.addAll(collection, array);

        return collection;
    }

}
