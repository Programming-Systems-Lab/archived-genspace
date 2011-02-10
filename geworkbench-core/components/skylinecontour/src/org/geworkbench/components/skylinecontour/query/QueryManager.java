package org.geworkbench.components.skylinecontour.query;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;

import edu.columbia.cs.skys.cache.CacheException;
import edu.columbia.cs.skys.cache.ICache;
import edu.columbia.cs.skys.db.DAO;
import edu.columbia.cs.skys.util.DBUtils;

public class QueryManager {

	// Entrez-related constants
	public static final String EUTILS_URL = "http://www.ncbi.nlm.nih.gov/entrez/eutils";
	public static final String EID = "tool=BioRank&email=jds1@cs.columbia.edu";
	public static final int BATCH_SIZE = 3000;
		
	// performance counters
	public static int _termCooccurLookups = 0;
	public static int _interestingLookups = 0;
	public static int _getChildrenLookups = 0;
	public static int _setUnion = 0;
	public static int _setIntersect = 0;
	
	// cache-related
	private static final String _cacheServiceHost = "elmo.cs.columbia.edu";
	protected ICache _cache; 
	protected Logger _logger = Logger.getLogger(QueryManager.class.getName());
	
	public QueryManager() throws CacheException{
		this(false);
	}
	
	public QueryManager(boolean init) throws CacheException {
		try {
			_cache = lookupCacheService(_cacheServiceHost);
			_logger.info(" cache found");

			if (init && !_cache.isInitialized()) {
				_logger.info(" calling init()");
				_cache.init();
				_logger.info(" init() finished");
			}
		} catch (RemoteException e) {
			throw new CacheException(e);
		} catch (NotBoundException e) {
			throw new CacheException("RMI Service not bound", e);
		}
	}
	
	public ICache getCache() {
		return _cache;
	}

	public void setCache(ICache _cache) {
		this._cache = _cache;
	}

	public QueryManager(int reqId) throws CacheException {
		try {
			_cache = lookupCacheService(_cacheServiceHost);
			System.out.println((new Date()).getTime() + " cache found");
			
			if (!_cache.isInitialized() && (reqId > -1)) {
				System.out.println((new Date()).getTime() + " calling init(" + reqId + ")");
				_cache.init(reqId);
				System.out.println((new Date()).getTime() + " init() finished");
			}
		} catch (RemoteException e) {
			throw new CacheException(e);
		} catch (NotBoundException e) {
			throw new CacheException("RMI Service not bound", e);
		}
	}

	public QueryManager(String pubDate) throws CacheException {
		try {
			_cache = lookupCacheService(_cacheServiceHost);
			System.out.println((new Date()).getTime() + " cache found");
			
			if (!_cache.isInitialized() && (pubDate.trim().length() > 0)) {
				System.out.println((new Date()).getTime() + " calling init(" + pubDate + ")");
				_cache.init(pubDate);
				System.out.println((new Date()).getTime() + " init() finished");
			}
		} catch (RemoteException e) {
			throw new CacheException(e);
		} catch (NotBoundException e) {
			throw new CacheException("RMI Service not bound", e);
		}
	}

	public QueryManager(String startPubDate, String endPubDate) throws CacheException {
		try {
			_cache = lookupCacheService(_cacheServiceHost);
			System.out.println((new Date()).getTime() + " cache found");
			
			if (!_cache.isInitialized() && ( (startPubDate.trim().length() > 0) || (endPubDate.trim().length() > 0))) {
				System.out.println((new Date()).getTime() + " calling init(" + startPubDate + " to " + endPubDate + ")");
				_cache.init(startPubDate, endPubDate);
				System.out.println((new Date()).getTime() + " init() finished");
			}
		} catch (RemoteException e) {
			throw new CacheException(e);
		} catch (NotBoundException e) {
			throw new CacheException("RMI Service not bound", e);
		}
	}
	
