package controller.sqlController;

import DataBase.DbConnection;
import model.MovableData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PiechartController {


    public static ArrayList<MovableData> getItemMovableData() throws SQLException {
        ArrayList<MovableData> data = new ArrayList<>();

        ResultSet resultSet = null;
        try {
            resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT DISTINCT MId FROM Medicine").executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (resultSet.next()) {
            ResultSet resultSet1 = null;
            try {
                resultSet1 = DbConnection.getInstance().getConnection().prepareStatement("SELECT SUM(Orderqty) FROM `Order Details` WHERE MoId='" + resultSet.getString(1) + "'").executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (resultSet1.next()) {
                data.add(new MovableData(
                        resultSet.getString(1),
                        Integer.parseInt(resultSet1.getString(1))
                ));
            }
        }
        return data;

    }
}
