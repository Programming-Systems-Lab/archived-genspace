package genspace.jclaim;

/**
 * This is the interface for any object that maintains a state-based session over IM.
 * 
 */


public interface StatefulObject {

	/*
	 * This method is used to receive input (in the form of a String) and then return the
	 * output that should be sent back to the user's IM client.
	 */
	public String handleInput(String in);
	
	/**
	 * Indicates whether or not this session is still ongoing.
	 */
	public boolean isActive();
}
