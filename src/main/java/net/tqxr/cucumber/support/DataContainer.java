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

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public T expected;
    public void setExpected(T expected) {
        this.expected = expected;
    }
    public T getExpected() {
        return expected;
    }

    public T actual;

    public void setActual(T actual) {

        this.actual = actual;
    }

    public T getActual() {
        return actual;
    }
}

