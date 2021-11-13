package controller.sqlController;
import DataBase.DbConnection;
import model.EmployeesDetails;
import model.OrderDetails;
import model.Supplier;
import model.SupplierDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {

    public boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Supplier VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, s.getSupplierID());
        stm.setObject(2, s.getSupName());
        stm.setObject(3, s.getCompanyName());
        stm.setObject(4, s.getContactNo());
        return stm.executeUpdate() > 0;
    }

    public boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Supplier SET ContactNo=?,CompanyName=?,SuppName=? WHERE SuppId=?");
        stm.setObject(4, s.getSupplierID());
        stm.setObject(3, s.getSupName());
        stm.setObject(2, s.getCompanyName());
        stm.setObject(1, s.getContactNo());
        return stm.executeUpdate() > 0;
    }

    public Supplier getSupplier(String s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier WHERE SuppId =?");
        stm.setObject(1, s);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );
        } else {
            return null;
        }
    }
    public static List<Supplier> searchSupplier(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Supplier WHERE CompanyName LIKE '%"+value+"%'");
        ResultSet rst = pstm.executeQuery();
        List<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            suppliers.add(new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return suppliers;
    }



    public ArrayList<SupplierDetails> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Supplier Details`");
        ResultSet rst = stm.executeQuery();
        ArrayList<SupplierDetails> details = new ArrayList<>();
        while (rst.next()) {
            details.add(new SupplierDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getInt(8)
            ));
        }

        return details;
    }





    public boolean deleteSupplier(String i) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Supplier WHERE SuppId='" + i + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }



    public ArrayList<Supplier> getAllSupplier() {
        PreparedStatement stm = null;
        try {
            stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier");
            ResultSet rst = stm.executeQuery();
            ArrayList<Supplier> Sup = new ArrayList<>();
            while (rst.next()) {
                Sup.add(new Supplier(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4)
                ));
            }
            return Sup;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public List<String> loadSupplierIds() throws SQLException, ClassNotFoundException{
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

}



