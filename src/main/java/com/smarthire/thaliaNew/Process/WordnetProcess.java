/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.WordSense;
import java.util.LinkedList;
import java.net.URL;
import edu.mit.jwi.*;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.WordnetStemmer;
import edu.smu.tspell.wordnet.AdjectiveSynset;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.VerbSynset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.WordInfo;
/**
 *
 * @author Nathalia
 */
public class WordnetProcess {
    
	//private String path = "C:\\Users\\Nathalia\\Desktop\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew\\dict";
	private String path = "D:\\THESIS TO GO MAN\\Final Integration\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew\\dict";
    private WordNetDatabase database;
    //private final String path = System.getProperty("user.dir") + "\\src\\dict";
    private StringEvaluator se = new StringEvaluator();
    private URL url;
    
    public final void init()
    {
       // System.setProperty("wordnet.database.dir", System.getProperty("user.dir") + "\\src\\dict\\");
        database = WordNetDatabase.getFileInstance();
        try{
            url = new URL("file", null, path);
            
        } catch(Exception e){
        	System.err.println("\n>>>>>>>>>>>NO WORDNET CONNECTION!!!\n");
        }
    }
    
    public POS getPOS(String input)
    {
        switch(input)
        {
            case "noun":
                return POS.NOUN;
            case "verb":
                return POS.VERB;
            case "adjective":
                return POS.ADJECTIVE;
            case "adverb":
                return POS.ADVERB;
            default: return null;
        }
    }
    public SynsetType getSynsetType(String input)
    {
        switch(input)
        {
            case "noun":
                return SynsetType.NOUN;
            case "verb":
                return SynsetType.VERB;
            case "adjective":
                return SynsetType.ADJECTIVE;
            case "adverb":
                return SynsetType.ADVERB;
            default: return null;
        }
    }
    
    public boolean doesWordWithCertainPostagExist(String input, String postag)
    {
    	System.out.println("doesWordWithCertainPostagExist==>"+input);
        init();
//        System.out.println("getWordInfo input==>"+input);
//        System.out.println("getWordInfo postag==>"+postag);
        try {
            IDictionary dict = new Dictionary(url);
            dict.open();
            POS pos = getPOS(postag);
            
            IIndexWord idxWord = dict.getIndexWord(getBaseForm(input,postag), pos);
            
            for(IWordID wordID : idxWord.getWordIDs())
            {
                return true;
            }
        }
        catch(Exception e)
        {
            //System.out.println("ERROR MAN DZAE==>"+e.toString());
        }
        return false;
    }
    
