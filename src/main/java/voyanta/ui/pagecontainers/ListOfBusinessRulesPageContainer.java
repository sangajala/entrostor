package voyanta.ui.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import voyanta.ui.pagecontainers.abstractPageContainer;

/**
 * Created by sriramangajala on 24/07/2014.
 */

public class ListOfBusinessRulesPageContainer extends abstractVoyataPageContainer {
    @FindBy(how = How.LINK_TEXT, using = "Create Business Rule")
    public WebElement createBusinessRule;

    @FindBy(how = How.CLASS_NAME, using = "list-grid")
    public WebElement defaultElement;


    @FindBy(how = How.CLASS_NAME, using = "list-grid")
    public WebElement tableElement;
    @FindBy(how = How.LINK_TEXT, using = "Replace / Mappings")
    public WebElement linkMappingRule;

    @FindBy(how = How.LINK_TEXT, using = "Create Replace or Mapping Rule")
    public WebElement createMappingRule;

    public WebElement getDefaultElement() {
        return defaultElement;
    }

    @Override
    public WebElement getHeaderElement() {
        return null;
    }

    @FindBy(how = How.LINK_TEXT, using = "Create Business Rule")
    public WebElement buttonCreateBR;

    @FindBy(how = How.ID, using = "showSystemRules")
    public WebElement checkBoxSystemRules;

    @FindBy(how = How.ID, using = "voyantaButton.small.icon.actionEditRule")
    public WebElement buttonEdit;
    @FindBy(how = How.ID, using = ".voyantaButton.small.icon.actionDeleteRule")
    public WebElement buttonDelete;

    @FindBy(how = How.ID, using = ".voyantaButton.small.icon.actionUnpublishRule")
    public WebElement buttonUnpublish;
    @FindBy(how = How.ID, using = ".voyantaButton.small.icon.actionPublishRule")
    public WebElement buttonPublish;

    @FindBy(how = How.LINK_TEXT, using = "Name")
    public WebElement headerName;
    @FindBy(how = How.LINK_TEXT, using = "Type")
    public WebElement headerType;
    @FindBy(how = How.LINK_TEXT, using = "CreatedBy")
    public WebElement headerCreatedBy;
    @FindBy(how = How.LINK_TEXT, using = "Created")
    public WebElement headerCreated;


	@FindBy(how = How.CSS, using = ".list-grid")
	public WebElement tableBR;

	
}
