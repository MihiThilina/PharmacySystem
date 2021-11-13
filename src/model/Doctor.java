package model;

public class Doctor {
    private String doctorId;
    private String name;
    private String type;
    private String contact;

    public Doctor() {
    }

    public Doctor(String doctorId, String name, String type, String contact) {
        this.setDoctorId(doctorId);
        this.setName(name);
        this.setType(type);
        this.setContact(contact);
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

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
