/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Nathalia
 */
public class DataCleaner {
    
    WordnetProcess wp = new WordnetProcess();
    KeySearchProcess ksp;
    
    public DataCleaner(KeySearchProcess ksp)
    {
        this.ksp = ksp;
    }
    
    public DataCleaner()
    {
        this.ksp = new KeySearchProcess();
    }
    
    public String removeRepeatedCharacters(String input, int repeatNum)
    {
        System.out.println("input==>"+input);
        String rule = "(.)\\1+";
        StringBuffer result = new StringBuffer();
        if (!rule.isEmpty()) {
            Pattern p = Pattern.compile(rule);
            Matcher m = p.matcher(input);
           
            while (m.find()) {
                String rep = "";
                for(int i=0; i<repeatNum; i++)
                {
                    rep = rep+m.group(1);
                }
                m.appendReplacement(result, rep);
            }
            m.appendTail(result);
        }
        return result.toString();
    }
    
    private String removeUrl(String input)
    { 
        String rule = "((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
            + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
            + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
            + "((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+"   // named host
            + "(?:"   // plus top level domain
            + "(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])"
            + "|(?:biz|b[abdefghijmnorstvwyz])"
            + "|(?:cat|com|coop|c[acdfghiklmnoruvxyz])"
            + "|d[ejkmoz]"
            + "|(?:edu|e[cegrstu])"
            + "|f[ijkmor]"
            + "|(?:gov|g[abdefghilmnpqrstuwy])"
            + "|h[kmnrtu]"
            + "|(?:info|int|i[delmnoqrst])"
            + "|(?:jobs|j[emop])"
            + "|k[eghimnrwyz]"
            + "|l[abcikrstuvy]"
            + "|(?:mil|mobi|museum|m[acdghklmnopqrstuvwxyz])"
            + "|(?:name|net|n[acefgilopruz])"
            + "|(?:org|om)"
            + "|(?:pro|p[aefghklmnrstwy])"
            + "|qa"
            + "|r[eouw]"
            + "|s[abcdeghijklmnortuvyz]"
            + "|(?:tel|travel|t[cdfghjklmnoprtvwz])"
            + "|u[agkmsyz]"
            + "|v[aceginu]"
            + "|w[fs]"
            + "|y[etu]"
            + "|z[amw]))"
            + "|(?:(?:25[0-5]|2[0-4]" // or ip address
            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]"
            + "|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]"
            + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
            + "|[1-9][0-9]|[0-9])))"
            + "(?:\\:\\d{1,5})?)" // plus option port number
            + "(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&\\=\\#\\~"  // plus option query params
            + "\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?"
            + "(?:\\b|$)";
        
        Pattern p = Pattern.compile(rule,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input+" ");
        int i = 0;
        StringBuffer result = new StringBuffer();
        
