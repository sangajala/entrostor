package voyanta.ui.utils;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

//import com.venkyold.org.abstractTest;

public class CommonUtil {

	public String HOST = "localhost";
	public String BROWSER = "*chrome";
	public String URL = "http://sangajala-001-site1.smarterasp.net/";
	public static Random random = new Random();
	public static String newEmail;
    public static Selenium selenium;
    public void pause()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
    public static Selenium getSelenium()
    {
        return selenium;
    }
    public void setSelenium(Selenium selenium)
    {
        CommonUtil.selenium = selenium;
    }

    public static void generteEmail()
    {
        int emailprefix = random.nextInt();
        String newEmail = String.valueOf(emailprefix)+"@example.com";
        System.out.println(newEmail);
        setEmail(newEmail);
    }

    public static void setEmail(String newEmail)
    {
        CommonUtil.newEmail = newEmail;
    }
    public String getNewEmail() {
        generteEmail();
        return CommonUtil.newEmail;
    }

    public String getEmail(){
        return CommonUtil.newEmail;
    }

    public static String getCurrentEmail() {
        return newEmail;
    }


    public static WebDriver getDriver()
    {
        return null;
//        return abstractTest.getDriver();
    }
    public void setDriver(WebDriver driver)
    {
    }

    public static void selectElement(By by,String text)
    {
        (new Select(getDriver().findElement(by))).selectByVisibleText(text);
    }

    public static void WaitTillGreenIsShown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static boolean isTextPresent(String Text )
    {
    return getDriver().getPageSource().contains(Text);
    
    }
    
    public static boolean isElementPresent(By by) {
        return (getDriver().findElements(by).size() > 0) ? true : false;
//        String s = getDriver().findElement(by).getAttribute("src");
//        if(s.equals("upload\a\logo.jpg"))
    }

    public static void clickByLink(String product) {
        getDriver().findElement(By.linkText(product)).click();
    }
	public static void getRandomString() {
		
	//	return "shoonya";//UUID.randomUUID().toString();
	}
	
	 public void loadProperties()  {
	        Properties properties = new Properties();
	        try {
	            properties.load(new FileInputStream("src/main/resources/system.properties"));
	        } catch (IOException e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        }
	        HOST = properties.getProperty("HOST");
	        BROWSER = properties.getProperty("BROWSER");
	        URL = properties.getProperty("URL");
	    }

	    public static void waitFor(int pollingEveryMs) {
	        try {
	            Thread.sleep(pollingEveryMs);
	        } catch (InterruptedException e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        }
	    }
}
