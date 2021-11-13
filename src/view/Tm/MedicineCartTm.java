package view.Tm;

import java.util.Objects;

public class MedicineCartTm {
    private String medicineID;
    private String medicinename;
    private Integer qty;
    private Double unitprice;
    private Double discount;
    private Double total;

/*
    public MedicineCartTm(Object value, String name, int qtyOnHand, int qty, double unitPrice, double discount, double total) {
    }
*/

    public MedicineCartTm(String medicineID, String medicinename, Integer qty, Double unitprice, Double discount, Double total) {
        this.setMedicineID(medicineID);
        this.setMedicinename(medicinename);
        this.setQty(qty);
        this.setUnitprice(unitprice);
        this.setDiscount(discount);
        this.setTotal(total);
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "MedicineCartTm{" +
                "medicineID='" + medicineID + '\'' +
                ", medicinename='" + medicinename + '\'' +
                ", qty=" + qty +
                ", unitprice=" + unitprice +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineCartTm that = (MedicineCartTm) o;
        return Objects.equals(medicineID, that.medicineID) &&
                Objects.equals(medicinename, that.medicinename) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(unitprice, that.unitprice) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineID, medicinename, qty, unitprice, discount, total);
    }
}
