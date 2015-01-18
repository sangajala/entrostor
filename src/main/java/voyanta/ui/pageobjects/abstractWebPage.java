package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import voyanta.ui.pagecontainers.abstractPageContainer;
import voyanta.ui.pagecontainers.abstractVoyataPageContainer;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.webdriver.core.elements.impl.internal.ElementFactory;

import java.util.List;

/**
 * Created by sriramangajala on 24/07/2014.
 */
public class abstractWebPage implements TestersWorldPage {

    static Logger LOGGER = Logger.getLogger(abstractWebPage.class);

    abstractVoyataPageContainer pageContainer = getDataContainer(abstractVoyataPageContainer.class);

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public boolean isPageDisplayed() {
        return false;
    }

    @Override
    public VoyantaUser getCurrentUser() {
        return null;
    }

    @Override
    public String getCurrentPageText() {
        return null;
    }

    @Override
    public boolean waitTillPageLoaded() {
        return false;
    }
    public static <T extends abstractPageContainer> T getDataContainer(Class<T> className) {
       // T boundDataContainer = (T) PageFactory.initElements(VoyantaDriver.getCurrentDriver(), className);

        T boundDataContainer = (T) ElementFactory.initElements(VoyantaDriver.getCurrentDriver(), className);
        return boundDataContainer;
    }

    public ListOfBusinessRulesPage gotoBusinessRulePage()
    {
        pageContainer.TaskMenu.click();
        VUtils.waitFor(2);
        pageContainer.linkBusinessRules.click();
        return new ListOfBusinessRulesPage();
    }
    



    public DashboardPage gotoHomePage()
    {
        pageContainer.linkHome.click();
        VUtils.waitFor(2);
        return new DashboardPage();
    }
    
    public UploadPage goToUploadPage()
    {
        pageContainer.TaskMenu.click();
        VUtils.waitFor(2);
        pageContainer.linkUploadData.click();
        return new UploadPage();
    }


    public void gotoOperatingPage() {
        VoyantaDriver.getCurrentDriver().switchTo().defaultContent();
        pageContainer.linkOperatingPage.click();
    }

    public PermissionsPage gotoPermissionsPage() {
        pageContainer.linkPermission.click();
        return new PermissionsPage();

    }





    public void gotoTenantPage() {
        VoyantaDriver.getCurrentDriver().switchTo().defaultContent();
        pageContainer.linkTenant.click();
    }


    public void logout() {
        pageContainer.TaskMenu.click();
        VUtils.waitFor(2);
        pageContainer.linkLogout.click();
    }

    public void gotoTab(String tabname) {
        int i = 0;
        WebElement currentTab = VoyantaDriver.findElement(By.id("tabs-menu"));
        for (WebElement element : currentTab.findElements(By.tagName("a"))) {
            if (element.getText().equals(tabname)) {
                element.click();
                break;
            }
            VUtils.waitFor(5);
        }

    }

    public void checkButtonDisabled(boolean b, String buttonText) {
        try {
            VerifyUtils.equals(b, !VoyantaDriver.findElement(By.linkText(buttonText)).findElement(By.xpath("parent::div")).getAttribute("class").contains("disabled"));
        }
        catch (TimeoutException e)
        {
            throw  new RuntimeException("Unable to find the button with text "+buttonText);
        }
        catch (NoSuchElementException e)
        {
            throw  new RuntimeException("Unable to find the button with text "+buttonText);
        }
    }

    public void checkImageDisabled(boolean b, String imageName) {
        try {
            VerifyUtils.equals(b, !VoyantaDriver.findElement(By.cssSelector("img[alt='"+imageName+"']")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::div")).getAttribute("class").contains("disabled"));
        }
        catch (TimeoutException e)
        {
            throw  new RuntimeException("Unable to find the image with alt "+imageName);
        }
        catch (NoSuchElementException e)
        {
            throw  new RuntimeException("Unable to find the image with alt  "+imageName);
        }
    }

    public void checkButtonVisible(boolean expected, String buttonName) {

            VerifyUtils.equals(expected, VUtils.isElementPresent(By.linkText(buttonName)));

    }


    public void changeDropDownValue(String name, String value) {
        try

        {
            WebElement tableRowElement = VoyantaDriver.findElement(By.xpath("//strong[text()='" + name + "']")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::tr"));
            LOGGER.info("Found the row with name " + name);
            tableRowElement.findElement(By.className("chzn-single")).click();
            VUtils.waitFor(5);
            try
            {
                tableRowElement = VoyantaDriver.findElement(By.xpath("//strong[text()='" + name + "']")).findElement(By.xpath("parent::*")).findElement(By.xpath("parent::tr"));
                LOGGER.info("Found the row with name " + name);
//                tableRowElement.findElement(By.className("chzn-single")).click();
                VUtils.waitFor(2);
                List<WebElement> options = tableRowElement.findElements(By.className("active-result"));
                for (WebElement element : options) {
                    if (element.getText().equalsIgnoreCase(value)) {
                        LOGGER.info("Found the option with text " + value);
                        element.click();
                        VUtils.waitFor(5);
                        return;
                    }
                }

                LOGGER.info("Option with text " + value + " not found");
                VerifyUtils.fail("Option with text " + value + " not found");
            } catch (TimeoutException e) {
                throw new RuntimeException("Unable to find the name with alt " + name);}
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Unable to find the name with alt " + name);
            }
        }

    }


