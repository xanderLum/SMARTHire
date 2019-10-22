package com.smarthire.xander.controller;

import java.util.LinkedList;

import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.CategoryScore;

public class Monitor {

	LinkedList<Double> categoryScore;
	String catList = "sex,charity,gun,drug,alcohol,profane,politics,grammar,spelling";
	String nature = "-,+,-,-,-,-,#,-,-";

	String[] nList;

	private LinkedList<CHAR_CATEGORYSCORE> cscoreList;
	private double perProfileCharacterScore;
	private LinkedList<CategoryScore> cList;
	private LinkedList<CHAR_CATEGORYSCORE> catscoreList;
	private String username;

	public LinkedList<CategoryScore> getcList() {
		return cList;
	}

	public void setcList(LinkedList<CategoryScore> cList) {
		this.cList = cList;
	}

	public LinkedList<CHAR_CATEGORYSCORE> getCscoreList() {
		return cscoreList;
	}

	public void setCscoreList(LinkedList<CHAR_CATEGORYSCORE> cscoreList) {
		this.cscoreList = cscoreList;
	}

	public double getPerProfileCharacterScore() {
		return perProfileCharacterScore;
	}

	public void setPerProfileCharacterScore(double perProfileCharacterScore) {
		this.perProfileCharacterScore = perProfileCharacterScore;
	}

	public LinkedList<CHAR_CATEGORYSCORE> getCatscoreList() {
		return catscoreList;
	}

	public void setCatscoreList(LinkedList<CHAR_CATEGORYSCORE> catscoreList) {
		this.catscoreList = catscoreList;
	}

	public void inputCatScore() {
		CHAR_CATEGORYSCORE c;
		String[] cList = catList.split(",");
		nList = nature.split(",");

		int p = 10, neu = 10, n = 10;
		for (int i = 0; i < 8; i++) {
			c = new CHAR_CATEGORYSCORE(username, cList[i], p, neu, n, p + neu + n, "0.0");
			catscoreList.add(c);
			p--;
			neu++;
			n++;
		}
	}

	public Monitor() {
		super();
		String[] cList = catList.split(",");
		nList = nature.split(",");
		this.perProfileCharacterScore = 0f;
	}

	public Monitor(LinkedList<CHAR_CATEGORYSCORE> llcs) {
		super();
		String[] cList = catList.split(",");
		nList = nature.split(",");
		this.perProfileCharacterScore = 0f;
	}

	public double getFinalCharScore(CHAR_CATEGORYSCORE[] c){
		System.out.println("char category score list size = "+c.length);
		double score = 0.0;
		if(c.length>0){
			for(int x = 0; x<c.length; x++){
				if (nList[x].equalsIgnoreCase("-")) {
					System.out.println("category name = "+c[x].getCategoryName() +" >>>MONITOR>>> NEGATIVE NATURE");
					c[x].setCategoryScore(((double) c[x].getNegative() / c[x].getTotalPost()) * (-1));
					// perProfileCharacterScore +=
					// llcs.get(llcs.indexOf(c)).getCategoryScore();
					score += getScore("-", c[x].getCategoryScore());
				} else if (nList[x].equalsIgnoreCase("+")) {
					System.out.println("category name = "+c[x].getCategoryName() +" >>>MONITOR>>> POSITIVE NATURE");
					c[x].setCategoryScore((double) c[x].getPositive() / c[x].getTotalPost());
					// perProfileCharacterScore +=
					// llcs.get(llcs.indexOf(c)).getCategoryScore();
					score += getScore("+", c[x].getCategoryScore());
				} else {
					System.out.println("category name = "+c[x].getCategoryName() +" >>>MONITOR>>> NEUTRAL NATURE");
					c[x].setCategoryScore((double) c[x].getNegative() / c[x].getTotalPost());
					score += getScore(" ", c[x].getCategoryScore());
				}
			}
		}
		return (double) Math.round(((double) score / c.length) * 100) / 100;
	}
	public void startRealMonitor(LinkedList<CHAR_CATEGORYSCORE> llcs) {
		// cscoreList = new LinkedList<>();
		System.out.println("llcs size = "+llcs.size());
		double score=0.0;
		if (llcs != null) {
			for (CHAR_CATEGORYSCORE c : llcs) {
				if (nList[llcs.indexOf(c)].equalsIgnoreCase("-")) {
					System.out.println("category name = "+c.getCategoryName() +" >>>MONITOR>>> NEGATIVE NATURE");
					llcs.get(llcs.indexOf(c)).setCategoryScore(((double) c.getNegative() / c.getTotalPost()) * (-1));
					System.out.println(">>>>>>>>NAN category score double "+c.getCategoryScore());
					if(c.getCategoryScore()== Double.NaN){
						llcs.get(llcs.indexOf(c)).setCategoryScore(0.0);
					}
					// perProfileCharacterScore +=
					// llcs.get(llcs.indexOf(c)).getCategoryScore();
					System.out.println(">>>>>>>>NAN after parse to 0.0 score double "+c.getCategoryScore());
					score += getScore("-", c.getCategoryScore());
					
				} else if (nList[llcs.indexOf(c)].equalsIgnoreCase("+")) {
					System.out.println("category name = "+c.getCategoryName() +" >>>MONITOR>>> POSITIVE NATURE");
					llcs.get(llcs.indexOf(c)).setCategoryScore((double) c.getPositive() / c.getTotalPost());
					System.out.println(">>>>>>>>NAN category score double "+c.getCategoryScore());
					if(c.getCategoryScore()== Double.NaN){
						llcs.get(llcs.indexOf(c)).setCategoryScore(0.0);
					}
					// perProfileCharacterScore +=
					// llcs.get(llcs.indexOf(c)).getCategoryScore();
					System.out.println(">>>>>>>>NAN after parse to 0.0 score double "+c.getCategoryScore());
					score += getScore("+", c.getCategoryScore());
				} else {
					System.out.println("category name = "+c.getCategoryName() +" >>>MONITOR>>> NEUTRAL NATURE");
					llcs.get(llcs.indexOf(c)).setCategoryScore((double) c.getNegative() / c.getTotalPost());
					System.out.println(">>>>>>>>NAN category score double "+c.getCategoryScore());
					if(c.getCategoryScore()== Double.NaN){
						llcs.get(llcs.indexOf(c)).setCategoryScore(0.0);
					}
					System.out.println(">>>>>>>>NAN after parse to 0.0 score double "+c.getCategoryScore());
					score += getScore(" ", c.getCategoryScore());
				}
			}
			this.cscoreList = llcs;
			this.perProfileCharacterScore = (double) Math.round(((double) score / llcs.size()) * 100) / 100;
			System.out.println("perProfileCharScore = "+perProfileCharacterScore);
		}
	}

