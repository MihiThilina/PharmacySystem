package model;

public class MonthlyData {
    private String date;
    private Double cost;

    public MonthlyData() {}

    public MonthlyData(String date, Double cost) {
        this.date = date;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

}
