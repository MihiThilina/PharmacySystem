package controller;
import Util.Validation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.DocterController;
import controller.sqlController.EmployeeController;
import controller.sqlController.PatientsController;
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
import model.Patients;
import view.Tm.PatientsTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class PatientsFormController {
    public AnchorPane PatientsPane;
    public TableView<PatientsTm>tblPatients;
    public TableColumn colPatientsID;
    public TableColumn colRoleId;
    public TableColumn colDocterId;
    public TableColumn colPatientsName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDisease;
    public JFXTextField txtPatientsID;
    public JFXTextField txtPatientsName;
    public JFXTextField txtPatiNumber;
    public JFXTextField txtPatieAddress;
    public JFXTextField txtDisease;
    public JFXComboBox<String>cmbDocterId;
    public JFXComboBox<String>cmbRoleID;
    public Button butModify;
    public Button butSave;
    public Button butDelete;


    LinkedHashMap<JFXTextField, Pattern> allValidation =new LinkedHashMap();
    Pattern idpattern = Pattern.compile("^(P-)[0000-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern address = Pattern.compile("^[A-z ]{3,20}$");
    Pattern PhoneNumber = Pattern.compile("^(0)[0-9][-]?[0-9]{8}$");
    Pattern disease = Pattern.compile("^[A-z ]{3,20}$");


    private  void ValidateInt(){
        butSave.setDisable(false);
        allValidation.put(txtPatientsID,idpattern);
        allValidation.put(txtPatientsName,namePattern);
        allValidation.put(txtPatieAddress,address);
        allValidation.put(txtPatiNumber,PhoneNumber);
        allValidation.put(txtDisease,disease);
    }





    public void KeyReleasedOnActionValidation(KeyEvent keyEvent) {
       Object response = Validation.validate(allValidation, butSave);

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
        ValidateInt();
        colPatientsID.setCellValueFactory(new PropertyValueFactory<>("paId"));
        colRoleId.setCellValueFactory(new PropertyValueFactory<>("roId"));
        colDocterId.setCellValueFactory(new PropertyValueFactory<>("dorId"));
        colPatientsName.setCellValueFactory(new PropertyValueFactory<>("paName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("paAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("paContactNo"));
        colDisease.setCellValueFactory(new PropertyValueFactory<>("disease"));

        try {
            setPatientsToTable(new PatientsController().getAllPatients());
            loadEmployeeIds();
            loadDoctorIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    private void setPatientsToTable(ArrayList<Patients> customer) {
        ObservableList<PatientsTm> obList = FXCollections.observableArrayList();
        customer.forEach(e->{
            obList.add(
                    new PatientsTm(e.getPaId(),e.getRoId(),e.getDorId(),e.getPaName(),e.getPaAddress(),e.getPaContactNo(),e.getDisease()));
        });
        tblPatients.setItems(obList);
    }

   public void loadEmployeeIds() throws SQLException, ClassNotFoundException {
        List<String>EmployeeIds = new EmployeeController().loadEmployeeIds();
        cmbRoleID.getItems().addAll(EmployeeIds);
   }
   public void loadDoctorIds() throws SQLException, ClassNotFoundException {
        List<String> DoctorIds= new DocterController().loadDoctorIds();
        cmbDocterId.getItems().addAll(DoctorIds);
   }



    public void SearchPatients(ActionEvent actionEvent) {}

    public void AddNewPatients(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/NewPatientsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        PatientsPane.getChildren().clear();
        PatientsPane.getChildren().add(load);
    }


    public void SavePatients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Patients p1 = new Patients(
             txtPatientsID.getText(),(String) cmbRoleID.getValue(),(String) cmbDocterId.getValue(),
                txtPatientsName.getText(),txtPatieAddress.getText(),txtPatiNumber.getText(),
                txtDisease.getText()
        );
        if( new PatientsController().savePatients(p1)){
            setPatientsToTable(new PatientsController().getAllPatients());
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "This id already added").show();
        }
    }

  public void DeletePatients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String PatientsID= null;
        Patients c1 = new PatientsController().getPatients(PatientsID);
        if (new PatientsController().deletePatients(txtPatientsID.getText())){
            setPatientsToTable(new PatientsController().getAllPatients());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void ModifyPatients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Patients p1 = new Patients(
                txtPatientsID.getText(),(String) cmbRoleID.getValue(),(String) cmbDocterId.getValue(),
                txtPatientsName.getText(),txtPatieAddress.getText(),txtPatiNumber.getText(),
                txtDisease.getText()
        );
        if( new PatientsController().updatePatients(p1)){
            setPatientsToTable(new PatientsController().getAllPatients());
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }




    public void setTableDataMouseClicked(MouseEvent mouseEvent) {
        if(tblPatients.getSelectionModel().getSelectedItem()!=null){
            PatientsTm c=tblPatients.getSelectionModel().getSelectedItem();
            txtPatientsID.setText(c.getPaId());
            cmbRoleID.setValue(String.valueOf(c.getRoId()));
            cmbDocterId.setValue(String.valueOf(c.getDorId()));
            txtPatientsName.setText(c.getPaName());
            txtPatieAddress.setText(c.getPaAddress());
            txtPatiNumber.setText(c.getPaContactNo());
            txtDisease.setText(c.getDisease());

        }
    }
}
