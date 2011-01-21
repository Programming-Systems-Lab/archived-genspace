package org.geworkbench.components.genspace.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class DataVisibilityBean implements SecurityMessageBean, Serializable {
	private String username;
	private int logData;
	private int dataVisibility;
	private List selectedNetworks;
	private String message;

	public String getUsername() {
		return username;
	}

	public int getLogData() {
		return logData;
	}

	public void setLogData(int logData) {
		this.logData = logData;
	}

	public int getDataVisibility() {
		return dataVisibility;
	}

	public void setDataVisibility(int dataVisibility) {
		this.dataVisibility = dataVisibility;
	}

	public List getSelectedNetworks() {
		return selectedNetworks;
	}

	public void setSelectedNetworks(List selectedNetworks) {
		this.selectedNetworks = selectedNetworks;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setUName(String uname) {
		username = uname;
	}

	public void setMessage(String msg) {
		message = msg;
	}


}
