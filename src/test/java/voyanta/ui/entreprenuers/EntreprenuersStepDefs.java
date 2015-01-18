package voyanta.ui.entreprenuers;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import voyanta.ui.pageobjects.LoginPage;
import voyanta.ui.web.WebInterface;

/**
 * Created by sriramangajala on 23/07/2014.
 */
public class EntreprenuersStepDefs {

    EntrepreneursInterface entrepreneursInterface;
    WebInterface webInterface;
    LoginPage loginPage;
    WebDriver driver ;

    @Before
    public void start()
    {
        entrepreneursInterface = new EntrepreneursInterface();
        webInterface = new WebInterface();
    }

//    @Given("^Some thing happens$")
//    public void dummyStep()
//    {
////           loginPage.signIn();
////           loginPage.gotoBusinessRulePage();
//           entrepreneursInterface.createNewRule();
//    }
//
//    @Given("^Some thing happens(\\d+)$")
//    public void dummySte1p(int i)
//    {
////        loginPage.signIn();
////           loginPage.gotoBusinessRulePage();
//        entrepreneursInterface.createNewRule();
//    }
//
//    @After
//    public void stop(Scenario scenario)
//    {
//        if(scenario.isFailed())
//        {
//            VUtils.captureScreen(scenario);//.getName());
//        }
//    }
//
//    @Given("^user arrives on Business Rules Page$")
//    public void user_arrives_on_Business_Rules_Page() throws Throwable {
//        entrepreneursInterface.gotoBusinessRulePage();
//    }
//
//    @Given("^user creates the business rule$")
//    public void user_creates_the_rule() throws Throwable {
//        entrepreneursInterface.createNewRule();
//    }
//
//    @Given("^user creates the mapping rule$")
//    public void user_creates_the_rules() throws Throwable {
//        entrepreneursInterface.createNewMappingRule();
//    }
//
//    @Given("^user goes to Mapping Rules page$")
//    public void user_goes_to_mapping_rule_page() throws Throwable {
//        entrepreneursInterface.gotoMappingRulePage();
//    }
//
//
//    @Then("^user should see a page with header (.*)$")
//    public void user_should_see_a_page_with_header(String headerText) {
//            entrepreneursInterface.verifyHeaderOfPage(headerText);
//    }
//    @When("^user goes back to previous page$")
//    public void go_back_to_previous_page()
//    {
//        entrepreneursInterface.goBackToPreviousPage();
//    }
//
//    @When("^user provides below data and continues$")
//    public void user_provides_data(DataTable dataTable) {
//        Map<String,String> data = dataTable.asMap(String.class,String.class);
//        entrepreneursInterface.createBusinessRuleStep1(data.get("RuleName"),data.get("ObjectType"),data.get("Field"),data.get("ProviderOption"),data.get("Provider"));
//    }
//
//    @When("^user drag and drop '(.*)' with '(.*)'$")
//    public void set_drag_and_drop_fields(String value1,String value2)
//    {
//        entrepreneursInterface.setClause(value1, value2);
//    }
//
//    @When("^user provides below data in step 2 and saves the rule$")
//    public void user_provides_data_in_step2(DataTable dataTable) {
//        Map<String,String> data = dataTable.asMap(String.class,String.class);
//        entrepreneursInterface.createBusinessRuleStep2(data.get("Field"),data.get("FieldName"),data.get("Operator"),data.get("Value"),data.get("BRType"),data.get("MessageType"),data.get("Message"));
//    }
//
//    @When("^user modified the rule with below data in step 2 and saves the rule$")
//    public void user_modifies_data_in_step2(DataTable dataTable) {
//        Map<String,String> data = dataTable.asMap(String.class,String.class);
//        entrepreneursInterface.modifyRuleStep2(data.get("Field"),data.get("FieldName"),data.get("Operator"),data.get("Value"),data.get("BRType"),data.get("MessageType"),data.get("Message"));
//    }
//
//
//    @Then("^user should see '(.*)' on the page")
//    public void business_rules_should_be_saved_successfully_with_message(String message)
//    {
//        entrepreneursInterface.verifyMessageAfterSave(message);
//    }
//    @Then("^'(.*)' shows on the list$")
//    public void user_should_see_Business_rule_is_updated_message_on_the_page(String rule) throws Throwable {
//        entrepreneursInterface.check_business_rule_created();
//    }
//
//
//
//    @When("^user deletes the business rules with '(.*)'$")
//    public void user_deletes_the_business_rules_with_Delete_Test(String ruleName) throws Throwable {
//        entrepreneursInterface.deleteBusinessRule(ruleName);
//         }
//
//
//    @When("^user edits the business rules with '(.*)'$")
//    public void user_edits_the_business_rules_with(String ruleName) throws Throwable {
//        entrepreneursInterface.editBusinessRule(ruleName);
//    }
//    @Then("^the pop up box 'Confirm deletion' should appear$")
//    public void the_pop_up_box_Confirm_deletion_should_appear() throws Throwable {
//        entrepreneursInterface.isConfirmationShown();
//    }
//
//    @When("^user clicks 'Yes'$")
//    public void user_clicks_Yes() throws Throwable {
//        entrepreneursInterface.confirmDelete();
//    }
//
//    @Then("^'(.*)' should be deleted from the list$")
//    public void delete_Test_should_be_deleted_from_the_list(String ruleName) throws Throwable {
//        entrepreneursInterface.verifyRuleNotPresent(ruleName);
//    }
//
//    @Given("^Sample test$")
//    public void sample()
//    {
//        entrepreneursInterface.sample();
//    }
//
//    @When("^user defines '(.*)', '(.*)' and '(.*)' and saves the rule$")
//    public void user_defines_Rule(String from,String to,String notes) throws Throwable {
//        entrepreneursInterface.createMappingRuleStep2(from,to,notes);
//    }


    @Given("^User is in login page of entrostor$")
    public void user_is_in_homepage_of_entrostor() throws Throwable {
       webInterface.gotoLoginPage();
       entrepreneursInterface.verifyUserIsInLoginPage();
    }

    @When("^he tries to login with valid credentials$")
    public void he_tries_to_login_with_valid_credentials() throws Throwable {

    }

    @Then("^user is in dashboard page$")
    public void user_is_in_dashboard_page() throws Throwable {

    }
}
