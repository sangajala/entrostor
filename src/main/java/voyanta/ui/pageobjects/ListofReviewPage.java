package voyanta.ui.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import voyanta.ui.pagecontainers.ListDataManagerContainer;
import voyanta.ui.pagecontainers.ListReviewPageContainer;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.WaitUtils;

/**
 * @author ting.liu
 *
 */
public class ListofReviewPage extends abstractPageWithList {
  
	static Logger LOGGER = Logger.getLogger(ListofReviewPage.class);
	
	ListReviewPageContainer reviewContainer ;

	
    public ListofReviewPage(){
    
    	reviewContainer = ListofReviewPage.getDataContainer(ListReviewPageContainer.class);
    	 super.tableElement = reviewContainer.tableElement;
    }
    
    public ListofReviewPage click_DST_Object(String objectName){
    	WebElement objectElement=reviewContainer.getDSTObjectButton(objectName);
    	if(objectElement==null){
    		throw new RuntimeException("the targed DST Object is not available on the review page");
    	}
    	else objectElement.click();
    //	WebElement defaultElement=
    //	WaitUtils.waitForElement(reviewContainer.getDefaultElement());
    	WaitUtils.waitFor(3);
    	return new ListofReviewPage();
    }
	
    public String getValueForDSTColumn(int row, String columnName){
    	//this.getRowsInHashBasedOnIndex(0);
    	return this.getCellElementWithRow(row, columnName).getText().replace(",", "");
    }
    
    public String getValidationResultForDSTColumn(int row,String columnName){
    	LOGGER.info("recieve value for row "+row+ " under column " +columnName);
    	String validationResult=null;
    	WebElement element=getCellElementWithRow(row, columnName);
    	if(element.findElement(By.tagName("Span")).getAttribute("class").contains("red"))
    	{return "error";}
    	else if(element.findElement(By.tagName("Span")).getAttribute("class").equalsIgnoreCase("orange"))
    	{return "warning";}
    	else 
    	return null;
    }
    
    public WebElement getSentinelElement(int row){
    	WebElement element=getCellElementWithRow(row,"sentinel");
    	WebElement linkElement=element.findElement(By.tagName("a"));
    	return linkElement;
    }
    
    public String getValidationTypeFromSentinel(int row){
    	String sentinelType=getSentinelElement(row).getText();
    	return sentinelType;
    }
    
    public void clickSentinel(int row){
    	getSentinelElement(row).click();
    	reviewContainer = ListofReviewPage.getDataContainer(ListReviewPageContainer.class);
    	
    }
    
    public String getMsg(){
    	String msg="";
    	List<WebElement> elements=VoyantaDriver.getCurrentDriver().findElement(By.id(reviewContainer.BRMsgID)).findElements(By.tagName("li"));
    	for(WebElement element:elements){
    		
    		msg=msg.concat(element.getText());
    	}
    	return msg;
    }
    
    public void closeBRBox(){
    	VoyantaDriver.getCurrentDriver().findElement(By.linkText("X")).click();
    }


    
    public ListofReviewPage deleteSingleRow(int index){
    	reviewContainer.getDeleteSingleRowButtons().get(index).click();
    	return new ListofReviewPage();
    }
    
    public int checkErrorRowNumbers(){
    	return reviewContainer.getDeleteSingleRowButtons().size();
    }
    
    /**
     * @param row
     * @return the validation message after clicking the validation result link under sentinel
     */
    public String getSentinelMSG(int row){
    	this.clickSentinel(row);
    	return getMsg();
    }

	public boolean checkDeleteSingleButton() {
		// TODO Auto-generated method stub
		return VUtils.isElementPresent(reviewContainer.getDeleteSinglerLocator());
	}

	public boolean checkDeleteAllButton() {
		// TODO Auto-generated method stub
		return VUtils.isElementPresent(reviewContainer.getDeleteAllErrorLocator());
	}
	
	public boolean checkDeleteAllEWButton() {
		// TODO Auto-generated method stub
		return VUtils.isElementPresent(reviewContainer.getDeleteAllErrorAndWarningLocator());
	}
    public ListofReviewPage deleteAllErrors() {
        reviewContainer.buttonRemoveError.click();
        return new ListofReviewPage();
    }

    public ListofReviewPage confirmDeleteYES(){
        reviewContainer.confirmationYes.click();
        VUtils.waitFor(3);
        return new ListofReviewPage();
    }

    public ListDataManagerPage confirmDeleteAllYES(){
        reviewContainer.confirmationYes.click();
        VUtils.waitFor(3);
        return new ListDataManagerPage();
    }

    public void confirmDeleteNO(){
        reviewContainer.confirmationNO.click();
    }

    public String readMSG(){
        return reviewContainer.confirmationBox.getText();
    }

    public ListofReviewPage deleteAllWE() {
        reviewContainer.buttonRemoveErrorWarning.click();
        return new ListofReviewPage();
    }

}
