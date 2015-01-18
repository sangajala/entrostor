package voyanta.ui.utils.unused;

public class ClickAndWaitForElement implements Condition {
    @Override
    public boolean isSatisfied() {
        return false;
    }

    @Override
    public String describe() {
        return null;
    }
//
//    private static final Logger LOGGER = Logger.getLogger(com.venkyold.org.adv.advance.ClickAndWaitForElement.class);
//    private WebElement button;
//    private ElementIsDisplayed elementIsDisplayed;
//
//    public ClickAndWaitForElement(WebElement button, WebElement view) {
//        this.button = button;
//        elementIsDisplayed = new ElementIsDisplayed(view);
//    }
//
//    public boolean isSatisfied() {
//        try {
//            if (elementIsDisplayed.isSatisfied()) return true;
//            button.click();
//            return false;
//        } catch (Exception e) {
//            LOGGER.info(e.getClass().getSimpleName());// + " thrown while waiting for condition [" + describe() + "]", true);
//            return false;
//        }
//    }
//
//    public String describe() {
//        return "Waiting for click event to display view. Element xpath [" + CoreDriver.getXPath(button) + "]";
//    }

}
