/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import edu.mit.jwi.item.IWord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.SocketUtils;

import com.smarthire.thaliaNew.Model.WordInfo;

/**
 *
 * @author Nathalia
 */
public final class KeySearchProcess {
 
	//String path = "C:\\Users\\Nathalia\\Desktop\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew\\dat";
	String path = "D:\\THESIS TO GO MAN\\Final Integration\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew\\dat";
	
	private final HashMap<String, LinkedList<String>> categoryKeyword;
    private final HashMap<String, LinkedList<String>> categorySpecialKeyword;
    private final LinkedList<String> negativeWords;
    private final LinkedList<String> positiveWords;
    private final LinkedList<String> stopWords;
    private final LinkedList<String> bisayaWords;
    private final LinkedList<String> tagalogWords;
    private final LinkedList<String> profanityWords;
    
    public KeySearchProcess()
    {
        categoryKeyword = new HashMap();
        categorySpecialKeyword = new HashMap();
        negativeWords = new LinkedList();
        positiveWords = new LinkedList();
        stopWords = new LinkedList();
        profanityWords = new LinkedList();
        bisayaWords = new LinkedList();
        tagalogWords = new LinkedList();
        
        profanityWords.addAll(initAddTerms(path+"\\Special Keywords\\sk-profane-bis.txt"));
        profanityWords.addAll(initAddTerms(path+"\\Special Keywords\\sk-profane.txt"));
        profanityWords.addAll(initAddTerms(path+"\\Special Keywords\\sk-profane-tag.txt"));
        stopWords.addAll(initAddTerms(path+"\\StopWords.txt"));
        bisayaWords.addAll(initAddTerms(path+"\\bisaya terms.txt"));
        negativeWords.addAll(initAddTerms(path+"\\negative words.txt"));
        positiveWords.addAll(initAddTerms(path+"\\positive words.txt"));
        initSpecialKeyWord();
        initKeyWord();
        
       // initBisayaWords();
    }

    public LinkedList<String> transformToLinkedList(String[] input)
    {
        LinkedList<String> result = new LinkedList();
        result.addAll(Arrays.asList(input));
        return result;
    }
    
    private LinkedList<String> initAddTerms(String path)
    {
        LinkedList<String> result = new LinkedList();
        FileReader fr;
        try {
            fr = new FileReader(path);
        
            BufferedReader br = new BufferedReader(fr);
            String input_string;
            while((input_string = br.readLine())!=null)
            {
                result.addAll(transformToLinkedList(input_string.toLowerCase().split("\t")));
            }
        } catch (Exception ex) {
        }
        return result;
    }
    
    private void initKeyWord()
    {
        //[name, postag, sexRelatedPnt, drugRelatedPnt, alcoholRelatedPnt, politicsRelatedPnt, charityRelatedPnt, gunRelatedPnt, isAmbiguous]
        FileReader fr;
        
        
       // String cat[] = {"alcohol", "drug", "gun", "charity", "politics","profane", "sex"};
       String cat[] = {"alcohol", "drug", "gun", "politics","profane", "sex"};
        try {
            for(int i=0; i<cat.length; i++)
            {
                categoryKeyword.put(cat[i], initAddTerms(path+"\\keywords-"+cat[i]+".txt"));
            }
            
        } catch (Exception ex) {
        }
    }
    
    private void initSpecialKeyWord()
    {
        //[name, postag, sexRelatedPnt, drugRelatedPnt, alcoholRelatedPnt, politicsRelatedPnt, charityRelatedPnt, gunRelatedPnt, isAmbiguous]
        FileReader fr;
        
        
        String cat[] = {"alcohol", "drug", "gun", "charity", "politics","profane", "sex"};
        try {
            for(int i=0; i<cat.length; i++)
            {
                categorySpecialKeyword.put(cat[i], initAddTerms(path+"\\Special Keywords\\sk-"+cat[i]+"-bis.txt"));
                categorySpecialKeyword.put(cat[i], initAddTerms(path+"\\Special Keywords\\sk-"+cat[i]+".txt"));
                categorySpecialKeyword.put(cat[i], initAddTerms(path+"\\Special Keywords\\sk-"+cat[i]+"-tag.txt"));
            }
            
        } catch (Exception ex) {
        }
    }
    
