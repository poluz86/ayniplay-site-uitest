package ayniplay.steps;



import ayniplay.DriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.java.StepDefAnnotation;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

@StepDefAnnotation
public class CommonSteps {

    @Before
    public void Before(Scenario scenario) throws MalformedURLException {

        long dd =  Thread.currentThread().getId();
        WebDriver d = DriverManager.GetDriver();

    }
    @After
    public void After() throws MalformedURLException {
        WebDriver d = DriverManager.GetDriver();
        if (d!= null)
        {
            d.close();
            d.quit();
        }
    }
}