package net.tqxr.cucumber.steps;

import cucumber.api.DataTable;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFunctionsTest {

    @Test
    public void proveThatMapsAreAWasteOfTime() {
        Map<String, String> reallyHard = new HashMap<>();
        reallyHard.put("col1", "val1");
        reallyHard.put("col2", "val2");
        reallyHard.put("col3", "val3");

        String[] columnNamesArr = new String[1];
        reallyHard.values().toArray(columnNamesArr);

        String[] valuisArr = new String[reallyHard.size()];
        List<String> valuesList = Arrays.asList(valuisArr);

        DataTable ohmygod = DataTable.create(valuesList, "", columnNamesArr);

        System.out.println("DATATABLE:");
        ohmygod.asLists(String.class).forEach((v) -> {
                    v.forEach(System.out::print);
                    System.out.println("**");
                }
        );


    }


    @Test
    public void canProvideListOfListOfStrings() throws Exception {
    /*

    PROVE WE CAN FUDGE A TABLE IN THIS FORMAT:
    AS A LIST OF LISTS OF STRING

    | a | b | c |
    | d | e | f |
    | g | h | i |
    | j | k | l |

     */

        List<String> tableData = Arrays.asList("a", "b", "c");
        List<String> tableData2 = Arrays.asList("d", "e", "c");
        List<String> tableData3 = Arrays.asList("g", "h", "c");
        List<String> tableData4 = Arrays.asList("j", "k", "c");

        List<List<String>> rows = Arrays.asList(
                tableData,
                tableData2,
                tableData3,
                tableData4
        );

        System.out.println("DATATABLE:");
        DataTable pointlessTable = DataTable.create(rows);

        pointlessTable.asLists(String.class).forEach((v) -> {
                    v.forEach(System.out::print);
                    System.out.println("**");
                }
        );

    }

    @Test
    public void canProvideAKeyValueColumnarMap() throws Exception {
    /*

    PROVE WE CAN FUDGE A TABLE IN THIS FORMAT:
    AS A MAP

    | a | b |
    | d | e |
    | g | h |
    | j | k |

     */

        List<String> tableData = Arrays.asList("a", "b");
        List<String> tableData2 = Arrays.asList("d", "e");
        List<String> tableData3 = Arrays.asList("g", "h");
        List<String> tableData4 = Arrays.asList("j", "k");

        List<List<String>> rows = Arrays.asList(
                tableData,
                tableData2,
                tableData3,
                tableData4
        );

        System.out.println("DATATABLE:");
        DataTable pointlessTable = DataTable.create(rows);
        pointlessTable.asList(String.class).forEach((v) ->
                System.out.println(" - V: " + v)
        );

        pointlessTable.asMap(String.class, String.class).forEach((k, v) ->
                System.out.println(
                        String.format("KEY: %s, VAL: %s",
                                k, v)
                )
        );

    }

    @Test
    public void canCreateTableOfObjectsWithoutGherkinText() {
     /*

    PROVE WE CAN FUDGE A TABLE IN THIS FORMAT:

    | col1 | col2 | col3 |
    | d | e | f |
    | g | h | i |
    | j | k | l |

     */

        String[] columnNames = new String[]{
                "col1",
                "col2",
                "col3"
        };


        List<String> tableData = Arrays.asList(columnNames);
        List<String> tableData2 = Arrays.asList("a", "b", "c");
        List<String> tableData3 = Arrays.asList("k", "h", "q");
        List<String> tableData4 = Arrays.asList("m", "p", "g");

        List<List<String>> rows = Arrays.asList(
                tableData,
                tableData2,
                tableData3,
                tableData4
        );

        DataTable dataTable = DataTable.create(rows, "", columnNames);
        dataTable.asList(TestColumnThingy.class).forEach(System.out::println);

    }

    private class TestColumnThingy {
        String col1;
        String col2;
        String col3;

        @Override
        public String toString() {
            return String.format(
                    "TableColumnThingy: col1='%s', col2='%s', col3='%s'",
                    col1, col2, col3);
        }
    }

}