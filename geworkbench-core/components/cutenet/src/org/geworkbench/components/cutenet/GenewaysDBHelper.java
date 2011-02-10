package org.geworkbench.components.cutenet;

import geneways.util.dag.DAGType;
import geneways.util.dag.impl.DAGTypeMemoryImpl;

import javax.jdo.PersistenceManager;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kodo.runtime.KodoPersistenceManager;

/**
 * Created by IntelliJ IDEA.
 * User: kk2457
 * Date: Aug 30, 2007
 * Time: 4:23:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenewaysDBHelper {
  PersistenceManager m_pm;

  Date m_minDate;
  Date m_maxDate;
  String m_schema = "geneways60test.";

  Set m_entityDatabaseIds;
  DAGType m_actionTypeDag;
  Map m_actionType2ActionCountMap;

  public GenewaysDBHelper(PersistenceManager pm) throws Exception {
    m_pm = pm;
    load();
  }

  void load() throws Exception {
    // the dates (shold be done like
    // select min(age), max(age) from geneways60test.action;
    // but because it takes time I'll fix them for now\
    Calendar cal = Calendar.getInstance();

    cal.set(1994, 1, 1);
    m_minDate = cal.getTime();

    cal.set(2003, 10, 1);
    m_maxDate = cal.getTime();


    // should be replaced with a query like
    // select distinct dbid from geneways60test.namedentity;
    m_entityDatabaseIds = new HashSet();
    m_entityDatabaseIds.add("disorders");
    m_entityDatabaseIds.add("flybase");
    m_entityDatabaseIds.add("locuslink");
    m_entityDatabaseIds.add("species");
    m_entityDatabaseIds.add("swissprot");

    loadActionTypes();
  }


  public void loadActionTypes() throws Exception {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;
    DAGTypeMemoryImpl dag = new DAGTypeMemoryImpl();
    m_actionType2ActionCountMap = new HashMap();

    boolean conceptsFount = false;
    try {
      KodoPersistenceManager kpm = (KodoPersistenceManager) m_pm;
      conn = (Connection) kpm.getConnection ();
      stmt = conn.createStatement ();
      rset = stmt.executeQuery ("select id, name, am_count from " + m_schema + "action_type");
      while(rset.next()) {
        int id = rset.getInt(1);
        String name = rset.getString(2);
        int count = rset.getInt(3);
        dag.addDAGValue(id, name);
        if (count > 0) {
          m_actionType2ActionCountMap.put(new Integer(id),
                                          new Integer(count));
        }

      }
      conceptsFount = true;
    } finally {
      if (rset != null) {
        try {
          rset.close();
        } catch (SQLException ex) {
        }
      }

      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
        }
      }
    }

    if (!conceptsFount) {
      if (conn != null)
        conn.close();
      return;
    }

    try {
      stmt = conn.createStatement ();
      rset = stmt.executeQuery ("select parent, child from " + m_schema + "action_type_dag");
      while(rset.next()) {
        dag.addDAGValueRelation(rset.getInt(1), rset.getInt(2));
      }
      m_actionTypeDag = dag;
    } finally {
      if (rset != null) {
        try {
          rset.close();
        } catch (SQLException ex) {
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
        }
      }

      if (conn != null)
        conn.close();
    }
  }

  public Date getMinDate() {
    return m_minDate;
  }

  public Date getMaxDate() {
    return m_maxDate;
  }

  public Set getEntityDatabaseIds() {
    return m_entityDatabaseIds;
  }

  public DAGType getActionTypeDAG() {
    return m_actionTypeDag;
  }

  public Map getActionType2ActionCountMap() {
    return m_actionType2ActionCountMap;
  }

}
