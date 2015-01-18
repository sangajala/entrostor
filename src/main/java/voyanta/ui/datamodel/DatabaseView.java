package voyanta.ui.datamodel;



import org.apache.log4j.Logger;
import voyanta.ui.datamodel.dbtables.loadDBRecords;
import voyanta.ui.utils.PropertiesLoader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by sriramangajala on 01/07/2014.
 */
public class DatabaseView {

    static Logger LOGGER = Logger.getLogger(DatabaseView.class);

    String SQL;

    public String getSQL(String sqlEnumName)
    {
        return SQLEnum.getEnum(sqlEnumName).getSQL();
    }

    public List<VHashMap> getDataBaseRecords(String sqlEnumName)
    {
        SQL = getSQL(sqlEnumName);
        return getDataBaseRecordsWithQuery(SQL);
    }

    public boolean executeSQLFileWithParameters(String sqlFileName,String withparameter)
    {
       // String SQL = DBUtils.loadSQLFile(sqlFileName);
        sqlFileName = replaceSQLParams(sqlFileName,PropertiesLoader.getProperty("SQLQueryPlaceHolderchar"),withparameter);
        try {

        DBUtils.connectToDataBase(PropertiesLoader.getProperty("dbuserName_f"), PropertiesLoader.getProperty("dbpassword_f"), PropertiesLoader.getProperty("url_f"));

         return DBUtils.executeUpdate(sqlFileName)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.closeConnection();
        return false;

    }

    public String replaceSQLParams(String sqlFileName,String fromParameter,String withParameter)
    {
        return DBUtils.replaceSQLParams(sqlFileName,fromParameter,withParameter);
    }



    private List<VHashMap> getDataBaseRecordsWithQuery(String query) {
        loadDBRecords loadDBRecords = new loadDBRecords();
        return loadDBRecords.loadRecords(PropertiesLoader.getProperty("dbuserName"), PropertiesLoader.getProperty("dbpassword"), PropertiesLoader.getProperty("url"), query);
    }

    public List<VHashMap> getDataBaseRecordsInString(String sqlFileName)
    {
        List<VHashMap> vHashMapList = getDataBaseRecords(sqlFileName);
            for(VHashMap vHashMap:vHashMapList)
            {
                vHashMap = vHashMap.humanise();
            }

        return  vHashMapList;
    }

    public List<VHashMap> getDataBaseRecordsInStringWithLimit(String sqlFileName,int limit)
    {
        String SQL = getSQL(sqlFileName);
        return getSQLDataFromQueryWithLimit(limit, SQL);

    }

    private List<VHashMap> getSQLDataFromQueryWithLimit(int limit, String SQL) {
        SQL = SQL.replace(";","") + " LIMIT 0,"+limit;
        return getDataBaseRecordsWithQuery(SQL);
    }

    public List<VHashMap> getDataBaseRecordsFromFile(String fileName,int limit)
    {
        String SQL = DBUtils.loadSQLFile(fileName);
        if(SQL.trim().equals(""))
            throw new RuntimeException("File "+fileName+"not found to run the sql query");
        return getSQLDataFromQueryWithLimit(limit, SQL);
    }


}
