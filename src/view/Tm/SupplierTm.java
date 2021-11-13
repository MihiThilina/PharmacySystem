package view.Tm;

import javafx.scene.control.Button;

import java.util.Objects;

public class SupplierTm {
    private String supplierID;
    private String supName;
    private String companyName;
    private String contactNo;
    private Button btn;

    public SupplierTm() {
    }

    public SupplierTm(String supplierID, String supName, String companyName, String contactNo,Button btn) {
        this.setSupplierID(supplierID);
        this.setSupName(supName);
        this.setCompanyName(companyName);
        this.setContactNo(contactNo);
        this.setBtn(btn);
    }

    @Override
    public String toString() {
        return "SupplierTm{" +
                "supplierID='" + getSupplierID() + '\'' +
                ", supName='" + getSupName() + '\'' +
                ", companyName='" + getCompanyName() + '\'' +
                ", contactNo='" + getContactNo() + '\'' +
                ", btn=" + getBtn() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierTm that = (SupplierTm) o;
        return Objects.equals(getSupplierID(), that.getSupplierID()) &&
                Objects.equals(getSupName(), that.getSupName()) &&
                Objects.equals(getCompanyName(), that.getCompanyName()) &&
                Objects.equals(getContactNo(), that.getContactNo()) &&
                Objects.equals(getBtn(), that.getBtn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSupplierID(), getSupName(), getCompanyName(), getContactNo(), getBtn());
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
