/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudpi.services;


import crudpi.entities.comptesBancaire;
import crudpi.entities.Transaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import crudpi.interfaces.entityCRUD;
import crudpi.utils.connexion;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ANAS
 */
public class transactionCRUD implements entityCRUD<Transaction>{
    
      @Override
    public void addEntity(Transaction t) {
        try {
    comptesBancaire compte_source_id = t.getCompte_source_id(); 
    String nom = t.getNom();
    String prenom = t.getPrenom();
    String email = t.getEmail();
    int num_tlfn= t.getNum_tlfn();
    comptesBancaire compte_destination = t.getCompte_destination(); 
    float montant = t.getMontant();
    

    String insertReservationQuery = "INSERT INTO transaction (compte_source_id,nom, prenom, email, num_tlfn,compte_destination, montant)" + "VALUES (?,?,?, ?, ?, ?, ?)";
    PreparedStatement stmp = connexion.getInstance().getCnx().prepareStatement(insertReservationQuery);
        
    stmp.setInt(1, t.getCompte_source_id().getId());
    stmp.setString(2, nom);
    stmp.setString(3, prenom);
    stmp.setString(4, email);
    stmp.setInt(5, num_tlfn);
    stmp.setInt(6, t.getCompte_destination().getId());
    stmp.setFloat(7, montant);

    int rowsAffected = stmp.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("transaction added successfully");
    } else {
        System.out.println("Failed to add compte");
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
    }

    @Override
    public List<Transaction> entitiesList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
         public static List<Transaction> getAll() {
    List<Transaction> TransactionList = new ArrayList<>();
    String requete = "SELECT * FROM transaction" ;
    
    try (
            PreparedStatement statement=connexion.getInstance()
                .getCnx().prepareStatement(requete);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(resultSet.getInt("id"));
            comptesBancaire compte_source_id = new comptesBancaire();
            compte_source_id.setId(resultSet.getInt("compte_source_id"));
            transaction.setCompte_source_id(compte_source_id);
            //transaction.setCompte_source_id(resultSet.getInt("compte_source_id"));
            transaction.setNom(resultSet.getString("nom"));
            transaction.setPrenom(resultSet.getString("prenom"));
            transaction.setEmail(resultSet.getString("email"));
            transaction.setNum_tlfn(resultSet.getInt("num_tlfn"));
             comptesBancaire compte_destination = new comptesBancaire();
            compte_source_id.setId(resultSet.getInt("compte_source_id"));
            transaction.setCompte_destination(compte_destination);
           // transaction.setCompte_destination(resultSet.getInt("num_tlfn"));
            transaction.setMontant(resultSet.getFloat("montant"));

            TransactionList.add(transaction);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return TransactionList;
       }
    public void deleteEntity(int id) {
        
        try {
            String requete="DELETE FROM transaction WHERE id=?";
            PreparedStatement st=connexion.getInstance().getCnx().prepareStatement(requete);
            
            st.setInt(1,id);
            

            st.executeUpdate();
            System.out.println("transaction deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
    
    public void updateEntity(int id,int compte_source_id ,String nom,String prenom,String email,int num_tlfn,int compte_destination ,float montant){
        
         try{
        String requete = "UPDATE transaction set  compte_source_id = ?,nom = ?,prenom = ?,email = ?,num_tlfn  = ?, compte_destination = ?,montant=? WHERE id = ?";
        PreparedStatement st=connexion.getInstance()
                .getCnx().prepareStatement(requete);
              st.setInt(1, compte_source_id);
              st.setString(2, nom);
              st.setString(3, prenom);
              st.setString(4, email);
              st.setInt(5, num_tlfn);
              st.setInt(6, compte_destination);
              st.setFloat(7, montant);
                      st.setInt(8, id);

        st.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
    
    
    public static Map<String, Integer> countTransactionsByCompte() {
    try {
        String query = "SELECT cb.nom, COUNT(*) AS transaction_count " +
                       "FROM Transaction t " +
                       "INNER JOIN CompteBancaire cb ON t.compte_source_id = cb.id " +
                       "GROUP BY cb.nom";
            
        PreparedStatement statement = connexion.getInstance().getCnx().prepareStatement(query);
        ResultSet result = statement.executeQuery(query);
            
        Map<String, Integer> transactionCounts = new HashMap<>();

        while (result.next()) {
            String compteName = result.getString("nom");
            int transactionCount = result.getInt("transaction_count");
            transactionCounts.put(compteName, transactionCount);
        }

        return transactionCounts;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
    return null;
}

    
    
    

    
    
}
