
package controller;

import Util.Validation;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.DocterController;
import controller.sqlController.EmployeeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Doctor;
import view.Tm.DocterTm;
import view.Tm.UserDitailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DocterFormController {
    public JFXTextField txtDoctersID;
    public JFXTextField txtDocterName;
    public JFXTextField txtDocterNumber;
    public AnchorPane DocterPane;
    public JFXTextField txtDocterType;
    public TableView<DocterTm>tblDocter;
    public TableColumn colDocterId;
    public TableColumn DocterName;
    public TableColumn DocterType;
    public TableColumn colContactNumber;
    public Button butsave;


    LinkedHashMap<JFXTextField, Pattern> allValidation =new LinkedHashMap();
    Pattern idpattern = Pattern.compile("^(D-)[0000-9]{3,4}$");
    Pattern conactnumber = Pattern.compile("[0][0-9]{9}");
    Pattern type = Pattern.compile("^[A-z ]{3,20}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");

    public void KeyReleasedOnValidation(KeyEvent keyEvent) {
        Object response = Validation.validate(allValidation, butsave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }
    private  void ValidateInt(){
        butsave.setDisable(false);
        allValidation.put(txtDoctersID,idpattern);
        allValidation.put(txtDocterNumber,conactnumber);
        allValidation.put(txtDocterType,type);
        allValidation.put(txtDocterName,namePattern);
    }

    public void initialize() {
        ValidateInt();
        colDocterId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        DocterName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DocterType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
        try {
            setDocterToTable(new DocterController().getAllDoctor());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDocterToTable(ArrayList<Doctor> doctors) {
        ObservableList<DocterTm> obList = FXCollections.observableArrayList();
        doctors.forEach(e->{
            obList.add(
                    new DocterTm(e.getDoctorId(),e.getName(),e.getType(),e.getContact()));
        });
        tblDocter.setItems(obList);
    }

    public void SaveDocter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor d1 = new Doctor(
                txtDoctersID.getText(),txtDocterName.getText(),txtDocterType.getText(),txtDocterNumber.getText()
        );
        if( new DocterController().saveDoctor(d1)){
            new Alert(Alert.AlertType.CONFIRMATION, "Save..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "This id already added").show();
        }
    }

    public void DeleteDocter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String DoctorId= null;
        Doctor e1 = new DocterController().getDoctor(DoctorId);
        if (new DocterController().deleteDoctor(txtDoctersID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void ModifyDocter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor d1 = new Doctor(
                txtDoctersID.getText(),txtDocterName.getText(),txtDocterType.getText(),txtDocterNumber.getText()
        );
        if( new DocterController().updateDoctor(d1)){
            setDocterToTable(new DocterController().getAllDoctor());
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
    public void setData(Doctor e){
        txtDoctersID.setText(e.getDoctorId());
        txtDocterName.setText(e.getName());
        txtDocterType.setText(e.getType());
        txtDocterNumber.setText(e.getContact());
    }


    public void ontbleClick(MouseEvent mouseEvent) {
        if(tblDocter.getSelectionModel().getSelectedItem()!=null){
            DocterTm c=tblDocter.getSelectionModel().getSelectedItem();
            txtDoctersID.setText(c.getDoctorId());
            txtDocterName.setText(c.getName());
            txtDocterType.setText(c.getType());
            txtDocterNumber.setText(c.getContact());
        }
    }

    public void OpenNewDocter(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DocterDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        DocterPane.getChildren().clear();
        DocterPane.getChildren().add(load);
    }
}
