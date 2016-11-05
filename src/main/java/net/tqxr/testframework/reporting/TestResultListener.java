package net.tqxr.testframework.reporting;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

@RunListener.ThreadSafe
public class TestResultListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        System.out.println(String.format("TEST RUN '%s' started\n", description.getDisplayName()));
    }


    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        System.out.printf("TEST RUN FINISHED: %d failures\n", result.getFailureCount());
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        System.out.printf("TEST STARTED: '%s'\n", description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
        System.out.printf("TEST FINISHED '%s'\n", description);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        System.out.printf("TEST FAILURE '%s'\n", failure);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);
        System.out.printf("ASSUMPTION FAILURE '%s'\n", failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
        System.out.printf("FAILURE '%s'\n", description);
    }
}