	public double getScore(String nature, Double percent) {
		double perfectScore = 0.0;
		percent = (double) Math.round(percent * 100) / 100;
		if (nature.equals("-")) {
			perfectScore = 100f;
			System.out.println("percent = " + percent);

			perfectScore += (percent * 100);

		} else {
			System.out.println("detected charity and politics category");
			perfectScore += (percent * 100);

		}
		return perfectScore;
	}

	public void startMonitor(LinkedList<CategoryScore> llcs) {
		catscoreList = new LinkedList<>();
		if (llcs != null) {
			CHAR_CATEGORYSCORE catscore;
			for (CategoryScore c : llcs) {
				catscore = new CHAR_CATEGORYSCORE(username, c.getCategoryName(), c.getPositive(), c.getNeutral(),
						c.getNegative(), c.getTotalPost(), "0.0");
				catscoreList.add(catscore);
			}
			for (CategoryScore c : llcs) {
				if (nList[cscoreList.indexOf(c)].equalsIgnoreCase("-")) {
					catscoreList.get(catscoreList.indexOf(c))
							.setCategoryScore(((double) c.getNegative() / c.getTotalPost()) * (-1));
					perProfileCharacterScore += catscoreList.get(catscoreList.indexOf(c)).getCategoryScore();
				} else if (nList[catscoreList.indexOf(c)].equalsIgnoreCase("+")) {
					catscoreList.get(catscoreList.indexOf(c))
							.setCategoryScore((double) c.getPositive() / c.getTotalPost());
					perProfileCharacterScore += catscoreList.get(catscoreList.indexOf(c)).getCategoryScore();
				} else {
					catscoreList.get(catscoreList.indexOf(c))
							.setCategoryScore((double) c.getNegative() / c.getTotalPost());
				}
			}
		}

	}

	public void startTestMonitor() {
		System.out.println("Starting calculation ...    ");
		catscoreList = new LinkedList<>();
		this.cList = new LinkedList<>();
		inputCatScore();
		getCatScore();
		double sum = 0;
		for (CHAR_CATEGORYSCORE c : catscoreList) {
			CategoryScore cs = new CategoryScore(c.getCategoryName(), c.getPositive(), c.getNeutral(), c.getNegative(),
					c.getTotalPost());
			//System.out.println("printing " + c.toString());
			sum += c.getCategoryScore();
			cList.add(cs);
		}
		System.out.println("OVERALL : " + sum);

	}

	public void getCatScore() {
		for (CHAR_CATEGORYSCORE c : catscoreList) {
			if (nList[catscoreList.indexOf(c)].equalsIgnoreCase("-")) {
				catscoreList.get(catscoreList.indexOf(c))
						.setCategoryScore(((double) c.getNegative() / c.getTotalPost()) * (-1));
				perProfileCharacterScore += catscoreList.get(catscoreList.indexOf(c)).getCategoryScore();
			} else if (nList[catscoreList.indexOf(c)].equalsIgnoreCase("+")) {
				catscoreList.get(catscoreList.indexOf(c)).setCategoryScore((double) c.getPositive() / c.getTotalPost());
				perProfileCharacterScore += catscoreList.get(catscoreList.indexOf(c)).getCategoryScore();
			} else {
				catscoreList.get(catscoreList.indexOf(c)).setCategoryScore((double) c.getNegative() / c.getTotalPost());
			}
		}
	}
}
