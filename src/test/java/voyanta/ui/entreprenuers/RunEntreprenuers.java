package voyanta.ui.entreprenuers;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import voyanta.ui.baseTest;

/**
 * Created by sriramangajala on 23/07/2014.
 */
@RunWith(Cucumber.class)

@Cucumber.Options(tags="@businessRules",format = {"pretty", "html:target/cucumber","json:target/RunEntreprenuers/cucumber.json"})
public class RunEntreprenuers extends baseTest
{


}
