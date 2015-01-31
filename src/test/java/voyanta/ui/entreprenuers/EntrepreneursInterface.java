package voyanta.ui.entreprenuers;

import voyanta.ui.pageobjects.*;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;
import voyanta.ui.utils.VoyantaBucket;
import voyanta.ui.utils.VoyantaDriver;

import java.util.List;

/**
 * Created by sriramangajala on 23/07/2014.
 */
public class EntrepreneursInterface
{
    private ListOfBusinessRulesPage listOfBusinessRulesPage;
    private CreateRulePage createRulePage;
   private LoginPage loginPage;

    private DashboardPage dashboardPage;
    private ProposalPage proposalPage;


    public EntrepreneursInterface()
    {
//       listOfBusinessRulesPage = new ListOfBusinessRulesPage();
//        createBusinessRulePage = new CreateBusinessRulePage();
        dashboardPage = new DashboardPage();
        loginPage = new LoginPage();

    }

    public void createNewRule()
    {
        createRulePage = listOfBusinessRulesPage.createRule();

    }

    public void gotoBusinessRulePage()
    {
        listOfBusinessRulesPage = dashboardPage.gotoBusinessRulePage();
    }

    public void verifyHeaderOfPage(String headerText) {
        VerifyUtils.contains(createRulePage.getHeaderText(), headerText);
    }

    public void createBusinessRuleStep1(String ruleName, String objectType, String field, String providerOption, String provider) {
        createRulePage.createBusinessRuleStep1(ruleName,objectType,field,providerOption,provider);
    }

    public void setClause(String value1, String value2) {
        createRulePage.setClause(value1,value2);
    }

    public void createBusinessRuleStep2(String field, String fieldName, String operator, String value, String brType, String messageType, String message) {
        listOfBusinessRulesPage = createRulePage.createBusinessRuleStep2(field, fieldName, operator, value, brType, messageType, message);
    }

    public void verifyMessageAfterSave(String message) {
        VerifyUtils.contains(message, VoyantaDriver.getCurrentDriver().getPageSource());
    }

    public void check_business_rule_created() {
        listOfBusinessRulesPage.checkBusinessRuleExists(VoyantaBucket.getBusinessRule());
    }

    public void sample() {
        listOfBusinessRulesPage = new ListOfBusinessRulesPage();
        VUtils.waitFor(5);
        listOfBusinessRulesPage.getRowsInHash();
        System.out.println(listOfBusinessRulesPage.getElementInColumn(1, "").getText());
   
    }

    public void deleteBusinessRule(String ruleName) {
        loadData();
        listOfBusinessRulesPage.deleteRule(ruleName);
    }

    public void loadData()
    {
        VUtils.waitFor(2);
        listOfBusinessRulesPage.getRowsInHash();
    }


    public void confirmDelete() {
        listOfBusinessRulesPage.confirmDelete();
    }

    public void isConfirmationShown() {
        VerifyUtils.True(listOfBusinessRulesPage.isConfirmationDialogShown());
    }

    public void verifyRuleNotPresent(String ruleName) {
        VerifyUtils.True(!listOfBusinessRulesPage.isRulePresent(ruleName));
    }

    public void editBusinessRule(String ruleName) {
        loadData();
        createRulePage =  listOfBusinessRulesPage.editRuleByName(ruleName);
    }


    public void createNewMappingRule() {
        createRulePage = listOfBusinessRulesPage.createMappingRule();
    }

    public void createMappingRuleStep2(String from, String to, String notes) {
        createRulePage.createMappingRuleStep2(from,to,notes);
    }

    public void modifyRuleStep2(String field, String fieldName, String operator, String value, String brType, String messageType, String message) {
        listOfBusinessRulesPage = createRulePage.modifyBusinessRuleStep2(field, fieldName, operator, value, brType, messageType, message);
    }

    public void goBackToPreviousPage() {
        createRulePage.gotoPreviousPage();
    }

    public void verifyUserIsInLoginPage() {

        VerifyUtils.True(dashboardPage.isUserInDashBoardPage());
    }

    public void login() {
        loginPage.signIn();
    }

    public void gotoProposalPage() {
        proposalPage = dashboardPage.gotoProposalPage();
    }

    public void createProposal() {

        proposalPage.addNewProposal();
//        proposalPage.addNewProposal();
    }

    public void verifyCreatedProposalExists() {
        VoyantaDriver.getCurrentDriver().navigate().refresh();
        VerifyUtils.True(VUtils.isListContains(proposalPage.getProposals(), proposalPage.getProposalName()));
    }

    public void delete_created_proposal() {
        proposalPage.deleteFirstProposal();
        VoyantaDriver.getCurrentDriver().navigate().refresh();
        VUtils.waitFor(5);
    }

    public void delete_existing_proposal() {

        proposalPage.deleteProposal(proposalPage.getProposalName());
        VoyantaDriver.refresh();
    }

    public void give_proposal_should_be_deleted() {

        List<String> proposals = proposalPage.getListOfProsals();

        VerifyUtils.False(proposals.contains(proposalPage.getProposalName()));
    }
}
