/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smarthire.thaliaNew.Interfaces.RegexRules;
import com.smarthire.thaliaNew.Model.ClauseData;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.PartPointer;
import com.smarthire.thaliaNew.Model.PhraseData;
import com.smarthire.thaliaNew.Model.WordInfo;

/**
 *
 * @author Nathalia
 */
public class ClauseEvaluator {
        
        HashMap<String, String> grammarPattern; 
        String[] categories;
        
        LinguisticLibrary ll = new LinguisticLibrary();
        StringEvaluator se = new StringEvaluator();
        KeySearchProcess ksp = new KeySearchProcess();
        
        public ClauseEvaluator(KeySearchProcess ksp)
        {
            grammarPattern = new HashMap();
            
            grammarPattern.put("NO_OBJECT", RegexRules.NO_OBJECT);
            grammarPattern.put("ONE_OBJECT", RegexRules.ONE_OBJECT);
            grammarPattern.put("DOUBLE_OBJECT", RegexRules.DOUBLE_OBJECT);
            
            grammarPattern.put("PRESENT_TENSE", RegexRules.PRESENT_TENSE);
            grammarPattern.put("PAST_TENSE", RegexRules.PAST_TENSE);
            grammarPattern.put("FUTURE_TENSE", RegexRules.FUTURE_TENSE);
            
            grammarPattern.put("PERFECT_PROGRESSIVE_ASPECT", RegexRules.PERFECT_PROGRESSIVE_ASPECT);
            grammarPattern.put("PROGRESSIVE_ASPECT", RegexRules.PROGRESSIVE_ASPECT);
            grammarPattern.put("PERFECT_ASPECT", RegexRules.PERFECT_ASPECT);
            
            categories = ("drug,alcohol,"
                    + "gun,charity,"
                    + "sex,politics,profane,"
                    + "polarity,firstPerson").split(",");
        }
        
        public String getNameRule(String input, String[] rules)
        {
            for(String temp : rules)
            {
                String rule  = grammarPattern.get(temp);
                if (!rule.isEmpty()) {
                    
                    Pattern p = Pattern.compile(rule);
                    Matcher m = p.matcher(input);

                    while (m.find()) {
                        return temp;
                    }
                }
            }
            return null;
        }
        
        public String getVerbTense(String input)
        {
            return getNameRule(input, ("FUTURE_TENSE,PAST_TENSE,PRESENT_TENSE").split(","));
        }
        
        public String getVerbAspect(String input)
        {
            return getNameRule(input, ("PERFECT_PROGRESSIVE_ASPECT,PROGRESSIVE_ASPECT,PERFECT_ASPECT").split(","));
        }
        
        public HashMap<String, PartPointer> getBaseNounPhrasePointers(String input, String pattern, String voice, String mainType, 
                String mainSyntacticRole, LinkedList<String> phrase)
        {
            System.out.println("getBaseNounPhrasePointers mainType===>"+mainType);
            HashMap<String, PartPointer> output = new HashMap();
            String content[] = se.getSpecificPartAccToRuleName(se.getPhraseContent(input, phrase, true, se.BASE, false, true,false), se.BASE);
            
            for(String temp : content)
            {
                if(temp.startsWith("<PP["))
                {
                    Pattern p = Pattern.compile("<P("+RegexRules.EXTENSION+")"+ RegexRules.INDEX +">(<(NP(GS|GP|IS|IP|SO|PO|S|P|Y|F)P(C)?)"+ RegexRules.INDEX +">)");
                    Matcher m = p.matcher(phrase.get(se.getIndex(temp)));
                    StringBuffer result = new StringBuffer();
                    
                    while (m.find()) {
                        int index = se.getIndex(m.group(7));
                        String preposition = se.getBasePartLine(se.getPhraseContent(phrase.get(Integer.parseInt(m.group(5))), phrase, false, "", false, true,false), "TOKEN");
                        String semanticPrep = ll.getConnectorGenericSemanticRole(preposition);
                               
                        String syntacticPrep = mainSyntacticRole+"_MODIFIER["+index+"]";
                        
                        if(!mainSyntacticRole.contentEquals("SUBJECT") && !preposition.trim().contentEquals("of"))
                        {
                            syntacticPrep = "CLAUSE_MODIFIER["+index+"]";
                        }
                        else
                        {
                            PhraseData pd = new PhraseData("NOUN_PHRASE", m.group(8), getEquation(m.group(7), phrase, false));
                            output.put(syntacticPrep, new PartPointer("PHRASE", pd, se.getPhraseContent(m.group(7), phrase, false, "", false, true,false),
                                syntacticPrep, ll.getNumberAccToTag(m.group(8))));
                        }
                        
                    }
                }
                else if(temp.startsWith("<CSUB"))
                {
                    int index = se.getIndex(temp);
                    LinkedList<ClauseData> cd = getClauses(phrase.get(index), phrase,true);
                    output.put(mainSyntacticRole+"_MODIFIER["+index+"]", new PartPointer("CLAUSE", cd, se.getPhraseContent(phrase.get(index), phrase, false, "", false, true,false),
                        mainSyntacticRole+"_MODIFIER["+index+"]", "singular"));
                
                }
                else if(temp.startsWith("<INF"))
                {
                    int index = se.getIndex(temp);
                    
                    LinkedList<ClauseData> cd = getClauses(phrase.get(index), phrase,false);
                    output.put(mainSyntacticRole+"_MODIFIER["+index+"]", new PartPointer("CLAUSE", cd, se.getPhraseContent(phrase.get(index), phrase, false, "", false, true, false),
                        mainSyntacticRole+"_MODIFIER["+index+"]", "singular"));
                }
                else
                {
                    int index = se.getIndex(temp);
                    String headTag = se.getPartLine(temp, "TAG").trim();
                    
                    PhraseData pd = new PhraseData(mainType, headTag, getEquation(temp, phrase, false));
                    output.put(mainSyntacticRole, new PartPointer("PHRASE", pd, se.getPhraseContent(temp ,phrase, false, "", false, true,false),
                        mainSyntacticRole,  ll.getNumberAccToTag(headTag)));
                    
                }
            }
            
            return output;
        }
        
