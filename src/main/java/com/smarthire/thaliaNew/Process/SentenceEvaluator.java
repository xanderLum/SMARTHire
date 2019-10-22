/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smarthire.thaliaNew.Interfaces.RegexRules;
import com.smarthire.thaliaNew.Model.ClauseData;
import com.smarthire.thaliaNew.Model.PhraseData;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.WordInfo;

/**
 *
 * @author Nathalia
 */
public class SentenceEvaluator {
    
    private Chunker chunker;
    private StringEvaluator se;
    private ClauseEvaluator ce;
    HashMap<String, String> rules = new HashMap();
    KeySearchProcess ksp = new KeySearchProcess();
    
    public SentenceEvaluator() {
    
        this.chunker = new Chunker();
        se = new StringEvaluator();
        ksp = new KeySearchProcess();
        ce = new ClauseEvaluator(ksp);
        
        rules.put("SENTENCE_QUOTE_CLAUSE_CLAUSE", RegexRules.SENTENCE_QUOTE_CLAUSE_CLAUSE);
        rules.put("SENTENCE_CLAUSE_QUOTE_CLAUSE", RegexRules.SENTENCE_CLAUSE_QUOTE_CLAUSE);
        rules.put("SENTENCE_SUB_CONNECTOR_CLAUSE_CLAUSE", RegexRules.SENTENCE_SUB_CONNECTOR_CLAUSE_CLAUSE);
        rules.put("SENTENCE_CLAUSE_SUB_CONNECTOR_CLAUSE", RegexRules.SENTENCE_CLAUSE_SUB_CONNECTOR_CLAUSE);
        rules.put("SENTENCE_COR_CONNECTOR_CLAUSE_CLAUSE", RegexRules.SENTENCE_COR_CONNECTOR_CLAUSE_CLAUSE);
        rules.put("SENTENCE_COR_CONNECTOR_CLAUSE", RegexRules.SENTENCE_COR_CONNECTOR_CLAUSE);
        rules.put("SENTENCE_CLAUSE_CLAUSE", RegexRules.SENTENCE_CLAUSE_CLAUSE);
        rules.put("SENTENCE_CLAUSE", RegexRules.SENTENCE_CLAUSE);
        rules.put("SENTENCE", RegexRules.SENTENCE);
    }
    
    
    public SentenceData detectSentenceStructure(String content, String input, LinkedList<String> phrases, HashMap<String, String> error)
    {
        String[] keySentencePhaseSet = ("SENTENCE_QUOTE_CLAUSE_CLAUSE,SENTENCE_CLAUSE_QUOTE_CLAUSE,"
                + "SENTENCE_SUB_CONNECTOR_CLAUSE_CLAUSE,SENTENCE_CLAUSE_SUB_CONNECTOR_CLAUSE,"
                + "SENTENCE_COR_CONNECTOR_CLAUSE_CLAUSE,SENTENCE_COR_CONNECTOR_CLAUSE,"
                + "SENTENCE_CLAUSE_CLAUSE,SENTENCE_CLAUSE,SENTENCE").split(",");
        LinguisticLibrary ll = new LinguisticLibrary();
        HashMap<String, LinkedList<ClauseData>> clauseData = new HashMap();
        boolean flag;
        System.out.println("START chunkSentence===>"+content+"<--->"+input);
        
        for(String c : phrases)
        {
        	System.out.println("***HAYYY..."+c);
        }
        
        do {
            
            flag = false;
            for (String name : keySentencePhaseSet) {
                System.out.println(">>"+name);
                String rule = rules.get(name);
                if(!rule.isEmpty())
                {
                    Pattern p = Pattern.compile(rule);
                    Matcher m = p.matcher(input);
                    StringBuffer result = new StringBuffer();
                    while (m.find()) {
                        flag = true;
                        String clauseType;
                        String temp = m.group(0);
                        System.out.println("*HEY!!! name==>"+name);
                        System.out.println("*HEY!!! content 1==>"+m.group(0));
                        System.out.println("*HEY!!! content 2==>"+se.getPhraseContent(m.group(0), phrases, false, "", false, true, false));
                                
                        
                        for(int i=0; i<m.groupCount(); i++)
                        {
                            if(m.group(i)!=null)
                            System.out.println("CHUNK S m.group("+i+")==>"+m.group(i));
                        }
                        HashMap<String, LinkedList<ClauseData>> hold = new HashMap();
                        //ClauseData(String content, String mood, String subMood, String type)
                        
                        
                        if(name.contentEquals("SENTENCE_CLAUSE_QUOTE_CLAUSE"))
                        {
                            LinkedList<String> speaker = new LinkedList(), receiver = new LinkedList();
                            if(!m.group(2).contentEquals("S"))
                            {
                                hold.put(m.group(1),ce.getClauses(m.group(1),phrases,false));
                                String pattern = hold.get(m.group(9)).get(hold.get(m.group(9)).size()-1).getSentencePattern();
                                if(!pattern.contentEquals("PASSIVE_PATTERN"))
                                {
                                    speaker = hold.get(m.group(9)).get(hold.get(m.group(9)).size()-1).getParts().get("SUBJECT").getRoleA().getEquation();
                                    receiver = hold.get(m.group(9)).get(hold.get(m.group(9)).size()-1).getParts().get("TO_PREPOSIION").getRoleA().getEquation();
                                }
                            }
                            if(!m.group(9).contentEquals("S"))
                            {
                                hold.put(m.group(8),ce.getClauses(m.group(8),phrases,false));
                                
                            }
                            //cr.processSpeakerSieve(m.group(8), clauseData, phrases, speaker, receiver);
                        }
                        else if(name.contentEquals("SENTENCE_QUOTE_CLAUSE_CLAUSE"))
                        {
                            String speaker="", receiver="";
                            if(!m.group(10).contentEquals("S"))
                            {
                                hold.put(m.group(9),ce.getClauses(m.group(9),phrases,false));
                                String pattern = hold.get(m.group(9)).get(hold.get(m.group(9)).size()-1).getSentencePattern();
//                                if(!pattern.contentEquals("PASSIVE_PATTERN"))
//                                {
//                                    speaker = ((PhrasePointer)phrases.get(hold.get(m.group(9)).get(hold.get(m.group(9)).size()-1).getRoles().get("SUBJECT")).getHeadIndex());
//                                    receiver = ((PhrasePointer)phrases.get(hold.get(m.group(9)).get(hold.get(m.group(9)).size()-1).getPhrases().get("TO_PREPOSIION")).getHeadIndex());
//                                }
                            }
                            if(!m.group(2).contentEquals("S"))
                            {
                                hold.put(m.group(1),ce.getClauses(m.group(1),phrases,false));
                            }
                            
                        }
                        else if(name.contentEquals("SENTENCE_SUB_CONNECTOR_CLAUSE_CLAUSE"))
                        {
                            if(!m.group(2).contentEquals("S"))
                            {
                                int index = Integer.parseInt(m.group(8));
                                System.out.println("HALER!! 1 "+index+"==> "+phrases.get(index));
                                hold.put(m.group(1),ce.getClauses(phrases.get(index),phrases,false));
                                
                            }
                            if(!m.group(12).contentEquals("S"))
                            {
                                hold.put(m.group(11),ce.getClauses(m.group(11),phrases,false));
                            }
                        }
                        else if(name.contentEquals("SENTENCE_CLAUSE_SUB_CONNECTOR_CLAUSE"))
                        {
                            
                            if(!m.group(2).contentEquals("S"))
                            {
                                hold.put(m.group(1),ce.getClauses(m.group(1),phrases,false));
                            }
                            if(!m.group(9).contentEquals("S"))
                            {
                                int index = Integer.parseInt(m.group(15));
                                System.out.println("HALER!! 1 "+index+"==> "+phrases.get(index));
                                hold.put(m.group(8),ce.getClauses(phrases.get(index),phrases,false));
                            }
                        }
                        else if(name.contentEquals("SENTENCE_COR_CONNECTOR_CLAUSE_CLAUSE"))
                        {
                            String conn = se.getBasePartLine(se.getPhraseContent(m.group(10), phrases, flag, "", flag, true,false), "TOKEN");
                            String type = ll.getConnectorGenericSemanticRole(conn);
                            System.out.println("CONN TYPE==>"+type);
                            if(!m.group(2).contentEquals("S"))
                            {
                                hold.put(m.group(1),ce.getClauses(m.group(1),phrases,false));
                            }
                            if(!m.group(21).contentEquals("S"))
                            {
                                hold.put(m.group(20),ce.getClauses(m.group(20),phrases,false));
                            }
                            
                        }
                        else if(name.contentEquals("SENTENCE_COR_CONNECTOR_CLAUSE"))
                        {
                            String conn = se.getBasePartLine(se.getPhraseContent(m.group(1), phrases, flag, "", flag, true,false), "TOKEN");
                            String type = ll.getConnectorGenericSemanticRole(conn);
                            if(!m.group(20).contentEquals("S"))
                            {
                                hold.put(m.group(19),ce.getClauses(m.group(19),phrases,false));
                            }
                        }
                        else if(name.contentEquals("SENTENCE_CLAUSE_CLAUSE"))
                        {
                            if(!m.group(2).contentEquals("S") && !m.group(9).contentEquals("S"))
                            {
                                phrases.add(m.group(8));
                                String ttemp = se.getSpecificContent(phrases.get(Integer.parseInt(m.group(6))), se.PREDICATE, phrases, "", false,true);
                                temp = chunker.chunk(chunker.chunk(ttemp+"<CSUBNJ[" + (phrases.size()-1) + "]>", phrases, chunker.PREDICATE_KEY_SET, false, error), phrases, chunker.CLAUSE_KEY_SET, false, error);
                                
                                hold.put(temp,ce.getClauses(temp,phrases,false));
                            }
                        }
                        else if(name.contentEquals("SENTENCE_CLAUSE"))
                        {
                            if(m.group(1).contentEquals("CSVOC"))
                            {
                                String tempp = se.getPhraseContent(m.group(0), phrases, true, se.CLAUSE, false, true, false);
                                
                                for(String k1 : se.getSpecificPartAccToRuleName(tempp, se.CLAUSE))
                                {
                                    hold.put(k1,ce.getClauses(k1,phrases,false));
                                }
                            }
                            if(!m.group(1).contentEquals("S"))
                            {
                                hold.put(m.group(0),ce.getClauses(m.group(0),phrases,false));
                            }
                        }
                        else if(name.contentEquals("SENTENCE"))
                        {
                            if(m.group(3)!=null)
                            {
                                int index = Integer.parseInt(m.group(7));
                                hold.put(m.group(1),ce.getClauses(phrases.get(index),phrases,false));
                                if(!error.containsKey("Sentence Fragment"))
                                        error.put("Sentence Fragment","1");
                                    else
                                        error.replace("Sentence Fragment",""+(Integer.parseInt(error.get("Sentence Fragment"))+1));
                            }
                        }
                        
                        for(String key : hold.keySet())
                        {
                            System.out.println("DOT key==>"+key);
                            if(hold.get(key)!=null)
                            {
                                clauseData.put(key, hold.get(key));
                                for(ClauseData cd : hold.get(key))
                                {
                                    System.out.println("HEAD'2==>"+cd.getHead());
                                    if(!cd.getMood().contentEquals("SUBJUNCTIVE_MOOD") && se.isValidAccToRule(se.getPartLine(cd.getHead(), "TAG").trim(), "(CSSWEVO|CSSBVO|CSVO)"))
                                    {
                                        if(!error.containsKey("Subject-Verb Agreement"))
                                            error.put("Subject-Verb Agreement","1");
                                        else
                                            error.replace("Subject-Verb Agreement",""+(Integer.parseInt(error.get("Subject-Verb Agreement"))+1));
                                    }
                                    if(cd.getSentencePattern().contentEquals("SUBJECT_COMPLEMENT"))
                                    {
                                        String subjNum="", subjCompNum="";
                                        
                                        if(cd.getParts().containsKey("SUBJECT") && cd.getParts().get("SUBJECT").getType().contentEquals("CLAUSE"))
                                            subjNum = "singular";
                                        else if(cd.getParts().containsKey("SUBJECT") && cd.getParts().get("SUBJECT").getType().contentEquals("PHRASE"))
                                            subjNum = cd.getParts().get("SUBJECT").getNumber();
                                        if(cd.getParts().containsKey("SUBJECT_COMPLEMENT") && cd.getParts().get("SUBJECT_COMPLEMENT").getType().contentEquals("CLAUSE"))
                                            subjCompNum = "singular";
                                        else if(cd.getParts().containsKey("SUBJECT_COMPLEMENT") && cd.getParts().get("SUBJECT_COMPLEMENT").getType().contentEquals("PHRASE")
                                                && cd.getParts().get("SUBJECT_COMPLEMENT").getRoleA().getType().contentEquals("ADJECTIVE_PHRASE"))
                                            subjCompNum = "";
                                        else if(cd.getParts().containsKey("SUBJECT_COMPLEMENT") && cd.getParts().get("SUBJECT_COMPLEMENT").getType().contentEquals("PHRASE")
                                                && cd.getParts().get("SUBJECT_COMPLEMENT").getRoleA().getType().contentEquals("NOUN_PHRASE"))
                                            subjCompNum = cd.getParts().get("SUBJECT_COMPLEMENT").getNumber();
                                        if(!subjNum.isEmpty() && !subjCompNum.isEmpty() && !subjCompNum.contentEquals(subjNum))
                                        {
                                            if(!error.containsKey("Subject-Subject_Complememt Agreement"))
                                                error.put("Subject-Subject_Complememt Agreement","1");
                                            else
                                                error.replace("Subject-Subject_Complememt Agreement",""+(Integer.parseInt(error.get("Subject-Subject_Complememt Agreement"))+1));
                                        }
                                    }
                                    else if(cd.getSentencePattern().contentEquals("OBJECT_COMPLEMENT"))
                                    {
                                        String objNum="", objCompNum="";
                                        
                                        if(cd.getParts().containsKey("DIRECT_OBJECT") && cd.getParts().get("DIRECT_OBJECT").getType().contentEquals("CLAUSE"))
                                            objNum = "singular";
                                        else if(cd.getParts().containsKey("DIRECT_OBJECT") && cd.getParts().get("DIRECT_OBJECT").getType().contentEquals("PHRASE"))
                                            objNum = cd.getParts().get("DIRECT_OBJECT").getNumber();
                                        
                                        if(cd.getParts().containsKey("OBJECT_COMPLEMENT") && cd.getParts().get("OBJECT_COMPLEMENT").getType().contentEquals("CLAUSE"))
                                            objCompNum = "singular";
                                        else if(cd.getParts().containsKey("OBJECT_COMPLEMENT") && cd.getParts().get("OBJECT_COMPLEMENT").getType().contentEquals("PHRASE")
                                                && cd.getParts().get("OBJECT_COMPLEMENT").getType().contentEquals("ADJECTIVE_PHRASE"))
                                            objCompNum = "";
                                        else if(cd.getParts().containsKey("OBJECT_COMPLEMENT") && cd.getParts().get("OBJECT_COMPLEMENT").getType().contentEquals("PHRASE")
                                                && cd.getParts().get("OBJECT_COMPLEMENT").getType().contentEquals("NOUN_PHRASE"))
                                            objCompNum = cd.getParts().get("OBJECT_COMPLEMENT").getNumber();
                                        if(!objNum.isEmpty() && !objCompNum.isEmpty() && !objCompNum.contentEquals(objNum))
                                        {
                                            if(!error.containsKey("Object-Object_Complememt Agreement"))
                                                error.put("Object-Object_Complememt Agreement","1");
                                            else
                                                error.replace("Object-Object_Complememt Agreement",""+(Integer.parseInt(error.get("Object-Object_Complememt Agreement"))+1));
                                        }
                                    }
                                    
                                }
                            }
                        }
                        
                        System.out.println("END OF LINE ==>"+temp);
                        phrases.add(temp);
                        temp = "[" + (phrases.size()-1) + "]";
                        if(!name.contentEquals("SENTENCE"))
                        {
                            m.appendReplacement(result, "<S" +  temp + ">");
                        }
                        else
                        {
                            m.appendReplacement(result, "<SE" +  temp + ">");
                        }
                        
                    }
                    m.appendTail(result);
                    input = result.toString();
                }
            }
        } while(flag);
        System.out.println("ENDest OF LINE ==> "+input);
        
        
                
        return new SentenceData(transformChunks(phrases), content, clauseData, error, input);
    }
    
    public String transformChunks(LinkedList<String> phrases)
    {
    	String result = "";
    	for(String p : phrases)
    	{
    		result = result+" "+p;
    	}
    	System.out.println("HERE DZAE RESULT==>"+result);
    	return result.trim();
    }
    
}
