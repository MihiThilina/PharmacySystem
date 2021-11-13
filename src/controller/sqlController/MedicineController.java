package controller.sqlController;

import DataBase.DbConnection;
import model.Medicine;
import view.Tm.SupplierIdTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineController {


   /*public boolean saveMedicine(Medicine m) throws SQLException, ClassNotFoundException{
       Connection con = DbConnection.getInstance().getConnection();
       String query = "INSERT INTO Medicine VALUES (?,?,?,?,?,?,?,?)";
       PreparedStatement stm = con.prepareStatement(query);
       stm.setObject(1,m.getMedicineId());
       stm.setObject(2,m.getMedicineName());
       stm.setObject(3,m.getmUnit());
       stm.setObject(4,m.getMedicinemQty());
       stm.setObject(5,m.getMedicineManufacutre());
       stm.setObject(6,m.getMedicineExDate());
       stm.setObject(7,m.getMedicineCategory());
       stm.setObject(8,m.getMedicinePrice());
       return stm.executeUpdate() > 0;
   }
*/

   public  boolean saveMedicine(Medicine m,String date ,String time,double amount){

       Connection con = null;
       try {
           con=DbConnection.getInstance().getConnection();
           con.setAutoCommit(false);
           PreparedStatement stm = con.prepareStatement("INSERT INTO Medicine VALUES (?,?,?,?,?,?,?,?)");
           stm.setObject(1,m.getMedicineId());
           stm.setObject(2,m.getMedicineName());
           stm.setObject(3,m.getmUnit());
           stm.setObject(4,m.getMedicinemQty());
           stm.setObject(5,m.getMedicineManufacutre());
           stm.setObject(6,m.getMedicineExDate());
           stm.setObject(7,m.getMedicineCategory());
           stm.setObject(8,m.getMedicinePrice());
           if(stm.executeUpdate() > 0){
               if (saveSupplierDetails(m.getSupplierTms(),m.getMedicineId(),date,time,m.getMedicineCategory(),m.getMedicinePrice(),amount,m.getMedicinemQty())){
                   con.commit();
                 return  true;
               }else {
                   con.rollback();
                   return  false;
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

   public  boolean saveSupplierDetails(ArrayList<SupplierIdTm> arrayList ,String mid,String date,String time ,String coterogry,double price,double amount ,int qty) throws SQLException, ClassNotFoundException {
       for (SupplierIdTm tm : arrayList) {
           PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Supplier Details` VALUES (?,?,?,?,?,?,?,?)");
           stm.setObject(1,tm.getSupId());
           stm.setObject(2,mid);
           stm.setObject(3,date);
           stm.setObject(4,time);
           stm.setObject(5,coterogry);
           stm.setObject(6,price);
           stm.setObject(7,amount);
           stm.setObject(8,qty);
           return stm.executeUpdate()>0;

       }
       return false;
   }






    public boolean updateMedicine(Medicine m) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Medicine SET supID=? mName=?,MfgDate=?,ExDate=?,mQty=?,Manufacutre=?,Category=?,Price=? WHERE MId=?");
        stm.setObject(1,m.getMedicineId());
        stm.setObject(2,m.getMedicineName());
        stm.setObject(3,m.getmUnit());
        stm.setObject(4,m.getMedicinemQty());
        stm.setObject(5,m.getMedicineManufacutre());
        stm.setObject(6,m.getMedicineExDate());
        stm.setObject(7,m.getMedicineCategory());
        stm.setObject(8,m.getMedicinePrice());
        return stm.executeUpdate()>0;
    }
    public static List<Medicine> searchMedicine(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Medicine WHERE mName LIKE '%"+value+"%' || ExDate LIKE '%"+value+"%' ");
        ResultSet rst = pstm.executeQuery();
        List<Medicine> medicines = new ArrayList<>();
        while (rst.next()) {
            medicines.add(new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8)
            ));
        }

        return medicines;
    }


   public Medicine getMedicine(String e) throws SQLException, ClassNotFoundException{
       PreparedStatement stm =  DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Medicine WHERE MId=?");
       stm.setObject(1,e);
       ResultSet rst = stm.executeQuery();
       if(rst.next()){
           return new Medicine(
                   rst.getString(1),
                   rst.getString(2),
                   rst.getString(3),
                   rst.getInt(4),
                   rst.getString(5),
                   rst.getString(6),
                   rst.getString(7),
                   rst.getDouble(8)
           );
       }else{
           return null;
       }
   }

   public Medicine getExMedicine(String e) throws SQLException, ClassNotFoundException{
        PreparedStatement stm =  DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Medicine WHERE ExDate=?");
        stm.setObject(1,e);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            return new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8)
            );
        }else{
            return null;
        }
    }

    public boolean deleteMedicine(String i) throws SQLException, ClassNotFoundException{
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Medicine WHERE MId='"+i+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }




    public ArrayList<Medicine> getAllMedicine() {
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Medicine");
            ResultSet rst = stm.executeQuery();
            ArrayList<Medicine> Medicine = new ArrayList<>();
            while (rst.next()) {
                Medicine.add(new Medicine(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7),
                        rst.getDouble(8)
                ));
            }
            return Medicine;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    return  null;
}
    public List<String> loadMedicine() throws SQLException, ClassNotFoundException{
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Medicine").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

}