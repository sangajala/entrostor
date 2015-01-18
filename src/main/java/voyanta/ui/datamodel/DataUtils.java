package voyanta.ui.datamodel;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by sriramangajala on 14/07/2014.
 */
public class DataUtils {
    static Logger LOGGER = Logger.getLogger(DataUtils.class);

    public static List<VHashMap> addAdditionalColumnFrom(List<VHashMap> vHashMapList, String additionalColumn, String existingColumn) {
        int totalCount = vHashMapList.size()-1;

        if(additionalColumn.trim().equals("")||existingColumn.trim().equals(""))
            return vHashMapList;

        LOGGER.debug("Checking if the column "+existingColumn+" exists in the given data");
        if(!vHashMapList.get(0).containsKey(existingColumn))
        {
            throw new RuntimeException("Given column "+existingColumn+" doesn't exist in the given data");
        }

        for(int i=0;i<=totalCount;i++)
        {

            vHashMapList.get(i).put(additionalColumn,vHashMapList.get(i).get(existingColumn));
        }
        LOGGER.info("Added a new column "+additionalColumn+" from "+existingColumn);
        return vHashMapList;
    }
}
