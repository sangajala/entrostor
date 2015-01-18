package voyanta.ui.datamanager;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.openqa.selenium.WebDriver;

import voyanta.ui.pageobjects.LoginPage;
import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 23/07/2014.
 */
public class BusinessRuleFiringStepDefs extends SuperClassDataManage{

    LoginPage signInPage;
    WebDriver driver;

    
    @Before ("@businessRulesfiring")
    public void before()
    {  	
    	
    	if (VoyantaDriver.driverNotSet()) {
    		String username=System.getProperty("user.name");
    		System.out.println(" driver is not set");
    	  if(System.getProperty("os.name").toLowerCase().contains("mac"))
          {
          	fileFolder = PropertiesLoader.getProperty("mac_boxFolder")+
          			PropertiesLoader.getProperty("mac_BRFiringFolder");
          }
          else
          {
          	fileFolder = PropertiesLoader.getProperty("windows_boxFolder").replace("Administrator", username)+
          			PropertiesLoader.getProperty("windows_BRFiringFolder");
          }
		
			driver = VoyantaDriver.getCurrentDriver();
            driver.get("http://test.voyanta.com");
            signInPage=new LoginPage();
            VUtils.waitFor(5);
            signInPage.signIn("tester@entreprenuers.com", "password1!");
		
       
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

    @Given("^user arrives on Upload page$")
    public void user_arrives_on_Upload_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
     dataManagerInterface=new DataManagerInterface();
    	dataManagerInterface.go_to_UploadPage();
    	//dataManagerInterface.go
    	//dataManagerInterface.
    }
    
    
    @Given("^user upload DST '(.*)'$")
    public void user_upload_DST_Replace_LoV_with_non_LoV_xlsx_and_go_to_submission_page(String fileList) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    	String[] list=fileList.split(",");
    	dataManagerInterface.upload_files(fileFolder, list);
        
    }

    @And ("user goes to submission page$")
    public void user_go_to_submission_page(){
    	dataManagerInterface.submitFile();
    }

    @Then("^user see '(.*?)' is displayed for the submission on '(.*)'$")
    public void user_see_error_is_displayed_for_the_submission(String brResult, String page) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    	dataManagerInterface.checkTheValidationResult(brResult,page);
        //throw new PendingException();
    	}

    @Then("^user check the 'Review' page$")
    public void user_check_the_Review_page() throws Throwable {
    	dataManagerInterface.go_to_review_Page();
       // throw new PendingException();
    }
   
    @Then("^user check the result for '(.*?)' for '(.*?)' in row '(.*?)' with value '(.*?)'$")
    public void user_check_the_result_for_Asset_Valuation_Valuation_Type_Insurable(String dstType, String columnName, String rowNumber, String Value) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
/*       String[] rowList=rowNumber.split(",");
       String[] valueList=Value.split(",");
       if(rowList.length!=valueList.length){
    	   new RuntimeException("the row length and value length doesn't match");
       }
       else{
    	   for(int i=0; i<rowList.length; i++){
    		   System.out.println("to search for value on row "+ rowList[i]);*/
       dataManagerInterface.check_replaced_column(dstType,columnName,rowNumber,Value);
                 
    }

    //Then user check the BR fired for the right '<ObjectType>' right '<Column>' in correct '<Row>' with correct '<Sentinel>' and correct '<MSG>' and sentinel column also shows correct <BRValue>
    @Then ("user check the BR fired for the right '(.*?)' right '(.*?)' in correct '(.*?)' with correct '(.*?)' and correct '(.*?)' and sentinel column also shows correct '(.*?)'$")
    public void check_WarningAndError(String ObjectType, String column, String row, String sentinel, String msg, String BRValue){
    	dataManagerInterface.check_warningError_rule(ObjectType, column,row, sentinel,msg,BRValue);
    }

/*    @Then("^user check the <message>$")
    public void user_check_the_message() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
      //  throw new PendingException();
    }

    @When("^open error if exists$")
    public void open_error_if_exists() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
      //  throw new PendingException();
    }

    @Then("^check <message(\\d+)>$")
    public void check_message(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
     //   throw new PendingException();
    }*/

}
