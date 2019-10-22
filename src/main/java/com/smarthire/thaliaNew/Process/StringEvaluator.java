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
public class StringEvaluator {
    
    public final String CLAUSE = "(CSUB(N)?(J)?(V)?|CSVOINM|CSSWEVO|CPSWEVO|CSSBVO|CPSBVO|CVOIMM|CSVO)";
    public final String PREDICATE = "(V(L)?(BE|WE|WA|AM|S|P|B|N)O(S|P|N)?P)";
    public final String NOUN_PREPOSITION = "NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)P(P)?";
    public final String NOUN = "NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)P";
    public final String NOUN_TOKEN = "N(S|P)(P)?";
    public final String NOUN_BASE = "NN(P)?(S)?";
    public final String ADJECTIVE_NOUN_BASE = "(NN(P)?(S)?|ADJ)";
    public final String ADJECTIVE = "ADJ";
    public final String ADVERB = "ADV";
    public final String VERB = "VB(Z|P|D|G|N)?(C)?";
    public final String BASE = "(NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)P(C)?|CSUB(N)?(J)?(V)?|PP|ADJ(S)?(C)?|PRP|INF)";
    
    public boolean isValidAccToRule(String input, String rule)
    {
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(input.trim());
        
        return m.matches();
    }
    
    public String getSpecificContent(String input, String ruleName, LinkedList<String> phrase, String limit, boolean hasLimit, boolean isShallow)
    {
        StringBuffer result = new StringBuffer();
        if (!ruleName.isEmpty()) {
            Pattern p = Pattern.compile("<"+ ruleName +"("+RegexRules.EXTENSION+")?"+ RegexRules.INDEX+">");
            Matcher m = p.matcher(input);
           
            while (m.find()) {
                m.appendReplacement(result, getPhraseContent(m.group(0), phrase, hasLimit, limit, false, true, isShallow));
            }
            m.appendTail(result);
        }
        return result.toString();
    }
    
