/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.xander.classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Zander Lumapac
 */
public class GrammarChecker {

    String d = "./src/com/smarthire/zander/dictionary/dictionary.txt";
    String pattern = "";
    List<String> from;
    List<String> content;
    List<String> state;
    List<String> printf;
    List<String> thePattern;
    String notFound = null;

    public GrammarChecker() {
        from = new LinkedList<>();
        content = new LinkedList<>();
        state = new LinkedList<>();
        printf = new LinkedList<>();
        thePattern = new LinkedList<>();
    }

    public boolean isCorrect() {
        String q = "";
        String sub = "";
        String verb = "";
        int indexOfS = 0;
        int indexOfV = 0;
        for (String s : thePattern) {
            if (isASubject(s)) {
                sub = s;
                indexOfS = thePattern.indexOf(s);
                break;
            }
        }
        for (String s : thePattern) {
            if (isAVerb(s)) {
                verb = s;
                indexOfV = thePattern.indexOf(s);
                break;
            }
        }

        if (indexOfV < indexOfS) {
            sub = "";
        }
        
        //print subject and verb
        
//         if(sub.isEmpty()){
//             System.out.println("\nsubject is Understood you.");
//         }else{
//             System.out.println("\nsubject is "+sub);
//             q ="";
//         }
//         System.out.println("verb is "+verb);

        if (!verb.isEmpty()) {
            if (sub.equalsIgnoreCase("PP") && (verb.equalsIgnoreCase("VB") || verb.equals("VBD"))) {
                return true;
            } else if (sub.equals("PPS") && (verb.equalsIgnoreCase("VBZ") || verb.equals("VBD"))) {
                return true;
            } else if (sub.equalsIgnoreCase("NN") && (verb.equalsIgnoreCase("VBZ") || verb.equalsIgnoreCase("VBD"))) {
                return true;
            } else if (sub.equals("") && (verb.equalsIgnoreCase("VB") || verb.equalsIgnoreCase("VBP"))) {
                return true;
            } else if (sub.equalsIgnoreCase("NNS") && verb.equalsIgnoreCase("VBP")) {
                return true;
            } else if(sub.equalsIgnoreCase("PRP") && verb.equalsIgnoreCase("VBP")){
                return true;
            }

        } else {
            return false;
        }

        return false;
    }

    public boolean isASubject(String subject) {
        if (subject.contains("NN") || subject.equalsIgnoreCase("PP") || subject.equals("PPS") || subject.equalsIgnoreCase("PRP")) {
            return true;
        }
        return false;
    }

    public void byOnes() {
        int i = -1;
        for (String z : state) {
            if (z.equals("NN")) {
                state.add(state.indexOf(z), "Nom");
                state.remove(state.indexOf(z));

                break;
            } else if (z.startsWith("V")) {
                state.add(state.indexOf(z), "VB");
                state.remove(state.indexOf(z));

                break;
            } else if (z.equals("PP")) {
                state.add(state.indexOf(z), "Nom");
                state.remove(state.indexOf(z));

                break;
            } else if (z.equals("Nom")) {
                state.add(state.indexOf(z), "NP");
                state.remove(state.indexOf(z));

                break;
            } else if (z.equals("PN")) {
                state.add(state.indexOf(z), "NP");
                state.remove(state.indexOf(z));

                break;
            }
        }
//        for (String s : state) {
//            System.out.print("\t" + s);
//        }
//        System.out.println("");

    }

    public void byPattern() {
        if (state.size() == 3) {
            if (state.get(0).equals("AUX") && state.get(1).equals("NP") && state.get(2).equals("VP")) {
                state.add("S");
            }
        } else if (state.size() == 2) {
            if (state.get(0).equals("NP") && state.get(1).equals("VP")) {
                state.add("S");
            }
        } else if (state.size() == 1) {
            if (state.get(0).equals("VP")) {
                state.add("S");
            }
        }
    }

    public void processPairs() {
        String a = "", b = "s";
        for (String s : state) {
            a = b;
            b = s;
            if (!a.isEmpty() && !b.isEmpty()) {
                if (a.equalsIgnoreCase("dt") && b.equalsIgnoreCase("Nom")) {
                    state.add(state.indexOf(s) - 1, "NP");
                    state.remove(state.indexOf(a));
                    state.remove(s);
                    a = "";
                    b = "";
                    break;
                } else if (a.equals("NN") && b.equals("Nom")) {
                    state.add(state.indexOf(s) - 1, "Nom");
                    state.remove(state.indexOf(a));
                    state.remove(s);
                    a = "";
                    b = "";
                    break;
                } else if (a.equalsIgnoreCase("Nom") && b.equalsIgnoreCase("IN")) {
                    state.add(state.indexOf(s) - 1, "Nom");
                    state.remove(state.indexOf(a));
                    state.remove(s);
                    a = "";
                    b = "";
                    break;
                } else if (isAVerb(a) && b.equalsIgnoreCase("NP")) {
                    state.add(state.indexOf(s) - 1, "VP");
                    state.remove(state.indexOf(a));
                    state.remove(s);
                    a = "";
                    b = "";
                    break;
                }
            }
        }

    }

