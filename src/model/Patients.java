package model;

public class Patients {
     private String paId;
     private String roId;
     private String dorId;
     private String paName;
     private String paAddress;
     private String paContactNo;
     private String disease;

    public Patients() {
    }

    public Patients(String paId, String roId, String dorId, String paName, String paAddress, String paContactNo, String disease) {
        this.setPaId(paId);
        this.setRoId(roId);
        this.setDorId(dorId);
        this.setPaName(paName);
        this.setPaAddress(paAddress);
        this.setPaContactNo(paContactNo);
        this.setDisease(disease);
    }


    public String getPaId() {
        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String getRoId() {
        return roId;
    }

    public void setRoId(String roId) {
        this.roId = roId;
    }

    public String getDorId() {
        return dorId;
    }

    public void setDorId(String dorId) {
        this.dorId = dorId;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public String getPaAddress() {
        return paAddress;
    }

    public void setPaAddress(String paAddress) {
        this.paAddress = paAddress;
    }

    public String getPaContactNo() {
        return paContactNo;
    }

    public void setPaContactNo(String paContactNo) {
        this.paContactNo = paContactNo;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "Patients{" +
                "paId='" + paId + '\'' +
                ", roId='" + roId + '\'' +
                ", dorId='" + dorId + '\'' +
                ", paName='" + paName + '\'' +
                ", paAddress='" + paAddress + '\'' +
                ", paContactNo='" + paContactNo + '\'' +
                ", disease='" + disease + '\'' +
                '}';
    }
}
