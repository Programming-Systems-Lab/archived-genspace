package org.geworkbench.components.skylinecontour.query;

/*
 * 
 * Author: Julia Stoyanovich (jds2109@columbia.edu) 
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.columbia.cs.skys.cache.ICache;
import edu.columbia.cs.skys.data.MeshQueryMetaData;
import edu.columbia.cs.skys.data.Point;
import edu.columbia.cs.skys.util.Misc;

public class QuerySession {

	MeshQuery _meshQuery;
	MeshQuery[] _meshQueryTerms;
	
	QueryManager _qm;
	private int _currBatch = -1;
	private int _lastReadBatch = -1;
	private boolean _outstandingBatches = true;
	private Map<Integer, Point<?,?>[]> _batchResults = new HashMap<Integer, Point<?,?>[]>();
	private Logger _logger = Logger.getLogger(QuerySession.class.getName());
	private int _maxBatches = -1;
	
	public QuerySession(QueryManager qm) {
		_qm = qm;
	}
	
	public int getSessionId() {
		return _meshQuery._reqId;
	}
	
	public MeshQuery get_meshQuery() {
		return _meshQuery;
	}

	public int getReqId() {
		return _meshQuery._reqId;
	}
	
	public MeshQueryMetaData getQueryMeta() {
		return _meshQuery._queryMeta;
	}
	
	public MeshQueryMetaData[] getQueryTermMeta() {
		MeshQueryMetaData[] arr = new MeshQueryMetaData[_meshQueryTerms.length];
		for (int i=0; i<arr.length; i++) {
			arr[i] = _meshQueryTerms[i]._queryMeta;
		}
		return arr;
	}
	
	public int getQuerySize() {
		if (getQueryMeta() == null) {
			return 0;
		}
		if (getQueryMeta().getTermScope() == null) {
			return 0;
		}
		return getQueryMeta().getTermScope().size();
	}
	
	public Vector<Integer> searchPubMed(String keywordQuery, String searchMode) {
		
		Vector<Integer> resultVector = new Vector<Integer>();
		
		try {
			String db = "pubmed";
			
			// execute esearch; consume 'webEnv', 'queryKey' and 'count'
			String searchURL = QueryManager.EUTILS_URL + "/esearch.fcgi?" + QueryManager.EID;
			searchURL += "&db=" + db;
			
			/*
			if (keywordQuery.contains("AND")) {
				String[] queryTerms = keywordQuery.split("AND");
				searchURL += "&term=" + Misc.encode(queryTerms[0].trim()) + "[MH]";
				for (int i=1; i<queryTerms.length; i++) {
					searchURL += Misc.encode(" AND " + queryTerms[i].trim()) + "[MH]";
				}
			} else {
				searchURL += "&term=" + Misc.encode(keywordQuery);
			}
			*/
			searchURL += "&term=" + Misc.encode(keywordQuery);
			
			if (searchMode.length() > 0) {
				searchURL += "[" + searchMode + "]";
			}
			searchURL += "&retmode=xml";
			searchURL += "&sort=pub+date";
			searchURL += "&usehistory=y";
			
			int resultCount = 0;
			int queryKey = -1;
			String webEnv = null;
			
			URL entrez = new URL(searchURL);
			_logger.fine(searchURL);
			
			BufferedReader inURL = new BufferedReader(new InputStreamReader(entrez.openStream()));
			
			Pattern countPattern = Pattern.compile("(<Count>)(.+)(</Count>)", Pattern.CASE_INSENSITIVE);
			Pattern queryKeyPattern = Pattern.compile("(<QueryKey>)([0-9]+)(</QueryKey>)", Pattern.CASE_INSENSITIVE);
			Pattern webEnvPattern = Pattern.compile("(<WebEnv>)(.+)(</WebEnv>)", Pattern.CASE_INSENSITIVE);
			
			String inputLine;
			
			boolean foundTotalCount = false;
			while ((inputLine = inURL.readLine()) != null) {
				
				Matcher countMatcher = countPattern.matcher(inputLine);
				while (countMatcher.find()) {
					if (foundTotalCount == false) {
						resultCount = Integer.parseInt(countMatcher.group(2));
						foundTotalCount = true;
					} 
				}

				Matcher queryKeyMatcher = queryKeyPattern.matcher(inputLine);
				while (queryKeyMatcher.find()) {
					queryKey = Integer.parseInt(queryKeyMatcher.group(2));
				}
				Matcher webEnvMatcher = webEnvPattern.matcher(inputLine);
				while (webEnvMatcher.find()) {
					webEnv = webEnvMatcher.group(2);
					break; // done
				}
			}
			inURL.close();

			// now that search results are computed and stored on the server, retrieve PMIDs 
			// of results in batches
			int retStart = 0;
			int batchSize = 3000;

			Pattern idPattern = Pattern.compile("(<Id>)([0-9]+)(</Id>)", Pattern.CASE_INSENSITIVE);
			
			String fetchURL = searchURL;
			fetchURL += "&retmax="+batchSize;
			fetchURL += "&query_key=" + queryKey;
			fetchURL += "&WebEnv=" + webEnv;
			
			while (retStart <= resultCount) {
				
				entrez = new URL(fetchURL + "&retstart=" + retStart);
				_logger.fine(entrez.toString());
				
				inURL = new BufferedReader(new InputStreamReader(entrez.openStream()));
				
				while ((inputLine = inURL.readLine()) != null) {
					Matcher idMatcher = idPattern.matcher(inputLine);
					if (idMatcher.find()) {
						resultVector.add(new Integer(Integer.parseInt(idMatcher.group(2))));
					}	
				}
				retStart += batchSize;
			}
			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return resultVector;
	}
	
	static void searchPubMed(MeshQuery mq) {
		
		try {
			String db = "pubmed";
			
			// execute esearch; consume 'webEnv', 'queryKey' and 'count'
			String searchURL = QueryManager.EUTILS_URL + "/esearch.fcgi?" + QueryManager.EID;
			searchURL += "&db=" + db;
			searchURL += "&term=" + Misc.encode(mq._keywordQuery);
			if (mq._searchMode.length() > 0) {
				searchURL += "[" + mq._searchMode + "]";
			}
			searchURL += "&retmode=xml";
			searchURL += "&sort=pub+date";
			searchURL += "&usehistory=y";
			System.out.println(searchURL);
			
			URL entrez = new URL(searchURL);
			BufferedReader inURL = new BufferedReader(new InputStreamReader(entrez.openStream()));
			
			Pattern countPattern = Pattern.compile("(<Count>)(.+)(</Count>)", Pattern.CASE_INSENSITIVE);
			Pattern queryKeyPattern = Pattern.compile("(<QueryKey>)([0-9]+)(</QueryKey>)", Pattern.CASE_INSENSITIVE);
			Pattern webEnvPattern = Pattern.compile("(<WebEnv>)(.+)(</WebEnv>)", Pattern.CASE_INSENSITIVE);
			
			String inputLine;
			
			boolean foundTotalCount = false;
			while ((inputLine = inURL.readLine()) != null) {
				
				Matcher countMatcher = countPattern.matcher(inputLine);
				while (countMatcher.find()) {
					if (foundTotalCount == false) {
						mq._numResults = Integer.parseInt(countMatcher.group(2));
						foundTotalCount = true;
					} 
				}

				Matcher queryKeyMatcher = queryKeyPattern.matcher(inputLine);
				while (queryKeyMatcher.find()) {
					mq._queryKey = Integer.parseInt(queryKeyMatcher.group(2));
				}

				Matcher webEnvMatcher = webEnvPattern.matcher(inputLine);
				while (webEnvMatcher.find()) {
					mq._webEnv = webEnvMatcher.group(2);
					break; // done
				}
			}
			inURL.close();

			mq._results = new int[mq._numResults];
			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	static int[] retrievePubMedBatch(MeshQuery mq, int batchNum) {
		
		//Vector<Integer> resultVector = new Vector<Integer>();
		int[] resultArray = new int[mq._batchSize];
		try {
			String db = "pubmed";
			
			String searchURL = QueryManager.EUTILS_URL + "/esearch.fcgi?" + QueryManager.EID;
			searchURL += "&db=" + db;
			searchURL += "&term=" + Misc.encode(mq._keywordQuery);
			if (mq._searchMode.length() > 0) {
				searchURL += "[" + mq._searchMode + "]";
			}
			searchURL += "&retmode=xml";
			searchURL += "&sort=pub+date";
			searchURL += "&usehistory=y";
			searchURL += "&retstart=" + (mq._batchSize * batchNum);
			searchURL += "&retmax=" + mq._batchSize;
			System.out.println(searchURL);
			
			URL entrez = new URL(searchURL);
			BufferedReader inURL = new BufferedReader(new InputStreamReader(entrez.openStream()));
			
			Pattern idPattern = Pattern.compile("(<Id>)([0-9]+)(</Id>)", Pattern.CASE_INSENSITIVE);
			String inputLine;
			
			inURL = new BufferedReader(new InputStreamReader(entrez.openStream()));	
			int i=0;
			while ((inputLine = inURL.readLine()) != null) {	
				Matcher idMatcher = idPattern.matcher(inputLine);	
				if (idMatcher.find()) {
					//resultVector.add(new Integer(Integer.parseInt(idMatcher.group(2))));
					resultArray[i++] = Integer.parseInt(idMatcher.group(2));
				}	
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return resultArray;
	}
	
	public Vector<Integer> searchMesh(String keywordQuery, String searchMode) {
		Vector<Integer> resultVector = new Vector<Integer>();
		
		try {
			String db = "mesh";
			
			String searchURL = QueryManager.EUTILS_URL + "/esearch.fcgi?" + QueryManager.EID;
			searchURL += "&db=" + db;
			searchURL += "&term=" + Misc.encode(keywordQuery);
			if (searchMode.length() > 0) {
				searchURL += "[" + searchMode + "]";
			}
			searchURL += "&retmode=xml";
			
			//int resultCount = 0;
			URL entrez = new URL(searchURL);
			BufferedReader inURL = new BufferedReader(new InputStreamReader(entrez.openStream()));
			
			Pattern countPattern = Pattern.compile("(<Count>)(.+)(</Count>)", Pattern.CASE_INSENSITIVE);
			Pattern idPattern = Pattern.compile("(<Id>)([0-9]+)(</Id>)", Pattern.CASE_INSENSITIVE);
			
			String inputLine;
			
			boolean foundTotalCount = false;
			while ((inputLine = inURL.readLine()) != null) {
				
				Matcher countMatcher = countPattern.matcher(inputLine);
				while (countMatcher.find()) {
					if (foundTotalCount == false) {
						//resultCount = Integer.parseInt(countMatcher.group(2));
						foundTotalCount = true;
					} 
				}

				Matcher idMatcher = idPattern.matcher(inputLine);
				if (idMatcher.find()) {
					Integer id = Integer.parseInt(idMatcher.group(2).substring(2));
					resultVector.add(id);
				}	
			}
			inURL.close();
			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return resultVector;
	}

	public void initMeshQuery(String keywordQuery, int reqId) {
		
		_meshQuery = new MeshQuery(keywordQuery);	
		
		Vector<Integer> meshResults = searchMesh(keywordQuery, "");
		if (meshResults.size() == 0) {
			// the query did not match any MeSH terms, run the query against MeSH in smaller sets of terms
			meshResults = runSubQueries(keywordQuery);
		}
		_meshQuery.setMeshTerms(meshResults);
		Integer[] resultsArray = meshResults.toArray(new Integer[0]);
		_meshQueryTerms = new MeshQuery[meshResults.size()];
		for (int i=0; i<_meshQueryTerms.length; i++) {
			_meshQueryTerms[i] = new MeshQuery(keywordQuery);
			Vector<Integer> partialResults = new Vector<Integer>();
			partialResults.add(resultsArray[i]);
			_meshQueryTerms[i].setMeshTerms(partialResults);
		}
		
		// search PubMed and retrieve result meta-data -> _meshQuery
		searchPubMed(_meshQuery);
		_meshQuery.storeResultMetaDataInDB(reqId);
		
		// break up results into batches
		if (_meshQuery._numResults <= QueryManager.BATCH_SIZE * 1.5) {
			// process everything as a single batch
			_meshQuery._batchSize = _meshQuery._numResults;
			_meshQuery._numBatches = 1;
		} else {
			_meshQuery._batchSize = QueryManager.BATCH_SIZE;
			_meshQuery._numBatches = _meshQuery._numResults / _meshQuery._batchSize + (_meshQuery._numResults%_meshQuery._batchSize == 0 ? 0:1);
		}
	}
	
	// Runs a mesh query for each keyword of the keywordQuery.
	// In the future, generalize this to run on windows of length (queryKeywords.length - 1) through 1
	// and stop as soon as there are results.
	public Vector<Integer> runSubQueries(String keywordQuery) {
 		String[] queryKeywords = new String[1];
		if (keywordQuery.contains("AND")) {
			queryKeywords = keywordQuery.split("AND");		
		} else if ( (keywordQuery.contains("OR"))) {
			queryKeywords = keywordQuery.split("OR");		
		} else {
			queryKeywords = keywordQuery.split(" ");
		}
		Vector<Integer> resultsVector = new Vector<Integer>();
		for (int i=0; i<queryKeywords.length; i++) {
			resultsVector.addAll(searchMesh(queryKeywords[i], ""));
		}
		return resultsVector;
	}
	
	protected int getNumBatches() {
		return _meshQuery._numBatches;
	}
	
	public boolean isLastBatch() {
		return (_lastReadBatch == getNumBatches() - 1);
	}
	
	private void processBatch(MeshQuery mq, int batchNum) {
		
		// store the current batch in the cache
		try {
			
			// compute scores for the batch        
	        _logger.info(" Calling computeScore()...");
	        Point<?,?>[] batchResults = _qm._cache.computeScore(mq._currentBatch, mq._reqId, ICache.Score_Type.SPECIFICITY, ICache.Optimization_Type.NONE);
	        _batchResults.put(new Integer(batchNum), batchResults);
	        _logger.info(" ... computeScore() done");
	        
	        synchronized(this) {
	        	_batchResults.put(batchNum, batchResults);
	        	_currBatch = batchNum;
	        }

		} catch (RemoteException re) {
			re.printStackTrace(System.out);
		}		
	}
	
	/**
	 * Polls for the next batch of data points. 
	 * @return a set of points, which can be empty (no points currently available) or null (no more points available)
	 */
	public Point<?,?>[] readNextBatch() {
		
		Point<?,?>[] nextBatch = new Point<?,?>[0];
		synchronized(this) {
			if (_currBatch == _lastReadBatch && !_outstandingBatches) {
				_logger.info("read returning null - no more queries");
				nextBatch = null;
			}
			else if (_currBatch > _lastReadBatch) {
				_lastReadBatch++;
				if (_batchResults.containsKey(_lastReadBatch)) {
					_logger.info("read returning batch " + _lastReadBatch);
					nextBatch = _batchResults.get(_lastReadBatch);
					_batchResults.remove(_lastReadBatch);
				} 
			} else {
				_logger.info("read returning empty list");
			}
		} 
		return nextBatch;
	}
	
	
	public void runQuery() {
		Thread thread = new Thread(new QueryRunner());
		thread.start();
	}

    private synchronized void setOutstandingBatches(boolean flag) {
    	_outstandingBatches = flag;
    }

    /*
     // works, but unused
	private void initResultsFromDB() {
		try {
			DAO.openDBConnection();

			String query = "select pmid from Entrez_Result where reqId = " + _meshQuery._reqId + " order by pmid";
		    Statement st = DAO._conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    int i=0;
		    while (rs.next()) {
		        _meshQuery._results[i++] = rs.getInt(1);
		    }
		    rs.close();
		    st.close();

		    DAO.closeDBConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.out);
		}
	}
	*/
    
	public void runQueryStoreResult() {
		
		if (_meshQuery._numResults == 0) {
			return;
		}
		for (int batchNum=0; batchNum * _meshQuery._batchSize <= _meshQuery._numResults; batchNum++) {
			
			System.out.println("Processing batch " + batchNum);
			System.out.println((new Date()).toString() + " retrievePubMedBatch() start");

			int[] pubmedResults = retrievePubMedBatch(_meshQuery, batchNum);
			System.out.println((new Date()).toString() + " retrievePubMedBatch() done");

			_meshQuery.setResults(pubmedResults, batchNum);
			
			System.out.println((new Date()).toString() + " storeResultInDB() start");			
			_meshQuery.storeResultInDB(batchNum);	
			System.out.println((new Date()).toString() + " storeResultInDB() done");						
		}		
	}
	
	
	/**
	 * Set the maximum number of batches to process
	 * @param maxBatches maximum number of batches to process
	 */
	public void setMaxBatches(int maxBatches) {
		_maxBatches = maxBatches;
	}


	/**
	 * Internal Runner class to handle pubmed lookups.
	 *
	 * @author William Mee <wjm2107@cs.columbia.edu>
	 * Copyright (c) 2008 The Trustees of Columbia University
	 */
	class QueryRunner implements Runnable {

		@Override
		public void run() {
			
			for (int batchNum=0; batchNum * _meshQuery._batchSize <= _meshQuery._numResults && batchNum != _maxBatches; batchNum++) {
				
				_logger.info("Processing batch " + batchNum);				
				int[] pubmedResults = retrievePubMedBatch(_meshQuery, batchNum);
				_logger.info("retrievePubMedBatch() done");
	
				_meshQuery.setResults(pubmedResults, batchNum);
				
				processBatch(_meshQuery, batchNum);			
			}
			setOutstandingBatches(false);
		}
	}
	
}
