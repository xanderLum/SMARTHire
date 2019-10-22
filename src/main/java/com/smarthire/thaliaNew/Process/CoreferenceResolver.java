/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import com.smarthire.thaliaNew.Interfaces.GenderBagOfWords;
import com.smarthire.thaliaNew.Interfaces.RegexRules;
import com.smarthire.thaliaNew.Model.ClauseData;
import com.smarthire.thaliaNew.Model.NominalReference;
import com.smarthire.thaliaNew.Model.NounTokenData;
import com.smarthire.thaliaNew.Model.PartPointer;
import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.Model.PhraseData;

/**
 *
 * @author Nathalia
 */
public class CoreferenceResolver {
    
    private int counter;
    private LinkedList<String> seqKey;
    private HashMap<String, String> entityKey;
    private HashMap<String, NominalReference> nominalReferences;
    private LinguisticLibrary ll = new LinguisticLibrary();
    private StringEvaluator se = new StringEvaluator();
    private LinkedList<WordInfo> tokens;
    private HashMap<String, String> tokenIndexKey;
    private KeySearchProcess ksp;
    
    public CoreferenceResolver() {
        nominalReferences = new HashMap();
        seqKey = new LinkedList();
        entityKey = new HashMap();
        
        this.tokens = new LinkedList();
        this.tokenIndexKey = new HashMap();
        this.ksp = new KeySearchProcess();
    }

    public LinkedList<WordInfo> getTokens() {
        return tokens;
    }
    
    public int getPriority(String input)
    {
        switch(input)
        {
            case "SUBJECT":
                return 1;
            case "DIRECT_OBJECT":
                return 2;
            case "INDIRECT_OBJECT":
                return 3;
            default:
                return 4;
        }
    }
    
    public LinkedList<String> getModifierKeys(Set<String> input, String role)
    {
        LinkedList<String> output = new LinkedList();
        for(String key : input)
        {
            if(key.startsWith(role.trim()+"_MODIFIER"))
            {
                output.add(key);
            }
        }
        return output;
    }
    
    public int addToken(String type, String content, boolean hasLimit, LinkedList<String> lexnames)
    {
        System.out.println("***********addToken==>"+content);
        
        WordInfo wi = ksp.getWordInfoWithLimitations(content, type,  hasLimit, lexnames);
        if(type.contentEquals("pronoun"))
        {
            wi = new WordInfo(content, type);
        }
        if(wi!=null)
        {
            System.out.println("WI IS NOT NULL");
            if(!tokenIndexKey.containsKey("TOKEN["+content+":"+type+"]"))
            {
                tokens.add(wi);
                tokenIndexKey.put("TOKEN["+content+":"+type+"]", ""+(tokens.size()-1));
                return tokens.size()-1;
            }
            else
            {
                return Integer.parseInt(tokenIndexKey.get("TOKEN["+content+":"+type+"]"));
            }
        }
        else
        {
            System.out.println("WI IS NULL");
        }
        return -1;
    }
    
    public String getCommonNounGender(String input)
    {
        if(getCommonNounGender(input, GenderBagOfWords.FEMALE_COMMON_NOUN))
        {
            return "female";
        }
        else if(getCommonNounGender(input, GenderBagOfWords.MALE_COMMON_NOUN))
        {
            return "male";
        }
        return null;
    }
    
    public boolean getCommonNounGender(String input, String[] list)
    {
        for(String temp : list)
        {
            if(input.equalsIgnoreCase(temp))
            {
                return true;
            }
        }
        return false;
    }
    
