package model;

public class Supplier {
    private String supplierID;
    private String supName;
    private String companyName;
    private String contactNo;


    public Supplier() {
    }

    public Supplier(String supplierID, String supName, String companyName, String contactNo) {
        this.supplierID = supplierID;
        this.supName = supName;
        this.companyName = companyName;
        this.contactNo = contactNo;
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
}
