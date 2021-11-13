

package controller.sqlController;
import DataBase.DbConnection;
import model.DocterDetails;
import model.NewDocter;
import model.NewDocterDetails;
import model.SupplierDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewDoctorController {
  public boolean saveNewDocter(String id) throws SQLException, ClassNotFoundException {
      PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO NewDocter VALUES" + "(?)");
      stm.setString(1,id);
      return stm.executeUpdate()>0;
  }
    public boolean saveNewDocterDetails(DocterDetails d1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO  `NewDocter Details` VALUES (?,?,?,?)");
        stm.setString(1,d1.getDocterID());
        stm.setString(2,d1.getPaid());
        stm.setString(3,d1.getDate());
        stm.setString(4,d1.getTime());

        return stm.executeUpdate()>0;

    }

}







