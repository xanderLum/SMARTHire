/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.rankTools;

/**
 *
 * @author Zander Lumapac
 */

public class BSTNode {
    private String key;
    private Object value;
    private BSTNode left, right;

    public BSTNode(String key, Object value) {
       this.key = key;
       this.value =value;
    }
    
    public void put(String key, Object value){
        if(key.compareTo(this.key) < 0){
            if(left!= null){
                left.put(key, value);
            }else{
                left = new BSTNode(key, value);
            }
        }else if(key.compareTo(this.key) > 0){
            if(right!= null){
                right.put(key, value);
            }else{
                right = new BSTNode(key, value);
            }
        }else{
            this.value = value;
        }
    }
    
    public Object get(String key){
        if(this.key.equals(key)){
            return value;
        }
        
        if(key.compareTo(this.key) <0 ){
            return left == null? null: left.get(key);
        }else{
            return right == null ? null: right.get(key);
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
    
    
}
