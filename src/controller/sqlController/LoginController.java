package controller.sqlController;

import DataBase.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController{
    public String getUserType(String UserName,String Password) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT roId FROM User WHERE uName=? AND Password=sha1(?)");
        stm.setObject(1,UserName);
        stm.setObject(2,Password);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT RoType FROM Rolle WHERE RoId=?");
            preparedStatement.setObject(1,rst.getString(1));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               return resultSet.getString(1);
            }
        }
          return null;
        }

}