    // S -> NP VP
    // S -> VP
    // S -> AUX NP VP
    // NP -> Det Nominal
    // Nominal -> Noun
    // Nominal -> Noun Nominal
    // Nominal -> Nominal IN
    // NP - > ProperNoun / PN
    // VP - > Verb
    // VP - > Verb NP
    //PP is prepositional phrase?
    //IN is prep phrase
    //PP is personal pronoun
    public void clear() {
        from.clear();
        content.clear();
        state.clear();
        printf.clear();
        thePattern.clear();
    }

    public boolean isGrammaticallyCorrect(LinkedList<String> tags) {
        clear();
        String a = "";
        String b = "";
        if (revisedBottomUpLR(tags)) {
//            for (String es : state) {
//                System.out.print("\t" + es);
//            }
            state.stream().forEach((z) -> {
                thePattern.add(z);
            });

            //System.out.println("state size = " + state.size());
            boolean allTrue = false;
            boolean hasPaired = false;
            boolean hasOnced = false;
            boolean pdone = false;
            boolean odone = false;
            int size = state.size();
            int c1 = 0;
            int c2 = 0;
           // System.out.println("init size = " + size);
            while (!allTrue) {
                while (!pdone) {
                    processPairs();
                   // System.out.println("state size = " + state.size());
                    if (state.size() != size) {
                        hasPaired = true;
                        size = state.size();
                        c1 = 0;
                    } else {
                        c1++;
                    }
                    pdone = true;

                }
                while (!odone) {
                    byOnes();
                   // System.out.println("state size = " + state.size());
                    if (state.size() != size) {
                        size = state.size();
                        hasOnced = true;
                        c2 = 0;
                    } else {
                        c2++;
                    }
                    odone = true;
                }
                if (c2 >= 2 && c1 >= 2) {
                    allTrue = true;
                    break;
                }
                odone = false;
                pdone = false;
            }

//            for (String z : thePattern) {
//                System.out.print("\t" + z);
//            }

            if (isCorrect()) {
      //          System.out.println("Grammatically correct.\n");
               // JOptionPane.showMessageDialog(null, "Grammatically correct.");
                return true;
            } else {
         //       System.out.println("Grammatically wrong.\n");
               // JOptionPane.showMessageDialog(null, "Grammatically wrong.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can't process this sentence. '" + notFound + "' is not in the dictionary.");
            return false;
        }
      
    }

    private boolean revisedBottomUpLR(LinkedList<String> tags) {
        for (String s : tags) {
            state.add(s);
        }
        for (int x = 0; x < content.size(); x++) {
            pattern = pattern.concat("" + content.get(x) + " \t");
            pattern = pattern.concat("" + state.get(x));
            if (from.get(x).equals("Nom")) {
                pattern = pattern.concat("\tNP");
            }
            if (from.get(x).equals("S")) {
                pattern = pattern.concat("\tAUX");
            }
            if (isAVerb(from.get(x))) {
                pattern = pattern.concat("\tVP");
            }

        }
        return true;

    }

    private boolean bottomUpLR(String sentence) {
        StringTokenizer st;
        Deque<List<String>> theAll = new LinkedList<>();
        List<String> allTypes = new LinkedList<>();
        List<String> typeOfThree = new LinkedList<String>();

        st = new StringTokenizer(sentence);
        String currentString;
        while (st.hasMoreElements()) {
            currentString = st.nextToken();
            String type = getType(currentString);
          //  System.out.println("type = " + type);
            if (type.isEmpty()) {
                notFound = currentString;
                return false;

            } else {
                state.add(type);
            }

        }
        for (int x = 0; x < content.size(); x++) {
            pattern = pattern.concat("" + content.get(x) + " \t");
            pattern = pattern.concat("" + state.get(x));
            if (from.get(x).equals("Nom")) {
                pattern = pattern.concat("\tNP");
            }
            if (from.get(x).equals("S")) {
                pattern = pattern.concat("\tAUX");
            }
            if (isAVerb(from.get(x))) {
                pattern = pattern.concat("\tVP");
            }

        }
        return true;
    }

    public boolean isANominal(String type) {
        if (type.startsWith("N")) {
            return true;
        } else if (type.equals("PP")) {
            return true;
        } else if (type.equals("IN")) {
            return true;
        } else if (type.startsWith("P")) {
            return true;
        } else if (type.startsWith("J")) {
            return true;
        } else if (type.startsWith("R")) {
            return true;
        }
        return false;
    }

    private boolean isAVerb(String type) {
        return type.startsWith("V");
    }

    private String getType(String word) {
        try {
            FileReader access = null;
            access = new FileReader(d);
            StreamTokenizer stream = new StreamTokenizer(access);
            while (stream.nextToken() != StreamTokenizer.TT_EOF) {
                if (stream.sval.equalsIgnoreCase(word)) {
                    stream.nextToken();
                    return (stream.sval);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return "";
    }

    private String getTag(String word) {
        String tag = "";
        return tag;
    }
}
