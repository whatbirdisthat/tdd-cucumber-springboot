package net.tqxr.testframework.reporting;

import org.springframework.stereotype.Component;

@Component
class TestNameTransformer {

    String transformLowerCaseFirstLetterToUpperCase(String inputString) {

        // http://stackoverflow.com/a/33352947/1185717
        return Character.toUpperCase(inputString.charAt(0)) + inputString.substring(1);

    }

    String transformCamelTestNameToSpacedWords(String inputString) {


        // http://stackoverflow.com/a/2560017/1185717
        return inputString.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );

    }

    String transformTestName(String testName) {
        String upperFirstLetter = transformLowerCaseFirstLetterToUpperCase(testName);
        String spacedWords = transformCamelTestNameToSpacedWords(upperFirstLetter);
        return spacedWords;
    }


    String passFailSkip(boolean isFailed, boolean isIgnored) {
        AsciiColourHelper.AsciiCode colouredString = AsciiColourHelper.GREEN;
        char statusChar = '\u2714'; // tick mark
        if (isFailed) {
            statusChar = '\u2718'; // cross
            colouredString = AsciiColourHelper.RED;
        }
        if (isIgnored) {
            statusChar = '\u2615'; // ☕ cute coffee cup :)
            colouredString = AsciiColourHelper.YELLOW;
        }
        return String.format("%s%s%s ", colouredString, statusChar, AsciiColourHelper.RESET);
    }

}
