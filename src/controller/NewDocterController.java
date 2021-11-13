package controller;

import com.jfoenix.controls.JFXTextField;
import controller.sqlController.DocterController;
import controller.sqlController.NewDoctorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.NewDocter;
import view.Tm.NewDocterTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewDocterController {
    public JFXTextField txtDoctersID;
    public JFXTextField txtDocterName;
    public JFXTextField txtDocterNumber;
    public JFXTextField txtDocterType;
    public TableView<NewDocterTm>tblDocter;
    public TableColumn colDocterId;
    public TableColumn DocterName;
    public TableColumn DocterType;
    public TableColumn colContactNumber;
    public Button butsave;
    public AnchorPane NewDocterPane;



}
