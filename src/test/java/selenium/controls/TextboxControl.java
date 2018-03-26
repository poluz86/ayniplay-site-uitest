package selenium.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.ElementKind;

import java.lang.annotation.ElementType;

public class TextboxControl extends BaseControl {

    public  TextboxControl(WebDriver Driver){
        super(Driver);
    }

    public void setText(String locator, String value){
        //WebDriverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(getElementByType(ElementType.XPATH, locator)));
        By selector = this.getElementByType(ElementKind.XPATH, locator);
        this.Driver.findElement(selector).sendKeys(value);
    }

    public void clearText(String locator){
        //WebDriverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(getElementByType(ElementType.XPATH, locator)));
        By selector = this.getElementByType(ElementKind.XPATH, locator);
        this.Driver.findElement(selector).clear();
    }
}
