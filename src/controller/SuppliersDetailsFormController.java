package controller;

import com.jfoenix.controls.JFXTextField;
import controller.sqlController.OrderController;
import controller.sqlController.SupplierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.OrderDetails;
import model.SupplierDetails;
import view.Tm.OrderDetailsTm;
import view.Tm.SupplierDetailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuppliersDetailsFormController {
    public AnchorPane SupplierDetailsPane;
    public TableView tblSuppliersDetails;
    public TableColumn colMedicineType;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colSupId;
    public TableColumn colMediId;
    public JFXTextField txtSearchSupDetails;
    public Button butSave;
    public Button butDelete;
    public Button butModify;

    public void BackToSupplierTable(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SuppliersForm.fxml");
        Parent load = FXMLLoader.load(resource);
        SupplierDetailsPane.getChildren().clear();
        SupplierDetailsPane.getChildren().add(load);
    }

    public void initialize() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colMediId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colMedicineType.setCellValueFactory(new PropertyValueFactory<>("mediType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        //.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("mQty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            setTblOrderDetails(new SupplierController().getAllOrderDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void setTblOrderDetails(ArrayList<SupplierDetails> supplierDetails) {
        ObservableList<SupplierDetailsTm> obList = FXCollections.observableArrayList();
        supplierDetails.forEach(e->{
            obList.add(
                    new SupplierDetailsTm(e.getSupId(),e.getMedicineId(),e.getDate(),e.getTime(),e.getMediType(),e.getPrice(),e.getAmount(),e.getmQty()));
        });
        tblSuppliersDetails.setItems(obList);
    }


    public void SavePatients(ActionEvent actionEvent) {}

    public void DeletePatients(ActionEvent actionEvent) {}



    public void SearchDetails(ActionEvent actionEvent) {}


    public void KeyReleasedValidation(KeyEvent keyEvent) {
    }
}
