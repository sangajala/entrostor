package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import voyanta.ui.pagecontainers.CreateRulesPageContainers;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaBucket;
import voyanta.ui.utils.VoyantaDriver;

import java.util.List;

/**
 * Created by sriramangajala on 24/07/2014.
 */
public class CreateRulePage extends abstractWebPage {

    static Logger LOGGER = Logger.getLogger(CreateRulePage.class);

    CreateRulesPageContainers pageContainer = CreateRulePage.getDataContainer(CreateRulesPageContainers.class);

    BusinessRule businessRule = new BusinessRule();
    public CreateRulePage() {
        VUtils.waitForElement(pageContainer.getDefaultElement());
    }


    public String getHeaderText() {
        return pageContainer.getHeaderElement().getText();
    }

    public void createBusinessRuleStep1(String ruleName, String objectType, String field, String providerOption, String provider) {

        businessRule.withName(ruleName).withObjectType(objectType).withfield(field).withproviderOption(providerOption).withprovider(provider);
        pageContainer.textRuleName.clearText();
        pageContainer.textRuleName.sendKeys(ruleName);
        VoyantaDriver.selectByList(pageContainer.drpObjectType, pageContainer.drpObjectTypeSearch, objectType);
        VoyantaDriver.selectByList(pageContainer.drpField, pageContainer.drpFieldSearch, field);
        if (providerOption.equalsIgnoreCase("include")) {
        {
            pageContainer.radioproviderOption0.click();
        }
        } else {
            pageContainer.radioproviderOption0.click();
        }

        if (provider.equalsIgnoreCase("HCS"))
            pageContainer.HCS.click();
        else if (provider.equalsIgnoreCase("PROV001"))
            pageContainer.PROV001.click();
        else if (provider.equalsIgnoreCase("PROV002"))
            pageContainer.PROV002.click();
        else if (provider.equalsIgnoreCase("PROV003"))
            pageContainer.PROV003.click();
        else if (provider.equalsIgnoreCase("R04"))
            pageContainer.R04.click();
        else if (provider.equalsIgnoreCase("R05"))
            pageContainer.R05.click();
        pageContainer.btnContinue.click();
    }

    public void setClause(String value1, String value2) {

        WebElement firstElement = getElementfromText(value1);
        WebElement secondElement = pageContainer.dropClause1;
      //  clearExistingRules();
        pageContainer.linkDataValueHeader.click();
        VUtils.waitFor(2);
        VoyantaDriver.moveElement(firstElement, secondElement);
        VUtils.waitFor(2);
        firstElement = getElementfromText(value2);
        secondElement = pageContainer.dropClause2;
   //     pageContainer.linkSpecialValuesHeader.click();
        VUtils.waitFor(2);
        VoyantaDriver.moveElement(firstElement, secondElement);
        VUtils.waitFor(2);
    }

    private WebElement getElementfromText(String value1) {
        if (value1.equalsIgnoreCase("Submitted Value"))
            return pageContainer.drgSubmittedValue;
        else if (value1.equalsIgnoreCase("Current Value"))
            return pageContainer.drgCurrentValue;
        else if (value1.equalsIgnoreCase("Text"))
            return pageContainer.drgText;
        else if (value1.equalsIgnoreCase("Number"))
            return pageContainer.drgNumber;
        else if (value1.equalsIgnoreCase("BLANK"))
            return pageContainer.drgBLANK;
        else
            return null;
    }

