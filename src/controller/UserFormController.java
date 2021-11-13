package controller;

import Util.AlertMassage;
import Util.Validation;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.UserDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.UserDetails;
import view.Tm.SupplierTm;
import view.Tm.UserDitailsTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UserFormController {
    public JFXTextField txtRoleID;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public Button butSave;
    public Button butDelete;
    public Button butModify;
    public TableView<UserDitailsTm>tblUser;
    public JFXTextField txtType;
    public TableColumn colType;
    public TableColumn colRoleId;
    public TableColumn colPassword;
    public TableColumn colname;

    LinkedHashMap<JFXTextField, Pattern> allValidation =new LinkedHashMap();
    Pattern idpattern = Pattern.compile("^(R-)[0000-9]{3,4}$");
    Pattern password = Pattern.compile("^[0-9]{5}$");
    Pattern type = Pattern.compile("^[A-z ]{3,20}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");

    public void KeyReleasedOnValidation(KeyEvent keyEvent) {
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
    private  void ValidateInt(){
        butSave.setDisable(false);
        allValidation.put(txtRoleID,idpattern);
        allValidation.put(txtPassword,password);
        allValidation.put(txtType,type);
        allValidation.put(txtUserName,namePattern);
    }

    public void initialize(){
        ValidateInt();
        colRoleId.setCellValueFactory(new PropertyValueFactory<>("roleID"));
        colRoleId.setStyle("-fx-alignment: center");
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPassword.setStyle("-fx-alignment: center");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colType.setStyle("-fx-alignment: center");
        colname.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colname.setStyle("-fx-alignment: center");

        try {
            setUserToTable(new UserDetailsController().getAllUser());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setUserToTable(ArrayList<UserDetails> user) {
        ObservableList<UserDitailsTm> obList = FXCollections.observableArrayList();
        user.forEach(e->{ obList.add(
                new UserDitailsTm(e.getRoleID(),e.getPassword(),e.getType(),e.getUserName()));
        });
        tblUser.setItems(obList);
    }




    public void ModifyEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserDetails e1= new UserDetails(
                txtRoleID.getText(),txtUserName.getText(),txtType.getText(),txtPassword.getText()

        );
        if (new UserDetailsController().refreshUser(e1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clear();
    }
    public void clear(){
        txtRoleID.clear();
        txtUserName.clear();
        txtType.clear();
        txtPassword.clear();
    }

    public void rolrIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtRoleID.getText();
        UserDetails e1= new UserDetailsController().getUser(customerId);
        if (e1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(e1);
        }
        txtType.requestFocus();

    }

    private void setData(UserDetails e1) {
        txtRoleID.setText(e1.getRoleID());
        txtType.setText(e1.getType());
        txtUserName.setText(e1.getUserName());
        txtPassword.setText(e1.getPassword());
    }


    public void SaveEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserDetails e1= new UserDetails(
                txtRoleID.getText(),txtPassword.getText(),txtType.getText(),txtUserName.getText()
        );
        if(new UserDetailsController().newUser(e1)) {
            setUserToTable(new UserDetailsController().getAllUser());
            new AlertMassage().successMassage("Save Successful");
        }else{
            new AlertMassage().errorMassage("Waring");
        }
        clear();

    }

    public void DeleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new UserDetailsController().RemoveUser(txtRoleID.getText())){
            setUserToTable(new UserDetailsController().getAllUser());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clear();
    }


    public void ontableClick(MouseEvent mouseEvent) {
        if(tblUser.getSelectionModel().getSelectedItem()!=null){
            UserDitailsTm c=tblUser.getSelectionModel().getSelectedItem();
            txtRoleID.setText(c.getRoleID());
            txtPassword.setText(c.getPassword());
            txtType.setText(c.getType());
            txtUserName.setText(c.getUserName());
        }
    }
}
