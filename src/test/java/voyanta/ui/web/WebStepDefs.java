package voyanta.ui.web;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import voyanta.ui.utils.PropertiesLoader;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;

/**
 * Created by sriramangajala on 22/08/2014.
 */
public class WebStepDefs {

    WebInterface webInterface;
    String objectName=null;

    @Before()
    public void start() {
        VoyantaDriver.getCurrentDriver().get(PropertiesLoader.getProperty("ui_url"));
        webInterface = new WebInterface();
    }


    @Given("^user navigates to web page$")
    public void user_navigates_to_operating_page() {
        webInterface.gotoPermissionsPage();
    }


    @Given("^A user exists with '(.*)' web for an object '(.*)' and logs in$")
    public void a_user_exists_with_view_permissions_for_an_object_Business(String permissionType, String objectName) throws Throwable {
        webInterface.loginWithUser(permissionType, objectName);
    }

    @Given("^user goes to '(.*)' Tab$")
    public void user_goes_to_tab(String tab)
    {
        webInterface.gotoTab(tab);
    }


    @When("^go to permission page$")
    public void go_to_permission_page() {
        webInterface.gotoPermissionsPage();

    }
    @Given("^user go to homepage$")
    public void user_go_to_homepage()  {
       webInterface.gotoHomePage();
    }

    @Then("^should see the data with Type as '(.*)',Object as '(.*)' - '(.*)' and Level of Access as '(.*)'$")
    public void should_see_the_data_with(String type, String objectName, String objectReference, String levelOfAccess) throws Throwable {

        webInterface.checkPermissionRecordExists(type, objectName, objectReference, levelOfAccess);
    }

    @Then("^user successfully logs out$")
    public void user_successfully_logs_out() throws Throwable {
        webInterface.logout();
    }


    @Given("^opens the object '(.*)'$")
    public void opens_object(String objectName) throws Throwable {
        this.objectName = objectName;
        webInterface.openObjectWithName(objectName);
    }

    @Then("^should see the '(.*)' as '(.*)'$")
    public void should_see_the_Asset_Reference(String name,String value) throws Throwable {
            webInterface.checkifDataVisible(name,value);
    }

    @Then("^can(.*) open the child level object type '(.*)' ,object name '(.*)' with '(.*)' as '(.*)'$")
    public void cannot_open_the_child_level_object(String not,String childObjectType,String childObjectName,String fieldName,String fieldValue) throws Throwable {
        if(not.equals("not"))
            webInterface.checkChildLevelObjectCanbeOpened(objectName,childObjectType,childObjectName,fieldName,fieldValue,false);
        else
            webInterface.checkChildLevelObjectCanbeOpened(objectName,childObjectType,childObjectName,fieldName,fieldValue,true);
    }

    @Then("^can download the DST$")
    public void should_download_the_DST() throws Throwable {
            webInterface.downloadAndCheckFileDownloaded();
    }

    @Then("^should download the DST for top level object$")
    public void should_download_the_DST_for_top_level_object() {
        webInterface.downloadAndCheckFileDownloaded();}

    @When("^should download the DST for child level object$")
    public void should_download_the_DST_for_child_level_object()  {  webInterface.downloadAndCheckFileDownloaded();}

    @Then("^can(.*) attach a file$")
    public void cannot_attach_a_file(String not) throws Throwable {
        if(not.equals("not"))
           webInterface.checkButtonDisabled(false, "Attach Files");
        else
           webInterface.checkButtonDisabled(true, "Attach Files");
    }

    @Then("^cannot attach a file for top level object$")
    public void cannot_attach_a_file_for_top_level_object() {
        webInterface.checkButtonDisabled(false, "Attach Files");}

    @When("^can attach a file for child level object$")
    public void can_attach_a_file_for_child_level_object() { webInterface.checkButtonDisabled(true, "Attach Files");}

    @Then("^cannot edit the data$")
    public void cannot_edit_the_data() throws Throwable {
        webInterface.checkButtonDisabled(false, "Edit");
    }

    @Then("^cannot edit the data for top level object$")
    public void cannot_edit_the_data_for_top_level_object() {
        webInterface.checkButtonDisabled(false, "Edit");}

    @Then("^can edit the data for child level object$")
    public void can_edit_the_data_for_child_level_object() {
        webInterface.selectEditButton();}

    @Then("^cannot delete the data$")
    public void cannot_delete_the_data() throws Throwable {
        webInterface.checkImageDisabled(false, "Delete");
    }

    @Then("^cannot delete the data for top level object$")
    public void cannot_delete_the_data_for_top_level_object(){ webInterface.checkImageDisabled(false, "Delete");}

    @Then("^cannot see the share option$")
    public void cannot_see_the_share_option() throws Throwable {
        webInterface.checkButtonVisible(false, "Share");
    }

    @Then("^cannot see the share option for child level object$")
    public void cannot_see_the_share_option_for_child_level_object() { webInterface.checkButtonVisible(false, "Share");}

    @When("^user goes to data manager page$")
    public void user_goes_to_data_manager_page() throws Throwable {
        webInterface.gotoDatamanager();
    }

    @Then("^should not see the upload option$")
    public void should_not_see_the_upload_option() throws Throwable {
        webInterface.checkButtonVisible(false,"Upload Data");
    }


    @When("^user modifies the '(.*)' for top level object to '(.*)'$")
    public void user_modifies_the_provider_reference_for_top_level_object(String name,String value)  {

        webInterface.changeDropDownValue(name,value);
    }

    @When("^user deletes the data for top level object$")
    public void user_deletes_the_data_for_top_level_object()  {

    }

    @Then("^should see a record with 'Pending' status in 'Approval' column$")
    public void should_see_a_record_with_Pending_status_in_Approval_column() {

    }

    @Then("^cannot see the share option for top level object$")
    public void can_see_the_share_option_for_top_level_object()  { webInterface.checkButtonVisible(false, "Share");   }

    @Then("^can edit the data for top level object$")
    public void can_edit_the_data_for_top_level_object()  {

        webInterface.selectEditButton();
    }

//    @When("^user modifies the '(.*)' for top level object to '(.*)'$")
//    public void user_modifies_the_provider_reference_for_top_level_object_to(String name, String value)  {
//        webInterface.changeDropDownValue(name,value);
//    }

    @When("^should see the upload option$")
    public void should_see_the_upload_option()  {
        webInterface.checkButtonVisible(true,"Upload Data");
    }

    @When("^user upload DST with the object which he have permission for$")
    public void user_upload_DST_with_the_object_which_he_have_permission_for()  {

    }

    @Then("^should see a DST with 'Pending' status in 'Approval' column$")
    public void should_see_a_DST_with_Pending_status_in_Approval_column() {

    }

    @When("^Admin rejects all the changes requested by user$")
    public void admin_rejects_all_the_changes_requested_by_user()  {

    }

    @After
    public void stop(Scenario scenario) {

        if (scenario.isFailed()) {
            VUtils.captureScreen(scenario);
        }
        VoyantaDriver.quit();
    }
}
