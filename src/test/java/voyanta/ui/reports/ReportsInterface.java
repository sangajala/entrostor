package voyanta.ui.reports;

import voyanta.ui.pageobjects.DashboardPage;
import voyanta.ui.pageobjects.ReportLinksPage;
import voyanta.ui.pageobjects.ReportsPage;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;
import voyanta.ui.utils.VoyantaDriver;

import java.util.Map;

/**
 * Created by sriramangajala on 20/08/2014.
 */
public class ReportsInterface{
    ReportsPage reportsPage;
    DashboardPage dashboardPage;
    ReportLinksPage reportLinksPage;
    public ReportsInterface()
    {
        dashboardPage = new DashboardPage();
        reportsPage = new ReportsPage();
    }

    public void gotoReportsPage() {

        reportsPage = dashboardPage.gotoReportsPage();

    }

    public void checkUserCanSeeReports() {


        VerifyUtils.True(reportsPage.isReportDisplayed());
    }
    public boolean isUserCanSeeReports() {


        return reportsPage.isReportDisplayed();
    }

    public void selectFilters(String year, String sector,String region) {
        if(!year.trim().equals("")) {
            VoyantaDriver.selectElementByText(year);
            VUtils.waitFor(5);
        }
        if(!sector.trim().equals("")) {
            VoyantaDriver.selectElementByText(sector);
            VUtils.waitFor(5);
        }
        if(!region.trim().equals("")) {
            VoyantaDriver.selectElementByText(region);
            VUtils.waitFor(5);
        }
    }

    public void exportToExcel(String reportName) {
        reportsPage.exportToExcel(reportName);
    }

    public void checkExcelReportGenerated() {
        VerifyUtils.True(reportsPage.isExcelReportGenerated());
    }

    public void applyFilters(Map<String, String> data) {

     reportsPage.applyFilters(data);
    }

    public void gotoOperatingPage() {
        reportsPage.gotoOperatingPage();
    }

    public void clearFilters() {
        reportsPage.clearFilters();
    }

    public void gotoFinancePage() {
        reportsPage.gotoFinancePage();
    }

    public void closeExportDialogBox() {
            reportsPage.closeExportDialogBox();
    }

    public void gotoReportsLinkInTop() {
        reportLinksPage = dashboardPage.gotoReportsLinkInTop();
    }

    public void openView(String view) {

        reportLinksPage.gotoViewReportPage(view);
    }

    public void gotoFinancePageInReports() {
        reportLinksPage.gotoTab("Financial Performance");
    }

    public void gotoTenantPage() {
        reportsPage.gotoTenantPage();
    }


    public void gotoTenantPageInReports() {
        reportLinksPage.gotoTab("Tenant");
      //  VoyantaDriver.getCurrentDriver().switchTo().frame("report-page");
    }

    public void selectAllTenancyFilters() {
        reportsPage.selectAllTenancyFilters();
    }
}
