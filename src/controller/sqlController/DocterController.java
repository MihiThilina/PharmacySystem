

package controller.sqlController;

import DataBase.DbConnection;
import javafx.scene.control.Alert;
import model.DocterDetails;
import model.Doctor;
import model.EmployeesDetails;
import model.SupplierDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocterController {
    public boolean saveDoctor(Doctor e) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT 1 FROM " + "Docter WHERE DrId=?");
        statement.setString(1, e.getDoctorId());
        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
            return false;
        }
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO  Docter VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, e.getDoctorId());
        stm.setObject(2, e.getName());
        stm.setObject(3, e.getType());
        stm.setObject(4, e.getContact());
        return stm.executeUpdate() > 0;
    }

    public boolean updateDoctor(Doctor e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Docter SET DrName=?,DrType=?,DrContactNo=? WHERE DrId=?");
        stm.setObject(1, e.getName());
        stm.setObject(2, e.getType());
        stm.setObject(3, e.getContact());
        stm.setObject(4, e.getDoctorId());
        return stm.executeUpdate() > 0;
    }

    public Doctor getDoctor(String e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Docter WHERE DrId =?");
        stm.setObject(1, e);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        } else {
            return null;
        }
    }

    public ArrayList<DocterDetails> getAllDocterDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Docter Details`");
        ResultSet rst = stm.executeQuery();
        ArrayList<DocterDetails> details = new ArrayList<>();
        while (rst.next()) {
            details.add(new DocterDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            ));
        }

        return details;
    }


    public boolean deleteDoctor(String i) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Docter WHERE DrId='" + i + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Doctor> getAllDoctor() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Docter");
        ResultSet rst = stm.executeQuery();
        ArrayList<Doctor> Doctor = new ArrayList<>();
        while (rst.next()) {
            Doctor.add(new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return Doctor;
    }

    public List<String> loadDoctorIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Docter").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    public boolean saveDocterDetails(DocterDetails d1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = null;
        try {
            stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO  `DocterDetails` VALUES (?,?,?,?)");
            stm.setString(1, d1.getDocterID());
            stm.setString(2, d1.getPaid());
            stm.setString(3, d1.getDate());
            stm.setString(4, d1.getTime());

            return stm.executeUpdate() > 0;
        } catch (SQLException throwables) {
            if (new NewDoctorController().saveNewDocter(d1.getDocterID())) {
                if (new NewDoctorController().saveNewDocterDetails(d1)) {
                    return true;
                } else {

                }
            }

        } return false;
    }
}





