package voyanta.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by sriramangajala on 22/07/2014.
 */
public class WaitUtils {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(WaitUtils.class);
    
    public static void waitFor(int i) {
        try {
            Thread.sleep(i*1000);

        }
        catch (InterruptedException e)
        {
        }
    }
    
    public static void waitForElementShown(WebDriver driver, By by) {

        LOGGER.info("Started waiting for the Element Visible happen...");
        WebDriverWait webDriverWait = new WebDriverWait(driver,90);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LOGGER.info("Waiting over...");
    }

    public static void waitForTextIsNotPresentInElement(WebDriver driver, By by, String text) {
        LOGGER.info("Started waiting for the text to be shown...");
        WebDriverWait webDriverWait = new WebDriverWait(driver,90);
        int i=0;
        while(driver.findElement(by).getText().contains(text)&&i<90)
        {
            VUtils.waitFor(1);
            i++;
            LOGGER.info("Waiting for 1 sec... for text to be gone: "+text);
        }
//        webDriverWait.until(ExpectedConditions.(driver.findElement(by),text));
        LOGGER.info("Waiting over...");
    }
    
    public static void waitForElement(WebElement defaultElement) {
        for(int i=0;i<=(Integer.parseInt(PropertiesLoader.getProperty("MAX_TIME_OUT")));i++) {
        try {

                if (defaultElement.isDisplayed())
                    return;
                waitFor(1);
            LOGGER.info("Waiting for 1 sec ....");

        }catch (NoSuchElementException e)
        {
            LOGGER.info("Waiting for 1 sec ....");
            waitFor(1);
        }
        }
    }
}
