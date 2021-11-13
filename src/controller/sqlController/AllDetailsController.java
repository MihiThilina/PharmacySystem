package controller.sqlController;

import DataBase.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllDetailsController {

 /*public ArrayList<AllDetailsTm>getAllDetails() throws SQLException, ClassNotFoundException {
    PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Payment ");
    ArrayList<AllDetailsTm> detailsTms = new ArrayList<>();
     ResultSet set = stm.executeQuery();
     while (set.next()){
         PreparedStatement stm2 = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Appointment WHERE nIC=?");
         stm2.setObject(1,set.getString(5));
         ResultSet set2 =stm2.executeQuery();
         if(set2.next()){
             PreparedStatement stm3 = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Patients` WHERE nIC=?");
             stm3.setObject(1,set.getString(5));
             ResultSet set3 = stm3.executeQuery();
             if(set3.next()){
                 detailsTms.add(new AllDetailsTm(
                     set3.getString(9),
                     set.getString(5),
                     set3.getString(2),
                     set3.getString(3),
                     set3.getString(8),
                     set2.getString(1),
                         set2.getString(6),
                         set.getDouble(6)
                 ));
             }
         }
     }
     return detailsTms;
 }*/

    
}
