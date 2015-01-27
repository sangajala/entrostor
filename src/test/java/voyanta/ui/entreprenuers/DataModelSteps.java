package voyanta.ui.entreprenuers;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.apache.log4j.Logger;
import voyanta.ui.datamodel.*;

import voyanta.ui.utils.PropertiesLoader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
//#  for your reference
/**
 * Created by sriramangajala on 08/07/2014.
 */
public class DataModelSteps {
    String boxFolder ;
    String testDataFolder ;
    String SQLFolder;
    String dataSheet;
    String UISQLFolder;
    String exportExpectedFolder;
    String exportActualFolder;
    List<VHashMap> dataBaseData;
    List<VHashMap> excelSheetData;
    Logger LOGGER = Logger.getLogger(DataModelSteps.class);
    List<HashMap> expExportData;
    List<HashMap> actualExportData;
    File excelFolder;
    String ruleName;

    DataSheetsView dataSheetsView ;

    DatabaseView databaseView ;
    private String xml;
    private String xml1;
    private String primaryKey;
    private boolean ruleMPSwitch=false;

    @Before
    public void before()
    {
        if(System.getProperty("os.name").toLowerCase().contains("mac"))
        {
            boxFolder = PropertiesLoader.getProperty("mac_boxFolder");
            testDataFolder = PropertiesLoader.getProperty("mac_testDataFolder");
            SQLFolder = PropertiesLoader.getProperty("mac_SQLFolder");
            UISQLFolder = PropertiesLoader.getProperty("mac_UISQLFolder");
        }
        else
        {
            boxFolder = PropertiesLoader.getProperty("windows_boxFolder");
            testDataFolder = PropertiesLoader.getProperty("windows_testDataFolder");
            SQLFolder = PropertiesLoader.getProperty("windows_SQLFolder");
            UISQLFolder = PropertiesLoader.getProperty("windows_UISQLFolder");
        }
        dataSheet=null;
        dataBaseData=null;
        excelSheetData=null;
        dataSheetsView = new DataSheetsView();
        databaseView = new DatabaseView();
    }

    @Given("^a record exists in Database with name '(.*)'$")
    public void a_record_exists_in_Database_with_name(String rulename) throws Throwable {
        ruleMPSwitch=false;
        this.ruleName=rulename;
        databaseView.executeSQLFileWithParameters(boxFolder+UISQLFolder+"CreateBRForDelete.sql",rulename);
    }

    @Given("^a Mapping rule record exists in Database with name '(.*)'$")
    public void a_MP_record_exists_in_Database_with_name(String rulename) throws Throwable {

        ruleMPSwitch=true;
        this.ruleName=rulename;
        databaseView.executeSQLFileWithParameters(boxFolder+UISQLFolder+"createMappingRule.sql",rulename);
    }

