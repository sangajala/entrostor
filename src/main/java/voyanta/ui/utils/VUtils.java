package voyanta.ui.utils;

import com.google.common.base.Stopwatch;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.Augmenter;
import voyanta.ui.datamodel.ProductProductIdComparator;
import voyanta.ui.datamodel.VHashMap;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by sriramangajala on 11/07/2014.
 */
public class VUtils {
    static Logger LOGGER = Logger.getLogger(VUtils.class);
    public static void waitFor(int i) {
        try {
            Thread.sleep(i*1000);

        }
        catch (InterruptedException e)
        {

        }
    }

    public static void waitForElement(WebElement defaultElement) {
        for(int i=0;i<=15;i++) {
        try {

                if (defaultElement.isDisplayed())
                    return;
                waitFor(1);
            LOGGER.info("Waiting for 1 sec ....");

        }catch (NoSuchElementException e)
        {
            LOGGER.info("Waiting for 1 sec ....");
            waitFor(1);
        }
        }
    }

    public static void WaitForAllAjaxCalls(WebDriver driver, int timeout)
    {
        timeout=40;
        Stopwatch sw = new Stopwatch();
        sw.start();
        while (true)
        {
            if (sw.elapsed(TimeUnit.SECONDS) > timeout) try {
                throw new Exception("Timeout");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object o=((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
            if (o==null)
                break;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void captureScreen(Scenario scenario) {
       // try {
            WebDriver augmentedDriver = new Augmenter().augment(VoyantaDriver.getCurrentDriver());
            byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
////            File fileName,source;
////            byte[] source;
////            source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BASE64);
////            InputStream screenshotStream = new FileInputStream(source);
//////            byte[] source = VoyantaDriver.getCurrentDriver().getScreenshotAs(OutputType.FILE);
////            scenario.embed(screenshotStream,"image/png");
//            fileName = new File("./target/screenshots/"  + scenario.getName() + ".png");
//	        if(fileName.exists())
//	        {
//	        	fileName.delete();
//	        }
//            FileUtils.copyFile(source, fileName);
//        } catch (IOException e) {
//            throw  new  RuntimeException("Failed to capture screenshot: " + e.getMessage());
//        }
    }

    public static File getDownloadedFile(final String fileType)
    {
        File dir = new File(System.getProperty("user.home"));

//        File[] files = dir.listFiles();
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(fileType);
            }
        });

        if (files.length == 0) {
            return null;
        }
        else
        {
            LOGGER.info("Found file with count "+files.length);
        }

        File lastModifiedfile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedfile.lastModified() < files[i].lastModified()) {
                lastModifiedfile = files[i];
            }
        }
        LOGGER.info("this is the file found " + lastModifiedfile.getName() );
        return lastModifiedfile;
    }

    public static boolean isElementPresent(By by) {
        return elementExists(by);
    }

    public static boolean elementExists(By xpath) {
        try {
            return VoyantaDriver.getCurrentDriver().findElement(xpath).isDisplayed();
        }
        catch (NoSuchElementException e){
            LOGGER.info("Element not found :"+xpath);
        }
        return false;
    }

    public static boolean isElementWithTextPresentByDiv(String text) {
        String xPath = "//div[text()='" + text + "']";
        return isElementPresent(By.xpath(xPath));
    }

    public static boolean isElementWithTextPresentBy(String text) {
        String xPath = "//*[text()='" + text + "']";
        return isElementPresent(By.xpath(xPath));
    }

    public static boolean isElementWithTextPresentBySpan(String text) {
        String xPath = "//span[text()='" + text + "']";
        return isElementPresent(By.xpath(xPath));
    }

    public static int[] getRowList(String rows){
        String[] list=rows.split(",");
        int[] rowList=new int[list.length];
        for(int i=0;i<list.length;i++){
            rowList[i]=Integer.valueOf(list[i]);
        }
        return rowList;
    }