    public LinkedList<NounTokenData> getNounTokenData(LinkedList<String> equation, LinkedList<String> phrase, String phraseType)
    {
        WordnetProcess wp = new WordnetProcess();
        System.out.println("HEY! getNounTokenData");
        LinkedList<NounTokenData> output = new LinkedList();

        for(int i=0; i<equation.size(); i++)
        {
//            System.out.println(">equation.get(i)==>"+equation.get(i));
            String type="", subtype="personal";
            if(equation.get(i).startsWith("POS["))
            {
                int index = Integer.parseInt(equation.get(i).substring(4, equation.get(i).length()-1));
                subtype="possessive";
                
            }
            if(se.isValidAccToRule(equation.get(i), "(\\d+|<|>|\\Q+\\E|\\Q*\\E)"))
            {
            }
            else if(phraseType.contentEquals("NOUN_PHRASE") && se.isValidAccToRule(equation.get(i), "((PRP(S)?:"+RegexRules.TOKEN+")|"
                    + "(N(S|P)(P)?("+RegexRules.EXTENSION+")?"+RegexRules.INDEX+"))"))
            {
                double feminine=0, masculine=0;
                String number="";
                LinkedList<String> lexnames;
                LinkedList<String> hold = new LinkedList();
                String ttemp = se.getPhraseContent("<"+equation.get(i)+">", phrase, true, se.ADJECTIVE, false, true, false);
                String mainNoun = se.getBasePartLine(se.getPhraseContent(se.replaceSpecificPartAccToRuleName(ttemp,"ADJ(S)?C",""), phrase, false, "", false, true,false), "TOKEN").trim();
                String[] holder = mainNoun.split(" ");
                for(String h : se.getSpecificPartAccToRuleName(ttemp,"ADJ(S)?C"))
                {
                    for(String m : se.getSpecificPartAccToRuleName(se.getPhraseContent(h, phrase, true, se.ADJECTIVE, false, true,false),"ADJ"))
                    {
                        String temp = "";
                        for(String t : se.getSpecificPartAccToRuleName(ttemp,"ADJ(S)?C"))
                        {
                            String ttype = ll.getCategoryPOS(se.getBasePartLine(m, "TAG").trim());
                            String content = se.getBasePartLine(m, "TOKEN").trim();
                            int index;
                            if((index = addToken(ttype, content, false, null))!=-1)
                            {
                                if(!temp.isEmpty())
                                {
                                    temp = temp+"*";
                                }
                                temp = temp+"<TOKEN["+index+"]>";
                            }
                        }
                    }
                }
                counter++;
                LinkedList<String> ln = new LinkedList();
                if(equation.get(i).startsWith("PRPS")||equation.get(i).startsWith("PRP"))
                {
                    type = "pronoun";
                    if(equation.get(i).startsWith("PRPS"))
                    {
                        subtype="possessive";
                    }
                    String mainPronoun = holder[holder.length-1];
                    boolean isNonPerson = ll.isPronounNonPerson(mainPronoun);
                    System.out.println("BWETIT mainPronoun==>"+mainPronoun+"<=>"+isNonPerson);
                    if(ll.isNounPluralAccToTag(mainPronoun))
                    {
                        number = "plural";
                    }
                    else
                    {
                        System.out.println("OMG 1");
                        number = "singular";
                        if(!isNonPerson)
                        {
                            System.out.println("OMG 2");
                            ln.add("noun.person");
                            System.out.println("OMG 2");
                            if(ll.isPronounFemale(mainPronoun))
                            {
                                feminine = 1;
                                masculine = 0;
                                
                            }
                            else
                            {
                                feminine = 0;
                                masculine = 1;
                            }
                        }
                        System.out.println("OMG 3 ==>"+feminine);
                        System.out.println("OMG 4 ==>"+masculine);
                    }
                }
                else
                {
                    if(equation.get(i).startsWith("NSP")||equation.get(i).startsWith("NPP"))
                    {
                        feminine=0.5;
                        masculine=0.5;
                        ln = ll.getAllNounLexnames();
                        type = "proper noun";
                    }
                    else if(equation.get(i).startsWith("NS")||equation.get(i).startsWith("NP"))
                    {
                        if(ksp.isAStopword(holder[holder.length-1].trim()))
                        {
                            equation.set(i,"1");
                        }
                        else if(wp.doesTermExist(holder[holder.length-1]))
                        {
                            ln = wp.getLexFileNameAccWithLimits(holder[holder.length-1], "noun","([a-z])(.)*");
                            type = "common noun";
                            if(ln.contains("noun.person"))
                            {
                                String gender = getCommonNounGender(holder[holder.length-1]);
                                System.out.println("HEY SOUL gender==>"+gender);
                                if(gender==null)
                                {
                                    feminine = 0;
                                    masculine = 0;
                                }
                                else if(gender.contentEquals("female"))
                                {
                                    feminine = 1;
                                    masculine = 0;
                                }
                                else if(gender.contentEquals("male"))
                                {
                                    feminine = 0;
                                    masculine = 1;
                                }
                            }
                        }
                        
                    }
                }
                equation.set(i, "COREFER["+output.size()+"]");
                output.add(new NounTokenData(counter, counter, hold, mainNoun, type, subtype, number, ln, feminine, masculine));
            }
            else 
            {
                
                System.out.println("NAA KO DRI===>"+equation.toString());
                String ttype = ll.getCategoryPOS(se.getBasePartLine("<"+equation.get(i)+">", "TAG").trim());
                String content = se.getBasePartLine("<"+equation.get(i)+">", "TOKEN").trim();
                System.out.println("WHATTTT!! ttype===>"+ttype);
                System.out.println("WHATTTT!! content===>"+content);
                int index;
                if((index = addToken(ttype, content, false, null))!=-1)
                {
                    equation.set(i, "TOKEN["+index+"]");
                }
                
            }
        }
        return output;
    }
    
