package org.geworkbench.components.skylinecontour.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * $Id: ArticleMetadata.java,v 1.3 2009-11-18 16:57:42 jiz Exp $
 *
 * Holder object for document metadata.
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class ArticleMetadata {
	public int id;
	public String title;
	public Date pubDate;
	public String journal;
	public List<String> authors = new ArrayList<String>(3);
	public List<MeshNode> meshNodes = new ArrayList<MeshNode>();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yy");
	private Pattern quotePattern = Pattern.compile("\"");
	private Pattern eolPattern = Pattern.compile("\n|\n\r");
	private Pattern wsPattern = Pattern.compile("\\s+");
		
	public String toJson() {
		StringBuffer buffer = new StringBuffer("{");
		buffer.append("id: " + id + ",");
		buffer.append("title: \"" + clean(title) + "\",");
		buffer.append("journal: \"" + clean(journal)+"\",");
		buffer.append("pubdate: \"" + dateFormat.format(pubDate)+ "\",");
		buffer.append("author: \"");
		boolean first = true;
		for (String authorname: authors) {
			if (!first) {
				buffer.append("; ");
			}
			buffer.append(clean(authorname));
			first = false;
		}
		buffer.append("\"");		
		buffer.append("}");
		return buffer.toString();
	}
	
	protected String clean(String string) {
		Matcher m1 = quotePattern.matcher(string); // get a matcher object		
		string = m1.replaceAll("\\\\\"");
		Matcher m2 = eolPattern.matcher(string); // get a matcher object
		string = m2.replaceAll("");
		Matcher m3 = wsPattern.matcher(string); // get a matcher object
		string = m3.replaceAll(" ");
		return string;
	}
	
	public void setMeshNodes(List<MeshNode> nodes) {
		meshNodes = nodes;
	}
	
	public String meshNodesToString() {
		StringBuffer buffer = new StringBuffer();
		boolean first = true;
		for (MeshNode node: meshNodes) {
			if (!first) {
				buffer.append(", ");
			}
			buffer.append(node.getHeading());
			first = false;
		}
		return buffer.toString();		
	}
	

}