    public static Map<String, String> getSortedHashMap(DataTable dataTable) {
        Map<String,String> map = new LinkedHashMap<String, String>();


        for(List<String> drIn:dataTable.asLists(String.class))
        {

            System.out.print(drIn);
            map.put(drIn.get(0),drIn.get(1));

        }

        return map;
    }

    public static void waitForAttribute(WebElement element, String attribute, String value) {

        for(int i=0;i<=(Integer.parseInt(PropertiesLoader.getProperty("MAX_TIME_OUT")));i++) {
            try {

                if (element.getAttribute(attribute).contains(value))
                    return;
                waitFor(1);
                LOGGER.info("Waiting for 1 sec .... for an attribute "+attribute+" with value "+value+" to be presnet");

            }catch (NoSuchElementException e)
            {
                LOGGER.info("Waiting for 1 sec ....to element to be visible");
                waitFor(1);
            }
        }

    }

    public static boolean isElementPresentWithLocator(String locator, String value) {
        if(locator.equals("css"))
            return isElementPresent(By.cssSelector(value));
        return false;
    }

    public static WebElement getElement(String locator, String value) {
        if(locator.equals("css"))
            return VoyantaDriver.findElement(By.cssSelector(value));
        return null;
    }

    public static  List<VHashMap> sortData(List<VHashMap> extractedMap1, String[] keys) {
        for(String key:keys )
        {
            if(!key.equals(""))
            {
                key = key.toLowerCase();
                if(!extractedMap1.get(0).containsKey(key))
                    throw new RuntimeException("Given Key not found "+key);

                Collections.sort((List<HashMap>) (Object) extractedMap1, new ProductProductIdComparator(key));
            }


        }
        return (List<VHashMap>)(Object)extractedMap1;
//            else
//                throw new RuntimeException("The key provided :"+key+" is not a valid key in the given xml");
    }
    public static  List<VHashMap> sortDataByFirstColumn(List<VHashMap> expData) {
          List<HashMap> data = (List<HashMap>)(Object)expData;
            return sortData(expData,getKeys(data.get(0)));
    }

    public static String[] getKeys(HashMap map)
    {


        String[] keys = new String[map.size()];


        Map<Integer, Object> myHashMap = map;

//        Iterator<Integer> it = myHashMap.keySet().iterator();
//        while (it.hasNext())
//        {
//            int next = it.next();
//
//        }
//        Iterator<Map.Entry<String,Integer>> itr=  map.entrySet().iterator();
//        //please check
//        while(itr.hasNext())
//        {
//            System.out.println("key of : "+itr.next().getKey()+" value of      Map"+itr.next().getValue());
//        }

        int index=0;
        for(Object s: map.keySet()) {
           keys[index] = s.toString();
           // index++;
        }
        return keys;

    }

    public static void copyFile(File file, String fileName, String windows_boxFolder) {

            File destinationPathObject = new File(windows_boxFolder);
            File sourceFilePathObject = file;
        try {
            if ((destinationPathObject.isDirectory()) && (sourceFilePathObject.isFile()))
            //both source and destination paths are available
            {
                //creating object for File class
                File statusFileNameObject = new File(windows_boxFolder + "/" + fileName);
                if (statusFileNameObject.isFile())
                //Already file is exists in Destination path
                {
                    //deleted File
                    statusFileNameObject.delete();}
                    //paste file from source to Destination path with fileName as value of fileName argument
                    FileUtils.copyFile(sourceFilePathObject, statusFileNameObject);
            }
        }catch (IOException e)
        {
            e.getMessage();
        }
    }

	public static boolean isTextSelected(String value) {
		for(WebElement element:VoyantaDriver.getCurrentDriver().findElements(By.className("QvSelected")))
		{
			if(element.getText().contains(value)){
				return true;
			}
		}
			// TODO Auto-generated method stub
		return false;
    }

    public static void accept_alert()
    {
        VoyantaDriver.getCurrentDriver().switchTo().alert().accept();
    }

    public static boolean isListContains(List<WebElement> proposals, String proposalName) {
        for(WebElement proposal:proposals)
        {

            if(proposal.getText().contains(proposalName))
                return true;
        }
        return false;
    }
}
