package view.Tm;

public class SupplierIdTm {
    private String supId;
    private String name;


    public SupplierIdTm() {
    }

    public SupplierIdTm(String supId, String name) {
        this.setSupId(supId);
        this.setName(name);
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
