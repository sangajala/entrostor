package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;
import voyanta.ui.utils.VoyantaDriver;

import java.util.*;

/**
 * Created by sriramangajala on 24/07/2014.
 */

public class abstractPageWithList extends abstractWebPage {
    static Logger LOGGER = Logger.getLogger(abstractPageWithList.class);
    int rows;
    By tableLocator;
    int rowCount;
    public WebElement tableElement;
    public HashMap firstLineHashMap;
    public String tableId="sharing-table";

    public List<HashMap> listViewElements;


    public List<HashMap> getListViewElements() {
        return listViewElements;
    }

    public int getRowCount() {

        return 0;
    }

    public abstractPageWithList withLocator(By by) {
        tableLocator = by;
        return this;
    }

    public int getColumnCount() {
        return 0;
    }

    public WebElement getRow(int i) {
        return null;
    }

    public WebElement getCell(int row, int column) {
        return null;
    }

    public void navigatetoNextPage() {

    }

    public void setRowCount(int count) {
        rowCount = count;
    }

    public String getCellAsString(int row, int column) {
        return null;
    }

    /**
     * @param text: the text user wants to search in
     * @return
     */
    public boolean checkTheRowPresentWithText(String text) {
        for (WebElement element : getRowsAsElements()) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }


    /**
     * @return all the rows within the table in the page as a list
     */
    public List<WebElement> getRowsAsElements() {
        return tableElement.findElements(By.tagName("tr"));

    }


    /**
     * @return get the whole table as HashMap
     */
    public List<HashMap> getRowsInHash() {

        List<HashMap> listOfElement = new LinkedList<HashMap>();

        int rowNr = 0;
        for (WebElement element : tableElement.findElements(By.xpath("//tbody/tr"))) {
            if (element.getAttribute("class").contains("expandedView")) {
                continue;
            } else {
                int i = 0;
                HashMap hashMap = new HashMap();
                for (WebElement element1 : element.findElements(By.tagName("td"))) {
                    String header = tableElement.findElements(By.xpath("//thead/tr/th")).get(i).getText().trim();
                    hashMap.put(header, element1);
                    //  System.out.println("this is the row" + rowNr+ " value "+ element1.getText() + " for header " + header);
                    LOGGER.info("Key:" + tableElement.findElements(By.xpath("//thead/tr/th")).get(i).getText().trim() + " Value:" + element1.getText());
                    i++;
                }
                LOGGER.info("---------------------------------------");
                listOfElement.add(hashMap);
                rowNr++;
            }
        }
        this.listViewElements = listOfElement;
        System.out.println(listViewElements.size());
        return listOfElement;
    }

    public List<HashMap> getRowsAsHashIgnoreBlankHeaders() {
        List<HashMap> listOfElement = new LinkedList<HashMap>();

        int rowNr = 0;
        for (WebElement element : tableElement.findElements(By.xpath("//tbody/tr"))) {
            if (element.getAttribute("class").contains("expandedView"))
            {
                continue;
            }
            else
            {
                int colNum = 0;

                    HashMap hashMap = new HashMap();
                    for (WebElement element1 : element.findElements(By.tagName("td"))) {

                        tableId = "sharing-table";
                        String xpath="//table[@id='"+tableId+"']/thead/tr/th";
                        VUtils.waitForElement(VoyantaDriver.getCurrentDriver().findElement(By.xpath(xpath)));
                        VUtils.waitFor(5);
                        String header = VoyantaDriver.getCurrentDriver().findElements(By.xpath(xpath)).get(colNum).getText().trim();

                        if (!header.trim().equals(""))
                        {
                            hashMap.put(header, element1);
                            //  System.out.println("this is the row" + rowNr+ " value "+ element1.getText() + " for header " + header);
                            LOGGER.info("Key:" + tableElement.findElements(By.xpath("//thead/tr/th")).get(colNum).getText().trim() + " Value:" + element1.getText());
                        }
                        colNum++;
                    }
                    LOGGER.info("---------------------------------------");
                    listOfElement.add(hashMap);


                rowNr++;
            }
        }
        this.listViewElements = listOfElement;
        System.out.println(listViewElements.size());
        return listOfElement;
    }

