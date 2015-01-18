package voyanta.ui.web;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by sriramangajala on 22/08/2014.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(tags="@submitter",format = {"pretty", "html:target/cucumber","json:target/cucumber.json"})

public class RunWebTests {
}
