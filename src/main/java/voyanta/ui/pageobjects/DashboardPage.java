package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import voyanta.ui.pagecontainers.DashboardPageContainers;
import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 28/07/2014.
 */
public class DashboardPage extends abstractWebPage {

    static Logger LOGGER = Logger.getLogger(DashboardPage.class);

    DashboardPageContainers pageContainer = DashboardPage.getDataContainer(DashboardPageContainers.class);
    public boolean isUserInDashBoardPage() {

        return VoyantaDriver.getCurrentDriver().getTitle().contains("Entrostor");
    }

    public ProposalPage gotoProposalPage() {

        VoyantaDriver.getCurrentDriver().get("http://entrostor.com/proposals");
//        pageContainer.proposal.click();
//        WaitUtils.waitForElement(pageContainer.proposal_link);
        return new ProposalPage();

    }
}
