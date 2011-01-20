package org.geworkbench.components.genspace.bean.networks;

public class Friend extends NetworkMessage {
	public Profile profile;
	public boolean isMutual;

	public Friend() {
		fancyName = "Friend Message";
	}
}