    public ListOfBusinessRulesPage createBusinessRuleStep2(String field, String fieldName, String operator, String value, String brType, String messageType, String message) {

        businessRule.createRule().withfield(field).withfieldName(fieldName).withOperator(operator).withValue(value).withbrType(brType).withMessageType(messageType).withMessage(message);
        VoyantaBucket.setBusinessRule(businessRule);


        selectElementFromDragAndDropfield(pageContainer.linkSelectValue, field, fieldName);
        VUtils.waitFor(2);
        VoyantaDriver.selectByList(pageContainer.drpOutCome, null, brType);
        selectElementFromDragAndDropfield(pageContainer.linkSelectValue, value, fieldName);

        WebElement firstElement = getElementfromText(messageType);
        WebElement secondElement = pageContainer.dropClause3;
        VUtils.waitFor(2);
      //  pageContainer.linkSpecialValuesHeader.click();
        VUtils.waitFor(2);
        VoyantaDriver.moveElement(firstElement, secondElement);
        VUtils.waitFor(5);
        selectElementFromDragAndDropfield(pageContainer.linkSelectValue, message, message);
//        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.waitFor(3);
//        VoyantaDriver.findElement(By.linkText("Enter Text")).click();
////        VUtils.waitFor(2);
//
////        VoyantaDriver.findElement(By.linkText("Enter Text")).click();
//        VoyantaDriver.waitFor(3);
//
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
//        VoyantaDriver.waitFor(3);
//
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].text = 'hfa'");
////        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
////        new Actions(VoyantaDriver.getCurrentDriver()).moveToElement(VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input"))).doubleClick().perform();
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).hover()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")));
////        VoyantaDriver.mouseOver(By.cssSelector("div.type-item.userDefinedValue:nth-child(1) > input"));
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
//        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys("\n");
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
//        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
//        VUtils.waitFor(2);
//        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
//        VUtils.waitFor(2);
//        pageContainer.outSideArea2.click();
//        VUtils.waitFor(2);
//
////        VoyantaDriver.executeJS("return typeof jQuery == 'undefined'");
//
//
//     //   VoyantaDriver.executeJS("var jq = document.createElement('script');jq.src = '//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js';document.getElementsByTagName('head')[0].appendChild(jq);");
//
////          ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("arguments[0].cl()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")));
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).click()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")));
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).click()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")));
//////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys("\n");
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(Keys.RETURN);
      //  VUtils.waitFor(5);
        pageContainer.btnSaveNewRule.click();
//        pageContainer.linkTextField1.click();
//        pageContainer.txtTextFiled1.sendKeys(value);
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue > a")).get(1).click();
        VUtils.waitFor(2);

//        VoyantaDriver.findElement(By.xpath("//input[@type='text'])[7]")).sendKeys(message);
//        pageContainer.txtTextFiled1.sendKeys(message);
//        VUtils.waitFor(2);
//        pageContainer.btnSaveNewRule.click();
//        VUtils.waitFor(10);
//
//        if(VoyantaDriver.elementExists(pageContainer.btnSaveNewRule)) {
////            pageContainer.linkTextField.click();
//            VUtils.waitFor(2);
//            pageContainer.txtTextFiled.click();
//            VUtils.waitFor(2);
//            pageContainer.txtTextFiled.sendKeys(value);
//            VUtils.waitFor(2);
//            pageContainer.txtTextFiled.click();
//            VUtils.waitFor(2);
////            VoyantaDriver.findElement(By.linkText("Enter Text")).click();
////        VUtils.waitFor(2);
//
////        VoyantaDriver.findElement(By.linkText("Enter Text")).click();
//            VoyantaDriver.waitFor(3);
//            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VoyantaDriver.waitFor(3);
//
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].text = 'hfa'");
////        new Actions(VoyantaDriver.getCurrentDriver()).moveToElement(VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input"))).doubleClick().perform();
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).hover()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")));
////        VoyantaDriver.mouseOver(By.cssSelector("div.type-item.userDefinedValue:nth-child(1) > input"));
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
//            VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys("\n");
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
//            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
//            VUtils.waitFor(2);
////            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).click();
////            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).
////            pageContainer.btnSaveNewRule.click();
////            pageContainer.linkTextField.click();
//            VUtils.waitFor(2);
//            pageContainer.btnSaveNewRule.click();
//        }

