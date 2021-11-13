


package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.sqlController.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import org.controlsfx.control.textfield.TextFields;
import view.Tm.MedicineCartTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CashierBoradFormController {
    public Pane MenuBar;
    public AnchorPane ChaierPane;
    public JFXComboBox cmbMedicineId;
    public JFXTextField txtMedicineName;
    public JFXTextField txtPrice;
    public TextField txtDiscount;
    public TextField txtQty;
    public Label lblMOrderID;
    public TableView<MedicineCartTm>tblCartTable;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtUnit;
    public TableColumn colMId;
    public TableColumn colMedicineName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public JFXTextField txtAvailableQty;
    public JFXTextField txtExdate;
    public JFXTextField txtPriscriptionId;
    public JFXTextField txtMedicing;
    public JFXTextField txtDocterId;
    public Label lblTotal;
    public AnchorPane cashierFullContext;


    int cartSelectedRowRemove = -1;



    public void initialize() throws SQLException, ClassNotFoundException {

        TextFields.bindAutoCompletion(txtDocterId,new DocterController().loadDoctorIds());

        colMId.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicinename"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAdnTime();
        try {
            SetOrderID();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadMedicine();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        cmbMedicineId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           // System.out.println((String) newValue);
            try {
                setMedicineData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
    private void loadDateAdnTime(){
       // --------- Date----------------------------------------------------
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));
        //-------------------------------------Time--------------------------
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(
                    localTime.getHour()+":"+localTime.getMinute()+":"+localTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private  void SetOrderID() throws SQLException, ClassNotFoundException {
        lblMOrderID.setText(new OrderController().getOrderID());
    }

    private void loadMedicine() throws SQLException, ClassNotFoundException {
        List<String> medicineIds = new MedicineController().loadMedicine();
        cmbMedicineId.getItems().addAll(medicineIds);
    }

    private void setMedicineData(String medicineData) throws SQLException, ClassNotFoundException {
        Medicine M1 = new MedicineController().getMedicine(medicineData);
        if (M1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set");
        }else{
            txtMedicineName.setText(M1.getMedicineName());
            txtUnit.setText(M1.getmUnit());
            txtExdate.setText((String) M1.getMedicineExDate());
            txtAvailableQty.setText(String.valueOf(M1.getMedicinemQty()));
            txtPrice.setText(String.valueOf(M1.getMedicinePrice()));

        }
    }


    public void showPayment(ActionEvent actionEvent) {}

    /* public void OpenPayment(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/Reports/PhamacyReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }*/

    public void showLogin(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) ChaierPane.getScene().getWindow();
        window.setScene(new Scene(load));
        window.show();
    }



    ObservableList<MedicineCartTm> oList = FXCollections.observableArrayList();
    public void AddToCartOnAction(ActionEvent actionEvent) {
        addToCart();
    }

    public void addToCart(){
    if(String.valueOf(txtQty.getText()).isEmpty() || Integer.parseInt(txtAvailableQty.getText())>Integer.parseInt(txtAvailableQty.getText())) {
        new Alert(Alert.AlertType.WARNING, "Try Again").show();
        return;
    }

        String medicineID = (String) cmbMedicineId.getSelectionModel().getSelectedItem();
        String medicineName =txtMedicineName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price =Double.parseDouble(txtPrice.getText());
        double discount= Double.parseDouble(txtDiscount.getText());
        double medicineTotal = Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQty.getText());


        MedicineCartTm cartTm = new MedicineCartTm(
                (String) cmbMedicineId.getSelectionModel().getSelectedItem(),
                medicineID,
                qty,
                price,
                discount,
                medicineTotal

        );
      //  oList.add(cartTm);
        //tblCartTable.setItems(oList);


       int index=  findOnTable(medicineID);
        if( index == -1){
            tblCartTable.getItems().add(cartTm);
            oList.add(cartTm);
        }else{
            MedicineCartTm cart =  tblCartTable.getItems().get(index);
            int newQty = cart.getQty()+qty;
            double newTotal= cart.getTotal() +medicineTotal;
            cart.setQty(newQty);
            cart.setTotal(newTotal);
            tblCartTable.refresh();
        }
        calculate();
     //   tblCartTable.getItems().add(cartTm);
    }




    public int findOnTable(String id){
        for (int i=0; i<tblCartTable.getItems().size(); i++){
            if(tblCartTable.getItems().get(i).getMedicineID().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void calculate(){
        double subtotal = 0;
        double total = 0;

        for(MedicineCartTm cart : tblCartTable.getItems()){
            total=cart.getTotal();
            subtotal=cart.getDiscount();
        }
        lblTotal.setText(String.valueOf(total));

    }



    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Prescriptions prescriptions = new Prescriptions(
                txtPriscriptionId.getText(),txtDocterId.getText(),txtMedicing.getText(),lblTime.getText(),lblDate.getText()
        );
        if(new PrescriptionsController().savePresctriptions(prescriptions)) {
        }else{
            return;
        }
        DocterDetails details=new DocterDetails(txtDocterId.getText(),txtPriscriptionId.getText(),lblDate.getText(),
                lblTime.getText());

         new DocterController().saveDocterDetails(details);




            ArrayList<OrderDetails> medicine = new ArrayList<>();
        for(MedicineCartTm tempTm :oList
        ){
            System.out.println(tempTm.getMedicineID()

            );
            medicine.add(
                    new OrderDetails(
                            lblMOrderID.getText(),
                            tempTm.getMedicineID(),
                            tempTm.getQty(),
                            tempTm.getUnitprice(),
                            tempTm.getDiscount(),
                            tempTm.getTotal()
                    )
            );
        }
        Order order = new Order(
                lblMOrderID.getText(),
                txtPriscriptionId.getText(),
                lblTime.getText(),
                lblDate.getText(),
                Double.parseDouble(lblTotal.getText()),
                medicine
        );
        if (new OrderController().placeOrder(order,prescriptions)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            try {
                SetOrderID();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }



    public void ClearData(ActionEvent actionEvent) {
        if(cartSelectedRowRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else{
            oList.remove(cartSelectedRowRemove);
            calculate();
            tblCartTable.refresh();
        }

    }

    public void showPriscriptionTable(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PrescriptionsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ChaierPane.getChildren().clear();
        ChaierPane.getChildren().add(load);
    }
    public void openDashBoard(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierBoradForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierFullContext.getChildren().clear();
        cashierFullContext.getChildren().add(load);
    }
    public void showPatientsDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PatientsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ChaierPane.getChildren().clear();
        ChaierPane.getChildren().add(load);
    }

    public void showMedicineOrder(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ChaierPane.getChildren().clear();
        ChaierPane.getChildren().add(load);
    }
    public void showOrderDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        ChaierPane.getChildren().clear();
        ChaierPane.getChildren().add(load);

    }

    public void KeyRelesedOnAction(KeyEvent keyEvent) {
    }

    public void UpdateOnAction(ActionEvent actionEvent) {

    }

    public void DeleteOnAction(ActionEvent actionEvent) {
    }
}

