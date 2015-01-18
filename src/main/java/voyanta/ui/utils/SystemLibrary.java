package voyanta.ui.utils;

//import org.openqa.selenium.*;
//import org.openqa.selenium.remote.Augmenter;
//import org.openqa.selenium.support.ui.Select;


public class SystemLibrary {

//    Selenium sel;
//    public static WebDriver driver = abstractTest.driver;
//    private static Random random;
//
//    public void timeOut() {
////		try	{
////
////			sel.waitForPageToLoad(baseTest.TimeOut);
////		}
////	catch(SeleniumException e)
////		{
////			try{
////				sel.waitForPageToLoad(baseTest.TimeOut);
////				}
////					catch(SeleniumException e1)
////						{
////
////						}
//
//
////		}
//
//    }
//
//    public void waitUntil(int time) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public void ignoreAlert() {
//        try {
//            sel.isAlertPresent();
//        } catch (Exception e) {
//
//        }
//    }
//
//    public boolean isElementPresent(By by) {
//        try {
//            return driver.findElement(by).isDisplayed();
//        } catch (NoSuchElementException e) {
//
//        }
//        return false;
//    }
//
//    public void timeout(int t) {
//
//        driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
//    }
//
//
//    public static void captureScreen(String message) {
//
//        String path, browser;
//        browser = abstractTest.browser;
//
//
//        try {
//            WebDriver augmentedDriver = new Augmenter().augment(abstractTest.getDriver());
//            File fileName;
//            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
//            fileName = new File("./target/screenshots/" + browser + message + ".png");
////	        if(fileName.exists())
////	        {
////	        	fileName.delete();
////	        }
//            FileUtils.copyFile(source, fileName);
//        } catch (IOException e) {
//            path = "Failed to capture screenshot: " + e.getMessage();
//        }
//
//
//    }
//
//    public static void captureScreen() {
//
//        String path, browser;
//        browser = abstractTest.browser;
//
//
//        try {
//            WebDriver augmentedDriver = new Augmenter().augment(abstractTest.getDriver());
//            File fileName;
//            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
//            fileName = new File("./target/screenshots/" + browser + Thread.currentThread().getStackTrace()[2].getMethodName() + ".png");
//            FileUtils.copyFile(source, fileName);
//        } catch (IOException e) {
//            path = "Failed to capture screenshot: " + e.getMessage();
//        }
//
//
//    }
//
//    public static String findFolder(String folder) {
//        File actual = new File(".");
//        for (File f : findDirectories(actual)) {
//            File[] f1 = findDirectories(f);
//            for (File f2 : f1) {
//                if (f2.getName().equals(folder)) {
//                    return (f2.getAbsolutePath());
//
//                }
////	        		else
////	        		{
////
////	        			call();
////	        		}
//
//            }
//
//        }
//        return null;
//    }
//
//    ///not tested
//    public static String findFile(String file) {
//        File actual = new File(".");
//        for (File f : findFiles(actual)) {
//            File[] f1 = findFiles(f);
//            for (File f2 : f1) {
//                if (f2.getName().equals(file)) {
//                    return (f2.getAbsolutePath());
//
//                }
////	        		else
////	        		{
////
////	        			call();
////	        		}
//
//            }
//            System.out.println(f.getName());
//        }
//        return null;
//    }
//
//    public static File[] findDirectories(File root) {
//        return root.listFiles(new FileFilter() {
//            public boolean accept(File f) {
//                return f.isDirectory();
//            }
//        });
//    }
//
//    public static File[] findFiles(File root) {
//        return root.listFiles(new FileFilter() {
//            public boolean accept(File f) {
//                return f.isFile();
//            }
//        });
//    }
//
//    public void select(String id, String value) {
//
//        (new Select(driver.findElement(By.id(id)))).selectByVisibleText(value);
//
//    }
//
//    public boolean isTextPresent(String string) {
//        return driver.findElement(By.xpath("//html")).getText().contains(string);
//    }
//
//    public static String getRandomString() {
//        random = new Random();
//        return String.valueOf(random.nextInt());
//
//    }
//
//    public static void waitFor(int i) {
//        try {
//            Thread.sleep(i * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }
//
//
//    public static WebElement getElementFrmLabel(String configValue) {
//
////            if(!driver.getPageSource().contains(configValue))
////            {
////                Assert.fail();
////            }
//
//        List<WebElement> elements;
//        elements = driver.findElements(By.xpath("//*"));
//        WebElement beforeElement = null;
//        WebElement finalElement = null;
//
//        for (WebElement element : elements) {
//            //  System.out.print(element.getText());
//            if (element.getText().trim().equalsIgnoreCase(configValue)) {
//                System.out.print(element.getText());
//                System.out.print("Got the object");
//                if (beforeElement != null) {
//                    System.out.print(beforeElement.getText());
////                    beforeElement.click();
//                }
//                finalElement = beforeElement;
//            } else
//                beforeElement = element;
//        }
//
//
//        return finalElement;
//    }
//
//
//    public static boolean isElementPresentWithText(String configValue) {
//        List<WebElement> elements;
//        elements = driver.findElements(By.xpath("//*"));
//        WebElement beforeElement = null;
//        WebElement finalElement = null;
//
//        for (WebElement element : elements) {
//            if (element.getText().trim().equalsIgnoreCase(configValue)) {
//                return true;
//            }
//
//        }
//        return false;
//    }
//
//    public WebElement getElementWithText(String configValue) {
//        List<WebElement> elements;
//        elements = driver.findElements(By.xpath("//*"));
//        WebElement beforeElement = null;
//        WebElement finalElement = null;
//
//        for (WebElement element : elements) {
//            if (element.getText().trim().equalsIgnoreCase(configValue)) {
//                finalElement = element;
//            }
//
//        }
//        return finalElement;
//    }
//
//    private static List<WebElement> getAllElementsWithText() {
//
//        List<WebElement> newElements = null;
//        for (WebElement element : getAllElements()) {
//            if (!element.getText().equals(""))
//                newElements.add(element);
//        }
//        return newElements;
//    }

//    private static List<WebElement> getAllElements() {
//        List<WebElement> elements;
////        elements = driver.findElements(By.xpath("//*"));
//        return elements;
//    }
}

	
	
	
	
