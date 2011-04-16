package org.geworkbench.components.cutenet;

import geneways.geneways60test.commandLine.PMFactory;
import geneways.geneways60test.cutenetPlugin.DBInstanceImpl;
import geneways.geneways60test.cutenetPlugin.DBPluginImpl;
import geneways.geneways60test.object_model.Action;
import geneways.geneways60test.object_model.Name;
import geneways.geneways60test.object_model.NameId;
import geneways.geneways60test.object_model.NamedEntity;
import geneways.util.dag.DAGType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cgc.cutenet.Cutenet;
import cgc.cutenet.database.DatabaseBrowser;
import cgc.cutenet.database.DatabaseException;
import cgc.cutenet.database.plugin.IncidentActionQueryResult;
import cgc.cutenet.database.view.DBId;
import cgc.cutenet.graphic.CutenetWorld;

/**
 * @author Kaushal Kumar
 */
public class CutenetHelper {

	static Log log = LogFactory.getLog(CutenetHelper.class);
	private PersistenceManager m_pm;
	private Cutenet cutenet;
	private DatabaseBrowser db;

	private List firstOne = new ArrayList();
	private DBInstanceImpl dbInstanceImpl;
	private DBPluginImpl dbPluginImpl;
	private GenewaysDBHelper dbHelper;
	private List allCutenetInteractions = new ArrayList<CutenetInteraction>();
	private List allCutenetProteins = new ArrayList<CutenetProtein>();

	public CutenetHelper() throws Exception {
		init();
	}

	public void init() {
		cutenet = new Cutenet(CutenetWorld.WORLD2D);
		m_pm = PMFactory.getPersistenceManager();

		// connecting to the database
		try {
			// The database url is read from the cutenet.properties file.
			// If you need to take database url from user, find out how to set
			// database url here and then connect to it.
			db = cutenet.getDatabaseBrowser();
			db.connect(db.getDefaultDatabaseName());
			log.debug("default database Name: " + db.getDefaultDatabaseName());
			dbInstanceImpl = (DBInstanceImpl) db.getDBInstances().get(0);
			dbPluginImpl = (DBPluginImpl) dbInstanceImpl.getDBPlugin();
			dbHelper = new GenewaysDBHelper(m_pm);
		} catch (Exception dbe) {
			log.debug("database connection failure: ");
			// throw new Exception("Could not open database.");
		}
	}

	public void queryProtein() {
		// test run
		List queryResults = queryByName("p53", false);
		setFirstProtein(queryResults);
		for (Iterator itr = queryResults.iterator(); itr.hasNext();) {
			allCutenetProteins.add((CutenetProtein) itr.next());
		}
		log.debug(allCutenetProteins.size() + " Finished. ");
	}

	// This method is not used yet, but if you need to do db query by gene name
	// then use this method.
	public List queryByName(String queryString, boolean withActionCheck) {
		Collection queryResults = null; // collection of "Name" objects
		ArrayList cpArr = new ArrayList();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("name == namep");

		if (withActionCheck) {
			queryBuff.append(" && actionsCount > 0");
		}
		Query nQuery = m_pm.newQuery(m_pm.getExtent(Name.class, false),
				queryBuff.toString());
		nQuery.declareParameters("String namep");
		queryResults = (Collection) nQuery.execute(queryString);
		for (Iterator itr = queryResults.iterator(); itr.hasNext();) {
			Name nextName = (Name) itr.next();
			CutenetProtein cp = new CutenetProtein(GenewaysWidget.nodeId++);
			ArrayList namedEntities = new ArrayList();
			cp.setName(nextName.getName());
			cp.setDbIds(getDBIds(nextName));
			cp.setActionsCount(nextName.getActionsCount());
			cp.setFromActionsCount(nextName.getFromActionsCount());
			cp.setToActionsCount(nextName.getToActionsCount());

			namedEntities.addAll(nextName.getNamedEntities());
			for (int i = 0; i < namedEntities.size(); i++) {
				NamedEntity ne = (NamedEntity) namedEntities.get(i);
				if (ne.getDbId().equals("swissprot")) {
					cp.addSwissProtID(ne.getAccessionNumber());
				}
			}
			cpArr.add(cp);
			allCutenetProteins.add(cp);
		}

		return cpArr;
	}

