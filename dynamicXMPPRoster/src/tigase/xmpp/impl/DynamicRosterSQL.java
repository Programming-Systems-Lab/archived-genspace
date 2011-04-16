/*
 * Tigase DynamicRoster for SQL. 
 * elijah@riseup.net
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://www.gnu.org/licenses/.
 *
 */

package tigase.xmpp.impl;

//
// non-JDK imports
//

import tigase.xml.Element;

import tigase.xmpp.JID;
import tigase.xmpp.NotAuthorizedException;
import tigase.xmpp.XMPPResourceConnection;

import tigase.db.DBInitException;
import tigase.db.DataRepository;
import tigase.db.RepositoryFactory;

// 
// JDK imports
//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**

EXAMPLE CONFIGURATION
==================================================

init.properties:

sess-man/plugins-conf/jabber\:iq\:roster/dynamic-roster-classes=tigase.xmpp.impl.DynamicRosterSQL
sess-man/plugins-conf/jabber\:iq\:roster/db-uri=jdbc:mysql://localhost/mydb?user=tigase&password=tigase
sess-man/plugins-conf/jabber\:iq\:roster/default-domain=localhost
sess-man/plugins-conf/jabber\:iq\:roster/pass-local-jids-to-queries=true

sess-man/plugins-conf/presence/dynamic-roster-classes=tigase.xmpp.impl.DynamicRosterSQL
sess-man/plugins-conf/presence/db-uri=jdbc:mysql://localhost/mydb?user=tigase&password=tigase
sess-man/plugins-conf/presence/default-domain=localhost
sess-man/plugins-conf/presence/pass-local-jids-to-queries=true

EXAMPLE STORED PROCEDURES (IN MYSQL)
==================================================

drop procedure if exists TigBuddies;
drop procedure if exists TigBuddyDetails;
drop procedure if exists TigRosterDetails;

delimiter //

create procedure TigBuddies(_user_jid varchar(2049) CHARSET utf8)
begin
    SELECT users.login FROM users INNER JOIN relationships ON users.id = relationships.contact_id WHERE relationships.type = 'Friendship' AND relationships.user_id = (SELECT users.id FROM users WHERE users.login = _user_jid);
end //

create procedure TigBuddyDetails(_user_jid varchar(2049) CHARSET utf8, _buddy_jid varchar(2049) CHARSET utf8)
begin
    # a query for a single buddy detail record.
    # returns columns: group, jid, name, subscription
    # note: this query is more complicated than it needs to be, but eventually we will want to pull in relationship
    # data for this query. So, for now, leave it complicated.
    SELECT "Friends" as "group", users.login as "jid", users.display_name as "name", "both" as "subscription" FROM users INNER JOIN relationships ON users.id = relationships.contact_id WHERE relationships.type = 'Friendship' AND relationships.user_id = (SELECT users.id FROM users WHERE users.login = _user_jid) AND relationships.contact_id = (SELECT users.id FROM users WHERE users.login = _buddy_jid);
end //

create procedure TigRosterDetails(_user_jid varchar(2049) CHARSET utf8)
begin
    # a query for the details of a user's entire roster
    # returns columns: group, jid, name, subscription
    SELECT "Friends" as "group", users.login as "jid", users.display_name as "name", "both" as "subscription" FROM users INNER JOIN relationships ON users.id = relationships.contact_id WHERE relationships.type = 'Friendship' AND relationships.user_id = (SELECT users.id FROM users WHERE users.login = _user_jid);
end //

delimiter ;

 */

public class DynamicRosterSQL implements DynamicRosterIfc {

	//
	// CONSTANTS
	//

	private static Logger log = Logger.getLogger(DynamicRosterSQL.class.getName());

	private static final String CONFIG_CONNECTION_STRING = "db-uri";
	private static final String CONFIG_DOMAIN = "default-domain";
	private static final String CONFIG_LOCAL_JIDS = "pass-local-jids-to-queries";

	private static final String BUDDIES_QUERY = "{ call TigBuddies(?) }";
	private static final String BUDDY_DETAILS_QUERY = "{ call TigBuddyDetails(?, ?) }";
	private static final String ROSTER_DETAILS_QUERY = "{ call TigRosterDetails(?) }";

	//
	// FIELDS
	//

	private DataRepository data_repo = null;
	private Map<String, Element> memStorage = new LinkedHashMap<String, Element>();
	private Map<String, Object> props;

	//
	// INITIALIZATION
	//

	/**
	 * Method description
	 *
	 *
	 * @param props
	 */

	@Override
	public void init(Map<String, Object> properties) {
		try {
			// set the defaults
			HashMap<String,Object> defaults = new HashMap<String,Object>();
			defaults.put(CONFIG_DOMAIN, "localhost");
			defaults.put(CONFIG_LOCAL_JIDS, "false");
			defaults.putAll(properties);
			this.props = defaults;

			// connect to the database
			String connection_str = (String) props.get(CONFIG_CONNECTION_STRING);
			data_repo = RepositoryFactory.getDataRepository(null, connection_str, null);
			data_repo.initPreparedStatement(BUDDIES_QUERY, BUDDIES_QUERY);
			data_repo.initPreparedStatement(BUDDY_DETAILS_QUERY, BUDDY_DETAILS_QUERY);
			data_repo.initPreparedStatement(ROSTER_DETAILS_QUERY, ROSTER_DETAILS_QUERY);
		} catch (Exception e) {
			data_repo = null;
			log.log(Level.SEVERE, "Trouble initializing DynamicRosterSQL: ", e);
		}
	}

