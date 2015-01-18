package voyanta.ui.investor;

import voyanta.ui.pageobjects.DashboardPage;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 20/08/2014.
 */
public class ReportsInterface{

    DashboardPage dashboardPage;

    public ReportsInterface()
    {
        dashboardPage = new DashboardPage();

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


}
