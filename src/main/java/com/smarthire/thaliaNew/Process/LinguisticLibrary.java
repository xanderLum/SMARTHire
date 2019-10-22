/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Nathalia
 */
public class LinguisticLibrary {
    
    WordnetProcess wp = new WordnetProcess();
    StringEvaluator se = new StringEvaluator();
    
    public LinkedList<String[]> getPossibleSequence(String pattern, boolean isImperativeMood, String category)
    {
        LinkedList<String[]> output = new LinkedList();
        System.out.println("getPossibleSequence==>"+pattern);
        switch(pattern)
        {
            case "INTRANSITIVE_PATTERN":
                if(isImperativeMood)
                {
                    output.add(new String[] {category});
                }
                else
                {
                    output.add(new String[] {"firstPerson",category});
                    output.add(new String[] {category,"polarity"});
                }
                break;
            case "SIMPLE_TRANSITIVE_PATTERN":
                if(isImperativeMood)
                {
                    output.add(new String[] {category,""});
                    output.add(new String[] {"polarity",category});
                }
                else
                {
                    output.add(new String[] {"firstPerson",category,""});
                    output.add(new String[] {"firstPerson","polarity",category});
                    output.add(new String[] {category,"polarity",""});
                }
                break;
            case "SUBJECT_COMPLEMENT":
                if(isImperativeMood)
                {
                    output.add(new String[] {"polarity",category});
                }
                else
                {
                    output.add(new String[] {"firstPerson","polarity",category});
                    output.add(new String[] {category,"polarity","firstPerson"});
                    output.add(new String[] {category,"polarity","polarity"});
                }
                break;
            case "OBJECT_COMPLEMENT_ADJECTIVE":
                if(isImperativeMood)
                {
                    output.add(new String[] {"polarity",category,"polarity"});
                    output.add(new String[] {category,"",""});
                }
                else
                {
                    output.add(new String[] {"firstPerson","polarity",category,"polarity"});
                    output.add(new String[] {"firstPerson",category,"",""});
                    output.add(new String[] {category,"polarity","",""});
                }
                break;
            case "DOUBLE_NOUN":
                if(isImperativeMood)
                {
                    output.add(new String[] {"polarity","",category});
                    output.add(new String[] {category,"",""});
                }
                else
                {
                    output.add(new String[] {"firstPerson","polarity","",category});
                    output.add(new String[] {"","polarity","firstPerson",category});
                    output.add(new String[] {"firstPerson",category,"",""});
                    output.add(new String[] {category,"polarity","",""});
                }
                
                break;
        }
        for(String temp[] : output)
        {
            for(String tempp : temp)
            {
                System.out.println("getPossibleSequence OUTPUT==>"+tempp);
            }
        }
        return output;
    }
    
    public String[] getPhraseSequenceAccToSentencePattern(String input, boolean isImperativeMood)    
    {
        switch(input)
        {
            case "INTRANSITIVE_PATTERN":
                if(isImperativeMood)
                    return new String[] {"MAIN_VERB"};
                else
                    return new String[] {"SUBJECT","MAIN_VERB"};
            case "SIMPLE_TRANSITIVE_PATTERN":
                if(isImperativeMood)
                    return new String[] {"MAIN_VERB","DIRECT_OBJECT"};
                else 
                    return new String[] {"SUBJECT","MAIN_VERB","DIRECT_OBJECT"};
            case "SUBJECT_COMPLEMENT":
                if(isImperativeMood)
                    return new String[] {"MAIN_VERB","SUBJECT_COMPLEMENT"};
                else
                    return new String[] {"SUBJECT","MAIN_VERB","SUBJECT_COMPLEMENT"};
            case "OBJECT_COMPLEMENT":
                if(isImperativeMood)
                    return new String[] {"MAIN_VERB","DIRECT_OBJECT","OBJECT_COMPLEMENT"};
                else
                    return new String[] {"SUBJECT","MAIN_VERB","DIRECT_OBJECT","OBJECT_COMPLEMENT"};
            case "DOUBLE_OBJECT":
                if(isImperativeMood)
                    return new String[] {"MAIN_VERB","INDIRECT_OBJECT","DIRECT_OBJECT"};
                else
                    return new String[] {"SUBJECT","MAIN_VERB","INDIRECT_OBJECT","DIRECT_OBJECT"};
        }
        return null;
    }        
    