	private ICache lookupCacheService(String cacheServiceHost) throws RemoteException, NotBoundException {
        String name = "Cache";
        Registry registry = LocateRegistry.getRegistry(cacheServiceHost);
        ICache cache = (ICache) registry.lookup(name);
        _logger.fine("Pinging cache on " + cacheServiceHost + "...");
        _logger.fine("Ping: " + cache.ping());   
        return cache;
	}
	
	public QuerySession spawnQuerySession(String keywordQuery, int reqId) throws CacheException {		
		QuerySession qs = new QuerySession(this);
		qs.initMeshQuery(keywordQuery, reqId);
		
		// ship the query meta-data over to the cache
        try {
			_cache.setQueryMetaData(qs.getSessionId(), qs.getQueryMeta());
			// System.out.println(" +++ Conditional score for the query is " + qs.getQueryMeta().getConditionalScore());
			_cache.setQueryTermMetaData(qs.getSessionId(), qs.getQueryTermMeta());
		} catch (RemoteException e) {
			throw new CacheException(e);
		}		
		return qs;
	}
	
	public static void runAndInitFiveQueries() throws CacheException {
	
		String [] queries = {
				"receptors, g-protein-coupled[MeSH Terms] AND mice[MeSH Terms]",
				"Autoimmune Diseases[Mesh Terms] AND Pregnancy[MeSH Terms]",
				"Diabetes Mellitus[MeSH Terms] AND Myocardial Infarction[MeSH Terms]",
				"Lupus Erythematosus, Systemic[Mesh Terms] OR Antiphospholipid Antibodies[MeSH Terms]",
				"Connective Tissue Diseases[Mesh Terms] AND Autoimmune Diseases[MeSH Terms]"
				};
		
		int [] reqIdArray  = new int[queries.length]; // 836, 837, 838, 839, 840
		
		for (int i=0; i<queries.length; i++) {
		
			reqIdArray[i] = runQueryStoreResult(queries[i]);
			System.out.println(reqIdArray[i] + " " + queries[i]);
		}

		for (int i=0; i<reqIdArray.length; i++) {
			readRequestIntoCache(reqIdArray[i]);					
		}
	}
	
	public static void initFiveQueries() throws CacheException {
		int [] reqIdArray  = {836}; //, 837, 838, 839, 840};
		
		for (int i=0; i<reqIdArray.length; i++) {
			readRequestIntoCache(reqIdArray[i]);					
		}
	}
	