	// This method is not used yet, but if you need to do db query by substring
	// then use this method.
	public List queryBySubString(String queryString, boolean withActionCheck) {
		Collection queryResults; // collection of "Name" objects
		ArrayList cpArr = new ArrayList();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("name.ext:matches(\".*" + queryString + ".*\")");
		if (withActionCheck) {
			queryBuff.append(" && actionsCount > 0");
		}
		Query nQuery = m_pm.newQuery(m_pm.getExtent(Name.class, false),
				queryBuff.toString());
		queryResults = (Collection) nQuery.execute(queryString);
		for (Iterator itr = queryResults.iterator(); itr.hasNext();) {
			Name nextName = (Name) itr.next();
			CutenetProtein cp = new CutenetProtein(GenewaysWidget.nodeId++);
			ArrayList namedEntities = new ArrayList();
			cp.setName(nextName.getName());
			cp.setDbIds(getDBIds(nextName));
			cp.setActionsCount(nextName.getActionsCount());
			cp.setFromActionsCount(nextName.getFromActionsCount());
			cp.setToActionsCount(nextName.getToActionsCount());

			namedEntities.addAll(nextName.getNamedEntities());
			for (int i = 0; i < namedEntities.size(); i++) {
				NamedEntity ne = (NamedEntity) namedEntities.get(i);
				if (ne.getDbId().equals("swissprot")) {
					cp.addSwissProtID(ne.getAccessionNumber());
				}
			}
			cpArr.add(cp);
			allCutenetProteins.add(cp);
		}

		return cpArr;
	}

	// this method is called when clicked on "refresh" button.
	// It is used only once, for the proteins selected in the "selected marker
	// list"
	// It creates a node... i.e. initial node.
	public CutenetProtein queryByAccNo(String queryString, String dbName,
			boolean withActionCheck, GenewaysControlParams gwcp) {
		if (!db.isConnected()) {
			init();
			if (!db.isConnected()) {
				log.debug("Could not connect to the geneways database.");
				return null;
			}
		}
		Collection queryResults; // collection of "Name" objects
		StringBuffer queryBuff = new StringBuffer();
		queryBuff
				.append("namedEntities.contains(ne) && ne.dbId == dbid && ne.accessionNumber == acc");
		if (withActionCheck) {
			queryBuff.append(" && actionsCount > 0");
		}
		Query nQuery = m_pm.newQuery(m_pm.getExtent(Name.class, false),
				queryBuff.toString());
		nQuery.declareVariables("NamedEntity ne");
		nQuery.declareParameters("String dbid, String acc");
		queryResults = (Collection) nQuery.execute(dbName, queryString);
		ArrayList<DBId> dbIds = new ArrayList<DBId>();
		for (Iterator itr = queryResults.iterator(); itr.hasNext();) {
			Name nextName = (Name) itr.next();
			dbIds.add(name2DBId(nextName));
		}
		if (dbIds.size() > 0) {
			CutenetProtein cutProt = null;
			try {
				DBId[] tempDBIds = new DBId[dbIds.size()];
				int i = 0;
				for (Iterator itr = dbIds.iterator(); itr.hasNext();) {
					tempDBIds[i++] = (DBId) itr.next();
				}
				cutProt = new CutenetProtein(dbIds, getNodeName(tempDBIds));
				return cutProt;
			} catch (DatabaseException e) {
				log.debug("Could not find the name for the node");
			}
		}
		return null;
	}

