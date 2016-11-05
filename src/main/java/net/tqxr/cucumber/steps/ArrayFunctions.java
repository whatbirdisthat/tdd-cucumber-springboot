package net.tqxr.cucumber.steps;

import cucumber.api.DataTable;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.stringfunctions.ArrayUtil;


public class ArrayFunctions extends SpringStep<String, ArrayUtil<String>> {

    ArrayFunctions(ArrayUtil<String> testComponent, DataContainer<String> dataContainer) {
        this.dataContainer = dataContainer;
        this.testComponent = testComponent;
    }

    protected void setUpTestSteps() {

        Given("^an array of Strings and an ArrayList of Strings$", this::createArrayAndArrayList);
        When("^the two arrays are merged$", this::mergeArrays);
        Then("^the resultant array contains all elements from the source arrays$", this::verifyResults);

        Given("^two arrays of strings:$", this::createTwoArraysOfString);

    }

    void createArrayAndArrayList() {
        dataContainer.setArray("str1", "str2");
        dataContainer.setCollection("str3");

    }

    void createTwoArraysOfString(DataTable strings) {

        for (String s : strings.asList(String.class)) {
            System.out.println("STRING: " + s);
        }

    }

    void mergeArrays() {
        testComponent.mergeArrays(dataContainer.getCollection(), dataContainer.getArray());

    }

    void verifyResults() {
        String[] expectedList = {
                "str3", "str1", "str2"
        };

        assertThat(dataContainer.getCollection())
                .containsExactly(expectedList)
                .withFailMessage("ArrayList should equal tArray?");

    }

}
