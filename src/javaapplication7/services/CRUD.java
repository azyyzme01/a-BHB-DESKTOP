/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7.services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication7.entites.service;
import javaapplication7.utils.connection;

/**
 *
 * @author USER
 */
public class CRUD {
  public void ajouterservice(){
     
     
       
      try {
          String req="INSERT INTO `service`(`type`, `dispo`) VALUES ('meet','1')";
          
          Statement st = new connection().getcnx().createStatement();
          st.executeUpdate(req);
           System.out.println("Service ajouter\n");
      } catch (SQLException ex) {
         System.err.println(ex.getMessage());
      }
  }  
 public void ajouterservice2(service s) {
    try {
        String req2 = "INSERT INTO `service`(`type`, `dispo`) VALUES (?, ?)";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req2);
        pst.setString(1, s.getType());
        pst.setBoolean(2, s.isDispo());
        pst.executeUpdate();
         System.out.println("Service ajouter\n");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
  public List<service> afficherservice(){
      List<service> services = new ArrayList<>();

    try {
        String req = "SELECT * FROM `service`";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
              int id = rs.getInt("id");
            String type = rs.getString("type");
            boolean dispo = rs.getBoolean("dispo");

            service s = new service(dispo,type );
               s.setId(id);
            services.add(s);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
  for (service s : services) {
        System.out.println( s.getType() + " " + s.isDispo());
    }
    return services;
  }
  public void modifierService(int id, service s) {
    try {
        String req = "UPDATE `service` SET `type` = ?, `dispo` = ? WHERE `id` = ?";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        pst.setString(1, s.getType());
        pst.setBoolean(2, s.isDispo());
        pst.setInt(3, id);
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Le service avec l'ID " + id + " a été modifié avec succès.");
        } else {
            System.out.println("Le service avec l'ID " + id + " n'a pas été trouvé dans la base de données.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
 public void supprimerService(int id) {
    try {
        String req1 = "DELETE FROM `rdv` WHERE `services_id` = ?";
        PreparedStatement pst1 = new connection().getcnx().prepareStatement(req1);
        pst1.setInt(1, id);
        pst1.executeUpdate();
        
        String req2 = "DELETE FROM `service` WHERE `id` = ?";
        PreparedStatement pst2 = new connection().getcnx().prepareStatement(req2);
        pst2.setInt(1, id);
        int rowsDeleted = pst2.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Le service avec l'ID " + id + " a été supprimé avec succès.");
        } else {
            System.out.println("Le service avec l'ID " + id + " n'a pas été trouvé dans la base de données.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
 public List<service> chercherServiceParType(List<service> services, String type) {
    List<service> resultat = new ArrayList<>();
    for (service s : services) {
        if (s.getType().toLowerCase().contains(type.toLowerCase())) {
            resultat.add(s);
        }
    }
      for (service s : resultat) {
        System.out.println(s.getId() + " - " + s.getType() + " - " + s.isDispo());
    }
    return resultat;
}
 public void trierServiceParType(List<service> services) {
    Collections.sort(services, new Comparator<service>() {
        public int compare(service s1, service s2) {
            return s1.getType().compareTo(s2.getType());
        }
    });
     for (service s : services) {
        System.out.println(s);
    }
  
}
 public service chercherServiceParId(int id) {
    service s = null;
    try {
        String req = "SELECT * FROM `service` WHERE id=?";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String type = rs.getString("type");
            boolean dispo = rs.getBoolean("dispo");
            s = new service(dispo,type);
            s.setId(rs.getInt("id"));
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return s;
}
 public static service getServiceByType(String type) {
    service s = null;
    try {
        String req = "SELECT * FROM service WHERE type=?";
        PreparedStatement pst = new connection().getcnx().prepareStatement(req);
        pst.setString(1, type);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            s = new service();
            s.setId(rs.getInt("id"));
            s.setType(rs.getString("type"));
            s.setDispo(rs.getBoolean("dispo"));
        }
        rs.close();
        pst.close();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return s;
}

}
