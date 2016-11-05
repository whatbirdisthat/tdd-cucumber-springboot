package net.tqxr.lib.stringfunctions;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    public String transformUpper(String inputString) {
        return inputString.toUpperCase();
    }

    public String transformLower(String inputString) {
        return inputString.toLowerCase();
    }
}
