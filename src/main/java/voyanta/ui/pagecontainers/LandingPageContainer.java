package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 24/07/2014.
 */
public class LandingPageContainer extends abstractVoyataPageContainer{
    public static final int MAX_TIME_OUT = 60;


    @FindBy(how= How.LINK_TEXT, using="Login")
    public WebElement Login;

    @FindBy(how = How.CSS, using = "div.content-wrapper h1.left")
    public WebElement sidebar_label;

    @FindBy(how=How.TAG_NAME,using = "body")
    public WebElement body;

    //error message locator
    public static String ErrorMSGID="form-messenger";

    @Override
    public WebElement getDefaultElement() {
        return null;
    }

    @Override
    public WebElement getHeaderElement() {
        return null;
    }
}