	@Override
	public void init(String par) {
		log.log(Level.SEVERE, "DynamicRosterSQL does not support single string configuration.");
	}

	//
	// GET METHODS
	//

	/**
	 * Returns an array of JIDs who are the buddies of the currently
	 * authenticated user.
	 *
	 * @param session
	 *
	 * @return
	 *
	 * @throws NotAuthorizedException
	 */
	@Override
	public JID[] getBuddies(XMPPResourceConnection session)
			throws NotAuthorizedException {
		JID current_user = session.getJID(); // throws NotAuthorizedException if no user
		ArrayList<JID> jids = new ArrayList<JID>();
		ResultSet results = null;

		try {
			PreparedStatement buddies_ps = data_repo.getPreparedStatement(BUDDIES_QUERY);
			buddies_ps.setString(1, jidToString(current_user));
			results = buddies_ps.executeQuery();
			while (results.next()) {
				jids.add( stringToJID(results.getString(1)) );
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "DYNAMIC ROSTER: problem accessing dynamic roster.", e);
		} finally {
			data_repo.release(null, results);
		}
		JID[] return_jids = new JID[jids.size()];
		log.log(Level.FINEST, jids.toString());
		return jids.toArray(return_jids);
	}

	/**
	 * Return details of a particular buddy.
	 *
	 *
	 * @param session
	 * @param buddy
	 *
	 * @return
	 *
	 * @throws NotAuthorizedException
	 */
	@Override
	public Element getBuddyItem(XMPPResourceConnection session, JID buddy)
			throws NotAuthorizedException {
		JID current_user = session.getJID();
		ResultSet results = null;
		Element element = null;
		try {
			PreparedStatement buddy_details_ps = data_repo.getPreparedStatement(BUDDY_DETAILS_QUERY);
			buddy_details_ps.setString(1, jidToString(current_user));
			buddy_details_ps.setString(2, jidToString(buddy));
			results = buddy_details_ps.executeQuery();
			if (results.next()) {
				element = buildBuddyDetails(results);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "DYNAMIC ROSTER: problem building buddy details.", e);
		} finally {
			data_repo.release(null, results);
		}
		log.log(Level.FINEST, element.toString());
		return element;
	}

	/**
	 * Method description
	 *
	 *
	 * @param item
	 *
	 * @return
	 */
	@Override
	public Element getItemExtraData(Element item) {
		String jid = item.getAttribute("jid");
		Element result = memStorage.get(jid);

		if (log.isLoggable(Level.FINEST)) {
			log.log(Level.FINEST, "Retrieving item: {0}, for jid={1}", new Object[] { result, jid });
		}

		return result;
	}

	/**
	 * Returns details of all the currently authenticated user's buddies.
	 *
	 * @param session
	 *
	 * @return
	 *
	 * @throws NotAuthorizedException
	 */
	@Override
	public List<Element> getRosterItems(XMPPResourceConnection session)
			throws NotAuthorizedException {
		JID current_user = session.getJID();
		ResultSet results = null;
		List<Element> elements = new LinkedList<Element>();
		try {
			PreparedStatement roster_details_ps = data_repo.getPreparedStatement(ROSTER_DETAILS_QUERY);
			roster_details_ps.setString(1, jidToString(current_user));
			results = roster_details_ps.executeQuery();
			while (results.next()) {
				elements.add( buildBuddyDetails(results) );
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "DYNAMIC ROSTER: problem getting roster items details.", e);
		} finally {
			data_repo.release(null, results);
		}
		log.log(Level.FINEST, elements.toString());
		return elements;
	}

	//
	// SET METHODS
	//

	/**
	 * Method description
	 *
	 * @param item
	 */
	@Override
	public void setItemExtraData(Element item) {
		String jid = item.getAttribute("jid");
		if (log.isLoggable(Level.FINEST)) {
			log.log(Level.FINEST, "Storing item: {0}, for jid={1}", new Object[] { item, jid });
		}
		memStorage.put(jid, item);
	}

	//
	// PRIVATE METHODS
	//

	private Element buildBuddyDetails(ResultSet result) 
			throws java.sql.SQLException {
		String group = result.getString("group");
		String jid = result.getString("jid");
		if ( !jid.contains("@") ) {
			jid += "@" + props.get(CONFIG_DOMAIN);
		}
		String name = result.getString("name");
		String subscription = result.getString("subscription");
		return new Element("item",
			new Element[] { new Element("group", group) },
			new String[] { "jid", "name", "subscription" },
			new String[] {  jid,   name,   subscription }
		);
	}

	// ensure that the jid has a fully qualified domain
	private JID stringToJID(String jid) {
		if ( !jid.contains("@") ) {
			return JID.jidInstanceNS( jid + "@" + props.get(CONFIG_DOMAIN));
		} else {
			return JID.jidInstanceNS( jid );
		}
	}

	// remove domain if the query expects username only
	private String jidToString(JID jid) {
		if ( props.get(CONFIG_LOCAL_JIDS).equals("true") ) {
			return jid.getBareJID().toString().replaceFirst("@.*$","");
		} else {
			return jid.getBareJID().toString();
		}
	}
}
