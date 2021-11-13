
package controller;

import Util.AlertMassage;
import Util.Validation;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import controller.sqlController.EmployeeController;
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


import model.Supplier;

import view.Tm.PatientsTm;
import view.Tm.SupplierTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SuppliersFormController {
    public AnchorPane SupplierPane;
    public TableView<SupplierTm>tblsuppliers;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public TableColumn colCompanyName;
    public TableColumn colPhoneNumber;
    public JFXTextField txtSupId;
    public JFXTextField txtSupName;
    public JFXTextField txtSupPhone;
    public JFXTextField txtSupCompany;
    static ArrayList<Supplier> SupplierList = new ArrayList();
    public JFXTextField txtSearchSup;
    public Button btnSave;
    public Label lblSupID;
    public Label lblCompany;
    public Label lblName;
    public Label lblPhone;

    SupplierController supplierController = new SupplierController();

    LinkedHashMap<JFXTextField, Pattern> allValidation =new LinkedHashMap();
    Pattern idpattern = Pattern.compile("^(S-)[000-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern companyName = Pattern.compile("^[A-z ]{3,20}$");
    Pattern phoneNumber = Pattern.compile("^(0)[0-9][-]?[0-9]{8}$");

    private  void ValidateInt(){
        btnSave.setDisable(false);
        allValidation.put(txtSupId,idpattern);
        allValidation.put(txtSupName,namePattern);
        allValidation.put(txtSupCompany,companyName);
        allValidation.put(txtSupPhone,phoneNumber);
    }


    public void SupplierKeyReleased(KeyEvent keyEvent) {
      if(txtSupId.getText().trim().isEmpty() && txtSupName.getText().trim().isEmpty()) {
          lblSupID.setText("Invalid Id ");
          lblName.setText("Invalid User Name ");

      }else{

      }
        Object response = Validation.validate(allValidation, btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }

    public void initialize() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        loadAllSuppliers();
        ValidateInt();

    }

    private void loadAllSuppliers(){

        SupplierList=supplierController.getAllSupplier();

        for (Supplier temp : SupplierList ) {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #ff0000; ");
            btn.setAlignment(Pos.BOTTOM_CENTER);
            obList.add(
                    new SupplierTm(temp.getSupplierID(),temp.getSupName(),temp.getCompanyName(),temp.getContactNo(),btn)
            );
            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this Customer?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == yes) {
                    SupplierList.remove(temp);
                    loadAllSuppliers();
                } else {

                }
            });
            tblsuppliers.setItems(obList);
        }
    }

    ObservableList<SupplierTm> obList = FXCollections.observableArrayList();



    public void SearchSupplier(ActionEvent actionEvent) {
        search(txtSupId.getText());
    }

    private void search(String value) {

        List<Supplier> supplierList = null;
        try {
            supplierList = SupplierController.searchSupplier(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<SupplierTm> supplierTm = FXCollections.observableArrayList();
            Button btn = new Button("Delete");
            /*for (Supplier supplier : supplierList) {
                supplierList.add(new SupplierTm(
                        supplier.getSupplierID(),
                        supplier.getContactNo(),
                        supplier.getCompanyName(),
                        supplier.getSupName()

                ));
            }*/
            // Set Data to  table
            tblsuppliers.getItems().setAll(supplierTm);
    }


    public void ViewSupplierDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SuppliersDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        SupplierPane.getChildren().clear();
        SupplierPane.getChildren().add(load);
    }


    public void SaveSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(txtSupId.getText(),
                txtSupName.getText(),txtSupCompany.getText(),txtSupPhone.getText());
        if (new SupplierController().saveSupplier(supplier)){
            loadAllSuppliers();
            //setPrescriptionToTable(new PrescriptionController().getAllPrescription());

            new AlertMassage().successMassage("Save Successful");
        }else{
            new AlertMassage().errorMassage("Waring");
        }
    }

    public void DeleteSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String  SuppId = null;
        Supplier s1 = new SupplierController().getSupplier(SuppId);
        if (new SupplierController().deleteSupplier(txtSupId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void ModifySupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(txtSupId.getText(),txtSupCompany.getText(),
                txtSupName.getText(),txtSupPhone.getText());
        if( new SupplierController().updateSupplier(supplier)){
            loadAllSuppliers();
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }


    public void OntbaleDataClick(MouseEvent mouseEvent) {
        if(tblsuppliers.getSelectionModel().getSelectedItem()!=null){
            SupplierTm c=tblsuppliers.getSelectionModel().getSelectedItem();
            txtSupId.setText(c.getSupplierID());
            txtSupCompany.setText(c.getCompanyName());
            txtSupName.setText(c.getSupName());
            txtSupPhone.setText(c.getContactNo());
        }

    }
}
