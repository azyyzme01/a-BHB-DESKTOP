package services;

import entities.User;
import utils.ConnectionBD;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    try {
        String query = "SELECT * FROM `user` WHERE email ='" + email + "' AND password ='" + password + "'";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(query);
        if (!rs.isBeforeFirst()) {
            System.out.println("User not found!");
            return false;
        } else {
            System.out.println("User is logged in.");
            while (rs.next()) {
                LoginSession.UID = rs.getInt("id");
                LoginSession.Roles = rs.getString("roles");
                LoginSession.Name = rs.getString("name");
                LoginSession.email = rs.getString("email");
                LoginSession.password = rs.getString("password");
                //LoginSession.avatar = rs.getString("avatar");
                LoginSession.IsLogged = true;
            }
            System.out.println(LoginSession.Name + " is connected.");
            return true;
        }
    } catch (SQLException ex) {
        //System.out.println(ex);
    }
    return false;
}

    public String crypter_password(String password) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {
        }

        //   return hashValue;
        return hashValue;
    }
    
}