    public String[] getNounPhraseSequenceAccToSentencePattern(String input)    
    {
        switch(input)
        {
            case "INTRANSITIVE_PATTERN":
                return new String[] {"SUBJECT"};
            case "SIMPLE_TRANSITIVE_PATTERN":
                return new String[] {"SUBJECT","DIRECT_OBJECT"};
            case "SUBJECT_COMPLEMENT":
                return new String[] {"SUBJECT","SUBJECT_COMPLEMENT"};
            case "OBJECT_COMPLEMENT":
                return new String[] {"SUBJECT","DIRECT_OBJECT","OBJECT_COMPLEMENT"};
            case "DOUBLE_OBJECT":
                return new String[] {"SUBJECT","INDIRECT_OBJECT","DIRECT_OBJECT"};
        }
        return null;
    }
    
    public boolean isNounPluralAccToTag(String input)
    {
        switch (input) {
            case "NNS":
            case "NNPS":
                return true;
            default : return false;
        }
    }
    
    
    public boolean canBeNounDependentClause(String input)
    { 
        String rule = "who|whoever|how|why|whom|whether|whose|which|what|that|where|when";
        return se.isValidAccToRule(input,rule);
    }
    
    public boolean canBeAdjectiveDependentClause(String input)
    {
        String rule = "who|which|that|whom|whose";
        return se.isValidAccToRule(input,rule);
    }
    
    public boolean isNotNormalNoun(String input)
    {
        return se.isValidAccToRule(input, "(NP(GP|GS|IP|IS)P)(C)?");
    }
    
    public boolean isALinkingVerb(String input)
    {
        return se.isValidAccToRule(input,"(is|are|were|was|am)");
    }
    public String getNumberAccToTag(String input)
    {
        if(se.isValidAccToRule(input, "(NN(P)S|NP(P)?|DTNPP|NPPOS|NP(PS|PO|P|GP|IP)P)(C)?"))
            return "plural";
        else if(se.isValidAccToRule(input, "(NN(P)|NS(P)?|DTNSP|NSPOS|NP(SS|SO|S|F|Y|GS|IS)P)(C)?"))
            return "singular";
        else
            return "neutral";
    }
    
    public String getCategoryPOS(String pos) {
        switch (pos) {
            case "NN":
            case "NNS":
            case "NNP":
            case "NNPS":
                return "noun";
            case "PRP":
            case "PRPS":
                return "pronoun";
            case "VB":
            case "VBD":
            case "VBN":
            case "VBZ":
            case "VBP":
            case "VBG":
                return "verb";
            case "JJ":
            case "JJR":
            case "JJS":
                return "adjective";
            case "RB":
            case "RBR":
            case "RBS":
                return "adverb";
            case "CC":
                return "conjunction";
            case "DT":
                return "determiner";
            case "IN":
                return "preposition";
            case "WP": 
            case "WP$": 
                return "relative pronoun";
            default:
                return "none";
        }
    }
    
    public boolean isDelexicalVerb(String input)
    {
        input = input.trim();
        return input.equalsIgnoreCase("have") && input.equalsIgnoreCase("take") && 
                input.equalsIgnoreCase("make") && input.equalsIgnoreCase("give") &&
                input.equalsIgnoreCase("go") && input.equalsIgnoreCase("do");
            
    }
    
    
    public String getAuxiliaryGenericSemanticRole(String input)
    {
        switch(input)
        {
            case "have to": 
                return "NECESSITY";
            case "will":
            case "is going to":
            case "are going to":
            case "was going to":
            case "were going to":
            case "am going to":
            case "shall":
                return "FUTURE";
            case "would":
                return "CONTRARY_TO_FACT";
            case "used to":
                return "PAST_CUSTOM";
            case "had better":
                return "DESIRABILITY";
            case "had rather":
                return "PREFERENCE";
            case "could":
                return "PAST_ABILITY";
            case "can":
            case "may":
            case "might":
                return "POSSIBILITY";
            case "ought to":
            case "should":
                return "OBLIGATION";
            case "must":
                return "NECESSITY";
        }
        return "";
    }
    
    
    public String getConnectorGenericSemanticRole(String input)
    {
        switch(input.trim())
        {
            case "so that":
            case "so":
                return "PURPOSE";
            case "because":
            case "since":
            case "as":
                return "REASON";
            case "if":
            case "unless":
            case "as long as":
            case "provided":
            case "provided that":
                return "CONDITIONAL";
            case "while":
            case "when":
        //    case "as": 
        //    case "since": 
            case "before": 
            case "after":
            case "until":
            case "whenever":
            case "as soon as":
            case "by the time":
                return "TIME";
            case "where":
            case "wherever":
            case "everywhere":
                return "PLACE";
            case "moreover":
            case "furthermore":
            case "in addition":
            case "likewise":
            case "also":
            case "besides":
            case "then":
            case "in fact":
            case "as a matter of fact":
                return "ADDITIONAL_IDEA";
            case "however":
            case "nevertheless":
            case "on the contrary":
            case "instead":
            case "still":
                return "CONTRAST";
            case "else":
            case "otherwise":
            case "on the other hand":
            case "in the meantime":
                return "ALTERNATION";
            case "indeed":
            case "certainly":
            case "definitely":
                return "AMPLIFICATION";
            case "therefore":
            case "hence":
            case "as a result":
            case "accordingly":
            case "consequently":
            case "thus":
                return "INFERENCE";
        }
        return "";
    }
    
