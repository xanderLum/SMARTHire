/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Interfaces;

/**
 *
 * @author Nathalia
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public interface RegexRules {
    
    
    public final String BIG_LETTER = "[A-Z]";
    public final String SMALL_LETTER = "[a-z]";
    public final String EXTENSION = "(_("+BIG_LETTER+")+)";
    
    public final String TOKEN = "([a-z]|\\d|\\Q'\\E|\\Q$\\E|\\Q.\\E|\\Q?\\E|\\Q!\\E|\\Q;\\E|\\Q,\\E)+";
    public final String TAG = "(([A-Z]|(\\Q$\\E))+)";
    public final String INDEX = "(\\Q[\\E)(\\d+)(\\Q]\\E)";
    
    //==============================NEW PHRASE RULES==========================================================
    
    public final String COMPOUND = "(<(("+TAG+")("+ EXTENSION +")?"+ INDEX +")><SYM:(\\Q,\\E)>)*<(("+TAG+")("+ EXTENSION +")?"+ INDEX +")>(<SYM:(\\Q,\\E)>)?(<CC:(and|or|nor||but|so|then|yet|for)>)(<("+TAG+")("+ EXTENSION +")?"+ INDEX +">)";
    
    //-----------------------------------ADVERB_PHRASE_CHUNK_RULES--------------------------------------------
    public final String ADV = "(<(RB(R|S)?):(" + TOKEN + ")>)+";
    //--------------------------------------------------------------------------------------------------------
    
    //-----------------------------------VERB_PHRASE_CHUNK_RULES----------------------------------------------

    public final String VBZ = "(<(VBZ):(" + TOKEN + ")>)";
    public final String VBP = "(<(VBP):(" + TOKEN + ")>)";
    public final String VB = "(<(VB):(" + TOKEN + ")>)";
    public final String VBD = "(<(VBD):(" + TOKEN + ")>)";
    
    public final String VBG = "(<(VBG):(" + TOKEN + ")>)";
    public final String VBN = "(<(VBN):(" + TOKEN + ")>)";

    public final String INF = "(<ADV_NOT"+ INDEX +">)?(<TO:to>)(<VB:be>)?(<VB(P)?("+ EXTENSION +")?(" + INDEX + "|:"+TOKEN+")>)";
    public final String NPGS = "((<PRPS:"+ TOKEN +">)?(<ADJ(S)?(C)?("+ EXTENSION +")?"+ INDEX +">)?(<NS("+ EXTENSION +")?"+ INDEX +">)*<VBG("+ EXTENSION +")?"+ INDEX +">)";

    //======================================IMPORTANT============================================ !!!!
    public final String VPR = "(<(ADV)("+ EXTENSION +")?"+ INDEX +">)(<(VB(G|N|Z|P|D)?(C)?)"+ INDEX +">)";
    //VB(G|N|Z|P|D)?PR
    // for adverbs ==> R

    public final String VBEPR = "(<(VB(Z|P|N|D)?):(is|are|am|were|was|be|been)>)(<(ADV)("+ EXTENSION +")?"+ INDEX +">)";
    //VBE(S|P|AM|WE|WA|B|D|N)PR
    //for adverbs ==> R
    
    public final String VBEGP = "(<((VB(Z|P|N|D)?):(is|are|am|were|was|be|been)|(VBE(S|P|AM|WE|WA|B|D)?PR)"+ INDEX +")>)(<(ADV)("+ EXTENSION +")?"+ INDEX +">)?(<(VBG(PR)?(C)?"+ INDEX +"|VBG:"+TOKEN+")>)";
    //VBE(S|P|AM|WE|WA|B|D|N)GP
    
    public final String VBENP = "(<((VB(Z|P|N|D)?):(is|are|am|were|was|be|been)|(VBE(S|P|AM|WE|WA|B|D)?PR)"+ INDEX +")>)(<(ADV)("+ EXTENSION +")?"+ INDEX +">)?(<(VBN(PR)?(C)?"+ INDEX +"|VBN:"+TOKEN+")>)";
    //VBE(S|P|AM|WE|WA|B|D)NP
    
    public final String VHP = "(<(VB(Z|P|N|D)?):(has|have|had)>)(<(ADV)("+ EXTENSION +")?"+ INDEX +">)?(<VBD:had>)?(<((VB(N|D)(PR)?(C)?|VBEN(G|N)P)"+ INDEX +"|VBD:"+TOKEN+")>)";
    //VH(S|B|D)P
    
    public final String VDP = "(<(VB(Z|P|N|D)?):(does|do|did)>)(<(ADV)("+ EXTENSION +")?"+ INDEX +">)?(<((VB(PR)?(C)?)"+ INDEX +"|VB:"+TOKEN+")>)";
    //VD(S|B|D)P
    
    public final String VMDP = "(<MD:"+TOKEN+">)(<ADV("+ EXTENSION +")?"+ INDEX +">)?(<((VB(PR)?(C)?|VHBP|VDBP|VBEB(G|N)P)"+ INDEX +"|VB:"+TOKEN+")>)";
    
    //===================================================================================!!!!

    //--------------------------------------------------------------------------------------------------------
    
    //----------------------------------ADJECTIVE_PHRASE_CHUNK_RULES------------------------------------------
    
    //RB+DT?(JJ|JJR|JJS|CD|VBD|PRP\$)*((NN|NNS|NNP|NNPS)POS?)+
    
    public final String CD = "(<(CD):(" + TOKEN + ")>)";
    
    public final String CDP = "(<CD"+INDEX+">)((<SYM:(,|.)>)(<CD"+INDEX+">))*";
    
    public final String ADJ = "(<ADV(P)?("+ EXTENSION +")?"+ INDEX +">)*(<(JJ(R|S)?|CD):" + TOKEN + ">)+";
    
    //--------------------------------------------------------------------------------------------------------
    
    //----------------------------------NOUN_PHRASE_CHUNK_RULES-----------------------------------------------
    
    public final String NN="(<NN:(" + TOKEN + ")>)*<NN:(" + TOKEN + ")>";
    public final String NNS="(<NN:(" + TOKEN + ")>)*<NNS:(" + TOKEN + ")>";
    public final String NNP="(<NNP:(" + TOKEN + ")>)*<NNP:(" + TOKEN + ")>";
    public final String NNPS="(<NNP:(" + TOKEN + ")>)*<NNPS:(" + TOKEN + ")>";
    
    
    
    //========================================START
      
    public final String NPSSP = "<PRP:(he|she)>";
    public final String NPSOP = "<PRP:(me|him|her)>";
    public final String NPPSP = "<PRP:(we|they)>";
    public final String NPPOP = "<PRP:(us|them)>";
    public final String NPFP = "<PRP:i>";
    public final String NPYP = "<PRP:(you|thee)>";
    
    public final String PRP = "(<(PRP):("+TOKEN+")>)";
    
    public final String ADJNP = "(<(ADJ(S)?(C)?)"+ INDEX +">)?(<(NN(P)?(S)?(C)?)"+ INDEX +">)";
    
    public final String DTNP = "(<(PDT):("+TOKEN+")>)?(<(DT):("+TOKEN+")>)?(<(PRPS):("+TOKEN+")>)?(<(N(S|P)(P)?(C)?)("+ EXTENSION +")?"+ INDEX +">)";
   
    public final String POS = "((<(DTNSP|NS(P)?)(C)?("+ EXTENSION +")?"+ INDEX +"><POS:'s>)|(<(DTNPP|NP(P)?)(C)?("+ EXTENSION +")?"+ INDEX +"><POS:'>))";
    
    public final String NSPOS = "<POS("+ EXTENSION +")?"+ INDEX +"><NS(P)?(C)?("+ EXTENSION +")?"+ INDEX +">";
    public final String NPPOS = "<POS("+ EXTENSION +")?"+ INDEX +"><NP(P)?(C)?("+ EXTENSION +")?"+ INDEX +">";
//---    
    public final String NPPP = "<(DTNPP|NPPOS|NP(P)?)(C)?("+ EXTENSION +")?"+ INDEX +">";
    public final String NPSP = "<(DTNSP|NSPOS|NS(P)?)(C)?("+ EXTENSION +")?"+ INDEX +">";
//---  

    public final String NPGSP = "(<(NPGS)("+ INDEX +")>)(<(NP(S|P)P|CSUBN(J)?(V)?)("+ INDEX +")>)";
    
    public final String P = "(<(TO|IN):(" + TOKEN + ")>)";
    
    public final String PREPOSITION_PHRASE = "<P("+EXTENSION+")"+ INDEX +"><(NP(GS|GP|IS|IP|SO|PO|S|P|Y|F)P(C)?|CSUBN(J)?(V)?)"+ INDEX +">";
    public final String SPECIAL_PREPOSITION_PHRASE = "<P("+EXTENSION+")"+ INDEX +"><(CWH("+EXTENSION+"))"+ INDEX +">";
    /*?*/   //----------------------------------------------------------------------------------------------------

    public final String NOUN_COMPOUND_PREPOSITION_PHRASE = "(<(NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)P(P)?(C)?|CSUBN(J)?(V)?)"+ INDEX +">)"
            + "(("+PREPOSITION_PHRASE+"<SYM:(\\Q,\\E)>)*"+PREPOSITION_PHRASE+"<CC:and>"+PREPOSITION_PHRASE+")";
  
    public final String NOUN_PREPOSITION_PHRASE = "(<(NP(GS|GP|IS|IP|SO|SS|PO|PS|S|P|Y|F)P(P)?(C)?|CSUBN(J)?(V)?)"+ INDEX +">)("+PREPOSITION_PHRASE+")";
    
    public final String PP = "<P("+EXTENSION+")"+ INDEX +"><(NP(GS|GP|IS|IP|SO|PO|S|P|Y|F)P(C)?|CSUBN(J)?(V)?)"+ INDEX +">";
