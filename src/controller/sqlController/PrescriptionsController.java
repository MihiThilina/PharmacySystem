package controller.sqlController;
import DataBase.DbConnection;
import model.Prescriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionsController {

    public boolean savePresctriptions(Prescriptions e) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Priscription VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, e.getPaId());
        stm.setObject(2, e.getMedicing());
        stm.setObject(3, e.getTime());
        stm.setObject(4, e.getDate());
        return stm.executeUpdate() > 0;
    }
    public boolean updatePrescriptions(Prescriptions e) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Priscription SET drID=?,Medicing=?, pTime=?, pDate=? WHERE paId=?");
        stm.setObject(1, e.getPaId());
        stm.setObject(2, e.getMedicing());
        stm.setObject(3, e.getTime());
        stm.setObject(4, e.getDate());
        return stm.executeUpdate() > 0;
    }
    public boolean deletePrescriptions(Prescriptions i) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Priscription WHERE paId='" + i + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Prescriptions getPrescriptions(String i) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Priscription WHERE paId =?");
        stm.setObject(1, i);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Prescriptions(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        } else {
            return null;
        }
    }
    public ArrayList<Prescriptions> getAllPrescriptions() throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Priscription ");
        ResultSet rst = stm.executeQuery();
        ArrayList<Prescriptions> prescriptions= new ArrayList<>();
        while (rst.next()){
           prescriptions.add(new Prescriptions(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return prescriptions;
    }
    public List<String> loadPrescriptionsIds() throws SQLException, ClassNotFoundException{
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Priscription").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }




    }