    /**
     * @param rowNumber： how many rows in the table you want to use for building the hash map list
     * @return: listViewElements
     */
    public List<HashMap> getRowsInHashBasedOnIndex(int rowNumber) {
        List<HashMap> listOfElement = new LinkedList<HashMap>();
        List<WebElement> rowList = tableElement.findElements(By.xpath("//tbody/tr"));
        List<String> headerList = getHeaderList();
        if (rowNumber == 0) {
            rowNumber = rowList.size();
        }

        for (int j = 0; j < rowNumber; j++) {
            WebElement element = rowList.get(j);
            if (element.getAttribute("class").contains("expandedView")) {
                continue;
            } else {
                int i = 0;
                HashMap hashMap = new HashMap();
                for (WebElement element1 : element.findElements(By.tagName("td"))) {

                    String header = headerList.get(i).trim();
                    hashMap.put(header, element1);
                    // LOGGER.info("this is the row " + j+ " value "+ element1.getText() + " for header " + header);
                    LOGGER.debug("Key:" + headerList.get(i).trim() + " Value:" + element1.getText());
                    i++;
                }
                LOGGER.debug("---------------------------------------");
                listOfElement.add(hashMap);
            }
        }
        this.listViewElements = listOfElement;
        System.out.println(listViewElements.size());

        return listViewElements;
    }

    private List<String> getHeaderList() {
        List<String> headerList = new ArrayList();

        for (WebElement element : tableElement.findElements(By.xpath("//thead/tr/th"))) {  // System.out.println(element.getAttribute("class"));

            if (element.getText().equals("")) {
                if (element.findElement(By.tagName("div")).getAttribute("class").equals("sentinel")) {
                    headerList.add("sentinel");
                } else if (element.findElement(By.tagName("div")).getAttribute("class").equals("updated")) {
                    headerList.add("updated");
                }
            } else headerList.add(element.getText());
        }
        return headerList;
    }


   /* public HashMap getFirstLineHashmap(){    
        int rowNr=0;
        firstLineHashMap =  new HashMap();
       WebElement firstElement=tableElement.findElements(By.xpath("//tbody/tr")).get(0);
          int i=0;
            for(WebElement element1:firstElement.findElements(By.tagName("td"))) {
                String header=tableElement.findElements(By.xpath("//thead/tr/th")).get(i).getText().trim();
                firstLineHashMap.put(header, element1);
                System.out.println(((WebElement) firstLineHashMap.get(header)).getText());
               System.out.println("this is the row" + rowNr+ " value "+ element1.getText() + " for header " + header);
                LOGGER.debug("Key:"+tableElement.findElements(By.xpath("//thead/tr/th")).get(i).getText().trim()+" Value:"+element1.getText());
             i++;
            }
            LOGGER.debug("---------------------------------------");
            ;
       return  firstLineHashMap;
    }*/
    public WebElement getElementInColumn(int row, String ColumnName) {
        int i = getRowNumber("Name", "CBR-Error-001");
        return getCellElementWithRow(i, "Actions");
    }

    /**
     * @param column:       the column name where user already know the value
     * @param text:         the known value of the column which user uses as reference to find the value of another column
     * @param returnColumn: the column name whose value user wants to find
     * @return: the WebElement of the column user wants to find
     */
    public WebElement getRowElementFromText(String column, String text, String returnColumn) {
        int i = getRowNumber(column, text);
        return getCellElementWithRow(i, returnColumn);
    }


    /**
     * @param i:      row number
     * @param header: header(column) name of the table
     * @return: the value for the column in row i
     */
    public WebElement getCellElementWithRow(int i, String header) {
        i = i - 1;
        try {
            if (listViewElements.get(i).
                    containsKey(header)) {
                return (WebElement) listViewElements.get(i).get(header);
            } else
                throw new RuntimeException("The given header is not valid: " + header);

        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Row number not valid :" + i);
        }
    }


    /**
     * @param column: Name of the column
     * @param value:  The value of the column
     * @return : the row number where the column has the value
     */
    public int getRowNumber(String column, String value) {

        listViewElements = getListViewElements();
        int i = 0;
        for (HashMap map : listViewElements) {
            if (((WebElement) map.get(column)).getText().equals(value)) {
                LOGGER.info("Found record with text :" + map.toString());
                return i;

            }
            i++;

        }
//        return i;
        VerifyUtils.fail("Given value :" + value + " not found in column :" + column);
        return i;
    }


}
