/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import edu.mit.jwi.item.IWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.smarthire.thaliaNew.Model.Data;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.WordInfo;
/**
 *
 * @author Nathalia
 */
public class WordSenseDisambiguator {
    
    private final KeySearchProcess ksp;
    private final StringEvaluator se;
    private final WordnetProcess wp;
    private final Tagger tagger;
    private final LinguisticLibrary llibrary;
    private final LinkedList<WordInfo> searchKeysSource;

    
    public WordSenseDisambiguator(Tagger tagger, LinkedList<WordInfo> tokens, KeySearchProcess ksp)
    {
        wp = new WordnetProcess();
        this.tagger = tagger;
        llibrary = new LinguisticLibrary();
        this.searchKeysSource = tokens;
        this.se = new StringEvaluator();
        this.ksp = ksp;
        
    }
    
    public HashMap<String, TokenCount> readStringReturnToken(String input_string, boolean flag)
    {
        HashMap<String, TokenCount> output = new HashMap();
        if(!flag)
            input_string = se.getBasePartLine(input_string, "TAG_TOKEN");
        if(!input_string.trim().isEmpty()) 
        {
            for(String temp : input_string.trim().split(" "))
            {
                if(!temp.isEmpty())
                {
                    String tempp[] = temp.trim().split(":");
                    String postag,word;
                    if(flag) {
                        word = tempp[0];
                        postag = tempp[1].replace("_", " ");
                    }
                    else {
                        postag = llibrary.getCategoryPOS(tempp[0]);
                        word = tempp[1];
                    }
                    
                    if(!ksp.isAStopword(word))
                    {
                        String hold=wp.getBaseForm(word, postag);
                        if(hold!=null && !hold.isEmpty())
                        {
                            if(output.containsKey(hold))
                            {
                                output.get(hold).setCount(output.get(hold).getCount()+1); 
                            }
                            else
                            {
                                output.put(hold, new TokenCount(1,hold));
                            }
                        }
                    }
                }
            }
       }
        return output;
       
    }
    public void addWord(ArrayList<String> tokens, HashMap<String, TokenCount> a)
    {
        for(String key : a.keySet())
        {
            if(!tokens.contains(key))
            {
                tokens.add(a.get(key).getWord());
            }
        }
    }
    
    public String getSearchKey(String input, String tag)
    {
        String hold = "";
        for(WordInfo wi : searchKeysSource)
        {
            if(!wi.getWord().contentEquals(input) && !wi.getPostag().contentEquals(tag) &&
                    wi!=null && wi.getGlossInfo()!=null)
            {
                System.out.println("getSearchKey HELLO!!!!");
                for(GlossInfo gi : wi.getGlossInfo())
                {
                    hold = hold + ". " +gi.getDefinition();
//                        for(IWord temp : gi.getSynonyms())
//                        {
//                            holdA = holdA+" "+temp.getLemma()+":"+tag;
//                        }
                }
            }
        }
        //System.out.println("getSearchKey holdA==>"+holdA);
        System.out.println("getSearchKey hold==>"+hold);
        
        //return new String[] {hold,holdA};
        return hold;
    }
    
