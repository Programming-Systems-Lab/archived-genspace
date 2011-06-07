package org.geworkbench.engine.config.rules;

import org.geworkbench.util.BaseException;

/**
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: First Genetic Trust, Inc.</p>
 * @author First Genetic Trust, Inc.
 * @version $Id: MalformedMenuItemException.java 7944 2011-05-27 16:35:14Z zji $
 */

/**
 * Thrown when the string describing a menu item within a
 * <code>&lt;menu-item&gt;</code> block of the configuration file, is not correctly
 * formed.
 */
public class MalformedMenuItemException extends BaseException {
	private static final long serialVersionUID = -4892433534916486892L;

	// ---------------------------------------------------------------------------
    // --------------- Constructors
    // ---------------------------------------------------------------------------
    public MalformedMenuItemException() {
        super();
    }

    public MalformedMenuItemException(String message) {
        super(message);
    }

}