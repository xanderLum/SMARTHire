/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;

import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *
 * @author Zander Lumapac
 */
public class DegreeVectorEvaluator {

    private int[] doc_vectorRep;
    private int[] string_vectorRep;
    private double degrees;
    
    public DegreeVectorEvaluator(int[] doc_vectorRep, int[] string_vectorRep) {
        this.doc_vectorRep = doc_vectorRep;
        this.string_vectorRep = string_vectorRep;
        
        double sumprod = getSumProduct(doc_vectorRep, string_vectorRep);
        double sumprodF = getSumProduct(doc_vectorRep, doc_vectorRep);
        double sumprodS = getSumProduct(string_vectorRep, string_vectorRep);
        double absoluteV = getAbsoluteV(sumprodF);
        double absoluteS = getAbsoluteV(sumprodS);
        double cosAngle = getCosAngle(sumprod, absoluteV, absoluteS);
        double acosThetaRadiance = getAcosThetaRadiance(cosAngle);
        this.degrees = getDegrees(acosThetaRadiance);
    }

    public int[] getDoc_vectorRep() {
        return doc_vectorRep;
    }

    public void setDoc_vectorRep(int[] doc_vectorRep) {
        this.doc_vectorRep = doc_vectorRep;
    }

    public int[] getString_vectorRep() {
        return string_vectorRep;
    }

    public void setString_vectorRep(int[] string_vectorRep) {
        this.string_vectorRep = string_vectorRep;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }
    
    
    
    private void evaluatorRunnable(String args[]){
        String array1;
        String array2;
        
        Scanner s = new Scanner(System.in);
        System.out.println("Enter num_array1: ");
        array1 = s.nextLine();
        System.out.println("Enter num_array2: ");
        array2 = s.nextLine();
        
        StringTokenizer st = new StringTokenizer(array1);
        String read;
        int size = st.countTokens();
        int f_array[] = new int[size];
        int x = 0;
        while(st.hasMoreElements()){
               f_array[x] = Integer.parseInt(st.nextToken());
               x++;
        }
        
        st = new StringTokenizer(array2);
        size = st.countTokens();
        int s_array[] = new int[size];
        x = 0;
        while(st.hasMoreElements()){
            s_array[x] = Integer.parseInt(st.nextToken());
            x++;
        }
        
        double sumprod = getSumProduct(f_array, s_array);
        double sumprodF = getSumProduct(f_array, f_array);
        double sumprodS = getSumProduct(s_array, s_array);
        double absoluteV = getAbsoluteV(sumprodF);
        double absoluteS = getAbsoluteV(sumprodS);
        double cosAngle = getCosAngle(sumprod, absoluteV, absoluteS);
        double acosThetaRadiance = getAcosThetaRadiance(cosAngle);
        double degrees = getDegrees(acosThetaRadiance);

        System.out.println("Output:\n\tDegrees = "+degrees);
        
    }
    
    
    
    private double getSumProduct(int f_array[], int s_array[]){
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
