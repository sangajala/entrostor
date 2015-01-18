package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 24/07/2014.
 */
public class LoginPageContainer extends abstractVoyataPageContainer{
    public static final int MAX_TIME_OUT = 60;
    //	private WebDriver driver;
    //private static String URL="https://test.voyanta.com/";
    private static String URL="http://test.voyanta.com";


    //	The Email or Password you entered is incorrect. Please try again.
    
    
    @FindBy(how = How.NAME, using = "email")
    public WebElement inputEmail;

    @FindBy(how = How.NAME, using = "password")
    public WebElement inputPassword;

    @FindBy(how = How.CSS, using = "button.t-btn")
    public  WebElement buttonSignIn;

    @FindBy(how= How.PARTIAL_LINK_TEXT, using="Forgot your Password?")
    public WebElement linkFP;

    @FindBy(how= How.LINK_TEXT, using="Home")
    public WebElement Home;

    @FindBy(how= How.LINK_TEXT, using="Building")
    public WebElement Building;

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
