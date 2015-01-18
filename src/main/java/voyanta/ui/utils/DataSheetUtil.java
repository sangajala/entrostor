package voyanta.ui.utils;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import voyanta.ui.datamodel.VHashMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class DataSheetUtil {

    Workbook objExcelWorkBook;
    static Sheet objExcelInputSheet;
    public List<VHashMap> objHashMap = new LinkedList<VHashMap>();

    public int getStrInputSheetRows() {
        return strInputSheetRows;
    }

    private static int strInputSheetCols;
    private static int strInputSheetRows;
    private static int keyColumn = 3;
    private static int rowStart = 9;
    private static int columnCount = 10;
    static Logger LOGGER = Logger.getLogger(DataSheetUtil.class);
//    public static String strDataSheetLocation = "src/main/resources/datasheets/";

    public List<VHashMap> getTestDataFromExcel(String strDataSheetLocation,String strDataSheet, String strInputSheet) {
        try {
            FileInputStream fileInputStream = new FileInputStream(strDataSheetLocation + strDataSheet);
            return getvHashMaps(fileInputStream,strInputSheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<VHashMap> getvHashMaps(FileInputStream fileInputStream,String strInputSheet) {
        try {
            // Open the Excel datasheet at the relevant input sheet page
            objExcelWorkBook = WorkbookFactory.create(fileInputStream);
            LOGGER.info("Loading the data from Sheet :"+strInputSheet);
            if(strInputSheet.equals("0")||strInputSheet==null)
                objExcelInputSheet = objExcelWorkBook.getSheetAt(0);
            else
                objExcelInputSheet = objExcelWorkBook.getSheet(strInputSheet);

            // Check that the headings are on the first row to verify the format of the datasheet and if not, exit the test
//            if (isColHeadingsRowOne() == false) {
//                LOGGER.info("getTestDataFromExcel: Headings not correct on dataSheet");
//                Assert.fail("getTestDataFromExcel: Headings not correct on dataSheet");
//            }
            // Calculate the rows & cols (including headings) in the relevant input sheet
            strInputSheetCols = getDataSheetCols();
            LOGGER.info("Number of columns :" + strInputSheetCols);
            strInputSheetRows = getValidDataSheetRows();
            LOGGER.info("Number of rows :" + strInputSheetRows);
            // Get the test data from the datasheet
            getTestDataSet();
        } catch (Exception e) {
            Assert.fail("getTestDataFromExcel: Could not load data - " + e.getMessage());

        }
        return objHashMap;
    }
    public List<VHashMap> getFullHashMaps(FileInputStream fileInputStream,String strInputSheet) {
        try {
            // Open the Excel datasheet at the relevant input sheet page
            objExcelWorkBook = WorkbookFactory.create(fileInputStream);
            LOGGER.info("Loading the data from Sheet :"+strInputSheet);
            if(strInputSheet.equals("0")||strInputSheet==null)
                objExcelInputSheet = objExcelWorkBook.getSheetAt(0);
            else
                objExcelInputSheet = objExcelWorkBook.getSheet(strInputSheet);

            // Check that the headings are on the first row to verify the format of the datasheet and if not, exit the test
//            if (isColHeadingsRowOne() == false) {
//                LOGGER.info("getTestDataFromExcel: Headings not correct on dataSheet");
//                Assert.fail("getTestDataFromExcel: Headings not correct on dataSheet");
//            }
            // Calculate the rows & cols (including headings) in the relevant input sheet
            strInputSheetCols = getFullDataSheetCols();
            LOGGER.info("Number of columns :" + strInputSheetCols);
            strInputSheetRows = getFullValidDataSheetRows();
            LOGGER.info("Number of rows :" + strInputSheetRows);
            // Get the test data from the datasheet
            getFullTestDataSet();
        } catch (Exception e) {
            Assert.fail("getTestDataFromExcel: Could not load data - from " + e.getMessage());

        }
        return objHashMap;
    }

    protected void getTestDataSet() {
        // Re-Initialise the objTest string array so the table is the same size as the datasheet values
        // without the headings row included

        // Starting with the first test on Row 1, populate the test data set row by row
        for (int intRowCounter = 1; intRowCounter <= strInputSheetRows; intRowCounter++) {
            VHashMap hashMap= new VHashMap();
            for (int intColCounter = 1; intColCounter <= strInputSheetCols; intColCounter++) {
                int headerRow = keyColumn-1;
                int row = (rowStart-1)+(intRowCounter-1);
                int column = (intColCounter-1);
                LOGGER.debug("Getting data from cell : with row " + row + " and Column " + column);

                LOGGER.debug("key= " + getCellValueAsString(headerRow, column) + ": value= " + getCellValueAsString(row, column));
//                LOGGER.info("Value = "+getCellValueAsString(intRowCounter, intColCounter));
                hashMap.put(getCellValueAsString(headerRow,column).replace("(","").replace(")",""), getCellValueAsString(row, column).trim());
            }
            objHashMap.add(hashMap);
        }
    }

    protected void getFullTestDataSet() {
        // Re-Initialise the objTest string array so the table is the same size as the datasheet values
        // without the headings row included

        // Starting with the first test on Row 1, populate the test data set row by row
        for (int intRowCounter = 1; intRowCounter <= strInputSheetRows-1; intRowCounter++) {
            VHashMap hashMap= new VHashMap();
            for (int intColCounter = 1; intColCounter <= strInputSheetCols; intColCounter++) {
                int headerRow = 0;
                int row = (1)+(intRowCounter-1);
                int column = (intColCounter-1);
                LOGGER.debug("Getting data from cell : with row " + row + " and Column " + column);

                LOGGER.debug("key= " + getCellValueAsString(headerRow, column) + ": value= " + getCellValueAsString(row, column));
//                LOGGER.info("Value = "+getCellValueAsString(intRowCounter, intColCounter));
                hashMap.put(getCellValueAsString(headerRow,column).replace("(","").replace(")",""), getCellValueAsString(row, column).trim());
            }
            objHashMap.add(hashMap);
        }
    }

    @Deprecated
    protected static boolean isColHeadingsRowOne() {
        // Set return code to false as default
        boolean blnHeadingCheck = false;

        // Get cell value at location A1 which should be "TESTNAME"
        String strTestNameCheck = getCellValueAsString(0, 0);

        // Get cell value at location B1 which should be "BROWSER"
        String strBrowserNameCheck = getCellValueAsString(0, 1);

        // Now check that the values have been set correctly
        if ((strTestNameCheck.equalsIgnoreCase("TESTNAME") && (strBrowserNameCheck.equalsIgnoreCase("BROWSER")))) {
            blnHeadingCheck = true;
        }
        return blnHeadingCheck;
    }

    protected static String getCellValueAsString(int intRowRef, int intColRef) {
        // Get cell format type
        String strCellValue = null;
        int intCellFormat = getCellFormatType(intRowRef, intColRef);
        LOGGER.debug("Getting data from row:"+intRowRef+" column "+intColRef+" with the format "+intCellFormat);

        // Cast the cell value into a String
        switch (intCellFormat) {
            // STRING TYPE
            case Cell.CELL_TYPE_STRING:
                strCellValue = objExcelInputSheet.getRow(intRowRef).getCell(intColRef).getStringCellValue();
                break;

            // BOOLEAN TYPE
            case Cell.CELL_TYPE_BOOLEAN:
                boolean blnCellValue = objExcelInputSheet.getRow(intRowRef).getCell(intColRef).getBooleanCellValue();
                if (blnCellValue == true) {
                    strCellValue = "true";
                } else {
                    strCellValue = "false";
                }
                break;

            // INTEGER TYPE
            case Cell.CELL_TYPE_NUMERIC:
//                HSSFCell cell = ((HSSFSheet)objExcelInputSheet).getRow(intRowRef).getCell(intColRef);
//                HSSFCellStyle style = cell.getCellStyle();
//                                 if (HSSFDateUtil.isCellDateFormatted(cell))
//                                 {
//                                     strCellValue = cell.getDateCellValue().toString();
//                                 }
////                             else if(XlsDataSetWriter.DATE_FORMAT_AS_NUMBER_DBUNIT.equals(style.getDataFormatString()))
////                                 {
////                                     // The special dbunit date format
////                                     return getDateValueFromJavaNumber(cell);
////                                 }
//                             else
//                            {
                                String value = objExcelInputSheet.getRow(intRowRef).getCell(intColRef).toString();

                                LOGGER.debug("Data in row:"+intRowRef+" col:"+intColRef+" value:"+value);

                                if(value.contains("-")&&value.contains("."))
                                {
                                    strCellValue = value;
                                }
                                else if(value.contains(".")&&Double.parseDouble((value.replace(".","/").split("/")[1]))>0)
                                {


                                    strCellValue = value;//objExcelInputSheet.getRow(intRowRef).getCell(intColRef).toString();
//                               LOGGER.info("Date Found :"+strCellValue);
                                }
                                else if(value.contains("-"))
                                {
                                    //strCellValue = value;
                                    Date f = null;
                                    try {
                                        f = new SimpleDateFormat("dd-MMM-yyyy").parse(value);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    strCellValue =   (new  SimpleDateFormat(("yyyy-MM-dd"))).format(f);
                                }
                                else
                                {
                                     strCellValue = Integer.toString((int) (objExcelInputSheet.getRow(intRowRef).getCell(intColRef).getNumericCellValue()));
                                 }

                                break;

            // BLANK OR NULL TYPE
            case Cell.CELL_TYPE_BLANK:
                strCellValue = StringConstants.STRING_BLANK;
                break;
            case 9:
                strCellValue = StringConstants.STRING_BLANK;
                break ;
                

            // OTHER
            default:
                LOGGER.info("====== getCellValueAsString is a non recognised format: Type is " + intCellFormat);
        }
        return strCellValue;
    }

    protected static int getCellFormatType(int intRowRef, int intColRef) {
        //Initialise return value to be a non valid, null return code
        int intCellFormatType = 9;

        // Get cell type value. Be aware that if an Excel 2007+ cell is empty, an exception is thrown which we should
        // catch and ignore. Any method calling this must be able to handle a null return code of 9
        try {
            intCellFormatType = objExcelInputSheet.getRow(intRowRef).getCell(intColRef).getCellType();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());

        }
        return intCellFormatType;
    }

    protected static int getDataSheetCols() {
        // Count through the columns starting at A1 and return no of cols with actual values
        int intColCounter = 0;
        while (getCellValueAsString(keyColumn-1, intColCounter).equalsIgnoreCase(StringConstants.STRING_BLANK) == false) {
            intColCounter = intColCounter + 1;
        }

        return intColCounter;
    }

    protected static int getFullDataSheetCols() {
        // Count through the columns starting at A1 and return no of cols with actual values
        int intColCounter = 0;
        while (getCellValueAsString(0, intColCounter).equalsIgnoreCase(StringConstants.STRING_BLANK) == false) {
            intColCounter = intColCounter + 1;
        }

        return intColCounter;
    }

    protected static int getDataSheetRows() {
        // Count through the rows starting at A1 and return no of rows with actual values
        int intRowCounter = rowStart-1;
        while (getCellValueAsString(intRowCounter, 0).equalsIgnoreCase(StringConstants.STRING_BLANK) == false) {
            intRowCounter = intRowCounter + 1;
        }

        return intRowCounter;
    }

    protected static int getValidDataSheetRows() {
        // Count through the rows starting at A1 and return no of rows with actual values
        int intRowCounter = rowStart;
        int intValidRowCounter = 0;
        while (getCellValueAsString(intRowCounter, 0).equalsIgnoreCase(StringConstants.STRING_BLANK) == false) {
            intValidRowCounter = intValidRowCounter + 1;
            intRowCounter = intRowCounter + 1;
        }

        return intValidRowCounter;
    }
    protected static int getFullValidDataSheetRows() {
        // Count through the rows starting at A1 and return no of rows with actual values
      //  int intRowCounter = rowStart;
        int intValidRowCounter = 0;
        while (getCellValueAsString(intValidRowCounter, 0).equalsIgnoreCase(StringConstants.STRING_BLANK) == false) {
            intValidRowCounter = intValidRowCounter + 1;
            //intRowCounter = intRowCounter + 1;
        }

        return intValidRowCounter;
    }
}
