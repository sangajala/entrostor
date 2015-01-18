package voyanta.ui.investor;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.codec.binary.Base64;
import voyanta.ui.datamodel.DataSheetsView;
import voyanta.ui.datamodel.VHashMap;
import voyanta.ui.datamodel.ValidationUtils;
import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sriramangajala on 20/08/2014.
 */
public class ReportsStepDefs {

    ReportsInterface reportsInterface;
    static String boxFolder;
    @Before()
    public void start()
    {
        VoyantaDriver.getCurrentDriver().navigate().refresh();
       // reportsInterface = new ReportsInterface();
        
        if(System.getProperty("os.name").contains("mac"))
        	boxFolder=PropertiesLoader.getProperty("mac_boxFolder").replace("Administrator",System.getProperty("user.name"));
        else
        	boxFolder=PropertiesLoader.getProperty("windows_boxFolder").replace("Administrator",System.getProperty("user.name"));
    }




//    @Given("^user navigates to Financial performance page$")
//    public void user_navigates_to_finance_page()
//    {
//        reportsInterface.gotoFinancePage();
//    }



    @Given("^i see image$")
    public void image() throws Throwable {

    }
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }



    @When("^selects the filters with year as '(.*)',Sector as '(.*)' and Region as '(.*)'$")
    public void selects_year_and_Sector_Office(String year,String sector,String region) throws Throwable {
        reportsInterface.selectFilters(year,sector,region);
        VUtils.waitFor(5);
    }




    @Then("^the saved report should match with expected report with name '(.*)'$")
    public void the_saved_report_should_match_with_expected_report_with_name(String fileName) throws Throwable {
        List<VHashMap> expData;
        File file = VUtils.getDownloadedFile(".xls");

        if(System.getProperty("os.name").contains("mac"))
            VUtils.copyFile(file,fileName,boxFolder+"//QA//Automation Test//Reports//actualResultsFiles//");
        else
            VUtils.copyFile(file,fileName,boxFolder+"//QA//Automation Test//Reports//actualResultsFiles//");

        DataSheetsView dataSheetsView = new DataSheetsView();
        List<VHashMap> actualData = dataSheetsView.getExcelFileDataInHashMap(file,"0");
        System.out.println("this is the actual file "+ actualData);

        if(System.getProperty("os.name").contains("Mac")) {
           expData = dataSheetsView.getExcelFileDataInHashMap(new File(boxFolder + "/QA/Automation Test/Reports/expectedResultsFiles/" + fileName), "0");
        }
        else {
            expData = dataSheetsView.getExcelFileDataInHashMap(new File(boxFolder + "//QA//Automation Test//Reports//expectedResultsFiles//" + fileName), "0");
        }
//        List<VHashMap> expData = dataSheetsView.getExcelFileDataInHashMap(new File("/Users/sriramangajala/Box Sync/QA/Automation Test/Reports/expectedResultsFiles/"+fileName),"0");

//        System.out.println(expData);
//        expData = VUtils.sortDataByFirstColumn(expData);
     //   actualData = VUtils.sortDataByFirstColumn(actualData);


        ValidationUtils.compareTwoSimpleThings((List<HashMap>)(Object)expData,(List<HashMap>)(Object)actualData);
        file.delete();
    }

    @After
    public void stop(Scenario scenario)
    {
        //reportsInterface.closeExportDialogBox();

        if(scenario.isFailed())
        {
            DateFormat dateFormat= new SimpleDateFormat("MM-dd-HH-mm-ss");
            VUtils.captureScreen(scenario);//.getName()+(dateFormat.format((new Date()))));

        }
    }



}
