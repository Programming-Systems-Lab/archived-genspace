package org.geworkbench.components.skylinecontour.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import edu.columbia.cs.skys.data.MeshQueryMetaData;
import edu.columbia.cs.skys.data.MeshTerm;
import edu.columbia.cs.skys.db.DAO;
import edu.columbia.cs.skys.util.DBUtils;

public class MeshQuery {

	int _reqId;
	
	String _keywordQuery;
	String _searchMode = "";
	
	MeshQueryMetaData _queryMeta;
	
	String _webEnv;
	int _queryKey;
	int _numResults;
	int _batchSize;
	int _numBatches;
	
	int[] _results; // array of pmIds
	int[] _scores;
	int[] _ub;
	int[] _currentBatch;
	
	public MeshQuery() {}
	
	public MeshQuery(String keywordQuery) {
		_keywordQuery = keywordQuery;
		_queryMeta = new MeshQueryMetaData();
	}
	
	public MeshQuery(String keywordQuery, String searchMode) {
		_keywordQuery = keywordQuery;
		_searchMode = searchMode;
		_queryMeta = new MeshQueryMetaData();
	}
	
	public int getNumResults() {
		return _numResults;
	}
	
	public String getKeywordQuery() {
		return this._keywordQuery;
	}
	
	public MeshTerm[] getMeshTerms() {
		return this._queryMeta.getMeshTerms();
	}
	
	public int[] getResults() {
		return this._results;
	}
	
	public void setMeshTerms(Vector<Integer> termV) {
		_queryMeta.initMeshTerms(termV.size());
		
		Iterator<Integer> iter = termV.iterator();
		int i=0;
		while (iter.hasNext()) {
			MeshTerm currT = new MeshTerm(iter.next());
			currT.setTermScope(DAO.getMeshTermScopeFromDB(currT.getId()));
			currT.setNodeScope(DAO.getMeshNodeScopeFromDB(currT.getId()));
			_queryMeta.setMeshTerm(currT, i++);
		}
		_queryMeta.setScore();
	}
	
	public void setResults(Vector<Integer> articleV) {
		_results = new int[articleV.size()];
		Iterator<Integer> iter = articleV.iterator();
		
		int i=0;
		while (iter.hasNext()) {
			_results[i++] = iter.next();
		}
	}
	
	public void setResults(int[] articleArray, int batchNum) {
		_currentBatch = new int[articleArray.length];
		for (int i=0; i<articleArray.length; i++) {
			if (articleArray[i] == 0){
				break;
			}
			int idx = (_batchSize * batchNum) + i;
			_results[idx] = articleArray[i];
			_currentBatch[i] = articleArray[i];
		}
	}

	public String toString() {
		StringBuffer foo = new StringBuffer(_keywordQuery + "[");
		foo.append(_queryMeta.toString());
		foo.append("]");
		return foo.toString();
	}
	
	public String toStringFull() {
		StringBuffer foo = new StringBuffer(_keywordQuery + "[");
		foo.append(_queryMeta.toStringFull());
		foo.append("]");
		return foo.toString();
	}
	
	public int storeResultMetaDataInDB(int reqId) {
		
		if (reqId <= 0) {
			try {
				DAO.openDBConnection();
				
				_reqId = DBUtils.getIntFromDB(DAO._conn, "select reqId_SEQ.nextval from dual");

				String query = "insert into Entrez_Request(reqId, query) values (?,?)";
				PreparedStatement pstmt = DAO._conn.prepareStatement(query);
				pstmt.setInt(1, _reqId);
				pstmt.setString(2, _keywordQuery);
				pstmt.executeUpdate();

				query = "insert into Entrez_Query(reqId, meshId) values (?, ?)";
				pstmt = DAO._conn.prepareStatement(query);
				pstmt.setInt(1, _reqId);
				for (int i=0; i<this._queryMeta.getMeshTerms().length; i++) {
					pstmt.setInt(2, (_queryMeta.getMeshTerms())[i].getId());
					//System.out.println(_reqId + "," + (_queryMeta.getMeshTerms())[i].getId());
					try {
						pstmt.executeUpdate();
					} catch (SQLException sqle) {
						//System.out.println("Duplicate meshId " + (_queryMeta.getMeshTerms())[i].getId() + " for request " + _reqId);
					}
				}
				
				DAO.closeDBConnection();
				
			} catch (SQLException sqle) {
				sqle.printStackTrace(System.out);
			}
		} else {
			_reqId = reqId;
		}
		return _reqId;
	}	
	
	
	public void storeResultInDB(int batchNum) {
		
		try {
			DAO.openDBConnection();
			
			String query = "insert into Entrez_Result(reqId, pmId, batch) values (?, ?, ?)";
			PreparedStatement pstmt = DAO._conn.prepareStatement(query);
			pstmt.setInt(1, _reqId);
			pstmt.setInt(3, batchNum);
			int startIdx = batchNum * this._batchSize;
			int endIdx = Math.min(startIdx + _batchSize, _results.length);
			for (int i=startIdx; i<endIdx; i++) {
				pstmt.setInt(2, _results[i]);
				pstmt.executeUpdate();
			}
			
			DAO.closeDBConnection();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.out);
		}
	}
}
