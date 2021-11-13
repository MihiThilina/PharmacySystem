package view.Tm;

import model.Order;

import java.util.Objects;

public class OrderTm {
    private String morderID;
    private String priscriptionId;
    private String time;
    private String date;
    private double cost;


    public OrderTm() {
    }

    public OrderTm(String morderID, String priscriptionId, String time, String date, double cost) {
        this.morderID = morderID;
        this.priscriptionId = priscriptionId;
        this.time = time;
        this.date = date;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

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

    @Override
    public String toString() {
        return "Order{" +
                "morderID='" + morderID + '\'' +
                ", priscriptionId='" + priscriptionId + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTm orderTm = (OrderTm) o;
        return Objects.equals(morderID, orderTm.morderID) &&
                Objects.equals(priscriptionId, orderTm.priscriptionId) &&
                Objects.equals(time, orderTm.time) &&
                Objects.equals(date, orderTm.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(morderID, priscriptionId, time, date);
    }
}
