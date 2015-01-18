package voyanta.ui.pageobjects;

import voyanta.ui.pagecontainers.PageContainer;

/**
 * Created by sriramangajala on 24/07/2014.
 */
public interface TestersWorldPage {

    String URL = null;
    String title=null;
    PageContainer pageContainer = null;

    public String getTitle();

    public String getUrl();

    public boolean isPageDisplayed();

    public VoyantaUser getCurrentUser();

    public String getCurrentPageText();

    public boolean waitTillPageLoaded();


}
