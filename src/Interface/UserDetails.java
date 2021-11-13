package Interface;

import model.EmployeesDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDetails {
    public boolean saveEmployee(EmployeesDetails e) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeesDetails e) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(EmployeesDetails i) throws SQLException, ClassNotFoundException;
    public EmployeesDetails getEmployee(String i) throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeesDetails> getAllEmployee() throws SQLException, ClassNotFoundException;
    public List<String> loadEmployeeIds() throws SQLException, ClassNotFoundException;
}
