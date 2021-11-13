package model;

public class MovableData {

    private String medicineOrderID;
    private int quantity;

    public MovableData() {
    }

    public MovableData(String medicineOrderID, int quantity) {
        this.setMedicineOrderID(medicineOrderID);
        this.setQuantity(quantity);
    }

    public String getMedicineOrderID() {
        return medicineOrderID;
    }

    public void setMedicineOrderID(String medicineOrderID) {
        this.medicineOrderID = medicineOrderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
