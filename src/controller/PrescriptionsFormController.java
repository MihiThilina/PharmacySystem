
package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.DocterController;
import controller.sqlController.PrescriptionsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Prescriptions;
import view.Tm.PrescriptionsTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PrescriptionsFormController {
    public JFXComboBox cmbDoctorId;
    public JFXTextField txtMedicingName;
    public JFXTextField txtDate;
    public JFXTextField txtTime;
    public AnchorPane PrescriptionsPane;
    public JFXTextField txtPrescriptionID;
    public TableView tbPrescriptionl;
    public TableColumn colPrescriptionID;
    public TableColumn colDoctorID;
    public TableColumn colMediName;
    public TableColumn colTime;
    public TableColumn colDate;
    public JFXTextField txtSearchPre;



    public void initialize() {
        colPrescriptionID.setCellValueFactory(new PropertyValueFactory<>("paId"));
        colDoctorID.setCellValueFactory(new PropertyValueFactory<>("drId"));
        colMediName.setCellValueFactory(new PropertyValueFactory<>("medicing"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            setPrescriptionsToTable(new PrescriptionsController().getAllPrescriptions());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPrescriptionsToTable(ArrayList<Prescriptions> prescriptions) {
        ObservableList<PrescriptionsTm> obList = FXCollections.observableArrayList();
        prescriptions.forEach(e->{
            obList.add(
                    new PrescriptionsTm(e.getPaId(),e.getDrId(),e.getMedicing(),e.getTime(),e.getDate()));
        });
        tbPrescriptionl.setItems(obList);
    }

    public void DeletePrescrtiption(ActionEvent actionEvent) {


    }

    public void SearchPrescription(ActionEvent actionEvent) {

    }

    public void SavePrescrtiption(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Prescriptions p1 = new Prescriptions(
                txtPrescriptionID.getText(),(String) cmbDoctorId.getValue(),txtMedicingName.getText(),
                txtTime.getText(),txtDate.getText()
        );
        if( new PrescriptionsController().savePresctriptions(p1)){
            setPrescriptionsToTable(new PrescriptionsController().getAllPrescriptions());
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }


}
