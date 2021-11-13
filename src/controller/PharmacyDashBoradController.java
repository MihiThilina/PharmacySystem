

package controller;
import com.jfoenix.controls.JFXButton;
import controller.sqlController.IncomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.AnnuallyDataChart;
import view.DailyChartReports;
import view.MonthlyChartData;


import java.io.IOException;
import java.net.URL;

public class PharmacyDashBoradController {
    public AnchorPane PanePharmacyDashBorad;
    public AnchorPane mainPane;
    public JFXButton butEmployee;
    public JFXButton butSuppliers;
    public JFXButton butMedicine;
    public Pane MenuBar;
    public PieChart movablePieChart;
    public LineChart dailyChart;
    public LineChart monthChart;
    public LineChart annualChart;
    public Label lblDailyIcome;
    public Label lblMonthlyIncome;
    public Label lblAnnualyIncome;


   /* public void initialize() throws SQLException, ClassNotFoundException {

        ArrayList<MovableData> data = PiechartController.getItemMovableData();

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

        for (MovableData data1 :data) {
            list.add(new PieChart.Data(
                    data1.getMedicineOrderID(),
                    data1.getQuantity()
            ));
        }

        movablePieChart.setData(list);



    }*/


    public void initialize(){


        loadDailyChart();
        loadMonthlyChart();
        loadAnnuallyChart();
       //loadAnnuallyChart();

    }

    private void loadDailyChart() {
        DailyChartReports dailyChartData = IncomeController.getDailyIncome();
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("2H",dailyChartData.getHour2()));
        series.getData().add(new XYChart.Data<>("4H",dailyChartData.getHour4()));
        series.getData().add(new XYChart.Data<>("6H",dailyChartData.getHour6()));
        series.getData().add(new XYChart.Data<>("8H",dailyChartData.getHour8()));
        series.getData().add(new XYChart.Data<>("10H",dailyChartData.getHour10()));
        series.getData().add(new XYChart.Data<>("12H",dailyChartData.getHour12()));
        series.getData().add(new XYChart.Data<>("14H",dailyChartData.getHour14()));
        series.getData().add(new XYChart.Data<>("16H",dailyChartData.getHour16()));
        series.getData().add(new XYChart.Data<>("18H",dailyChartData.getHour18()));
        series.getData().add(new XYChart.Data<>("20H",dailyChartData.getHour20()));
        series.getData().add(new XYChart.Data<>("22H",dailyChartData.getHour22()));
        series.getData().add(new XYChart.Data<>("24H",dailyChartData.getHour24()));

        dailyChart.getData().add(series);
        lblDailyIcome.setText(dailyChartData.getTotal());

    }


    private void loadMonthlyChart() {
        MonthlyChartData monthlyChartData = IncomeController.getMonthlyIncome();

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("Week 1",monthlyChartData.getWeek1()));
        series.getData().add(new XYChart.Data<>("Week 2",monthlyChartData.getWeek2()));
        series.getData().add(new XYChart.Data<>("Week 3",monthlyChartData.getWeek3()));
        series.getData().add(new XYChart.Data<>("Week 4",monthlyChartData.getWeek4()));
        monthChart.getData().add(series);
        lblMonthlyIncome.setText(monthlyChartData.getTotal());
    }

    private void loadAnnuallyChart() {
        AnnuallyDataChart annuallyChartData = IncomeController.getAnnuallyIncome();
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("January",annuallyChartData.getMonth1()));
        series.getData().add(new XYChart.Data<>("February",annuallyChartData.getMonth2()));
        series.getData().add(new XYChart.Data<>("March",annuallyChartData.getMonth3()));
        series.getData().add(new XYChart.Data<>("April",annuallyChartData.getMonth4()));
        series.getData().add(new XYChart.Data<>("May",annuallyChartData.getMonth5()));
        series.getData().add(new XYChart.Data<>("June",annuallyChartData.getMonth6()));
        series.getData().add(new XYChart.Data<>("July",annuallyChartData.getMonth7()));
        series.getData().add(new XYChart.Data<>("August",annuallyChartData.getMonth8()));
        series.getData().add(new XYChart.Data<>("September",annuallyChartData.getMonth9()));
        series.getData().add(new XYChart.Data<>("October",annuallyChartData.getMonth10()));
        series.getData().add(new XYChart.Data<>("November",annuallyChartData.getMonth11()));
        series.getData().add(new XYChart.Data<>("December",annuallyChartData.getMonth12()));

        annualChart.getData().add(series);

       lblAnnualyIncome.setText(annuallyChartData.getTotal());

    }


    public void showEmployeeDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeesForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void ShowSuppliersDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SuppliersForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }
    public void showMedicineDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MedicineForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }
    public void showUserDetils(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }
    public void showDocterDetils(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DocterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }
    public void showLoginFace(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) mainPane.getScene().getWindow();
        window.setScene(new Scene(load));
        window.show();
    }

    public void openDashBoard(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PharmacyDashBorad.fxml");
        Parent load = FXMLLoader.load(resource);
        PanePharmacyDashBorad.getChildren().clear();
        PanePharmacyDashBorad.getChildren().add(load);
    }

    public void showNewDocterDetils(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/NewDocter.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);

    }
}