//        VUtils.waitFor(10);

//        if(VoyantaDriver.elementExists(By.linkText(message))) {
////        VUtils.WaitForAllAjaxCalls(VoyantaDriver.getCurrentDriver(),30);
//            VoyantaDriver.findElement(By.linkText(message)).click();
//            VUtils.waitFor(10);
//            pageContainer.btnSaveNewRule.click();
//        }
        VUtils.waitFor(10);
        return new ListOfBusinessRulesPage();
    }

    public ListOfBusinessRulesPage modifyBusinessRuleStep2(String field, String fieldName, String operator, String value, String brType, String messageType, String message) {

        businessRule.createRule().withfield(field).withfieldName(fieldName).withOperator(operator).withValue(value).withbrType(brType).withMessageType(messageType).withMessage(message);
        VoyantaBucket.setBusinessRule(businessRule);

        List<WebElement> existingRules = VoyantaDriver.getCurrentDriver().findElements(By.className("actionRemoveType"));
//        for(WebElement element:existingRules)
//        {
//            element.click();
//        }
        existingRules.get(2).click();
//        selectElementFromDragAndDropfield(pageContainer.linkSelectValue, field, fieldName);
//        VUtils.waitFor(2);
//        VoyantaDriver.selectByList(pageContainer.drpOutCome, null, brType);
//        selectElementFromDragAndDropfield(pageContainer.linkSelectValue, value, fieldName);

        WebElement firstElement = getElementfromText(messageType);
        WebElement secondElement = pageContainer.dropClause3;
        VUtils.waitFor(2);
        //  pageContainer.linkSpecialValuesHeader.click();
        VUtils.waitFor(2);
        VoyantaDriver.moveElement(firstElement, secondElement);
        VUtils.waitFor(5);
        selectElementFromDragAndDropfield(pageContainer.linkSelectValue, message, message);
//        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.waitFor(3);
//        VoyantaDriver.findElement(By.linkText("Enter Text")).click();
////        VUtils.waitFor(2);
//
////        VoyantaDriver.findElement(By.linkText("Enter Text")).click();
//        VoyantaDriver.waitFor(3);
//
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
//        VoyantaDriver.waitFor(3);
//
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].text = 'hfa'");
////        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
////        new Actions(VoyantaDriver.getCurrentDriver()).moveToElement(VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input"))).doubleClick().perform();
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).hover()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")));
////        VoyantaDriver.mouseOver(By.cssSelector("div.type-item.userDefinedValue:nth-child(1) > input"));
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
//        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys("\n");
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
//        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
//        VUtils.waitFor(2);
//        VoyantaDriver.executeJS("$( \".hidden\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].setAttribute(\"Style\",\"\")");
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
//        VUtils.waitFor(2);
//        pageContainer.outSideArea2.click();
//        VUtils.waitFor(2);
//
////        VoyantaDriver.executeJS("return typeof jQuery == 'undefined'");
//
//
//     //   VoyantaDriver.executeJS("var jq = document.createElement('script');jq.src = '//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js';document.getElementsByTagName('head')[0].appendChild(jq);");
//
////          ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("arguments[0].cl()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")));
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).click()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")));
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).click()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")));
//////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys("\n");
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(Keys.RETURN);
        //  VUtils.waitFor(5);
        pageContainer.btnSaveNewRule.click();
//        pageContainer.linkTextField1.click();
//        pageContainer.txtTextFiled1.sendKeys(value);
//        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue > a")).get(1).click();
        VUtils.waitFor(2);

