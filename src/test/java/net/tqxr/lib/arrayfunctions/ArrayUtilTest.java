package net.tqxr.lib.arrayfunctions;

import net.tqxr.lib.stringfunctions.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayUtilTest {

    @Test
    public void canTransformArrayListToStringArray() {
        String[] strings = {"a", "b", "C"};

        ArrayList<String> moreStrings = new ArrayList<>();
        Collections.addAll(moreStrings, strings);

        moreStrings.add("d");
        moreStrings.add("e");
        moreStrings.add("F");

        String[] stringArr = new String[moreStrings.size()];
        moreStrings.toArray(stringArr);

        String[] expectedStrings = {"a", "b", "C", "d", "e", "F"};
        assertThat(stringArr)
                .isEqualTo(expectedStrings)
                .withFailMessage("String arrays should match :(");

    }

    @Test
    public void arrayUtilCanMergeArrayIntoArrayList() {
        String[] arrA = new String[] { "two", "three" };
        ArrayList<String> arrB = new ArrayList<>();
        arrB.add("one");

        ArrayUtil<String> arrayUtil = new ArrayUtil<>();

        Collection<String> strings = arrayUtil.mergeArrays(arrB, arrA);

        String[] expectedStrings =new String[] {"one", "two", "three"};

        assertThat(strings)
                .containsExactly(expectedStrings);

    }

}