    public ArrayList<Double> preprocess(String postag, LinkedList<GlossInfo> glossInfo, ArrayList<Data> data, ArrayList<String> tokens)
    {
       ArrayList<String> urls = new ArrayList();
       HashMap<String, TokenCount> output;
       HashMap<String, HashMap<String, TokenCount>> outputTc = new HashMap();

       ArrayList<String> pathStr = new ArrayList();
       Scanner scan = new Scanner(System.in);

        for(GlossInfo gi : glossInfo)
        {
            DocumentPreprocessor dc = tagger.preprocess(gi.getDefinition());

            String holdA="";
            for(String temp : gi.getSynonyms()) {
                holdA = holdA+" "+temp+":"+postag;
            }
           
            System.out.println("preprocess holdA===>"+holdA);
            for (List<HasWord> s : dc) {

                String hold = tagger.process(s)[0];
                output=readStringReturnToken(hold, false);
                output.putAll(readStringReturnToken(holdA, true));
                addWord(tokens, output);
                outputTc.put(gi.getDefinition(), output);
            }
        }
        Collections.sort(tokens, new Comparator<String>()
        {
           @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        for(int i=0; i<glossInfo.size(); i++)
        {
            if(glossInfo.get(i)!=null)
            {
                data.add(new Data(glossInfo.get(i).getDefinition(), i));
                HashMap<String, TokenCount> tokenCount = outputTc.get(glossInfo.get(i).getDefinition());
                for(int j=0; j<tokens.size(); j++)
                {
                    data.get(data.size()-1).getValue().add(0);
                    data.get(data.size()-1).addTf(0);

                    if(tokenCount.containsKey(tokens.get(j)))
                    {
                        data.get(data.size()-1).getValue().set(data.get(data.size()-1).getValue().size()-1,tokenCount.get(tokens.get(j)).getCount());
                        data.get(data.size()-1).getTf().set(data.get(data.size()-1).getTf().size()-1,(double)tokenCount.get(tokens.get(j)).getCount());
                    }

                }
            }
            
        }
       //------------------------------------------------------------------
       
//        System.out.println("\nData: ");
//        for(int j=0; j<data.size(); j++)
//        {
//           System.out.println("> Definition: "+data.get(j).getDefinition());
//           System.out.println("> Tf: ");
//           for(int i=0; i<data.get(j).getTf().size(); i++)
//           {
//               System.out.print(data.get(j).getTf().get(i)+" ");
//           }
//           System.out.println("tf size: "+data.get(j).getTf().size());
//           System.out.println("> Value: ");
//           for(int i=0; i<data.get(j).getValue().size(); i++)
//           {
//               System.out.print(data.get(j).getValue().get(i)+" ");
//           }
//           System.out.println("value size: "+data.get(j).getValue().size());
//           System.out.println("");
//
//        }
        System.out.println("\nTokens: ");
        for(int j=0; j<tokens.size(); j++)
        {
           System.out.print(tokens.get(j)+"|");
        }
        System.out.println("token size: "+tokens.size());
        
       //---------------------------------------------------------------

       ArrayList<Double> idf = getIdf(data, tokens);
       for(int i=0; i<data.size(); i++)
       {
           for(int j=0; j<idf.size(); j++)
           {
               data.get(i).getTf().set(j, data.get(i).getTf().get(j)*idf.get(j));
           }
       }
       return idf;
    }
    
    public LinkedList<GlossInfo> process(String input, String tag, LinkedList<GlossInfo> glossInfo)
    {
        ArrayList<String> tokens = new ArrayList();
        ArrayList<Data> data = new ArrayList();
        
        System.out.println("\n=================================================================\n");
        System.out.println("WORD: "+input);
        ArrayList<Double> idf = preprocess(tag, glossInfo, data, tokens);
        int searchStr[] = new int[tokens.size()];
        for(int i=0; i<searchStr.length; i++)
        {
           searchStr[i]=0;
        }
        
        String searchKey = getSearchKey(input, tag);
        
        DocumentPreprocessor dc = tagger.preprocess(searchKey);
        
        String hold = "";
        for (List<HasWord> s : dc) {

            hold = hold.trim()+" "+se.getBasePartLine(tagger.process(s)[0], "TAG_TOKEN").trim();
        }
        searchKey=hold;
        
//        System.out.println("search key[0]==>"+searchKey[0]);
//        System.out.println("search key[1]==>"+searchKey[1]);
                
        ArrayList<Double> qTfIdf = new ArrayList();
        for(int i=0; i<tokens.size(); i++)
        {
           qTfIdf.add(0.0);
        }
        for(String temp : searchKey.trim().split(" "))
        {
            if(!temp.isEmpty())
            {
                String tempp[] = temp.trim().split(":");
                String word = tempp[1];
                String postag = llibrary.getCategoryPOS(tempp[0]);
               
                if(!ksp.isAStopword(tempp[1]))
                {
                    word = wp.getBaseForm(tempp[1], postag);
                    if(word!=null)
                    {
                        for(int i=0; i<tokens.size(); i++)
                        {
                            if(tokens.get(i).equalsIgnoreCase(word) || (wp.isSynonym(word, postag, tokens.get(i))))
                            {
                                qTfIdf.set(i, qTfIdf.get(i)+1);
                            }
                        }
                    }
                }
            }
        }
        
                
        for(int i=0; i<tokens.size(); i++)
        {
            qTfIdf.set(i, (qTfIdf.get(i)/getTotalDataVal(data,i))*idf.get(i));
        }

        for(int i=0; i<data.size(); i++)
        {
            data.get(i).setCosSim(0.0);
            for(int j=0; j<tokens.size(); j++)
            {
                data.get(i).setCosSim(data.get(i).getCosSim()+(data.get(i).getTf().get(j)*qTfIdf.get(j)));
            }
            
            data.get(i).setCosSim(data.get(i).getCosSim()/(getDocLength(data.get(i).getTf())*getDocLength(qTfIdf)));
            data.get(i).setDegree(calculateDegrees(calculateAngle(data.get(i).getCosSim())));
            
        }
        
        System.out.println("Ordered List of URLs matching search_string: ");
        int count=0;
        sort(data);
        LinkedList<GlossInfo> glossInfoOutput = new LinkedList();
        for(int i=0; i<data.size(); i++)
        {
            if(data.get(i).getDegree()<90 && !Double.isNaN(data.get(i).getDegree()))
            {
                System.out.println("*****"+i);
                glossInfoOutput.add(glossInfo.get(data.get(i).getDefPntr()));
                System.out.println(i+" => "+data.get(i).getDefinition()+" => "+data.get(i).getCosSim()+" => "+data.get(i).getDegree());
                count++;
            }
        }
        System.out.println(count+" total number of search result/s");

        return glossInfoOutput;        
    }
    
    
    
    public double getDocLength(ArrayList<Double> a)
    {
        double result=0;
        
        for(int i=0; i<a.size(); i++)
        {
            result=result+(a.get(i)*a.get(i));
        }
        
        return Math.sqrt(result);
    }
    
    public ArrayList<Double> getIdf(ArrayList<Data> data, ArrayList<String> tokens)
    {    
        ArrayList<Double> temp = new ArrayList();
        for (int i = 0; i<tokens.size(); i++) {
            Double hold = (double)((Math.log((double)data.size() / getTotalDataVal(data, i))) / Math.log(2));
            temp.add(hold);
        }
        return temp;
    }
    public int getTotalDataVal(ArrayList<Data> data, int index)
    {
        int total=0;
        for(int i=0; i<data.size(); i++)
        {
            total=total+data.get(i).getValue().get(index);
        }
        return total;
    }
    public void sort(ArrayList<Data> data)
    {
        if (data == null || data.isEmpty())
        {
            return;
        }
        quickSort(data, 0, data.size() - 1);
    }
 
    private void quickSort(ArrayList<Data> data, int lowerIndex, int higherIndex) 
    {
        int i = lowerIndex;
        int j = higherIndex;
        double pivot = data.get(lowerIndex+(higherIndex-lowerIndex)/2).getDegree();
        
        while (i <= j)
        {
            while (data.get(i).getDegree() < pivot) 
            {
                i++;
            }
            while (data.get(j).getDegree()> pivot) 
            {
                j--;
            }
            if (i <= j)
            {
                exchangeNumbers(data, i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(data, lowerIndex, j);
        if (i < higherIndex)
            quickSort(data, i, higherIndex);
        
   }
   public void exchangeNumbers(ArrayList<Data> data, int i, int j) 
   {
        Data temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
   }
   public double calculateAngle(double cosine)
   {
        return Math.acos(cosine);
   }
   public double calculateDegrees(double angle)
   {
        return Math.toDegrees(angle);
   }
    //--------------------------------------------------------------------------------------------------
    
    
   
    
}
