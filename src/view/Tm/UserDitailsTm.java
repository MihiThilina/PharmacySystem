package view.Tm;

public class UserDitailsTm {
    private String roleID;
    private String userName;
    private String type;
    private String password;

    public UserDitailsTm() {
    }

    public UserDitailsTm(String roleID, String userName, String type, String password) {
        this.setRoleID(roleID);
        this.setUserName(userName);
        this.setType(type);
        this.setPassword(password);
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
