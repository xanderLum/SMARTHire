/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.xander.controller;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author Zander Lumapac
 */
public class Test {

    static String catList = "sex,charity,gun,drug,alcohol,profane,politics,grammar_spelling";
    static String nature = "-,+,-,-,-,-,#,-";
    static LinkedList<CatScore> cscoreList;
    static String[] nList;

    public static void inputCatScore() {
        CatScore c;
        String[] cList = catList.split(",");
        nList = nature.split(",");

        int p = 10, neu = 10, n = 10;
        for (int i = 0; i < 8; i++) {
            c = new CatScore(cList[i], p, neu, n, p + neu + n, 0f);
            cscoreList.add(c);
            p--;
            neu++;
            n++;
        }
    }

    public static void main(String args[]) {
        System.out.println("Starting calculation ...    ");
        cscoreList = new LinkedList<>();
        inputCatScore();
        getCatScore();
        double sum=0;
        for(CatScore c: cscoreList){
            System.out.println("printing "+c.toString());
            sum+=c.getCatScore();
        }
        System.out.println("OVERALL : "+sum);
    }

    public static void getCatScore() {
        for (CatScore c : cscoreList) {
            if (nList[cscoreList.indexOf(c)].equalsIgnoreCase("-")) {
                cscoreList.get(cscoreList.indexOf(c)).setCatScore(((double)c.getNegative() / c.getTotalPost())*(-1));
            } else if (nList[cscoreList.indexOf(c)].equalsIgnoreCase("+")) {
                cscoreList.get(cscoreList.indexOf(c)).setCatScore((double)c.getPositive() / c.getTotalPost());
            } else {
                cscoreList.get(cscoreList.indexOf(c)).setCatScore((double)c.getNegative() / c.getTotalPost());
            }
        }
    }
    
}
