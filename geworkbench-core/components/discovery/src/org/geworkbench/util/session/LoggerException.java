package org.geworkbench.util.session;

/**
 * <p>Title: Sequence and Pattern Plugin</p>
 * <p>Description: This class represents an exception that happened in the
 * Logger class.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version $Id: LoggerException.java 7567 2011-03-11 21:46:06Z zji $
 */

public class LoggerException extends Exception {
	private static final long serialVersionUID = 7589218659609671843L;

	public LoggerException() {
    }

    public LoggerException(String message) {
        super(message);
    }

}