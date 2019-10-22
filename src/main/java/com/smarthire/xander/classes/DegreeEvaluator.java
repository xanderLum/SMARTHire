/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;

/**
 *
 * @author Zander Lumapac
 */
public class DegreeEvaluator {

    private double[] doc_vectorRep;
    private double[] string_vectorRep;
    private double degrees;
    private double cosAngle;
    
    public DegreeEvaluator(double[] doc_vectorRep, double[] string_vectorRep) {
        this.doc_vectorRep = doc_vectorRep;
        this.string_vectorRep = string_vectorRep;
        
        double sumprod = getSumProduct(doc_vectorRep, string_vectorRep);
        double sumprodF = getSumProduct(doc_vectorRep, doc_vectorRep);
        double sumprodS = getSumProduct(string_vectorRep, string_vectorRep);
        double absoluteV = getAbsoluteV(sumprodF);
        double absoluteS = getAbsoluteV(sumprodS);
        cosAngle = getCosAngle(sumprod, absoluteV, absoluteS);
        double acosThetaRadiance = getAcosThetaRadiance(cosAngle);
        this.degrees = getDegrees(acosThetaRadiance);
    }

   

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public double[] getDoc_vectorRep() {
        return doc_vectorRep;
    }

    public void setDoc_vectorRep(double[] doc_vectorRep) {
        this.doc_vectorRep = doc_vectorRep;
    }

    public double[] getString_vectorRep() {
        return string_vectorRep;
    }

    public void setString_vectorRep(double[] string_vectorRep) {
        this.string_vectorRep = string_vectorRep;
    }

    public double getCosAngle() {
        return cosAngle;
    }

    public void setCosAngle(double cosAngle) {
        this.cosAngle = cosAngle;
    }
    
    
    private double getSumProduct(double f_array[], double s_array[]){
        double sumprod = 0.0f;
        for (int i = 0; i < f_array.length; i++) {
            sumprod += f_array[i] * s_array[i];    
        }
        return sumprod;
    }
    
    private double getAbsoluteV(double sumprod){
        double absoluteV = 0.0f;
        absoluteV = Math.sqrt(sumprod);
        return absoluteV;
    }
    
    private double getCosAngle(double sumprod, double absoluteV, double absoluteS){
        return sumprod / (absoluteV*absoluteS);
    }
    
    private double getAcosThetaRadiance(double cosAngle){
        return Math.acos(cosAngle);
    }
    
    private double getDegrees(double acosThetaRadiance){
        return Math.toDegrees(acosThetaRadiance);
    }
}
