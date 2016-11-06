package net.tqxr.lib.stringfunctions;

import net.tqxr.testframework.categories.DeliberatelyFail;
import net.tqxr.testframework.categories.ExampleTests;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;
@Category(ExampleTests.class)
public class StringUtilTest {

    private StringUtil stringUtil;

    @Before
    public void setUp() {
        stringUtil = new StringUtil();
    }

    @Test
    public void transformUpper() throws Exception {

        String expected = "STRING";

        String actual = stringUtil.transformUpper("string");

        assertThat(actual)
                .isEqualTo(expected)
                .withFailMessage("The string should be upper case now");

    }

    @Test
    public void transformLower() throws Exception {

        String expected = "string";

        String actual = stringUtil.transformLower("STRING");

        assertThat(actual)
                .isEqualTo(expected)
                .withFailMessage("The string should be lower case now");

    }

    @Test
    @Category(DeliberatelyFail.class)
    public void transformLowerFAIL() throws Exception {

        String expected = "stringz";

        String actual = stringUtil.transformLower("STRING");

        assertThat(actual)
                .isEqualTo(expected)
                .withFailMessage("The string should be lower case now");

    }

    @Test
    @Ignore("Test ignored.")
    public void transformUpperIGNORE() throws Exception {

        String expected = "STRINGZ";

        String actual = stringUtil.transformUpper("string");

        assertThat(actual)
                .isEqualTo(expected)
                .withFailMessage("The string should be upper case now");

    }

}