//        VoyantaDriver.findElement(By.xpath("//input[@type='text'])[7]")).sendKeys(message);
//        pageContainer.txtTextFiled1.sendKeys(message);
//        VUtils.waitFor(2);
//        pageContainer.btnSaveNewRule.click();
//        VUtils.waitFor(10);
//
//        if(VoyantaDriver.elementExists(pageContainer.btnSaveNewRule)) {
////            pageContainer.linkTextField.click();
//            VUtils.waitFor(2);
//            pageContainer.txtTextFiled.click();
//            VUtils.waitFor(2);
//            pageContainer.txtTextFiled.sendKeys(value);
//            VUtils.waitFor(2);
//            pageContainer.txtTextFiled.click();
//            VUtils.waitFor(2);
////            VoyantaDriver.findElement(By.linkText("Enter Text")).click();
////        VUtils.waitFor(2);
//
////        VoyantaDriver.findElement(By.linkText("Enter Text")).click();
//            VoyantaDriver.waitFor(3);
//            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VoyantaDriver.waitFor(3);
//
////        VoyantaDriver.executeJS("$( \".label.actionActivate\")[2].text = 'hfa'");
////        new Actions(VoyantaDriver.getCurrentDriver()).moveToElement(VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input"))).doubleClick().perform();
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        ((JavascriptExecutor)VoyantaDriver.getCurrentDriver()).executeScript("$(arguments[0]).hover()",VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")));
////        VoyantaDriver.mouseOver(By.cssSelector("div.type-item.userDefinedValue:nth-child(1) > input"));
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
////        VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).click();
//            VUtils.waitFor(2);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys("\n");
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
////        VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).click();
//            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("input")).sendKeys(message);
//            VUtils.waitFor(2);
////            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).click();
////            VoyantaDriver.findElements(By.cssSelector("div.type-item.userDefinedValue")).get(1).findElement(By.tagName("a")).
////            pageContainer.btnSaveNewRule.click();
////            pageContainer.linkTextField.click();
//            VUtils.waitFor(2);
//            pageContainer.btnSaveNewRule.click();
//        }

//        VUtils.waitFor(10);

//        if(VoyantaDriver.elementExists(By.linkText(message))) {
////        VUtils.WaitForAllAjaxCalls(VoyantaDriver.getCurrentDriver(),30);
//            VoyantaDriver.findElement(By.linkText(message)).click();
//            VUtils.waitFor(10);
//            pageContainer.btnSaveNewRule.click();
//        }
        VUtils.waitFor(10);
        return new ListOfBusinessRulesPage();
    }

    private void clearExistingRules() {
        List<WebElement> existingRules = VoyantaDriver.getCurrentDriver().findElements(By.className("actionRemoveType"));
        for(WebElement element:existingRules)
        {
            element.click();
        }
    }

    private void selectElementFromDragAndDropfield(WebElement selectelement, String field, String fieldName) {
        selectelement.click();
        VUtils.waitFor(2);
        VoyantaDriver.scrollfindElement(By.linkText(field),By.cssSelector("div.scroll-down.active"));
        VUtils.waitFor(1);
    }

    public String getMessageAfterSave() {
        return pageContainer.pageText.getText();
    }

    public String checkSucessMessage() {
           if(VoyantaDriver.elementExists(pageContainer.sucessMessage))
           {
                return pageContainer.sucessMessage.getText();
           }
        return null;
    }

    public void createMappingRuleStep2(String from, String to, String notes) {

        VoyantaBucket.setBusinessRule(businessRule);
        VUtils.waitFor(2);

        pageContainer.outComeTableFromRow1.clickAndDoubleClick();
        pageContainer.textArea.clearText();
        pageContainer.textArea.clickAndSendKeys(from);

        pageContainer.outComeTableToRow1.clickAndDoubleClick();
        pageContainer.textArea.clearText();
        pageContainer.textArea.clickAndSendKeys((to));
        pageContainer.outComeTableNotesRow1.clickAndDoubleClick();
        pageContainer.textArea.clearText();
        pageContainer.textArea.clickAndSendKeys(notes);

        pageContainer.btnSaveNewRule.click();
        VUtils.waitFor(29);
    }

    public void gotoPreviousPage() {
        pageContainer.gotoStep1.click();
    }
}