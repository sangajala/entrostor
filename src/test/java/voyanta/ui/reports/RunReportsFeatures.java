package voyanta.ui.reports;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import voyanta.ui.baseTest;

/**
 * Created by sriramangajala on 20/08/2014.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(tags="@report",format = {"pretty", "html:target/cucumber","json:target/RunReportsFeatures/cucumber.json"})
public class RunReportsFeatures extends baseTest {
}
