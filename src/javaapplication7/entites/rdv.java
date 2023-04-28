/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7.entites;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class rdv {
    private int id;
    private Date date;
    private String raison;   
       private service s;
   
    private String heure;

    public rdv() {
    }

    public rdv(Date date, String raison, service s, String heure) {
        this.date = date;
        this.raison = raison;
        this.s = s;
        this.heure = heure;
    }

    public rdv(int id, Date date, String raison, service s, String heure) {
        this.id = id;
        this.date = date;
        this.raison = raison;
        this.s = s;
        this.heure = heure;
    }

    public rdv(java.util.Date dateRdv, String raison_du_rendezvous, service s, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public service getS() {
        return s;
    }

    public void setS(service s) {
        this.s = s;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "rdv{" + "id=" + id + ", date=" + date + ", raison=" + raison + ", s=" + s + ", heure=" + heure + '}';
    }
    
}
