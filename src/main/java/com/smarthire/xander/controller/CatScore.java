/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.xander.controller;

/**
 *
 * @author Zander Lumapac
 */
public class CatScore {

    private String catName;
    private int positive;
    private int neutral;
    private int negative;
    private int totalPost;
    private double catScore;

    public CatScore(String catName, int positive, int neutral, int negative, int totalPost, double catScore) {
        this.catName = catName;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
        this.totalPost = totalPost;
        this.catScore = catScore;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getNeutral() {
        return neutral;
    }

    public void setNeutral(int neutral) {
        this.neutral = neutral;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(int totalPost) {
        this.totalPost = totalPost;
    }

    public double getCatScore() {
        return catScore;
    }

    public void setCatScore(double catScore) {
        this.catScore = catScore;
    }

    @Override
    public String toString() {
        return "CatScore{" + "catName=" + catName + ", positive=" + positive + ", neutral=" + neutral + ", negative=" + negative + ", totalPost=" + totalPost + ", catScore=" + catScore + '}';
    }

    
}