        while (m.find()) {
            m.appendReplacement(result, "");
        }
        m.appendTail(result);
        return result.toString();
    }
    public String preserveCamelCaseWord(String input)
    {
        Pattern p = Pattern.compile("([a-zA-Z])([A-Z])");
        Matcher m = p.matcher(input);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(result,m.group(1)+" "+m.group(2));
        }
        m.appendTail(result);
        
        return result.toString();
    }
    
    public LinkedList<String> maximumMatchingWordSegmentation(String input)
    {
        LinkedList<String> result = new LinkedList();
        LinkedList<String> hold = new LinkedList();
        int start=0, end=input.length();
        do {
            String temp = input.substring(start, end);
            String tempp = temp+"["+start+"-"+end+"]";
            if(!hold.contains(tempp)&&(ksp.isAStopword(temp)||(temp.length()!=1 && wp.doesTermExist(temp))))
            {
                hold.add(tempp);
                result.add(temp);
                start = end;
                end = input.length();
            }
            else if(end-1==start&&!result.isEmpty())
            {
                temp = result.removeLast();
                start = start-temp.length();
                end = input.length();
            }
            else
            {
                end--;
            }
            
        } while(end!=start);
        
        return result;
    }
    
    public String removeEmoticons(String input)
    {
        String pattern = "[<>]?" //optional hat/brow
                + "[:;8BX=]" //eyes
                + "[\\Q-\\E\\Q~\\E\\Q'\\E\\Q^\\E]?" //optional nose
                + "[\\Q)\\E\\Q(\\E\\Q]\\E\\Q/\\EDP]"; 
        
////        # approach 1: pattern for "generic smiley"
//        eyes, noses, mouths = "", r"-~'^", r")(/\|DP"
//        pattern1 = "[%s][%s]?[%s]" % tuple(map(re.escape, [eyes, noses, mouths]))
//
////        # approach 2: disjunction of a list of smileys
//        smileys = """:-) :) :o) :] :3 :c) :> =] 8) =) :} :^) 
//                     :D 8-D 8D x-D xD X-D XD =-D =D =-3 =3 B^D""".split()
//        pattern2 = "|".join(map(re.escape, smileys))
//
//        text = "bla bla bla :-/ more text 8^P and another smiley =-D even more text"
//        print re.findall(pattern1, text)
        Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input+" ");
        int i = 0;
        StringBuffer result = new StringBuffer();
        
        while (m.find()) {
            m.appendReplacement(result, "");
        }
        m.appendTail(result);
        return result.toString();
    }
    public String preserveHashTagWords(String word)
    {
        System.out.println("preserveHashTagWords===>"+word);
                
        Pattern p = Pattern.compile("\\Q#\\E((([A-Z])|([a-z])|([0-9]))+)");
        Matcher m = p.matcher(word);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            String output = "";
            System.out.println("HALA DZAE==>"+m.group(0));
            if(m.group(3)!=null && m.group(4)!=null)
            {
                output = preserveCamelCaseWord(m.group(1))+".";
            }
            else
            {
                for(String temp : maximumMatchingWordSegmentation(m.group(1)))
                {
                    System.out.println("> "+temp);
                            
                    output = output+" "+temp;
                }
            }
            output = output.trim();
            output = output+".";
            m.appendReplacement(result," "+output);
        }
        m.appendTail(result);
        return result.toString();
    }
    
    public String removeTags(String input) {
     
        Pattern p = Pattern.compile("(<([A-Z]|(\\Q$\\E))+:)|(>)");
        Matcher m = p.matcher(input);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            
            m.appendReplacement(result," ");
        }
        m.appendTail(result);

        return result.toString();
    }
    public String cleanText(String st)
    {
        st = removeUrl(st);
        st = preserveHashTagWords(st);
        st = removeEmoticons(st);
        st = st.replaceAll("[^\\x00-\\x7F]", "");
        st = st.replace("<3", " ");
        st = st.replace(":p", " ");
        st = st.replace("\\t", " ");
        st = st.replace("\\n", " ");
        st = st.replace(">", " ");
        st = st.replace("<", " ");
        st = st.replace("(", " ");
        st = st.replace(")", " ");
        st = st.replace(":", " ");
        st = st.replace("=", " ");
        st = st.replace("<", " ");
        st = st.replace("&", "and");
        st = st.replace("/", " ");
        st = st.replace("\\", " ");
        st = st.replace("\\", " ");
        st = st.replace("+", " ");
        st = st.replace("|", " ");
        st = st.replace("#", " ");
        st = st.replace("%", " ");
        
        Pattern p = Pattern.compile("(\\Q.\\E|,|;|!|\\Q?\\E|:)(\\Q.\\E|,|;|!| |\\Q?\\E|:)*");
        Matcher m = p.matcher(st);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            
            m.appendReplacement(result," "+m.group(1)+" ");
        }
        m.appendTail(result);
        String newSt = result.toString();
        String finalResult="";
//        for(String n : newSt.split(" "))
//        {
//            if(hasMeaning(n)||n.trim().equalsIgnoreCase(".")||n.trim().equalsIgnoreCase(",")||
//                    n.trim().equalsIgnoreCase("?")||n.trim().equalsIgnoreCase("!")||n.trim().equalsIgnoreCase("and")
//                    ||n.trim().equalsIgnoreCase("nor")||n.trim().equalsIgnoreCase("or")||n.trim().equalsIgnoreCase("but"))
//            {
//                finalResult=finalResult+" "+n;
//            }
//        }
//        return finalResult;
            return newSt;
    }
    
}
