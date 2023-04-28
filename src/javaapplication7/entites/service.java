/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7.entites;

/**
 *
 * @author USER
 */
public class service {
    private int id;
    private boolean dispo;
    private String type;

    public service() {
    }

    public service(boolean dispo, String type) {
        this.dispo = dispo;
        this.type = type;
    }

    public service(int id, boolean dispo, String type) {
        this.id = id;
        this.dispo = dispo;
        this.type = type;
    }

    public service(int id, String newType, boolean newDispo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  type ;
    }
    
    
}
