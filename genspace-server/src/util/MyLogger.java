package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * General Utility class to instantiate and setup the logger
 * @author swapneel
 */
public class MyLogger {

	/**
	 * The location of the conf file for the logging
	 */
	private static final String log4jConfFile = "src/util/log4j.properties";
	protected static Logger log;
		
	/**
	 * Method to be called to get a logger
	 * @param clazz The Name of the Class
	 * @return The logger object
	 */
	public static Logger getInstance(String clazz) {
		log = Logger.getLogger(clazz);
		PropertyConfigurator.configure(log4jConfFile);
		return log;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = MyLogger.getInstance(MyLogger.class.getName());
		log.info("Test Logging");
	}
}
