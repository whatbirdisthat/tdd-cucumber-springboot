package net.tqxr.cucumber.steps;

import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.stringfunctions.StringUtil;

class StringFunctions extends SpringStep<String, StringUtil> {

    StringFunctions(StringUtil testComponent, DataContainer<String> dataContainer) {
        this.testComponent = testComponent;
        this.dataContainer = dataContainer;
    }

    protected void setUpTestSteps() {

        Given("^a String \"([^\"]*)\"$", this::setSourceString);
        When("^the string is capitalised$", this::capitaliseString);
        When("^the string is uncapitalised$", this::uncapitaliseString);
        Then("^the string becomes upper-case \"([^\"]*)\"$", this::verifyString);

    }

    void setSourceString(final String sourceString) {
        dataContainer.setSource(sourceString);
    }

    void capitaliseString() {
        String sourceString = dataContainer.getSource();
        String actualString = testComponent.transformUpper(sourceString);
        dataContainer.setActual(actualString);
    }

    void uncapitaliseString() {
        String sourceString = dataContainer.getSource();
        dataContainer.setActual(testComponent.transformLower(sourceString));
    }

    void verifyString(final String expectedString) {
        dataContainer.setExpected(expectedString);
        String actualString = dataContainer.getActual();
        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("Actual must match expected.");
    }

}
