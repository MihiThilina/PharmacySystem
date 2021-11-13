package controller.sqlController;
import DataBase.DbConnection;
import model.EmployeesDetails;
import model.Patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PatientsController {
     public boolean savePatients(Patients p) throws SQLException, ClassNotFoundException{
         PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT 1 FROM " +
                 "Patients WHERE paId=?");
         statement.setString(1,p.getPaId());
         ResultSet rst=statement.executeQuery();
         if(rst.next()){
             return false;
         }
         Connection con = DbConnection.getInstance().getConnection();
         String query = "INSERT INTO Patients VALUES (?,?,?,?,?,?,?)";
         PreparedStatement stm = con.prepareStatement(query);
         stm.setObject(1, p.getPaId());
         stm.setObject(2, p.getRoId());
         stm.setObject(3, p.getDorId());
         stm.setObject(4, p.getPaName());
         stm.setObject(5, p.getPaAddress());
         stm.setObject(6, p.getPaContactNo());
         stm.setObject(7, p.getDisease());
         return stm.executeUpdate() > 0;
     }
   public boolean updatePatients(Patients p) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Patients SET roId=?,DorId=?,paName=?,paAddress=?, paContactNo=?,Disease=? WHERE paId=?");
        stm.setObject(1, p.getRoId());
       stm.setObject(2, p.getDorId());
       stm.setObject(3, p.getPaName());
       stm.setObject(4, p.getPaAddress());
       stm.setObject(5, p.getPaContactNo());
       stm.setObject(6, p.getDisease());
       stm.setObject(7, p.getPaId());
        return stm.executeUpdate()>0;
    }
    public boolean deletePatients(String i) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Patients WHERE paId='" + i + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Patients getPatients(String i) throws SQLException, ClassNotFoundException{
        PreparedStatement stm =  DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patients WHERE paId =?");
        stm.setObject(1,i);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return new Patients(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }else{
            return null;
        }
    }
   public ArrayList<Patients> getAllPatients() throws SQLException, ClassNotFoundException{
       PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patients");
       ResultSet rst = stm.executeQuery();
       ArrayList<Patients> Patients = new ArrayList<>();
       while (rst.next()){
           Patients.add(new Patients(
                   rst.getString(1),
                   rst.getString(2),
                   rst.getString(3),
                   rst.getString(4),
                   rst.getString(5),
                   rst.getString(6),
                   rst.getString(7)
           ));
       }
       return Patients;
   }

   public List<String> loadPatientsIds() throws SQLException, ClassNotFoundException{
       ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patients").executeQuery();
       List<String> ids = new ArrayList<>();
       while (rst.next()){
           ids.add(
                   rst.getString(1)
           );
       }
       return ids;
   }
   }

