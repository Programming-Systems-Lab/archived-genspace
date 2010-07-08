package org.geworkbench.components.genspace.bean.networks;

import java.util.HashMap;

public class Profile extends NetworkMessage{
/**
* 
*/
private static final long serialVersionUID = -8588636721948189064L;
//profile stored as k,v pair to support extendability
public HashMap<String, String> profile = new HashMap<String, String>();
public Profile()
{
fancyName = "Profile Message";
}
@Override
public String toString() {
// TODO Auto-generated method stub
return profile.get("username");
}
}

