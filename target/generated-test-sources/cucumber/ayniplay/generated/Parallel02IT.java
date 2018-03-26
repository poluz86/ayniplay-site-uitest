package ayniplay.generated;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = {"F:/temp/ayniplay-site-uitest/target/classes/login.feature"},
        plugin = {"json:F:/temp/ayniplay-site-uitest/target/cucumber-parallel/2.json"},
        monochrome = false,
        tags = {},
        glue = {"ayniplay.steps"})
public class Parallel02IT extends AbstractTestNGCucumberTests {
}
