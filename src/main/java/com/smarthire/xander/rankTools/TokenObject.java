/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.xander.rankTools;

/**
 *
 * @author Zander Lumapac
 */
public class TokenObject {
    private String token;
    private int tf;
    private double idf;
    
    public TokenObject(String token, int tf) {
        this.token = token;
        this.tf = tf;
    }
     
  
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

   

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    public double getIdf() {
        return idf;
    }

    public void setIdf(double idf) {
        this.idf = idf;
    }

    
  
    
    
    
}
