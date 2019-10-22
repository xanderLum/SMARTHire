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
public class Post {
    private int postID;
    private boolean isSexual;
    private boolean postSenseSexual;
    private double truePointsSexual;
    private double falsePointsSexual;
    private boolean forCharity;
    private boolean postSenseCharity;
    private double truePointsCharity;
    private double falsePointsCharity;
    private boolean forGuns;
    private boolean postSenseGuns;
    private double truePointsGuns;
    private double falsePointsGuns;
    private int correctGrammar;
    private int totalSentenceNo;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public boolean isIsSexual() {
        return isSexual;
    }

    public void setIsSexual(boolean isSexual) {
        this.isSexual = isSexual;
    }

    public boolean isPostSenseSexual() {
        return postSenseSexual;
    }

    public void setPostSenseSexual(boolean postSenseSexual) {
        this.postSenseSexual = postSenseSexual;
    }

    public double getTruePointsSexual() {
        return truePointsSexual;
    }

    public void setTruePointsSexual(double truePointsSexual) {
        this.truePointsSexual = truePointsSexual;
    }

    public double getFalsePointsSexual() {
        return falsePointsSexual;
    }

    public void setFalsePointsSexual(double falsePointsSexual) {
        this.falsePointsSexual = falsePointsSexual;
    }

    public boolean isForCharity() {
        return forCharity;
    }

    public void setForCharity(boolean forCharity) {
        this.forCharity = forCharity;
    }

    public boolean isPostSenseCharity() {
        return postSenseCharity;
    }

    public void setPostSenseCharity(boolean postSenseCharity) {
        this.postSenseCharity = postSenseCharity;
    }

    public double getTruePointsCharity() {
        return truePointsCharity;
    }

    public void setTruePointsCharity(double truePointsCharity) {
        this.truePointsCharity = truePointsCharity;
    }

    public double getFalsePointsCharity() {
        return falsePointsCharity;
    }

    public void setFalsePointsCharity(double falsePointsCharity) {
        this.falsePointsCharity = falsePointsCharity;
    }

    public boolean isForGuns() {
        return forGuns;
    }

    public void setForGuns(boolean forGuns) {
        this.forGuns = forGuns;
    }

    public boolean isPostSenseGuns() {
        return postSenseGuns;
    }

    public void setPostSenseGuns(boolean postSenseGuns) {
        this.postSenseGuns = postSenseGuns;
    }

    public double getTruePointsGuns() {
        return truePointsGuns;
    }

    public void setTruePointsGuns(double truePointsGuns) {
        this.truePointsGuns = truePointsGuns;
    }

    public double getFalsePointsGuns() {
        return falsePointsGuns;
    }

    public void setFalsePointsGuns(double falsePointsGuns) {
        this.falsePointsGuns = falsePointsGuns;
    }

    public int getCorrectGrammar() {
        return correctGrammar;
    }

    public void setCorrectGrammar(int correctGrammar) {
        this.correctGrammar = correctGrammar;
    }

    public int getTotalSentenceNo() {
        return totalSentenceNo;
    }

    public void setTotalSentenceNo(int totalSentenceNo) {
        this.totalSentenceNo = totalSentenceNo;
    }
    
    
    
}
