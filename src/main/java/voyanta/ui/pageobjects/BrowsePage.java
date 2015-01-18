package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import voyanta.ui.pagecontainers.BrowsePageContainer;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.webdriver.core.elements.Select;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class BrowsePage extends  abstractPageWithList{

    static Logger LOGGER = Logger.getLogger(BrowsePage.class);

    BrowsePageContainer pageContainer = BrowsePage.getDataContainer(BrowsePageContainer.class);

    public BrowsePage()
    {

        VUtils.waitForElement(pageContainer.content);
        if(!pageContainer.content.getText().contains("Order by"))
            throw new RuntimeException("Browse page not loaded");
    }


    public void checkPermissionRecordExists(String type, String objectName, String objectReference, String levelOfAccess) {
                getRowsAsHashIgnoreBlankHeaders();
                int rowNumber = getRowNumber("Object",objectName+" - "+objectReference);
                LOGGER.info("Found the object in row number "+rowNumber);
                VerifyUtils.True(rowNumber>0);
    }

    public void openObjectWithName(String objectName) {
        VoyantaDriver.getDivByText(objectName).click();

    }






    public void checkChildLevelObjectCanbeOpened(String objectName,boolean b) {
       // VerifyUtils.equals(b,checkChildLevelObjectCanbeOpened(objectName));
    }

    public void checkChildLevelObjectCanbeOpened(String name, String childObjectType,String childObjectName,  String fieldName, String objectName, boolean b) {

        VerifyUtils.equals(b,checkChildLevelObjectCanbeOpened(name,childObjectType,childObjectName,fieldName,objectName));
    }

    public boolean checkChildLevelObjectCanbeOpened(String name, String childObjectType,String childObjectName,  String fieldName, String fieldValue) {

        WebElement parentElement;
        try
        {

          //  VoyantaDriver.getDivByText(name).click();
            VUtils.waitFor(2);
            parentElement = VoyantaDriver.findElement(By.linkText(name)).findElement(By.xpath("parent::*"));
            if(!parentElement.getText().contains(childObjectType))
            {
                LOGGER.info("The child object type "+childObjectType+" is not visible so returning false....");
                return false;
            }
            else
            {

                LOGGER.info("The child object type "+childObjectType+" visible ");
                VUtils.waitFor(2);

                LOGGER.info("The child object type "+childObjectType+" is visible ");

                VoyantaDriver.getLinkByText(childObjectType).click();
                VUtils.waitFor(5);
                parentElement = VoyantaDriver.findElement(By.linkText(name)).findElement(By.xpath("parent::*"));

                if(!parentElement.getText().contains(childObjectName))
                {
                    LOGGER.info("The child object name "+childObjectName+" is not visible so returning false....");
                    return false;
                }
                else
                {
                    LOGGER.info("The child object name "+childObjectName+"is visible ");
                    VoyantaDriver.getLinkByText(childObjectName).click();
                    VUtils.waitFor(2);
                    checkFieldVisible(fieldName,fieldValue);
                    return true;
                }


            }
        }
        catch (TimeoutException e)
        {
            throw  new RuntimeException("Unable to load the data for object data in time  : "+name);
        }
        catch (NoSuchElementException e)
        {
            throw  new RuntimeException("Unable to load the data for object data : "+name);
        }
       // return true;
    }


    public void downloadDST() {
            pageContainer.linkDownload.click();
            VUtils.waitFor(5);
    }



    private boolean canUserAttachFiles() {
        try
        {
        return  pageContainer.btnAttachFiles.isDisplayed();
        }
            catch (TimeoutException e)
            {
                throw  new RuntimeException("Unable to find the attach button");
            }
            catch (NoSuchElementException e)
            {
                throw  new RuntimeException("Unable to find the attach button");
            }
    }


    public void checkFieldVisible(String fieldName, String fieldValue) {
        HashMap data = getSectionData("References");
        if(data.containsKey(fieldName))
        {
            LOGGER.info("Found field "+fieldName);
            if(data.get(fieldName).equals(fieldValue))
            {
                LOGGER.info("Found field value "+fieldValue);
            }
            else
                VerifyUtils.fail("Field value not found "+fieldValue);
        }
        else
        {
            VerifyUtils.fail("Field not found " + fieldName);
        }
//        VoyantaDriver.getDivByText("References").findElement(By.xpath("parent::*")).getText();
//        VoyantaDriver.findElement(By.xpath("//h1[text()='References']")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).findElement(By.tagName("table")).getText();
//    }
    }

    public HashMap getSectionData(String section) {

        HashMap hashMap=null;
        LOGGER.info("Getting the section data "+section);

        try
        {
            if(VoyantaDriver.findElement(By.xpath("//h1[text()='"+section+"']")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).findElement(By.tagName("table")).isDisplayed())
            {
                WebElement table = VoyantaDriver.findElement(By.xpath("//h1[text()='"+section+"']")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).findElement(By.tagName("table"));
                hashMap = getRowsAsHashFromSection(table);
            }
        }
        catch (TimeoutException e)
        {
            throw  new RuntimeException("Unable to load the data for section data "+section);
        }
        catch (NoSuchElementException e)
        {
            throw  new RuntimeException("Unable to load the data for section data "+section);
        }
        return hashMap;
    }

    public HashMap getRowsAsHashFromSection(WebElement table) {

        HashMap  hashMap = new LinkedHashMap();
        String key,value;

        try {

            for (WebElement row : table.findElements(By.tagName("tr"))) {

                key = row.findElements(By.tagName("td")).get(0).getText();
                value = row.findElements(By.tagName("td")).get(1).getText().split("\n")[0];
                LOGGER.info("Loading the section data with key: '" + key + "' value: '" + value+"'");
                hashMap.put(key,value);

            }
        }
        catch (java.util.NoSuchElementException e)
        {
            throw new RuntimeException("Unable to load the elements from table "+table.getText());
        }

        return hashMap;
    }

    public void selectEditButton() {
        pageContainer.linkEdit.click();
        VUtils.waitFor(5);
    }


}