//---    
    
    //--------------------------------------------------------------------------------------------------------
   
    //(NP((SO|SS|PO|PS|F|GS|IS|S|P|Y)PP|(SSP|SOP|PSP|POP|PP|SP|GSP|ISP|GPP|IPP|FP)))(C)?
    public final String NOUN_PHRASE_PLURAL_SUBJECT_TAG = "(NPPSP|NPPSPP|NPPSPC|NPPSPPC|"
            + "NPPSPRL|NPPSPPRL|NPPSPCRL|NPPSPPCRL|"
            + "NPPSPINF|NPPSPPINF|NPPSPCINF|NPPSPPCINF)";
    public final String NOUN_PHRASE_SINGULAR_SUBJECT_TAG = "(NPSSP|NPSSPP|NPSSPC|NPSSPPC"
            + "|NPSSPRL|NPSSPPRL|NPSSPCRL|NPSSPPCRL|"
            + "NPSSPINF|NPSSPPINF|NPSSPCINF|NPSSPPCINF)";
    public final String NOUN_PHRASE_SUBJECT_TAG = "("+NOUN_PHRASE_PLURAL_SUBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_SUBJECT_TAG+")";
    public final String NOUN_PHRASE_PLURAL_OBJECT_TAG = "(NPPOP|NPPOPP|NPPOPC|NPPOPPC"
            + "|NPPOPRL|NPPOPPRL|NPPOPCRL|NPPOPPCRL|"
            + "NPPOPINF|NPPOPPINF|NPPOPCINF|NPPOPPCINF)";
    public final String NOUN_PHRASE_SINGULAR_OBJECT_TAG = "(NPSOP|NPSOPP|NPSOPC|NPSOPPC"
            + "|NPSOPRL|NPSOPPRL|NPSOPCRL|NPSOPPCRL|"
            + "NPSOPINF|NPSOPPINF|NPSOPCINF|NPSOPPCINF)";
    public final String NOUN_PHRASE_OBJECT_TAG = "("+NOUN_PHRASE_PLURAL_OBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_OBJECT_TAG+")";
    
    public final String NOUN_PHRASE_PLURAL_TAG = "(NPPP|NPGPP|NPIPP|NPGP|NPIP|NPPPP|NPGPPP|NPIPPP|"
            + "NPPPC|NPGPPC|NPIPPC|NPPPPC|NPGPPPC|NPIPPPC"
            + "|NPPPRL|NPPPPRL|NPPPCRL|NPPPPCRL"
            + "||NPPPINF|NPPPPINF|NPPPCINF|NPPPPCINF)";
    public final String NOUN_PHRASE_SINGULAR_TAG = "(NPSP|NPGSP|NPISP|NPGS|NPIS|NPGSPP|NPSPP|NPISPP|"
            + "NPSPC|NPGSPC|NPISPC|NPGSPPC|NPSPPC|NPISPPC|CSUBN|CSUBNJ|CSUBNV|CSUBNJV|PRP"
            + "|NPSPRL|NPSPPRL|NPSPCRL|NPSPPCRL"
            + "||NPSPINF|NPSPPINF|NPSPCINF|NPSPPCINF)";
    public final String NOUN_PHRASE_TAG = "("+NOUN_PHRASE_PLURAL_TAG+"|"+NOUN_PHRASE_SINGULAR_TAG+")";
    
    public final String NOUN_PHRASE_I_TAG = "(NPFPP|NPFP|NPFPPRL|NPFPRL|NPFPPINF|NPFPINF)";
    public final String NOUN_PHRASE_YOU_TAG = "(NPYP|NPYPP|NPYPRL|NPYPPRL|NPYPINF|NPYPPINF)";
    
    public final String NOUN_PHRASE_ALL_TAG= "("+NOUN_PHRASE_PLURAL_SUBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_SUBJECT_TAG
            +"|"+NOUN_PHRASE_PLURAL_OBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_OBJECT_TAG
            +"|"+ NOUN_PHRASE_PLURAL_TAG+"|"+NOUN_PHRASE_SINGULAR_TAG
            +"|"+NOUN_PHRASE_I_TAG+"|"+NOUN_PHRASE_YOU_TAG+")";
    
    public final String VERB_PHRASE_SINGULAR_TAG = "(VBESGP|VBESNP|VHSP|VHSPR|VDSP|VDSPR|VBZPR|VBEZPR|VBZC|VBZ)";
    public final String VERB_PHRASE_BASE_TAG = "(VBPR|VBEPR|VBPPR|VBEPPR|VB|VBC|VBP|VBPC)";
    public final String VERB_PHRASE_PLURAL_TAG = "(VBEPGP|VBEPNP|VHBP|VHBPR|VDBP|VDBPR)";
    public final String VERB_PHRASE_NEUTRAL_TAG = "(VBENGP|VBENNP|VMDP|VHDP|VHDPR|VDDP|VDDPR|VBD|VBDPR)";
    public final String VERB_PHRASE_WAS_TAG = "(VBEWAGP|VBEWANP)";
    public final String VERB_PHRASE_WERE_TAG = "(VBEWEGP|VBEWENP)";
    public final String VERB_PHRASE_AM_TAG = "(VBEAMGP|VBEAMNP)";
    public final String VERB_PHRASE_TAG = "("+VERB_PHRASE_SINGULAR_TAG+"|"
            +VERB_PHRASE_PLURAL_TAG+"|"+VERB_PHRASE_NEUTRAL_TAG+"|"+
            VERB_PHRASE_AM_TAG+"|"+VERB_PHRASE_WAS_TAG+"|"+VERB_PHRASE_WERE_TAG+"|"+VERB_PHRASE_BASE_TAG+")";
    
    public final String PREDICATE_OBJECT = "(((<("+NOUN_PHRASE_ALL_TAG+")("+ EXTENSION +")?"+ INDEX +">)(<RP:"+TOKEN+">)?(<("+NOUN_PHRASE_ALL_TAG+"|ADJ(S)?(C)?)("+ EXTENSION +")?"+ INDEX +">)?)|(<ADJ(S)?(C)?"+ INDEX +">))?(<PP"+ INDEX +">)?";
    
    //--------------------------------------------------------------------------------------------------------
    //-----------------------------PARTICIPIAL_PHRASE_CHUNK_RULES---------------------------------------------
    
    public final String PARTICIPIAL_PHRASE = "(<((VBN|NPG)(C)?)"+ INDEX +">)"+ PREDICATE_OBJECT;
    
    public final String NPSPAPNP = "(<"+NOUN_PHRASE_SINGULAR_TAG+ INDEX +">"+PARTICIPIAL_PHRASE+")"
            + "|("+PARTICIPIAL_PHRASE+"<SYM:(\\Q,\\E)><"+NOUN_PHRASE_SINGULAR_TAG+ INDEX +">)";
    public final String NPPPAPNP = "(<"+NOUN_PHRASE_PLURAL_TAG+ INDEX +">"+PARTICIPIAL_PHRASE+")"
            + "|("+PARTICIPIAL_PHRASE+"<SYM:(\\Q,\\E)><"+NOUN_PHRASE_PLURAL_TAG+ INDEX +">)";
    public final String NPSSPAPNP = "(<"+NOUN_PHRASE_SINGULAR_SUBJECT_TAG+ INDEX +">"+PARTICIPIAL_PHRASE+")"
            + "|("+PARTICIPIAL_PHRASE+"<SYM:(\\Q,\\E)><"+NOUN_PHRASE_SINGULAR_SUBJECT_TAG+ INDEX +">)";
    public final String NPPSPAPNP = "(<"+NOUN_PHRASE_PLURAL_SUBJECT_TAG+ INDEX +">"+PARTICIPIAL_PHRASE+")"
            + "|("+PARTICIPIAL_PHRASE+"<SYM:(\\Q,\\E)><"+NOUN_PHRASE_PLURAL_SUBJECT_TAG+ INDEX +">)";
    public final String NPSOPAPNP = "(<"+NOUN_PHRASE_SINGULAR_OBJECT_TAG+ INDEX +">"+PARTICIPIAL_PHRASE+")"
            + "|("+PARTICIPIAL_PHRASE+"<SYM:(\\Q,\\E)><"+NOUN_PHRASE_SINGULAR_OBJECT_TAG+ INDEX +">)";
    public final String NPPOPAPNP = "(<"+NOUN_PHRASE_PLURAL_OBJECT_TAG+ INDEX +">"+PARTICIPIAL_PHRASE+")"
            + "|("+PARTICIPIAL_PHRASE+"<SYM:(\\Q,\\E)><"+NOUN_PHRASE_PLURAL_OBJECT_TAG+ INDEX +">)";
    
    //--------------------------------------------------------------------------------------------------------
    //-----------------------------ABSOLUTE_PHRASE_CHUNK_RULES------------------------------------------------
    
    public final String ABSOLUTE_PHRASE = "<("+NOUN_PHRASE_PLURAL_SUBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_SUBJECT_TAG+"|"+NOUN_PHRASE_PLURAL_TAG
            +"|"+NOUN_PHRASE_PLURAL_TAG+"|"+NOUN_PHRASE_I_TAG+"|"+NOUN_PHRASE_YOU_TAG+")"+ INDEX +">"+PARTICIPIAL_PHRASE;
    //--------------------------------------------------------------------------------------------------------    
   
    public final String PREDICATE_VERB_OBJECT = "((<"+ VERB_PHRASE_TAG +"("+ INDEX +"|:"+TOKEN+")>)(<RP:"+TOKEN+">)?(" + PREDICATE_OBJECT + "))";
    
    
    public final String PREDICATE_PHRASE = "<(V(BE|WE|WA|AM|S|P|B|N)O(S|P|N)?P(C)?)"+ INDEX +">";

    public final String CLAUSE_SUBJECT_VERB_OBJECT ="(<(ADV)("+ EXTENSION +")?"+ INDEX +">)?(<"+ NOUN_PHRASE_ALL_TAG +"("+EXTENSION+")?"+ INDEX +">)?("+ PREDICATE_PHRASE +")(<(ADV)("+ EXTENSION +")?"+ INDEX +">)?";
    
