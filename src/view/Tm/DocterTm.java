package view.Tm;

import java.util.Objects;

public class DocterTm {
    private String doctorId;
    private String name;
    private String type;
    private String contact;

    public DocterTm() {
    }

    public DocterTm(String doctorId, String name, String type, String contact) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocterTm docterTm = (DocterTm) o;
        return Objects.equals(doctorId, docterTm.doctorId) &&
                Objects.equals(name, docterTm.name) &&
                Objects.equals(type, docterTm.type) &&
                Objects.equals(contact, docterTm.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, name, type, contact);
    }
}
