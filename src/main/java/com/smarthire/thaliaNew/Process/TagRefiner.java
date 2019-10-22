/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smarthire.thaliaNew.Interfaces.RegexRules;

/**
 *
 * @author Nathalia
 */
public class TagRefiner {
    
    private WordnetProcess wp = new WordnetProcess();
    private HashMap<String, String> rules = new HashMap();
    private LinguisticLibrary ll = new LinguisticLibrary();
    private String[] keys;
    
    
    Chinker chinker = new Chinker();
    
    public TagRefiner() {
        
        init();
    }
    
    public void init()
    {
        
        rules.put("ADJ_TO_NN", RegexRules.ADJ_TO_NN);
        rules.put("V_TO_JJ_1", RegexRules.V_TO_JJ_1);
        //rules.put("V_TO_JJ_2", RegexRules.V_TO_JJ_2);
    
        rules.put("V_TO_NN", RegexRules.V_TO_NN);
        rules.put("ADV_TO_VB", RegexRules.ADV_TO_VB);
    
        rules.put("IN_TO_DT", RegexRules.IN_TO_DT);
        rules.put("ADV_TO_JJ", RegexRules.ADV_TO_JJ);
        rules.put("POS_TO_VBZ", RegexRules.POS_TO_VBZ);
        rules.put("DT_TO_PDT", RegexRules.DT_TO_PDT);
        rules.put("NN_TO_VBP", RegexRules.NN_TO_VBP);
        rules.put("WONT_TO_WOULD_NOT", RegexRules.WONT_TO_WOULD_NOT);
        rules.put("CANT_TO_CANNOT", RegexRules.CANT_TO_CANNOT);
        rules.put("INCRT_VBG_TO_VBG", RegexRules.INCRT_VBG_TO_VBG);
        rules.put("GONNA_TO_GOING_TO", RegexRules.GONNA_TO_GOING_TO);

        
        rules.put("TO_PARALLEL", RegexRules.TO_PARALLEL);
        
        keys = ("ADJ_TO_NN,V_TO_JJ_1,V_TO_NN,ADV_TO_VB,IN_TO_DT,ADV_TO_JJ,"
                + "POS_TO_VBZ,DT_TO_PDT,NN_TO_VBP,WONT_TO_WOULD_NOT,CANT_TO_CANNOT,INCRT_VBG_TO_VBG,"
                + "GONNA_TO_GOING_TO,TO_PARALLEL").split(",");
    }
    
    public String getProbableTag(String input)
    {
        System.out.println("getProbablePostag==>"+input);
        Pattern p = Pattern.compile(RegexRules.TAG+RegexRules.EXTENSION+"_("+RegexRules.TAG+")(_(\\d)+)?");
        Matcher m = p.matcher(input);

        while (m.find()) {
            return m.group(6).trim();
        }
        return null;
    }
    
    public String renameTokens(String input)
    {
        String rules[] = {"(<VBZ:)('s)(>)",
            "(<VBP:)('re)(>)","(<RB:)(n't)(>)",
            "(<VBP:)('m)(>)","(<MD:)('d)(>)",
            "(<PRP:)('s)(>)","(<VB:|<VBP:)('ve)(>)",
            "(<MD:)('ll)(>)"};
        for(String rule : rules)
        {
            if (!rule.isEmpty()) {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);

                StringBuffer result = new StringBuffer();


                while (m.find()) {
                    String temp = "";
                    if(m.group(1).contentEquals("<PRP:") && m.group(2).contentEquals("'s"))
                        temp = "us";
                    else if(m.group(2).contentEquals("'s"))
                        temp = "is";
                    else if(m.group(2).contentEquals("'re"))
                        temp = "are";
                    else if(m.group(2).contentEquals("n't"))
                        temp = "not";
                    else if(m.group(2).contentEquals("'m"))
                        temp = "am";
                    else if(m.group(2).contentEquals("'d"))
                        temp = "would";
                    else if(m.group(2).contentEquals("'ve"))
                        temp = "have";
                    else if(m.group(2).contentEquals("'ll"))
                        temp = "will";
                    
                    m.appendReplacement(result, m.group(1)+temp+m.group(3));
                }
                m.appendTail(result);
                input = result.toString();
            }
        }
        return input;
    }
    
    public String renameSym(String input)
    {
        //String rule = "<(([^A-Z<>])+):(([^a-z<>])+)>";
        String rule = "<(([^A-Z<>])+):";
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);

            StringBuffer result = new StringBuffer();

            
            while (m.find()) {
                for(int i=0; i<m.groupCount(); i++)
                    System.out.println("renameSym m.group("+i+")"+m.group(i));
                //m.appendReplacement(result, "<SYM:"+m.group(3)+">");
                m.appendReplacement(result, "<SYM:");
            }
            m.appendTail(result);
            input = result.toString();
        }
        return input;
    }
    
    
