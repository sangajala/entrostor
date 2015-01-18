package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.unused.VoyantaElement;

/**
 * Created by sriramangajala on 29/07/2014.
 */
public class ReportsPageContainer extends abstractVoyataPageContainer {
    @FindBy(how = How.ID, using = "app-headings")
    public WebElement headerElement;

    @FindBy(how = How.ID, using = "c2_label")
    public VoyantaElement textRuleName;

    @FindBy(how = How.XPATH, using = "//div[@id='dstTypeId_chzn']/a/div/b")
    public WebElement drpObjectType;

    @FindBy(how = How.XPATH, using = "//div[@id='dstColumnId_chzn']/a/div/b")
    public WebElement drpField;

    @FindBy(how = How.XPATH, using = "//div[@id='comparator_0_0_chzn']/a/div")
    public WebElement drpComparator;

    @FindBy(how = How.CSS, using = "#severityType_selector_chzn > a.chzn-single > div > b")
    public WebElement drpOutCome;


    @FindBy(how = How.XPATH, using = "//div[@class='chzn-search']/input")
    public WebElement drpObjectTypeSearch;

    @FindBy(how = How.XPATH, using = "//div[@id='dstColumnId_chzn']/div/div/input")
    public WebElement drpFieldSearch;

    @FindBy(how = How.ID, using = "c2_providersSelection-0")
    public WebElement radioproviderOption0;

    @FindBy(how = How.ID, using = "c2_providersSelection-1")
    public WebElement radioproviderOption1;

    @FindBy(how = How.ID, using = "c2_providerKeys-0")
    public WebElement HCS;

    @FindBy(how = How.ID, using = "c2_providerKeys-1")
    public WebElement PROV001;

    @FindBy(how = How.ID, using = "c2_providerKeys-2")
    public WebElement PROV002;

    @FindBy(how = How.ID, using = "c2_providerKeys-3")
    public WebElement PROV003;

    @FindBy(how = How.ID, using = "c2_providerKeys-4")
    public WebElement R04;

    @FindBy(how = How.ID, using = "c2_providerKeys-5")
    public WebElement R05;

    @FindBy(how = How.ID, using = "ui-accordion-rule-selector-header-0")
    public WebElement linkDataValueHeader;

    @FindBy(how = How.CSS, using = "li.submittedValue.ui-draggable")
    public WebElement drgSubmittedValue;

    @FindBy(how = How.CSS, using = "li.currentValue.ui-draggable")
    public WebElement drgCurrentValue;

    @FindBy(how = How.ID, using = "ui-accordion-rule-selector-header-1")
    public WebElement linkUserSetValuesHeader;



    @FindBy(how = How.CSS, using = "li.userDefinedValue.ui-draggable")
    public WebElement drgText;

    @FindBy(how = How.ID, using = "ui-accordion-rule-selector-header-2")
    public WebElement linkSpecialValuesHeader;

    @FindBy(how = How.CSS, using = "div[id='ui-accordion-rule-selector-panel-1'] ul li:nth-child(3)")
    public WebElement drgDate;

    @FindBy(how = How.CSS, using = "div[id='ui-accordion-rule-selector-panel-1'] ul li:nth-child(2)")
    public WebElement drgNumber;

    @FindBy(how = How.CSS, using = "li.blankValue.ui-draggable")
    public WebElement drgBLANK;

    @FindBy(how = How.CSS, using = "li.operatorValue.ui-draggable")
    public WebElement drgMathOperator;

    @FindBy(how = How.LINK_TEXT, using = "Save and Exit")
    public WebElement btnSaveNewRule;

    @FindBy(how = How.LINK_TEXT, using = "Back to Step 1")
    public WebElement btnBackToStep1;

    @FindBy(how = How.LINK_TEXT, using = "Cancel Rule")
    public WebElement btnCancelRule;

    @FindBy(how = How.CSS, using = "div.clause> div:nth-child(1)")
    public WebElement dropClause1;

    @FindBy(how = How.CSS, using = "div.clause> div:nth-child(3)")
    public WebElement dropClause2;

    @FindBy(how = How.XPATH, using = "//div[@id='app-outcome']/div/div[2]/div")
    public WebElement dropClause3;

    @FindBy(how = How.NAME, using = "save")
    public WebElement btnContinue;

    @FindBy(how = How.LINK_TEXT, using = "Select value")
    public WebElement linkSelectValue;

    @FindBy(how = How.CSS, using = "div.type-item.userDefinedValue > a")
    public WebElement linkTextField;

    @FindBy(how = How.CSS, using = "div.type-item.userDefinedValue > input")
    public WebElement txtTextFiled;

    @FindBy(how = How.CSS, using = "div.righthand-side.drop-area.ui-droppable")
    public WebElement outSideArea1;

    @FindBy(how = How.CSS, using = "div.outcome.drop-area.ui-droppable")
    public WebElement outSideArea2;


    @FindBy(how = How.CSS, using = "div.type-item.userDefinedValue:nth-child(1) > a")
    public WebElement linkTextField1;

    @FindBy(how = How.CSS, using = "div.type-item.userDefinedValue:nth-child(1) > input")
    public WebElement txtTextFiled1;

    @FindBy(how = How.ID, using = "container")
    public WebElement pageText;
    @FindBy(how = How.CSS, using = "div.message-list.success")
    public WebElement sucessMessage;

    @FindBy(how = How.XPATH, using = "//table[@class='htCore']/tbody/tr/td")
    public VoyantaElement outComeTableFromRow1;

    @FindBy(how = How.XPATH, using = "//table[@class='htCore']/tbody/tr[2]/td")
    public WebElement outComeTableFromRow2;

    @FindBy(how = How.CSS, using = "textarea.handsontableInput")
    public VoyantaElement textArea;

    @FindBy(how = How.XPATH, using = "//table[@class='htCore']//tbody//tr//td[2]")
    public VoyantaElement outComeTableToRow1;

    @FindBy(how = How.XPATH, using = "//table[@class='htCore']/tbody/tr/td[3]")
    public VoyantaElement outComeTableNotesRow1;

    @FindBy(how = How.CSS, using = "a.voyantaButton.medium.actionBack")
    public WebElement gotoStep1;

    @FindBy(how = How.XPATH, using = "//div[text()='Current Selections']")
    public VoyantaElement currentSelectionsHeader;

    @FindBy(how = How.CSS, using = "div.ModalDialog_Header > span")
    public WebElement downloadConfirmBox;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class,'QvButton')]/button)[2]")
    public WebElement clearFiltersButton;

    @FindBy(how = How.CLASS_NAME, using = "Qv_Hotspot")
    public VoyantaElement toggleButton;

    @Override
    public WebElement getDefaultElement() {
        return headerElement;
    }

    @Override
    public WebElement getHeaderElement() {
        return headerElement;
    }

    private String locator;

    private  String value;

    public ReportsPageContainer getDialogBox() {
        this.locator = "css";
        this.value = "button.ModalDialog_Button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only";
        return this;
    }

    public String getLocator() {
        return locator;
    }

    public String getValue() {
        return value;
    }
}
