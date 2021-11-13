package model;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private String morderID;
    private String priscriptionId;
    private String time;
    private String date;
    private double cost;
    private ArrayList<OrderDetails> medicine;

    public Order() {}

    public Order(String morderID, String priscriptionId, String time, String date,double cost) {
        this.morderID = morderID;
        this.priscriptionId = priscriptionId;
        this.time = time;
        this.date = date;
        this.cost= cost;
    }

    public Order(String morderID, String priscriptionId, String time, String date, double cost, ArrayList<OrderDetails> medicine) {
        this.morderID = morderID;
        this.priscriptionId = priscriptionId;
        this.time = time;
        this.date = date;
        this.cost = cost;
        this.medicine = medicine;
    }

    public double getCost() { return cost; }

    public void setCost(double cost) { this.cost = cost;}


    public String getMorderID() {
        return morderID;
    }

    public void setMorderID(String morderID) {
        this.morderID = morderID;
    }

    public String getPriscriptionId() {
        return priscriptionId;
    }

    public void setPriscriptionId(String priscriptionId) {
        this.priscriptionId = priscriptionId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<OrderDetails> getMedicine() {
        return medicine;
    }

    public void setMedicine(ArrayList<OrderDetails> medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "Order{" +
                "morderID='" + morderID + '\'' +
                ", priscriptionId='" + priscriptionId + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", medicine=" + medicine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(morderID, order.morderID) &&
                Objects.equals(priscriptionId, order.priscriptionId) &&
                Objects.equals(time, order.time) &&
                Objects.equals(date, order.date) &&
                Objects.equals(medicine, order.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(morderID, priscriptionId, time, date, medicine);
    }

}