//    public String processParallel(String input)
//    {
//        
//    }
    
    public String process(String input)
    { 
        System.out.println("==========================================================");
        System.out.println("TAG REFINER==>"+input);
        input = renameSym(input).replace("$", "S");;
        for(String key : keys)
        {
            String rule = rules.get(key);
            if (!rule.isEmpty()) {

                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);
                StringBuffer result = new StringBuffer();
                while (m.find()) {
                    

                    for(int i=0; i<m.groupCount(); i++)
                        System.out.println("tag m.group("+i+")===>"+m.group(i));
                    if(key.contentEquals("TO_PARALLEL"))
                    {
                        //m.appendReplacement(result, processParallel(m.group(0)));
                    }
                    else if(key.contentEquals("GONNA_TO_GOING_TO"))
                    {
                        String start="";
                        if(m.group(1)!=null)
                            start = m.group(1);
                        m.appendReplacement(result, start+"<VBG:going><TO:to>");
                    }
                    else if(key.contentEquals("WONT_TO_WOULD_NOT"))
                    {
                        String start="", end="";
                        if(m.group(1)!=null)
                            start = m.group(1);
                        if(m.group(11)!=null)
                            end = m.group(11);
                        m.appendReplacement(result, start+"<MD:would>"+end);
                    }
                    else if(key.contentEquals("CANT_TO_CANNOT"))
                    {
                        String start="", end="";
                        if(m.group(1)!=null)
                            start = m.group(1);
                        if(m.group(11)!=null)
                            end = m.group(11);
                        m.appendReplacement(result, start+"<MD:can>"+end);
                    }
                    else if(key.contentEquals("INCRT_VBG_TO_VBG"))
                    {
                        String start="", end="";
                        if(m.group(1)!=null)
                            start = m.group(1);
                        m.appendReplacement(result, start+"<VBG:"+m.group(9)+"g>");
                    }
                    else
                    {
                        String tag = getProbableTag(key);
                        String postag = ll.getCategoryPOS(tag);
                        String word = m.group(9);
                        boolean isValid = wp.doesWordWithCertainPostagExist(word, postag);
                        String base = wp.getBaseForm(word, postag);
                        
                        if(isValid && base!=null)
                        {
                            String start="", end="";
                            if(m.group(1)!=null)
                                start = m.group(1);
                            if(m.group(11)!=null)
                                end = m.group(11);
                            if(key.contentEquals("ADJ_TO_NN")&&end!=null&&ll.getCategoryPOS(m.group(12)).contentEquals("noun"))
                            {
                            }
                            else if(key.contentEquals("V_TO_JJ_2")&&!m.group(9).endsWith("ing")&&!m.group(9).endsWith("ed"))
                            {
                            }
                            else if(key.contentEquals("V_TO_JJ_2")&&!ll.isALinkingVerb(m.group(4)))
                            {
                            }
                            else
                            {
                                m.appendReplacement(result, start+"<"+tag+":"+word+">"+end);
                            }
                        }
                        
                    }
                }
                m.appendTail(result);
                input = result.toString();
            }
        }
        input = renameTokens(input);
        System.out.println("FINAL====>"+input);
        System.out.println("==========================================================");
        return input;
    }
}
