package model;

public class Prescriptions {
    private String paId;
    private String drId;
    private String medicing;
    private String time;
    private String date;

    public Prescriptions() {
    }

    public Prescriptions(String paId, String drId, String medicing, String time, String date) {
        this.setPaId(paId);
        this.setDrId(drId);
        this.setMedicing(medicing);
        this.setTime(time);
        this.setDate(date);
    }


    public String getPaId() {
        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getMedicing() {
        return medicing;
    }

    public void setMedicing(String medicing) {
        this.medicing = medicing;
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
        return "Prescriptions{" +
                "paId='" + paId + '\'' +
                ", drId='" + drId + '\'' +
                ", medicing='" + medicing + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
