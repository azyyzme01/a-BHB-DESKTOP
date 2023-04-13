package utils;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class ConnectionBD {
    public String url="jdbc:mysql://localhost:3306/user";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static ConnectionBD instance;

    private ConnectionBD() {
        try {
            cnx= DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection Etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static ConnectionBD getInstance() {
        if(instance==null)
        {instance=new ConnectionBD();
        }
        return instance;}
}
