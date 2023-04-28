/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7.utils;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class connection {
    public String  url="jdbc:mysql://localhost:3306/pidev";
        public String  login="root";
            public String  pwd="";
          Connection cnx;  
public connection(){
        try {
          cnx=  (Connection) DriverManager.getConnection(url, login, pwd);
          System.out.println("connection success");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
}
public Connection getcnx()
{
    return  cnx;
}
}
