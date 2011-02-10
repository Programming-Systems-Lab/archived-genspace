package org.geworkbench.components.skylinecontour.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;

import edu.columbia.cs.skys.Config;
import edu.columbia.cs.skys.cache.CacheException;
import edu.columbia.cs.skys.cache.ICache;
import edu.columbia.cs.skys.data.AnnotationSet;
import edu.columbia.cs.skys.data.Point;
import edu.columbia.cs.skys.data.PointYComparator;
import edu.columbia.cs.skys.db.DAO;
import edu.columbia.cs.skys.skyline.ISkylineQuery;
import edu.columbia.cs.skys.util.Action;
import edu.columbia.cs.skys.util.DBUtils;
import edu.columbia.cs.skys.util.Profiler;

public class ExperimentsManager extends QueryManager {
		
	public ExperimentsManager() throws CacheException{
		super();
	}
	
	public ExperimentsManager(boolean init) throws CacheException {
		super(init);
	}

	public ExperimentsManager(int reqId) throws CacheException {
		super(reqId);
	}

	public ICache getCache() {
		return _cache;
	}
	
	public static Point<?,?>[] computeUBSkyline (ICache cache, ISkylineQuery skylineQuery, Point<?,?>[] points, int numContours, boolean isExact) 
																throws RemoteException {
		
		skylineQuery.startProfiling();
	
		Date start = new Date();
		Point<?,?>[] skyline = skylineQuery.getSkyline(points, numContours);		
		Date end = new Date();

		Profiler cacheProf = cache.getProfiler();
		Profiler skylineProfiler = skylineQuery.getProfiler();
		cacheProf.add(skylineProfiler.collate("computeUB"));	
		cacheProf.add(skylineProfiler.collate("computeScore"));
		cacheProf.add(skylineProfiler.collate("assignLinearDominance"));
		cacheProf.add(new Action("computeUBSkyline" + (isExact?" - EXACT" : " - UB"), start, end, points.length));
	
		skylineQuery.stopProfiling();
		return skyline;
	}

	public static void runSkylineExperiment(PrintStream outFP, String query) throws IOException, RemoteException, CacheException {
		
		ExperimentsManager em = new ExperimentsManager(false);	
		QuerySession qs = em.spawnQuerySession(query, -1);
		
		// local execution
		//ISkylineQuery _skylineQuery = new DCBatchedUBSkyline(em._cache, qs.getReqId(), sType, exact);	
	    //_skylineQuery.setDominanceComparators(new IDominanceComparator[] 
	    //                                     {new SmallerBetterDominance<Double>(), new LargerBetterDominance<Double>()});	

		// retrieve results from PubMed, and store them to be used by all different methods
		int numBatches = qs._meshQuery._numResults / qs._meshQuery._batchSize;
		if (qs._meshQuery._numResults % qs._meshQuery._batchSize > 0) {
			numBatches++;
		}
		int [][] allPubmedResults = new int[numBatches][qs._meshQuery._batchSize];
		
		for (int batchNum=0; batchNum < numBatches; batchNum++) {
			
			int[] pubmedResults = QuerySession.retrievePubMedBatch(qs._meshQuery, batchNum);
			
			for (int i=0; i<pubmedResults.length; i++) {
				allPubmedResults[batchNum][i] = pubmedResults[i];
			}
		}		
		int querySize = qs._meshQuery._queryMeta.getTermScope().size();

		ICache.Score_Type sTypes[] = { ICache.Score_Type.SCORE, ICache.Score_Type.CONDITIONAL, ICache.Score_Type.BALANCED };
		int[] numContours = {1,2,5,10,20};
		boolean[] computeExactSkyline = {true, false};
		
		for (int K : numContours) {
			outFP.print("'" + query + "'," + querySize + "," + numBatches + "," + qs._meshQuery._numResults + "," + K);
			
			for (ICache.Score_Type sType : sTypes) {
				
				for (boolean exact : computeExactSkyline) {
					
					em._cache.forceInitSkyline( qs.getReqId(), sType, exact);
					
					long duration = 0;
					int numCalls = 0;		
					int numUBCalls = 0;
					int numExactCalls = 0;
					
					for (int batchNum=0; batchNum < allPubmedResults.length; batchNum++) {
						em._cache.startProfiling();				
								
						Vector<Integer> batchIds = new Vector<Integer>();	
						qs._meshQuery.setResults(allPubmedResults[batchNum], batchNum);
						for (int i=0; i<allPubmedResults[batchNum].length; i++) {
							int id = allPubmedResults[batchNum][i];
							
							if (em._cache.isCached(id)) {
								batchIds.add(new Integer(id));
							}
						}
						Point<?,?>[] batchPoints = new Point<?,?>[batchIds.size()];		 
						for (int i=0; i<batchIds.size(); i++) {
							batchPoints[i] = new Point<Integer,Integer>(batchIds.get(i).intValue(), 0, 0);
						}
		
						if (batchPoints.length > 0) {
							//Point<?,?>[] skylinePoints =  em._cache.computeUBSkyline(batchPoints, qs.getReqId(), K, sType, ICache.Optimization_Type.NONE, exact);
							em._cache.computeUBSkyline(batchPoints, qs.getReqId(), K, sType, ICache.Optimization_Type.NONE, exact);				
						}
						
						Profiler prof = em._cache.getProfiler();
						Action act = prof.getAction("computeUBSkyline");
						em._cache.stopProfiling();

						duration += act.duration();
						numCalls += act.getNumCalls();
						
						if (!exact) {
							act = prof.getAction("computeUB collated");
							numUBCalls += act.getNumCalls();
							
							act = prof.getAction("computeScore collated");
							numExactCalls += act.getNumCalls();
						}
					}
					double averageDuration = (double)duration/(double)numBatches;
					outFP.print("," + duration + "," + Math.round(averageDuration) + "," + numCalls);
					if (!exact) {
						outFP.print("," + numUBCalls + "," + numExactCalls);
					}
				}
			}
			outFP.print("\n");
			outFP.flush();
		}		
	}
	
