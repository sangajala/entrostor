package voyanta.ui.utils.unused;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.webdriver.core.elements.Element;
import voyanta.ui.webdriver.core.elements.impl.ElementImpl;
import voyanta.ui.webdriver.core.elements.impl.internal.ImplementedBy;

/**
 * Created by sriramangajala on 08/08/2014.
 */
@ImplementedBy(voyanta.ui.utils.VoyantaElementImpl.class)
public interface VoyantaElement extends Element {
    public void doubleClick();


    public void clickAndDoubleClick();

    public void clickAndSendKeys(String keys);

    public void clearText();


    public void rightclick();

    public void clickFor(int i);
}
