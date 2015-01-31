package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import voyanta.ui.pagecontainers.PermissionsPageContainer;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class PermissionsPage extends  abstractPageWithList{

    static Logger LOGGER = org.apache.log4j.Logger.getLogger(PermissionsPage.class);

    PermissionsPageContainer pageContainer = PermissionsPage.getDataContainer(PermissionsPageContainer.class);

    public PermissionsPage()
    {
        super.tableElement = pageContainer.tableElement;
        super.tableId = "sharing-table";

        VUtils.waitForElement(pageContainer.content);
        if(!pageContainer.content.getText().contains("Permissions"))
            throw new RuntimeException("Permission page not loaded");
    }


    public void checkPermissionRecordExists(String type, String objectName, String objectReference, String levelOfAccess) {
                getRowsAsHashIgnoreBlankHeaders();
                int rowNumber = getRowNumber("Object",objectName+" - "+objectReference);
                LOGGER.info("Found the object in row number "+rowNumber);
        VerifyUtils.True(rowNumber>0);
    }
}
