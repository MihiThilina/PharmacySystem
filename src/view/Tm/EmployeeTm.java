package view.Tm;

import javafx.scene.control.Button;

import java.util.Objects;

public class EmployeeTm {
    private String roleID;
    private String name;
    private String rolePhoneNum;
    private String roleType;
    private String roleAddress;
    private String dateOfBirth;
    private String roleNic;
    private String gender;

    public EmployeeTm(String roleID, String roleType, String roleNic, String dateOfBirth, String gender, String rolePhoneNum, String name, String roleAddress, Button update) {}

    public EmployeeTm(String roleID, String name, String rolePhoneNum, String roleType, String roleAddress, String dateOfBirth, String roleNic, String gender) {
        this.setRoleID(roleID);
        this.setName(name);
        this.setRolePhoneNum(rolePhoneNum);
        this.setRoleType(roleType);
        this.setRoleAddress(roleAddress);
        this.setDateOfBirth(dateOfBirth);
        this.setRoleNic(roleNic);
        this.setGender(gender);
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRolePhoneNum() {
        return rolePhoneNum;
    }

    public void setRolePhoneNum(String rolePhoneNum) {
        this.rolePhoneNum = rolePhoneNum;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleAddress() {
        return roleAddress;
    }

    public void setRoleAddress(String roleAddress) {
        this.roleAddress = roleAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRoleNic() {
        return roleNic;
    }

    public void setRoleNic(String roleNic) {
        this.roleNic = roleNic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "roleID='" + roleID + '\'' +
                ", name='" + name + '\'' +
                ", rolePhoneNum='" + rolePhoneNum + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleAddress='" + roleAddress + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", roleNic='" + roleNic + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTm that = (EmployeeTm) o;
        return Objects.equals(roleID, that.roleID) &&
                Objects.equals(name, that.name) &&
                Objects.equals(rolePhoneNum, that.rolePhoneNum) &&
                Objects.equals(roleType, that.roleType) &&
                Objects.equals(roleAddress, that.roleAddress) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(roleNic, that.roleNic) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, name, rolePhoneNum, roleType, roleAddress, dateOfBirth, roleNic, gender);
    }

}
