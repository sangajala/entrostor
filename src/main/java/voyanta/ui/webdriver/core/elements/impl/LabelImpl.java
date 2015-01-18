package voyanta.ui.webdriver.core.elements.impl;

import org.openqa.selenium.WebElement;
import voyanta.ui.webdriver.core.elements.Label;


/**
 * Wraps a label on a html form with some behavior.
 */
public class LabelImpl extends ElementImpl implements Label {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public LabelImpl(WebElement element) {
        super(element);
    }

    @Override
    public String getFor() {
        return getWrappedElement().getAttribute("for");
    }
}
