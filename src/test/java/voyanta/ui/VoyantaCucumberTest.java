package voyanta.ui;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(tags="@entro",format = {"pretty", "html:target/cucumber","json:target/VoyantaCucumberTest/cucumber.json"})
public class VoyantaCucumberTest extends baseTest{
}