	public static void doComputeScoreDistribution(String query, ICache.Score_Type sType, List<Point<?,?>>  pointsSortedOnScore) throws SQLException {
		
		Vector<Point<Integer, Double>> scoreHistogram = new Vector<Point<Integer, Double>>();
		double currentScore = Double.MAX_VALUE;
		int currentCount = 0;
		
		for (Point<?,?> pt : pointsSortedOnScore) {
			if (pt.yToDouble() < currentScore) {
				if (currentCount > 0) {
					Point<Integer,Double> scorePoint = new Point<Integer,Double>(currentCount, currentScore);
					scoreHistogram.add(scorePoint);
				}
				currentScore = pt.yToDouble();
				currentCount = 1;
			} else {
				currentCount++;
			}
		}
		// add last one
		if (currentCount > 0) {
			Point<Integer,Double> scorePoint = new Point<Integer,Double>(currentCount, currentScore);
			scoreHistogram.add(scorePoint);
		}		
		
		// store the information in the database
		for (Point<Integer,Double> pt : scoreHistogram) {
			String sql = "insert into VLDB_Results_Score_Histogram (query, similarity, score, num_results) " +
					"		values ('" + query + "', '" + sType.toString() + "', " + pt.yToDouble() + ", " + pt.xToDouble() + ")";
			DBUtils.executeUpdate(DAO.openDBConnection(), sql);
		}
		DAO.closeDBConnection();
	}

