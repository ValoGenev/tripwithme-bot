package bot.processor;

import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bot.config.ExecutorConfiguration;

import static java.lang.String.format;
import static bot.utils.Constants.*;
import static bot.utils.Constants.XPATH_TO_LOGIN_ELEMENT;

public class BotNavigationService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ChromeDriver chromeDriver;

    public BotNavigationService(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public void openFaceBook() throws InterruptedException {
        LOGGER.info(OPEN_FACEBOOK_MESSAGE);

        chromeDriver.get(FACEBOOK_URL);
    }

    public void loginToFacebook() throws InterruptedException {
        LOGGER.info(format(LOGIN_TO_FACEBOOK_MESSAGE,EMAIL,PASS));

        Thread.sleep(10000);

        chromeDriver.findElementByXPath(XPATH_TO_FACEBOOK_ACCEPT_COOKIES).click();

        chromeDriver.findElementById(XPATH_TO_EMAIL_ELEMENT).sendKeys(EMAIL);
        chromeDriver.findElementById(XPATH_TO_PASS_ELEMENT).sendKeys(PASS);
        chromeDriver.findElementByXPath(XPATH_TO_LOGIN_ELEMENT).click();
        Thread.sleep(2000);

    }

    public void loginToFacebook(String email,String password) throws InterruptedException {
        LOGGER.info(format(LOGIN_TO_FACEBOOK_MESSAGE,email,password));

        Thread.sleep(10000);

        chromeDriver.findElementByXPath(XPATH_TO_FACEBOOK_ACCEPT_COOKIES).click();

        chromeDriver.findElementById(XPATH_TO_EMAIL_ELEMENT).sendKeys(email);
        chromeDriver.findElementById(XPATH_TO_PASS_ELEMENT).sendKeys(password);
        chromeDriver.findElementByXPath(XPATH_TO_LOGIN_ELEMENT).click();
        Thread.sleep(2000);

    }

    public void logoutFromFacebook(){

    }



    public void navigateToGroup(String groupName,String groupUrl){
        LOGGER.info(format(OPEN_FACEBOOK_GROUP_MESSAGE,groupName));

        if(!groupUrl.endsWith(URL_EXTENSION_FOR_CHRONOLOGICAL)){
            groupUrl += URL_EXTENSION_FOR_CHRONOLOGICAL;
        }

        chromeDriver.navigate().to(groupUrl);
    }


    public BotExecutorService prepareExecutor(ExecutorConfiguration configuration){
        return new BotExecutorService(chromeDriver,configuration);
    }

}