    public boolean isPossessivePronoun(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("mine")||token.contentEquals("yours")||token.contentEquals("his")
                ||token.contentEquals("hers")||token.contentEquals("ours")||token.contentEquals("theirs"));
    }
    
    public boolean isAdjectivePronoun(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("my")||token.contentEquals("your")||token.contentEquals("his")
                ||token.contentEquals("her")||token.contentEquals("our")||token.contentEquals("their")
                ||token.contentEquals("its"));
    }
    
    public boolean isFirstPerson(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("i")||token.contentEquals("we")||token.contentEquals("me")
                ||token.contentEquals("us")||token.contentEquals("mine")||token.contentEquals("ours")||
                token.contentEquals("my")||token.contentEquals("our"));
    }
    
    public boolean isSecondPerson(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("you")||token.contentEquals("your")||token.contentEquals("yours"));
    }
    
    public boolean isThirdPerson(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("it")||token.contentEquals("she")||token.contentEquals("he")
                ||token.contentEquals("her")||token.contentEquals("his")||token.contentEquals("hers")
                ||token.contentEquals("they")||token.contentEquals("him")||token.contentEquals("them")
                ||token.contentEquals("theirs")||token.contentEquals("its")||token.contentEquals("their"));
    }
    
    public boolean isPronounPlural(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("they")||token.contentEquals("him")||token.contentEquals("them")
                ||token.contentEquals("theirs")||token.contentEquals("their"));
    }
    
    public boolean isPronounNonPerson(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("it")||token.contentEquals("its"));
    }
    
    public boolean isPronounFemale(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("she")||token.contentEquals("her")||token.contentEquals("hers"));
    }
    
    public boolean isPronounMale(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("he")||token.contentEquals("his")||token.contentEquals("him"));
    }
    
    public boolean isThirdPersonPlural(String token)
    {
        token = token.trim();
        
        return (token.contentEquals("they")||token.contentEquals("them")
                ||token.contentEquals("theirs")||token.contentEquals("their"));
    }
    
    public boolean isFirstPersonAdjective(String tag, String token)
    {
        return (tag.contentEquals("PRPS"))&&(token.contentEquals("my")||token.contentEquals("our"));
    }
    
    public LinkedList<String> getAllNounLexnames()
    {
        LinkedList<String> result = new LinkedList();
        String input[] = ("noun.act,noun.animal,noun.artifact,"
                + "noun.attribute,noun.body,noun.cognition,noun.communication,"
                + "noun.event,noun.feeling,noun.food,noun.group,"
                + "noun.location,noun.motive,noun.object,noun.person,"
                + "noun.phenomenon,noun.plant,noun.possession,"
                + "noun.process,noun.quantity,noun.relation,noun.shape,"
                + "noun.state,noun.substance,noun.time").split(",");
        result.addAll(Arrays.asList(input));
        return result;
    }
    
    public LinkedList<String> getAllRelativeClauseLexnames(String input)
    {
        LinkedList<String> result = new LinkedList();
        String temp;
        switch(input)
        {
            case "WHO_CLAUSE":
                temp = "noun.person";
                break;
            case "WHERE_CLAUSE":
                temp = "noun.location";
                break;
            case "WHEN_CLAUSE":
                temp = "noun.phenomenon,noun.event,noun.time";
                break;
            case "WHICH_CLAUSE":
                temp = "noun.act,noun.animal,noun.artifact,"
                + "noun.attribute,noun.body,noun.cognition,noun.communication,"
                + "noun.event,noun.feeling,noun.food,noun.group,"
                + "noun.location,noun.motive,noun.object,"
                + "noun.phenomenon,noun.plant,noun.possession,"
                + "noun.process,noun.quantity,noun.relation,noun.shape,"
                + "noun.state,noun.substance,noun.time";
                break;
            default : 
                temp = "noun.act,noun.animal,noun.artifact,"
                + "noun.attribute,noun.body,noun.cognition,noun.communication,"
                + "noun.event,noun.feeling,noun.food,noun.group,"
                + "noun.location,noun.motive,noun.object,noun.person,"
                + "noun.phenomenon,noun.plant,noun.possession,"
                + "noun.process,noun.quantity,noun.relation,noun.shape,"
                + "noun.state,noun.substance,noun.time";
                break;
        }
        result.addAll(Arrays.asList(temp.split(",")));
        return result;
    }
    
}
