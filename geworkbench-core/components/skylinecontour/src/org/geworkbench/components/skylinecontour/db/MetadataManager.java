package org.geworkbench.components.skylinecontour.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.columbia.cs.skys.Config;
import edu.columbia.cs.skys.db.DAO;

/**
 * $Id: MetadataManager.java,v 1.4 2009-11-18 16:57:42 jiz Exp $
 *
 * Manages collectionf of metadata.
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class MetadataManager {
	
	private static MetadataManager _instance = new MetadataManager();
	private Connection _conn;
	private int _maxCacheSize = 2000;	// the maximum size of the cache

	/*
	private String BASE_QUERY = "SELECT " +
	"articles.pseudoid PSEUDOID, articles.PMID PMID, articles.TITLE TITLE, articles.PUBDATE PUBDATE, articles.JOURNALID JOURNALID, " +
	"ENTREZ_AUTHOR.AUTHOR_FIRST AFIRST, ENTREZ_AUTHOR.AUTHOR_LAST ALAST " +
	"FROM " +
	"(SELECT ROWNUM pseudoid, PMID, TITLE, PUBDATE, JOURNALID from ENTREZ_ARTICLE where JOURNALID IS NOT NULL) articles " +
	"RIGHT OUTER JOIN ENTREZ_ARTICLE_AUTHOR " +
	"ON articles.PMID = ENTREZ_ARTICLE_AUTHOR.PMID " +
	"RIGHT OUTER JOIN ENTREZ_AUTHOR " +
	"ON ENTREZ_ARTICLE_AUTHOR.AID = ENTREZ_AUTHOR.AID " +
	"WHERE articles.pseudoid ";
	*/

	private static final String ARTICLE_QUERY = "SELECT " + 
			" ENTREZ_ARTICLE.PMID PMID, ENTREZ_ARTICLE.ARTICLETITLE TITLE, ENTREZ_ARTICLE.PUBDATE PUBDATE, " +
			" ENTREZ_ARTICLE.JOURNALTITLE JTITLE, ENTREZ_AUTHOR.AUTHOR_FIRST AFIRST, ENTREZ_AUTHOR.AUTHOR_LAST ALAST " +
			" from  ENTREZ_ARTICLE, ENTREZ_AUTHOR, ENTREZ_ARTICLE_AUTHOR "+ 
			" where ENTREZ_ARTICLE.PMID = ? " +
			" and   ENTREZ_ARTICLE.PMID = ENTREZ_ARTICLE_AUTHOR.PMID (+) " +
			" and   ENTREZ_ARTICLE_AUTHOR.AID = ENTREZ_AUTHOR.AID (+) ";
	
	private static final String MESH_NODES = "SELECT m.ID ID, m.HEADING HEADING " +
		"FROM Entrez_Article_Mesh am, MeSH_Terms m " +
		"WHERE  m.ID = am.MESHID " + 
		"AND am.PMID = ?";
	
	// cache of document metadata
	private static Map<Integer,ArticleMetadata> _metadataCache = new HashMap<Integer, ArticleMetadata>();
	
	private MetadataManager() {
		//conn = DriverManager.getConnection("jdbc:oracle:thin:@tango.cs.columbia.edu:1521:jds", "snp","snp");
		_conn = DAO.openDBConnection();
		//DriverManager.getConnection("jdbc:oracle:thin:@tango.cs.columbia.edu:1521:jds", "snp","snp");
	}
	
	public static MetadataManager getInstance() {
		return _instance;
	}
	
	public void checkConnection() throws SQLException {
		if (_conn.isClosed()) {
			_conn = DAO.openDBConnection();
		}
		
	}
	
	public ArticleMetadata getMetadataById(int id) throws SQLException {
		ArticleMetadata cachedMetadata = _metadataCache.get(""+id);
		if (cachedMetadata != null) {
			return cachedMetadata;
		}
		checkConnection();
		PreparedStatement stmt = _conn.prepareStatement(ARTICLE_QUERY);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		ArticleMetadata md = metadataFromRS(rs);
		rs.close();
		stmt.close();
		if (md!=null) {
			if (Config.DEBUG_MODE) {
				md.setMeshNodes(getMeshNode(id));
			}
			return md;			
		} else {
			return null;
		}
	}
	
	public ArticleMetadata [] getMetadataList(String csList) throws SQLException {
		int lastIdx = csList.length() - 1;
		if (csList.charAt(lastIdx) == ',') {
			csList = csList.substring(0,lastIdx);
		}
		String [] ids = csList.split(",");
		int [] idList = new int[ids.length];
		int idx = 0;
		for (String idString: ids) {
			int id = -1;
			try {
				id=Integer.parseInt(idString);
			} catch (Exception e) {
			}
			idList[idx++] = id;			
		}
		return getMetadataList(idList);
	}
	
	private List<MeshNode> getMeshNode(int id) throws SQLException {
		List<MeshNode> meshNodes = new ArrayList<MeshNode>();
		checkConnection();
		PreparedStatement stmt = _conn.prepareStatement(MESH_NODES);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String meshId = rs.getString("ID");
			String heading = rs.getString("HEADING");
			MeshNode node = new MeshNode(heading, meshId);
			meshNodes.add(node);
		}
		rs.close();
		stmt.close();
		return meshNodes;
		
	}
	
	/**
	 * Gets a list of DocMetadata objects for associated pubmed IDs
	 * @param ids
	 * @return a list of DocMetadatat for each index, or null if the id did not correspond to data.
	 * @throws SQLException
	 */
	public ArticleMetadata [] getMetadataList(int [] ids) throws SQLException {
		List<ArticleMetadata> list = new ArrayList<ArticleMetadata>();
		checkConnection();
		PreparedStatement stmt = _conn.prepareStatement(ARTICLE_QUERY);
		//int idx = 0;
		ResultSet rs = null;
		for (int id: ids) {
			if (id==-1) {
				break;
			}
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			ArticleMetadata metadata = metadataFromRS(rs);
			if (metadata!=null && Config.DEBUG_MODE) {
				metadata.setMeshNodes(getMeshNode(id));
			}
			list.add(metadata);
		}
		
		try {
		/*
			if (rs != null) {
			if (!rs.isClosed()) {
				rs.close();
			}
		*/
			rs.close();
		} catch (Exception e) {}
		
		stmt.close();
		return list.toArray(new ArticleMetadata[]{});		
	}
	
	/**
	 * Extract a DocMetadata object from a result set
	 * @param rs SQL result set
	 * @return a DocMetadata object (or null if none found)
	 * @throws SQLException
	 */
	private ArticleMetadata metadataFromRS(ResultSet rs) throws SQLException {
		ArticleMetadata metadata = new ArticleMetadata();
		if (!rs.next()) {
			return null;
		}
		int pmId = rs.getInt("PMID");
		metadata.title = rs.getString("TITLE");
		metadata.pubDate = rs.getDate("PUBDATE");
		metadata.journal = rs.getString("JTITLE");
		metadata.id = pmId;
		addAuthor(metadata, rs);
		while(rs.next()) {
			addAuthor(metadata, rs);
		}
		addToCache(metadata);
		return metadata;
	}
	
	private void addAuthor(ArticleMetadata metadata, ResultSet rs) throws SQLException {
		String lname = rs.getString("ALAST");
		if (lname == null || lname.length()==0) {
			return;
		}
		String fname = rs.getString("AFIRST");
		String name = (fname==null)?lname:lname+", "+fname;
		metadata.authors.add(name);
	}
	
	/**
	 * Add metadata to the cache, making room if necessary
	 * @param metadata metadata to add
	 */
	private void addToCache(ArticleMetadata metadata) {
		while (_metadataCache.size() >= _maxCacheSize) {
			Integer victimId = _metadataCache.keySet().iterator().next();
			_metadataCache.remove(victimId);
		}
		_metadataCache.put(metadata.id, metadata);
	}

}