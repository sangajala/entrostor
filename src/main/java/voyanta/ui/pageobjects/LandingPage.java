package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import voyanta.ui.utils.VUtils;

/**
 * Created by sriramangajala on 28/07/2014.
 */
public class LandingPage extends abstractWebPage {
    static Logger LOGGER = Logger.getLogger(LandingPage.class);

    voyanta.ui.pagecontainers.LandingPageContainer pageContainer = getDataContainer(voyanta.ui.pagecontainers.LandingPageContainer.class);
    public LoginPage gotoLoginPage() {
        pageContainer.Login.click();
        VUtils.waitFor(2);
        return new LoginPage();
    }
}
