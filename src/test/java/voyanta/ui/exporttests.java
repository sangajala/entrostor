package voyanta.ui;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by sriramangajala on 21/07/2014.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(tags="@export",format = {"pretty", "html:target/cucumber","json:target/cucumber.json"})
public class exporttests {
}
