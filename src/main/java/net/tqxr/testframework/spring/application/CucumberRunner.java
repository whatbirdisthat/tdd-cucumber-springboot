package net.tqxr.testframework.spring.application;

import cucumber.api.cli.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class CucumberRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

        ArrayList<String> moreStrings = new ArrayList<>();
        Collections.addAll(moreStrings, strings);

        moreStrings.add("-p");
        moreStrings.add("pretty");
        moreStrings.add("--add-plugin");
        moreStrings.add("html:target/cucumber");
        moreStrings.add("-g");
        moreStrings.add("net.tqxr.cucumber.steps");
        moreStrings.add("classpath:features");

        String[] stringArr = new String[moreStrings.size()];
        moreStrings.toArray(stringArr);

        try {
            Main.main(stringArr);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("Exception encountered, or non-zero exit code.");
    }
}
