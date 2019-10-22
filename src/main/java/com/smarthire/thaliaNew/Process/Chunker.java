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

/**
 *
 * @author Nathalia
 */
public class Chunker {

    private PronunciationProcess pp = new PronunciationProcess();
    private HashMap<String, String> rules = new HashMap();
    private StringEvaluator se = new StringEvaluator();
    private Chinker chink = new Chinker();
    
    public final String PRIMARY_PART_KEY_SET[] = {
        "ADV","ADJ","VBZ","VB","VBP","VBD","VBG","VBN","VPR","VBEPR","VBEGP","VBENP","VHP","VDP","VMDP","INF"
    };
    public final String PHRASE_KEY_SET[] = {
        "NN","NNS","NNP","NNPS","ADJNP","DTNP","POS","NSPOS","NPPOS","NPPP","NPSP","NPGSP","NPSSP","NPSOP","NPPSP",
        "NPPOP","NPFP","NPYP","PRP","NPGS","NPGSP","P","NOUN_PREPOSITION_PHRASE","NOUN_COMPOUND_PREPOSITION_PHRASE","PP"
    };
    public final String PREDICATE_KEY_SET[] = {
        "INF_CLAUSE","PREDICATE_VERB_OBJECT","SUB_CLAUSE"
    };
    public final String CLAUSE_KEY_SET[] = {
        "CLAUSE_SUBJECT_VERB_OBJECT"
    };
    

