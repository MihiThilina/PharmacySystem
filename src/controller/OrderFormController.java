package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.OrderController;
import controller.sqlController.PatientsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Order;
import model.Patients;
import view.Tm.OrderTm;
import view.Tm.PatientsTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderFormController {
    public AnchorPane OrderPane;
    public TableView<OrderTm> tblOrder;
    public TableColumn colPriscriptionID;
    public TableColumn colMoID;
    public TableColumn colTotal;
    public TableColumn colDate;


    public void initialize() {

        colPriscriptionID.setCellValueFactory(new PropertyValueFactory<>("PriscriptionId"));
        colMoID.setCellValueFactory(new PropertyValueFactory<>("morderID"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            setToTableAllOrder(new OrderController().getAllOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void OrderDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(tblOrder.getSelectionModel().getSelectedItem() != null){
            OrderTm temp = tblOrder.getSelectionModel().getSelectedItem();
            if(new OrderController().deleteOrder(temp.getMorderID())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Selected Row..").show();
        }


    }


    public  void  setToTableAllOrder(ArrayList<Order> orders){
        ObservableList<OrderTm> observableList = FXCollections.observableArrayList();
        orders.forEach(e->{
            observableList.add(new OrderTm(e.getMorderID(),e.getPriscriptionId(),e.getTime(),e.getDate(),e.getCost()));

        });
        tblOrder.setItems(observableList);
    }

    public void setTableDataMouseClicked(MouseEvent mouseEvent) {

    }
}


