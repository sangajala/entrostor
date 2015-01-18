package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class PermissionsPageContainer extends abstractVoyataPageContainer{

//    @FindBy(how = How.ID, using = "content")
    public WebElement content;

    @FindBy(how = How.ID, using = "sharing-table")
    public WebElement tableElement;
}
