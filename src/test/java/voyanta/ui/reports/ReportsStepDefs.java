package voyanta.ui.reports;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
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
import java.util.Map;

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

    @Given("^user is in reports page$")
    public void user_is_in_reports_page() throws Throwable {
        reportsInterface.gotoReportsPage();

    }

    @Given("^user navigates to (.*) page$")
    public void user_navigates_to_operating_page(String page)
    {
        int i=0;
        if(page.equalsIgnoreCase("Operating performance"))
            reportsInterface.gotoOperatingPage();
        else if(page.equalsIgnoreCase("Financial performance"))
            reportsInterface.gotoFinancePage();
        else if(page.equalsIgnoreCase("Report Operating Statement")) {
            reportsInterface.gotoReportsLinkInTop();
            reportsInterface.gotoFinancePageInReports();
            reportsInterface.openView("Operating Statement");
        }
        else if(page.equalsIgnoreCase("Report Balance Sheet")) {
            reportsInterface.gotoReportsLinkInTop();
            reportsInterface.gotoFinancePageInReports();
            reportsInterface.openView("Balance Sheet");
        }
        else if(page.equalsIgnoreCase("Report Operating Performance"))
        {
            reportsInterface.gotoReportsLinkInTop();
        }
        else if(page.equalsIgnoreCase("Dashboard Tenant"))
        {
            reportsInterface.gotoTenantPage();
        }
        else if(page.equalsIgnoreCase("Report Tenancy"))
        {
            reportsInterface.gotoReportsLinkInTop();
            reportsInterface.gotoTenantPageInReports();
        }

//        if(i<=3) {
//            if (!reportsInterface.isUserCanSeeReports()) {
//                user_navigates_to_operating_page(page);
//                i++;
//            }
//        }
//            ;


    }

//    @Given("^user navigates to Financial performance page$")
//    public void user_navigates_to_finance_page()
//    {
//        reportsInterface.gotoFinancePage();
//    }

    @Given("^user can see reports$")
    public void user_can_see_reports() throws Throwable {
        reportsInterface.checkUserCanSeeReports();
    }

    @Given("^user goes to '(.*)'$")
    public void user_opens_view(String view)
    {
        reportsInterface.openView(view);
    }

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

    @Given("^clears the existing filters")
    public void user_clears_filters() throws Throwable {
        reportsInterface.clearFilters();
    }

    @When("^selects the filters with year as '(.*)',Sector as '(.*)' and Region as '(.*)'$")
    public void selects_year_and_Sector_Office(String year,String sector,String region) throws Throwable {
        reportsInterface.selectFilters(year,sector,region);
        VUtils.waitFor(5);
    }


    @When("^selects the filters as below$")
    public void select_filters(DataTable dataTable)
    {

        Map<String,String> data = VUtils.getSortedHashMap(dataTable);
        reportsInterface.applyFilters(data);
    }
    @When("^export the '(.*)' report$")
    public void export_the_Asset_Diversification_report(String reportName) throws Throwable {
        reportsInterface.exportToExcel(reportName);
        VUtils.waitFor(5);

    }

    @Then("^the report is exported successfully$")
    public void report_is_saved()
    {
        reportsInterface.checkExcelReportGenerated();
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

    @And("^select all the values in report$")
    public void select_all_the_values_in_report() throws Throwable {
       reportsInterface.selectAllTenancyFilters();
    }


}
