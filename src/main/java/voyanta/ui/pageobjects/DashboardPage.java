package voyanta.ui.pageobjects;

import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 28/07/2014.
 */
public class DashboardPage extends abstractWebPage {
    public boolean isUserInDashBoardPage() {

        return VoyantaDriver.getCurrentDriver().getTitle().contains("Entrostor");
    }
}
