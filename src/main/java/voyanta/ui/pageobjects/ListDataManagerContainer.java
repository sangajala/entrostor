// ############################## DATA MANAGER PAGE ##############################
// This file should contain all objects and object manipulations pertaining to the
// data management page and its associated tabs
// ###############################################################################
//
// v 0.0.1		Kevin McCarthy		Created file
// v 0.0.2		Kevin McCarthy		Added main page objects
// v 0.0.3		Sowmya Vudathu		Added navigation functions

package voyanta.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import voyanta.ui.pagecontainers.*;

public class ListDataManagerContainer extends voyanta.ui.pagecontainers.abstractVoyataPageContainer {
	
	@FindBy(how = How.CSS, using = ".voyantaButton.medium.callToAction.right")
	public WebElement UploadDataButton;
	
	
	//-----------------------------------------------------
	// NAVIGATION TABS
	//-----------------------------------------------------
	@FindBy(how = How.LINK_TEXT, using = "My Submissions")
	public WebElement MySubmissionsTab;

	@FindBy(how = How.LINK_TEXT, using = "Pending Approval")
	public WebElement PendingApproval;

	@FindBy(how = How.LINK_TEXT, using = "History")
	public WebElement SubmissionHistory;
	

	//-----------------------------------------------------
	// RADIO BUTTONS FOR DATA TYPES
	//-----------------------------------------------------
	@FindBy(how = How.LINK_TEXT, using = "Only Data")
	public WebElement SubmissionRadioData;
	
	@FindBy(how = How.CSS, using = "Only Documents")
	public WebElement SubmissionRadioDocument;
	
	@FindBy(how = How.LINK_TEXT, using = "All Submissions")
	public WebElement SubmissionAll;
	

	//-----------------------------------------------------
	// TABLE HEADERS (FOR SORTS)
	//-----------------------------------------------------
	@FindBy(how = How.LINK_TEXT, using = "Name")
	public WebElement SubmissionTableName;
	
	@FindBy(how = How.LINK_TEXT, using = "Type")
	public WebElement SubmissionTableType;
	
	@FindBy(how = How.LINK_TEXT, using = "Notes")
	public WebElement SubmissionTableNotes;
	
	@FindBy(how = How.LINK_TEXT, using = "Submitted")
	public WebElement SubmissionTableSubmitted;
	
	@FindBy(how = How.LINK_TEXT, using = "Validation")
	public WebElement SubmissionTableValidation;
	
	@FindBy(how = How.LINK_TEXT, using = "Approval")
	public WebElement SubmissionTableApproval;
	
	@FindBy(how = How.LINK_TEXT, using = "Actions")
	public WebElement SubmissionTableActions;

	//-----------------------------------------------------
	// TABLE ELEMENTS - fetch as lists
	//-----------------------------------------------------
	
	@FindBy(how= How.CSS, using=".expander.actionToggleExpandedView")
	public WebElement ShowFilesLink;
	
	@FindBy(how= How.CSS, using=".eye")
	public WebElement ReviewButton;
	
	@FindBy(how= How.CSS, using=".trash")
	public WebElement CancelButton;

	WebElement errorLabel;
	final String errorLableLoc="flag red";
	
	WebElement warningLabel;
	final String warningLableLoc="flag orange";
	
  //Table container
	@FindBy(how = How.CSS, using = ".list-grid")
	public WebElement tableElement;

	//-----------------------------------------------------
	// PUBLIC METHODS
	//-----------------------------------------------------
	
}
