package voyanta.ui.experimental;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import voyanta.ui.webdriver.core.elements.*;
import voyanta.ui.webdriver.core.elements.impl.internal.ElementFactory;


import java.net.MalformedURLException;
import java.util.List;

/**
 * declare elements of a form.
 */
public class FormTestObject {

    private WebDriver driver;

    public TextInput texta;

    @FindBy(id = "test1")
    public Element element;

    @FindBy(id = "test1")
    public WebElement webElement;

    @FindBy(id = "option1")
    public Select option1;

    @FindBy(id = "checkbox")
    public CheckBox checkbox;
    
    @FindBy(id = "table")
    public Table table;

    @FindBy(tagName = "label")
    public List<Label> labels;

    @FindBy(tagName = "label")
    public List<Element> elementLabels;

    @FindBy(tagName = "label")
    public List<WebElement> webElementLabels;

    @FindBy(css = "label[for='textb']")
    public WebElement labelForTextB;

    public FormTestObject(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Static factory for generating the class.
     *
     * @param driver The WebDriver for the session.
     * @return FormTestObject.
     */
    public static FormTestObject initialize(WebDriver driver) {
        return ElementFactory.initElements(driver, FormTestObject.class);
    }

    public void get() {
        try {
            PageLoader.get(driver, "forms.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        driver.close();
    }

}
