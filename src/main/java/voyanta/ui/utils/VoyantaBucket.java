package voyanta.ui.utils;

import voyanta.ui.pageobjects.BusinessRule;

/**
 * Created by sriramangajala on 01/08/2014.
 */
public class VoyantaBucket {


    private static BusinessRule businessRule;



    public static void setBusinessRule(BusinessRule businessRule) {
        VoyantaBucket.businessRule = businessRule;
    }

    public static BusinessRule getBusinessRule() {
        return businessRule;
    }
}
