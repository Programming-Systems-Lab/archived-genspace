package org.geworkbench.components.genspace.bean;

import java.io.Serializable;

public class Tool implements Serializable {

	public String name;
	public int id; // the ID may not always be available if not explicitly
					// loaded from the DB
	public String description;// the current description in the DB is always
								// null for all tools
	public String mostCommonParameters;
	public int mostCommonParametersCount;

	public Tool() {
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Tool) {
			Tool t = (Tool) o;
			return t.name.equals(this.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return id + " - " + name + " - " + description + " - "
				+ mostCommonParameters + " - " + mostCommonParametersCount;
	}
}
