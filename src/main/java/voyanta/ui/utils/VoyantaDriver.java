package voyanta.ui.utils;

//import com.venkyold.org.abstractTest;
//import static com.venkyold.org.adv.advance.AutomationConstants.DEFAULT_WAIT_SECONDS;
//import static com.venkyold.org.adv.advance.AutomationConstants.TWENTY_SECONDS;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: dev
 * Date: 05/04/13
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class VoyantaDriver {
    private static final Logger LOGGER = Logger.getLogger(VoyantaDriver.class);
    private static final Integer DEFAULT_WAIT_SECONDS = 120;
    private static final int TWENTY_SECONDS = 20;
    private static WebDriver mDriver;
    public final static WebElement NULL_WEB_ELEMENT = new RemoteWebElement();
    private static long timer = 0;
    private static final String NULL_STRING = new String();

    public static WebElement findElement(By by) {
        return findElement(by, 10);
    }

    public static WebElement findElement(By by, long waitInSeconds) {
        FluentWait<WebDriver> webDriverFluentWait = new WebDriverWait(mDriver, waitInSeconds).pollingEvery(1, TimeUnit.SECONDS).withMessage("Could not find Element");
        return webDriverFluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement findElement(String id) {
        return findElement(By.id(id));
    }

    public static List<WebElement> findElements(By by) {
        return getList(by);
    }

    public static List<WebElement> findElements(By mainElementBy, By subElementsBy) {
        WebElement main = findElement(mainElementBy);
        return main.findElements(subElementsBy);
    }

    public static WebElement findElement(By mainElementBy, By subElementsBy) {
        WebElement main = findElement(mainElementBy);
        return main.findElement(subElementsBy);
    }

    //Web Element action methods

    public static void click(By by) {
        WebElement element = findElement(by);
        if (element.isDisplayed()) {
            LOGGER.info("element is not null");
            element.click();
        } else {
            LOGGER.info("Element with By: " + by + " wasn't clicked!");
        }
    }

    public static void click(String id) {
        click(By.id(id));
    }

    public static void click(WebElement webElement) {
        if (webElement != null) webElement.click();
    }

    public static void clear(By by) {
        WebElement element = findElement(by);
        if (element != NULL_WEB_ELEMENT) element.clear();
    }

    public static void clear(String id) {
        clear(By.id(id));
    }

    public static void clear(WebElement element) {
        if (element != null) element.clear();
    }

    public static void sendKeys(By by, String value) {
        WebElement element = findElement(by);
        element.sendKeys(value);
    }

    public static void sendKeys(String id, String value) {
        WebElement element = findElement(By.id(id));
        element.sendKeys(value);
    }

    public static void sendKeys(WebElement element, String value) {
        if (element.isDisplayed()) {
            element.sendKeys(value);
        }
    }

    public static String getElementText(By by) {
        WebElement element = findElement(by);
        if (element != NULL_WEB_ELEMENT) return element.getText();
        return NULL_STRING;
    }

    public static String getElementText(String id) {
        return getElementText(By.id(id));
    }

    public static String getAttribute(String id, String attribute) {
        return getAttribute(By.id(id), attribute);
    }

    public static String getAttribute(By by, String attribute) {
        WebElement element = findElement(by);
        return element.equals(NULL_WEB_ELEMENT) ? NULL_STRING : element.getAttribute(attribute);
    }

    public static String getAttribute(WebElement element, String attribute) {
        if (element == null) {
            return NULL_STRING;
        }
        LOGGER.info("WebElement [" + attribute + "] had value [" + element.getAttribute(attribute) + "]");
        return element.getAttribute(attribute) == (null) ? NULL_STRING : element.getAttribute(attribute);

    }

    public static boolean verifyAttributeValue(By by, String attribute, String expected) {
        return getAttribute(by, attribute).equals(expected);
    }

    public static boolean verifyAttributeValue(String id, String attribute, String expected) {
        return getAttribute(id, attribute).equals(expected);
    }

    public static boolean verifyAttributeValue(WebElement webElement, String attribute, String expected) {
        return getAttribute(webElement, attribute).equals(expected);
    }

    public static String getCssValue(By by, String cssValue) {
        WebElement element = findElement(by);
        return element.equals(NULL_WEB_ELEMENT) ? NULL_STRING : element.getCssValue(cssValue);
    }

    public static String getCssValue(String id, String cssValue) {
        return getCssValue(By.id(id), cssValue);
    }

    public static String getTagName(By by) {
        WebElement element = findElement(by);
        return element.equals(NULL_WEB_ELEMENT) ? NULL_STRING : element.getTagName();
    }

    public static String getTagName(String id) {
        return getTagName(By.id(id));
    }

    public static boolean verifyElementText(By by, String expected) {
        return getElementText(by).equals(expected);
    }

    public static boolean verifyElementText(String id, String expected) {
        return verifyElementText(By.id(id), expected);
    }

    public static List<WebElement> getDropDownOptions(WebElement webElement) {
        Select select = new Select(webElement);
        return select.getOptions();
    }

    public static WebElement getDropDownOption(By by, String value) {
        List<WebElement> options = getDropDownOptions(findElement(by));
        for (WebElement element : options) {
            if (element.getAttribute("value").equalsIgnoreCase(value)) {
                return element;
            }
        }
        return NULL_WEB_ELEMENT;
    }

    public static WebElement getDropDownOption(WebElement webElement, String value) {
        List<WebElement> options = getDropDownOptions(webElement);
        for (WebElement element : options) {
            if (element.getAttribute("value").equalsIgnoreCase(value)) {
                return element;
            }
        }
        return NULL_WEB_ELEMENT;
    }


    public static void clickDropDownOption(String id, String value) {
        WebElement element = getDropDownOption(By.id(id), value);
        if (element != NULL_WEB_ELEMENT) {
            element.click();
        } else {
            LOGGER.error("Element: " + id + " not clicked");
        }
    }

    public static void clickDropDownOption(By by, String value) {
        WebElement element = getDropDownOption(by, value);
        if (element != NULL_WEB_ELEMENT) {
            element.click();
        } else {
            LOGGER.error("Element: " + by + " not clicked");
        }
    }

    public static void clickDropDownOption(WebElement webelement, String value) {
        WebElement element = getDropDownOption(webelement, value);
        if (element != NULL_WEB_ELEMENT) {
            element.click();
        } else {
            LOGGER.error("Element: " + webelement.getTagName() + " not clicked");
        }
    }

    public static List<WebElement> getDropDownOptions(By by) {
        return getDropDownOptions(findElement(by));
    }

    public static List<WebElement> getDropDownOptions(String id) {
        return getDropDownOptions(findElement(id));
    }

    public static boolean isDisplayed(By by) {
        return findElement(by).isDisplayed();
    }

//    public static void assertDisplayed(WebElement webElement, String description) {
//        boolean usernameDisplayed = Poller.start().pollUntilConditionIsSatisfied(new ElementIsDisplayed(webElement));
//        assertThat(description + " is not displayed", usernameDisplayed, is(true));
//    }
//
//    public static void assertDisplayed(By by, String description) {
//        boolean usernameDisplayed = Poller.start().pollUntilConditionIsSatisfied(new ElementIsDisplayed(by));
//        assertThat(description + " is not displayed", usernameDisplayed, is(true));
//    }

    public static boolean isDisplayed(String id) {
        return isDisplayed(By.id(id));
    }

    public static boolean isDisplayed(WebElement webelement) {
        return webelement.isDisplayed();
    }

    public static boolean isSelected(By by) {
        return findElement(by).isSelected();
    }

    public static boolean isSelected(String id) {
        return isSelected(By.id(id));
    }

    public static Boolean elementExists(By by) {
        return findElement(by) != NULL_WEB_ELEMENT;
    }

    public static Boolean elementExists(String pId) {
        return elementExists(By.id(pId));
    }

    public static Boolean elementExists(WebElement webElement) {
        try
        {
            return webElement.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            LOGGER.info("No element found ");
        }
        return false;
    }

    //Get, use and reload Driver

    public static WebDriver getCurrentDriver() {
        try {
            if (driverNotSet()) {
                LOGGER.info("mDriver is null - get new webdriver");
                mDriver = BrowserFactory.getBrowser();
             //   Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
        } catch (UnreachableBrowserException e) {
            LOGGER.error("get new webdriver, UnreachableBrowserException thrown");
            mDriver = BrowserFactory.getBrowser();
        } catch (WebDriverException e) {
            LOGGER.error("get new webdriver, WebDriverException thrown");
            mDriver = BrowserFactory.getBrowser();
        }
        return mDriver;
    }

    public static boolean driverNotSet() {
        if( mDriver == null )
            return true;
      //  else if(mDriver.getWindowHandle() == null)
//
        return false;

    }

    private static boolean driverSet() {
        return !driverNotSet();
    }

    private static void reloadBrowser() {
        boolean retry = true;
        int attempts = 3;
        while (retry || attempts != 0) {
            try {
                getCurrentDriver();
                retry = false;
                attempts = 0;
            } catch (UnreachableBrowserException e) {
                attempts--;
            }
        }
    }

    /**
     * Selenium alternative to existing {@link #isDisplayedCheck(org.openqa.selenium.WebElement)}
     *
     * @param elementToWaitFor WebElement
     * @return WebElement Element that was found
     */
    public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
        WebElement element = null;

        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = DEFAULT_WAIT_SECONDS;
        }

        try {
            element = (new WebDriverWait(getCurrentDriver(), waitTimeInSeconds)).until(
                    ExpectedConditions.visibilityOf(elementToWaitFor));
        } catch (NoSuchElementException ex) {
            LOGGER.warn("NoSuchElementException: Could not find element after waiting for " + waitTimeInSeconds + " secs");
        } catch (TimeoutException tex) {
            LOGGER.warn("TimeoutException: Could not find element after waiting for " + waitTimeInSeconds + " secs");
        }

        return element;
    }

    public static WebElement waitForElement(WebElement elementToWaitFor) {
        return waitForElement(elementToWaitFor, null);
    }

    public static WebElement waitForElement(String elementToWaitForByCSS) {
        return waitForElement(elementToWaitForByCSS, null);
    }

//    public static void waitForTransitionsToComplete() {
//        waitForTransitionsToComplete(10000);
//    }

//    public static void waitForTransitionsToComplete(int waitTimeMilliseconds) {
//        com.venkyold.org.adv.advance.ProcessTimer processTimer = ProcessTimer.start();
//        Condition elementIsDisplayed = new ElementIsDisplayed(By.id("ve_done"));
//        Poller.start().withTimeout(waitTimeMilliseconds).pollingEvery(200).pollUntilConditionIsSatisfied(elementIsDisplayed);
//        LOGGER.info("Transitions completed in " + processTimer.executionTime());
//    }

    /**
     * Selenium alternative to existing {@link #isDisplayedCheck(org.openqa.selenium.WebElement)}
     * Method takes CSS path in as parameter
     *
     * @param elementToWaitForByCSS WebElement
     * @return WebElement Element that was found
     */
    public static WebElement waitForElement(String elementToWaitForByCSS, Integer waitTimeInSeconds) {
        WebElement element = null;

        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = DEFAULT_WAIT_SECONDS;
        }

        try {
            element = (new WebDriverWait(getCurrentDriver(), waitTimeInSeconds)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementToWaitForByCSS)));
        } catch (NoSuchElementException ex) {
            LOGGER.debug("Could not find element via CSS path [" + elementToWaitForByCSS + " " + "after waiting for " + waitTimeInSeconds);
        }

        return element;
    }

    public static void waitFor(long millis) {
        pauseSearchFor(millis);
    }

    public static void press(Keys key) {
        new Actions(getCurrentDriver()).keyDown(key).keyUp(key).perform();
    }

    public static String getPageHTML() {
        return getCurrentDriver().getPageSource();
    }


    public static void selectFrame(String pId) {
        getCurrentDriver().switchTo().frame(pId);
    }

    public static void close() {
        try {
            if (driverSet()) {
                getCurrentDriver().quit();
                mDriver = null;
                LOGGER.info("~~~~~~~~~  browser closed ~~~~~~~~~");
            } else {
                LOGGER.info("~~~~~~~~~  browser was not open ~~~~~~~~~");
            }
        } catch (UnreachableBrowserException e) {

            LOGGER.info("CANNOT QUIT BROWSER. UnreachableBrowserException thrown");
        }
    }


    public static void deleteAllCookies() {
        LOGGER.info("deleteAllCookies()");
        deleteAllCookies(null);
    }

    public static void deleteAllCookies(String url) {
        LOGGER.info("deleteAllCookies(url)");

        if (url != null) {
            LOGGER.info("URL to delete cookies: " + url);
            loadPage(url);
        }

        Set<Cookie> allCookies = getCurrentDriver().manage().getCookies();
        if (allCookies != null) {
            for (Cookie cookie : allCookies) {
                LOGGER.info("Cookie name before : " + cookie.getName() + " Cookie value: " + cookie.getValue());
            }
            getCurrentDriver().manage().deleteAllCookies();

            allCookies = getCurrentDriver().manage().getCookies();
            for (Cookie cookie : allCookies) {
                LOGGER.info("Cookie name after : " + cookie.getName() + " Cookie value: " + cookie.getValue());
            }
        } else {
            LOGGER.info("~~~~~~~~ NO COOKIES ~~~~~~~~~~");
        }
    }

    public static void setCookie(String domainUrl, Cookie... cookie) {
        loadPage(domainUrl);
        for (Cookie cookieToAdd : cookie) {
            getCurrentDriver().manage().addCookie(cookieToAdd);
        }
    }

    public static void setCookie(Cookie cookie, String domainUrl) {
        setCookie(domainUrl, cookie);
    }

    public static void loadPage(String url) {
        getCurrentDriver();
        LOGGER.info("loadPage in: " + "CoreDriver");
        LOGGER.info("url is: " + url);
        try {
            LOGGER.info("try to loadPage [" + url + "]");
            getCurrentDriver().get(url);
        } catch (UnreachableBrowserException e) {
            getCurrentDriver();
            try {
                getCurrentDriver().get(url);
            } catch (UnreachableBrowserException second) {
                reopenAndLoadPage(url);
            }
        }

    }

    public static void reopenAndLoadPage(String url) {
        try {
            mDriver = null;
            getCurrentDriver();
            loadPage(url);
        } catch (WebDriverException we) {
            if (we.toString().contains("Session ID may not be null")) {
                LOGGER.info("reopening failed. Session ID was null");
                throw new WebDriverException(we);
            }
        }
    }

    public static WebElement getDivByText(String divText) {
        String xPath = "//div[text()='" + divText + "']";
        return findElement(By.xpath(xPath));
    }
    public static WebElement getDivByTitle(String divText) {
        String xPath = "//div[@title='" + divText + "']";
        return findElement(By.xpath(xPath));
    }
    public static WebElement getDivByTitleAndText(String divText) {
        String xPath = "//div[@title='" + divText + "']//div[text()='"+divText+"']";
        return findElement(By.xpath(xPath));
    }

    public static WebElement getTableCellByText(String divText) {
        String xPath = "//td[text()='" + divText + "']";
        return findElement(By.xpath(xPath));
    }
    public static List<WebElement> getDivByTextElements(String divText) {
        String xPath = "//div[text()='" + divText + "']";
        return findElements(By.xpath(xPath));
    }
    public static WebElement getChildDivByText(By byParent,String divText) {

        String xPath = "//div[text()='" + divText + "']";
        return findElement(byParent).findElement(By.xpath(xPath));
    }

    public static WebElement getSpanByText(String spanText) {
        String xPath = "//span[text()='" + spanText + "']";
        return findElement(By.xpath(xPath));
    }



    private static void pauseSearchFor(long millis) {
        try {
            timer = timer + millis;
            LOGGER.info("Pausing JZDriver for " + millis + " ms");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<WebElement> getDivElementsByTitleAndText(String divText) {
        String xPath = "//div[@title='" + divText + "']//div[text()='"+divText+"']";
        return findElements(By.xpath(xPath));
    }

    private static List<WebElement> getList(By by){
        FluentWait<WebDriver> webDriverFluentWait = new WebDriverWait(mDriver, 10).pollingEvery(1, TimeUnit.SECONDS).withMessage("Could not find Element");
        return webDriverFluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * Method to invoke a blur action on an element identified by ID.  This should be deleted
     * when the triggerBlur by WebElement is complete
     *
     * @param elementId
     */
    public static void triggerBlurById(String elementId) {
        String jsString = "$('#" + elementId + "').blur();";
        VoyantaDriver.executeJS(jsString);
    }


    /**
     * Method to invoke a blur action on an element.  You can only use this method
     * if the WebElement you pass it has an ID for JQuery to use.
     *
     * @param element
     */
    public static void triggerBlurOnWebElementUsingId(WebElement element) {
        if (element.getAttribute("id") != null) {
            triggerBlurById(element.getAttribute("id"));
        } else {
            LOGGER.warn("This WebElement does not have an ID so you can't use this method");
        }

    }

    public static String getXPath(WebElement element) {
        String jscript = "function getPathTo(node){" +
                " var stack = [];" +
                " while(node.parentNode !== null) {" +
                "  stack.unshift(node.tagName + '[id=' + node.id + ',class=' + node.className + ']');" +
                "  node = node.parentNode;" +
                " }" +
                " return stack.join('/');" +
                "}" +
                "return getPathTo(arguments[0]);";
        return (String) ((JavascriptExecutor) mDriver).executeScript(jscript, element);
    }

    /**
     * For some reason, getting children using By.CssSelector is very slow with WebElement... this is so much faster
     */
    public static List<WebElement> getChildren(WebElement element) {
        List<Object> children = (List<Object>) ((JavascriptExecutor) mDriver).executeScript("return arguments[0].childNodes", element);
        List<WebElement> elements = new ArrayList<WebElement>();
        for (Object child : children) {
            if (child instanceof WebElement) {
                elements.add((WebElement) child);
            }
        }
        return elements;
    }

    public static WebElement getParent(WebElement element) {
        return element.findElement(By.xpath(".."));
    }

    /**
     * Get a node's previous node (equivalent to DOM's "previousSibling" attribute)
     */
    public static WebElement getPreviousSibling(WebElement element) {
        Object response = ((JavascriptExecutor) mDriver).executeScript("return arguments[0].previousSibling", element);
        if (response instanceof WebElement) {
            return (WebElement) response;
        } else {
            return null;
        }
    }

    public static void executeJS(String jsCode) {
        JavascriptExecutor js = (JavascriptExecutor) mDriver;
        js.executeScript(jsCode);
    }



    /**
     * Method used to establish if a particular element is currently displayed.  This
     * method makes use of {@link #waitForElement(org.openqa.selenium.WebElement)} to determine if the
     * element existed before determining if its actually displayed
     *
     * @param element WebElement
     * @return boolean
     */
    public static boolean isDisplayedCheck(WebElement element) {
        if (element != null) {
            WebElement pageDiv = VoyantaDriver.waitForElement(element);
            if (pageDiv == null) {
                return false;
            }
            return pageDiv.isDisplayed();
        } else {
            LOGGER.info("Unable to determine if element is displayed as element was null");
            return false;
        }
    }

    public static boolean isNotDisplayedCheck(WebElement element) {
        int numberOfIterations = TWENTY_SECONDS * 5;
        //if element is deleted return true (i.e catch noSuchElementException)
        try {
            for (int i = 0; i < numberOfIterations; i++) {
                if (element.isDisplayed()) {
                    waitFor(200);
                } else {
                    return true;
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return true;
        } catch (StaleElementReferenceException e) {
            return true;
        }
    }

    public static void clickDropDownOptionByText(WebElement webelement, String text) {
        try {
            (new Select(webelement)).selectByVisibleText(text);
        } catch (NoSuchElementException nse) {
            LOGGER.error("Given option " + text + " not exists");
            throw new NoSuchElementException(text);
        }

    }

    public static String getSelectedValueInDropdown(WebElement webElement) {
        String selectedOption = null;
        try {
            selectedOption = (new Select(webElement)).getFirstSelectedOption().getText();
        } catch (NoSuchElementException nse) {
            LOGGER.error("Cant find selected option");
        }

        return selectedOption;
    }

    public static Boolean elementExistsByClass(WebElement webElement) {
        try {

            return elementExists(By.className(webElement.getAttribute("class")));

        } catch (NoSuchElementException nse) {
            LOGGER.info("Element not found");
        }
        return false;
    }

    public static void hoverOver(WebElement webElement) {
        Actions actions = new Actions(getCurrentDriver());
        actions.moveToElement(webElement).build().perform();
        //    actions.moveByOffset(1, 1).build().perform();

    }

    public static void setWebdriverImplicitWait(int timeInSeconds) {
        getCurrentDriver().manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }

    public static void switchToMobileDriver() {
        close();
        mDriver = BrowserFactory.getMobileDriver();
    }

    public static void switchToWebDriver() {
        close();
        mDriver = BrowserFactory.getBrowser();
    }

    public static void select(WebElement drpField, String objectType) {
        Select select = new Select(drpField);
        select.selectByVisibleText(objectType);
    }

    public static void radioButtonSelect(WebElement radioproviderOption, String providerOption) {

        VoyantaDriver.findElement(By.cssSelector("label:contains('"+providerOption+"')")).click();
//        for(WebElement element:radioproviderOption.findElements(By.tagName("input")))
//        {
//            if(element.getText().equalsIgnoreCase(providerOption))
//            {
//                element.click();
//            }
//        }
    }

    public static void selectByList(WebElement drpField,WebElement search, String objectType) {

        try {
            drpField.click();
            VUtils.waitFor(1);
            //        search.click();
            //        search.sendKeys(objectType);
            //        VUtils.waitFor(1);
            VoyantaDriver.findElement(By.xpath("//li[contains(text(),'" + objectType + "')]")).click();
            //        search.sendKeys(Keys.ENTER);
            VUtils.waitFor(1);
        }
        catch (TimeoutException e)
        {
            throw new RuntimeException("The dropdown field doesnt match the existing list:"+objectType);
        }
    }

    public static void moveElement(WebElement firstElement, WebElement secondElement) {
        (new Actions(VoyantaDriver.getCurrentDriver())).dragAndDrop(firstElement, secondElement).perform();
    }

    public static void scrollfindElement(By by, By by1) {
        do
        {
            try
            {
                if(elementExists(by)) {
                    VoyantaDriver.findElement(by).click();
                    break;
                }
            }catch (TimeoutException e)
            {
                LOGGER.info("Scrooling down....");
            }
            VoyantaDriver.mouseOver(by1);
           // VUtils.waitFor(2);
        }while (elementExists(by1));

    }

    public static void mouseOver(By by1) {
        Actions action = new Actions(VoyantaDriver.getCurrentDriver());
        action.moveToElement(VoyantaDriver.findElement(by1)).build().perform();
    }

    public static boolean isTextPresent(String text) {
        return VoyantaDriver.getCurrentDriver().findElement(By.tagName("body")).getText().contains(text);
    }

    public static void selectElementByText(String text) {
        List<WebElement> elements =  VoyantaDriver.getCurrentDriver().findElements(By.xpath("//div[text()='"+text+"']"));
        if(elements.size()==0)
            throw new RuntimeException("The text given to select is not visible on page :"+text);
        VoyantaDriver.getCurrentDriver().findElements(By.xpath("//div[text()='"+text+"']")).get(0).click();
    }

    public static WebElement getLinkByText(String text) {
        return VoyantaDriver.findElement(By.xpath("//div[contains(text(),'"+text+"')]"));
//        return VoyantaDriver.findElement(By.cssSelector("a:contains('"+text+"')"));
    }

    public static void refresh() {
        VoyantaDriver.getCurrentDriver().navigate().refresh();
    }

    private static class BrowserCleanup implements Runnable {

        public void run() {
            if (driverSet()) {
                LOGGER.info("Closing remaining browser");
                close();
            }
        }
    }

    public static void getTimeWaited() {

        LOGGER.info("Total time waited " + timer);

    }

    public static void quit() {
        mDriver.close();
        mDriver.quit();
        mDriver=null;
    }

}
