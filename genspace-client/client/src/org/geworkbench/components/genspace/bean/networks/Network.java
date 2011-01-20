package org.geworkbench.components.genspace.bean.networks;

public class Network extends NetworkMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7932890249021942732L;
	public NetScope networkScope;
	public String creator;

	public enum NetScope {
		ME, ALL, USER, NETWORK, PENDING
	};

	public Network() {
		fancyName = "Network Message";
	}

	public String getName() {
		return subject;
	}
}