	public static void runTopKExperiment(PrintStream outFP, String query, 
										 boolean computeScoreDistribution, boolean generateAnnotationSets, boolean storeScores) 
																							throws IOException, RemoteException, CacheException {
		
		int NUM_RUNS = 1; // 3 for performance experiments
		
		ExperimentsManager em = new ExperimentsManager(false);	
		QuerySession qs = em.spawnQuerySession(query, -1);
		Profiler prof = em.getCache().getProfiler();
		
		Vector<Integer> resultsV = qs.searchPubMed(query, "");	
		
		int[] results = new int[resultsV.size()];
		for (int i=0; i<resultsV.size(); i++) {
			results[i] = resultsV.get(i).intValue();
		}

		if ((results.length > 0) && generateAnnotationSets) {	
			AnnotationSet[] annotationSets = em._cache.getAnnotationSets(results, Config.NUM_ANNOTATION_SETS);
			System.out.println("Retrieved " + annotationSets.length + " annotation sets.");
		}

		int querySize = qs._meshQuery._queryMeta.getTermScope().size();
		int resultSize = resultsV.size();
		
		outFP.println("");
		
		outFP.println("\"" + query + "\"," + resultSize + "," + querySize);

		ICache.Score_Type [] sTypes = {ICache.Score_Type.SCORE, ICache.Score_Type.CONDITIONAL, ICache.Score_Type.BALANCED, ICache.Score_Type.INFO, ICache.Score_Type.PATH};
										 
		long duration = 0;
		int numCalls = 0;
		
		outFP.print("computeScore,");	
		for (ICache.Score_Type sType : sTypes) {
			for (int run=0; run<NUM_RUNS; run++) {
				
				em._cache.startProfiling();
					
				Point<?,?>[] scores = em._cache.computeScore(results, qs.getReqId(), sType, ICache.Optimization_Type.NONE);
				List<Point<?,?>> sortedScores = Arrays.asList(scores);
				Collections.sort(sortedScores, new PointYComparator(false));
					
				if (computeScoreDistribution) {
					try {
						doComputeScoreDistribution(query, sType, sortedScores);
					} catch (SQLException sqle) {
						sqle.printStackTrace(System.err);
					}
				}

				if (storeScores) {
					try {
						DAO.openDBConnection();
					
						String sql = "insert into VLDB_Results_Scores (query, similarity, pmid, score, rnk, dense_rnk) values (?, ?, ?, ?, ?, ?)";
						PreparedStatement pstmt = DAO._conn.prepareStatement(sql);
						pstmt.setString(1, query);
						pstmt.setString(2, sType.toString());
						double currScore = Double.MAX_VALUE;
						int currRank = 0;
						int currDenseRank = 0;
						int currTies = 0;
						for (Point<?,?> pt : sortedScores) {
							if (pt.yToDouble() == 0) {
								continue;
							}
							pstmt.setInt(3, pt.getId());
							pstmt.setDouble(4, pt.yToDouble());
							if (pt.yToDouble() < currScore) {
								currScore = pt.yToDouble();
								currRank++;
								currDenseRank += currTies;
								currTies = 1;
							} else {
								currTies++;
							}
							pstmt.setInt(5, currRank);
							pstmt.setInt(6, currDenseRank);
							pstmt.executeUpdate();
						}
						
						pstmt.close();
						DAO.closeDBConnection();
					
					} catch (SQLException sqle) {
						sqle.printStackTrace(System.err);
					}
				}
				
				prof = em._cache.getProfiler();
				Action act = prof.getAction("computeScore");
				em._cache.stopProfiling();
				
				duration += act.duration();
				numCalls = act.getNumCalls();
			}
			double averageDuration = (double)duration/(double)3;
			duration = 0;
			outFP.print("," + Math.round(averageDuration) + "," + numCalls);
		}
		outFP.print("\n");
		outFP.flush();

		outFP.print("computeUB,");
		for (ICache.Score_Type sType : sTypes) {
			for (int run=0; run<3; run++) {
				
				em._cache.startProfiling();
					
				Point<?,?>[] ubs = em._cache.computeUB(results, qs.getReqId(),sType, ICache.Optimization_Type.NONE);
				List<Point<?,?>> sortedUBs = Arrays.asList(ubs);
				Collections.sort(sortedUBs, new PointYComparator(false));

				prof = em._cache.getProfiler();
				Action act = prof.getAction("computeUB");
				em._cache.stopProfiling();

				duration += act.duration();
				numCalls = act.getNumCalls();
			}
			double averageDuration = (double)duration/(double)3;
			duration = 0;
			outFP.print("," + Math.round(averageDuration) + "," + numCalls);
		}
		outFP.print("\n");
		outFP.flush();

		int[] K = {1, 10, 20, 50, 100, 1000};
		for (int j=0; j<K.length; j++) {
			outFP.print("computeTopK," + K[j]);
			
			for (ICache.Score_Type sType : sTypes) {			
				
				for (int run=0; run<3; run++) {
				
					em._cache.startProfiling();
						
					// Point<?,?>[] topK = em._cache.computeTopK(results, qs.getReqId(), K[j], sType, ICache.Optimization_Type.NONE);
					em._cache.computeTopK(results, qs.getReqId(), K[j], sType, ICache.Optimization_Type.NONE);
					
					prof = em._cache.getProfiler();
					Action act = prof.getAction("computeTopK");
					
					em._cache.stopProfiling();

					duration += act.duration();
					numCalls = act.getNumCalls();
				}
				double averageDuration = (double)duration/(double)3;
				duration = 0;
				outFP.print("," + Math.round(averageDuration) + "," + numCalls);				
			}
			outFP.print("\n");
			outFP.flush();
		}
	}

