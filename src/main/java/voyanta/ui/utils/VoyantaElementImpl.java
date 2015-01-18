package voyanta.ui.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import voyanta.ui.utils.unused.VoyantaElement;
import voyanta.ui.webdriver.core.elements.impl.ElementImpl;

/**
 * Created by sriramangajala on 08/08/2014.
 */
public class VoyantaElementImpl extends ElementImpl implements VoyantaElement {
    static Logger LOGGER = org.apache.log4j.Logger.getLogger(VoyantaElementImpl.class);

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public VoyantaElementImpl(WebElement element) {
        super(element);
    }

    public void doubleClick()
    {
        new Actions(VoyantaDriver.getCurrentDriver()).moveToElement(getWrappedElement()).doubleClick().perform();
    }

    public void clickAndDoubleClick()
    {
        VUtils.waitFor(2);
        click();
        VUtils.waitFor(2);
        doubleClick();
        VUtils.waitFor(2);
    }

    public void clickAndSendKeys(String keys) {
        VUtils.waitFor(2);
        click();
        VUtils.waitFor(2);
        sendKeys(keys);
        VUtils.waitFor(2);
    }

    @Override
    public void clearText() {
        getWrappedElement().clear();
    }

    @Override
    public void rightclick() {
        new Actions(VoyantaDriver.getCurrentDriver()).moveToElement(getWrappedElement()).contextClick().perform();
    }

    @Override
    public void clickFor(int numberOfClicks) {
        for(int i=1;i<=numberOfClicks;i++)
        {
            click();
        }
    }

}
