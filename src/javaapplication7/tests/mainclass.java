/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7.tests;


import java.sql.Date;
import java.util.List;
import javaapplication7.entites.rdv;
import javaapplication7.entites.service;
import javaapplication7.services.CRUD;
import javaapplication7.services.CRUDRDV;
import javaapplication7.utils.connection;

/**
 *
 * @author USER
 */
public class mainclass {
  public static void main(String[] args) {
    CRUD p= new CRUD();
    CRUDRDV r= new CRUDRDV();
    Date dateRdv = new Date(20131010);
    
    service s = new service(false, "halééééééééé");
    p.ajouterservice2(s);
        service sdv = new service(19,false, "meet");
    rdv rr = new rdv(dateRdv, "Raison du rendez-vous", sdv, "14:00");
    r.ajouterRDV(rr, r.afficherRDV(), dateRdv);
    System.out.println("*********les services dispo :********* ");
    List<service> services = p.afficherservice();
    // p.modifierService(2,s);
    // p.supprimerService(2);
   // System.out.println("***********les services meet sont : *******");
   // List<service> resultat = p.chercherServiceParType(services, "me");
  //  System.out.println("*********les services Apres Trie :********* ");
   // p.trierServiceParType(services);
     System.out.println("*********les RDVS:********* ");
    List<rdv> rdvs = r.afficherRDV();
      System.out.println("*********les RDVS:********* ");
      rr = new rdv(dateRdv, "modifier", sdv, "14:00");
      r.modifierRDV(54, rr);
     r.supprimerRDV(45);
     
      rdvs = r.trierRdvParDate(rdvs, true);
   System.out.println("*********les RDVS apres tri:********* ");    
    r.afficherRDV();
}}