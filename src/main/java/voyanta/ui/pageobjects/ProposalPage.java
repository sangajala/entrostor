package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import voyanta.ui.pagecontainers.ProposalPageContainers;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.WaitUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by sriramangajala on 28/07/2014.
 */
public class ProposalPage extends abstractWebPage {

    static Logger LOGGER = Logger.getLogger(ProposalPage.class);

    ProposalPageContainers pageContainer = ProposalPage.getDataContainer(ProposalPageContainers.class);

    public String proposal_name = null;


    public void addNewProposal() {
      //  WaitUtils.waitForElement(pageContainer.save_proposal);
        pageContainer.add_new_proposal.click();
        VUtils.accept_alert();
        WaitUtils.waitForElement(pageContainer.save_proposal);
        WebDriver driver = VoyantaDriver.getCurrentDriver();
        //driver.findElement(By.name("proposal[name]")).clear();
        proposal_name = "test"+(new Random()).nextInt();
        driver.findElement(By.name("proposal[name]")).sendKeys(proposal_name);
        driver.findElement(By.name("proposal[shortSummary]")).clear();
        driver.findElement(By.name("proposal[shortSummary]")).sendKeys("test");
        driver.findElement(By.name("proposal[propProblem]")).clear();
        driver.findElement(By.name("proposal[propProblem]")).sendKeys("test");
        driver.findElement(By.cssSelector("p.label")).click();
        driver.findElement(By.xpath("//form[@id='proposal_form']/div/div[4]/div/div/div[3]/ul/li[6]")).click();
        driver.findElement(By.name("proposal[state]")).clear();
        driver.findElement(By.name("proposal[state]")).sendKeys("tet");
        driver.findElement(By.name("proposal[city]")).clear();
        driver.findElement(By.name("proposal[city]")).sendKeys("test");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.id("ui-multiselect-user_industries-option-0")).click();
        driver.findElement(By.xpath("//form[@id='proposal_form']/div/div[8]/div/div/div[2]/p")).click();
        driver.findElement(By.xpath("//form[@id='proposal_form']/div/div[8]/div/div/div[3]/ul/li[2]")).click();
        driver.findElement(By.name("proposal[propPurpose]")).clear();
        driver.findElement(By.name("proposal[propPurpose]")).sendKeys("rar");
        driver.findElement(By.xpath("//form[@id='proposal_form']/div/div[10]/div/div/div[2]/p")).click();
        driver.findElement(By.xpath("//form[@id='proposal_form']/div/div[10]/div/div/div[3]/ul/li[2]")).click();
        driver.findElement(By.name("proposal[propCapitalFrom]")).clear();
        driver.findElement(By.name("proposal[propCapitalFrom]")).sendKeys("12");
        driver.findElement(By.name("proposal[propEquity]")).clear();
        driver.findElement(By.name("proposal[propEquity]")).sendKeys("12");
        driver.findElement(By.name("proposal[propMinvestment]")).clear();
        driver.findElement(By.name("proposal[propMinvestment]")).sendKeys("12");
        driver.findElement(By.name("proposal[propPinvested]")).clear();
        driver.findElement(By.name("proposal[propPinvested]")).sendKeys("12");
        driver.findElement(By.id("percentage_funded")).clear();
        driver.findElement(By.id("percentage_funded")).sendKeys("12");
        driver.findElement(By.name("proposal[propPitch]")).clear();
        driver.findElement(By.name("proposal[propPitch]")).sendKeys("12");
        driver.findElement(By.cssSelector("input.save_proposal")).click();
        LOGGER.info("Created proposal name with "+proposal_name);


    }

    public String getProposalName() {

        return proposal_name;
    }

    public List<WebElement> getProposals() {
        LOGGER.info("Getting list of proposals");
        return VoyantaDriver.getCurrentDriver().findElements(By.cssSelector("div.project > p"));
    }

    public void deleteFirstProposal() {

        pageContainer.first_close.click();
        VUtils.accept_alert();


    }
}
