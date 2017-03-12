package net.tqxr.cucumber.support;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import net.tqxr.testframework.spring.configuration.CucumberConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A cucumber step class that mixes in the awesomeness of AssertJ assertions.
 * This cucumber step class will also get wiring from Spring
 * if an appropriate constructor is provided.
 * <p>
 * Field injection also works, but constructor injection forces us to keep
 * the test classes small and tight. Too many @Autowire'd fields can cause
 * problems with candidate selection too, especially when there's test class
 * inheritance going on (another thing that is not recommended!).
 * <p>
 * In short, best to use constructor injection, and avoid inheritance.
 */
@ContextConfiguration(classes = {CucumberConfiguration.class})
public abstract class SpringStep<T, TT> extends Assertions implements En {

    protected DataContainer<T> dataContainer;
    protected TT testComponent;

    /**
     * The empty constructor is called by cucumber, so we can put a hook in there
     * to "automatically" call the method to set up all the test steps.
     * <p>
     * This means we can define the actual steps in methods of their own: making each step
     * a *unit of work* - and we can *unit test* them!
     */
    protected SpringStep() {
        setUpTestSteps();
    }

    /**
     * Override this method to collect all the Given(), When(), Then() etc calls
     * which makes the stepdef class a *lot* more testable.
     * <p>
     * If you put all your step definitions into the lambdas at constructor time,
     * it's a world of pain trying to get a mock JavaBackend to fire the steps:
     * it can be done, but we're not testing whether or not we can control cucumber.
     * <p>
     * We are verifying the intent of our own test steps, so we *remove* as much extraneous
     * work as possible, °minimise* the number of moving parts and *focus* the testing effort
     * on only the thing we are verifying.
     */
    protected abstract void setUpTestSteps();
    protected void throwPending() {
        throw new PendingException();
    }


    protected String getStringResource(String resourceName) {
        String content = "";
        try {

            URL resource = getClass().getClassLoader()
                    .getResource(resourceName);

            if (null != resource) {
                URI uri = resource.toURI();
                content = new String(Files.readAllBytes(Paths.get(uri)));
            }

        } catch (NullPointerException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return content;

    }

}
