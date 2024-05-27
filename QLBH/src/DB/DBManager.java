/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
/**
 *
 * @author ktkun
 */
public class DBManager {
    private static Logger l = Logger.getLogger(DataBase.class.getName());
    
    public static ResultSet GetData(String sql){
        DataBase db = new DataBase();
        db.Connect();
        return db.GetData(sql);
    }
    
    public static void Update(String sql){
        DataBase db = new DataBase();
        db.Connect();
        db.Update(sql);
        db.Disconnect();
    }
    
    public static boolean Table(JTable j, String sql){
        ResultSet rs = GetData(sql);
        if(rs==null)
            return false;
        try{
            DefaultTableModel dtm = (DefaultTableModel)j.getModel();
            dtm.setRowCount(0);
            
            ResultSetMetaData rd = rs.getMetaData();
            int end = rd.getColumnCount();
            
            while(rs.next()){
                Object[] o = new Object[end];
                for(int i=0;i<end;i++){
                    o[i] = rs.getString(i+1);
                }
                dtm.addRow(o);
            }
            return true;
        }catch(SQLException ex){
            l.log(Level.SEVERE, null, ex);
            return false;
        }finally{
            DataBase db = new DataBase();
            db.Disconnect();
        }
    }
    
     public static boolean kt(String sql){
         ResultSet rs;    
         try{
                rs=GetData(sql);
                return rs.next();
         }catch(SQLException ex){
             l.log(Level.SEVERE, null, ex);
             return false;
         }finally{
             DataBase db = new DataBase();
             db.Disconnect();
         }

     }     
     
public static boolean Table1(JTable j, String sql) {
    ResultSet rs = GetData(sql);
    if (rs == null)
        return false;
    try {
        // Tạo một DefaultTableModel mới
        DefaultTableModel dtm = new DefaultTableModel();

        // Thêm model mới vào JTable
        j.setModel(dtm);

        // Thêm dữ liệu từ ResultSet vào bảng một cách tự động
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // Thêm tên cột vào DefaultTableModel
        for (int i = 1; i <= columnCount; i++) {
            dtm.addColumn(metaData.getColumnLabel(i));
        }
        // Thêm dữ liệu từ ResultSet vào DefaultTableModel
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            dtm.addRow(rowData);
        }

        // Đóng ResultSet
        rs.close();

        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        DataBase db = new DataBase();
        db.Disconnect();
    }
}
     
}
