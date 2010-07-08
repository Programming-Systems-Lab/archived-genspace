package org.geworkbench.components.genspace.bean.networks;

import java.util.HashMap;
import java.util.List;

public class Friend extends NetworkMessage{
	public Profile profile;
	public boolean isMutual;
	public Friend()
	{
		fancyName = "Friend Message";
	}
}
