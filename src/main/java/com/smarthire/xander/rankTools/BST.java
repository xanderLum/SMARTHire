/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.rankTools;

import com.smarthire.main.dto.UserFinalScoresDTO;

import java.util.LinkedList;

import com.smarthire.xander.dto.RecommendedDTO;
import com.smarthire.xander.models.PathObj;
import com.smarthire.xander.rankTools.TokenObject;

/**
 *
 * @author Zander Lumapac
 */
public class BST {
    private BSTNode root;
    private LinkedList<TokenObject> objectList;
    private LinkedList<PathObj> rankList;
    
    
    private LinkedList<RecommendedDTO> recommendList;

    public BST() {
        root =null;
    }
    
    public void put(String key, Object value){
        if(root == null){
            root = new BSTNode(key, value);
        }else{
            root.put(key, value);
        }
    }
    
    public Object get(String key){
        return root == null? null: root.get(key);
    }
    
    public void inorder(){
        inorder(root);
    }
    
    private void inorderList(BSTNode r){
    	if(r!=null){
    		inorderList(r.getLeft());
    		RecommendedDTO u = (RecommendedDTO) r.getValue();
    		recommendList.add(u);
    		inorderList(r.getRight());
    	}
    }
    
    private void inorder(BSTNode r){
        if(r!=null){
            inorder(r.getLeft());
           // System.out.println(""+r.getKey()+"\t\t\t"+r.getValue());
            TokenObject t = new TokenObject(r.getKey(), Integer.parseInt(String.valueOf(r.getValue())));
            objectList.add(t);
            inorder(r.getRight());
        }
    }
    
     private void rankInorder(BSTNode r){
        if(r!=null){
            rankInorder(r.getLeft());
            PathObj j = new PathObj(Double.parseDouble(r.getKey()),  String.valueOf(r.getValue()));
            rankList.add(j);
            rankInorder(r.getRight());
        }
    }
     
     
    public LinkedList<RecommendedDTO> rank_Applicants(){
    	this.recommendList = new LinkedList<>();
    	inorderList(root);
    	return this.recommendList;
    }
    
    public LinkedList<TokenObject> retrieved(){
        this.objectList = new LinkedList<>();
        inorder(root);
        return this.objectList;
    }
    
    public LinkedList<PathObj> rank(){
        this.rankList = new LinkedList<>();
        rankInorder(root);
        return this.rankList;
    }
    
    
    public void preorder(){
        preorder(root);
    }
    
    private void preorder(BSTNode r){
        if(r!=null){
            System.out.println(""+r.getKey()+"\t\t\t"+r.getValue());
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }
    
    public void postorder(){
        postorder(root);
    }
    
    private void postorder(BSTNode r){
        if(r!=null){
            preorder(r.getLeft());
            preorder(r.getRight());
            System.out.println(""+r.getKey()+"\t\t\t"+r.getValue());
        }
    }
}
