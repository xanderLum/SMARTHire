/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.CategoryScore;
import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.Model.ClauseData;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.Model.PartPointer;
import com.smarthire.thaliaNew.Model.PhraseData;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.WordInfo;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.smarthire.main.models.Employer;
import com.smarthire.main.models.JobPost;
import com.smarthire.main.models.SocialMediaData;

//import smarthirealgo.DataAdder;

/**
 *
 * @author Nathalia
 */
public class MainProcess {

	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";
	public static final String uriReadBackendProcessStatus = SMART_HIRE_URL + "/backendProcessStatusRC/read/";
	public static final String uriCreateBackendProcessStatus = SMART_HIRE_URL + "/backendProcessStatusRC/create/";
	public static final String uriUpdateBackendProcessStatus = SMART_HIRE_URL + "/backendProcessStatusRC/update/";
	public static final String uriReadAllSentenceData = SMART_HIRE_URL + "/sentenceDataRC/readAll/";
	public static final String uriCreateSentenceData = SMART_HIRE_URL + "/sentenceDataRC/create/";
	public static final String uriReadAllCategoryPoint = SMART_HIRE_URL + "/categoryPointRC/readAll/";
	public static final String uriCreateCategoryPoint = SMART_HIRE_URL + "/categoryPointRC/create/";
	public static final String uriReadAllGlossInfo = SMART_HIRE_URL + "/glossInfoRC/readAll/";
	public static final String uriReadGlossInfo = SMART_HIRE_URL + "/glossInfoRC/readByfkWSID/";
	public static final String uriUpdateGlossInfo = SMART_HIRE_URL + "/glossInfoRC/update/";
	public static final String uriCreateGlossInfo = SMART_HIRE_URL + "/glossInfoRC/create/";
	public static final String uriReadAllGrammarError = SMART_HIRE_URL + "/grammarErrorRC/readAll/";
	public static final String uriCreateGrammarError = SMART_HIRE_URL + "/grammarErrorRC/create/";
	public static final String uriReadAllSynonym = SMART_HIRE_URL + "/synonymRC/readAll/";
	public static final String uriCreateSynonym = SMART_HIRE_URL + "/synonymRC/create/";
	public static final String uriReadWordInfo = SMART_HIRE_URL + "/wordInfoRC/read/";
	public static final String uriReadAllWordInfo = SMART_HIRE_URL + "/wordInfoRC/readAll/";
	public static final String uriReadAllwtWordInfo = SMART_HIRE_URL + "/wordInfoRC/readAllwt/";
	public static final String uriCreateWordInfo = SMART_HIRE_URL + "/wordInfoRC/create/";

	private String username;
	private LinkedList<CHAR_CATEGORYSCORE> llcs;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LinkedList<CHAR_CATEGORYSCORE> getLlcs() {
		return llcs;
	}

	public void setLlcs(LinkedList<CHAR_CATEGORYSCORE> llcs) {
		this.llcs = llcs;
	}

	OutputExcel oe = new OutputExcel();

	KeySearchProcess ksp = new KeySearchProcess();
	DataCleaner dataclean = new DataCleaner(ksp);
	String categories[];
	EquationProcessor ep = new EquationProcessor();
	Map<String, Object[]> preprocessExcel = new HashMap<String, Object[]>();
	Map<String, Object[]> postOutputExcel = new HashMap<String, Object[]>();
	Map<String, Object[]> sentenceDataExcel = new HashMap<String, Object[]>();
	Map<String, Object[]> clauseDataExcel = new HashMap<String, Object[]>();
	Map<String, Object[]> tokenDataExcel = new HashMap<String, Object[]>();
	Map<String, Object[]> wordInfoExcel = new HashMap<String, Object[]>();
	int wordInfoExcelCount = 2;
	Map<String, Object[]> sentenceErrorExcel = new HashMap<String, Object[]>();
	int sentenceErrorExcelCount = 2;
	int preprocessCount = 2;
	int tokenDataExcelCount = 2;
	int postOutputExcelCount = 2;
	int sentenceDataExcelCount = 2;
	int clauseDataExcelCount = 2;
	Tagger tagger = new Tagger();
	ClauseEvaluator ce = new ClauseEvaluator(ksp);
	EquationProcessor eqProcessor = new EquationProcessor();
	LinguisticLibrary ll = new LinguisticLibrary();
	SpellChecker spellcheck = new SpellChecker();

