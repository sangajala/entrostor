package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import voyanta.ui.pagecontainers.ReportsPageContainer;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.VoyantaElementImpl;
import voyanta.ui.utils.unused.VoyantaElement;

import java.util.Date;
import java.util.Map;

/**
 * Created by sriramangajala on 20/08/2014.
 */
public class ReportsPage extends abstractWebPage {
    static Logger LOGGER = org.apache.log4j.Logger.getLogger(ReportsPage.class);

    ReportsPageContainer pageContainer = ReportsPage.getDataContainer(ReportsPageContainer.class);

    public ReportsPage()
    {
        VoyantaDriver.getCurrentDriver().switchTo().defaultContent();
        VoyantaDriver.getCurrentDriver().switchTo().frame("report-page");
        VUtils.waitForElement(pageContainer.currentSelectionsHeader);
    }

    public boolean isReportDisplayed() {
        try {
            VoyantaDriver.getCurrentDriver().switchTo().defaultContent();
     //       if(VUtils.isElementPresent(By.id("report-page")))
            VoyantaDriver.getCurrentDriver().switchTo().frame("report-page");
            VUtils.waitForElement(pageContainer.currentSelectionsHeader);
            return pageContainer.currentSelectionsHeader.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void exportToExcel(String reportName) {


      //  else if((VUtils.elementExists(By.xpath("//div[text()='Send to Excel']"))))
      //      VoyantaDriver.getDivByText("Send to Excel").click();
            VUtils.waitFor(5);
            if (reportName.equalsIgnoreCase("Operating Statistics")) {
                generateReportFromBody("Document\\CT_OperatingStatistics");
            }
            else if(reportName.contains("Valuation / NOI Trend"))
            {

                VUtils.waitFor(2);
                VoyantaDriver.findElement(By.xpath("//div[@id='Document\\CH_ValuationNOI']//div[@title='Send to Excel']")).click();
//                (new VoyantaElementImpl(VoyantaDriver.findElement(By.id("Document\\CH_ValuationNOI")).findElement(By.className("QvGraph")))).rightclick();
                VUtils.waitFor(3);
            }
            else if(reportName.contains("Budget vs Actual"))
            {

                generateReportFromBody("Document\\CT_ActVsBud");
            }

            else if(reportName.contains("Revenue and Expense Breakdown"))
            {

                generateReportFromBody("Document\\CT_RevExp");
            }
            else if(reportName.contains("Operating Statement"))
            {
                generateReportFromGrid("Document\\CT_OperatingStatement");
            }
            else if(reportName.contains("Balance Sheet"))
            {
                generateReportFromGrid("Document\\CT_BalanceSheet");
            }
            else if(reportName.contains("Lease Expiry Profile Report"))
            {
                generateReportFromGrid("Document\\CT_LeaseExpiryProfile");
            }
            else if(reportName.contains("Rental Arrears Report"))
            {
                generateReportFromGrid("Document\\CT_RentalArrearsReport");
            }
            else if(reportName.equalsIgnoreCase("Operating Statistics Overview")||reportName.equalsIgnoreCase("Operating Statistics As Of - 31/12/2013"))
            {
                generateReportFromGrid("Document\\CT_OperatingStatistics");
            }
            else if(reportName.contains("Building Unit Inventory"))
            {
                generateReportFromGrid("Document\\CT_BuildingUnitInventory");
            }
            else if(reportName.contains("Tenancy Schedule"))
            {
                generateReportFromGrid("Document\\CT_TenancySchedule");
            }
            else if(reportName.contains("Rental Analysis Report"))
            {
                generateReportFromGrid("Document\\CT_RentalAnalysis");
            }
            else
            {
                WebElement element = VoyantaDriver.getDivByText(reportName);
                VoyantaElement voyantaElement = new VoyantaElementImpl(element);
                voyantaElement.rightclick();
                VUtils.waitFor(3);
                if((VUtils.isElementPresent(By.xpath("//span[text()='Send to Excel']")))) {
                    VoyantaDriver.getSpanByText("Send to Excel").click();
                }
                else
                    throw new RuntimeException("Unable to generate report for "+reportName);
            }
        }

//        if(reportName.equals("Asset Diversification")) {
//
//            WebElement element = VoyantaDriver.findElement(By.xpath("//div[@title='Send to Excel']"));
//            element.click();
//        }
//        else if(reportName.equals("Top 10 Assets")) {
//
//            WebElement element = VoyantaDriver.findElements(By.xpath("//div[@title='Send to Excel']")).get(1);
//            element.click();
//        }



    public void generateReportFromBody(String reportLocator)
    {
        (new VoyantaElementImpl(VoyantaDriver.findElement(By.id(reportLocator)).findElement(By.className("QvGraph")))).rightclick();
        //xpath=//div[@id='Document\CT_RevExp']//div[@class='QvGraph']
        VUtils.waitFor(3);
        if(VUtils.isElementWithTextPresentBySpan("Send to Excel"))
            VoyantaDriver.getSpanByText("Send to Excel").click();
        else
            throw new RuntimeException("Unable to generate report for "+reportLocator);
    }
    public void generateReportFromGrid(String reportLocator)
    {
        (new VoyantaElementImpl(VoyantaDriver.findElement(By.id(reportLocator)).findElement(By.className("QvGrid")))).rightclick();
        //xpath=//div[@id='Document\CT_RevExp']//div[@class='QvGraph']
        VUtils.waitFor(3);
        if(VUtils.isElementWithTextPresentBySpan("Send to Excel"))
            VoyantaDriver.getSpanByText("Send to Excel").click();
      //  else
        //    throw new RuntimeException("Unable to generate report for "+reportLocator);
    }
    public void generateReportFromImage(String reportLocator)
    {
        (new VoyantaElementImpl(VoyantaDriver.findElement(By.id(reportLocator)).findElement(By.className("QvGraph")))).rightclick();
        //xpath=//div[@id='Document\CT_RevExp']//div[@class='QvGraph']
        VUtils.waitFor(3);
        if(VUtils.isElementWithTextPresentBySpan("Send to Excel"))
            VoyantaDriver.getSpanByText("Send to Excel").click();
        else
            throw new RuntimeException("Unable to generate report for "+reportLocator);
    }


    public boolean isExcelReportGenerated() {
        VUtils.waitForElement(pageContainer.downloadConfirmBox);
        return pageContainer.downloadConfirmBox.isDisplayed();
    }

    public void applyFilters(Map<String, String> data) {
        String value;
        for(String key:data.keySet())
        {
            if(!data.get(key).equals("")) {
                if (key.equals("Year")) {
                    value = data.get(key);
                    if(value.equals("All"))
                    {
                        VoyantaDriver.getDivByText("Year").click();
                        LOGGER.info("Clicking on the Section: " + key);
                        VUtils.waitFor(2);
                        value = "2014";//Document\F_DATE_SELECTString.valueOf((new Date()).getTime());
                        LOGGER.info("Clicking on the Section: " + key + " and value: " + value);

                        VoyantaDriver.getDivByTextElements(value).get(1).click();
                        VUtils.waitFor(2);
                    }
                    else if(!value.equals(String.valueOf((new Date()).getYear())))
                    {
                        VoyantaDriver.getDivByText("Year").click();
                        LOGGER.info("Clicking on the Section: " + key);
                        VUtils.waitFor(2);

                        LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
                        VoyantaDriver.getDivByText(value).click();
                        VUtils.waitFor(2);
                        if(VUtils.isTextSelected(value)==false){
                        	 VoyantaDriver.getDivByText(value).click();
                             VUtils.waitFor(2);
                        };
                    }
                }
                else if (key.equals("Quarter")) {
                    VUtils.waitFor(5);
                    VoyantaDriver.getDivByText("Quarter").click();
                    LOGGER.info("Clicking on the Section: "+key);
                    VUtils.waitFor(2);
                    value = data.get(key);
                    VUtils.waitForAttribute(VoyantaDriver.getDivByText("Quarter"),"class","QvTab QvFloatLeft QvTabSelected");
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
                    VoyantaDriver.getDivByText(value).click();
                    VUtils.waitFor(3);

                } else if (key.equals("Month")) {
                    VUtils.waitFor(5);
                    VoyantaDriver.getDivByText("Month").click();
                    VUtils.waitFor(2);
                    value = data.get(key);
                    VUtils.waitForAttribute(VoyantaDriver.getDivByText("Month"),"class","QvTab QvFloatLeft QvTabSelected");
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
                    VoyantaDriver.getDivByText(value).click();
                    VUtils.waitFor(3);

                }
                else if(key.equals("Toggle")||key.equals("report name"))
                {
                    clickAsPerReportAndToggleValue(data.get("report name"), data.get(key));
                    VUtils.waitFor(3);
                }
                else if(key.equals("Tab"))
                {
                    value = data.get(key);
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
                    VUtils.waitFor(3);
                    selectTab(data.get("report name"),value);
                    VUtils.waitFor(3);

                }
                else if(key.equalsIgnoreCase("Checkbox"))
                {
                    value = data.get(key);
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);

                    if(value.equalsIgnoreCase("Activity"))
                    {
                        LOGGER.info("Ignoring as is default value on the Section: " + key + " and value: " + value);
                    }
                    else if(value.equalsIgnoreCase("Expiring"))
                    {
                        LOGGER.info("Ignoring as is default value on the Section: " + key + " and value: " + value);
                    }
                    else
                        VoyantaDriver.getDivByText(value).click();


                }
                else if(key.equalsIgnoreCase("Area Measure"))
                {
                    value = data.get(key);
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
                    VUtils.waitFor(3);
                    if(VoyantaDriver.getDivByTitleAndText(value).isDisplayed())
                        VoyantaDriver.getDivByTitleAndText(value).click();
                    else
                        VoyantaDriver.getDivElementsByTitleAndText(value).get(1).click();
                    VUtils.waitFor(3);
                }
                else if(key.equals("Investment"))
                {

                   LOGGER.info("AJAX:"+(Long)((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("return jQuery.active"));
                    value = data.get(key);
                    VUtils.waitFor(3);
                    VoyantaElement element = new VoyantaElementImpl(VoyantaDriver.findElement(By.cssSelector("div[title='"+value+"']")));
                    element.rightclick();


                    VUtils.waitFor(3);
                    VoyantaDriver.getSpanByText("Expand all").click();
                    VUtils.waitFor(5);

                 //   VoyantaDriver.findElement(By.cssSelector("div[title='"+value+"']  div.cell-icon.cell-EXC-icon")).click();
                   // VUtils.waitFor(3);
                    VoyantaDriver.getDivByText(value).click();
                    VUtils.waitFor(3);
               //     VoyantaDriver.findElement(By.cssSelector("div[title='"+value+"']")).click();
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
;                }


                else {
                    value = data.get(key);
                    LOGGER.info("Clicking on the Section: " + key + " and value: " + value);
                    VUtils.waitFor(2);
                    VoyantaDriver.getDivByText(value).click();
                    VUtils.waitFor(3);
                }
            }
            else
                LOGGER.info("Ignore the filter as the value is not given in scenario:"+key);
        }
    }

    private void clickAsPerReportAndToggleValue(String reportName, String toggle) {
        if(reportName.equalsIgnoreCase("Asset Diversification"))
        {

            if (toggle.equals("Asset Value")) {
                LOGGER.info("Not clicking on any toggle as this is default toggle");
            }
            else if(toggle.equals("Gross Rental Income"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_AssetDiv']//div[@class='Qv_Hotspot']",1);
                VUtils.waitFor(3);

            }
            else if(toggle.equals("Net Rental Income"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_AssetDiv']//div[@class='Qv_Hotspot']",2);
            }
            else if(toggle.equals("Leasable Area"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_AssetDiv']//div[@class='Qv_Hotspot']",3);
            }
            else if(toggle.equals("Number of Assets"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_AssetDiv']//div[@class='Qv_Hotspot']",4);
            }
            else{

                LOGGER.info("Invalid toogle "+toggle);
         //   throw new RuntimeException("Invalid toggle "+toggle);


        }



        }
        else if(reportName.contains("Top 10 Assets"))
        {
            if(toggle.equalsIgnoreCase("Net Rental Income")) {
                LOGGER.info("Not clicking on any toggle as this is default toggle");
            }
            else if(toggle.equalsIgnoreCase("Gross Rental Income"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopAssets']//div[@class='Qv_Hotspot']", 3);
            }
            else if(toggle.equalsIgnoreCase("Rental Arrears"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopAssets']//div[@class='Qv_Hotspot']", 2);
            }
            else if(toggle.equalsIgnoreCase("Leasable Area"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopAssets']//div[@class='Qv_Hotspot']", 4);
            }
            else if(toggle.equalsIgnoreCase("Asset Value"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopAssets']//div[@class='Qv_Hotspot']", 1);
            }
            else
            {
                LOGGER.info("Invalid toogle "+toggle);
//            throw new RuntimeException("Invalid toggle "+toggle);


        }
        }
        else if(reportName.contains("Valuation / NOI Trend"))
        {
            VUtils.waitFor(3);
            if(toggle.contains("NOI Trend"))
            {
                LOGGER.info("Not clicking on any toggle as this is default toggle");
            }
            else if(toggle.contains("Asset Value")) {

                clickOnToggleFor("//div[@id='Document\\CH_ValuationNOI']//div[@class='Qv_Hotspot']",1);

            }
            else{

                LOGGER.info("Invalid toogle "+toggle);
           // throw new RuntimeException("Invalid toggle "+toggle);


        }
             //   pageContainer.toggleButton.clickFor(1);
        }
        else if(reportName.contains("Lease Expiry Schedule"))
        {

            VUtils.waitFor(3);
            if(toggle.equalsIgnoreCase("Expiring Area(SF)"))
            {
                LOGGER.info("Not clicking on any toggle as this is default toggle");

            }
            else if(toggle.contains("Expiring & Terminating % of Total")) {


                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_LeaseExpiry']//div[@class='Qv_Hotspot']",1);

            }
            else if(toggle.contains("Expiring % of Total")) {

                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_LeaseExpiry']//div[@class='Qv_Hotspot']",2);

            }
            else if(toggle.contains("Expiring & Termination Area (SF)")) {

                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_LeaseExpiry']//div[@class='Qv_Hotspot']",3);

            }
            else
            {
                LOGGER.info("Invalid toggle "+toggle);
//                throw new RuntimeException("Invalid toggle "+toggle);
            }
        }
        else if(reportName.equalsIgnoreCase("Occupancy Trend"))
        {
        	 if(toggle.equalsIgnoreCase("Leased Area"))
             {
                 LOGGER.info("Not clicking on any toggle as "+toggle+" is default toggle");
             }
        	 else  if(toggle.equalsIgnoreCase("Unit Area"))
                {
                    LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                    clickOnToggleFor("//div[@id='Document\\CH_OccupTrend']//div[@class='Qv_Hotspot']",1);
                }
        }
        else if(reportName.equalsIgnoreCase("Top 10 Tenants"))
        {
            if(toggle.equalsIgnoreCase("Leased Area"))
            {
                LOGGER.info("Not clicking on any toggle as "+toggle+" is default toggle");
            }
            else if(toggle.equalsIgnoreCase("Retail Sales"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopTenants']//div[@class='Qv_Hotspot']",1);
            }
            else if(toggle.equalsIgnoreCase("Net Rental Income pcm"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopTenants']//div[@class='Qv_Hotspot']",2);
            }
            else if(toggle.equalsIgnoreCase("Gross Rental Income pcm"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopTenants']//div[@class='Qv_Hotspot']",3);
            }
            else if(toggle.equalsIgnoreCase("Rental Arrears"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TopTenants']//div[@class='Qv_Hotspot']",4);
            }
            else
            {
                LOGGER.info("Given report doesn't have toggles :"+reportName);
//            throw new RuntimeException("Given report doesn't have toggles :"+reportName);
            }
        }
        else if(reportName.equalsIgnoreCase("Tenant Mix"))
        {
            if(toggle.equalsIgnoreCase("Leased Area"))
            {
                LOGGER.info("Not clicking on any toggle as "+toggle+" is default toggle");
            }
            else if(toggle.equalsIgnoreCase("No. of Tenants"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TenantMix']//div[@class='Qv_Hotspot']",1);
            }
            else if(toggle.equalsIgnoreCase("Sales Volume"))
            {
                LOGGER.info("Clicking on toggle as to generate "+toggle+" report");
                clickOnToggleFor("//div[@id='Document\\CH_TenantMix']//div[@class='Qv_Hotspot']",2);
            }

            else
            {
                LOGGER.info("Given report doesn't have toggles :"+reportName);
//            throw new RuntimeException("Given report doesn't have toggles :"+reportName);
            }
        }
        else
        {
            LOGGER.info("Given report doesn't have toggles :"+reportName);
//            throw new RuntimeException("Given report doesn't have toggles :"+reportName);
      }



    }

    public void clickOnToggleFor(String xpathToggle,int count)
    {
        for(int i=1;i<=count;i++) {

            VoyantaDriver.findElement(By.xpath(xpathToggle)).click();
            VUtils.waitFor(3);
        }
    }

    public void clearFilters() {

        pageContainer.clearFiltersButton.click();
        VUtils.waitFor(3);
    }

    public void selectTab(String reportName,String value)
    {
       // VoyantaDriver.getCurrentDriver().manage().deleteAllCookies();
        if(reportName.equalsIgnoreCase("Revenue and Expense Breakdown"))
        {
            VoyantaDriver.findElement(By.xpath("//div[@id='Document\\CT_RevExp']//td[text()='" + value + "']")).click();
         //   VoyantaDriver.getTableCellByText(value).click();
            VUtils.waitFor(3);
        }
        else
            VoyantaDriver.getTableCellByText(value).click();

    }


    public void closeExportDialogBox() {
        if(VUtils.isElementPresentWithLocator(pageContainer.getDialogBox().getLocator(),pageContainer.getDialogBox().getValue()))
            VUtils.getElement(pageContainer.getDialogBox().getLocator(),pageContainer.getDialogBox().getValue()).click();
    }

    public void selectAllTenancyFilters() {
        VUtils.waitFor(3);
        (new VoyantaElementImpl(VoyantaDriver.findElement(By.xpath("//div[@id='Document\\CT_TenancySchedule']//div[@class='QvListbox']")))).rightclick();

         VUtils.waitFor(3);
        if((VUtils.isElementPresent(By.xpath("//span[text()='Select All']")))) {
            VoyantaDriver.getSpanByText("Select All").click();
        }
        VUtils.waitFor(3);
       // VoyantaDriver.getCurrentDriver().findElement(By.xpath("//div[@id='Document\\CT_TenancySchedule']//div[@class='QvListbox']"))
    }
}
