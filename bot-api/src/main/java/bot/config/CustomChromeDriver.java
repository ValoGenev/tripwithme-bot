package bot.config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static bot.utils.Constants.*;

public class CustomChromeDriver {

    private final ChromeDriver chromeDriver;

    public CustomChromeDriver() {

        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);

        //Create a map to store  preferences
        Map<String, Object> prefs = new HashMap<String, Object>();

        //add key and value to map as follow to switch off browser notification
        //Pass the argument 1 to allow and 2 to block
        prefs.put("profile.default_content_setting_values", 2);

        //Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // set ExperimentalOption - prefs
        options.setExperimentalOption("prefs", prefs);


        options.addArguments("--disable-notifications");
// set ExperimentalOption - prefs
        options.setExperimentalOption("prefs", prefs);


//        options.addArguments("enable-automation");
     //   options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--dns-prefetch-disable");
//        options.addArguments("--disable-gpu");


        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

//        Proxy p = new Proxy();
//        p.setHttpProxy("51.75.147.35:3128");
//
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability(CapabilityType.PROXY,p);
//
//        options.setCapability(CapabilityType.PROXY,p);

     //  options.addArguments("--proxy-server=167.172.191.249:44048");

        chromeDriver = new ChromeDriver(options);

        chromeDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        chromeDriver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
    }

    public ChromeDriver getChromeDriver(){
        return chromeDriver;
    }


}
