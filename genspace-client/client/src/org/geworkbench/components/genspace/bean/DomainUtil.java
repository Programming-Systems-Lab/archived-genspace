package org.geworkbench.components.genspace.bean;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A bunch of static functionalities used by domain classes and others
 * 
 * @author flavio
 * 
 */
public class DomainUtil {

	/**
	 * Given a list of tools (e.g., the sequence of tools in a workflow), it
	 * returns a string that identifies such list uniquely
	 * toolA,toolB,toolC,toolD to be used as a workflow identifier and to
	 * generate hashcode
	 * 
	 * @param tools
	 * @return
	 */
	public static String getStringID(ArrayList<Tool> tools) {
		StringBuilder s = new StringBuilder();
		Iterator<Tool> it = tools.iterator();
		if (it.hasNext()) {
			Tool t = it.next();
			s.append(t.name);
		}
		while (it.hasNext()) {
			Tool t = it.next();
			s.append("," + t.name);
		}
		return s.toString();
	}

	public static ArrayList<String> fromWorkflowStringToList(String workflow) {
		ArrayList<String> result = new ArrayList<String>();
		String[] tools = workflow.split(",");
		for (String t : tools) {
			result.add(t);
		}
		return result;
	}

}
