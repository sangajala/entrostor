package voyanta.ui;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import voyanta.ui.datamodel.DBUtils;
import voyanta.ui.datamodel.VHashMap;
import voyanta.ui.utils.DataSheetUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

//#  for your reference
/**
 * Created by sriramangajala on 30/06/2014.
 */
public class DataUnitTests {
    static Logger LOGGER = Logger.getLogger(DBUtils.class);

    Properties prop;
    String strDataSheetLocation = "src/main/resources/framework.properties";

    @Before
    public void start()
    {
        prop = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream(strDataSheetLocation);
       //     System.out.println(input);
            prop.load(input);
         //   System.out.print(prop.getProperty("TEST_FILE_NAME"));
//            , "firefox"));
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void uploadDataTest()
    {
        DataSheetUtil dataSheetUtil = new DataSheetUtil();
        List<VHashMap> data = dataSheetUtil.getTestDataFromExcel(prop.getProperty("TEST_FOLDER_NAME"),prop.getProperty("TEST_FILE_NAME"),prop.getProperty("TEST_SHEET_NAME"));
        Assert.assertTrue(0<data.size());

    }



}
