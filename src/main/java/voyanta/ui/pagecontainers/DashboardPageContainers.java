package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 29/07/2014.
 */
public class DashboardPageContainers extends abstractVoyataPageContainer {
    @FindBy(how = How.ID, using = "app-headings")
    public WebElement headerElement;

    @FindBy(how = How.CSS, using = "div.sidebar_btn")
    public WebElement proposal;

    @FindBy(how = How.CSS, using = "div.messages.proposals")
    public WebElement proposal_link;

    @Override
    public WebElement getDefaultElement() {
        return headerElement;
    }

    @Override
    public WebElement getHeaderElement() {
        return headerElement;
    }
}
