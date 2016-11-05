package net.tqxr.testframework.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.tqxr.lib,net.tqxr.cucumber.support")
public class CucumberConfiguration {

}
