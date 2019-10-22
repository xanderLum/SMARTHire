/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

/**
 *
 * @author Nathalia
 */
public class Point {
    
    private String categoryName;
    private boolean isPossible;
    private int point;
    private int nature;

    public Point(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isIsPossible() {
        return isPossible;
    }

    public void setIsPossible(boolean isPossible) {
        this.isPossible = isPossible;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getNature() {
        return nature;
    }

    public void setNature(int nature) {
        this.nature = nature;
    }
    
    
   
}
