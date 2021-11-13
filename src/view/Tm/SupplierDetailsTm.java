package view.Tm;

public class SupplierDetailsTm {
    private String supId;
    private String medicineId;
    private String date;
    private String time;
    private String mediType;
    private double price;
    private double amount;
    private int mQty;

    public SupplierDetailsTm() {
    }

    public SupplierDetailsTm(String supId, String medicineId, String date, String time, String mediType, double price, double amount, int mQty) {
        this.setSupId(supId);
        this.setMedicineId(medicineId);
        this.setDate(date);
        this.setTime(time);
        this.setMediType(mediType);
        this.setPrice(price);
        this.setAmount(amount);
        this.setmQty(mQty);
    }


    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
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

    public String getMediType() {
        return mediType;
    }

    public void setMediType(String mediType) {
        this.mediType = mediType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getmQty() {
        return mQty;
    }

    public void setmQty(int mQty) {
        this.mQty = mQty;
    }

    @Override
    public String toString() {
        return "SupplierDetailsTm{" +
                "supId='" + supId + '\'' +
                ", medicineId='" + medicineId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", mediType='" + mediType + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", mQty=" + mQty +
                '}';
    }
}
