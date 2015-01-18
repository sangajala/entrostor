package voyanta.ui.datamanager;

import cucumber.api.java.Before;

import org.openqa.selenium.WebDriver;


//import voyanta.ui.datamanager.BrowserDriver.BrowserCleanup;
import voyanta.ui.pageobjects.LoginPage;
import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 23/07/2014.
 */
public class BRStepDefs {

    DataManagerInterface dataManagerInterface;
    LoginPage signInPage;
    String fileFolder;
    WebDriver driver;

    @Before ("@businessRules")
    public void before()
    {  	
    	
    	if (VoyantaDriver.driverNotSet()) {
    		System.out.println(" driver is not set");
    	  if(System.getProperty("os.name").toLowerCase().contains("mac"))
          {
          	fileFolder = PropertiesLoader.getProperty("mac_boxFolder")+
          			PropertiesLoader.getProperty("mac_BRFiringFolder");
          }
          else
          {
          	fileFolder = PropertiesLoader.getProperty("windows_boxFolder")+
          			PropertiesLoader.getProperty("windows_BRFiringFolder");
          }
		
			driver = VoyantaDriver.getCurrentDriver();
            driver.get("http://test.voyanta.com");
            signInPage=new LoginPage();
            VUtils.waitFor(5);
            signInPage.signIn("tester@reportscal.com", "password1!");
		
       
        	Runtime.getRuntime().addShutdownHook(new Thread()
        	{
                public void run() {
                	System.out.print("goes to finally");
                    VoyantaDriver.quit();
                    driver.quit();
                    driver=null;
                   
             }
        	});	
        
       
    }
    
    }



}