	public static void runTopKOutputResults(PrintStream outFP, String query, int K) throws IOException, RemoteException, CacheException {

		String url = "http://www.ncbi.nlm.nih.gov/sites/entrez?db=pubmed&cmd=search&term=";

		ICache.Score_Type [] sTypes = {  ICache.Score_Type.SCORE, ICache.Score_Type.CONDITIONAL, ICache.Score_Type.BALANCED };
		
		ExperimentsManager em = new ExperimentsManager(false);	
		QuerySession qs = em.spawnQuerySession(query, -1);
		
		Vector<Integer> resultsV = qs.searchPubMed(query, "");	
		
		int[] results = new int[resultsV.size()];
		for (int i=0; i<resultsV.size(); i++) {
			results[i] = resultsV.get(i).intValue();
		}
		System.out.println("Query " + query + " returned " + results.length + " results.");
		
		for (ICache.Score_Type sType : sTypes) {
					
			Point<?,?>[] scores = em._cache.computeScore(results, qs.getReqId(), sType, ICache.Optimization_Type.NONE);
			List<Point<?,?>> sortedScores = Arrays.asList(scores);
			
			// get top-K results (ignore ties and articles that pre-date 2000)
			Collections.sort(sortedScores, new PointYComparator(false));
			int collected = 0;
			int rank = 0;
			while ((rank < sortedScores.size()) && (collected < 2*K)) {
				Point<?,?> point = sortedScores.get(rank);
				rank++;
				if (point.xToDouble() > 3000) {
					continue;
				}
				if (collected >= K) {
						outFP.println(sType.toString() + ",best," + collected + "," + point.yToDouble() + "," + 
								      point.getId() + "," + url + point.getId());
						outFP.flush();
				}
				collected++;
			}

			// get bottom-K results (ignore ties, items with 0 score, and items that pre-date 2000)			
			Collections.sort(sortedScores, new PointYComparator(true));
			rank = 0;
			collected = 0;
			while ((rank < sortedScores.size()) && (collected < 2*K)) {
				Point<?,?> point = sortedScores.get(rank);
				rank++;
				if (point.xToDouble() == 0) {
					continue;
				}
				if (point.xToDouble() > 3300) {
					continue;
				}
				int actualRank = sortedScores.size() - rank;
				if (collected >= K) {
					outFP.println(sType.toString() + ",worst," + actualRank + "," + point.yToDouble() + "," + 
								   point.getId() + "," + url + point.getId());
					outFP.flush();
				}
				collected++;
			}
		}
	}
	
