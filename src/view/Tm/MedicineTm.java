package view.Tm;

import javafx.scene.control.Button;

public class MedicineTm {
    private String medicineId;
    private String medicineName;
    private String gram;
    private int medicinemQty;
    private String medicineManufacutre;
    private String medicineExDate;
    private String medicineCategory;
    private Double medicinePrice;
    private Button btn;

    public MedicineTm() {
    }

    public MedicineTm(String medicineId, String medicineName, String gram, int medicinemQty, String medicineManufacutre, String medicineExDate, String medicineCategory, Double medicinePrice) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.gram = gram;
        this.medicinemQty = medicinemQty;
        this.medicineManufacutre = medicineManufacutre;
        this.medicineExDate = medicineExDate;
        this.medicineCategory = medicineCategory;
        this.medicinePrice = medicinePrice;
    }

    public MedicineTm(String medicineId, String medicineName, String gram, int medicinemQty, String medicineManufacutre, String medicineExDate, String medicineCategory, Double medicinePrice, Button btn) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.gram = gram;
        this.medicinemQty = medicinemQty;
        this.medicineManufacutre = medicineManufacutre;
        this.medicineExDate = medicineExDate;
        this.medicineCategory = medicineCategory;
        this.medicinePrice = medicinePrice;
        this.btn = btn;
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

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
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

    @Override
    public String toString() {
        return "MedicineTm{" +
                "medicineId='" + medicineId + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", mUnit='" + gram + '\'' +
                ", medicinemQty='" + medicinemQty + '\'' +
                ", medicineManufacutre='" + medicineManufacutre + '\'' +
                ", medicineExDate='" + medicineExDate + '\'' +
                ", medicineCategory='" + medicineCategory + '\'' +
                ", medicinePrice=" + medicinePrice +
                '}';
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
