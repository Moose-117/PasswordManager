/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.passwordmanager;

/**
 *
 * @author marco
 */
public class ServizioRecuperatoDto {
    
    String nomeServizio = null;
    String passwordServizio = null;
    String saltServizio = null;
    
    public String getNomeServizio() {
        return nomeServizio;
    }
    
    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }
    
    public String getPasswordServizio() {
        return passwordServizio;
    }
    
    public void setPasswordServizio(String passwordServizio) {
        this.passwordServizio = passwordServizio;
    }
    
    public String getSaltServizio() {
        return saltServizio;
    }
    
    public void setSaltServizio(String saltServizio) {
        this.saltServizio = saltServizio;
    }
    
    public void cleanServiceDto() {
        setNomeServizio(null);
        setPasswordServizio(null);
        setSaltServizio(null);
    }
}
