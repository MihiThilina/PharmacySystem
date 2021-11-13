package controller.sqlController;

import DataBase.DbConnection;
import model.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDetailsController {
    public ArrayList<UserDetails> getAllUser() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User");
        ResultSet rst = stm.executeQuery();
        ArrayList<UserDetails> user = new ArrayList<>();
        while (rst.next()) {
            user.add(new UserDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return user;
    }

    public boolean refreshUser(UserDetails e1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE User SET Password=?, uType=?,uName=?  WHERE  roId=?");
        stm.setObject(1,e1.getPassword());
        stm.setObject(2,e1.getType());
        stm.setObject(3,e1.getUserName());
        stm.setObject(4,e1.getRoleID());


        return stm.executeUpdate()>0;
    }

    public boolean newUser(UserDetails e1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO User VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(4,e1.getRoleID());
        stm.setObject(1,e1.getPassword());
        stm.setObject(2,e1.getType());
        stm.setObject(3,e1.getUserName());


        return stm.executeUpdate()>0;
    }

    public UserDetails getUser(String ep) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM User WHERE roId=?");
        stm.setObject(1,ep);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new UserDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

        } else {
            return null;
        }
    }

    public boolean RemoveUser(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM User WHERE roId='"+text+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
}
