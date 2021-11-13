package model;


import view.Tm.SupplierIdTm;
import view.Tm.SupplierTm;

import java.util.ArrayList;

public class Medicine {
    private String medicineId;
    private String medicineName;
    private String mUnit;
    private int medicinemQty;
    private String medicineManufacutre;
    private String medicineExDate;
    private String medicineCategory;
    private Double medicinePrice;
    ArrayList<SupplierIdTm> supplierTms;

    public Medicine() {
    }


    public Medicine(String medicineId, String medicineName, String mUnit, int medicinemQty, String medicineManufacutre, String medicineExDate, String medicineCategory, Double medicinePrice, ArrayList<SupplierIdTm> supplierTms) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.mUnit = mUnit;
        this.medicinemQty = medicinemQty;
        this.medicineManufacutre = medicineManufacutre;
        this.medicineExDate = medicineExDate;
        this.medicineCategory = medicineCategory;
        this.medicinePrice = medicinePrice;
        this.supplierTms = supplierTms;
    }

    public Medicine(String medicineId, String medicineName, String mUnit, int medicinemQty, String medicineManufacutre, String medicineExDate, String medicineCategory, Double medicinePrice) {
        this.setMedicineId(medicineId);
        this.setMedicineName(medicineName);
        this.setmUnit(mUnit);
        this.setMedicinemQty(medicinemQty);
        this.setMedicineManufacutre(medicineManufacutre);
        this.setMedicineExDate(medicineExDate);
        this.setMedicineCategory(medicineCategory);
        this.setMedicinePrice(medicinePrice);
    }


    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getmUnit() {
        return mUnit;
    }

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    public int getMedicinemQty() {
        return medicinemQty;
    }

    public void setMedicinemQty(int medicinemQty) {
        this.medicinemQty = medicinemQty;
    }

    public String getMedicineManufacutre() {
        return medicineManufacutre;
    }

    public void setMedicineManufacutre(String medicineManufacutre) {
        this.medicineManufacutre = medicineManufacutre;
    }

    public String getMedicineExDate() {
        return medicineExDate;
    }

    public void setMedicineExDate(String medicineExDate) {
        this.medicineExDate = medicineExDate;
    }

    public String getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(String medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    public Double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
    public ArrayList<SupplierIdTm> getSupplierTms() {
        return supplierTms;
    }

    public void setSupplierTms(ArrayList<SupplierIdTm> supplierTms) {
        this.supplierTms = supplierTms;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId='" + medicineId + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", medicinemQty=" + medicinemQty +
                ", medicineManufacutre='" + medicineManufacutre + '\'' +
                ", medicineExDate='" + medicineExDate + '\'' +
                ", medicineCategory='" + medicineCategory + '\'' +
                ", medicinePrice=" + medicinePrice +
                '}';
    }
}