        public String getPredicate(String input, LinkedList<String> phrase)
        {
            System.out.println("START getPredicate "+input);
            String hold = "";
            LinkedList<ClauseData> cd = new LinkedList();
            
            String rule = "(<("+RegexRules.NOUN_PHRASE_ALL_TAG+"("+RegexRules.EXTENSION+")?)"+ RegexRules.INDEX +">)?("+RegexRules.PREDICATE_PHRASE+")";
            if (!rule.isEmpty()) {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);
               
                while (m.find()) {
//                    for(int i=0; i<m.groupCount(); i++)
//                        if(m.group(i)!=null)
//                            System.out.println("HELP m.group("+i+")==>"+m.group(i));
                    for(String temp : getParts(se.getPhraseContent(m.group(18), phrase, true, se.PREDICATE, false, true,false)))
                    {
                        String np = "";
                        if(m.group(1)!=null)
                            np = m.group(1);
                        hold = hold+"<START>"+np+phrase.get(se.getIndex(temp))+"<END>";
                    }
                }
            }
            return hold;
        }
        
        public String[] getParts(String input)
        {
            String rule = "(<(("+RegexRules.TAG + RegexRules.INDEX+")|("+RegexRules.TAG+":"+RegexRules.TOKEN+"))>)";
            String result="";
            if (!rule.isEmpty()) {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);
               
                while (m.find()) {
                    result=" "+m.group(0);
                }
            }
            return result.trim().split(" ");
        }
        
        public LinkedList<ClauseData> getClauses(String input, LinkedList<String> phrase, boolean isRelativeClause)
        {
            System.out.println("START getClauses");
            String type = "CLAUSE";
            String conn[] = se.getSpecificPartAccToRule(input, "(<(W(DT|PS|P|RB)|P)("+RegexRules.EXTENSION+")?(:("+ RegexRules.TOKEN +")|"+RegexRules.INDEX+")>)");
            if(conn.length>0)
            {
                String temp = se.getBasePartLine(se.getPhraseContent(conn[0].trim(), phrase, false, "", false, true,false), "TOKEN").trim();
                System.out.println("clause temp okay!=>"+temp);
                if(isRelativeClause)
                {
                    type = temp.toUpperCase()+"_CLAUSE";
                }
                else
                {
                    type = ll.getConnectorGenericSemanticRole(temp)+"_CLAUSE";
                }
            }
            System.out.println("clause TYPE okay!=>"+type);
            String hold = "";
            int index=-1;
            String clauseTag = "";
            LinkedList<ClauseData> cd = new LinkedList();
            String rule = "<(CSVOINM|CSSWEVO|CPSWEVO|CSSBEVO|CPSBEVO|CSSBVO|CPSBVO|CVOIMM|CSVO)("+RegexRules.INDEX+")>";
            if (!rule.isEmpty()) {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);

                while (m.find()) {
                    index = Integer.parseInt(m.group(4));
                    hold = hold+getPredicate(phrase.get(index), phrase);
                    clauseTag = m.group(1);
                }
            }
            System.out.println("HERE clauseTag==>"+clauseTag);
            return evaluateClauses(index,hold,phrase,clauseTag,type);
        }
        
        public LinkedList<ClauseData> evaluateClauses(int index, String input, LinkedList<String> phrase, String clauseTag, String type)
        {
            WordnetProcess wp = new WordnetProcess();
            LinkedList<ClauseData> cd = new LinkedList();
            System.out.println("evaluateClauses input===>"+input);
            LinkedList<String> mainVerb = new LinkedList();
            for (String name : ("DOUBLE_OBJECT,ONE_OBJECT,NO_OBJECT").split(",")) {
                System.out.println(">> name==>"+name);
                String rule = "(<START>)("+grammarPattern.get(name)+")(<END>)";

                if (!rule.isEmpty()) {
                    Pattern p = Pattern.compile(rule);
                    Matcher m = p.matcher("<START>"+input+"<END>");
                    StringBuffer result = new StringBuffer();
                    while (m.find()) {
                        
//                        for(int i = 0;  i<m.groupCount(); i++)
//                        {
//                            System.out.println("DUTERTE RULES m.group("+i+")===>"+m.group(i));
//                        }
                        HashMap<String, PartPointer> parts = new HashMap();
                        
                        String mvContent = se.getPhraseContent(m.group(20), phrase, true, se.VERB+"(C)?", false, true,false);
                        
                        String tense = getVerbTense(mvContent);
                        String aspect = getVerbAspect(mvContent);
                        LinkedList frames = new LinkedList();
                        LinkedList<String> lexname = new LinkedList();
                        for(String mv : se.getMainVerb(m.group(20), phrase))
                        {
                            mainVerb.add(mv.trim());
                            String temp = se.getBasePartLine(se.getPhraseContent(mv, phrase, false, "", false, true,false), "TOKEN");
                            if(lexname.isEmpty() && !temp.isEmpty())
                            {
                                lexname.addAll(wp.getLexFileName(temp, "verb"));
                            }
                            else if(!temp.isEmpty()){
                                lexname.addAll(se.getIntersection(lexname, wp.getLexFileName(temp, "verb")));
                            }
                            
                            if(frames.isEmpty() && !temp.isEmpty())
                            {
                                frames.addAll(wp.getVerbFramesGeneral(temp, true));
                            }
                            else if(!temp.isEmpty()){
                                frames.addAll(se.getIntersection(frames, wp.getVerbFramesGeneral(temp, true)));
                            }
                            
                        }
                        String pattern = "", voice = "ACTIVE_VOICE";
                        if(isPassive(mvContent))
                        {
                            voice = "PASSIVE_VOICE";
                        }
                        
                        parts.put("MAIN_VERB", new PartPointer("PHRASE", new PhraseData("VERB_PHRASE", "", getEquation(m.group(20), phrase, false)),
                            se.getPhraseContent(m.group(20),phrase, false, "", false, true,false), "MAIN_VERB", ""));
                        
                        String typePhrase = "NOUN_PHRASE";
                        
                        if(name.contentEquals("NO_OBJECT"))
                        {
                            pattern = "INTRANSITIVE_PATTERN";
                        }
                        else if(name.contentEquals("ONE_OBJECT"))
                        {
                            if(frames.contains("NP V Adjective/Noun"))
                            {
                                pattern = "SUBJECT_COMPLEMENT";
                                if(m.group(38).startsWith("ADJ"))
                                {
                                    typePhrase = "ADJECTIVE_PHRASE";
                                }
                            }
                            else if(frames.contains("NP V Adjective"))
                            {
                                if(m.group(38).startsWith("ADJ"))
                                {
                                    System.out.println("OMY WHY DI BA?");
                                    typePhrase = "ADJECTIVE_PHRASE";
                                    pattern = "SUBJECT_COMPLEMENT";
                                }
                                else
                                {
                                    pattern = "SIMPLE_TRANSITIVE_PATTERN";
                                }
                            }
                            else
                            {
                                pattern = "SIMPLE_TRANSITIVE_PATTERN";
                            }
                            
                            String synRole;
                            if(pattern.contentEquals("SUBJECT_COMPLEMENT"))
                            {
                                synRole = "SUBJECT_COMPLEMENT";
                            }
                            else
                            {
                                synRole = "DIRECT_OBJECT";
                            }
                            if(m.group(38).contentEquals("CSUBN")||m.group(38).contentEquals("CSUBNJ")
                                    ||m.group(38).contentEquals("CSUBNV")||m.group(38).contentEquals("CSUBNJV"))
                            {
                                parts.put(synRole, new PartPointer("CLAUSE", getClauses(phrase.get(se.getIndex(m.group(37))), phrase, true),
                                    se.getPhraseContent(phrase.get(se.getIndex(m.group(37))), phrase, false, "", false, true,false),
                                    synRole, "singular"));
                            }
                            else
                            {
                               parts.putAll(getBaseNounPhrasePointers(m.group(37), pattern, voice, typePhrase, synRole, phrase));
                            }
                            
                        }
                        else if(name.contentEquals("DOUBLE_OBJECT"))
                        {
                            String n1, n2;
                            
                            if(frames.contains("NP V NP Adjective/Noun"))
                            {
                                pattern = "OBJECT_COMPLEMENT";
                                n1 = "DIRECT_OBJECT";
                                n2 = "OBJECT_COMPLEMENT";
                            }
                            else
                            {
                                pattern = "DOUBLE_OBJECT";
                                n1 = "INDIRECT_OBJECT";
                                n2 = "DIRECT_OBJECT";
                            }
                            String t2;
                            if(m.group(57).startsWith("ADJ"))
                            {
                                t2 = "ADJECTIVE_PHRASE";
                            }
                            else
                            {
                                t2 = "NOUN_PHRASE";
                            }
                            parts.putAll(getBaseNounPhrasePointers(m.group(37), pattern, voice, "NOUN_PHRASE",n1,phrase));
                            
                            parts.putAll(getBaseNounPhrasePointers(m.group(56), pattern, voice, t2,n2,phrase));

                        }
                        if(m.group(3)!=null)
                        {
                            if(m.group(4).contentEquals("CSUBN")||m.group(4).contentEquals("CSUBNJ")
                                    ||m.group(4).contentEquals("CSUBNV")||m.group(4).contentEquals("CSUBNJV"))
                            {
                                parts.put("SUBJECT", new PartPointer("CLAUSE", getClauses(phrase.get(Integer.parseInt(m.group(18))), phrase, true),
                                        se.getPhraseContent(phrase.get(Integer.parseInt(m.group(18))), phrase, false, "", false, true,false),
                                        "SUBJECT", "singular"));
                            }
                            else
                            {
                                parts.putAll(getBaseNounPhrasePointers(m.group(3), pattern, voice, "NOUN_PHRASE", "SUBJECT", phrase));
                            }
                        }
                        
                        
                        String content = (m.group(0).replace("<START>", "")).replace("<END>", "");
                        String mood="", submood="";
                        if(type.contentEquals("CONDITIONAL_CLAUSE") && 
                                (clauseTag.contentEquals("CSSWEVO")||clauseTag.contentEquals("CPSWEVO")||tense.contentEquals("PAST_TENSE")))
                        {
                            mood = "SUBJUNCTIVE_MOOD";
                            submood = "CONTRARY_TO_FACT";
                        }
                        else if(type.contentEquals("THAT_CLAUSE") && 
                                (clauseTag.contentEquals("CSSBVO")||clauseTag.contentEquals("CPSBVO")))
                        {
                            mood = "SUBJUNCTIVE_MOOD";
                            submood = "DEMAND_OR_SUGGEST";
                        }
                        else if(clauseTag.contentEquals("CVOIMM"))
                        {
                            mood = "IMPERATIVE_MOOD";
                        }
                        else{
                            mood = "INDICATIVE_MOOD";
                        }
                        cd.add(new ClauseData("<"+clauseTag+"["+index+"]>",content,pattern,mood,submood,
                                type,voice,aspect,tense,1, parts));
                        m.appendReplacement(result, "<S>");
                        
//                        System.out.println("PATTERN===>"+pattern);
                        
                    }
                    m.appendTail(result);
                    input = (result.toString().replace("<START>", "")).replace("<END>", "");
                }
            }
            return cd;
        }
        
        public boolean isPassive(String input)
        {
            String rule = RegexRules.PASSIVE_VOICE;
            if (!rule.isEmpty()) {
                Pattern p = Pattern.compile(rule);
                Matcher m = p.matcher(input);

                while (m.find()) {
                    return true;
                }
            }
            return false;
        }
        
        public String[] splitParenthesisAndContent(String input)
        {
            input = input.trim();
            input = input.replace("<", " < ");
            input = input.replace(">", " > ");
            String temp[] = input.split(" ");
            return temp;
        }
        
        public LinkedList<String> getEquation(String input, LinkedList<String> phrase, boolean isClause)
        {
            String temp;
            if(isClause)
                temp = se.getPhraseContent(input, phrase,true,se.CLAUSE,false,false,false);
            else
                temp = se.getPhraseContent(input, phrase,true,se.NOUN_TOKEN,false,false,false);
            temp = temp.replace("><", ">*<");
//            System.out.println("getEquation==>"+temp);
            return preevaluateEquation(transformToLinkedList(splitParenthesisAndContent(temp)));
        }
        
        public LinkedList<String> transformToLinkedList(String[] input)
        {
            LinkedList<String> result = new LinkedList();
            result.addAll(Arrays.asList(input));
            return result;
        }
        
        public LinkedList<String> preevaluateEquation(LinkedList<String> equation)
        {
            System.out.println("START preevaluateEquation===>"+equation.toString());
            for(int i=0; i<equation.size(); i++)
            {
                if(!equation.get(i).isEmpty())
                {
                    System.out.println("eq==>"+equation.get(i));
                    if(equation.get(i).contains(":"))
                    {
                        String temp[] = equation.get(i).trim().split(":");
                        String tag = ll.getCategoryPOS(temp[0]);
                        System.out.println("HEY GHURL..."+temp[1]+"<<==>>"+tag);
                        if(!tag.contentEquals("pronoun") && ksp.isAStopword(temp[1].trim()))
                        {
                            System.out.println("WHY WHY WHY DELILA");
                            int polarity = ksp.getPolarityPoint(temp[1],"");
                            if(polarity==0)
                                equation.set(i,"1");
                            else
                                equation.set(i,""+polarity);
                        }
                    }
                }
                else
                {
                    equation.remove(i);
                    i=i-1;
                }
            }
            System.out.println("END preevaluateEquation===>"+equation);
            return equation;
        }
        
        public HashMap<String, String> evaluateEquation(LinkedList<String> postEquation, LinkedList<WordInfo> tokens)
        {
            System.out.println("STARTING HERE evaluateEquation");
            System.out.println("EQUATION....."+postEquation.toString());
            EquationProcessor eqProcessor = new EquationProcessor();
            HashMap<String, String> result = new HashMap();
            for(String category : categories)
            {
                LinkedList<String> newEquation = new LinkedList();
                        //eqProcessor.getPostFix(postEquation);
                boolean flag = false;
                for(String eq : postEquation)
                {
                    System.out.println("Hey girl!!! eq===>"+eq);
                    if(eq.startsWith("TOKEN["))
                    {
                        boolean flagA = false;
                        int index = Integer.parseInt(eq.substring(6, eq.length()-1));
                        if(category.contentEquals("firstPerson"))
                        {
                            if(tokens.get(index).getPostag().contentEquals("pronoun")
                                    && ll.isFirstPerson(tokens.get(index).getWord()))
                            {
                                flag = true;
                            }
                            newEquation.add("1");
                        }
                        else if(category.contentEquals("polarity"))
                        {
                            newEquation.add(""+ksp.getPolarityPoint(tokens.get(index).getWord(), tokens.get(index).getPostag()));
                        }
                        else
                        {
                            if(tokens.get(index).getGlossInfo()!=null && !tokens.get(index).getGlossInfo().isEmpty())
                            {
                                for(GlossInfo gi : tokens.get(index).getGlossInfo())
                                {
                                    if(ksp.isInputInBasis(gi.getWordSenseID(), ksp.getCategoryKeyword(), category))
                                    {
                                        flagA = true;
                                    }
                                }
                                if(flagA)
                                {
                                    flag=true;
                                }
                            }
                            newEquation.add("1");
                        }
                    }
                    else
                    {
                        newEquation.add(eq);
                    }
                }
                
                if(flag || category.contentEquals("polarity"))
                {
                    try{
                    result.put(category, eqProcessor.calculateResult(newEquation));
                    
                    }catch(Exception e)
                    {
                        System.out.println("There is an exception..");
                        result.put(category, "0");
                    }
                }
            }
            
            System.out.println("END evaluateEquation..."+postEquation.toString());
            
            return result;
        }
        
        public void setAdditionalTokens(String role, HashMap<String, String> token, HashMap<String, PartPointer> partPntr)
        {
            for(String tempKey : partPntr.keySet())
            {
                if(tempKey.startsWith(role+"_MODIFIER"))
                {
                    for(String category : categories)
                    {
                        if(partPntr.get(tempKey).getCategoryPnt().get(category)!=null)
                        {
                            if(token.get(category)==null)
                            {
                                token.put(category, partPntr.get(tempKey).getCategoryPnt().get(category));
                            }
                            else
                            {
                                token.replace(category, ""+(Integer.parseInt(token.get(category)) + Integer.parseInt(partPntr.get(tempKey).getCategoryPnt().get(category))));
                            }
                        }
                    }
                }  
            }
        }
        
        public HashMap<String, String> evaluateClause(ClauseData input)
        {
            System.out.println("================================PRINTS=========================================");
            for(String temp : input.getParts().keySet())
            {
                System.out.println("KEY===>"+temp);
                System.out.println("CONTENT====>"+input.getParts().get(temp).getContent());
                System.out.println(""+input.getParts().get(temp).getType());
                for(String key : input.getParts().get(temp).getCategoryPnt().keySet())
                {
                    System.out.println(key+"<==>"+input.getParts().get(temp).getCategoryPnt().get(key));
                }   
            }
            boolean isImperativeMood = false;
            if(input.getMood().contentEquals("IMPERATIVE_MOOD"))
                isImperativeMood = true;
            for(String key : ll.getPhraseSequenceAccToSentencePattern(input.getSentencePattern(), isImperativeMood))
            {
                if(input.getParts().get(key)!=null)
                {
                    setAdditionalTokens(key, input.getParts().get(key).getCategoryPnt(), input.getParts());
                }
                
            }
            System.out.println("=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>=>>");
            System.out.println(">> "+input.getContent());
            HashMap<String, String> result = new HashMap();
            String roleSeq[];
            if(input.getMood().contentEquals("IMPERATIVE_MOOD"))
                roleSeq = ll.getPhraseSequenceAccToSentencePattern(input.getSentencePattern(), true);
            else
                roleSeq = ll.getPhraseSequenceAccToSentencePattern(input.getSentencePattern(), false);
            for(String category : categories)
            {
                LinkedList<String[]> pSeq = new LinkedList();
                System.out.println("CATEGORY==>"+category);
                LinkedList<String[]> possSeq;
             
                if(input.getMood().contentEquals("IMPERATIVE_MOOD"))
                {
                    possSeq = ll.getPossibleSequence(input.getSentencePattern(),true,category);
                }
                else
                {
                    possSeq = ll.getPossibleSequence(input.getSentencePattern(),false,category);
                }
                
                int index = 0;
                
                while(index<possSeq.size())
                {
                    System.out.println("");
                    boolean flag = false;
                    for(int i=0; i<roleSeq.length; i++)
                    {
                        if(input.getParts().get(roleSeq[i])!=null)
                        {
                            if(possSeq.get(index)[i].isEmpty())
                            {
                            }
                            else if(input.getParts().get(roleSeq[i]).getCategoryPnt().get(possSeq.get(index)[i])==null)
                            {
                                possSeq.remove(index);
                                flag=true;
                                break;
                            }
                        }
                        else
                        {
                            while(index<possSeq.size() && !possSeq.get(index)[i].isEmpty())
                            {
                                possSeq.remove(index);
                                System.out.println("REMOVE 3");
                            }
                            break;
                        }

                    }
                    if(!flag)
                    {
                        index++;
                    }
                }
                int total=0;
                if(!possSeq.isEmpty())
                {
                    for(String[] temp : possSeq)
                    {
                        int tempTotal=1;
                        for(int i=0; i<roleSeq.length; i++)
                        {
                            if(input.getParts().get(roleSeq[i])!=null && !temp[i].isEmpty())
                            {
                                tempTotal=tempTotal*Integer.parseInt(input.getParts().get(roleSeq[i]).getCategoryPnt().get(temp[i]));
                            }
                        }
                        total = total + tempTotal;
                    }
                    if(input.getMood().contentEquals("SUBJUNCTIVE_MOOD") && input.getSubMood().contentEquals("CONTRARY_TO_FACT"))
                    {
                        total = total * -1;
                    }
                    result.put(category, ""+total);
                }
            }
//            
//            result.put("polarity", ""+total);
            return result;
        }
        
    }
    