//---    
    public final String SUB_CLAUSE = "(<(W(DT|PS|P|RB)|P)("+EXTENSION+")?(:("+ TOKEN +")|"+INDEX+")>)("+CLAUSE_SUBJECT_VERB_OBJECT+")";
    //CSUBNJV
    public final String INF_CLAUSE = "(<("+NOUN_PHRASE_ALL_TAG+")"+INDEX+">)?(<(INF)"+ INDEX +">)("+PREDICATE_OBJECT+")?";
    //CINF
//--------    
    public final String NP_RELATIVE_ADJECTIVE = "(<("+NOUN_PHRASE_ALL_TAG+"("+EXTENSION+")?)"+ INDEX +">)(<(CSUB(N)?J(V)?)"+ INDEX +">)";
    //(np..)RL
//--------    
    public final String CLAUSE_QUESTION_SUBJECT_VERB_OBJECT = "(((<"+TAG+":(who|whose|when|where)>)?|((<"+TAG+":(what|which)>)(<("+ NOUN_PHRASE_SINGULAR_TAG +"|"+ NOUN_PHRASE_SINGULAR_SUBJECT_TAG +")("+EXTENSION+")?"+ INDEX +">))?)"
        + "(((<(MD|(VB(Z|P|N|D)?)):("+ TOKEN +")>)(<("+ NOUN_PHRASE_SINGULAR_TAG +"|"+ NOUN_PHRASE_SINGULAR_SUBJECT_TAG +")("+EXTENSION+")?"+ INDEX +">))"
        + "|((<(MD|(VB(Z|P|N|D)?)):("+ TOKEN +")>)(<("+ NOUN_PHRASE_PLURAL_TAG +"|"+ NOUN_PHRASE_PLURAL_SUBJECT_TAG +")("+EXTENSION+")?"+ INDEX +">)))"
        + "(<(V(L)?(P|B)O(S|N)?P(C)?)"+ INDEX +">))";
    
    public final String CLAUSE_DUMMY_SUBJECT_VERB_OBJECT = "(<(EX:there|PRP_I"+INDEX+")>((<VLSPPCC"+ INDEX +">)|(<VLPPPCC"+ INDEX +">)))";
    
    public final String CLAUSE_SUBJECT_VERB_OBJECT_INDICATIVE_MOOD ="((<("+ NOUN_PHRASE_SINGULAR_TAG +"|"+ NOUN_PHRASE_SINGULAR_SUBJECT_TAG +")("+EXTENSION+")?"+ INDEX +">)<(V(L)?(WA|S|N)O(S|N)?P(C)?)"+ INDEX +">)"
        + "|((<("+ NOUN_PHRASE_PLURAL_TAG +"|"+ NOUN_PHRASE_PLURAL_SUBJECT_TAG +")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(P|N)O(P|N)?P(C)?)"+ INDEX +">))"
        + "|((<"+ NOUN_PHRASE_I_TAG +"("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(WA|AM|N|P)O(S|N)?P(C)?)"+ INDEX +">))"
        + "|((<"+NOUN_PHRASE_YOU_TAG+"("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(P|N)O(S|N)?P(C)?)"+ INDEX +">))";

    public final String CLAUSE_SINGULAR_SUBJECT_WERE_VERB_OBJECT = 
            "(<("+NOUN_PHRASE_I_TAG+"|"+NOUN_PHRASE_SINGULAR_SUBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_TAG+")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(WE)O(S|N)?P(C)?)"+ INDEX +">)";
    
    public final String CLAUSE_PLURAL_SUBJECT_WERE_VERB_OBJECT = 
            "((<("+NOUN_PHRASE_PLURAL_SUBJECT_TAG+"|"+NOUN_PHRASE_PLURAL_TAG+")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(WE)O(P|N)?P(C)?)"+ INDEX +">)"
            + "|(<("+NOUN_PHRASE_YOU_TAG+")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(WE)O(S|N)?P(C)?)"+ INDEX +">))";
    
    public final String CLAUSE_SINGULAR_SUBJECT_BASE_VERB_OBJECT = "(<("+NOUN_PHRASE_SINGULAR_SUBJECT_TAG+"|"+NOUN_PHRASE_SINGULAR_TAG+"|"+NOUN_PHRASE_OBJECT_TAG+")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(B)O(S|N)?P(C)?)"+ INDEX +">)";
    
    public final String CLAUSE_PLURAL_SUBJECT_BASE_VERB_OBJECT = "((<("+NOUN_PHRASE_PLURAL_SUBJECT_TAG+"|"+NOUN_PHRASE_PLURAL_TAG+")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(B)O(P|N)?P(C)?)"+ INDEX +">)"
            + "|((<("+NOUN_PHRASE_I_TAG+"|"+NOUN_PHRASE_YOU_TAG+")("+EXTENSION+")?"+ INDEX +">)(<(V(L)?(B)O(S|N)?P(C)?)"+ INDEX +">)))";

    public final String CLAUSE_VERB_OBJECT_IMPERATIVE_MOOD = "(<(VBOP(C)?|(VLBEO(S|N)?P(C)?))"+ INDEX +">)";
    //
    public final String REG_CLAUSE_TAG = "(CSVOINM|CSSWEVO|CPSWEVO|CSSBVO|CPSBVO|CVOIMM|CSVO)";
    public final String SUB_CLAUSE_TAG = "(CSUB(N)?(J)?V)";
    
    public final String SENTENCE_QUOTE_CLAUSE_CLAUSE = "<SYM:``>(<("+REG_CLAUSE_TAG+"(C)?)"+ INDEX + ">)<SYM:,><SYM:''>(<("+REG_CLAUSE_TAG+"(C)?|S)"+ INDEX + ">)";
    public final String SENTENCE_CLAUSE_QUOTE_CLAUSE = "(<("+REG_CLAUSE_TAG+"(C)?|S)"+ INDEX + ">)<SYM:,><SYM:``>(<("+REG_CLAUSE_TAG+"(C)?|S)"+ INDEX + ">)<SYM:''>";
            
    public final String SENTENCE_SUB_CONNECTOR_CLAUSE_CLAUSE = "(<("+SUB_CLAUSE_TAG+"(C)?)"+ INDEX + ">)(<SYM:,>)(<("+REG_CLAUSE_TAG+"(C)?|S)"+ INDEX + ">)";
    public final String SENTENCE_CLAUSE_SUB_CONNECTOR_CLAUSE = "(<("+REG_CLAUSE_TAG+"(C)?|S)"+ INDEX + ">)(<("+SUB_CLAUSE_TAG+"(C)?)"+ INDEX + ">)";
    public final String SENTENCE_COR_CONNECTOR_CLAUSE_CLAUSE = "(<("+REG_CLAUSE_TAG+"(C)?)"+ INDEX +">)(<SYM:(;)>)(<(CCP|ADV)("+EXTENSION+")?"+ INDEX +">)(<SYM:(,)>)(<("+REG_CLAUSE_TAG+"(C)?)"+ INDEX +">)";
    public final String SENTENCE_COR_CONNECTOR_CLAUSE = "(<((CC):"+TOKEN+"|(CCP|ADV)"+ INDEX +")>)(<SYM:(,)>)(<("+REG_CLAUSE_TAG+"(C)?)"+ INDEX +">)";
    public final String SENTENCE_CLAUSE_CLAUSE = "(<("+REG_CLAUSE_TAG+"(C)?)"+ INDEX +">)(<("+REG_CLAUSE_TAG+"(C)?|S)"+ INDEX + ">)";
    public final String SENTENCE_CLAUSE = "<("+REG_CLAUSE_TAG+"(C)?)"+ INDEX +">";
    public final String SENTENCE = "(<(S|"+SUB_CLAUSE_TAG+")"+ INDEX +">)(<SYM:((\\Q.\\E)|(\\Q!\\E)|(\\Q?\\E)|(\\Q;\\E))>)";
    
    //====================================================================================================
    //==========================================PATTERN===================================================
    
    public final String NO_OBJECT = "(<("+NOUN_PHRASE_ALL_TAG+"("+EXTENSION+")?)"+ INDEX +">)?(<("+ VERB_PHRASE_TAG +")("+ INDEX +"|:"+TOKEN+")>)(<PP"+INDEX+">)?";
    public final String ONE_OBJECT = "(<("+NOUN_PHRASE_ALL_TAG+"("+EXTENSION+")?)"+ INDEX +">)?(<("+ VERB_PHRASE_TAG +")("+ INDEX +"|:"+TOKEN+")>)(<RP:"+TOKEN+">)?(<(("+NOUN_PHRASE_ALL_TAG+"|ADJ(C)?)("+ EXTENSION +")?)"+ INDEX +">)(<RP:"+TOKEN+">)?(<PP"+INDEX+">)?";
    public final String DOUBLE_OBJECT = "(<("+NOUN_PHRASE_ALL_TAG+"("+EXTENSION+")?)"+ INDEX +">)?(<("+ VERB_PHRASE_TAG +")("+ INDEX +"|:"+TOKEN+")>)(<RP:"+TOKEN+">)?(<("+NOUN_PHRASE_ALL_TAG+")("+EXTENSION+")?"+ INDEX +">)(<RP:"+TOKEN+">)?(<(("+NOUN_PHRASE_ALL_TAG+"|ADJ(C)?)("+ EXTENSION +")?)"+ INDEX +">)(<PP"+INDEX+">)?";
    
   //========================================================================================= 
    
    public final String PUNCTUATION_MARKS = "<SYM:((\\Q.\\E)|(\\Q?\\E)|(\\Q!\\E)|(\\Q;\\E)|(\\Q,\\E))>";
    //================================================================================================    
    //==========================================TENSE=================================================
    public final String PRESENT_TENSE = "<(VBZ|VB(P)?)(C)?("+ INDEX +"|:"+TOKEN+")>";
    public final String PAST_TENSE = "<(VBD(C)?)("+ INDEX +"|:"+TOKEN+")>";
    public final String FUTURE_TENSE = "<MD:(will|shall)>";
    //================================================================================================ 
    //==========================================ASPECT================================================
   
    public final String PROGRESSIVE_ASPECT = "<VB(Z|P|D)?:(is|are|were|was|be|am)><VBG(C)?"+ INDEX +">";
    public final String PERFECT_ASPECT = "<VB(D|Z|P)?:(have|had|has)><VBN(C)?"+ INDEX +">";
    public final String PERFECT_PROGRESSIVE_ASPECT = "<VB(D|Z|P)?:(have|had|has)><VBN:been><VBG(C)?"+ INDEX +">";
    //================================================================================================    
    //==========================================VOICE=================================================
    
    public final String PASSIVE_VOICE = "<(VB(Z|P|D)?):(be|been|being|is|are|was|were|am)><VBN(C)?"+ INDEX +">";
    
    //=================================================================================================
    //=========================================TAG_REFINER=============================================
    
    public final String WONT_TO_WOULD_NOT = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((MD)):((wo))>)(<((RB)):((n't))>)";
    public final String INCRT_VBG_TO_VBG = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((VBG)):(([a-z])+in)>)(<((SYM)):(('))>)";
    public final String GONNA_TO_GOING_TO = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((VBG)):((gon))>)(<((TO)):((na))>)";
    public final String CANT_TO_CANNOT = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((MD)):((ca))>)(<((RB)):((n't))>)";
//    public final String AINT_TO_CRTV = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((VB|VBP|VBZ|VBD)):((ai))>)(<((RB)):((n't))>)";
    
    public final String ADJ_TO_NN = "(<((DT|PRPS)):("+TOKEN+")>)(<((JJ|JJR|JJS)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
    public final String V_TO_JJ_1 = "(<((DT|JJ|JJR|JJS|RB|RBR|RBS)):("+TOKEN+")>)(<((VBG|VBD)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
/*?*/  //  public final String V_TO_JJ_2 = "(<((VB|VBP|VBZ|VBD)):((be|being|been|is|are|were|was|am))>)(<((VBG|VBD|VBN)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
    

    public final String V_TO_NN = "(<((DT)):("+TOKEN+")>)(<((VB|VBP|VBZ)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
    public final String ADV_TO_VB = "(<((PRP|PRPS)):("+TOKEN+")>)(<((RB|RBR|RBS)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
    
    public final String IN_TO_DT = "(<((IN)):("+TOKEN+")>)(<((IN)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
    public final String ADV_TO_JJ = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((RB)):("+TOKEN+")>)(<((NN)):("+TOKEN+")>)";
    public final String POS_TO_VBZ = "(<((PRP)):("+TOKEN+")>)(<((POS)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
    public final String DT_TO_PDT = "(<(("+BIG_LETTER+")+):("+TOKEN+")>)?(<((DT)):("+TOKEN+")>)(<((DT)):("+TOKEN+")>)";
    public final String NN_TO_VBP = "(<((PRP)):("+TOKEN+")>)(<((NN)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";

    
    
/*??*/    public final String TO_PARALLEL = "(<("+TAG+"):("+TOKEN+")>)((<"+TAG+":("+TOKEN+")><SYM:(\\Q,\\E)>)*<"+TAG+":("+TOKEN+")><CC:and><"+TAG+":("+TOKEN+")>)(<("+TAG+"):("+TOKEN+")>)";
/*??*/    public final String NN_TO_VB = "(<((TO|MD)):("+TOKEN+")>)(<((NN)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";
/*??*/    public final String VB_TO_VBP = "(<((PRP|NNS)):("+TOKEN+")>)(<((VB)):("+TOKEN+")>)(<(("+BIG_LETTER+")+):("+TOKEN+")>)?";

    
    //public final String VBD_TO_VBN = "<VBD><VBD><VBD>";
    //public final String VBN_TO_VBD = "<PRP|NNS><VBN>";

    //==================================================================================================

}

