package ayniplay.steps;

import ayniplay.DriverManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@StepDefAnnotation
public class SimpleSteps{


    //mvn test  -Dsurefire.suiteXmlFiles=testngP.xml
    @Given("^a precondition$")
    public void a_precondition() throws Throwable {

        long id = Thread.currentThread().getId();

        System.out.println("Thread " + id);

        WebDriver d = DriverManager.GetDriver();
        d.get("https://www.google.com.pe");

        Thread.sleep(5000);

    }

    @When("^an action takes place$")
    public void an_action_takes_place() throws Throwable {
        WebDriver d = DriverManager.GetDriver();
        WebElement input = d.findElement(By.id("lst-ib"));
        input.sendKeys("testing ");
    }

    @Then("^the expected behavior is displayed$")
    public void the_expected_behavior_is_displayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebDriver d = DriverManager.GetDriver();
        WebElement btn = d.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]"));
        btn.submit();
        Thread.sleep(10000);
    }
}