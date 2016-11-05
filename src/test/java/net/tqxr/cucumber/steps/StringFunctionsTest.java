package net.tqxr.cucumber.steps;

import cucumber.runtime.java.JavaBackend;
import cucumber.runtime.java8.ConstantPoolTypeIntrospector;
import net.tqxr.cucumber.support.DataContainer;
import net.tqxr.lib.stringfunctions.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StringFunctionsTest {

    @Mock
    StringUtil fakeStringUtil;
    @Mock
    DataContainer<String> fakeDataContainer;
    @Mock
    JavaBackend fakeJavaBackend;

    private StringFunctions testComponent;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        assertThat(fakeStringUtil).isNotNull();
        assertThat(fakeDataContainer).isNotNull();
        assertThat(fakeJavaBackend).isNotNull();
        JavaBackend.INSTANCE.set(fakeJavaBackend);
        testComponent = new StringFunctions(fakeStringUtil, fakeDataContainer);

    }

    @Test
    public void canInitialiseWithMockComponentAndContainer() {

        verify(fakeJavaBackend).addStepDefinition(
                eq("^a String \"([^\"]*)\"$"),
                eq(0L),
                any(),
                eq(ConstantPoolTypeIntrospector.INSTANCE)
        );
        verify(fakeJavaBackend).addStepDefinition(
                eq("^the string is capitalised$"),
                eq(0L),
                any(),
                eq(ConstantPoolTypeIntrospector.INSTANCE)
        );
        verify(fakeJavaBackend).addStepDefinition(
                eq("^the string is uncapitalised$"),
                eq(0L),
                any(),
                eq(ConstantPoolTypeIntrospector.INSTANCE)
        );
        verify(fakeJavaBackend).addStepDefinition(
                eq("^the string becomes upper-case \"([^\"]*)\"$"),
                eq(0L),
                any(),
                eq(ConstantPoolTypeIntrospector.INSTANCE)
        );

    }

    @Test
    public void canSetSourceString() {
        testComponent.setSourceString("TEST ONE");

        verify(fakeDataContainer).setSource("TEST ONE");
    }

    @Test
    public void canCapitaliseString() {
        when(fakeStringUtil.transformUpper(any())).thenReturn("UPPER_CASE_STRING");
        when(fakeDataContainer.getSource()).thenReturn("TEST ONE");

        testComponent.capitaliseString();

        verify(fakeDataContainer).getSource();
        verify(fakeStringUtil).transformUpper("TEST ONE");
        verify(fakeDataContainer).setActual("UPPER_CASE_STRING");
    }

    @Test
    public void canUncapitaliseString() {
        when(fakeStringUtil.transformLower(any())).thenReturn("lower_case_string");
        when(fakeDataContainer.getSource()).thenReturn("TEST UNCAPITALISE");

        testComponent.uncapitaliseString();

        verify(fakeDataContainer).getSource();
        verify(fakeStringUtil).transformLower("TEST UNCAPITALISE");
        verify(fakeDataContainer).setActual("lower_case_string");
    }

    @Test
    public void canVerifyString() {

        when(fakeDataContainer.getActual()).thenReturn("ACTUAL");

        testComponent.verifyString("ACTUAL");

        verify(fakeDataContainer).setExpected("ACTUAL");
    }

    @Test
    public void canVerifyStringMismatchAndCatchException() {

        when(fakeDataContainer.getActual()).thenReturn("ACTUAL");

        assertThatThrownBy(
                () -> testComponent.verifyString("EXPECTED")
        )
                .isInstanceOf(org.junit.ComparisonFailure.class)
                .hasMessageContaining("expected:<\"[EXPECTED]\"> but was:<\"[ACTUAL]\"");

        verify(fakeDataContainer).setExpected("EXPECTED");
    }

}
