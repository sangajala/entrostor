package voyanta.ui.datamanager;

import cucumber.api.Scenario;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;

import voyanta.ui.pageobjects.LoginPage;
import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by sriramangajala on 23/07/2014.
 */
public class DataSubmissionStepDev extends SuperClassDataManage {

   // DataManagerInterface dataManagerInterface;
    LoginPage signInPage;
   // static String fileFolder;
    WebDriver driver;

    
    @Before ("@DataSubmission")
    public void before()
    {  	
    	// dataManagerInterface=new DataManagerInterface();
    	
    	if (VoyantaDriver.driverNotSet()) {
    		String username=System.getProperty("user.name");
    		System.out.println(" driver is not set");
    	  if(System.getProperty("os.name").toLowerCase().contains("mac"))
          {
          	fileFolder = PropertiesLoader.getProperty("mac_boxFolder")+
          			PropertiesLoader.getProperty("mac_dataSubmissionFolder");
          }
          else
          {
          	fileFolder = PropertiesLoader.getProperty("windows_boxFolder").replace("Administrator", username)+
          			PropertiesLoader.getProperty("windows_dataSubmissionFolder");
          }
		
			driver = VoyantaDriver.getCurrentDriver();
            driver.get("http://test.voyanta.com");
            signInPage=new LoginPage();
            VUtils.waitFor(5);
            signInPage.signIn(PropertiesLoader.getProperty("admin_username"),PropertiesLoader.getProperty("password") );
		
       
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
    
    @Then("^correct message '(.*?)' and status '(.*?)' is showed on the upload page$")
    public void correct_and_is_showed_on_the_upload_page(String msg, String uploadResult) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
          super.dataManagerInterface.check_uploadMSG(msg);
          super.dataManagerInterface.check_uploadResult(uploadResult);
    }


    @When("^user delete row '(\\d+)'$")
    public void user_delete_row(int rowNumber) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.deleteSingleErrorRow(rowNumber);
    }

    @Then("^user see '(\\d+)' delete single row button$")
    public void user_see_delete_single_row_button(int errorRowNumbers) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.checkNumberOfDeleteErrorButtons(errorRowNumbers);
    }


    @After
    public void stop(Scenario scenario)
    {

        if(scenario.isFailed())
        {
            DateFormat dateFormat= new SimpleDateFormat("MM-dd-HH-mm-ss");
            VUtils.captureScreen(scenario);

        }
    }

    @When("^user click delete all '(.*)'$")
    public void user_click_delete_all_Error(String deleteAll) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.deleteAll(deleteAll);
    }

    @When("^user confirm '(.*)' delete$")
    public void user_confirm_yes_delete(String yesOrNo ) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.confirmDelete(yesOrNo);
    }

    @Then("^user '(.*)' see delete all error button$")
    public void user_cannot_see_delete_all_error_button(String exist) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.checkExistOfDeleteAllEW(exist);
    }

    @Then("^user '(.*)' see delete all warning and error button$")
    public void user_cannot_see_delete_all_warning_and_error_button(String exist) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.checkExistOfDeleteAll(exist);
    }

    @When("^user confirm '(.*)' delete all$")
    public void user_confirm_yes_delete_all(String yesOrNo) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.confirmDeleteAll(yesOrNo);
    }

    @Then("^user see error/warning successfully deleted msg$")
    public void user_see_error_warning_successfully_deleted_msg() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        super.dataManagerInterface.checkdeleteSucMSG();
    }

}

