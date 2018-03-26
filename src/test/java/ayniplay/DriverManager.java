package ayniplay;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager{


    //java -jar selenium-server-standalone-3.11.0.jar -role hub
    //java -jar selenium-server-standalone-3.11.0.jar -role node  -hub http://localhost:4444/grid/register

    private static final ThreadLocal<Long> ID = new ThreadLocal<Long>();
    private static final ThreadLocal<WebDriver> DriverInstance = new ThreadLocal<WebDriver>();
    public static Long GetID(){
        Long id = ID.get();
        if ( id == null){
            id = Thread.currentThread().getId();
            ID.set(id);
        }
        return id;
    }
    public static WebDriver GetDriver() throws MalformedURLException{
        WebDriver d = DriverManager.DriverInstance.get();
        if ( d == null){
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            d = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap);
            d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            DriverManager.DriverInstance.set(d);
        }
        return d;
    }
}