	// This method is called when you need to find interaction for a node.
	// Note that no new query is made... interactions are found out using
	// persistence manager (m_pm).
	public ArrayList<CutenetInteraction> getInteractions(CutenetProtein cp,
			GenewaysControlParams gwcp) {
		if (!db.isConnected()) {
			init();
			if (!db.isConnected()) {
				log.debug("Could not connect to the geneways database.");
				return null;
			}
		}
		DBId[] dbIds = cp.getDbIds();
		ArrayList arl = new ArrayList();
		ArrayList itypes = new ArrayList();
		ArrayList interactionList = new ArrayList<CutenetInteraction>();
		int fromCount = 0;
		int toCount = 0;
		for (int i = 0; i < dbIds.length; i++) {
			DBId nextDbId = dbIds[i];
			if (!dbPluginImpl.getNameNameSpace()
					.equals(nextDbId.getNameSpace()))
				continue;
			Name name;
			try {
				name = (Name) m_pm.getObjectById(new NameId(nextDbId.getId()),
						false);
				/*
				 * Set<Action> test=name.getFromActions(); Iterator<Action>
				 * testi=test.iterator(); while (testi.hasNext()){ Action
				 * otest=testi.next(); Set<ActionMention> amstest =
				 * otest.getActionMentions(); Iterator<ActionMention> amitest =
				 * amstest.iterator(); while (amitest.hasNext()){ ActionMention
				 * amtest = amitest.next(); String attest =
				 * amtest.getActionType(); TermMention tmustest =
				 * amtest.getUpstream(); TermMention tmdwtest =
				 * amtest.getDownstream(); Article atest=amtest.getArticle();
				 * String pmid=atest.getPmid(); System.out.println(pmid); } }
				 */
			} catch (Exception e) {
				continue;
			}
			/*
			 * CutenetManager cutenetManager=CutenetManager.getCutenetManager();
			 * HashMap gnIdMap = cutenetManager.getGenewaysNameIdMap(); HashMap
			 * gIdPMap = cutenetManager.getGenewaysIdProteinMap(); Object temp =
			 * gnIdMap.get(cp.getName()); if (temp!=null)
			 * System.out.print(temp.toString());
			 */
			ArrayList namedEntities = new ArrayList();
			namedEntities.addAll(name.getNamedEntities());
			for (int cx = 0; cx < namedEntities.size(); cx++) {
				NamedEntity ne = (NamedEntity) namedEntities.get(cx);
				// System.out.println(cp.getId()+cp.getName()+":"+ne.getDbId()+":"+ne.getAccessionNumber());
				if (ne.getDbId().equals("swissprot")) {
					cp.addSwissProtID(ne.getAccessionNumber());
				}
			}

			if ((gwcp.getDirection() != null)
					&& (gwcp.getDirection().equals("both") || gwcp
							.getDirection().equals("out"))) {
				for (Iterator it = name.getFromActions().iterator(); it
						.hasNext();) {
					Action action = (Action) it.next();

					if (!doesActionFitFilter(action, gwcp))
						continue;

					IncidentActionQueryResult iaqr = new IncidentActionQueryResult();
					iaqr.edgeDbid = new DBId(dbPluginImpl.getActionNameSpace(),
							"" + action.getActionId());
					iaqr.nodeDbids = new DBId[] { name2DBId(action
							.getDownstream()) };
					iaqr.out = true;
					itypes.add(action.getActionType());
					arl.add(iaqr);
					fromCount++;
					log.debug("CutenetHelper:  From interaction.");
				}
			}
			if ((gwcp.getDirection() != null)
					&& (gwcp.getDirection().equals("both") || gwcp
							.getDirection().equals("in"))) {
				for (Iterator it = name.getToActions().iterator(); it.hasNext();) {
					Action action = (Action) it.next();

					if (!doesActionFitFilter(action, gwcp))
						continue;

					IncidentActionQueryResult iaqr = new IncidentActionQueryResult();
					iaqr.edgeDbid = new DBId(dbPluginImpl.getActionNameSpace(),
							"" + action.getActionId());
					iaqr.nodeDbids = new DBId[] { name2DBId(action
							.getUpstream()) };
					iaqr.out = false;
					itypes.add(action.getActionType());
					arl.add(iaqr);
					toCount++;
					log.debug("CutenetHelper: To interaction.");
				}
			}
		}
		IncidentActionQueryResult[] r = new IncidentActionQueryResult[arl
				.size()];
		System.arraycopy(arl.toArray(), 0, r, 0, r.length);

		for (int i = 0; i < r.length; i++) {
			CutenetProtein nextToCP = null;
			try {
				nextToCP = new CutenetProtein(r[i].nodeDbids,
						getNodeName(r[i].nodeDbids));
				nextToCP.setActionsCount(arl.size());
				nextToCP.setToActionsCount(toCount);
				nextToCP.setFromActionsCount(fromCount);
				ArrayList<String> swissProtIDs = getSwissProtIDsForCP(nextToCP);
				for (String swissProtID : swissProtIDs) {
					nextToCP.addSwissProtID(swissProtID);
				}
				CutenetInteraction nextInteraction = null;
				if (r[i].out) {
					nextInteraction = new CutenetInteraction(cp, nextToCP, 1,
							(String) itypes.get(i));
				} else {
					nextInteraction = new CutenetInteraction(nextToCP, cp, 1,
							(String) itypes.get(i));
				}
				interactionList.add(nextInteraction);
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
		}
		allCutenetInteractions.addAll(interactionList);
		return interactionList;
	}

	public void getNodeInfo(CutenetProtein cp) {
		DBId[] dbIds = cp.getDbIds();
		final ArrayList namedEntities = new ArrayList();
		for (int i = 0; i < dbIds.length; i++) {
			if (dbIds[i].getNameSpace().equals(dbPluginImpl.getNameNameSpace())) {
				try {
					Name name = (Name) m_pm.getObjectById(new NameId(dbIds[i]
							.getId()), false);
					namedEntities.addAll(name.getNamedEntities());
				} catch (Exception e) {
				}
			}
		}
		for (Iterator itr = namedEntities.iterator(); itr.hasNext();) {
			NamedEntity ne = (NamedEntity) itr.next();
			log.debug("DB Id: " + ne.getDbId() + " Accession Number: "
					+ ne.getAccessionNumber());
		}

	}

	public CutenetProtein findProteinBySwissProtId(String swissProtId) {
		for (Iterator itr = allCutenetProteins.iterator(); itr.hasNext();) {
			CutenetProtein nextCP = (CutenetProtein) itr.next();
			Iterator itr1 = nextCP.getSwissProtIDs().iterator();
			while (itr1.hasNext()) {
				String nextSwissProtId = (String) itr1.next();
				if (nextSwissProtId.equalsIgnoreCase(swissProtId)) {
					return nextCP;
				}
			}
		}
		return null;
	}

	// TODO
	// rightnow all interactions have strength 0 or 1, 1 means there is an
	// interaction and 0 means otherwise.
	// check in cutenet code how actual interaction strength (value between 0
	// and 1) can be found out.
	public int getInteractionStrength(CutenetProtein cp1, CutenetProtein cp2) {
		for (Iterator itr = allCutenetInteractions.iterator(); itr.hasNext();) {
			CutenetInteraction interaction = (CutenetInteraction) itr.next();
			if ((interaction.getProtein1().getId() == cp1.getId() && interaction
					.getProtein2().getId() == cp2.getId())
					|| (interaction.getProtein1().getId() == cp2.getId() && interaction
							.getProtein2().getId() == cp1.getId())) {
				return 1;
			}
		}
		return 0;
	}

	public ArrayList<CutenetInteraction> getInteractionInfo(CutenetProtein cp1,
			CutenetProtein cp2) {
		ArrayList<CutenetInteraction> answer = new ArrayList<CutenetInteraction>();
		for (Iterator itr = allCutenetInteractions.iterator(); itr.hasNext();) {
			CutenetInteraction interaction = (CutenetInteraction) itr.next();
			if ((interaction.getProtein1().getId() == cp1.getId() && interaction
					.getProtein2().getId() == cp2.getId())
					|| (interaction.getProtein1().getId() == cp2.getId() && interaction
							.getProtein2().getId() == cp1.getId())) {
				answer.add(interaction);
			}
		}
		return answer;
	}

	public List getFirstProtein() {
		return firstOne;
	}

	public void setFirstProtein(List firstOne) {
		this.firstOne = firstOne;
	}

	public DBId[] getDBIds(Name name) {
		return new DBId[] { name2DBId(name) };
	}

	DBId name2DBId(Name name) {
		return new DBId(dbPluginImpl.getNameNameSpace(), "" + name.getNameId());
	}

	public GenewaysDBHelper getDBHelper() {
		return dbHelper;
	}

	boolean doesActionFitFilter(Action action, GenewaysControlParams g) {
		ActionFilter f = getActionFilter(g);
		return doesActionFitFilter(action, f);
	}

	// checks if the interaction satisfy the control parameters criteria
	boolean doesActionFitFilter(Action action, ActionFilter f) {
		if (action.getActionMentionCount() < f.minInstanceCount)
			return false;
		if (action.getArticleCount() < f.minArticleCount)
			return false;
		// The datates
		if (f.newerThan.after(action.getAge()))
			return false;

		if (f.olderThan.before(action.getAge()))
			return false;

		if (f.actionTypesSet != null) {
			if (!f.actionTypesSet.contains(action.getActionType()))
				return false;
		}

		return true;
	}

	// contains value from the control parameters and used to check if the
	// interactions satisfy those criteria.
	class ActionFilter {
		int minInstanceCount;
		int minArticleCount;

		boolean out = false;
		boolean in = false;

		Date newerThan;
		Date olderThan;

		Set actionTypesSet;
	}

	ActionFilter getActionFilter(GenewaysControlParams g) {
		ActionFilter ff = new ActionFilter();
		ff.minInstanceCount = g.getMinInstanceCount();
		ff.minArticleCount = g.getMinArticleCount();

		ff.in = (g.getDirection().equals("both") || g.getDirection().equals(
				"in")) ? true : false;
		ff.out = (g.getDirection().equals("both") || g.getDirection().equals(
				"out")) ? true : false;

		ff.newerThan = g.getNewerThan();
		ff.olderThan = g.getOlderThan();

		DAGType actionTypeDag = dbHelper.getActionTypeDAG();
		int[] selected = g.getActionTypeDAG().getSelectedValueIds();

		if (selected.length > 0) {
			ff.actionTypesSet = new HashSet();
			for (int i = 0; i < selected.length; i++) {
				if (g.getActionTypeDAG().isHierarchical()) {
					int[] children = actionTypeDag
							.getValueTrcChildren(selected[i]);
					for (int j = 0; j < children.length; j++) {
						String at = actionTypeDag.getValueName(children[j]);
						log.debug("Adding: |" + at + "| aciton type");
						ff.actionTypesSet.add(at);
					}
				} else {
					ff.actionTypesSet.add(actionTypeDag
							.getValueName(selected[i]));
				}
			}
		} else {
			ff.actionTypesSet = null;
		}
		return ff;
	}

	public IncidentActionQueryResult[] getIncidentActions(CutenetProtein cp,
			GenewaysControlParams g) throws DatabaseException {
		ArrayList arl = new ArrayList();
		// ActionFilter f = getActionFilter();
		DBId[] dbids = cp.getDbIds();
		for (int i = 0; i < dbids.length; i++) {
			log.debug("ID: " + dbids[i]);
			if (!dbPluginImpl.getNameNameSpace()
					.equals(dbids[i].getNameSpace()))
				continue;
			Name name;
			try {
				name = (Name) m_pm.getObjectById(new NameId(dbids[i].getId()),
						false);
			} catch (Exception e) {
				continue;
			}
			ArrayList namedEntities = new ArrayList();
			namedEntities.addAll(name.getNamedEntities());
			for (int cx = 0; cx < namedEntities.size(); cx++) {
				NamedEntity ne = (NamedEntity) namedEntities.get(cx);
				if (ne.getDbId().equals("swissprot")) {
					cp.addSwissProtID(ne.getAccessionNumber());
				}
			}

			if (g.getDirection().equals("both")
					|| g.getDirection().equals("out")) {
				for (Iterator it = name.getFromActions().iterator(); it
						.hasNext();) {
					Action action = (Action) it.next();

					if (!doesActionFitFilter(action, g))
						continue;

					IncidentActionQueryResult iaqr = new IncidentActionQueryResult();
					iaqr.edgeDbid = new DBId(dbPluginImpl.getActionNameSpace(),
							"" + action.getActionId());
					iaqr.nodeDbids = new DBId[] { name2DBId(action
							.getDownstream()) };
					iaqr.out = true;
					arl.add(iaqr);
					log.debug("From interaction.");
				}
			}

			if (g.getDirection().equals("both")
					|| g.getDirection().equals("in")) {
				for (Iterator it = name.getToActions().iterator(); it.hasNext();) {
					Action action = (Action) it.next();

					if (!doesActionFitFilter(action, g))
						continue;

					IncidentActionQueryResult iaqr = new IncidentActionQueryResult();
					iaqr.edgeDbid = new DBId(dbPluginImpl.getActionNameSpace(),
							"" + action.getActionId());
					iaqr.nodeDbids = new DBId[] { name2DBId(action
							.getUpstream()) };
					iaqr.out = false;

					arl.add(iaqr);
					log.debug("To interaction.");
				}
			}
		}

		IncidentActionQueryResult[] r = new IncidentActionQueryResult[arl
				.size()];
		System.arraycopy(arl.toArray(), 0, r, 0, r.length);
		log.debug("Returning " + r.length + " interactions");
		return r;
	}

	public ArrayList<CutenetInteraction> getIncidentActions2(CutenetProtein cp,
			GenewaysControlParams g, HashMap genewaysNameIdMap,
			HashMap genewaysIdProteinMap) throws DatabaseException {
		ArrayList arl = new ArrayList();
		ArrayList itypes = new ArrayList();
		// ActionFilter f = getActionFilter();
		DBId[] dbids = cp.getDbIds();
		for (int i = 0; i < dbids.length; i++) {
			log.debug("ID: " + dbids[i]);
			if (!dbPluginImpl.getNameNameSpace()
					.equals(dbids[i].getNameSpace()))
				continue;
			Name name;
			try {
				name = (Name) m_pm.getObjectById(new NameId(dbids[i].getId()),
						false);
			} catch (Exception e) {
				continue;
			}
			ArrayList namedEntities = new ArrayList();
			namedEntities.addAll(name.getNamedEntities());
			for (int cx = 0; cx < namedEntities.size(); cx++) {
				NamedEntity ne = (NamedEntity) namedEntities.get(cx);
				if (ne.getDbId().equals("swissprot")) {
					cp.addSwissProtID(ne.getAccessionNumber());
				}
			}

			if (g.getDirection().equals("both")
					|| g.getDirection().equals("out")) {
				for (Iterator it = name.getFromActions().iterator(); it
						.hasNext();) {
					Action action = (Action) it.next();

					if (!doesActionFitFilter(action, g))
						continue;

					IncidentActionQueryResult iaqr = new IncidentActionQueryResult();
					iaqr.edgeDbid = new DBId(dbPluginImpl.getActionNameSpace(),
							"" + action.getActionId());
					iaqr.nodeDbids = new DBId[] { name2DBId(action
							.getDownstream()) };
					iaqr.out = true;
					itypes.add(action.getActionType());
					arl.add(iaqr);
					log.debug("From interaction.");
				}
			}

			if (g.getDirection().equals("both")
					|| g.getDirection().equals("in")) {
				for (Iterator it = name.getToActions().iterator(); it.hasNext();) {
					Action action = (Action) it.next();

					if (!doesActionFitFilter(action, g))
						continue;

					IncidentActionQueryResult iaqr = new IncidentActionQueryResult();
					iaqr.edgeDbid = new DBId(dbPluginImpl.getActionNameSpace(),
							"" + action.getActionId());
					iaqr.nodeDbids = new DBId[] { name2DBId(action
							.getUpstream()) };
					iaqr.out = false;
					itypes.add(action.getActionType());
					arl.add(iaqr);
					log.debug("To interaction.");
				}
			}
		}

		IncidentActionQueryResult[] r = new IncidentActionQueryResult[arl
				.size()];
		System.arraycopy(arl.toArray(), 0, r, 0, r.length);
		log.debug("Returning " + r.length + " interactions");
		ArrayList<CutenetInteraction> interactions = new ArrayList<CutenetInteraction>();
		for (int i = 0; i < r.length; i++) {
			CutenetProtein nextToCP = null;
			String newOrOldName = this.getNodeName(r[i].nodeDbids);
			if (genewaysNameIdMap.containsKey(newOrOldName)) {
				Integer oldId = (Integer) genewaysNameIdMap.get(newOrOldName);
				if (oldId != null)
					nextToCP = (CutenetProtein) genewaysIdProteinMap.get(oldId);
			} else {
				nextToCP = new CutenetProtein(r[i].nodeDbids, newOrOldName);
			}
			nextToCP.setActionsCount(r.length);
			nextToCP.setActionsCount(arl.size());
			interactions.add(new CutenetInteraction(cp, nextToCP, 1,
					(String) itypes.get(i)));
		}

		return interactions;
	}

	public String getNodeName(DBId dbids[]) throws DatabaseException {
		// for (Iterator it = dbInstanceImpl.iterator(); it.hasNext(); ) {
		// DBInstance dbInst = (DBInstance)it.next();
		String name = dbInstanceImpl.getNodeName(dbids);
		if (name != null)
			return name;
		// }
		return "unknown";
	}

	public Name getNameByDBId(DBId dbId) {
		Name name = null;
		try {
			name = (Name) m_pm.getObjectById(new NameId(dbId.getId()), false);
		} catch (Exception e) {
		}

		return name;
	}

	public boolean isConnected() {
		if (db.isConnected())
			return true;
		else
			return false;
	}

	public ArrayList<String> getSwissProtIDsForCP(CutenetProtein cp) {
		if (!db.isConnected()) {
			init();
			if (!db.isConnected()) {
				log.debug("Could not connect to the geneways database.");
				return null;
			}
		}
		DBId[] dbIds = cp.getDbIds();
		ArrayList swissProtIDs = new ArrayList<String>();
		int fromCount = 0;
		int toCount = 0;
		for (int i = 0; i < dbIds.length; i++) {
			DBId nextDbId = dbIds[i];
			if (!dbPluginImpl.getNameNameSpace()
					.equals(nextDbId.getNameSpace()))
				continue;
			Name name;
			try {
				name = (Name) m_pm.getObjectById(new NameId(nextDbId.getId()),
						false);
				/*
				 * Set<Action> test=name.getFromActions(); Iterator<Action>
				 * testi=test.iterator(); while (testi.hasNext()){ Action
				 * otest=testi.next(); Set<ActionMention> amstest =
				 * otest.getActionMentions(); Iterator<ActionMention> amitest =
				 * amstest.iterator(); while (amitest.hasNext()){ ActionMention
				 * amtest = amitest.next(); String attest =
				 * amtest.getActionType(); TermMention tmustest =
				 * amtest.getUpstream(); TermMention tmdwtest =
				 * amtest.getDownstream(); Article atest=amtest.getArticle();
				 * String pmid=atest.getPmid(); System.out.println(pmid); } }
				 */
			} catch (Exception e) {
				continue;
			}
			/*
			 * CutenetManager cutenetManager=CutenetManager.getCutenetManager();
			 * HashMap gnIdMap = cutenetManager.getGenewaysNameIdMap(); HashMap
			 * gIdPMap = cutenetManager.getGenewaysIdProteinMap(); Object temp =
			 * gnIdMap.get(cp.getName()); if (temp!=null)
			 * System.out.print(temp.toString());
			 */
			ArrayList namedEntities = new ArrayList();
			namedEntities.addAll(name.getNamedEntities());
			for (int cx = 0; cx < namedEntities.size(); cx++) {
				NamedEntity ne = (NamedEntity) namedEntities.get(cx);
				// System.out.println(cp.getId()+cp.getName()+":"+ne.getDbId()+":"+ne.getAccessionNumber());
				if (ne.getDbId().equals("swissprot")) {
					swissProtIDs.add(ne.getAccessionNumber());
				}
			}
		}
		return swissProtIDs;
	}
}
