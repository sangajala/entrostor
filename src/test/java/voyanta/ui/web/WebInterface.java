package voyanta.ui.web;

import org.openqa.selenium.support.PageFactory;
import voyanta.ui.pageobjects.DashboardPage;
import voyanta.ui.pageobjects.LandingPage;
import voyanta.ui.pageobjects.LoginPage;
import voyanta.ui.pageobjects.PermissionsPage;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.unused.PermissionEnum;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class WebInterface {

    PermissionsPage permissionsPage;
    DashboardPage dashboardPage;
    LoginPage signInPage;

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







    public void gotoHomePage() {
        dashboardPage.gotoHomePage();
    }

    public void gotoLoginPage() {
        landingPage.gotoLoginPage();

    }
}