	public static String[] chooseRandomQueries(PrintStream outFP, int numQueries) {
		
		String[] queries = new String[numQueries * 3];
		
		try {	
			// choose "numQueries" random ids from between 1 and 7471, build AND queries
			HashSet<String> querySet = new HashSet<String>();
			Random generator = new Random();

			ExperimentsManager em = new ExperimentsManager(false);	

			while (querySet.size() < numQueries) {
				
				int queryId = generator.nextInt(7470);
				String heading1 = DBUtils.getStringFromDB(DAO.openDBConnection(), 
						"select heading from Co_Queries_Disjoint q, Mesh_Terms m " +
						" where q.queryId = " + queryId + " and q.rightId = m.id ");

				String heading2 = DBUtils.getStringFromDB(DAO.openDBConnection(), 
						"select heading from Co_Queries_Disjoint q, Mesh_Terms m " +
						" where q.queryId = " + queryId + " and q.leftId = m.id");
				
				String keywordQuery = heading1 + "[MeSH Terms] AND " + heading2 + "[MeSH Terms]";				

				System.out.println("QUERY " + queryId);

				QuerySession qs = em.spawnQuerySession(keywordQuery, -1);				
				Vector<Integer> resultsV = qs.searchPubMed(keywordQuery, "");	
				
				int numResults = resultsV.size();
				int querySize = qs._meshQuery._queryMeta.getTermScope().size();

				System.out.println(keywordQuery + " " + numResults + " " + querySize);
				
				if ((numResults >= 1000) && (querySize >= 10)) {
					querySet.add(new String(keywordQuery));
					outFP.println(keywordQuery);
					outFP.flush();
					System.out.println("\t ADDED");
				}
			}
		
			Iterator<String> iter = querySet.iterator();
			int i=0;
			while (iter.hasNext()) {
				queries[i++] = iter.next();
			}
			querySet = new HashSet<String>();
			
			while (querySet.size() < numQueries) {
				
				int queryId = generator.nextInt(486);
				
				String heading1 = DBUtils.getStringFromDB(DAO.openDBConnection(), 
						"select heading from Co_Queries_Overlapping q, Mesh_Terms m " +
						" where q.queryId = " + queryId + " and q.rightId = m.id ");

				String heading2 = DBUtils.getStringFromDB(DAO.openDBConnection(), 
						"select heading from Co_Queries_Overlapping q, Mesh_Terms m where q.queryId = " + queryId + " and q.leftId = m.id");
				
				if (heading1.equalsIgnoreCase(heading2)) {
					continue;
				}				
				String keywordQuery = heading1 + "[MeSH Terms] AND " + heading2 + "[MeSH Terms]";			

				QuerySession qs = em.spawnQuerySession(keywordQuery, -1);				
				Vector<Integer> resultsV = qs.searchPubMed(keywordQuery, "");	
				
				int numResults = resultsV.size();
				int querySize = qs._meshQuery._queryMeta.getTermScope().size();
				System.out.println(keywordQuery + " " + numResults + " " + querySize);
				
				if ( (numResults > 1000) && (querySize > 10) ) {
					querySet.add(new String(keywordQuery));
					outFP.println(keywordQuery);
					outFP.flush();
					System.out.println("\t ADDED");
				}
			}
			iter = querySet.iterator();
			i=0;
			while (iter.hasNext()) {
				queries[i++] = iter.next();
			}
			querySet = new HashSet<String>();
			
			while (querySet.size() < numQueries) {
				
				int queryId = generator.nextInt(486);
				
				String heading1 = DBUtils.getStringFromDB(DAO.openDBConnection(), 
						"select heading from Co_Queries_Overlapping q, Mesh_Terms m where q.queryId = " + queryId + " and q.rightId = m.id");

				String heading2 = DBUtils.getStringFromDB(DAO.openDBConnection(), 
						"select heading from Co_Queries_Overlapping q, Mesh_Terms m where q.queryId = " + queryId + " and q.leftId = m.id");
				
				if (heading1.equalsIgnoreCase(heading2)) {
					continue;
				}
				String keywordQuery = heading1 + "[MeSH Terms] OR " + heading2 + "[MeSH Terms]";			

				QuerySession qs = em.spawnQuerySession(keywordQuery, -1);				
				Vector<Integer> resultsV = qs.searchPubMed(keywordQuery, "");	
				
				int numResults = resultsV.size();
				int querySize = qs._meshQuery._queryMeta.getTermScope().size();
				System.out.println(keywordQuery + " " + numResults + " " + querySize);
				
				if ( (numResults > 1000) && (numResults < 200000) && (querySize > 10) ) {
					querySet.add(new String(keywordQuery));
					outFP.println(keywordQuery);
					outFP.flush();
					System.out.println("\t ADDED");
				}
			}
			iter = querySet.iterator();
			i=0;
			while (iter.hasNext()) {
				queries[i++] = iter.next();
			}

			DAO.closeDBConnection()	;

		} catch (SQLException sqle) {
			sqle.printStackTrace(System.out);
			
		} catch (CacheException e) {
			e.printStackTrace(System.out);
		}		
		
		return queries;
	}

