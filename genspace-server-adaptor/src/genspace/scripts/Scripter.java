package genspace.scripts;

/**
 * Base class for writing timed scripts. All scripts should inherit from this class and MUST override the doThis() method.
 * The default timeout for repeating the scripts is 2sec. Override the getTimeout() method in the derived class to control this.
 * @author swapneel
 */
public class Scripter implements Runnable {

	/**
	 * Empty method; to be overridden by the derived class
	 * This is the starting entry point for the script
	 */
	public void doThis() {}
	
	public void run() {
		try {
			while(true) {
				doThis();
				Thread.sleep(getTimeout());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The default timeout is 2 sec. Override this method in the derived class to change this
	 * @return the timeout
	 */
	public long getTimeout() {
		return 2*1000;
	}

}