    public String getLeftMostBase(String input, LinkedList<String> phrase, boolean hasLimit,
            String limitType, boolean isComplete, boolean isChink)
    {
        StringBuffer result = new StringBuffer();
        String rule="<START>(<((("+RegexRules.BIG_LETTER+")+)(_("+RegexRules.BIG_LETTER+")+)*("+RegexRules.INDEX+")|(("+RegexRules.BIG_LETTER+")+):(("+RegexRules.SMALL_LETTER+")+))>)";
        
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher("<START>"+input);
            while (m.find()) {
                
                for(int i=0; i<m.groupCount(); i++)
                    System.out.println("THANK YOU!!!==>>"+m.group(i));
                if(m.group(3)!=null)
                {
                    if(!hasLimit || (hasLimit && (!(isValidAccToRule(m.group(3), limitType)))))
                    {
                        String temp = getLeftMostBase(phrase.get(Integer.parseInt(m.group(9))),phrase,hasLimit,limitType,isComplete,isChink);
                
                        if(isComplete)
                        {
                            if(isChink)
                                m.appendReplacement(result, temp);
                            else
                                m.appendReplacement(result, "<"+temp+">");
                        }
                        else
                        {
                            return temp;
                        }
                    }
                    else if(!isComplete)
                    {
                         return m.group(0).replace("<START>", "");
                    }
                }
                else if(!isComplete)
                {
                    return "<"+m.group(11)+":"+m.group(13)+">";
                }
                else{
                    m.appendReplacement(result, m.group(1));
                }
                
            }
            m.appendTail(result);
            input = result.toString().replace("<START>", "");
        }
        return input;
    }
    
    
    public String[] getSpecificPartAccToRuleName(String input, String ruleName)
    {
        return getSpecificPartAccToRule(input, "<"+ ruleName +"("+RegexRules.EXTENSION+")?"+ RegexRules.INDEX+">");
    }
    
    public String replaceSpecificPartAccToRuleName(String input, String ruleName, String replacement)
    {
        return replaceSpecificPartAccToRule(input, "<"+ ruleName +"("+RegexRules.EXTENSION+")?"+ RegexRules.INDEX+">", replacement);
    }
    
    public String replaceSpecificPartAccToRule(String input, String rule, String replacement)
    {
        StringBuffer result = new StringBuffer();
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
           
            while (m.find()) {
                m.appendReplacement(result, "");
            }
            m.appendTail(result);
        }
        return result.toString();
    }
    
    public String[] getSpecificPartAccToRule(String input, String rule)
    {
        String result="";
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
           
            while (m.find()) {
                result=result+" "+m.group(0);
            }
        }
        if(!result.isEmpty())
            return result.trim().split(" ");
        else
            return new String[]{};
    }
    
    public String getRightMostBase(String input, LinkedList<String> phrase, boolean hasLimit,
            String limitType, boolean isComplete, boolean isChink)
    {
        System.out.println("getRightMostBase input==>"+input);
        StringBuffer result = new StringBuffer();
        String rule="(<((("+RegexRules.BIG_LETTER+")+)(_("+RegexRules.BIG_LETTER+")+)*("+RegexRules.INDEX+")|(("+RegexRules.BIG_LETTER+")+):(("+RegexRules.SMALL_LETTER+")+))>)<END>";
        
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input+"<END>");
            while (m.find()) {
                
                for(int i = 0; i<m.groupCount(); i++)
                    if(m.group(i)!=null)
                        System.out.println("GRMB Hey mama.."+i+"-->"+m.group(i));
                if(m.group(3)!=null)
                {
                    if(!hasLimit || (hasLimit && (!(isValidAccToRule(m.group(3), limitType)))))
                    {
                        System.out.println("OMY m.group(9)===>"+m.group(9)+"<=>"+phrase.get(Integer.parseInt(m.group(9))));
                        String temp = getRightMostBase(phrase.get(Integer.parseInt(m.group(9))),phrase,hasLimit,limitType,isComplete,isChink);
                
                        if(isComplete)
                        {
                            if(isChink)
                                m.appendReplacement(result, temp);
                            else
                                m.appendReplacement(result, "<"+temp+">");
                        }
                        else
                        {
                            return temp;
                        }
                    }
                    else if(!isComplete)
                    {
                         return m.group(0).replace("<END>", "");
                    }
                }
                else if(!isComplete)
                {
                    return "<"+m.group(11)+":"+m.group(13)+">";
                }
                else{
                    m.appendReplacement(result, m.group(1));
                }
                
            }
            m.appendTail(result);
            input = result.toString().replace("<END>", "");
        }
        System.out.println("getRightMostBase output==>"+input);
        return input;
    }
    
    public String transformConnectorsToOperators(String input)
    {
        return (((input.replace("<CC:and>", "+")).replace("<CC:or>", "+")).replace("<CC:nor>", "+")).replace("<CC:but>", "+").replace("<SYM:,>", "+");
    }
    
    public String getPhraseContent(String temp, LinkedList<String> phrase, boolean hasLimit, 
            String limitType, boolean isPrevCompound, boolean isChink, boolean isShallow)
    {
        if(isPrevCompound)
        {
            temp = transformConnectorsToOperators(temp);
        }
        StringBuffer result = new StringBuffer();
        String rule="<(("+RegexRules.BIG_LETTER+")+)(_("+RegexRules.BIG_LETTER+")+)*("+RegexRules.INDEX+")>";
        
        
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(temp);

            while (m.find()) {
                
                if(isShallow)
                {
                    m.appendReplacement(result,phrase.get(Integer.parseInt(m.group(7))));
                }
                else if(!hasLimit || (hasLimit && (!(isValidAccToRule(m.group(1), limitType)))))
                {
                    if(isValidAccToRule(m.group(1), "((V(L)?(BE|WE|WA|AM|S|P|B|N)O(S|P|N)?P)|"+
                            "(NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)P(P)?)|(N(S|P)(P)?)|(NN(P)?(S)?)|"+
                            "(ADJ)(S)?|(ADV)(S)?|(VB(Z|P|D|G|N)(L|LL)?))C"))
                    {
                        if(isChink)
                        {
                            m.appendReplacement(result, getPhraseContent(phrase.get(Integer.parseInt(m.group(7))),phrase,hasLimit,limitType,true,isChink, isShallow));
                        }
                        else{
                            m.appendReplacement(result, "<"+getPhraseContent(phrase.get(Integer.parseInt(m.group(7))),phrase,hasLimit,limitType,true,isChink,isShallow)+">");
                        }
                    }
                    else
                    {
                        if(isChink)
                        {
                            m.appendReplacement(result, getPhraseContent(phrase.get(Integer.parseInt(m.group(7))),phrase,hasLimit,limitType,false,isChink,isShallow));
                        }
                        else{
                            m.appendReplacement(result, "<"+getPhraseContent(phrase.get(Integer.parseInt(m.group(7))),phrase,hasLimit,limitType,false,isChink,isShallow)+">");
                        }
                    }
                }
            }
            m.appendTail(result);
            temp = result.toString();
        }
        return temp;
    }
    
    public String getBasePartLine(String input, String choice)
    {
        String result = "";
        String rule = "<(("+RegexRules.BIG_LETTER+")+):("+RegexRules.TOKEN+")>";
        
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
            
            while (m.find()) {
                
                if(choice.contentEquals("TOKEN"))
                    result = result +" "+ m.group(3);
                else if(choice.contentEquals("TAG"))
                    result = result +" "+ m.group(1);
                else if(choice.contentEquals("TAG_TOKEN"))
                    result = result +" "+m.group(1) +":"+ m.group(3);
                    
            }
        }
        return result;
    }
    
    public String getPartLine(String input, String choice)
    {
        
        String result = "";
        String rule = "<(("+RegexRules.BIG_LETTER+")+)(_("+RegexRules.BIG_LETTER+")+)?("+RegexRules.INDEX+")>";
        
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
            
            while (m.find()) {
                
                if(choice.contentEquals("TAG"))
                    result = result +" "+ m.group(1);
                else if(choice.contentEquals("INDEX"))
                    result = result +" "+m.group(5);
            }
        }
        
        return result;
    }
    
    public LinkedList<String> getUnion(LinkedList<String> s1, LinkedList<String> s2)
    {
        s1.addAll(s2);
        return s1;
    }
    
    public LinkedList<String> getIntersection(LinkedList<String> s1, LinkedList<String> s2)
    {
        LinkedList<String> output = new LinkedList();
        for(String temp1 : s1)
        {
            if(s2.contains(temp1))
            {
                output.add(temp1);
            }
        }
        return output;
    }
    
    public int getIndex(String input)
    {
        try{
            Pattern p = Pattern.compile(RegexRules.INDEX);
            Matcher m = p.matcher(input);

            String index="";

            while (m.find()) {
                index = m.group(2);
            }

            return Integer.parseInt(index);
        }catch(Exception e)
        {
            return -1;
        }
    }
    
    public String[] getMainVerb(String input, LinkedList<String> phrase)
    {
        String mv = getRightMostBase(input, phrase, true, VERB+"(C)?", false, true);
        if(isValidAccToRule(mv, "<"+RegexRules.TAG+":"+RegexRules.TOKEN+">"))
            return new String[]{mv};
        else
            return getSpecificPartAccToRuleName(getPhraseContent(mv, phrase, true, VERB, false, true,false), VERB);
            
    }
    
}
