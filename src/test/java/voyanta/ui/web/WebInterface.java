package voyanta.ui.web;

import org.openqa.selenium.support.PageFactory;
import voyanta.ui.pageobjects.*;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.unused.PermissionEnum;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class WebInterface {

    PermissionsPage permissionsPage;
    DashboardPage dashboardPage;
    LoginPage signInPage;
    BrowsePage browsePage ;
    LoginPage loginPage;
    LandingPage landingPage;


    public WebInterface()
    {
        dashboardPage = new DashboardPage();
        landingPage = new LandingPage();
    }

    public void gotoPermissionsPage() {

        permissionsPage = dashboardPage.gotoPermissionsPage();

    }

    public void loginWithUser(String permissionType, String objectName) {

        signInPage=new LoginPage();

        String username=null;
        String password=null;

        if(permissionType.equalsIgnoreCase("view")) {
            username = PermissionEnum.VIEWER.getUsername();
            password = PermissionEnum.VIEWER.getPassword();
        }
        else if(permissionType.equalsIgnoreCase("view on child"))
        {
            username = PermissionEnum.VIEWONCHILD.getUsername();
            password = PermissionEnum.VIEWONCHILD.getPassword();
        }
        else if(permissionType.equalsIgnoreCase("Submitter"))
        {
            username = PermissionEnum.SUBMITTER.getUsername();
            password = PermissionEnum.SUBMITTER.getPassword();
        }
        else if(permissionType.equalsIgnoreCase("submitchild"))
        {
            username = PermissionEnum.SUBMITCHILD.getUsername();
            password = PermissionEnum.SUBMITCHILD.getPassword();
        }
        else if(permissionType.equalsIgnoreCase("submitboth"))
        {
            username = PermissionEnum.SUBMITBOTH.getUsername();
            password = PermissionEnum.SUBMITBOTH.getPassword();
        }
        PageFactory.initElements(VoyantaDriver.getCurrentDriver(), signInPage);
        signInPage.signIn(username,password);
    }

    public void checkPermissionRecordExists(String type, String objectName, String objectReference, String levelOfAccess) {
        permissionsPage.checkPermissionRecordExists(type,objectName,objectReference,levelOfAccess);
    }

    public void logout() {
        permissionsPage.logout();
    }

    public void gotoTab(String tab) {
        dashboardPage.gotoTab(tab);
    }

    public void openObjectWithName(String objectName) {
        browsePage = new BrowsePage();
        browsePage.openObjectWithName(objectName);
    }

    public void checkifDataVisible(String fieldName, String fieldValue) {

        browsePage.checkFieldVisible(fieldName, fieldValue);
    }

    public void checkChildLevelObjectCanbeOpened(String name,String childObjectType, String childObjectName, String fieldName, String objectName, boolean b) {
        browsePage.checkChildLevelObjectCanbeOpened(name,childObjectType,childObjectName,fieldName,objectName,b);
    }

    public void downloadAndCheckFileDownloaded() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        browsePage.downloadDST();
        File file = VUtils.getDownloadedFile(".csv");
        VerifyUtils.True(file != null);

        try {
            Date FileDate = sdf.parse(String.valueOf(file.lastModified()));
            Date now = new Date();
            long diff = FileDate.getTime() - now.getTime();
//            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
//            long diffHours = diff / (60 * 60 * 1000);
            VerifyUtils.True(diffMinutes<2);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void checkButtonDisabled(boolean b, String buttonText) {
        browsePage.checkButtonDisabled(b,buttonText );
    }

    public void checkImageDisabled(boolean b, String imageName) {
        browsePage.checkImageDisabled(b,imageName);
    }

    public void gotoDatamanager() {
        browsePage.gotoDataManagerPage();
    }

    public void checkButtonVisible(boolean expected, String buttonName) {
        browsePage.checkButtonVisible(expected,buttonName);
    }

    public void selectEditButton() { browsePage.selectEditButton(); }

    public void changeDropDownValue(String name, String value) {
        browsePage.changeDropDownValue(name,value);}

    public void gotoHomePage() {
        dashboardPage.gotoHomePage();
    }

    public void gotoLoginPage() {
        landingPage.gotoLoginPage();

    }
}
