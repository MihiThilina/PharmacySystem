package model;

public class AnnuallyDataModel {

    private String date;
    private double cost;

    public AnnuallyDataModel() {
    }

    public AnnuallyDataModel(String date, double cost) {
        this.date = date;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
