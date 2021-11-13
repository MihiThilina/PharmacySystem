package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane LoginPane;
    public Button butLogin;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Label lblWrongLogIn;

    public void initialize(){

    }


    public void openDashBord(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

       String user = new LoginController().getUserType(txtUserName.getText(),txtPassword.getText());
        if (user == null){
            lblWrongLogIn.setText("Please enter a valid password.");
        }else {
            if (user.equals("Admin")) {
                URL resource = getClass().getResource("../view/PharmacyDashBorad.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) LoginPane.getScene().getWindow();
                window.setScene(new Scene(load));
                window.show();
            }else if(user.equals("Cashier")){
                URL resource = getClass().getResource("../view/CashierBoradForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) LoginPane.getScene().getWindow();
                window.setScene(new Scene(load));
                window.show();
            } else {
            if (user.equals("Doctor")) {
                URL resource = getClass().getResource("../View/DocterDashBoard.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) LoginPane.getScene().getWindow();
                window.setScene(new Scene(load));
                window.show();
            }
        }
        }
    }
}
