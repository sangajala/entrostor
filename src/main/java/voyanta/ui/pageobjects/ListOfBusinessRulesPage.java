package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import voyanta.ui.utils.VUtils;


import voyanta.ui.pagecontainers.ListOfBusinessRulesPageContainer;
import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 23/07/2014.
 */
public class ListOfBusinessRulesPage extends abstractPageWithList{

    static Logger LOGGER = Logger.getLogger(ListOfBusinessRulesPage.class);


    ListOfBusinessRulesPageContainer container = ListOfBusinessRulesPage.getDataContainer(ListOfBusinessRulesPageContainer.class);

    public ListOfBusinessRulesPage() {
        //   VUtils.waitForElement(container.getDefaultElement());
           super.tableElement = container.tableElement;
       }

    public CreateRulePage createRule() {
        container.buttonCreateBR.click();
        VoyantaDriver.getCurrentDriver().navigate().refresh();
        return new CreateRulePage();
    }



    public CreateRulePage editRule(String businessruleName) {
    	container.buttonEdit.click();
    	return new CreateRulePage();
    }
    
    public int findTheRuleByName(String ruleName){
    	int rowNumber=0;
    	return rowNumber;
    }

    public void checkBusinessRuleExists(BusinessRule businessRule) {
        checkTheRowPresentWithText(businessRule.getRuleName());
    }

    public void printBRS()
    {
        getRowsInHash();
    }

    public void deleteRule(String ruleName) {

     //   LOGGER.info("Element found with text:"+actionsElements.getText());
        getActionElements(ruleName).findElement(By.linkText("Delete")).click();
        VUtils.waitFor(2);

    }

    public  WebElement getActionElements(String ruleName)
    {
        VUtils.waitFor(2);
        return getRowElementFromText("Name",ruleName,"Actions");
    }


    public void confirmDelete() {
        VoyantaDriver.click(By.linkText("Yes"));
        VUtils.waitFor(4);
        VoyantaDriver.getCurrentDriver().navigate().refresh();
        VUtils.waitFor(4);
    }

    public boolean isConfirmationDialogShown() {
        return  VoyantaDriver.isTextPresent("Confirm deletion");
    }

    public boolean isRulePresent(String ruleName) {
        return VoyantaDriver.isTextPresent(ruleName);
    }

    public CreateRulePage editRuleByName(String ruleName) {
        getActionElements(ruleName).findElement(By.linkText("Edit")).click();
        VUtils.waitFor(2);
        return new CreateRulePage();
    }


    public CreateRulePage createMappingRule() {
        container.createMappingRule.click();
        VoyantaDriver.getCurrentDriver().navigate().refresh();
        return new CreateRulePage();
    }
}
