package com.smarthire.xander.controller;

import java.util.LinkedList;

import com.smarthire.xander.interfaces.CategoryDetails;
import com.smarthire.xander.models.CategoryScore;
import com.smarthire.xander.models.FScore;

public class Scorer implements CategoryDetails{
	private LinkedList<CategoryScore> object;
	private LinkedList<FScore>  finalObject;
	
	public Scorer(LinkedList<CategoryScore> list) {
		super();
		this.object = list;
		finalObject = new LinkedList<>();
	}
	
	public LinkedList<FScore> edited(){
		for(CategoryScore i: this.object){
			int nature = getNature(i.getCategoryName());
			double threshold = getThreshold(i.getCategoryName());
			double avg, gap = 50f, percentage, score, value;
			if(nature == 0){
				System.out.println("nature = 0");
				if(threshold > 0.5f){
					avg = 1 - threshold;
					
				}else
					avg = threshold;
				  
				System.out.println("avg = "+avg);
					value = ((double)i.getPositive()/i.getTotalPost()) * 100;
					System.out.println("value = "+value);
					if(value < 50){
						percentage = (double)value/gap;
						score = percentage * (avg*100);
					}else{
						percentage = (double)(value-gap) / gap;
						score = (percentage * (threshold*100)) + (avg*100);
					}
					
					System.out.println("percentage = "+percentage);
					System.out.println("score = "+score);
					
			}else if(nature == -1){
				System.out.println("nature = -1");
				if(threshold > 0.5f){
					avg = 1 - threshold;
				}else
					avg = threshold;
				
				System.out.println("avg = "+avg);
					/*value =  ((double)i.getNoOfNegaPosts()/i.getTotalNoOfPosts()) * 100 * -1;*/

				value =  ((double)i.getNegative()/i.getTotalPost()) * 100;	
				System.out.println("val = "+value);
					if(value < 50){
						percentage =(double) value/gap;
						score = percentage * (avg*100);
					}else{
						percentage = (double)(value-gap) / gap;
						score = (percentage * (threshold*100)) + (avg*100);
					}
					System.out.println("percentage = "+percentage);
					System.out.println("score = "+score);
					
			}else{
				System.out.println("nature = 1");
				if(threshold > 0.5f){
					avg = 1 - threshold;
				}else
					avg = threshold;
				System.out.println("avg = "+avg);
					value =((double)i.getPositive()/i.getTotalPost()) * 100;
					System.out.println("val = "+value);
					if(value < 50){
						percentage =(double) value/gap;
						score = percentage * (avg*100);
					}else{
						percentage =(double) (value-gap) / gap;
						score = (percentage * (threshold*100)) + (avg*100);
					}
					System.out.println("percentage = "+percentage);
					System.out.println("score = "+score);
					
			}
			
			FScore fscore = new FScore();
			fscore.setCategoryName(i.getCategoryName());
			fscore.setFscore(Double.valueOf(String.format("%.2f",score)));
			finalObject.add(fscore);
			
		}
		
		return this.finalObject;
		
	}
	
	public LinkedList<FScore> getFinalCharScore(){
		for(CategoryScore i: this.object){
			int nature = getNature(i.getCategoryName());
			double threshold = getThreshold(i.getCategoryName());
			double percentage, score = 0f;
			
			if(i.getTotalPost()>0 && !i.getCategoryName().equalsIgnoreCase("politics")){
				System.out.println("positive = "+i.getPositive());
				System.out.println("totalPost = "+i.getTotalPost());
				percentage = ((double) i.getPositive()/i.getTotalPost()) ;//* 100;
				System.out.println("percentage = "+percentage);
				System.out.println("threshold = "+threshold);
				score = percentage * (threshold*100);
				System.out.println("getFinalC score = "+score);
				score*= nature;
				System.out.println("getFinalC score nature = "+score);
			}
			
		//	System.out.println("FSCORE THIS GETFINALCHARSCORE : score: "+score);
			FScore fscore = new FScore();
			fscore.setCategoryName(i.getCategoryName());
			fscore.setFscore(Double.valueOf(String.format("%.2f",score)));
			
			if(fscore.getFscore() == -0.0f){
				fscore.setFscore(0.0f);
			}
			System.out.println("fscore.getFscore format = "+fscore.getFscore());
			finalObject.add(fscore);
		}
		
		return this.finalObject;
		
	}
	
	public double getOverallOver(){
		double positive = 0f;
		double negative = 0f;
		double over = 0f;
		for(int r = 0; r<THRESHOLD.length; r++){
			if(THRESHOLD[r][0].equalsIgnoreCase("charity") || THRESHOLD[r][0].equalsIgnoreCase("grammar_spelling")){
				positive+= Double.parseDouble(THRESHOLD[r][1])*100;
			}else if(THRESHOLD[r][0].equalsIgnoreCase("politics")){
				//no effect
			}
			else{
				negative+= Double.parseDouble(THRESHOLD[r][1])*100;
			}
		}
		over = positive+(negative*-1);

		System.out.println("===============================");
		
		System.out.println("OVERALL = "+over);
		System.out.println("POSITIVE: "+positive);
		System.out.println("NEGATIVE: "+negative);
		System.out.println("===============================");
		return over;
	}
	
	public LinkedList<FScore> getProfileCharacterScore(){
		System.out.println("Scorer Class getProScore()");
		double a;
		for(CategoryScore i: this.object){
			System.out.println("cat name = "+i.getCategoryName());
			System.out.println("+ = "+i.getPositive());
			System.out.println("- = "+i.getNegative());
			System.out.println("N = "+i.getNeutral());
			a = (double) (i.getPositive() + i.getNegative()) / (double)(i.getTotalPost()-i.getNeutral());
			System.out.println("a before multiplied to nature  = == = "+a);
			double threshold;
			double percentage;
			double score;
			
			if(getNature(i.getCategoryName()) != 0){
				a = a * Integer.parseInt(String.valueOf(getNature(i.getCategoryName())));
				threshold = getThreshold(i.getCategoryName());
				
				 percentage = a * threshold;
				 score = (percentage + threshold) * 100;
				
			}else{
				System.out.println("Pass thru politics as neutral category");
				 threshold = getThreshold(i.getCategoryName());
				 percentage = a * threshold;
				 score = percentage * 100;
			}
			
			System.out.println("CATEGORY === "+i.getCategoryName());
			System.out.println("a after = "+a);
			System.out.println("perc = "+percentage);
			System.out.println("score = "+score);
			System.out.println("total post = "+(i.getTotalPost()));
			
			FScore fscore = new FScore();
			fscore.setCategoryName(i.getCategoryName());
			fscore.setFscore(score);
			finalObject.add(fscore);
		}
		
		System.out.println("Return finalObject");
		return this.finalObject;
	}

	private int getNature(String categoryName){
		for(int r = 0; r<NATURE.length; r++){
			if(NATURE[r][0].equalsIgnoreCase(categoryName)){
				//System.out.println("return getNature = "+Integer.valueOf(NATURE[r][1]));
				return Integer.valueOf(NATURE[r][1]);
			}
		}
		//System.out.println("return getNature default 0");
		return 0;
	}
	
	private double getThreshold(String categoryName){
		for(int r = 0; r<THRESHOLD.length; r++){
			if(THRESHOLD[r][0].equalsIgnoreCase(categoryName)){
				//System.out.println("return getThreshold = "+Double.valueOf(THRESHOLD[r][1]));
				return Double.valueOf(THRESHOLD[r][1]);
			}
		}
		//System.out.println("return getThreshold default -1.0f");
		return -1.0f;
	}
	
	
	
}
