package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class BrowsePageContainer extends abstractVoyataPageContainer{

    @FindBy(how = How.CLASS_NAME, using = "content-wrapper")
    public WebElement content;

    @FindBy(how = How.ID, using = "sharing-table")
    public WebElement tableElement;

    @FindBy(how = How.LINK_TEXT, using = "Download")
    public WebElement linkDownload;

    @FindBy(how = How.LINK_TEXT, using = "Attach Files")
    public WebElement btnAttachFiles;

    @FindBy(how=How.LINK_TEXT, using ="Edit")
    public WebElement linkEdit;
}