	public void init() {
		categories = ("sex,charity,"
                + "gun,drug,"
                + "alcohol,profane,politics").split(",");
		//categories = ("drug,alcohol," + "gun,charity," + "sex,politics,profane").split(",");
		preprocessExcel.put("1", new Object[] { "Raw Data", "Clean Data", "Tagged Data", "Refined Tagged Data" });
		postOutputExcel.put("1",
				new Object[] { "Grammar Points", "Drug Related Points", "Alcohol Related Points", "Sex Related Points",
						"Charity Related Points", "Gun Related Points", "Politics Related Points", "Profanity Points",
						"Grammar Points" });
		sentenceDataExcel.put("1", new Object[] { "Clause", "Pattern", "Type", "Mood", "Submood", "Tense" });
		;
		clauseDataExcel.put("1", new Object[] { "Syntactic Role", "Content", "Type", "Sub Type", "Equation" });
		;
		tokenDataExcel.put("1", new Object[] { "Index", "Word", "Postag", "Is a Keyword?", "Is Ambiguous?" });
		wordInfoExcel.put("1", new Object[] { "Lexname", "Definition" });
	}
	
	public void main(ArrayList<SocialMediaData> socialMediaData) {

		init();
		LinkedList<CHAR_CATEGORYSCORE> cs = new LinkedList();
		cs.add(new CHAR_CATEGORYSCORE(this.username,"sex", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"charity", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"gun", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"drug", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"alcohol", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"profane", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"politics", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"grammar", 0, 0, 0, 0,"0.0"));
		cs.add(new CHAR_CATEGORYSCORE(this.username,"spelling", 0, 0, 0, 0,"0.0"));
		try {
			for (SocialMediaData smd : socialMediaData) {
				System.out.println("START HERE....");
				HashMap<String, Boolean> skCategoryExist = new HashMap();
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
					protected boolean hasError(HttpStatus statusCode) {
						return false;
					}
				});

				System.out.println("smd.getID()==>" + smd.getData_id());
				ResponseEntity<BackendProcessStatus> rebps = restTemplate.getForEntity(
						uriReadBackendProcessStatus + "/" + smd.getData_id() + "/", BackendProcessStatus.class);
				BackendProcessStatus bps;

				if (!rebps.hasBody()) {
					bps = new BackendProcessStatus(false, false, false, smd.getData_id());
					ResponseEntity<BackendProcessStatus> rejp = restTemplate
							.postForEntity(uriCreateBackendProcessStatus, bps, BackendProcessStatus.class);

					if (rejp.getStatusCode().equals(HttpStatus.OK)) {

						System.out.println("CREATE BACKEND PROCESS STATUS SUCCESS!!! ");
					} else {
						System.out.println("CREATE BACKEND PROCESS STATUS NOT SUCCESS!!! ");
					}
					rebps = restTemplate.getForEntity(uriReadBackendProcessStatus + "/" + smd.getData_id() + "/",
							BackendProcessStatus.class);
					bps = rebps.getBody();
				} else {
					System.out.println("BACKEND PROCESS STATUS is ALREADY AVAILABLE!!! ");
					bps = rebps.getBody();
				}

				
				if (!bps.isChunkingDone()) {
					String st = dataclean.cleanText(smd.getData());
					//----------------------
					DocumentPreprocessor dc = tagger.preprocess(st);
					String newSt = "";
					try{
					for(List<HasWord> s : dc)
		            {
		                for(TaggedWord tw : tagger.getT(s))
		                {
		                    StringEvaluator streval = new StringEvaluator();
		                    String word = tw.word();
		                    String pos = ll.getCategoryPOS(tw.tag());
		                    boolean canBeABisyaTerm = ksp.isABisayaWord(word);
		                    if(ksp.isAProfanityWord(word.toLowerCase().trim()))
		                    {
		                        skCategoryExist.put("profane", true);
		                        
		                    }
		                    WordnetProcess wp = new WordnetProcess();
		                    if((pos.contentEquals("noun")||pos.contentEquals("verb")||pos.contentEquals("adjective")
		                    ||pos.contentEquals("adverb")) && !tw.tag().contentEquals("NNP") && 
		                    !tw.tag().contentEquals("NNPS") && !streval.isValidAccToRule(word, "'s|n't|'re|'m|'ve")
		                    && !wp.doesWordWithCertainPostagExist(tw.word(), pos)
		                            )
		                    {
		                        if(canBeABisyaTerm)
		                        {
		                            word = "";
		                        }
		                        else
		                        {
		                        	skCategoryExist.put("spelling", true);
		                            word = spellcheck.processWithPostag(word, pos);
		                        }
		                    }
		                    if(canBeABisyaTerm)
		                    {
		                        for(String cat : categories)
		                        {
		                            if(ksp.isInputInBasis(word, ksp.getCategorySpecialKeyword(), cat))
		                            {
		                                skCategoryExist.put(cat, true);
		                            }
		                        }
		                    }
		                    newSt = newSt+" "+word;
		                }
		            }
					}catch(Exception e)
					{
						newSt = st;
						System.out.println("ERROR IN CHUNKING===>"+e.getMessage());
					}
	//----------------------
					dc = tagger.preprocess(newSt);

					StringEvaluator streval = new StringEvaluator();
					String holder = "", holdr = "";
					int prevStartPar = 0;
					for (List<HasWord> s : dc) {
						String[] hold = tagger.process(s);

						TagRefiner tagRefiner = new TagRefiner();
						hold[0] = tagRefiner.process(hold[0]);

						Chunker chunker = new Chunker();
						HashMap<String, String> errorlist = new HashMap();
						LinkedList chunks = new LinkedList();
						String remnant = chunker.process(holder + hold[0], chunks, errorlist);

						SentenceData sd = new SentenceData(holdr + hold[1], remnant);
						sd.setData_id(smd.getData_id());
						sd.setChunk(transformChunks(chunks));
						ResponseEntity<SentenceData> rejp = restTemplate.postForEntity(uriCreateSentenceData, sd,
								SentenceData.class);
						Long sentenceID = rejp.getBody().getId();
					//	System.out.println("b4");
					//	System.out.println("sentenceID==>" + sentenceID);
					//	System.out.println("afteee");

						if (sd.getError() != null) {
							for (String key : sd.getError().keySet()) {
								System.out.println(">>>DETECTED GRAMMAR ERROR");
								GrammarError ge = new GrammarError(key, Integer.parseInt(sd.getError().get(key)),
										sentenceID);
								ResponseEntity<GrammarError> rege = restTemplate.postForEntity(uriCreateGrammarError,
										ge, GrammarError.class);
							}
						}

					}
					bps.setChunkingDone(true);
					restTemplate.put(uriUpdateBackendProcessStatus, bps);

					System.out.println("DONE... CHUNKING..");
				}
				
				//if(bps.isCoreferenceResolutionDone() && bps.isWordSenseDisambiguationDone()())

				ResponseEntity<SentenceData[]> resd = restTemplate
						.getForEntity(uriReadAllSentenceData + "/" + smd.getData_id() + "/", SentenceData[].class);
				LinkedList<SentenceData> posts = new LinkedList();

				if (resd.hasBody()) {
					for (SentenceData sd : resd.getBody()) {

						HashMap<String, String> errorList = new HashMap();
						ResponseEntity<GrammarError[]> rege = restTemplate
								.getForEntity(uriReadAllGrammarError + "/" + sd.getId() + "/", GrammarError[].class);
						for (GrammarError ge : rege.getBody()) {
							errorList.put(ge.getName(), "" + ge.getCount());
						}
						sd.setError(errorList);
						System.out.println("CHUNKS HERE 1==>>" + sd.getChunk());
						SentenceEvaluator stnceEval = new SentenceEvaluator();
						posts.add(stnceEval.detectSentenceStructure(sd.getContent(), sd.getRemnant(),
								transformToLinkedList(sd.getChunk()), sd.getError()));
						System.out.println("CHUNKS HERE==>>" + posts.getLast().getChunk());
					}
				}

				// if(!bps.isCoreferenceResolutionDone())
				// {
				System.out.println("START... COREFERENCE..");
				for (SentenceData sd : posts) {
					System.out.println("HERE ME 1");
					for (String kcd : sd.getClauseData().keySet()) {
						System.out.println("HERE ME 2");

						for (ClauseData cd : sd.getClauseData().get(kcd)) {
							System.out.println("HEAD..." + cd.getHead());
							System.out.println("PATTERN..." + cd.getSentencePattern());
							System.out.println("CHUNKS...." + sd.getChunk());
							System.out.println("PARTS....");
							for (String key : cd.getParts().keySet()) {
								System.out.println("****" + cd.getParts().get(key));
							}

							System.out.println("HERE ME 3");
							CoreferenceResolver cr = new CoreferenceResolver();
							System.out.println("HERE ME 4");
							cr.mentionDetection("" + cd.getHead(), cd.getSentencePattern(),
									transformToLinkedList(sd.getChunk()), cd.getParts());
							cr.processPreciseConstructs();
							cr.pronounResolutionSieve();
							cr.postProcess("" + cd.getHead(), cd.getSentencePattern(),
									transformToLinkedList(sd.getChunk()), cd.getParts());
							System.out.println("HERE ME 5");
							for (WordInfo wi : cr.getTokens()) {
								System.out.println("***HEY DZAE==>" + smd.getData_id());
								System.out.println("***HEY DZAE==>" + wi.getWord());
								System.out.println("***HEY DZAE==>" + wi.getPostag());

								ResponseEntity<WordInfo> rewi = restTemplate.getForEntity(uriReadWordInfo + "/"
										+ smd.getData_id() + "/" + wi.getWord() + "/" + wi.getPostag() + "/",
										WordInfo.class);
								if (!rewi.hasBody()) {
									System.out.println("*HELLO REWI!");
									wi.setData_id(smd.getData_id());
									ResponseEntity<WordInfo> rewi1 = restTemplate.postForEntity(uriCreateWordInfo, wi,
											WordInfo.class);
									if (!rewi1.getStatusCode().equals(HttpStatus.OK)) {
										System.out.println("UNSUCCESSFUL CREATE WORDINFO==>" + wi.getWord());
									}
									try {
										WordInfo tempwi = new WordnetProcess().getWordInfo(wi.getWord(),
												wi.getPostag());
										// System.out.println("tempwi glossInfo
										// size =
										// "+tempwi.getGlossInfo().size());
										for (GlossInfo gi : tempwi.getGlossInfo()) {
											gi.setWordInfoId(rewi1.getBody().getId());
											ResponseEntity<GlossInfo> regi = restTemplate
													.postForEntity(uriCreateGlossInfo, gi, GlossInfo.class);
											if (!regi.getStatusCode().equals(HttpStatus.OK)) {
												System.out.println("UNSUCCESSFUL CREATE GLOSSINFO==>" + wi.getWord());
											}
										}

									} catch (Exception e) {

									}
									System.out.println("outside loop tempwi.getGlossInfo");
								}
								System.out.println(
										"CORE 1==>" + smd.getData_id() + "==>" + wi.getWord() + "==>" + wi.getPostag());
								rewi = restTemplate.getForEntity(uriReadWordInfo + "/" + smd.getData_id() + "/"
										+ wi.getWord() + "/" + wi.getPostag() + "/", WordInfo.class);
								System.out.println("CORE 2");
								// System.out.println("wi.getGlossInfo size =
								// "+wi.getGlossInfo().size());
								if (wi.getGlossInfo() != null) {
									for (GlossInfo gi : wi.getGlossInfo()) {
										ResponseEntity<GlossInfo> regi = restTemplate.getForEntity(uriReadGlossInfo
												+ "/" + rewi.getBody().getId() + "/" + gi.getWordSenseID() + "/",
												GlossInfo.class);
										if (regi.hasBody()) {
											regi.getBody().setValidInCR(true);
											restTemplate.put(uriUpdateGlossInfo, regi.getBody());
											break;
										}
									}
								}

								System.out.println("CORE 3");
							}
						}
					}

				}

				// }
				LinkedList<WordInfo> tokens = new LinkedList<WordInfo>();
				ResponseEntity<WordInfo[]> rewi = restTemplate
						.getForEntity(uriReadAllWordInfo + "/" + smd.getData_id() + "/", WordInfo[].class);
				if (rewi.hasBody()) {
					for (WordInfo wi : rewi.getBody()) {
						wi.setGlossInfo(new LinkedList());
						ResponseEntity<GlossInfo[]> regi = restTemplate
								.getForEntity(uriReadAllGlossInfo + "/" + wi.getId() + "/", GlossInfo[].class);
						if (regi.hasBody()) {
							for (GlossInfo gi : regi.getBody()) {
								WordnetProcess wp = new WordnetProcess();
								gi.setSynonyms(wp.getSynonyms(wi.getWord(), wi.getPostag()));
								wi.getGlossInfo().add(gi);
							}
						}
						tokens.add(wi);
					}
				}

				// if(!bps.isWordSenseDisambiguationDone())
				// {
				System.out.println("START... WSD..");

				System.out.println("WORDSENSE DISAMBIGUATION");
				WordSenseDisambiguator wsd = new WordSenseDisambiguator(tagger, tokens, ksp);

				for (int i = 0; i < tokens.size(); i++) {
					boolean flag = false, flagA = false;
					if (tokens.get(i).getGlossInfo() != null && !tokens.get(i).getGlossInfo().isEmpty()) {
						for (GlossInfo glossinfo : tokens.get(i).getGlossInfo()) {
							if (ksp.getKeywordCategories(glossinfo.getWordSenseID()) != null) {
								flag = true;
							} else {
								flagA = true;
							}
						}
					}
					if (flag && flagA) {
						tokens.get(i).setGlossInfo(wsd.process(tokens.get(i).getWord(), tokens.get(i).getPostag(),
								tokens.get(i).getGlossInfo()));
						for (GlossInfo gi : tokens.get(i).getGlossInfo()) {
							ResponseEntity<GlossInfo> regi = restTemplate.getForEntity(
									uriReadGlossInfo + "/" + tokens.get(i).getId() + "/" + gi.getWordSenseID() + "/",
									GlossInfo.class);
							if (regi.hasBody()) {
								regi.getBody().setValidInWSD(true);
								restTemplate.put(uriUpdateGlossInfo, regi.getBody());
							}
						}
					}
				}

				
				// }
				HashMap<String, String> pntHolder = new HashMap();
				boolean flag = false;
				int gcheck = 0, gwrong = 0;

				for (SentenceData post : posts) {
					System.out.println("CONTENT==>" + post.getContent());
					System.out.println("REMNANT==>" + post.getRemnant());

					for (String str : transformToLinkedList(post.getChunk())) {
						System.out.println("PHRASE==>" + str);
					}
					System.out.println("POST REMNANT====>" + post.getRemnant());
					StringEvaluator streval = new StringEvaluator();
					String content = streval.getPhraseContent(post.getRemnant(), transformToLinkedList(post.getChunk()),
							true, streval.CLAUSE + "(C)?", false, true, false);
					System.out.println("clauses CONTENT==>" + content);
					String[] keySet = streval.getSpecificPartAccToRuleName(content, streval.CLAUSE + "(C)?");
					String keyPrevClause = "";
					for (String k1 : post.getClauseData().keySet()) {
						System.out.println("clause KEY==>" + k1);
						System.out.println("" + post.getClauseData());
						pntHolder = getNewPnts(pntHolder, evaluateRoles(skCategoryExist,post.getClauseData().get(k1), tokens));
					}

					if (post.getError().isEmpty()) {
						gcheck++;
					} else {
						gwrong++;
					}
				}
				if (posts.size() > 0) {
					pntHolder.put("grammar_pos", "" + ((double) gcheck / posts.size()));
					pntHolder.put("grammar_neg", "" + ((double) gwrong / posts.size()));
				}

				bps.setCoreferenceResolutionDone(true);
				restTemplate.put(uriUpdateBackendProcessStatus, bps);
				bps.setWordSenseDisambiguationDone(true);
				restTemplate.put(uriUpdateBackendProcessStatus, bps);
				addCategoryScores(cs, pntHolder);
				System.out.println("END HERE....");
			}

		} catch (Exception ex) {
			System.out.println("ERROR DZAE==>" + ex);
		}
		System.out.println(">>>Printing collected CHAR_CATEGORYSCORE");
		for(CHAR_CATEGORYSCORE c  : cs)
		{
			System.out.println(c.toString());
		}
		this.setLlcs(cs);
	}

	public int getNumberEquivalent(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			return 0;
		}
	}

    public static boolean hasBothFirstPersonAndCategory(String category, HashMap<String, String> pnt)
    {
        System.out.println("HERE hasBothFirstPersonAndCategory==>"+category);
        if(pnt.get("firstPerson")!=null && pnt.get(category)!=null)
        {
            System.out.println(">>>HERE 1");
            return pnt.get("firstPerson").contentEquals("1") && pnt.get(category).contains("1");
        }
        return false;
    }

    public HashMap<String, String> getPhraseCategoryPoints(HashMap<String, String> catPnt)
    {
        HashMap<String, String> result = new HashMap();
        for(String cat : categories)
        {
            if(hasBothFirstPersonAndCategory(cat, catPnt))
            {
                String temp;
                if(!(temp=catPnt.get("polarity")).contentEquals("0"))
                {
                    result.put(cat, temp);
                }
                else
                {
                    result.put(cat, "1");
                }
            }
        }
        return result;
    }
	
	public HashMap<String, String> evaluateRoles(HashMap<String, Boolean> skCategoryExist, LinkedList<ClauseData> input, LinkedList<WordInfo> tokens)
    {
        HashMap<String, String> output = new HashMap();
        if(input!=null && !input.isEmpty())
        {
            for(String cat : skCategoryExist.keySet())
            {
            	if(cat.contentEquals("spelling") && skCategoryExist.get(cat))
            	{
            		output.put(cat, "-1");
            	}
            	else if(skCategoryExist.get(cat))
                {
                    output.put(cat, "1");
                }
            }
            for(ClauseData cd : input)
            {
                System.out.println("SENTENCE_PATTERN==>"+cd.getSentencePattern());
                System.out.println("TENSE==>"+ cd.getTense());
                System.out.println("CONTENT==>"+ cd.getContent());
                System.out.println("MOOD==>"+ cd.getMood());
                System.out.println("SUB_MOOD==>"+ cd.getSubMood());
                System.out.println("TYPE==>"+ cd.getType());
                for(String k2 : cd.getParts().keySet())
                {
                    if(cd.getParts().get(k2).getType().contentEquals("PHRASE"))
                    {
                        System.out.println("PHRASE==>"+k2);
                        System.out.println("PHRASE CONTENT==>"+cd.getParts().get(k2).getContent());
                        cd.getParts().get(k2).setCategoryPnt(ce.evaluateEquation(ep.getPostFix(cd.getParts().get(k2).getRoleA().getEquation()), tokens));
                        HashMap<String, String> tempCatPnt = new HashMap();
                        output = getSumPnts(output, getPhraseCategoryPoints(cd.getParts().get(k2).getCategoryPnt()));
                    }
                    else if(cd.getParts().get(k2).getType().contentEquals("CLAUSE"))
                    {
                        cd.getParts().get(k2).setCategoryPnt(evaluateRoles(skCategoryExist,cd.getParts().get(k2).getRoleB(), tokens));
                    }
                }
                output = getSumPnts(output, ce.evaluateClause(cd));
            }
        }
        return output;
    }

	public HashMap<String, String> getSumPnts(HashMap<String, String> h1, HashMap<String, String> h2) {
		for (String key : h2.keySet()) {

			if (!h1.containsKey(key)) {
				h1.put(key, h2.get(key));
			} else {
				h1.replace(key, "" + (Integer.parseInt(h1.get(key)) + Integer.parseInt(h2.get(key))));
			}
		}
		return h1;
	}

	public HashMap<String, String> getNewPnts(HashMap<String, String> h1, HashMap<String, String> h2) {
		for (String key : h2.keySet()) {
			System.out.println("HERE GIRL key==>"+h1.get(key)+"<===>"+h2.get(key));
			double val = Double.parseDouble(h2.get(key));
			System.out.println("HERE DZAE VAL==>" + key + "<==>" + val);
			if (val > 0) {
				h1.put(key + "_pos", "1");
			} else if (val < 0) {
				h1.put(key + "_neg", "1");
			} else {
				h1.put(key + "_neu", "1");
			}

		}
		return h1;
	}

	public void addCategoryScores(LinkedList<CHAR_CATEGORYSCORE> cs, HashMap<String, String> h1) {
		for (int i = 0; i < cs.size(); i++) {
			double total = 0, pos = 0, neg = 0, neu = 0;
			boolean flag = false;
			if (h1.get(cs.get(i).getCategoryName() + "_pos") != null) {
				flag = true;
				pos = Double.parseDouble(h1.get(cs.get(i).getCategoryName() + "_pos"));
			}
			if (h1.get(cs.get(i).getCategoryName() + "_neg") != null) {
				flag = true;
				neg = Double.parseDouble(h1.get(cs.get(i).getCategoryName() + "_neg"));
			}
			if (h1.get(cs.get(i).getCategoryName() + "_neu") != null) {
				flag = true;
				neu = Double.parseDouble(h1.get(cs.get(i).getCategoryName() + "_neu"));
			}
			total = pos + neg + neu;
			if (pos > neg && pos > neu) {
				cs.get(i).setPositive(1 + cs.get(i).getPositive());
				cs.get(i).setTotalPost(cs.get(i).getTotalPost() + 1);
			} else if (neg > pos && neg > neu) {
				cs.get(i).setNegative(1 + cs.get(i).getNegative());
				cs.get(i).setTotalPost(cs.get(i).getTotalPost() + 1);
			} else if (neu > pos && neu > neg) {
				cs.get(i).setNeutral(1 + cs.get(i).getNeutral());
				cs.get(i).setTotalPost(cs.get(i).getTotalPost() + 1);
			}

			// if(flag)
			// {
			// cs.get(i).setNoOfPlusPosts((pos/total)+cs.get(i).getNoOfPlusPosts());
			// cs.get(i).setNoOfNegaPosts((neg/total)+cs.get(i).getNoOfNegaPosts());
			// cs.get(i).setNoOfNeutralPosts((neu/total)+cs.get(i).getNoOfNeutralPosts());
			// cs.get(i).setTotalNoOfPosts(cs.get(i).getTotalNoOfPosts()+1);
			// }
		}
	}

	public LinkedList<String> transformToLinkedList(String input) {
		LinkedList<String> result = new LinkedList();
		result.addAll(Arrays.asList(input.split(" ")));
		return result;
	}

	public String transformChunks(LinkedList<String> phrases) {
		String result = "";
		for (String p : phrases) {
			result = result + " " + p;
		}
		System.out.println("HERE DZAE RESULT==>" + result);
		return result.trim();
	}
}
