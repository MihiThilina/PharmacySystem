package model;

public class EmployeesDetails {
    private String roleID;
    private String roleType;
    private String roleNic;
    private String dateOfBirth;
    private String gender;
    private String rolePhoneNum;
    private String name;
    private String roleAddress;

    public EmployeesDetails() {
    }

    public EmployeesDetails(String roleID, String roleType, String roleNic, String dateOfBirth, String gender, String rolePhoneNum, String name, String roleAddress) {
        this.setRoleID(roleID);
        this.setRoleType(roleType);
        this.setRoleNic(roleNic);
        this.setDateOfBirth(dateOfBirth);
        this.setGender(gender);
        this.setRolePhoneNum(rolePhoneNum);
        this.setName(name);
        this.setRoleAddress(roleAddress);
    }


    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleNic() {
        return roleNic;
    }

    public void setRoleNic(String roleNic) {
        this.roleNic = roleNic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRolePhoneNum() {
        return rolePhoneNum;
    }

    public void setRolePhoneNum(String rolePhoneNum) {
        this.rolePhoneNum = rolePhoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleAddress() {
        return roleAddress;
    }

    public void setRoleAddress(String roleAddress) {
        this.roleAddress = roleAddress;
    }

    @Override
    public String toString() {
        return "EmployeesDetails{" +
                "roleID='" + roleID + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleNic='" + roleNic + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", rolePhoneNum='" + rolePhoneNum + '\'' +
                ", name='" + name + '\'' +
                ", roleAddress='" + roleAddress + '\'' +
                '}';
    }
}
