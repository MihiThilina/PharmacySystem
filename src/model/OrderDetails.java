package model;

import java.util.Objects;

public class OrderDetails {
         private String medicineOrderID;
         private String medicineID;
         private Integer orderqty;
         private Double unitPrice;
         private Double discount;
         private Double cost;
/*
   public OrderDetails(String medicineID, Integer qty, Double unitprice, Double discount, Double total) {
    }*/

    public OrderDetails(String medicineOrderID, String medicineID, Integer orderqty, Double unitPrice, Double discount, Double cost) {
        this.medicineOrderID = medicineOrderID;
        this.medicineID = medicineID;
        this.orderqty = orderqty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.cost = cost;
    }

    public String getMedicineOrderID() {
        return medicineOrderID;
    }

    public void setMedicineOrderID(String medicineOrderID) {
        this.medicineOrderID = medicineOrderID;
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public Integer getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(Integer orderqty) {
        this.orderqty = orderqty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "medicineOrderID='" + medicineOrderID + '\'' +
                ", medicineID='" + medicineID + '\'' +
                ", orderqty=" + orderqty +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(medicineOrderID, that.medicineOrderID) &&
                Objects.equals(medicineID, that.medicineID) &&
                Objects.equals(orderqty, that.orderqty) &&
                Objects.equals(unitPrice, that.unitPrice) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineOrderID, medicineID, orderqty, unitPrice, discount, cost);
    }
}
