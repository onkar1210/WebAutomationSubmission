package BasePackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Base {
    public static WebDriver driver;

    @BeforeMethod
    public void launchBrowser() {
        String browser = readProperty("browser");
        // System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
        if(browser.equalsIgnoreCase("edge")){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars"); //its depricated
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation")); //--disable-infobars
            options.addArguments("--disable-extensions");
            //    options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            //    options.addArguments("--incognito");
            options.addArguments("ignore-certificate-errors"); //it will accept the certificate
            driver = WebDriverManager.edgedriver().capabilities(options).create();  //This is using web driver manager (using a maven by downloading web driver manager) used to directly setup and invoke browser drivers
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            WebDriverManager.chromedriver().setup();  //This is using web driver manager (using a maven by downloading web driver manager) used to directly setup and invoke browser drivers
            driver = new ChromeDriver(options);
        }
        else {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            DesiredCapabilities cp = new DesiredCapabilities();
            cp.setCapability(EdgeOptions.CAPABILITY, options);
            options.merge(cp);
            driver = new EdgeDriver(options);  //This is using Selenium manager inbuilt in selenium (above version 4.6.0) used to directly setup and invoke browser drivers
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        driver.manage().window().maximize();
        driver.get(readProperty("URL"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }

    public String readProperty(String key) {
        Properties prop = new Properties();
        InputStream readFile = null;

        try {
            readFile = new FileInputStream("src/main/resources/config.properties");

            prop.load(readFile);

            return prop.getProperty(key);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (readFile != null) {
                try {
                    readFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(key);
    }

}
