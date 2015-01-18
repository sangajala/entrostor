package voyanta.ui.pagecontainers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//import voyanta.ui.data.voyanta.PropertiesLoader;
//import voyanta.ui.data.voyanta.VoyantaDriver;



import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VoyantaDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class UploadPageContainer extends abstractVoyataPageContainer {
 
	public String url=PropertiesLoader.getProperty("ui_url")+"/submission/upload";


    @FindBy(how = How.TAG_NAME, using = "Body")
    private WebElement body;

	 @FindBy(how = How.CSS, using = ".fileinput-button>input")
	    public WebElement selectFileButton;
	 
	 @FindBy(how = How.NAME, using = "save")
	    public WebElement submitButton;
	 
	 @FindBy(how = How.ID, using = "name")
	 public WebElement nameInput;
	 
	 @FindBy(how = How.ID, using = "notes")
	 public WebElement notesInput;
	 
	 private WebElement sucMsg;
	 private static String sucMsgLoc=".green";
	 
	 private WebElement errorMsg;
	 private static String errorMsgLoc="";

    @FindBy(how = How.CSS, using = "div.proccess-state.state-uploaded")
    public WebElement uploadedStatus;
    
    private String uploadedStatusLoc="div.proccess-state.state-uploaded";
    
    @FindBy(how = How.CSS, using = "div.status-bar")
    public WebElement uploadedResult;
    private String uploadResultLoc="div.status-bar";
    
    @FindBy(how = How.ID, using = "app-headings")
    public WebElement headerElement;
    
    @FindBy(how = How.CSS, using = "span.error.hidden.red")
    public WebElement uploadMSG;
    private String uploadMSGLoc="span.error.hidden.red";
    
    @FindBy(how = How.CSS, using = ".content-wrapper.noTabsMenu>h2")
    public WebElement subTitle;
    
    @Override
    public WebElement getDefaultElement() {
        return null;
    }

    @Override
    public WebElement getHeaderElement() {
        return headerElement;
    }
    
	public WebElement getSucMsg(){
		sucMsg=VoyantaDriver.findElement(By.cssSelector(sucMsgLoc));
		return sucMsg;
	}
	
	public WebElement getUploadedStatus(){
		uploadedStatus=VoyantaDriver.findElement(By.cssSelector(uploadedStatusLoc));
		return uploadedStatus;
	}
	
	public WebElement getUploadResult(){
		uploadedResult=VoyantaDriver.findElement(By.cssSelector(uploadResultLoc));
		return uploadedResult;
	}
	
	public WebElement getUploadMSG(){
		uploadMSG=VoyantaDriver.findElement(By.cssSelector(uploadMSGLoc));
		return uploadMSG;
	}
}
