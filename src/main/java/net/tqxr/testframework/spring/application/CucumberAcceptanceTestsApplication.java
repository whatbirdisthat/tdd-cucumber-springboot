package net.tqxr.testframework.spring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CucumberAcceptanceTestsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CucumberRunner.class, args);
    }
}
