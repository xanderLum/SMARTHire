/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smarthire.thaliaNew.Interfaces.RegexRules;

/**
 *
 * @author Nathalia
 */
public class Chinker {
    
    StringEvaluator se = new StringEvaluator();
    
    
    public String chinkPrevSub(String input, LinkedList<String> phrase)
    {
        String rule = "(<((V(L)?(WE|WA|AM|S|P|B|N)O(S|P|N)?P|("+RegexRules.BIG_LETTER+")+INF)(C)?)"+RegexRules.INDEX +">)(<(CSUB(N)?(J)?(V)?)"+RegexRules.INDEX +">)";
        //System.out.println("HERE CHINK PREDICATE"+input);
        if (!rule.isEmpty()) {
            
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
            StringBuffer result = new StringBuffer();
            while (m.find()) {
//                for(int i=0; i<m.groupCount(); i++)
//                {
//                    System.out.println("hALA ka diaha!! m.group("+i+")==>"+m.group(i));
//                }
                String temp = se.getPhraseContent(m.group(1), phrase, false, "", false, true, true);
                m.appendReplacement(result, temp+m.group(12));
                
            }
            m.appendTail(result);
            input = result.toString();
        }
//        System.out.println("FINAL OUTPUT==>"+input);
        return input;
        
    }
    
    public String chinkPredicate(String input, LinkedList<String> phrase)
    {
        String rule = "(<(V(L)?(WE|WA|AM|S|P|B|N)O(S|P|N)?P(C)?)"+RegexRules.INDEX +">)(<(V(L)?(WE|WA|AM|S|P|B|N)O(S|P|N)?P(C)?)"+RegexRules.INDEX +">)";
        //System.out.println("HERE CHINK PREDICATE"+input);
        if (!rule.isEmpty()) {
            

            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
            StringBuffer result = new StringBuffer();
            while (m.find()) {
//                for(int i=0; i<m.groupCount(); i++)
//                {
//                    System.out.println("hALA!! m.group("+i+")==>"+m.group(i));
//                }
                String temp = se.getRightMostBase(phrase.get(Integer.parseInt(m.group(8))), phrase, true, se.NOUN_PREPOSITION+"(C)?", false, true);
//                System.out.println("CHINK PREDICATE temp =>"+temp);
                String hold = se.getRightMostBase(m.group(1), phrase, true, se.NOUN_PREPOSITION+"(C)?", true, true);
//                System.out.println("CHINK PREDICATE hold =>"+hold);
                phrase.set(Integer.parseInt(m.group(8)), (hold).replace(temp, ""));
//                System.out.println("CHINK PREDICATE phrase =>"+phrase.get(Integer.parseInt(m.group(8))));
                m.appendReplacement(result, m.group(1)+temp+m.group(10));
                
            }
            m.appendTail(result);
            input = result.toString();
        }
//        System.out.println("FINAL OUTPUT==>"+input);
        return input;
    }
    
    
    public String chinkPhrase(String input, LinkedList<String> phrase)
    {
        String rule = "(((<("+RegexRules.VERB_PHRASE_TAG+")"+RegexRules.INDEX +">)"
            + "(<("+RegexRules.NOUN_PHRASE_TAG+"|"+RegexRules.NOUN_PHRASE_YOU_TAG+"|"+RegexRules.NOUN_PHRASE_OBJECT_TAG+")"+RegexRules.INDEX +">)?"
            + "(<((NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)PP)|(NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)PC))"+ RegexRules.INDEX +">))|(<(PP)"+RegexRules.INDEX +">))"
            + "(<("+RegexRules.VERB_PHRASE_TAG+")("+RegexRules.INDEX +"|:"+RegexRules.TOKEN+")>)";
        
        if (!rule.isEmpty()) {
            

            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
            StringBuffer result = new StringBuffer();
            while (m.find()) {
                
//                for(int i=0; i<m.groupCount(); i++)
//                {
//                    System.out.println("hey mama!! m.group("+i+")==>"+m.group(i));
//                }
                if(m.group(37)!=null && m.group(37).isEmpty())
                {
                    m.appendReplacement(result, se.getRightMostBase(m.group(1), phrase, true, se.NOUN+"(C)?", true, true)+m.group(42));
                }
                else if(m.group(30)!=null && !m.group(30).isEmpty())
                {
                    m.appendReplacement(result, se.getRightMostBase(m.group(1), phrase, true, se.NOUN+"(C)?", true, true)+m.group(42));
                }
                else
                {
                    m.appendReplacement(result, se.getRightMostBase(m.group(1), phrase, true, se.NOUN, true, true)+m.group(42));
                }
            }
            m.appendTail(result);
            input = result.toString();
        }
//        System.out.println("FINAL OUTPUT==>"+input);
        return input;
    }
    
    
}
