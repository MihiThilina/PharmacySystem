
package controller;

import DataBase.DbConnection;
import Util.Validation;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.EmployeesDetails;
import controller.sqlController.EmployeeController;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.Tm.EmployeeTm;
import view.Tm.PatientsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeesFormController {
    public AnchorPane EmployeePane;
    public TableView<EmployeeTm>tblEmployee;
    public TableColumn colEmpNo;
    public TableColumn colName;
    public TableColumn colPhoneNo;
    public TableColumn colType;
    public TableColumn colDrithOfBrith;
    public TableColumn colNic;
    public TableColumn colGender;
    public TableColumn colAddress;
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public JFXTextField txtNum;
    public JFXTextField txtEmpType;
    public JFXTextField txtDateofBirth;
    public JFXTextField txtNic;
    public JFXTextField txtEmpAddress;
    public JFXTextField txtGender;
    public Button butSave;
    public Button butDelete;
    public Button butModify;
    public JFXTextField txtSearchEmp;

    LinkedHashMap<JFXTextField, Pattern> allValidation =new LinkedHashMap();
    Pattern idpattern = Pattern.compile("^(E-)[0000-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern address = Pattern.compile("^[A-z ]{3,20}$");
    Pattern PhoneNumber = Pattern.compile("^(0)[0-9][-]?[0-9]{8}$");
    Pattern nic =Pattern.compile("^[0-9]{9}[V]");
    Pattern birthday = Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]2]");
    Pattern employeType = Pattern.compile("^[A-z ]{3,20}$");


    private  void ValidateInt(){
        butSave.setDisable(false);
        allValidation.put(txtEmpId,idpattern);
        allValidation.put(txtEmpName,namePattern);
        allValidation.put(txtEmpAddress,address);
        allValidation.put(txtNum,PhoneNumber);
        allValidation.put(txtNic,nic);
        allValidation.put(txtDateofBirth,birthday);
        allValidation.put(txtEmpType,employeType);
    }

    public void validateOnkeyPressed(KeyEvent keyEvent) {
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
        try {
            colEmpNo.setCellValueFactory(new PropertyValueFactory<>("roleID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("rolePhoneNum"));
            colType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("roleAddress"));
            colDrithOfBrith.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            colNic.setCellValueFactory(new PropertyValueFactory<>("roleNic"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            setEmployeesToTable(new EmployeeController().getAllEmployee());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void setEmployeesToTable(ArrayList<EmployeesDetails> items) {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        items.forEach(e->{
            obList.add(
                    new EmployeeTm(e.getRoleID(),e.getName(),e.getRolePhoneNum(),e.getRoleType(),e.getRoleAddress(),e.getDateOfBirth(),e.getRoleNic(),e.getGender()));
        });
        tblEmployee.setItems(obList);
    }

    public void AddNewEmployee(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/NewEmployeeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        EmployeePane.getChildren().clear();
        EmployeePane.getChildren().add(load);
    }

    public void SaveEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeesDetails e1 = new EmployeesDetails(
                txtEmpId.getText(),txtEmpName.getText(),txtNum.getText(),
                txtEmpType.getText(),txtEmpAddress.getText(),txtDateofBirth.getText(),
                txtNic.getText(),txtGender.getText()
        );
        if( new EmployeeController().saveEmployee(e1)){
            setEmployeesToTable(new EmployeeController().getAllEmployee());
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "This id already added").show();        }
    }

    public void DeleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String RoleID= null;
        EmployeesDetails e1 = new EmployeeController().getEmployee(RoleID);
        if (new EmployeeController().deleteEmployee(txtEmpId.getText())){
            setEmployeesToTable(new EmployeeController().getAllEmployee());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    public void ModifyEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeesDetails e1 = new EmployeesDetails(
                txtEmpId.getText(),txtEmpName.getText(),txtNum.getText(),
                txtEmpType.getText(),txtEmpAddress.getText(),txtDateofBirth.getText(),
                txtNic.getText(),txtGender.getText()
        );
        if( new EmployeeController().updateEmployee(e1)){
            setEmployeesToTable(new EmployeeController().getAllEmployee());
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else{
           new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
    public void setData(EmployeesDetails e1){
        txtEmpId.setText(e1.getRoleID());
        txtEmpName.setText(e1.getName());
        txtNum.setText(e1.getRolePhoneNum());
        txtEmpType.setText(e1.getRoleType());
        txtEmpAddress.setText(e1.getRoleAddress());
        txtDateofBirth.setText(e1.getDateOfBirth());
        txtNic.setText(e1.getRoleNic());
        txtGender.setText(e1.getGender());
    }
    public void SearchFullName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String EmpId = txtEmpId.getText();
        EmployeesDetails e1= new EmployeeController().getEmployee(EmpId);
        if(e1==null){

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else{
            setData(e1);
        }
       // txtEmpId.requestFocus();
    }

    public void SearchEmployee(ActionEvent actionEvent) {
        search(txtSearchEmp.getText());
    }

    private void search(String value) {
        List<EmployeesDetails> employeesDetails = null;
        try {
            employeesDetails = EmployeeController.searchEmployee(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<EmployeeTm> tableData = FXCollections.observableArrayList();
            for (EmployeesDetails details :employeesDetails){
                tableData.add(new EmployeeTm(
                        details.getRoleID(),
                        details.getName(),
                        details.getRolePhoneNum(),
                        details.getRoleType(),
                        details.getRoleAddress(),
                        details.getDateOfBirth(),
                        details.getRoleNic(),
                        details.getGender()
                ));
            }
            // Set Data to  table
            tblEmployee.getItems().setAll(tableData);
    }

    public void setTableDataMouseClicked(MouseEvent mouseEvent) {
        if(tblEmployee.getSelectionModel().getSelectedItem()!=null){
            EmployeeTm c=tblEmployee.getSelectionModel().getSelectedItem();
            txtEmpId.setText(c.getRoleID());
            txtEmpName.setText(c.getName());
            txtNum.setText(c.getRolePhoneNum());
            txtEmpType.setText(c.getRoleType());
            txtEmpAddress.setText(c.getRoleAddress());
            txtDateofBirth.setText(c.getDateOfBirth());
            txtNic.setText(c.getRoleNic());
            txtGender.setText(c.getGender());
        }
    }


    public void ViewReports(ActionEvent actionEvent) {
        try {

            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/UserDetails.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

