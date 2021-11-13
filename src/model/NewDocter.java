package model;

public class NewDocter {
    private String doctorId;
    private String name;
    private String type;
    private String contact;

    public NewDocter() {
    }

    public NewDocter(String doctorId, String name, String type, String contact) {
        this.doctorId = doctorId;
        this.name = name;
        this.type = type;
        this.contact = contact;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
