package voyanta.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import voyanta.ui.pagecontainers.*;
import voyanta.ui.utils.VoyantaDriver;

public class ListReviewPageContainer extends voyanta.ui.pagecontainers.abstractVoyataPageContainer {
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
	
	public final String BRMsgLocator="html/body/div[4]/div/div[2]/ul/li";
	
	public final String CloseBoxClassLocator="closeDialogButton actionCloseModalDialog";
	
	//public WebElement defaultElement;
	
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
}
