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



    @When("^user deletes the data for top level object$")
    public void user_deletes_the_data_for_top_level_object()  {

    }

    @Then("^should see a record with 'Pending' status in 'Approval' column$")
    public void should_see_a_record_with_Pending_status_in_Approval_column() {

    }



//    @When("^user modifies the '(.*)' for top level object to '(.*)'$")
//    public void user_modifies_the_provider_reference_for_top_level_object_to(String name, String value)  {
//        webInterface.changeDropDownValue(name,value);
//    }


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
