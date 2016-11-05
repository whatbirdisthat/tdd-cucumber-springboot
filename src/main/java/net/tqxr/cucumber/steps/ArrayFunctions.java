package net.tqxr.cucumber.steps;

import cucumber.api.DataTable;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.stringfunctions.ArrayUtil;


public class ArrayFunctions extends SpringStep {

    public ArrayFunctions(ArrayUtil<String> testComponent, DataContainer<String> dataContainer) {

        Given("^an array of Strings and an ArrayList of Strings$", () -> {

            dataContainer.setArray("str1", "str2");
            dataContainer.setCollection("str3");

        });
        When("^the two arrays are merged$", () -> {
            testComponent.mergeArrays(dataContainer.getCollection(), dataContainer.getArray());
        });
        Then("^the resultant array contains all elements from the source arrays$", () -> {

            String[] expectedList = {
                    "str3", "str1", "str2"
            };

            assertThat(dataContainer.getCollection())
                    .containsExactly(expectedList)
                    .withFailMessage("ArrayList should equal tArray?");

        });
        Given("^two arrays of strings:$", (DataTable strings) -> {

            for (String s : strings.asList(String.class)) {
                System.out.println("STRING: " + s);
            }

        });
    }
}
