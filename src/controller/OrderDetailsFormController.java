package controller;

import DataBase.DbConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.OrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.OrderDetails;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.Tm.OrderDetailsTm;
import view.Tm.OrderTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsFormController {
    public TableView<OrderDetailsTm>tblOrderDetails;
    public TableColumn colMOrderID;
    public TableColumn colMedicineID;
    public TableColumn colOrderQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colCost;
    public JFXTextField txtSearchPre;


    public void initialize() {
        colMOrderID.setCellValueFactory(new PropertyValueFactory<>("medicineOrderID"));
        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderqty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        try {
            setTblOrderDetails(new OrderController().getAllOrderDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTblOrderDetails(ArrayList<OrderDetails> orderDetails) {
        ObservableList<OrderDetailsTm> obList = FXCollections.observableArrayList();
        orderDetails.forEach(e->{
            obList.add(
                    new OrderDetailsTm(e.getMedicineOrderID(),e.getMedicineID(),e.getOrderqty(),e.getUnitPrice(),e.getDiscount(),e.getCost()));
        });
        tblOrderDetails.setItems(obList);
    }

    public void SearchPrescription(ActionEvent actionEvent) {}

   /* public void DeleteOrderDetails(ActionEvent actionEvent) {
        if(tblOrderDetails.getSelectionModel().getSelectedItem() != null){
          //  OrderTm temp = tblOrderDetails.getSelectionModel().getSelectedItem();
            if(new OrderController().deleteOrder(//temp.getMorderID())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Selected Row..").show();
        }


    }*/

    public void openReports(MouseEvent mouseEvent) {
    }

    public void DeleteOrderDetails(ActionEvent actionEvent) {
    }

   /* public void openReports(MouseEvent mouseEvent) {
        try {

            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/Reports/Details1.jrxml"));
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
    }*/
}

