package org.geworkbench.components.genspace.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class NetworkVisibilityBean implements SecurityMessageBean, Serializable {
	private String username;
	private int userVisibility;
	private List selectedNetworks;
	private String message;

	public String getUsername() {
		return username;
	}

	public int getUserVisibility() {
		return userVisibility;
	}

	public void setUserVisibility(short visibility) {
		this.userVisibility = visibility;
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
