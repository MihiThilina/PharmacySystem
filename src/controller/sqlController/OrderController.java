package controller.sqlController;

import DataBase.DbConnection;
import model.Medicine;
import model.Order;
import model.OrderDetails;
import model.Prescriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class OrderController {

        public boolean placeOrder(Order order , Prescriptions prescriptions){
            Connection con=null;
            try {
                con=DbConnection.getInstance().getConnection();
                con.setAutoCommit(false);
                PreparedStatement stm= con.prepareStatement("INSERT INTO `Medicine Order` VALUES(?,?,?,?,?)");

                stm.setObject(1,order.getMorderID());
                stm.setObject(2,order.getPriscriptionId());
                stm.setObject(3,order.getTime());
                stm.setObject(4,order.getDate());
                stm.setObject(5,order.getCost());

                if( stm.executeUpdate()>0){

                    if( saveOrderDetails(order.getMorderID(),order.getMedicine())){
                        con.commit();
                        return true;
                    }else {
                        con.rollback();
                        return false;

                    }
                }else {
                    con.rollback();
                    return false;
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return false;
        }



    public boolean deleteOrder(String i) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Medicine Order` WHERE MoId='" + i + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

        public boolean updateOrder(OrderDetails i) throws SQLException, ClassNotFoundException {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Order Details` SET  MiD=?,Orderqty=?,unitPrice=?,discount=?,cost=? WHERE MoId=?");
            stm.setObject(1,i.getMedicineOrderID());
            stm.setObject(2,i.getMedicineID());
            stm.setObject(3,i.getOrderqty());
            stm.setObject(4,i.getUnitPrice());
            stm.setObject(5,i.getDiscount());
            stm.setObject(6,i.getCost());
            return stm.executeUpdate()>0;
        }

        private boolean saveOrderDetails(String orderId, ArrayList<OrderDetails> medicines) throws SQLException, ClassNotFoundException {
            for(OrderDetails temp:medicines
            ){
                PreparedStatement stm= DbConnection.getInstance().
                        getConnection().prepareStatement("INSERT INTO `Order Details` VALUES(?,?,?,?,?,?)");
                stm.setObject(1,orderId);
                stm.setObject(2,temp.getMedicineID());
                stm.setObject(3,temp.getOrderqty());
                stm.setObject(4,temp.getUnitPrice());
                stm.setObject(5,temp.getDiscount());
                stm.setObject(6,temp.getCost());
                if(stm.executeUpdate()>0){
                    if(updateQty(temp.getMedicineID(),temp.getOrderqty())){
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
            return true;
        }


        public  OrderDetails getOrder(String Id) throws SQLException, ClassNotFoundException {
            PreparedStatement stm =DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM `Order Details` WHERE  MoId=?");
            stm.setObject(1,Id);
            ResultSet rst= stm.executeQuery();
            if(rst.next()){
                return new OrderDetails(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getDouble(4),
                        rst.getDouble(5),
                        rst.getDouble(6));

            }else{
                return null;
            }
        }

    public ArrayList<Order> getAllOrder() throws SQLException, ClassNotFoundException {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Medicine Order`");
            ResultSet rst = stm.executeQuery();
            ArrayList<Order> Order = new ArrayList<>();
            while (rst.next()) {
                Order.add(new Order(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getDouble(5)
                ));
            }

        return Order;
    }


    public ArrayList<OrderDetails> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Order Details`");
        ResultSet rst = stm.executeQuery();
        ArrayList<OrderDetails> details = new ArrayList<>();
        while (rst.next()) {
            details.add(new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6)
            ));
        }

        return details;
    }







        private boolean updateQty(String MedicineID,int qty ) throws SQLException, ClassNotFoundException {
            PreparedStatement stm= DbConnection.getInstance().getConnection().
                    prepareStatement("UPDATE Medicine SET mQty=(mQty-"+
                            qty+")WHERE MId='"+MedicineID+"'");
            return stm.executeUpdate()>0;

        }


        public String getOrderID() throws SQLException, ClassNotFoundException {
            ResultSet rst =  DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM `Medicine Order` ORDER BY MoId DESC LIMIT 1").executeQuery();
            if(rst.next()){
                int temp=Integer.parseInt(rst.getString(1).split("-")[1]);
                temp=temp+1;
                if(temp<=9){
                    return "MO-00"+temp;
                }else if(temp<99){
                    return "MO-0"+temp;
                }else{
                    return "O-"+temp;
                }

            }else {
                return "MO-0001";
            }
        }

}



