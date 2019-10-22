package com.smarthire.xander.interfaces;

/**
*
* @author Zander Lumapac
*/

public interface CategoryDetails {
	public final boolean NATURE_SEXUALPOST = false;
	public final boolean NATURE_VOLUNTEERISM = true;
	public final boolean NATURE_FIREARMS = false;
	public final boolean NATURE_DRUGS = false;	
	public final boolean NATURE_ALCOHOL = false;
	public final boolean NATURE_PROFANITY = false;
	public final boolean NATURE_POLITICS = true;
	public final boolean NATURE_GRAMMAR = true;
	
	public final String[][] NATURE = {
			{"sexual", "-1"},
			{"charity", "1"},
			{"guns", "-1"},
			{"illegal_drugs", "-1"},
			{"alcohol", "-1"},
			{"profanity", "-1"},
			{"politics", "0"},
			{"grammar_spelling", "1"}								
	};
	
	
	public final String[][] THRESHOLD = {
			{"sexual", "0.7"},
			{"charity", "0.65"},
			{"guns", "0.51"},
			{"illegal_drugs", "0.83"},
			{"alcohol", "0.44"},
			{"profanity", "0.63"},
			{"politics", "0.69"},
			{"grammar_spelling", "0.66"}		
	};
	
	
	//overall positive =  66+65 = 131
	//overall negative = 70+51+83+44+63 = 311
	//neutral = 69;
	
	//overall over = -180
	public final float THRESHOLD_SEXUALPOST = 0.7f;
	public final float THRESHOLD_VOLUNTEERISM = 0.65f;
	public final float THRESHOLD_FIREARMS = 0.51f;
	public final float THRESHOLD_DRUGS = 0.83f;
	public final float THRESHOLD_ALCOHOL = 0.44f;
	public final float THRESHOLD_PROFANITY = 0.63f;
	public final float THRESHOLD_POLITICS = 0.69f;
	public final float THRESHOLD_GRAMMAR = 0.66f;
			
}
