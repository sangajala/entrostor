package voyanta.ui.utils.unused;

/**
 * Created by sriramangajala on 05/09/2014.
 */
public enum PermissionEnum {




    VIEWER("Viewer","Building","Abit REIT","INV-005","APPROVER","VIEWER","viewer1@automation.com","password1!"),
    VIEWONCHILD("View on child","Building","Abit REIT","INV-005","APPROVER","VIEWONCHILD","viewer2@automation.com","password1!"),
    SUBMITTER("Submitter","Building","Abit REIT","INV-005","APPROVER","VIEWONCHILD","submit1@automation.com","password1!"),
    SUBMITCHILD("submitchild","Building","Abit REIT","INV-005","APPROVER","VIEWONCHILD","submitchild@automation.com","password1!"),
    SUBMITBOTH("submitboth","Building","Abit REIT","INV-005","APPROVER","VIEWONCHILD","submit2@automation.com","password1!");


    private String Permission_Type;
    private String Type;
    private String Object_Name;
    private String Object_Ref;
    private String Shared_With;

    public String getPermission_Type() {
        return Permission_Type;
    }

    public String getType() {
        return Type;
    }

    public String getObject_Name() {
        return Object_Name;
    }

    public String getObject_Ref() {
        return Object_Ref;
    }

    public String getShared_With() {
        return Shared_With;
    }

    public String getLevel_Of_Access() {
        return Level_Of_Access;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String Level_Of_Access;
    private String username;
    private String password;


    PermissionEnum(String Permission_Type, String Type, String Object_Name,String Object_Ref,String Shared_With, String Level_Of_Access,String username, String password) {

        this.Permission_Type=Permission_Type;
        this.Type = Type;
        this.Object_Name = Object_Name;
        this.Object_Ref = Object_Ref;
        this.Shared_With = Shared_With;
        this.Level_Of_Access = Level_Of_Access;
        this.username = username;
        this.password = password;

    }


}
