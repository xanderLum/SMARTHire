package com.smarthire.xander.POSTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

class POSTag {

  private POSTag() {}

  public static void main(String[] args) throws Exception {
//    if (args.length != 2) {
//      System.err.println("usage: java POSTag modelFile fileToTag");
//      return;
//    }
    
    String arg1 = "./src/com/smarthire/zander/POSModels/english-left3words-distsim.tagger";
//    String text = "./src/com/zander/POSTestFile/1";
    String text = "./src/com/zander/POSTestFile/sample-input.txt";
    
    //uses MaxEntropy Tagger
    MaxentTagger tagger = new MaxentTagger(arg1);
    
    List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(text)));
    for (List<HasWord> sentence : sentences) {
      List<TaggedWord> tSentence = tagger.tagSentence(sentence);
      System.out.println(Sentence.listToString(tSentence, false));
      
      
        System.out.println("tsentence = "+tSentence.toString());
    }
    
  }

}
