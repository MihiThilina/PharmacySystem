package model;

public class DailyData {
    private String time;
    private double cost;

    public DailyData() {
    }

    public DailyData(String time, double cost) {
        this.time = time;
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
