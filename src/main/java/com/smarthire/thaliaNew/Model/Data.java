package com.smarthire.thaliaNew.Model;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nathalia
 */
public class Data {
    
    private String definition;
    private int defPntr;
    private ArrayList<Integer> value = new ArrayList();
    private double cosSim;
    private ArrayList<Double> tf = new ArrayList();
    private double degree;

    public Data(String definition, int defPntr) {
        this.definition = definition;
        this.defPntr = defPntr;
    }

    public Data(String def) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getCosSim() {
        return cosSim;
    }

    public void setCosSim(double cosSim) {
        this.cosSim = cosSim;
    }

    public ArrayList<Double> getTf() {
        return tf;
    }

    public void addTf(double tf) {
        this.tf.add(tf);
    }
    
    public ArrayList<Integer> getValue() {
        return value;
    }

    public void addValue(int val) {
        this.value.add(val);
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getDefPntr() {
        return defPntr;
    }

    public void setDefPntr(int defPntr) {
        this.defPntr = defPntr;
    }
    
    
    
}
