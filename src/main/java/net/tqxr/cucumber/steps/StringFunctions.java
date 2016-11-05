package net.tqxr.cucumber.steps;

import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.stringfunctions.StringUtil;

public class StringFunctions extends SpringStep {

    public StringFunctions(StringUtil testComponent, DataContainer<String> dataContainer) {

        Given("^A String \"([^\"]*)\"$",
                (String theString) -> {
                    dataContainer.source = theString;
                });

        When("^the string is capitalised$",
                () -> {
                    String sourceString = dataContainer.source;
                    dataContainer.actual = testComponent.transformUpper(sourceString);
                });

        When("^the string is uncapitalised$",
                () -> {
                    String sourceString = dataContainer.source;
                    dataContainer.actual = testComponent.transformLower(sourceString);
                });

        Then("^the string becomes upper-case \"([^\"]*)\"$",
                (final String expectedString) -> {
                    String actualString = dataContainer.actual;
                    assertThat(actualString)
                            .isEqualTo(expectedString)
                            .withFailMessage("Actual must match expected.");

                });

    }
}
