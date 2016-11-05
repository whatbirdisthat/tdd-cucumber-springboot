package net.tqxr.cucumber.support;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataContainer<T> {

    public void setArray(T... things) {
        tArray = things;
    }

    public void setCollection(T... things) {
        tCollection = new ArrayList<>();
        for (T eachThing : things) {
            tCollection.add(eachThing);
        }
    }

    public T[] getArray() {
        return tArray;
    }

    public List<T> getCollection() {
        return tCollection;
    }

    private T[] tArray;
    private List<T> tCollection;

    public T source;
    public T expected;
    public T actual;
}