    public LinkedList<String> getSynonyms(String input, String postag)
    {
    	init();
//      System.out.println("getWordInfo input==>"+input);
//      System.out.println("getWordInfo postag==>"+postag);
      try {
    	  LinkedList<String> synonym = new LinkedList();
          IDictionary dict = new Dictionary(url);
          dict.open();
          POS pos = getPOS(postag);
          
          IIndexWord idxWord = dict.getIndexWord(getBaseForm(input,postag), pos);
          LinkedList<GlossInfo> glossInfo = new LinkedList();
          
          for(IWordID wordID : idxWord.getWordIDs())
          {
              IWord word = dict.getWord(wordID);
              ISynset synset = word.getSynset();
              
              for(IWord temp : synset.getWords()) {
                  synonym.add(temp.getLemma());
              }
          }
          dict.close();
          return synonym;
          
      } catch (Exception ex) {
//          Logger.getLogger(WordnetProcess.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
    }
    
    public WordInfo getWordInfoWithLexnamesLimitation(String input, String postag, boolean hasLimit, LinkedList<String> lexnames)
    {
    	System.out.println("getWordInfoWithLexnamesLimitation==>"+input);
        init();
//        System.out.println("getWordInfo input==>"+input);
//        System.out.println("getWordInfo postag==>"+postag);
        try {
            IDictionary dict = new Dictionary(url);
            dict.open();
            POS pos = getPOS(postag);
            
            IIndexWord idxWord = dict.getIndexWord(getBaseForm(input,postag), pos);
            LinkedList<GlossInfo> glossInfo = new LinkedList();
            
            for(IWordID wordID : idxWord.getWordIDs())
            {
                IWord word = dict.getWord(wordID);
                ISynset synset = word.getSynset();
                String LexFileName = synset.getLexicalFile().getName();
                LinkedList<String> synonym = new LinkedList();
                for(IWord temp : synset.getWords()) {
                    synonym.add(temp.getLemma());
                }
                if(!hasLimit||(hasLimit && lexnames.contains(LexFileName)))
                {
                    glossInfo.add(new GlossInfo(wordID.getSynsetID().toString(), word.getSynset().getGloss(), synonym, LexFileName));
                }
            }
            dict.close();
            return new WordInfo(idxWord.getLemma().replace("_", " "), postag, glossInfo);
            
        } catch (Exception ex) {
//            Logger.getLogger(WordnetProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public WordInfo getWordInfo(String input, String postag)
    {
    	System.out.println("getWordInfo input===>"+input);
        init();
        return getWordInfoWithLexnamesLimitation(input, postag, false, null);
    }
    public LinkedList<String> getLexFileName(String input, String postag)
    {
    	System.out.println("getLexFileName input===>"+input);
        init();
        return getLexFileNameAccWithLimits(input, postag, ".+");
    }
    
    public LinkedList<String> getLexFileNameAccWithLimits(String input, String postag, String ruleWordInclusion)
    {
        init();
        System.out.println("getLexFileName input===>"+input);
        System.out.println("getLexFileName postag===>"+postag);
        if(doesTermExist(input))
        {
            try {
                LinkedList<String> result = new LinkedList();
                IDictionary dict = new Dictionary(url);
                dict.open();
                POS pos = getPOS(postag);
                IIndexWord idxWord = dict.getIndexWord(getBaseForm(input,postag), pos);
                LinkedList<GlossInfo> glossInfo = new LinkedList();

                for(IWordID wordID : idxWord.getWordIDs())
                {
                    IWord word = dict.getWord(wordID);
                    ISynset synset = word.getSynset();
                    if(se.isValidAccToRule(word.getLemma(), ruleWordInclusion))
                    {
                        result.add(synset.getLexicalFile().getName());
                    }
                }
                dict.close();
                return result;
            } catch (Exception ex) {
                //Logger.getLogger(WordnetProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new LinkedList();
    }
    
    public String getBaseForm(String input, String postag)
    {
    	System.out.println("getBaseForm==>"+input);
        init();
//        System.out.println("getBaseForm input==>"+input);
//        System.out.println("getBaseForm postag==>"+postag);
        try {
            String minBaseForm="";
            IDictionary dict = new Dictionary(url);
            dict.open();
            WordnetStemmer stemmer = new WordnetStemmer(dict);
            
            POS pos = getPOS(postag);
            
            List<String> stems = stemmer.findStems(input, pos);
//            for (String stem : stems)
//            {
//                System.out.println("HAHAHA==>"+stem);
//                if(minBaseForm.isEmpty()||(!minBaseForm.isEmpty() && minBaseForm.length()>stem.length()))
//                {
//                    minBaseForm = stem;
//                }
//            }
            dict.close();
//            minBaseForm = minBaseForm.replace("_", " ");
//            System.out.println("getBaseForm minBaseForm==>"+minBaseForm);
//            return minBaseForm;
            return stems.get(0).replace("_", " ");
        } catch (Exception ex) {
            //Logger.getLogger(WordnetProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public LinkedList<String> getAntonyms(String word){
        init();
        LinkedList<String> list = new LinkedList();
        
        Synset[] synsets = database.getSynsets(word);
        for(Synset synset : synsets){
            WordSense[] wordsenses = synset.getAntonyms(word);

            for(WordSense wordsense : wordsenses) {
      //          System.out.println(wordsense.getWordForm());
                list.add(wordsense.getWordForm());
            }
        }
        return list;
    }
    public boolean isExisting(LinkedList<String> list, String word) {
        init();
        System.out.println("isExisting==>"+word);
        for (String temp : list) {
            if (word.equalsIgnoreCase(temp)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean doesTermExist(String term)
    {
    	System.out.println("getAttributeOfAdjective==>"+term);
        init();
        System.out.println("term ===>"+term);
        Synset[] synset = database.getSynsets(term);
        for (Synset i : synset) {
            return true;
        }
        return false;
    }
    
    public boolean isSynonym(String w1, String t1, String w2)
    {
        init();
//        System.out.println("> w1===>"+w1);
//        System.out.println("> t1===>"+t1);
//        System.out.println("> w2===>"+w2);
        Synset[] synsets = database.getSynsets(w1);
        ArrayList<String> synWords = new ArrayList();
        if (synsets.length > 0)
        {
            for (int i = 0; i < synsets.length; i++)
            {
                String[] wordForms = synsets[i].getWordForms();
                for (int j = 0; j < wordForms.length; j++)
                {
                    //if(getBaseForm(wordForms[j].trim(), t1).equalsIgnoreCase(w2))
                    if(wordForms[j].trim().equalsIgnoreCase(w2))
                    {
                       return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    public LinkedList<String> getAttributeOfAdjective(String input)
    {
    	System.out.println("getAttributeOfAdjective==>"+input);
        init();
        
        LinkedList<String> result = new LinkedList();
        Synset[] synsets = database.getSynsets(input, SynsetType.ADJECTIVE);
        for(Synset synset : synsets)
        {
            for(String synsetWordForm : synset.getWordForms())
            {
                AdjectiveSynset nounSynset = (AdjectiveSynset) (synset);
                for(NounSynset hyponym : nounSynset.getAttributes())
                {
                    for(String wf : hyponym.getWordForms())
                    {
                        result.add(wf);
                    }
                }
            }
        }
        return result;
    }
    
    
    public String getFrameEquivalent(String input)
    {
    	System.out.println("getFrameEquivalent==>"+input);
    	
        String result="";
        for(String hold : input.split(" "))
        {
            System.out.println("> "+hold);
            if(hold.startsWith("----"))
            {
                result=result+" V";
            }
            else if(hold.equalsIgnoreCase("Something") || hold.equalsIgnoreCase("Somebody"))
            {
                result=result+" NP";
            }
            else
            {
                result=result+" "+hold;
            }
        }
        return result.trim();
    }
    
    public LinkedList<String> getVerbFramesGeneral(String input , boolean flag){
        init();
        System.out.println("getVerbFramesGeneral==>"+input);
        LinkedList<String> frames = new LinkedList();
        Synset[] synsets = database.getSynsets(input.trim(),SynsetType.VERB);
        for(Synset synset : synsets)
        {
            for(String s : ((VerbSynset) synset).getSentenceFrames())
            {
                if(flag)
                {
                    String hold = getFrameEquivalent(s);
                    if(!frames.contains(hold))
                    {
                        frames.add(hold);
                    }
                }
                else
                {
                    if(!frames.contains(s))
                    {
                        frames.add(s);
                    }
                }
            }   
        }
        return frames;
    }
}
    