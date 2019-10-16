package hago.gosport;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    Connection conn=null;
    public Connection ConnectDB()
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://SQL5035.site4now.net/DB_A4A489_GoSport","DB_A4A489_GoSport_admin","abdo41Body2019");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //يستخدم لللاضافة و التعديل و الحذف
    public String RUNDML(String st)
    {
        try {
            Statement connStatement=conn.createStatement();
            connStatement.executeUpdate(st);
            return "Ok";
        } catch (SQLException e) {
             return e.getMessage();
        }

    }
    //to search
    public ResultSet RunSearch(String st)
    {
        ResultSet bola;
        try {
            Statement connStatement=conn.createStatement();
         bola= connStatement.executeQuery(st);
            return  bola;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