	public static void runAndInitBalancedQueries() throws CacheException {
		
		try {
			
			BufferedWriter outFP = new BufferedWriter (new FileWriter("//Users/Dustin/Desktop/queries.csv"));

			outFP.write("reqId,queryId,term1,term2,type,query,query size,number of results\n");

			// choose 50 random ids from between 1 and 7471, build AND queries
			HashSet<Integer> queryIds = new HashSet<Integer>();
			HashSet<Integer> requestIds = new HashSet<Integer>();
			
			Random generator = new Random();
			while (queryIds.size() < 50) {
				int rand = generator.nextInt(7470);
				queryIds.add(1 + rand);
			}

			for (int queryId : queryIds) {
				
				String term1 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select leftid from Co_Queries_Disjoint where queryId = " + queryId);
				String term2 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select rightid from Co_Queries_Disjoint where queryId = " + queryId);
				String heading1 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select heading from mesh_terms where id = '"  + term1 + "'");
				String heading2 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select heading from mesh_terms where id = '"  + term2 + "'");
				
				int reqId = runQueryStoreResult(heading1 + "[MeSH Terms] AND " + heading2 + "[MeSH Terms]");
				requestIds.add(new Integer(reqId));
				
				int numResults = DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from Entrez_Result where reqId = "  + reqId);
				int querySize = DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from mesh_tree_size where id = '" + term1 + "'");
				querySize += DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from mesh_tree_size where id = '" + term2 + "'");
				
				outFP.write(reqId + "," + queryId + "," + term1 + "," + term2 + 
								   ",AND,'" + heading1 + "[MeSH Terms] AND " + heading2 + "[MeSH Terms]'," + 
								   querySize + "," + numResults + "\n");
			}
	
			// re-set the query ids, now look for 50 queries from Co_Queries_Overlapping
			queryIds = new HashSet<Integer>();
			
			while (queryIds.size() < 50) {
				int rand = generator.nextInt(486);
				queryIds.add(1 + rand);
			}

			for (int queryId : queryIds) {
				
				String term1 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select leftid from Co_Queries_Overlapping where queryId = " + queryId);
				String term2 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select rightid from Co_Queries_Overlapping where queryId = " + queryId);
				String heading1 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select heading from mesh_terms where id = '"  + term1 + "'");
				String heading2 = DBUtils.getStringFromDB(DAO.openDBConnection(), "select heading from mesh_terms where id = '"  + term2 + "'");
				
				int reqId = runQueryStoreResult(heading1 + "[MeSH Terms] AND " + heading2 + "[MeSH Terms]");
				requestIds.add(new Integer(reqId));
				
				int numResults = DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from Entrez_Result where reqId = "  + reqId);
				int querySize = DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from mesh_tree_size where id = '" + term1 + "'");
				querySize += DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from mesh_tree_size where id = '" + term2 + "'");
				
				outFP.write(reqId + "," + queryId + "," + term1 + "," + term2 + 
								   ",AND,'" + heading1 + "[MeSH Terms] AND " + heading2 + "[MeSH Terms]'," + 
								   querySize + "," + numResults  + "\n");
				
				if (numResults <= 50000 ) {
					// if the AND gave 5000 results or fewer -- try an OR
					reqId = runQueryStoreResult(heading1 + "[MeSH Terms] OR " + heading2 + "[MeSH Terms]");
					requestIds.add(new Integer(reqId));
					
					numResults = DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from Entrez_Result where reqId = "  + reqId);
					querySize = DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from mesh_tree_size where id = '" + term1 + "'");
					querySize += DBUtils.getIntFromDB(DAO.openDBConnection(), "select count(*) from mesh_tree_size where id = '" + term2 + "'");
					
					outFP.write(reqId + "," + queryId + "," + term1 + "," + term2 + 
									   ",OR,'" + heading1 + "[MeSH Terms] OR " + heading2 + "[MeSH Terms]'," + 
									   querySize + "," + numResults + "\n");
				}
			}
			
			for (int reqId : requestIds) {
				readRequestIntoCache(reqId);					
			}
			DAO.closeDBConnection()	;
			
			outFP.close();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.out);
			
		} catch (IOException e) {
			
			e.printStackTrace(System.out);
		}
	}
	
	public static int runQueryStoreResult(String query) throws CacheException {
		
		QueryManager qm = new QueryManager(-1);	
		QuerySession qs = qm.spawnQuerySession(query, -1);
		qs.runQueryStoreResult();	
		return qs.getReqId();
	}
	
	public static QueryManager readRequestIntoCache(int reqId) throws CacheException {
		
		QueryManager qm = new QueryManager(reqId);	
		return qm;
	}
	
	public static QueryManager readRecentIntoCache(String pubDate) throws CacheException {
		QueryManager qm = new QueryManager(pubDate);
		return qm;
	}
	
	
	public static QueryManager readRecentIntoCache(String startPubDate, String endPubDate) throws CacheException {
		QueryManager qm = new QueryManager(startPubDate, endPubDate);
		return qm;
	}
		
	public static void main(String[] args) throws RemoteException {
		
		try {
			//runAndInitFiveQueries();
			//runAndInitBalancedQueries();
			//initFiveQueries();
			readRecentIntoCache("2000-01-01");
			
		} catch (CacheException e) {
			e.printStackTrace(System.out);		
		}
	}
}

