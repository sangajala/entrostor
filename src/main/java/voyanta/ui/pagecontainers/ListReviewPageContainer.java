package voyanta.ui.pagecontainers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import voyanta.ui.utils.VoyantaDriver;

import java.util.List;

public class ListReviewPageContainer extends abstractVoyataPageContainer{

    public static String deleteAllWEMSG="Are you sure you want to remove all error and warning rows from this DST type?";
    public static String deleteAllError="Are you sure you want to remove all error rows from this DST type? ";

	@FindBy(how = How.LINK_TEXT, using = "Revalidate")
	public WebElement ButtonRevalidate;
	
	@FindBy(how = How.ID, using = "showChangedRows")
	public WebElement boxChangedRows;
	
	@FindBy(how = How.LINK_TEXT, using = "« Back to My Submissions")
	public WebElement linkBackToSubmission;
	
	@FindBy(how = How.ID, using = "showErrorRows")
	public WebElement boxErrorRows;
	
	@FindBy(how = How.LINK_TEXT, using = "Remove errors and warning rows")
	public WebElement buttonRemoveErrorWarning;
	
	@FindBy(how = How.LINK_TEXT, using = "Remove error rows")
	public WebElement buttonRemoveError;

    @FindBy(how = How.CLASS_NAME, using = "content-box")
    public WebElement confirmationBox;

    @FindBy(how = How.LINK_TEXT, using = "Yes")
    public WebElement confirmationYes;

    @FindBy(how = How.LINK_TEXT, using = "No")
    public WebElement confirmationNO;

    @FindBy(how = How.CSS, using = ".voyantaButton.small.actionCleanReview")
    public WebElement confirmationYesDeleteAll;
	
	public final String BRMsgID="modal-dialog";
	
	public final String CloseBoxClassLocator="closeDialogButton actionCloseModalDialog";
	

	private final String deleteSingleErrorLoc=".voyantaButton.actionRemoveRow";
	private final String deleteAllErrors="Remove error rows";
	private final String deleteAllErrorAndWarning="Remove errors and warning rows";

	
	  //Table container
		@FindBy(how = How.CSS, using = ".list-grid")
		public WebElement tableElement;

    public WebElement getDSTObjectButton(String DSTObjectName){
		WebElement DSTObjectButton=VoyantaDriver.getCurrentDriver().findElement(By.linkText(DSTObjectName));
		return DSTObjectButton;
	}
	
	public WebElement getDefaultElement(){
		WebElement DSTObjectButton=VoyantaDriver.getCurrentDriver().findElement(By.cssSelector(".list-grid"));
		return tableElement;
	}

	
	public List<WebElement> getDeleteSingleRowButtons(){
		List<WebElement> errors=VoyantaDriver.getCurrentDriver().findElements(By.cssSelector(deleteSingleErrorLoc));
		System.out.println("there are "+ errors.size());
		return errors;
	}
	
	public By getDeleteSinglerLocator(){
		return By.cssSelector(deleteSingleErrorLoc);
	}

	public By getDeleteAllErrorLocator() {
		// TODO Auto-generated method stub
		return By.linkText(deleteAllErrors);
	}
	
	public By getDeleteAllErrorAndWarningLocator() {
		// TODO Auto-generated method stub
		return By.linkText(deleteAllErrorAndWarning);
	}

}
