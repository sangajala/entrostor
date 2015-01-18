package voyanta.ui.datamodel.dbtables;



import voyanta.ui.datamodel.DBUtils;
import voyanta.ui.datamodel.VHashMap;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sriramangajala on 01/07/2014.
 */
public class loadDBRecords {

 public List<VHashMap> loadRecords(String username,String password,String url,String query)  {

     try {
         DBUtils.connectToDataBase(username, password, url);
         List<VHashMap> records = DBUtils.executeAndGetResults(query);
         DBUtils.closeConnection();
         return records;
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return null;
 }

}