    public int getPolarityPoint(String input, String pos)
    {
        SentiWordnet sw = new SentiWordnet();
        input = input.toLowerCase().trim();
        int pnt = Integer.parseInt(sw.getSentiWord(input, pos));
        if(pnt==0)
        {
            if(positiveWords.contains(input))
            {
                return 1;
            }
            else if(negativeWords.contains(input))
            {
                return -1;
            }
        }
        
        return pnt;
    }
    
    public WordInfo getWordInfo(String input, String postag)
    {
        return getWordInfoWithLimitations(input, postag, false, null);
    }
    
    public WordInfo getWordInfoWithLimitations(String input, String postag, boolean hasLimit, LinkedList<String> lexnames)
    {
        WordnetProcess wp = new WordnetProcess();
        WordInfo wi = wp.getWordInfoWithLexnamesLimitation(input, postag, hasLimit, lexnames);
        if(wi==null)
        {
            for(int i=0; i+1<input.length(); i++)
            {
                if(input.charAt(i)==' ')
                {
                    wi = wp.getWordInfoWithLexnamesLimitation(input.substring(i+1), postag, hasLimit, lexnames);
                    if(wi!=null)
                    {
                        break;
                    }
                }
            }
        }
        return wi;
    }
    
    public String[] getSpecialKeywordCategories(String word)
    {
        String result = "";
        for(String category : ("drug,alcohol,gun,charity,sex,politics,profane").split(","))
        {
            if(isInputInBasis(word, categorySpecialKeyword, category))
            {
                if(result.isEmpty())
                    result = category;
                else
                    result = result+","+category;
            }
        }
        if(!result.isEmpty())
            return result.split(",");
        return null;
    }
    
    public String[] getKeywordCategories(String wordSenseID)
    {
        String result = "";
        for(String category : ("drug,alcohol,gun,charity,sex,politics,profane").split(","))
        {
            if(isInputInBasis(wordSenseID, categoryKeyword, category))
            {
                if(result.isEmpty())
                    result = category;
                else
                    result = result+","+category;
            }
        }
        if(!result.isEmpty())
            return result.split(",");
        return null;
    }
    
    public boolean isInputInBasis(String input, HashMap<String, LinkedList<String>> basis,
            String category)
    {
        System.out.println("HERE isInputInBasis===>>"+input);
        if(basis.containsKey(category))
        {
            if(basis.get(category).contains(input.toLowerCase().trim()))
            {
                return true;
            }
        }
        return false;
    }
    
    public LinkedList<String> getNounTerms(String[] s1, String[] s2)
    {
        LinkedList<String> result = new LinkedList();
        for(String t2 : s2)
        {
            for(String t1 : s1)
            {
                if(result.isEmpty() || (!result.isEmpty()&&!result.contains(t1+t2)))
                    result.add(t1+t2);
            }
        }
        return result;
    }
    
    public boolean isAStopword(String input)
    {
        return stopWords.contains(input.toLowerCase().trim());
    }
    
    public boolean isAProfanityWord(String input)
    {
        return profanityWords.contains(input.toLowerCase().trim());
    }
    
    public boolean isABisayaWord(String input)
    {
        return bisayaWords.contains(input.toLowerCase().trim());
    }

    public HashMap<String, LinkedList<String>> getCategoryKeyword() {
        return categoryKeyword;
    }

    public HashMap<String, LinkedList<String>> getCategorySpecialKeyword() {
        return categorySpecialKeyword;
    }

    public LinkedList<String> getNegativeWords() {
        return negativeWords;
    }

    public LinkedList<String> getPositiveWords() {
        return positiveWords;
    }

    public LinkedList<String> getStopWords() {
        return stopWords;
    }

    public LinkedList<String> getBisayaWords() {
        return bisayaWords;
    }

    public LinkedList<String> getTagalogWords() {
        return tagalogWords;
    }

    public LinkedList<String> getProfanityWords() {
        return profanityWords;
    }
    
    
            
}