    @Before("@db")
    public void clean()
    {

        try {
            databaseView.executeSQLFileWithParameters(boxFolder+UISQLFolder+"RemoveCreatedBR.sql", ruleName);
        }
        catch (NullPointerException e)
        {

        }
    }
    @After("@db")
    public void cleanDB()
    {
        databaseView.executeSQLFileWithParameters(boxFolder+UISQLFolder+"RemoveCreatedBR.sql",ruleName);
        if(ruleMPSwitch)
            databaseView.replaceSQLParams(boxFolder+UISQLFolder+"CreateBRForDelete.sql",ruleName,PropertiesLoader.getProperty("SQLQueryPlaceHolderchar"));
        else
            databaseView.replaceSQLParams(boxFolder+UISQLFolder+"createMappingRule.sql",ruleName,PropertiesLoader.getProperty("SQLQueryPlaceHolderchar"));
         databaseView.replaceSQLParams(boxFolder+UISQLFolder+"RemoveCreatedBR.sql",ruleName,PropertiesLoader.getProperty("SQLQueryPlaceHolderchar"));
    }
//    @Given("^The DataSheet exists in the QA Box with name '(.*)'$")
//    public void the_DataSheet_exits_in_the_QA_Box_with_name(String datasheet) throws Throwable {
//        this.dataSheet = datasheet;
//        LOGGER.info("Searching for the file :"+datasheet+" in folder :"+boxFolder+testDataFolder);
//        excelFolder = FileSearch.findFile(datasheet, new File(boxFolder + testDataFolder));
//        Assert.assertTrue(excelFolder.getAbsolutePath().contains(datasheet));
//    }
//
//    @Given("^the data from DST is collected and saved as expected data$")
//    public void the_datasheet_data_is_saved() throws Throwable {
//        LOGGER.info("Collecting data from spreadsheet...");
//        excelSheetData = dataSheetsView.getExcelFileDataInHashMap(boxFolder+testDataFolder,dataSheet,"0");
//        Assert.assertTrue("Checking if atleast one row is returned from excel sheet",excelSheetData.size()>0);
//    }


//    @When("^data is collected from database with query '(.*)'$")
//    public void data_should_be_saved_in_database(String SQLQueryName) throws Throwable {
//         String FileName = boxFolder+"/"+SQLFolder+"/"+SQLQueryName;
//         dataBaseData = databaseView.getDataBaseRecordsFromFile(FileName,dataSheetsView.getNumberOfRecordsInExcel());
//         Assert.assertTrue("Checking if atleast one row is returned from database",dataBaseData.size()>0);
//    }
//
//    @Then("^the uploaded data from DST should match with database tables$")
//    public void data_in_all_the_cells_should_match() throws Throwable {
//        excelSheetData = ValidationUtils.lowerCaseColumnsAndRemoveSpaces(excelSheetData);
//        dataBaseData = ValidationUtils.lowerCaseColumnsAndRemoveSpaces(dataBaseData);
//
//        ValidationUtils.compareColumnHeaders(excelSheetData,dataBaseData);
//
//    }
//
//    @When("^an additional column '(.*)' is mapped with '(.*)'$")
//    public void add_additional_column(String additionalColumn,String existingColumn)
//    {
//        excelSheetData = dataSheetsView.copyDataToAdditionalColumn(excelSheetData,additionalColumn,existingColumn);
//    }
////----------------------------------------------------------------------//
//    @Given("^a file with name '(.*)' is existing with expected xml data$")
//    public void a_file_with_name_existing(String fileName) throws Throwable {
//        LOGGER.info("Checking for file with name :"+fileName);
//        xml = DBUtils.loadSQLFile(boxFolder + exportExpectedFolder + fileName);
//        Assert.assertNotNull(xml);
//
//    }
//
//    @Given("^a file with name '(.*)' is existing with actual xml data$")
//    public void a_file_with_name_Actual_File_folder(String fileName) throws Throwable {
//        LOGGER.info("Checking for file with name :"+fileName);
//        xml1 = DBUtils.loadSQLFile(boxFolder + exportActualFolder + fileName);
//        Assert.assertNotNull(xml1);
//    }
//
//    @When("^the data is loaded from actual file with root '(.*)', header '(.*)' and primary key '(.*)'$")
//    public void the_data_is_loaded_from_expected_file(String root, String header, String primaryKey,String secondaryKey) throws Throwable {
//        expExportData = VXMLUtils.sortData(VXMLUtils.getXMLData(xml, root, header), primaryKey, secondaryKey);
//    }
//
//    @When("^the data is loaded from expected file with root '(.*)', header '(.*)' and primary key '(.*)'$")
//    public void the_data_is_loaded_from_actual_file(String root, String header, String primaryKey,String secondaryKey) throws Throwable {
//        actualExportData = VXMLUtils.sortData(VXMLUtils.getXMLData(xml1, root, header),primaryKey,secondaryKey);
//    }
//
//
//    @Then("^both files should have same set of data with primary key '(.*)'$")
//    public void both_files_should_have_same_set_of_data(String key) throws Throwable {
//        ValidationUtils.compareTwoThings(expExportData,actualExportData,key);
//    }
//
//    @Given("all the files are saved under '(.*)' folder")
//    public void all_the_files_are_saved_under_folder(String folderLocation){
//        if(System.getProperty("os.name").toLowerCase().contains("mac"))
//        {
//          if(folderLocation=="StandartExport"){
//            exportExpectedFolder=PropertiesLoader.getProperty("mac_ExportExpectedFolder");
//            exportActualFolder =PropertiesLoader.getProperty("mac_ExportActualFolder");}
//          else
//          {
//              exportExpectedFolder=PropertiesLoader.getProperty("mac_ExportExpectedTaliance");
//              exportActualFolder =PropertiesLoader.getProperty("mac_ExportActualTaliance");
//          }
//        }
//        else
//        {
//            if(folderLocation.equals("StandartExport")){
//                exportExpectedFolder=PropertiesLoader.getProperty("windows_ExportExpectedFolder");
//                exportActualFolder =PropertiesLoader.getProperty("windows_ExportActualFolder");}
//            else{
//            exportExpectedFolder=PropertiesLoader.getProperty("windows_TalianceExpected");
//            exportActualFolder =PropertiesLoader.getProperty("windows_TalianceActual");
//            }
//        }
//    }
//
//
//    @Given("^an exported file exists with name '(.*)'$")
//    public void an_exported_file_exists_with_name(String file) throws Throwable {
//        a_file_with_name_existing(file);
//        a_file_with_name_Actual_File_folder(file);
//    }
//
//
//
//    @Given("^data is loaded with entity name '(.*)' with primary key '(.*)' and '(.*)'$")
//    public void data_is_loaded_with_entity_name__with_primary_key_AssetReference(String entity,String primaryKey,String secondaryKey) throws Throwable {
//        this.primaryKey = primaryKey;
//        the_data_is_loaded_from_expected_file(entity + "_EXTRACT", entity, primaryKey,secondaryKey);
//        the_data_is_loaded_from_actual_file(entity + "_EXTRACT", entity, primaryKey,secondaryKey);
//
//    }
//
//    @Then("^both files should have same set of data$")
//    public void both_files_should_have_same_set_of_data() throws Throwable {
//        both_files_should_have_same_set_of_data(primaryKey);
//
//    }
//
//    @And("^ignore the validation taking today's value for '(.*)'$")
//    public void ignore_the_validation_taking_today_s_value_for(String columns) throws Throwable {
//        String replaceValue;
//        if(!columns.equals("")) {
//            for (String column:columns.split(","))
//            {
//                replaceValue = (new SimpleDateFormat("yyyy-MM-dd")).format((new Date()));
//                expExportData=VXMLUtils.replaceValueForColumn(expExportData,column.trim(),replaceValue);
//            }
//        }
//    }
}
