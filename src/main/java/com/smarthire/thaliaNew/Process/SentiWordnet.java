/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Nathalia
 */
public class SentiWordnet {
   
	//private String path = "C:\\Users\\Nathalia\\Desktop\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew";
	private String path = "D:\\THESIS TO GO MAN\\Final Integration\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew";
    private String SWN_PATH = path+"\\data\\SentiWordNet_3.0.0_20130122.txt";
	private HashMap<String, String> SWN_DICT;

    public SentiWordnet(){
        
        SWN_DICT = new HashMap();
        HashMap<String, Vector<Double>> _temp = new HashMap<String, Vector<Double>>();
        try{
            BufferedReader csv =  new BufferedReader(new FileReader(SWN_PATH));
            String line = "";           
            while((line = csv.readLine()) != null)
            {
                if(!line.isEmpty())
                {
                    String[] data = line.split("\t");
                    int count = 0;
                    Double score = Double.parseDouble(data[2])-Double.parseDouble(data[3]);
                    String[] words = data[4].split(" ");
                    for(String w:words)
                    {
                        String[] w_n = w.split("#");
                        w_n[0] += "#"+data[0];
                        int index = Integer.parseInt(w_n[1])-1;
                        if(_temp.containsKey(w_n[0]))
                        {
                            Vector<Double> v = _temp.get(w_n[0]);
                            if(index>v.size())
                                for(int i = v.size();i<index; i++)
                                    v.add(0.0);
                            v.add(index, score);
                            _temp.put(w_n[0], v);
                        }
                        else
                        {
                            Vector<Double> v = new Vector<Double>();
                            for(int i = 0;i<index; i++)
                                v.add(0.0);
                            v.add(index, score);
                            _temp.put(w_n[0], v);
                        }
                    }
                }
            }
            Set<String> temp = _temp.keySet();
            for (Iterator<String> iterator = temp.iterator(); iterator.hasNext();) {
                String word = (String) iterator.next();
                Vector<Double> v = _temp.get(word);
                double score = 0.0;
                double sum = 0.0;
                for(int i = 0; i < v.size(); i++)
                    score += ((double)1/(double)(i+1))*v.get(i);
                for(int i = 1; i<=v.size(); i++)
                    sum += (double)1/(double)i;
                score /= sum;
                String result="0";
                if(score>0)
                    result = "1";
                else if(score < 0)
                    result = "-1";
                SWN_DICT.put(word, result);
            }
        }
        catch(Exception e){e.printStackTrace();}        
    }

    public String getSentiWord(String word, String pos)
    {
        String temp;
        if(pos!=null && (temp = getSentiPostag(pos))!=null)
        {
            String result;
            if((result = SWN_DICT.get(word+"#"+temp))!=null)
            {
                return result;
            }
        }
        return "0";
    }
    
    public String getSentiPostag(String input)
    {
        switch(input)
        {
            case "noun":
                return "n";
            case "verb":
                return "v";
            case "adjective":
                return "a";
            case "adverb":
                return "r";
            default : return null;
        }
    }
    
//    
//    def get_wordnet_pos(treebank_tag):
//   if treebank_tag.startswith('J'):
//       return 'a'
//   elif treebank_tag.startswith('V'):
//       return 'v'
//   elif treebank_tag.startswith('N'):
//       return 'n'
//   elif treebank_tag.startswith('R'):
//       return 'r'
//   else:
//       return ''
}
