package services;

import entities.User;
import utils.ConnectionBD;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserServices {
    private Connection cnx = ConnectionBD.getInstance().getCnx();
    public static int cUserId;
    public static ResultSet cUserRow;
    public int ajouteruser(User u) {
        try {
           String requete = "INSERT INTO user (name, prenomc, num_tel, city, email, password, roles) VALUES (?, ?, ?, ?, ?, ?, ?)";
PreparedStatement pst = ConnectionBD.getInstance().getCnx().prepareStatement(requete);

pst.setString(1, u.getName());
pst.setString(2, u.getPrenomc());
pst.setString(3, u.getNumTel());
pst.setString(4, u.getCity());
pst.setString(5, u.getEmail());
pst.setString(6, u.getPassword());
pst.setString(7, u.getRoles());



            pst.executeUpdate();
            System.out.println("Client ajouteé avec succées !");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int id = 0;
        try {
            String requete = "SELECT ID FROM user WHERE ID = ( SELECT MAX(ID) FROM user)";
            Statement st = ConnectionBD.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return id;
    }






        public static List<User> listerUsers() {
        List<User>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM user";
            Statement st=ConnectionBD.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
            
            User per=new User();
            per.setId(rs.getInt(1));
            per.setNumTel((rs.getString("num_tel")));

            
            per.setRoles(rs.getString("roles"));
            
            per.setPassword(rs.getString("password"));
            per.setName(rs.getString("name"));
            per.setPrenomc(rs.getString("prenomc"));
            per.setEmail(rs.getString("email"));
            per.setCity(rs.getString("city"));
           
              
            
            
      
            
            mylist.add(per);
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;}
  public void modifierUser(User C) {
    try {
        String requete = "UPDATE user SET num_tel=?, name=?, prenomc=?, city=?, email=?, password=?, roles=? WHERE id=?";

        PreparedStatement pst = ConnectionBD.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, Integer.parseInt(C.getNumTel())); // convert string to int
        pst.setString(2, C.getName());
        pst.setString(3, C.getPrenomc());
        pst.setString(4, C.getCity());
        pst.setString(5, C.getEmail());
        pst.setString(6, C.getPassword());
        pst.setString(7, C.getRoles());
        pst.setInt(8, C.getId());

        pst.executeUpdate();
        System.out.println("user modifiée!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    public static void supprimerUser(User u){
        try {
            String requete="DELETE from user where id=?";
            PreparedStatement pst = ConnectionBD.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,u.getId());
            pst.executeUpdate();
            System.err.println("user supprimé avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
public boolean login(String email, String password) {
    if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
        System.out.println("Invalid email or password.");
        return false; 
    }

    try {
        String query = "SELECT * FROM `user` WHERE email = '" + email + "'";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(query);

        if (!rs.isBeforeFirst()) {
            System.out.println("Invalid email.");
            return false;
        }

        rs.next();
        String hashedPassword = rs.getString("password");
        
        String inputPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        if (!BCrypt.checkpw(inputPasswordHash, hashedPassword)) {
            System.out.println("Invalid password.");
            return false; 
        }

        // Set login session variables here
        // ...

        System.out.println("Login successful.");
        return true;
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return false; 
}
public String crypter_password(String password) {
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    return hashedPassword;
}


    
    
}