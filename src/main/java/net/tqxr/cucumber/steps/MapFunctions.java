package net.tqxr.cucumber.steps;

import cucumber.api.DataTable;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.cucumber.support.SpringStep;
import net.tqxr.lib.stringfunctions.MapUtil;

public class MapFunctions extends SpringStep<String, MapUtil> {

    MapFunctions(MapUtil testComponent, DataContainer<String> dataContainer) {
        this.testComponent = testComponent;
        this.dataContainer = dataContainer;
    }

    @Override
    protected void setUpTestSteps() {
        Given("a datatable with the following data:", this::getData);
        When("a hashmap is created from that data", this::throwPending);
        Then("the hashmap data is equivalent", this::throwPending);
    }


    void getData(DataTable theData) {
        theData.getGherkinRows().forEach(
                r ->
                r.getCells().forEach(System.out::println)
        );

    }

}
