/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ktkun
 */
public class DataBase {
    private Connection con = null;
    private String driver = "com.mysql.cj.jdbc.Driver", url="jdbc:mysql://localhost:3306/qlbh",user="root",pass="";
    private Logger l = Logger.getLogger(DataBase.class.getName());
    
    public DataBase(){
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException ex){
            l.log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection Connect(){
        try{
            this.con = DriverManager.getConnection(url, user, pass);
            return con;
        }catch(SQLException ex){
            l.log(Level.SEVERE, null, ex);
            if(con !=  null){
                try{
                    con.close();
                }catch(SQLException e){
                    l.log(Level.SEVERE, null, e);
                }
            }
            return null;
        }
    }
    
    public void Disconnect(){
            if(con !=  null){
                try{
                    con.close();
                }catch(SQLException e){
                    l.log(Level.SEVERE, null, e);
                }
            }        
    }
    
    public ResultSet GetData(String sql){
        ResultSet rs = null;
        try{
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException ex){
            l.log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
     public int Update(String sql){
        int kq = -1;
        try{
            Statement st = con.createStatement();
            kq = st.executeUpdate(sql);
        }catch(SQLException ex){
            l.log(Level.SEVERE, null, ex);
        }
        return kq;
    }   
}
