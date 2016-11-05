package net.tqxr.cucumber.support;

import cucumber.api.java8.En;
import net.tqxr.testframework.spring.configuration.CucumberConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.test.context.ContextConfiguration;

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
public abstract class SpringStep extends Assertions implements En {

}
