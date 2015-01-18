package voyanta.ui.datamodel.sheets;

/**
 * Created by sriramangajala on 01/07/2014.
 */
public class accountRowData {
    private String Active;
    private String Chart_Of_Accounts_Name;
    private String Account_Category;
    private String Account_Number;
    private String Account_Name;
    private String Account_Description;
    private String Sub_Category;
    private String Sub_Account_Number;
    private String Sub_Account_Name;
    private String Sub_Account_Description;

    public accountRowData(String active, String chart_Of_Accounts_Name, String account_Category, String account_Number, String account_Name, String account_Description, String sub_Category, String sub_Account_Number, String sub_Account_Name, String sub_Account_Description) {
        Active = active;
        Chart_Of_Accounts_Name = chart_Of_Accounts_Name;
        Account_Category = account_Category;
        Account_Number = account_Number;
        Account_Name = account_Name;
        Account_Description = account_Description;
        Sub_Category = sub_Category;
        Sub_Account_Number = sub_Account_Number;
        Sub_Account_Name = sub_Account_Name;
        Sub_Account_Description = sub_Account_Description;
    }
    public String getActive() {
        return Active;
    }

    public String getAccount_Category() {
        return Account_Category;
    }

    public String getAccount_Number() {
        return Account_Number;
    }

    public String getAccount_Name() {
        return Account_Name;
    }

    public String getAccount_Description() {
        return Account_Description;
    }

    public String getSub_Category() {
        return Sub_Category;
    }

    public String getSub_Account_Number() {
        return Sub_Account_Number;
    }

    public String getSub_Account_Name() {
        return Sub_Account_Name;
    }

    public String getSub_Account_Description() {
        return Sub_Account_Description;
    }



    public String getChart_Of_Accounts_Name() {
        return Chart_Of_Accounts_Name;
    }


}
