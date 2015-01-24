package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 29/07/2014.
 */
public class ProposalPageContainers extends abstractVoyataPageContainer {
    @FindBy(how = How.ID, using = "app-headings")
    public WebElement headerElement;

    @FindBy(how = How.CSS, using = "div.sidebar_btn")
    public WebElement proposal;

    @FindBy(how = How.ID, using = "add_new_proposal")
    public WebElement add_new_proposal;

    @FindBy(how = How.CLASS_NAME, using = "save_proposal")
    public WebElement save_proposal;

    @FindBy(how = How.CSS, using = "div.mp_item_inner > div.icon_block > div.close")
    public WebElement first_close;



    @Override
    public WebElement getDefaultElement() {
        return headerElement;
    }

    @Override
    public WebElement getHeaderElement() {
        return headerElement;
    }
}
