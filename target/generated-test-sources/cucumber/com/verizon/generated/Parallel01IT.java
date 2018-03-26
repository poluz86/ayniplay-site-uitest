package com.verizon.generated;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = {"F:/temp/ayniplay-site-uitest/target/classes/login.feature"},
        plugin = {"json:F:/temp/ayniplay-site-uitest/target/cucumber-parallel/1.json"},
        monochrome = false,
        tags = {},
        glue = {"com.verizon.steps"})
public class Parallel01IT extends AbstractTestNGCucumberTests {
}
