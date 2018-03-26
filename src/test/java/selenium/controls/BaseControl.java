package selenium.controls;

import org.openqa.selenium.WebDriver;
import selenium.SeleniumElement;

public abstract class BaseControl extends SeleniumElement {

    public BaseControl(WebDriver Driver) {
        super(Driver);
    }

}
