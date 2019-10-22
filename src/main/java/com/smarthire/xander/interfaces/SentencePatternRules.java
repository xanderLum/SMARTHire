/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.interfaces;

/**
 *
 * @author Zander Lumapac
 */
public interface SentencePatternRules {     
    public final String PATTERN1 = "S-LV-SC(N)";
    public final String PATTERN2 = "S-LV-SC(ADJ)";
    public final String PATTERN3 = "S-IV";
    public final String PATTERN4 = "S-TV-DO";
    public final String PATTERN5 = "S-TV-IO-DO";
    public final String PATTERN6 = "S-TV-DO-OC"; //always precedes by a comma
    
    public final String[] USE_OF_NN = {
        "S", "SC", "IO", "A", "OC", "DO"
    };
    
    //Examples
    //1. She is a lawyer.
    //2. She is beautiful.
    //3. She dances gracefully.
    //4. She writes a letter.
    //5. She gives him a gift.
    //6. She buys a car, "Toyota".
    
    
    //INTERNVENING WORD GROUP DOES NOT AFFECT THE NUMBER of the SUBJECT 
    //in the sentence
    
    //COMPLEMENTS = is a word followed by a linking verb which refers back to the subject
    
    //APPOSITVE = My siblings, april and argie, are
                  //The teacher called the students, argie and lee, to do work.
    
    
    //Uses of Nouns
    //1. Subject
    //2. Subjective Complement
    //3. Indirect Objective
    //4. Appositive
    //5. Objective Complement
    //6. Direct Object (ans. the question what)
        
    //Cases of Nouns
    //1. Nominative = subjective or nominative (which means they act as the subject of independent or dependent clauses
    //2. Possessive = possessive (which means they show possession of something else
    //3. Objective  = objective (which means they function as the recipient of action or are the object of a preposition
    
    
    //Point of View Nouns
        
    
    
    //Three Types of Verbs
    //1. Action Verb 
    //2. Linking Verb  (is, are)
    //3. Helping Verb (has) 
    //4. Modal Verb (did, do, shall, shan't, will, may, can)
    
    
    //Transitive verb is an action verb.
    
    //She buys a car, "Toyota".
    //She = Pn
    //buys = VB
    //a = 
    //car = 
}
