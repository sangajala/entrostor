package voyanta.ui.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sriramangajala on 08/07/2014.
 */
public class PropertiesLoader {

    public static Properties getProperties() {
        return prop;
    }

    public static void setProp(Properties prop) {
        PropertiesLoader.prop = prop;
    }

    public static void setProperty(String key, String value)
    {
        if(getProperty(key)!=null)
        {
            getProperties().setProperty(key,value);
        }
    }

    static Properties prop,prop1,merged;
    static String strDataSheetLocation = "src/main/resources/";

    public static String getProperty(String key)
    {
        if(getProperties()==null)
        {
            loadPropertyFile();
        }

        return merged.getProperty(key);
    }

    private static void loadPropertyFile() {
        prop= new Properties();
        prop1=new Properties();
        merged = new Properties();
        FileInputStream input = null;
        try {

         //   Properties merged = new Properties();

            if(System.getProperty("test_phase")==null||System.getProperty("test_phase").equals(""))
                 input = new FileInputStream(strDataSheetLocation+"test"+".properties");
            else
                input = new FileInputStream(strDataSheetLocation+System.getProperty("test_phase")+".properties");

            prop.load(input);

            input = new FileInputStream(strDataSheetLocation+"framework"+".properties");
            prop1.load(input);

            merged.putAll(prop1);
            merged.putAll(prop);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
