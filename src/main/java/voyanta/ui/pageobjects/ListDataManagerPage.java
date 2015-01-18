package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;


import org.openqa.selenium.WebElement;



//import voyanta.ui.data.pageobject.voyanta.pageobject.VUtils;
import voyanta.ui.pagecontainers.ListDataManagerContainer;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

public class ListDataManagerPage extends abstractPageWithList{
	static Logger LOGGER = Logger.getLogger(ListDataManagerPage.class);
	
	ListDataManagerContainer DMpageContainer ;
	public SubmissionObject submission;
	private static String currentPage;
	
	public String getCurrentPage(){
		return currentPage;
	} 
	
    public ListDataManagerPage(){
    
    	DMpageContainer = ListDataManagerPage.getDataContainer(ListDataManagerContainer.class);
   	     super.tableElement = DMpageContainer.tableElement;
    	 submission=new SubmissionObject();
    	 currentPage="MySubmission";
    }
	
	public void DataManagerPageNavigation(String NewLink)
	{
        if (NewLink.equals("My Submissions")) {
            go_to_MySubmissions();

        } else if (NewLink.equals("Pending Approval")) {
            go_to_PendingApproval();

        } else if (NewLink.equals("History")) {
            go_to_History();
        }
	}

	//-----------------------------------------------------
	// PRIVATE METHODS
	//-----------------------------------------------------
	private void go_to_MySubmissions()
	{
		DMpageContainer.MySubmissionsTab.click();
	}
	
	private void go_to_PendingApproval()
	{
		DMpageContainer.PendingApproval.click();
	}
	
	/**
	 *  to to history page, rebuild the page and the tableElement
	 */
	private void go_to_History()
	{
       DMpageContainer.SubmissionHistory.click();
        VUtils.waitFor(2);
      	this.DMpageContainer = ListDataManagerPage.getDataContainer(ListDataManagerContainer.class);
        super.tableElement = DMpageContainer.tableElement;
		currentPage="History";
	}
	

   /**
 * wait until validation is completed
 */
	
	private boolean checkName(){
		//this.getRowsInHash();
       // VUtils.waitFor(5);
		//WebElement nameCell= getCellElementWithRow(1,"Name");

	 if (getCellElementWithRow(1,"Name").getText().contains(submission.getName()))
	    {
		return true;
		}
	 return false;
	} 
	
	public void setValidationStatus(){
		WebElement validationCell=(WebElement) this.getCellElementWithRow(1,"Validation");
		String status=validationCell.getText();
	 if (status.contains("failed"))
	    {
		throw new RuntimeException("Test failed with 'validation failed' error");
		}
	 else if (status.equalsIgnoreCase("")) 
		 {
		 submission.setValidationStatus("successfull");
		 }
	 else submission.setValidationStatus(validationCell.getText().replace("/n", ","));
	}
	
	public void setApprovalStatus(){
		WebElement approvalCell=(WebElement) this.getCellElementWithRow(1,"Approval");
	 if (approvalCell.getText().contains("failed"))
	    {
		throw new RuntimeException("Test failed with 'Approval failed' error");
		}
	 else submission.setApprovalStatus(approvalCell.getText());

	}
	
/**
 *  wait until the current submission is validated or approved, go to the page which has the current submission
 */


public void waitValidatedSubmission(){
	getRowsInHashBasedOnIndex(2);
	LOGGER.info("checking where is the current submission");
	if(checkName()){
		setApprovalStatus();
		setValidationStatus();
		while(submission.getValidationStatus().contains("Validating")||submission.getApprovalStatus().contains("Approving")){
			LOGGER.info("current submission is still on My submission page waiting for validation or approval");
			VUtils.waitFor(2);
	    	this.DMpageContainer = ListDataManagerPage.getDataContainer(ListDataManagerContainer.class);
	      	 super.tableElement = this.DMpageContainer.tableElement;
			waitValidatedSubmission();	
		}
		LOGGER.info("validated submission is on My submission page");
	}
	else
		{
		go_to_History();
		LOGGER.info("current submission goes to history page");
		getRowsInHashBasedOnIndex(1);
		checkName();
		setApprovalStatus();
		setValidationStatus();
       // currentPage="History";
		}
	}

public ListofReviewPage go_to_ReviewPage(){
	DMpageContainer.ReviewButton.click();
	return new ListofReviewPage();
}
public void delete_submission(){
}
public boolean deleteMsgExist(){
    return VUtils.isElementWithTextPresentBy(DMpageContainer.deleteAllMsg);
  //  VUtils.isElementPresent(DMpageContainer.getdeleteAllMsgBy());
}

}