	public static String[] readQueriesFromFile(String inFileName) throws IOException {

		BufferedReader inFP = new BufferedReader(new FileReader(inFileName));
		TreeSet<String> querySet = new TreeSet<String>();
		String query = null;
		while ((query = inFP.readLine()) != null) {
			querySet.add(query.trim());
		}
		String [] queries = querySet.toArray(new String[0]);
		return queries;
	}
	
	public static void main(String[] args)  {

		boolean generateUserStudyOutput = false;
		boolean runTopKPerformanceExperiment = true;
		boolean runSkylinePerformanceExperiment = false;
		boolean chooseRandomQueries = false;
		boolean readQueriesFromFile = false;

		int numRandomQueries = 50;
		
		try {
			Date ts = new Date();
			PrintStream outFP = new PrintStream("/Users/Dustin/Desktop/Skylines_Experiments/Results_" + ts.getTime() + ".csv");
			
			String [] queries = {
					"Autoimmune Diseases[MeSH Terms] AND Stress, Psychological[MeSH Terms]",
					"genetics[MeSH Terms] AND Parkinsonian Disorders[MeSH Terms]",
					"genetics[MeSH Terms] AND Lipid Metabolism[MeSH Terms]",
					//"Olfactory Mucosa[MeSH Terms]"
					//"olfactory pathways[MeSH terms] AND olfactory mucosa[MeSH terms]"
					//"olfactory pathways[MeSH terms] OR receptors, odorant[MeSH Terms]"
					"Nerve Growth Factors[MeSH terms] AND Stem Cells[MeSH terms]",
					//"receptors, g-protein-coupled[MeSH Terms] AND mice[MeSH Terms]",
					"Autoimmune Diseases[Mesh Terms] AND Pregnancy[MeSH Terms]",
					"Diabetes Mellitus[MeSH Terms] AND Myocardial Infarction[MeSH Terms]",
					"Lupus Erythematosus, Systemic[Mesh Terms] OR Antiphospholipid Antibodies[MeSH Terms]",
					//"Connective Tissue Diseases[Mesh Terms] AND Autoimmune Diseases[MeSH Terms]"
					};

			if (chooseRandomQueries) {
				queries = chooseRandomQueries(outFP, numRandomQueries);
				
			} else if (readQueriesFromFile) {
				queries = readQueriesFromFile("/Users/Dustin/Research/Ontorank/Skylines_Experiments/queries/Queries.csv");
			}

			if (generateUserStudyOutput) {
				outFP.println("score type,quality,rank,score,pmid,url");
				int K = 10;
				for (String query : queries) {
					outFP.println(query);
					runTopKOutputResults(outFP, query, K);
				}				
			}

			if (runTopKPerformanceExperiment) {
				boolean computeScoreDistribution = false;
				boolean generateAnnotationSets = false;
				boolean storeScores = true;
				outFP.println("action,K,score(ms),score(#calls),conditional(ms),conditional(#calls),balanced(ms),balanced(#calls)");							
				for (String query : queries) {
					runTopKExperiment(outFP, query, computeScoreDistribution, generateAnnotationSets, storeScores);
				}
			}
			
			if (runSkylinePerformanceExperiment) {
				
				outFP.println("query,num results,num batches,query size,");
				outFP.print("K,exact score(ms) - total,exact scores(ms) - average,exact score(#calls),");
				outFP.print("UB score(ms) - total,UB scores(ms) - average,UB score(#calls),# UB calls,# score calls,");
				outFP.print("exact conditional(ms) - total,exact conditional(ms) - average,exact conditional(#calls),");
				outFP.print("UB conditional(ms) - total,UB conditional(ms) - average,UB conditional(#calls),# UB calls,# score calls,");
				outFP.print("exact balanced(ms) - total,exact balanced(ms) - average,exact balanced(#calls),");
				outFP.print("UB balanced(ms) - total,UB balanced(ms) - average,UB balanced(#calls),# UB calls,# score calls\n");				
			
				for (String query : queries) {
					runSkylineExperiment(outFP, query);
				}
			}

			outFP.close();
		
		} catch (RemoteException re) {
			re.printStackTrace(System.out);
			
		} catch (CacheException ce) {
			ce.printStackTrace(System.out);
		
		} catch (IOException ioe) {
			ioe.printStackTrace(System.out);
		}
	}
}
