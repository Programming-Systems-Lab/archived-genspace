package org.geworkbench.engine.config.rules;

/**
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: First Genetic Trust, Inc.</p>
 * @author First Genetic Trust, Inc.
 * @version $Id: DynamicMenuItemException.java 7944 2011-05-27 16:35:14Z zji $
 */

/**
 * Thrown when the menu path specified within some <code>&lt;menu-item&gt;</code>
 * block of the configuration file, is in conflict with the already existing menu
 * structure. A conflict situation arises when:
 * <UI>
 * <LI>   The ending point of the new menu item path coincides with an existing
 * submenu.</LI>
 * <LI>   A submenu node in the new menu item path coincides with an existing
 * final menu item.</LI>
 * </UI>
 */
public class DynamicMenuItemException extends org.geworkbench.util.BaseException {
	private static final long serialVersionUID = -1785392318270874137L;

	// ---------------------------------------------------------------------------
    // --------------- Constructors
    // ---------------------------------------------------------------------------
    public DynamicMenuItemException() {
        super();
    }

    public DynamicMenuItemException(String message) {
        super(message);
    }

}