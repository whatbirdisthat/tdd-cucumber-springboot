package net.tqxr.testframework.reporting;

import net.tqxr.testframework.categories.Bloated;
import net.tqxr.testframework.spring.configuration.JUnitConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A Unit test that uses **All the things!!!***
 * Just to get the {see @Autowired} property into a unit test,
 * so much infrastructure is needed and this clouds the intent of the test,
 * de-focuses the actual testing effort and slows down the operation.
 * Added to that is the log-flooding which Spring does at `test` time.
 * <p>
 * This is an awesome example of how powerful Spring can be,
 * but a great example of when *not* to use it.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Category(Bloated.class)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@ContextConfiguration(classes = {JUnitConfiguration.class})
public class BloatedTestNameTransformerTest {

    @Autowired
    private TestNameTransformer testNameTransformer;

    @Test
    public void canTransformFirstLetterToUpperCase() throws Exception {

        String fakeTestName = "firstLetter";
        String expectedString = "FirstLetter";

        String actualString = testNameTransformer.transformLowerCaseFirstLetterToUpperCase(fakeTestName);

        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("First letter should be upper case.");

    }

    @Test
    public void canTransformCamelTestNameToSpacedWords() throws Exception {

        String fakeTestName = "canTransformArraylistToStringArray";
        String expectedString = "can Transform Arraylist To String Array";

        String actualString = testNameTransformer.transformCamelTestNameToSpacedWords(fakeTestName);

        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("Expected string should equal actual string");

    }

    @Test
    public void canTransformCamelTestNameToSpacedWordsWithUpperFirstLetter() throws Exception {

        String fakeTestName = "canTransformArraylistToStringArray";
        String expectedString = "Can Transform Arraylist To String Array";

        String actualString = testNameTransformer.transformTestName(fakeTestName);

        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("Expected string should equal actual string");

    }

}
