package net.tqxr.cucumber.support;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DataContainerTest {
    @Test
    public void canSetArrayWithStrings() throws Exception {

        DataContainer<String> testContainer = new DataContainer<>();

        testContainer.setArray("one", "two");

        assertThat(testContainer.getArray())
                .containsExactly("one", "two");

    }

    @Test
    public void canSetCollectionWithStrings() throws Exception {
        DataContainer<String> testContainer = new DataContainer<>();

        testContainer.setCollection("one", "two");

        assertThat(testContainer.getCollection())
                .containsExactly("one", "two");

    }

}