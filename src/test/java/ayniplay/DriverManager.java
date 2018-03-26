package ayniplay;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverManager{

    //mvn test  -Dsurefire.suiteXmlFiles=testngP.xml
    //java -jar selenium-server-standalone-3.11.0.jar -role hub
    //java -jar selenium-server-standalone-3.11.0.jar -role hub -port 80
    //java -jar selenium-server-standalone-3.11.0.jar -role node  -hub http://localhost:4444/grid/register
    //java -jar selenium-server.jar -role node  -hub http://10.118.128.250:80/grid/register
    private static String OS = System.getProperty("os.name").toLowerCase();

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
    public static void ReleaseDriver(){
        WebDriver d = DriverManager.DriverInstance.get();
        if ( d != null){
            try{
                d.close();
                d.quit();
            }
            finally {
                DriverManager.DriverInstance.remove();
            }
        }
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    //mvn clean  test -Dui_runtime=hub -Dus_browser=chrome -Dui_server=http://10.118.128.250:80/wd/hub
    //-Denv_var=%MY_ENV_VAR% (Windows)
    //-Denv_var=$MY_ENV_VAR (Linux)
    public static WebDriver GetDriver() throws MalformedURLException{

        WebDriver driver = DriverManager.DriverInstance.get();
        if (driver != null)
            return driver;

        String ui_server = System.getProperty("ui_server");//"http://127.0.0.1:4444/wd/hub"
        String driver_kind = System.getProperty("ui_runtime"); // default local, server, hub
        String browser_kind = System.getProperty("us_browser"); // default firefox, chrome, ie



        if(driver_kind == null  || driver_kind.isEmpty()){
            driver_kind = "local";
        }
        driver_kind = driver_kind.toLowerCase();
        if(browser_kind == null  || browser_kind.isEmpty()){
            browser_kind = "firefox";
        }
        browser_kind = browser_kind.toLowerCase();

        System.out.println("=========================================");
        System.out.println("ui server  " + ui_server);
        System.out.println("ui runtime " + driver_kind);
        System.out.println("ui browser " + browser_kind);
        System.out.println("=========================================");


        switch (driver_kind){
            case "local":

                switch (browser_kind){
                    case "firefox":
                        //System.setProperty("webdriver.chrome.driver", "/Users/gvalderrama/bin/chromedriver");
                        //ChromeDriver browser = new ChromeDriver();
                        //export PATH=$PATH:/Users/gvalderrama/Documents/tools/geckodriver
                        // echo "export PATH=\"/Users/gvalderrama/Documents/tools:$PATH\"" > .bash_profile

                        if ( DriverManager.isMac()){
                            System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
                        }

                        FirefoxDriver browser = new FirefoxDriver();
                        driver = browser;
                    break;
                    default:
                        throw new IllegalArgumentException("Invalid : " + browser_kind);
                }

                break;
            case "hub":

               Capabilities cap = null;

               switch (browser_kind){
                   case "firefox":
                       cap = DesiredCapabilities.firefox();
                   break;
                   case "chrome":
                       cap = DesiredCapabilities.chrome();
                   break;
                   default:
                       throw new IllegalArgumentException("Invalid : " + browser_kind);
               }
               driver = new RemoteWebDriver(new URL(ui_server), cap);
               driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            break;

            default:
                throw new IllegalArgumentException("Invalid : " + driver_kind);
        }
        DriverManager.DriverInstance.set(driver);
        return driver;
    }
}