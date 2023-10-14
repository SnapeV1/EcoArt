package MyConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karra
 */
public class MyConnection {
   static  String url="jdbc:mysql://localhost:3306/javaproject";
    static String login="root";
    static String pwd="";
   static  String driver ="com.mysql.cj.jdbc.Driver";
    // private Connection cnx;
   private static MyConnection instance;

    
   public static Connection getCon() throws SQLException{
   Connection con=null;
       try {
           Class.forName(driver);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
   con=DriverManager.getConnection(url,login,pwd);
       System.out.println("Connexion établie!");}
       catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
   
   }
}