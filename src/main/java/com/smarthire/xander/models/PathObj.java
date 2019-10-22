/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.models;

/**
 *
 * @author Zander Lumapac
 */
public class PathObj {
    private double degrees;
    private String path;

    public PathObj(double degrees, String path) {
        this.degrees = degrees;
        this.path = path;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
