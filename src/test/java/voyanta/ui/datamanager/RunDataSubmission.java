package voyanta.ui.datamanager;

import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import voyanta.ui.baseTest;
//#  for your reference
/**
 * Created by sriramangajala on 23/07/2014.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(tags="@ErrorRows",format = {"pretty", "html:target/cucumber","json:target/cucumber.json"})
public class RunDataSubmission {


}
