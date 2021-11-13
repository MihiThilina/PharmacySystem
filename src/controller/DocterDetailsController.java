package controller;

import controller.sqlController.DocterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.DocterDetails;
import view.Tm.DocterDetailsTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocterDetailsController {
    public TableView tblDocter;
    public TableColumn colDocterId;
    public AnchorPane NewDocterPane;
    public TableColumn colPriscriptionId;
    public TableColumn colDate;
    public TableColumn colTime;


    public void initialize() {
        colDocterId.setCellValueFactory(new PropertyValueFactory<>("docterID"));
        colPriscriptionId.setCellValueFactory(new PropertyValueFactory<>("paid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        try {
            setTblDocterDetails(new DocterController().getAllDocterDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setTblDocterDetails(ArrayList<DocterDetails> docterDetails) {
        ObservableList<DocterDetailsTm> obList = FXCollections.observableArrayList();
        docterDetails.forEach(e->{
            obList.add(
                    new DocterDetailsTm(e.getDocterID(),e.getPaid(),e.getDate(),e.getTime()));
        });
        tblDocter.setItems(obList);
    }



    public void ontbleClick(MouseEvent mouseEvent) {}

    public void BackToDocterInformation(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DocterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        NewDocterPane.getChildren().clear();
        NewDocterPane.getChildren().add(load);
    }
}


