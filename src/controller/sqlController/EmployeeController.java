package controller.sqlController;
import DataBase.DbConnection;
import model.EmployeesDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController{

    public boolean saveEmployee(EmployeesDetails e) throws SQLException, ClassNotFoundException{
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT 1 FROM " + "Rolle WHERE RoId=?");
        statement.setString(1,e.getRoleID());
        ResultSet rst=statement.executeQuery();
        if(rst.next()){
            return false;
        }
       Connection con = DbConnection.getInstance().getConnection();
       String query="INSERT INTO  Rolle VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,e.getRoleID());
        stm.setObject(2,e.getRoleType());
        stm.setObject(3,e.getRoleNic());
        stm.setObject(4,e.getDateOfBirth());
        stm.setObject(5,e.getGender());
        stm.setObject(6,e.getRolePhoneNum());
        stm.setObject(7,e.getName());
        stm.setObject(8,e.getRoleAddress());
        return stm.executeUpdate()>0;
    }



    public boolean deleteEmployee(String i) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Rolle WHERE RoId='"+i+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }


    public ArrayList<EmployeesDetails> getAllEmployee() throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Rolle");
        ResultSet rst = stm.executeQuery();
        ArrayList<EmployeesDetails> Employee = new ArrayList<>();
        while (rst.next()){
            Employee.add(new EmployeesDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return Employee;

    }
    public static List<EmployeesDetails> searchEmployee(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Rolle WHERE RoName  LIKE '%"+value+"%' || RoNic LIKE '%"+value+"%' ");
        ResultSet rst = pstm.executeQuery();
        List<EmployeesDetails> employeesDetails = new ArrayList<>();
        while (rst.next()) {
            employeesDetails.add(new EmployeesDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }

        return employeesDetails;
    }

    public List<String> loadEmployeeIds() throws SQLException, ClassNotFoundException{
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Rolle").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public boolean updateEmployee(EmployeesDetails e1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Rolle SET RoType=?,RoNic=?,DateofBirth=?,Gender=?, RoPhoneNo=?,RoName=?,RoAddress=? WHERE RoId=?");

        stm.setObject(1,e1.getRoleType());
        stm.setObject(2,e1.getRoleNic());
        stm.setObject(3,e1.getDateOfBirth());
        stm.setObject(4,e1.getGender());
        stm.setObject(5,e1.getRolePhoneNum());
        stm.setObject(6,e1.getName());
        stm.setObject(7,e1.getRoleAddress());
        stm.setObject(8,e1.getRoleID());
        return stm.executeUpdate()>0;
    }

    public EmployeesDetails getEmployee(String empId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm =  DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Rolle WHERE RoId =?");
        stm.setObject(1,empId);

        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return new EmployeesDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            );
        }else{
            return null;
        }
    }
}