    public Chunker() {
        rules.put("ADV", RegexRules.ADV);
        rules.put("ADJ", RegexRules.ADJ);
        rules.put("NP_RELATIVE_ADJECTIVE", RegexRules.NP_RELATIVE_ADJECTIVE);
        
/**/    rules.put("VBZ", RegexRules.VBZ);
        rules.put("VB", RegexRules.VB);
/**/    rules.put("VBP", RegexRules.VBP);
/**/    rules.put("VBD", RegexRules.VBD);

/**/    rules.put("VBG", RegexRules.VBG);
/**/    rules.put("VBN", RegexRules.VBN);

        rules.put("VPR", RegexRules.VPR);
        rules.put("VBEPR", RegexRules.VBEPR);
    
        rules.put("VBEGP", RegexRules.VBEGP);//VBE(S|P|AM|WE|WA|AM|N|B)GP
    
        rules.put("VBENP", RegexRules.VBENP);//VBE(S|P|AM|WE|WA|B|N)NP
    
        rules.put("VHP", RegexRules.VHP);//VH(S|B|D)P
        
        rules.put("VDP", RegexRules.VDP);//VD(S|B|D)P
    
        rules.put("VMDP", RegexRules.VMDP);
        rules.put("INF", RegexRules.INF);
        
        
        rules.put("NPGS", RegexRules.NPGS);
        rules.put("NPGSP", RegexRules.NPGSP);
        
        rules.put("NN", RegexRules.NN);
        rules.put("NNS", RegexRules.NNS);
        rules.put("NNP", RegexRules.NNP);
        rules.put("NNPS", RegexRules.NNPS);
        
        rules.put("ADJNP", RegexRules.ADJNP);
        
        rules.put("DTNP", RegexRules.DTNP);
        
        rules.put("POS", RegexRules.POS);
    
        rules.put("NSPOS", RegexRules.NSPOS);
        rules.put("NPPOS", RegexRules.NPPOS);
    //--------------------------------------------------------------------------------------------------------
        
        rules.put("NPPP", RegexRules.NPPP);
        rules.put("NPSP", RegexRules.NPSP);
        rules.put("NPGSP", RegexRules.NPGSP);
        
        rules.put("NPSSP", RegexRules.NPSSP);
        rules.put("NPSOP", RegexRules.NPSOP);
        rules.put("NPPSP", RegexRules.NPPSP);
        rules.put("NPPOP", RegexRules.NPPOP);
        rules.put("NPFP", RegexRules.NPFP);
        rules.put("NPYP", RegexRules.NPYP);
        rules.put("PRP", RegexRules.PRP);
        
        

        rules.put("P", RegexRules.P);
        rules.put("NOUN_PREPOSITION_PHRASE", RegexRules.NOUN_PREPOSITION_PHRASE);
        
        rules.put("NOUN_COMPOUND_PREPOSITION_PHRASE", RegexRules.NOUN_COMPOUND_PREPOSITION_PHRASE);
    /*?*/  //--------------------------------------------------------------------------------------------------------------

        rules.put("PP", RegexRules.PP);
        
        rules.put("PREDICATE_VERB_OBJECT", RegexRules.PREDICATE_VERB_OBJECT);
        rules.put("SUB_CLAUSE", RegexRules.SUB_CLAUSE);
        rules.put("INF_CLAUSE", RegexRules.INF_CLAUSE);
        
        rules.put("CLAUSE_SUBJECT_VERB_OBJECT", RegexRules.CLAUSE_SUBJECT_VERB_OBJECT);

        rules.put("CSVOINM", RegexRules.CLAUSE_SUBJECT_VERB_OBJECT_INDICATIVE_MOOD);
        rules.put("CSSWEVO", RegexRules.CLAUSE_SINGULAR_SUBJECT_WERE_VERB_OBJECT);
        rules.put("CPSWEVO", RegexRules.CLAUSE_PLURAL_SUBJECT_WERE_VERB_OBJECT);
        rules.put("CSSBVO", RegexRules.CLAUSE_SINGULAR_SUBJECT_BASE_VERB_OBJECT);
        rules.put("CPSBVO", RegexRules.CLAUSE_PLURAL_SUBJECT_BASE_VERB_OBJECT);

        rules.put("CVOIMM", RegexRules.CLAUSE_VERB_OBJECT_IMPERATIVE_MOOD);
        
        rules.put("NPGSPP", "(NPGSP(P)?(C)?)");
        rules.put("NPGPPP", "(NPGPP(P)?(C)?)");
        rules.put("NPISPP", "(NPISP(P)?(C)?)");
        rules.put("NPIPPP", "(NPIPP(P)?(C)?)");
        rules.put("NPSOPP", "(NPSOP(P)?(C)?)");
        rules.put("NPSSPP", "(NPSSP(P)?(C)?)");
        rules.put("NPPOPP", "(NPPOP(P)?(C)?)");
        rules.put("NPPSPP", "(NPPSP(P)?(C)?)");
        rules.put("NPSPP", "(NPSP(P)?(C)?)");
        rules.put("NPPPP", "(NPPP(P)?(C)?)");
        rules.put("NPYPP", "(NPYP(P)?(C)?)");
        rules.put("NPFPP", "(NPFP(P)?(C)?)");
        
        rules.put("ADJ_CC", "ADJ(S)?(C)?");
        rules.put("ADV_CC", "ADV(S)?(C)?");
        rules.put("VBZ_CC", "VBZ(C)?");
        rules.put("VBP_CC", "VBP(C)?");
        rules.put("VBD_CC", "VBD(C)?");
        rules.put("VBG_CC", "VBG(C)?");
        rules.put("VBN_CC", "VBN(C)?");
        
        rules.put("NS_CC", "N(S|P)(C)?");
        rules.put("NSP_CC", "N(S|P)P(C)?");
        
        rules.put("NPGSP_CC", "NPG(S|P)P(C)?");
        rules.put("NPISP_CC", "NPI(S|P)P(C)?"); 
        rules.put("NPSP_CC", "NP(S|P)P(C)?"); 
        rules.put("NPSSP_CC", "NP(S|P)(S)?P(C)?");
        rules.put("NPSOP_CC", "NP(S|P)(O)?P(C)?");
        
        rules.put("NPGSPP_CC", "NPG(S|P)P(P)?(C)?");
        rules.put("NPISPP_CC", "NPI(S|P)P(P)?(C)?"); 
        rules.put("NPSPP_CC", "NP(S|P)P(P)?(C)?"); 
        rules.put("NPSSPP_CC", "NP(S|P)(S)?P(P)?(C)?");
        rules.put("NPSOPP_CC", "NP(S|P)(O)?P(P)?(C)?");
        
        rules.put("CSVO_CC", "CSVOINM|CSSWEVO|CPSWEVO|CSSBEVO|CPSBEVO|CSSBVO|CPSBVO|CVOIMM|CSVO(C)?");
        rules.put("S_CC", "S(C)?");
    }

    
    public String process(String input, LinkedList<String> phraseList, HashMap<String, String> errorList)
    {
        
        System.out.println("==================CHUNK_PRIMARY_PARTS=================");
        String result = input;
        result = chunk(result, phraseList, PRIMARY_PART_KEY_SET,false,errorList);

        System.out.println("==================CHUNK_PHRASES=================");
        result = chunk(result, phraseList, PHRASE_KEY_SET, false, errorList);
        
        System.out.println("==================CHINK_PHRASES=================");
        result = chink.chinkPhrase(result, phraseList);
        
        System.out.println("==================CHUNK_PREDICATE=================");
        result = chunk(result, phraseList, PREDICATE_KEY_SET, false, errorList);
        
        System.out.println("==================CHINK_PREDICATE=================");
        result = chink.chinkPredicate(result, phraseList);
        
        System.out.println("==================CHUNK_CLAUSE=================");
        result = chunk(result, phraseList, CLAUSE_KEY_SET, false, errorList);
        
        return result;
    }
    
