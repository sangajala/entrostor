package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sriramangajala on 24/07/2014.
 */
public class abstractVoyataPageContainer extends abstractPageContainer {
	
     /*Top Header Menu*/
	@FindBy(how = How.LINK_TEXT, using = "Permissions")
	 public WebElement linkPermission;
	@FindBy(how = How.LINK_TEXT, using = "Data Manager")
	 public WebElement linkDataManager;
	@FindBy(how = How.LINK_TEXT, using = "Home")
	 public WebElement linkHome;
	@FindBy(how = How.LINK_TEXT, using = "Reports")
	 public WebElement linkReports;
    @FindBy(how = How.ID,using = "menu-button")
    public WebElement TaskMenu;

    @FindBy(how = How.LINK_TEXT, using = "Login")
    public WebElement linkLogin;
    
    /*Links inside Tasks*/
	@FindBy(how = How.LINK_TEXT, using = "Business Rules")
	 public WebElement linkBusinessRules;
	
	@FindBy(how = How.LINK_TEXT, using = "Upload Data")
	 public WebElement linkUploadData;
	@FindBy(how = How.LINK_TEXT, using = "Download DST")
	 public WebElement linkDownloadDST;



	@FindBy(how = How.LINK_TEXT, using = "Manage Organization")
	 public WebElement linkManageOrg;
	@FindBy(how = How.LINK_TEXT, using = "Manager Tags")
	 public WebElement linkManageTags;
	
	
	/*Search box*/
	@FindBy(how = How.CSS, using = ".default.ui-autocomplete-input")
	 public WebElement inputSearch;
	@FindBy(how = How.ID, using = "#search-submit")
	 public WebElement submitButtonSearch;
	
	
	/*Account Management*/
	@FindBy(how = How.CSS, using = ".account-link")
	 public WebElement linkAccount;
	@FindBy(how = How.CSS, using = "#organization-select>span")
	 public WebElement testOrg;
	
	/*footer*/
	@FindBy(how = How.LINK_TEXT, using = "terms and conditions")
	 public WebElement linkTermAndCondition;

    @FindBy(how = How.LINK_TEXT, using = "Operating Performance")
    public WebElement linkOperatingPage;

    @FindBy(how = How.LINK_TEXT, using = "Financial Performance")
    public WebElement linkFinance;
    @FindBy(how = How.LINK_TEXT, using = "Tenants")
    public WebElement linkTenant;
    @FindBy(how = How.LINK_TEXT, using = "Logout")
    public WebElement linkLogout;

    @Override
    public WebElement getDefaultElement() {
        return null;
    }

    @Override
    public WebElement getHeaderElement() {
        return null;
    }


}
