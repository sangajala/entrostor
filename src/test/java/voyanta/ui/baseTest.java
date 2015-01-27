package voyanta.ui;


import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import voyanta.ui.pageobjects.LoginPage;
import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VoyantaDriver;

import java.net.MalformedURLException;
//#  for your reference
/**
 * Created by sriramangajala on 22/07/2014.
 */
public class baseTest {
    static String URL;
    static LoginPage signInPage;
    static WebDriver driver;
    static String propertiesFileName = "src/main/resources/";
    String fileName ;
    static Logger LOGGER = Logger.getLogger(baseTest.class);


    @BeforeClass
    public static void driverUp() throws MalformedURLException, InterruptedException {

	   System.setProperty("webdriver.chrome.driver", "/Users/sriramangajala/Documents/Automated-UAT/voyanta-availablity-tests/src/main/resources/chromedriver 5");
        driver= VoyantaDriver.getCurrentDriver();
//////       System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chrome\\chromedriver.exe");
//        Capabilities capabilities = DesiredCapabilities.chrome();
//        LOGGER.info("Server URL is " + PropertiesLoader.getProperty("server"));
//
//        driver = new RemoteWebDriver(new java.net.URL(PropertiesLoader.getProperty("server")),capabilities);
        VoyantaDriver.getCurrentDriver().manage().deleteAllCookies();
        URL = PropertiesLoader.getProperty("ui_url");
        LOGGER.info("Loading url "+URL+" ....");
        driver.get(URL);

        driver.manage().window().maximize();

//        signInPage=new LoginPage();
//        PageFactory.initElements(driver, signInPage);
//        signInPage.signIn(PropertiesLoader.getProperty("username"), PropertiesLoader.getProperty("password"));
//        LOGGER.info("Test phase:"+System.getProperty("test_phase"));


//        signInPage.waitForFirstPageToLoad(driver,(By.className("QvContent")));

    }

    @AfterClass
    public static void tearDown(){
//        VoyantaDriver.quit();
        driver.quit();
        driver=null;

    }
}