    public String getPluralForm(String input)
    {
        switch(input)
        {
            case "ADJ":
                return "ADJS";
            case "ADV":
                return "ADVS";
            case "NS":
                return "NP";
            case "NSP":
                return "NPP";
            case "NPGSP":
                return "NPGPP";
            case "NPISP":
                return "NPIPP";
            case "NPSOP":
                return "NPPOP";
            case "NPSSP":
                return "NPPSP";
            case "NPSP":
                return "NPPP";
            case "NPGSPP":
                return "NPGPPP";
            case "NPISPP":
                return "NPIPPP";
            case "NPSOPP":
                return "NPPOPP";
            case "NPSSPP":
                return "NPPSPP";
            case "NPSPP":
                return "NPPPP";
                
        }
        return input;
    }
    
    public String getNameCompound(String input[])
    {
        boolean flag = false;
        for(String key : ("ADJ_CC,ADV_CC,VBZ_CC,VBP_CC,VBD_CC,VBG_CC,VBN_CC,"
                + "NS_CC,NSP_CC,NPGSP_CC,NPISP_CC,NPSP_CC,NPSSP_CC,NPSOP_CC,NPGSPP_CC,"
                + "NPISPP_CC,NPSPP_CC,NPSSPP_CC,NPSOPP_CC,CSVO_CC,S_CC").split(","))
        {
            Pattern p = Pattern.compile(rules.get(key));
            
            for(int i=0; i<input.length; i++)
            {
                Matcher m = p.matcher(se.getPartLine(input[i], "TAG").trim());
                if(!m.matches())
                    break;
                else if(i==input.length-1)
                    flag = true;
            }
            if(flag)
                return key.substring(0, key.length()-3);
        }
        return null;
    }
    
    
    public String chunkCompounds(String input, LinkedList<String> phraseList)
    {
        
        Chinker chinker = new Chinker();
        //System.out.println("START chunkCompounds");
        boolean flag;
        String rule = RegexRules.COMPOUND;
        do {
            flag = false;
            if(!rule.isEmpty())
            {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);
                StringBuffer result = new StringBuffer();
                while (m.find()) {
                    String lastItemTag = m.group(30);
                    String connToken = m.group(28);
                    String name = getNameCompound(se.getSpecificPartAccToRuleName(m.group(0), RegexRules.TAG));
//                    System.out.println(m.group(0)+"==>HEY COMPOUND===>"+name);
                    if(name!=null)
                    {
//                        for(int i=0; i<m.groupCount(); i++)
//                        {
//                            if(m.group(i)!=null)
//                            {
//                                System.out.println("CHUNK COMPOUND m.group("+i+")==>"+m.group(i));
//                            }
//                        }
                        if(connToken.contentEquals("and"))
                        {
                            name = getPluralForm(name);
                        }
                        else if(connToken.contentEquals("or")||connToken.contentEquals("nor"))
                        {
                            if(lastItemTag.endsWith("C"))
                            {
                                lastItemTag = lastItemTag.substring(0, lastItemTag.length()-1);
                            }
                            name = lastItemTag;
                        }
                        flag = true;
                        String temp = m.group(0);
                        phraseList.add(temp.replace("$", "S"));
                        temp = "[" + (phraseList.size()-1) + "]";
                        m.appendReplacement(result, "<" + name+"C" + temp + ">");
                    }
                    
                }
                m.appendTail(result);
                input = result.toString();
            }
            
        } while(flag);
        
        
        