    public void mentionDetection(String cdName, String pattern, LinkedList<String> phrase, HashMap<String, PartPointer> partpntr)
    {
        System.out.println("mentionDetection==>"+cdName+"<===>"+pattern);
        WordnetProcess wp = new WordnetProcess();
        getNounTokenData(partpntr.get("MAIN_VERB").getRoleA().getEquation(), phrase, partpntr.get("MAIN_VERB").getRoleA().getType());
        System.out.println("pattern==>"+pattern);
        for(String key : ll.getNounPhraseSequenceAccToSentencePattern(pattern))
        {
            System.out.println("<3 key==>"+key);
            if(partpntr.get(key)!=null)
            {
                System.out.println("PHRASE!!");
                System.out.println("partpntr.get(key)==>"+partpntr.get(key));
                String type="";
                boolean isMvReportingVerb = false;

                counter++;
                int entityId = counter;
                
                if(partpntr.get(key).getType().contentEquals("PHRASE"))
                {
                    System.out.println("partpntr.get(key).getContent()==>"+partpntr.get(key).getContent());
                    
                    LinkedList<NounTokenData> nountoken = getNounTokenData(partpntr.get(key).getRoleA().getEquation(), phrase, partpntr.get(key).getRoleA().getType());

                    if(!partpntr.get(key).getRoleA().getType().contentEquals("ADJECTIVE_PHRASE"))
                    {
                        LinkedList<String> lexnames = new LinkedList();
                        for(NounTokenData ntd : nountoken)
                        {
                            if(lexnames.isEmpty())
                            {
                                lexnames.addAll(ntd.getLexnames());
                            }
                            else
                            {
                                lexnames.addAll(se.getIntersection(lexnames, ntd.getLexnames()));
                            }
                        }
                        for(NounTokenData ntd : nountoken)
                        {
                            System.out.println("SWERTE 0==>"+ntd.getHeadNoun());
                            for(String ln : ntd.getLexnames())
                            {
                                System.out.println("SWERTE 1==>"+ln);
                            }
                        }
                        for(String ln : lexnames)
                        {
                            System.out.println("SWERTE 2==>"+ln);
                        }

                        nominalReferences.put(cdName+" "+key, new NominalReference(entityId, entityId, type, nountoken, key, partpntr.get(key).getNumber(), isMvReportingVerb,lexnames));
                        seqKey.add(cdName+" "+key);
                        entityKey.put(entityId+"", cdName+" "+key);
                        System.out.println("HALA KA DZAE==>"+key);
                        for(String k1 : getModifierKeys(partpntr.keySet(), key))
                        {
                            if(partpntr.get(k1).getType().contentEquals("PHRASE"))
                            {
                                System.out.println("MODIFIER==>"+k1);
                                counter++;
                                entityId = counter;

                                nountoken = getNounTokenData(partpntr.get(k1).getRoleA().getEquation(), phrase, partpntr.get(k1).getRoleA().getType());

                                lexnames = new LinkedList();
                                for(NounTokenData ntd : nountoken)
                                {
                                    if(lexnames.isEmpty())
                                    {
                                        lexnames.addAll(ntd.getLexnames());
                                    }
                                    else
                                    {
                                        lexnames.addAll(se.getIntersection(lexnames, ntd.getLexnames()));
                                    }
                                }

                                nominalReferences.put(cdName+" "+k1, new NominalReference(entityId, entityId, type, nountoken, partpntr.get(k1).getSyntacticRole(), partpntr.get(k1).getNumber(), isMvReportingVerb, lexnames));
                                seqKey.add(cdName+" "+k1);
                                entityKey.put(entityId+"", cdName+" "+k1);
                            }
                            else{
                                for(ClauseData cd : partpntr.get(k1).getRoleB())
                                {
                                    mentionDetection(cd.getHead(), cd.getSentencePattern(),
                                        phrase, cd.getParts());
                                    nominalReferences.put(cdName+" "+k1, new NominalReference(entityId, entityId, type, new LinkedList<NounTokenData>(), k1, "singular", isMvReportingVerb,new LinkedList()));
                                    seqKey.add(cdName+" "+k1);
                                    entityKey.put(entityId+"", cdName+" "+k1);
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("CLAUSE!!");
                    for(ClauseData cd : partpntr.get(key).getRoleB())
                    {
                        mentionDetection(cd.getHead(), cd.getSentencePattern(),
                            phrase, cd.getParts());
                        
                        nominalReferences.put(cdName+" "+key, new NominalReference(entityId, entityId, type, new LinkedList<NounTokenData>(), key, "singular", isMvReportingVerb,new LinkedList()));
                        seqKey.add(cdName+" "+key);
                        entityKey.put(entityId+"", cdName+" "+key);
                    }
                }
            }
        }
    }
    
    public void processSpeakerSieve()
    {
        
    }
    
    public void processStringMatch()
    {
        
    }
    
    public void processPreciseConstructs()
    {
        System.out.println("START processPreciseConstructs");
        for(String key : seqKey)
        {
            System.out.println("key==>"+key);
            String[] hold = key.trim().split(" ");
            if(nominalReferences.get(key).getPosition().contentEquals("SUBJECT_COMPLEMENT"))
            {
                //nominalReferences.get(key).setEntityId(nominalReferences.get(hold[0]+" SUBJECT").getEntityId());
                if(nominalReferences.get(hold[0]+" SUBJECT")!=null)
                {
                    System.out.println("NOW!!=>"+nominalReferences.get(key).getEntityId());
                
                    LinkedList<String> lexnames = se.getIntersection(nominalReferences.get(key).getLexnames(), nominalReferences.get(hold[0]+" SUBJECT").getLexnames());
                    for(String ln : lexnames)
                    {
                        System.out.println(">>ln=>"+ln);
                    }
                    for(NounTokenData ntd : nominalReferences.get(hold[0]+" SUBJECT").getNountoken())
                    {
                        ntd.setLexnames(lexnames);
                    }
                    nominalReferences.get(key).setLexnames(lexnames);
                    nominalReferences.get(hold[0]+" SUBJECT").setLexnames(lexnames);
                }
            }
            else if(nominalReferences.get(key).getPosition().contentEquals("OBJECT_COMPLEMENT"))
            {
                if(nominalReferences.get(hold[0]+" DIRECT_OBJECT")!=null)
                {
                    nominalReferences.get(key).setEntityId(nominalReferences.get(hold[0]+" DIRECT_OBJECT").getEntityId());
                    System.out.println("NOW!!=>"+nominalReferences.get(key).getEntityId());
                    LinkedList<String> lexnames = se.getIntersection(nominalReferences.get(key).getLexnames(), nominalReferences.get(hold[0]+" DIRECT_OBJECT").getLexnames());
                    for(String ln : lexnames)
                    {
                        System.out.println(">>ln=>"+ln);
                    }
                    for(NounTokenData ntd : nominalReferences.get(hold[0]+" DIRECT_OBJECT").getNountoken())
                    {
                        ntd.setLexnames(lexnames);
                    }
                    nominalReferences.get(hold[0]+" DIRECT_OBJECT").setLexnames(lexnames);
                }
            }
        }
    }
    
    public void addNominalReferenceToStack(Stack<String> stackNounRefPntr, String input)
    {
        int i=stackNounRefPntr.size()-1;
        System.out.println("i==>"+i);
        while(i>-1 && getPriority(nominalReferences.get(stackNounRefPntr.get(i)).getPosition())
                < getPriority(nominalReferences.get(input).getPosition()))
            i--;
        System.out.println("i==>"+i);
        stackNounRefPntr.add(i+1,""+input);
    }
    
    public void pronounResolutionSieve()
    {
        System.out.println("===============PronounResolutionSieve====================");
        Stack<String> stackNounRefPntr = new Stack();
        
        for(String key : seqKey)
        {
            System.out.println("key===>"+key);
            
            for(int i=0; i<nominalReferences.get(key).getNountoken().size(); i++)
            {
                if((nominalReferences.get(key).getNountoken().get(i).getEntityId()==nominalReferences.get(key).getNountoken().get(i).getMentionId())
                        && nominalReferences.get(key).getNountoken().get(i).getType().contentEquals("pronoun") && ll.isThirdPerson(nominalReferences.get(key).getNountoken().get(i).getHeadNoun()))
                {
                    System.out.println("HELLO PRO==>"+nominalReferences.get(key).getNountoken().get(i).getHeadNoun());
                    while(!stackNounRefPntr.isEmpty())
                    {
                        String temp = stackNounRefPntr.pop();
                        System.out.println("===info===");
                        System.out.println("number==>"+nominalReferences.get(key).getNountoken().get(i).getNumber());
                        for(String lex : nominalReferences.get(key).getNountoken().get(i).getLexnames())
                        {
                            System.out.println("lexnames==>"+lex);
                        }
                        System.out.println("masculine==>"+nominalReferences.get(key).getNountoken().get(i).getMasculine());
                        System.out.println("feminine==>"+nominalReferences.get(key).getNountoken().get(i).getFeminine());
                        System.out.println("===info===");
                        
                        System.out.println("===based info===");
                        System.out.println("entity id==>"+nominalReferences.get(temp).getEntityId());
                        System.out.println("number==>"+nominalReferences.get(temp).getNumber());
                        for(String lex : nominalReferences.get(temp).getLexnames())
                        {
                            System.out.println("lexnames==>"+lex);
                        }
                        System.out.println("===based info===");
                        
                        if(nominalReferences.get(temp).getNumber().contentEquals(nominalReferences.get(key).getNountoken().get(i).getNumber()))
                        {
                            if(nominalReferences.get(temp).getNumber().contentEquals("plural"))
                            {
                                nominalReferences.get(key).getNountoken().get(i).setEntityId(nominalReferences.get(temp).getEntityId());
                                break;
                            }
                            else if(!nominalReferences.get(key).getNountoken().get(i).getLexnames().contains("noun.person"))
                            {
                                System.out.println("HAS NO PERSON");
                                if(!nominalReferences.get(temp).getLexnames().contains("noun.person"))
                                {
                                    System.out.println("HEY!!! WHAT THE HECK!!");
                                    nominalReferences.get(key).getNountoken().get(i).setEntityId(nominalReferences.get(temp).getEntityId());
                                    break;
                                }
                            }
                            else
                            {
                                double femalePnts=0, malePnts=0;
                                for(int j=0; j<nominalReferences.get(temp).getNountoken().size(); j++)
                                {
                                    System.out.println(">BLEH!=>"+nominalReferences.get(temp).getNountoken().get(j).getHeadNoun());
                                    System.out.println(">BLEH!=>"+nominalReferences.get(temp).getNountoken().get(j).getFeminine());
                                    System.out.println(">BLEH!=>"+nominalReferences.get(temp).getNountoken().get(j).getMasculine());
                                    
                                    System.out.println("HAS PERSON");
                                    femalePnts = femalePnts+nominalReferences.get(temp).getNountoken().get(j).getFeminine();
                                    malePnts = malePnts+nominalReferences.get(temp).getNountoken().get(j).getMasculine();
                                    
                                }
                                System.out.println("femalePnts==>"+femalePnts);
                                System.out.println("malePnts==>"+malePnts);
                                double totalPnts = femalePnts+malePnts;
                                femalePnts = totalPnts/femalePnts;
                                malePnts = totalPnts/malePnts;

                                if(nominalReferences.get(key).getNountoken().get(i).getFeminine()>=0.5 && femalePnts>=0.5)
                                {
                                    nominalReferences.get(key).getNountoken().get(i).setEntityId(nominalReferences.get(temp).getEntityId());
                                    break;
                                }
                                else if(nominalReferences.get(key).getNountoken().get(i).getMasculine()>=0.5 && malePnts>=0.5)
                                {
                                    nominalReferences.get(key).getNountoken().get(i).setEntityId(nominalReferences.get(temp).getEntityId());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if(nominalReferences.get(key).getPosition().contentEquals("SUBJECT")
                    ||nominalReferences.get(key).getPosition().contentEquals("DIRECT_OBJECT")
                    ||nominalReferences.get(key).getPosition().contentEquals("INDIRECT_OBJECT"))
            {
                addNominalReferenceToStack(stackNounRefPntr, key);
            }
        }
        System.out.println("===============PronounResolutionSieve====================");
    }
    
    public void replaceCoreferInEquation(LinkedList<String> equation, NominalReference nr)
    {
        System.out.println("START replaceCoreferInEquation===>"+equation);
        for(int i=0; i<equation.size(); i++)
        {
            
            if(equation.get(i).startsWith("COREFER["))
            {
            	System.out.println("**equation["+i+"]==>"+equation.get(i));
                int index = Integer.parseInt(equation.get(i).substring(8, equation.get(i).length()-1));
                System.out.println(nr.getNountoken().get(index).getEntityId()+"<=>"+nr.getNountoken().get(index).getMentionId()+"<=>"+nr.getNountoken().get(index).getHeadNoun());
                if(nr.getNountoken().get(index).getEntityId()==nr.getNountoken().get(index).getMentionId() ||
                        (nr.getPosition().contentEquals("SUBJECT_COMPLEMENT") || nr.getPosition().contentEquals("OBJECT_COMPLEMENT")))
                {
                    int indexToken;
                    if(nr.getNountoken().get(index).getType().contentEquals("pronoun"))
                    {
                        System.out.println("HORRAY!!! "+nr.getNountoken().get(index).getHeadNoun());
                        if((indexToken=addToken("pronoun", nr.getNountoken().get(index).getHeadNoun(), true, nr.getNountoken().get(index).getLexnames()))!=-1)
                        {
                            equation.set(i,"TOKEN["+indexToken+"]");
                        }
                        else
                        {
                            System.out.println("WHY GIRL?? WHY????");
                        }
                    }
                    else if((indexToken=addToken("noun", nr.getNountoken().get(index).getHeadNoun(), true, nr.getNountoken().get(index).getLexnames()))!=-1)
                    {
                        equation.set(i,"TOKEN["+indexToken+"]");
                    }
                    else
                    {
                        System.out.println("HORRAY FOR THE PHILIPPINES!!!");
                        equation.set(i,"0");
                    }
                }
                else
                {
                    LinkedList<String> temp = new LinkedList(); 
                    System.out.println(">how?>"+nr.getNountoken().get(index).getEntityId());
                    System.out.println(">content how>"+nr.getNountoken().get(index).getHeadNoun());
                    
                    for(NounTokenData ntd : nominalReferences.get(entityKey.get(nr.getNountoken().get(index).getEntityId()+"")).getNountoken())
                    {
                          int indexToken;
                        if((indexToken=addToken("noun", ntd.getHeadNoun(), true, nr.getNountoken().get(index).getLexnames()))!=-1)
                        {
                            System.out.println("indexToken==>"+indexToken);
                            temp.add("TOKEN["+indexToken+"]");
                        }
                        else
                        {
                            temp.add("0");
                        }
                    }
                    equation.remove(i);
                    equation.addAll(i, temp);
                }
            }
        }
        
    }
    
    public void postProcess(String cdName, String pattern, LinkedList<String> phrase, HashMap<String, PartPointer> partpntr)
    {
        System.out.println("START postProcess");
        for(String key : ll.getNounPhraseSequenceAccToSentencePattern(pattern))
        {
            if(partpntr.get(key)!=null)
            {
                String type="";
                boolean isMvReportingVerb = false;

                if(partpntr.get(key).getType().contentEquals("PHRASE"))
                {
                    replaceCoreferInEquation(partpntr.get(key).getRoleA().getEquation(),nominalReferences.get(cdName+" "+key));
                    for(String k1 : getModifierKeys(partpntr.keySet(), key))
                    {
                        System.out.println(">>"+k1);
                        if(partpntr.get(k1).getType().contentEquals("PHRASE"))
                        {
                            replaceCoreferInEquation(partpntr.get(k1).getRoleA().getEquation(),nominalReferences.get(cdName+" "+k1));
                        }
                        else
                        {
                            for(ClauseData cd : partpntr.get(k1).getRoleB())
                            {
                                postProcess(cd.getHead(), cd.getSentencePattern(),phrase, cd.getParts());
                            }                       
                        }
                    }
                }
                else
                {
                    for(ClauseData cd : partpntr.get(key).getRoleB())
                    {
                        postProcess(cd.getHead(), cd.getSentencePattern(),phrase, cd.getParts());
                    }
                    
                }
            }
        }
        for(String key : entityKey.keySet())
        {
            System.out.println("entityKey==>"+key+"<=>"+entityKey.get(key));
        }
        System.out.println("END postProcess");
    }
}
