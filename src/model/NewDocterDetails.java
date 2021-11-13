package model;

public class NewDocterDetails {
    private String docterID;
    private String paid;
    private String date;
    private String time;

    public NewDocterDetails() {}

    public NewDocterDetails(String docterID, String paid, String date, String time) {
        this.setDocterID(docterID);
        this.setPaid(paid);
        this.setDate(date);
        this.setTime(time);
    }


    public String getDocterID() {
        return docterID;
    }

    public void setDocterID(String docterID) {
        this.docterID = docterID;
    }

    public String getPaid() {
        return paid;
    }


    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}