        return input;
    }
    
    public String getNameClause(String input)
    {
//        System.out.println("getNameClause");
        for (String name : ("CSVOINM,CSSWEVO,CPSWEVO,CSSBVO,CPSBVO,CVOIMM").split(",")) {
            String rule = rules.get(name);
            if (!rule.isEmpty()) {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);

                while (m.find()) {
                    return name;
                }
            }
        }
        return "CSVO";
    }
    
    public String getNamePreposition(String input)
    {
        for(String key : ("NPGSPP,NPGPPP,NPISPP,NPIPPP,NPSOPP,NPSSPP,NPPOPP,NPPSPP,NPSPP,NPPPP,NPYPP,NPFPP").split(","))
        {
            Pattern p = Pattern.compile(rules.get(key));
            Matcher m = p.matcher(input);
            
            if(m.matches())
                return key;
        }
        return null;
    }
    
    public String chunk(String input, LinkedList<String> phraseList, String keys[], boolean flag, HashMap<String, String> errorList) {
      
        LinguisticLibrary ll = new LinguisticLibrary();
        StringBuffer result = new StringBuffer();
        boolean flagA, flagB;
        flagA = false;
        System.out.println("sentence==>"+input);
        do{
            for (String name : keys) {

                flagB = false;
                System.out.println("name===>"+name);
                String rule = rules.get(name);

                if (!rule.isEmpty()) {
                    Pattern p = Pattern.compile(rule);
                    Matcher m = p.matcher(input);

                    result = new StringBuffer();

                    while (m.find()) {

                        String start = "", end = "";
                        
                        String temp = m.group(0);
                        flagA = true;
                        flagB = true;
                        boolean isValid=true;
                        String tempName= name;

                        for(int i=0; i<m.groupCount(); i++)
                        {
                            if(m.group(i)!=null)
                                System.out.println("SUPERMAN m.group("+i+")==>"+m.group(i));
                        }
                        if(name.contentEquals("NP_RELATIVE_ADJECTIVE"))
                        {
                            tempName = m.group(2)+"RL";
                        }
                        else if(se.isValidAccToRule(name, "VB(Z|P|D|G|N)?"))
                        {
                            if(se.isValidAccToRule(m.group(3), "(be|being|been|is|are|were|was|am|have|has|had|do|did|does)"))
                                isValid = false;
                        }
                        else if(name.contentEquals("VPR"))
                        {
                            String tempp = "";
                            if(m.group(11)!=null)
                                tempp = m.group(11);
                            tempName = "VB"+tempp+"PR";
                        }
                        else if(name.contentEquals("VBEPR"))
                        {
                            tempName = "VBE";
                            if(m.group(4).contentEquals("is"))
                                tempName = tempName+"S";
                            else if(m.group(4).contentEquals("are"))
                                tempName = tempName+"P";
                            else if(m.group(4).contentEquals("am"))
                                tempName = tempName+"AM";
                            else if(m.group(4).contentEquals("were"))
                                tempName = tempName+"WE";
                            else if(m.group(4).contentEquals("was"))
                                tempName = tempName+"WA";
                            else if(m.group(4).contentEquals("been"))
                                tempName = tempName+"N";
                            else if(m.group(4).contentEquals("be"))
                                tempName = tempName+"B";
                            tempName = tempName+"PR";
                        }
                        else if(name.contentEquals("VBEGP")||name.contentEquals("VBENP"))
                        {
                            tempName = "VBE";
                            //VBE(S|P|AM|WE|WA|D|N)GP
                            if((m.group(5)!=null && m.group(5).contentEquals("is"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("S")))
                                tempName = tempName+"S";
                            else if((m.group(5)!=null && m.group(5).contentEquals("are"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("P")))
                                tempName = tempName+"P";
                            else if((m.group(5)!=null && m.group(5).contentEquals("am"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("AM")))
                                tempName = tempName+"AM";
                            else if((m.group(5)!=null && m.group(5).contentEquals("were"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("WE")))
                                tempName = tempName+"WE";
                            else if((m.group(5)!=null && m.group(5).contentEquals("was"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("WA")))
                                tempName = tempName+"WA";
                            else if((m.group(5)!=null && m.group(5).contentEquals("been"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("N")))
                                tempName = tempName+"N";
                            else if((m.group(5)!=null && m.group(5).contentEquals("be"))||
                                    (m.group(7)!=null && m.group(7).contentEquals("B")))
                                tempName = tempName+"B";
                            tempName = tempName+name.substring(3);
                            if(m.group(11)!=null)
                                tempName = tempName+"R";
                            
                        }
                        else if(name.contentEquals("VHP"))
                        {
                            //VH(S|B|D)P
                            tempName = "VH";
                            if(m.group(4).contentEquals("have"))
                                tempName = tempName+"B";
                            else if(m.group(4).contentEquals("has"))
                                tempName = tempName+"S";
                            else if(m.group(4).contentEquals("had"))
                                tempName = tempName+"D";
                            tempName = tempName+"P";
                            if(m.group(5)!=null)
                                tempName = tempName+"R";
                        }
                        else if(name.contentEquals("VDP"))
                        {
                            //VD(S|B|D)P
                            tempName = "VD";
                            if(m.group(4).contentEquals("do"))
                                tempName = tempName+"B";
                            else if(m.group(4).contentEquals("does"))
                                tempName = tempName+"S";
                            else if(m.group(4).contentEquals("did"))
                                tempName = tempName+"D";
                            tempName = tempName+"P";
                            if(m.group(5)!=null)
                                tempName = tempName+"R";
                        }
                        else if(name.contentEquals("ADJNP"))
                        {
                            if(m.group(9).contentEquals("NN"))
                                tempName = "NS";
                            else if(m.group(9).contentEquals("NNP"))
                                tempName = "NSP";
                            else if(m.group(9).contentEquals("NNS"))
                                tempName = "NP";
                            else if(m.group(9).contentEquals("NNPS"))
                                tempName = "NPP";
                        }
                        else if(name.contentEquals("DTNP"))
                        {
                            if(ll.getNumberAccToTag(m.group(14)).contentEquals("plural"))
                            {
                                tempName = "DTNPP";
                            }
                            else
                            {
                                tempName = "DTNSP";
                            }
                            if(m.group(7)!=null && m.group(7).contentEquals("the"))
                            {
                                
                            }
                            else if(m.group(7)!=null && (m.group(7).contentEquals("a")|| m.group(7).contentEquals("an")))
                            {
                                if(tempName.contentEquals("DTNPP"))
                                {
                                    if(!errorList.containsKey("Wrong article"))
                                        errorList.put("Wrong article","1");
                                    else
                                        errorList.replace("Wrong article",""+(Integer.parseInt(errorList.get("Wrong article"))+1));
                                }
                                
                                String tempp = se.getBasePartLine(se.getLeftMostBase(m.group(13), phraseList, false, "", false, true), "TOKEN");
                                if(m.group(7).contentEquals("a"))
                                {
                                    if(pp.isPronunciationStartsWithVowel(tempp.trim()))
                                    {
                                        if(!errorList.containsKey("Wrong article"))
                                            errorList.put("Wrong article","1");
                                        else
                                            errorList.replace("Wrong article",""+(Integer.parseInt(errorList.get("Wrong article"))+1));
                                    }
                                }
                                else if(m.group(7).contentEquals("an"))
                                {
                                    if(!(pp.isPronunciationStartsWithVowel(tempp)))
                                    {
                                        if(!errorList.containsKey("Wrong article"))
                                            errorList.put("Wrong article","1");
                                        else
                                            errorList.replace("Wrong article",""+(Integer.parseInt(errorList.get("Wrong article"))+1));
                                    }
                                }
                            }
                            
                        }
                        else if(name.contentEquals("PREDICATE_VERB_OBJECT"))
                        {
                            WordnetProcess wp = new WordnetProcess();
                            LinkedList<String> frames = new LinkedList();
                            for(String mv : se.getMainVerb(m.group(2), phraseList))
                            {
                                String mainverb = se.getBasePartLine(se.getPhraseContent(mv, phraseList, false, "", false, true,false), "TOKEN");
                                System.out.println("HERE HERE MAIN VERB DZAE==>"+mainverb);
                                if(frames.isEmpty() && !mainverb.isEmpty())
                                {
                                    frames.addAll(wp.getVerbFramesGeneral(mainverb, true));
                                }
                                else
                                {
                                    frames.addAll(se.getIntersection(frames, wp.getVerbFramesGeneral(mainverb, true)));
                                }
                            }
                            
                            for(String f : frames)
                            {
                                System.out.println("frame===>"+f);
                            }
                            
                            boolean flagC=false;
                            if(m.group(40)!=null)
                            {
                                if(frames.contains("NP V NP Adjective/Noun") || frames.contains("NP V NP NP"))
                                {
                                    flagC=true;
                                }
                                else
                                {
                                    temp=m.group(2)+m.group(21);
                                    end = end+m.group(40);
                                }
                            }
                            if(!flagC && m.group(21)!=null)
                            {
                                if(frames.contains("NP V NP") || frames.contains("NP V Adjective/Noun"))
                                {
                                    flagC=true;
                                }
                                else
                                {
                                    temp=m.group(2);
                                    end = end+m.group(21);
                                }
                            }
                            else if(!flagC && m.group(59)!=null)
                            {
                                if(frames.contains("NP V Adjective") || frames.contains("NP V Adjective/Noun"))
                                {
                                    flagC=true;
                                }
                                else
                                {
                                    temp=m.group(2);
                                    end = end+m.group(59);
                                }
                            }
                            
                            tempName = "V";
                            if(m.group(11).contentEquals(":was"))
                                tempName = tempName+"WAOP";
                            else if(m.group(11).contentEquals(":were"))
                                tempName = tempName+"WEOP";
                            else if(m.group(11).contentEquals(":am"))
                                tempName = tempName+"AMOP";
                            else if(m.group(4)!=null)
                                tempName = tempName+"SOP";
                            else if(m.group(5)!=null)
                                tempName = tempName+"POP";
                            else if(m.group(6)!=null)
                                tempName = tempName+"NOP";
                            else if(m.group(7)!=null)
                                tempName = tempName+"AMOP";
                            else if(m.group(8)!=null)
                                tempName = tempName+"WAOP";
                            else if(m.group(9)!=null)
                                tempName = tempName+"WEOP";
                            else if(m.group(10)!=null)
                                tempName = tempName+"BOP";
                            
                        }
                        else if(name.contentEquals("CLAUSE_SUBJECT_VERB_OBJECT"))
                        {
                            tempName = getNameClause(m.group(0));
                        }
                        else if(name.contentEquals("INF_CLAUSE"))
                        {
                            temp = chunk(phraseList.get(Integer.parseInt(m.group(18)))+m.group(20), phraseList, new String[]{"PREDICATE_VERB_OBJECT","CLAUSE_SUBJECT_VERB_OBJECT"}, false, errorList);
                            System.out.println("TEMP LAGEH==>"+temp);
                            phraseList.add(temp);
                            temp = "<INF[" + (phraseList.size()-1) + "]>";
                            
                            if(m.group(1)!=null)
                            {
                                tempName = m.group(2)+"INF";
                                temp = m.group(1)+temp;
                            }
                            else
                            {
                                tempName = "CINF";
                            }
                        }
                        else if(name.contentEquals("SUB_CLAUSE"))
                        {
                            tempName = getNameClause(m.group(13));
                            phraseList.add(m.group(13));
                            String tempB = m.group(1)+"<"+tempName+"[" + (phraseList.size()-1) + "]>";
                            phraseList.add(tempB);
                            temp = tempB;
                            tempName = "CSUB";
                            //CSUBNJV
                            if(m.group(2).contentEquals("P"))
                            {
                                String tempp= se.getBasePartLine(se.getPhraseContent(m.group(1), phraseList, false, "", false, true,false),"TOKEN");
                                if(ll.canBeNounDependentClause(tempp.trim()))
                                    tempName = tempName+"N";
                                if(ll.canBeAdjectiveDependentClause(tempp.trim()))
                                    tempName = tempName+"J";
                            }
                            else
                            {
                                if(ll.canBeNounDependentClause(m.group(8)))
                                    tempName = tempName+"N";
                                if(ll.canBeAdjectiveDependentClause(m.group(8)))
                                    tempName = tempName+"J";
                            }
                            
                            if(m.group(2).contentEquals("P")||m.group(2).contentEquals("WRB"))
                                tempName = tempName+"V";
                        }
                        else if(name.contentEquals("NOUN_COMPOUND_PREPOSITION_PHRASE"))
                        {
    //                            for(int i=0; i<m.groupCount(); i++)
    //                            {
    //                                System.out.println("hasCompoundPrepositionPhrase m.group("+i+") ===> "+m.group(i));
    //                            }
                        }
                        else if(name.contentEquals("NOUN_PREPOSITION_PHRASE"))
                        {
                            tempName = getNamePreposition(m.group(2));
                            phraseList.add(m.group(11));

                            String tempB = m.group(1)+"<PP[" + (phraseList.size()-1) + "]>";
                            phraseList.add(tempB);
                            temp = tempB;
                        }
                        else if(name.contentEquals("PRP")||name.contentEquals("P"))
                        {
                            tempName = name+"_"+m.group(3).toUpperCase();
                        }
                        if(isValid)
                        {
                            phraseList.add(temp);

                            temp = "[" + (phraseList.size()-1) + "]";
                            m.appendReplacement(result, start+"<" + tempName + temp + ">"+end);
                        }
                    }
                    m.appendTail(result);
                    input = chunkCompounds(result.toString(), phraseList);
                    if(name.contentEquals("SUB_CLAUSE") && flagB)
                    {
                        Chinker chink = new Chinker();
                        System.out.println("B4 SUB_CLAUSE==>"+input);
                        input = chink.chinkPrevSub(input, phraseList);
                        input = chunk(input, phraseList, PHRASE_KEY_SET, false, errorList);
                        
                        input = chunk(input, phraseList, new String[]{"NP_RELATIVE_ADJECTIVE"}, false, errorList);
                        input = chunk(input, phraseList, PREDICATE_KEY_SET, false, errorList);
                        System.out.println("AFTER SUB_CLAUSE==>"+input);
                        
                    }
//                    System.out.println("AFTER CHUNK COMPOUND==>"+sentence);
    //                    System.out.println("> "+sentence);
                }
            }
        }while(flag && flagA);
        
        return input;
    }
}
