package voyanta.ui.pageobjects;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import voyanta.ui.pagecontainers.LoginPageContainer;
import voyanta.ui.utils.PropertiesLoader;

/**
 * @author ting 
 *  This class defines all the relevant elements and services provided by Sign In Page
 *
 */
public class LoginPage extends abstractWebPage {
    public static final int MAX_TIME_OUT = 60;
    static Logger LOGGER = Logger.getLogger(LoginPage.class);
    LoginPageContainer pageContainer;

    public LoginPage(){
		 //this.driver=selenium;
        pageContainer = LoginPage.getDataContainer(LoginPageContainer.class);
	 }
	
	 public void signIn(String userName, String passWord){
         LOGGER .info("Logging in with the username and password :"+userName+" , "+passWord);
		 pageContainer.inputEmail.sendKeys(userName);
         pageContainer.inputPassword.sendKeys(passWord);
         pageContainer.buttonSignIn.click();



		// WebElement myDynamicElement = (new WebDriverWait(driver, 10))
		//		  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tabs.original-tabs>ul>li>a>span")));
		 try{
				Thread.sleep(8000);
				}
				catch(InterruptedException i){
					System.out.println("sleep error");
				}
         Assert.assertTrue("Login unsuccessful. Please check if application is up and running!",pageContainer.body.getText().contains("Activity Stream"));
         LOGGER .info("Loggin success with the username and password: "+userName+" , "+passWord);
	 }
	 
	
	 public void forgotPW(){
		 pageContainer.linkFP.click();
	 }
     public static String getURL(){
    	 return URL;
     }

    public void waitForFirstPageToLoad(WebDriver driver,By by) throws InterruptedException {
        driver.switchTo().frame("report-page");
        waitForElementLoaded(driver,by);
        driver.switchTo().defaultContent();
    }

    private void waitForElementLoaded(WebDriver driver,By by) throws InterruptedException {
      int i=1000;
      int counter=0;
      while((!elementPresent(driver,by))&&counter<MAX_TIME_OUT)
        {

            Thread.sleep(i);
            counter ++;
            System.out.println("Waiting for a sec....");
        }
    }

    private boolean elementPresent(WebDriver driver,By by) {
        try {
            return driver.findElement(by).isDisplayed();

        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void signIn() {
        signIn(PropertiesLoader.getProperty("username"),PropertiesLoader.getProperty("password"));
    }
}
