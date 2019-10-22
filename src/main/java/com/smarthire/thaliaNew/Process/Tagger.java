/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author Nathalia
 */
public class Tagger {
    
    Reader read;
    MaxentTagger tagger;
    SpellChecker spellcheck;
    LinguisticLibrary ll = new LinguisticLibrary();
    StringEvaluator se = new StringEvaluator();
    
    public void init()
    {
    	//String POST_PATH = "C:\\Users\\Nathalia\\Desktop\\SMARTHire\\src\\main\\java\\com\\smarthire\\zander\\POSModels\\english-left3words-distsim.tagger";
        String POST_PATH = "D:\\THESIS TO GO MAN\\Final Integration\\SMARTHire\\src\\main\\java\\com\\smarthire\\zander\\POSModels\\english-left3words-distsim.tagger";
        tagger = new MaxentTagger(POST_PATH);
        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "untokenizable=noneKeep");
    }
    
    public Tagger()
    {
        init();
        spellcheck = new SpellChecker();
    }
    
    public DocumentPreprocessor preprocess(String input)
    {
        read = new StringReader(input);
        return new DocumentPreprocessor(read);
    }
    
    public List<TaggedWord> getT(List<HasWord> input)
    {
        return tagger.tagSentence(input);
    }
    
    public String[] process(List<HasWord> input)
    {
        System.out.println("TAGGING...."+input.toString());
        WordnetProcess wp = new WordnetProcess();
        List<TaggedWord> t = tagger.tagSentence(input);
        
        String tagString = "";
        String tokenString = "";

        String result = "";
        for (TaggedWord tw : t) {
            String word = tw.word();
            String pos = ll.getCategoryPOS(tw.tag());
            
            if(!word.isEmpty())
            {
                tagString = tagString + " " + tw.tag();
                tokenString = tokenString + " " + word;
                result = result + "<" + tw.tag() + ":" + word.toLowerCase() + ">";
            }

        }
    	
        return new String[] {result, tokenString};
    }
    
}