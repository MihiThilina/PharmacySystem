package controller;

import DataBase.DbConnection;
import Util.Validation;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.protocol.Resultset;
import controller.sqlController.EmployeeController;
import controller.sqlController.MedicineController;
import controller.sqlController.OrderController;
import controller.sqlController.SupplierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.EmployeesDetails;
import model.Medicine;
import model.Supplier;
import view.Tm.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class MedicineFormController {
    public AnchorPane MedicinePane;
    public TextField txtMedicId;
    public TextField txtMedicName;
    public TextField txtUnit;
    public TextField txtExDate;
    public TextField txtMedicQty;
    public ComboBox cmbCategory;
    public TextField txtPrice;
    public TableView<MedicineTm>tblMedicine;
    public TableColumn colMediID;
    public TableColumn colMediName;
    public TableColumn colUnit;
    public TableColumn colQty;
    public TableColumn colManufacutre;
    public TableColumn colExdate;
    public TableColumn colCategory;
    public TableColumn colPrice;
    public TableColumn colExprired;
    public TextField txtManufactured;
    public JFXTextField txtSearchMedi;
    static ArrayList<Medicine> medicines = new ArrayList<>();
    public ComboBox cmbSupplier1;
    public TableView tblSupplier;
    public TableColumn colSupplier;
    public Label LblSupplierName;
    public Label lblMassage;
    public Label lblDate;
    public Label lblTime;
    public Button butSave;
    public TableColumn colGrams;

    MedicineController medicineController = new MedicineController();

    LinkedHashMap<TextField, Pattern> allValidation =new LinkedHashMap();
    Pattern idpattern = Pattern.compile("^(P-)[0000-9]{3,4}$");
    Pattern qty = Pattern.compile("^[0-9]{5}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern date =Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]2]");
    Pattern unit =Pattern.compile  ("^[0-9][mg]");
    Pattern exdate =Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]2]");
    Pattern Price = Pattern.compile("^[A-z ]{3,20}$");
    private KeyEvent keyEvent;

    private  void ValidateInt(){
        butSave.setDisable(false);
        allValidation.put(txtMedicId,idpattern);
        allValidation.put(txtMedicName,namePattern);
        allValidation.put(txtMedicQty,qty);
        allValidation.put(txtManufactured,date);
        allValidation.put(txtUnit,unit);
        allValidation.put(txtExDate,exdate);
        allValidation.put(txtPrice,Price);

    }



   /* public void PressKeys() {
        Object response = Validation.validateTextField(allValidation, butSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }*/



    public void initialize() {
        MedicineCategory();
        ValidateInt();


        lblMassage.setVisible(false);

        colSupplier.setCellValueFactory(new PropertyValueFactory<>("name"));

        colMediID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colMediName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        colGrams.setCellValueFactory(new PropertyValueFactory<>("gram"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("medicinemQty"));
        colManufacutre.setCellValueFactory(new PropertyValueFactory<>("medicineManufacutre"));
        colExdate.setCellValueFactory(new PropertyValueFactory<>("medicineExDate"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("medicineCategory"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("medicinePrice"));
        colExprired.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {

            loadMedicine();
            loadAllMedicine();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSupplier1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println((String) newValue);
            try {
                lblMassage.setVisible(false);
                setSupplerName((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



    }

    public void  setSupplerName(String id) throws SQLException, ClassNotFoundException {
       PreparedStatement pst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier WHERE SuppId='"+id+"'");
        ResultSet rst = pst.executeQuery();
        if(rst.next()){
            LblSupplierName.setText(rst.getString(2));
        }
    }

    private void loadMedicine() throws SQLException, ClassNotFoundException {
        List<String> medicineIds = new SupplierController().loadSupplierIds();
        cmbSupplier1.getItems().addAll(medicineIds);
    }


    private void loadAllMedicine() throws SQLException, ClassNotFoundException {
        ObservableList<MedicineTm> obList = FXCollections.observableArrayList();
        medicines=medicineController.getAllMedicine();
        for (Medicine temp : medicines ) {
            Button btn = new Button("Expire");
            btn.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            btn.setStyle("-fx-background-color: #ff0000; ");

            btn.setAlignment(Pos.BOTTOM_CENTER);
            obList.add(
                    new MedicineTm(temp.getMedicineId(),temp.getMedicineName(),temp.getmUnit(),temp.getMedicinemQty(),
                            temp.getMedicineManufacutre(),temp.getMedicineExDate(),temp.getMedicineCategory(),temp.getMedicinePrice(),btn)
            );
            System.out.println(temp.getmUnit());
            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this Customer?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == yes) {

                    medicines.remove(temp);

                } else {
                    try {
                        loadAllMedicine();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
            });
            tblMedicine.setItems(obList);
        }
    }


    ObservableList<SupplierIdTm> supplierIdTms = FXCollections.observableArrayList();

    public void AddSupplierId(ActionEvent actionEvent) {
        SupplierIdTm supplierIdTm = new SupplierIdTm((String) cmbSupplier1.getValue(),LblSupplierName.getText());
        for (SupplierIdTm temp : supplierIdTms) {
            if(cmbSupplier1.getValue().equals(temp.getSupId())){
                lblMassage.setVisible(true);
                return;
            }

        }
        supplierIdTms.add(supplierIdTm);
         tblSupplier.setItems(supplierIdTms);
    }




    public void MedicineCategory(){
        cmbCategory.getItems().addAll(
                "Alcohol","Opioids","Benzodiazepines","Cannabis","Barbiturates");
    }

    public void AddMedicineAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        double amount = Double.parseDouble(txtPrice.getText())*Integer.parseInt(txtMedicQty.getText());
        ArrayList<SupplierIdTm> supplierTms = new ArrayList<>();
        for (SupplierIdTm temp: supplierIdTms) {
            supplierTms.add(new SupplierIdTm(
                    temp.getSupId(),temp.getName()
            ));
        }
        Medicine m1 = new Medicine(
               txtMedicId.getText(),txtMedicName.getText(),txtUnit.getText(),
                Integer.parseInt(txtMedicQty.getText()),txtManufactured.getText(),txtExDate.getText(),(String) cmbCategory.getValue(),Double.parseDouble(txtPrice.getText()),supplierTms
        );
        if( new MedicineController().saveMedicine(m1,lblDate.getText(),lblTime.getText(),amount)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void DeleteMedicineAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String MId= null;
        Medicine m1 = new MedicineController().getMedicine(MId);
        if (new EmployeeController().deleteEmployee(txtMedicId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void ModifyMedicineAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Medicine m1 = new Medicine(
         txtMedicId.getText(),txtMedicName.getText(),txtUnit.getText(),Integer.parseInt(txtMedicQty.getText()),txtManufactured.getText(),txtExDate.getText(),(String) cmbCategory.getValue(),Double.parseDouble(txtPrice.getText())
        );
        if( new MedicineController().updateMedicine(m1)){
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
    public void setData(Medicine m){
        txtMedicId.setText(m.getMedicineId());
        txtMedicName.setText(m.getMedicineName());
        txtUnit.setText(m.getmUnit());
        txtExDate.setText(m.getMedicineExDate());
        txtMedicQty.setText(String.valueOf(m.getMedicinemQty()));
        txtManufactured.setText(m.getMedicineManufacutre());
        cmbCategory.setValue(String.valueOf(m.getMedicineCategory()));
        txtPrice.setText(String.valueOf(m.getMedicinePrice()));
    }


    public void ShowexpiredMedi(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ExpiredForm.fxml");
        Parent load = FXMLLoader.load(resource);
        MedicinePane.getChildren().clear();
        MedicinePane.getChildren().add(load);
    }



    public void AddToExpiredTable(ActionEvent actionEvent) {}

    public void SearchMedicine(ActionEvent actionEvent) {
        search(txtSearchMedi.getText());
    }

    private void search(String value) {
        List<Medicine> medicines = null;
        try {
            medicines = MedicineController.searchMedicine(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<MedicineTm> tableData = FXCollections.observableArrayList();
        for (Medicine m :medicines){
            tableData.add(new MedicineTm(
                    m.getMedicineId(),
                    m.getMedicineName(),
                    m.getmUnit(),
                    m.getMedicinemQty(),
                    m.getMedicineManufacutre(),
                    m.getMedicineExDate(),
                    m.getMedicineCategory(),
                    m.getMedicinePrice()
            ));
        }
        // Set Data to  table
        tblMedicine.getItems().setAll(tableData);
    }

    public void PressKeys(KeyEvent keyEvent) {
    }

    public void ontableClick(MouseEvent mouseEvent) {
    }
}
