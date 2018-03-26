package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class SeleniumElement {

    protected WebDriver Driver;
    public SeleniumElement(WebDriver Driver){
        this.Driver = Driver;
    }


    public By getElementByType(ElementKind type, String locator){
        switch (type){
            case ID: return By.id(locator);
            case NAME: return By.name(locator);
            case XPATH: return By.xpath(locator);
            case CSS: return By.cssSelector(locator);
            case TAGNAME: return By.tagName(locator);
            case LINKTEXT: return By.linkText(locator);
            case CLASSNAME: return By.className(locator);
            case PARTIALLINKTEXT: return By.partialLinkText(locator);

            default: throw new IllegalArgumentException("Element type not valid");
        }
    }

}
