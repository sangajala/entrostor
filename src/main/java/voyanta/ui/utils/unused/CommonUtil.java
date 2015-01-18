package voyanta.ui.utils.unused;

import com.thoughtworks.selenium.Selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class CommonUtil {

	public String HOST = "localhost";



    public String BROWSER = "*chrome";
	public String URL = "http://demo.nopcommerce.com";
	public Random random = new Random();
	public static String newEmail;
    public static Selenium selenium;
    Properties properties;
    public void pause()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
    public static Selenium getSelenium()
    {
        return selenium;
    }
    public void setSelenium(Selenium selenium)
    {
        this.selenium = selenium;
    }

    public void generteEmail()
    {
        int emailprefix = random.nextInt();
        String newEmail = String.valueOf(emailprefix)+"@example.com";
        System.out.println(newEmail);
        setEmail(newEmail);
    }

    public void setEmail(String newEmail)
    {
        this.newEmail = newEmail;
    }
    public String getNewEmail() {
        generteEmail();
        return this.newEmail;
    }

    public String getEmail(){
        return this.newEmail;
    }

    public static String getCurrentEmail() {
        return newEmail;
    }

    public void loadProperties()  {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/system.properties"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        HOST = properties.getProperty("HOST");
        BROWSER = properties.getProperty("BROWSER");
        URL = properties.getProperty("URL");
    }

    public static void waitFor(int pollingEveryMs) {
        try {
            Thread.sleep(pollingEveryMs);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
