package org.geworkbench.components.cutenet;

import cgc.cutenet.database.view.DBId;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

/**
 * @author John Watkinson, Kaushal Kumar
 */
public class CutenetProtein {

    private int id;

    private DBId[] dbIds;
    private String name;
    private int toActionsCount;
    private int fromActionsCount;
    private int ActionsCount;
    //private String preferredName;

    // This list can just be filtered to those IDs that start with 'P'.
    private HashSet<String> swissProtIDs;

    public CutenetProtein(int id) {
        this.id = id;
        swissProtIDs = new HashSet<String>();
    }

    public CutenetProtein(ArrayList<DBId> dbIds, String name) {

        if(dbIds != null && dbIds.size() > 0){
            this.dbIds = new DBId[dbIds.size()];
            int i=0;
            for(Iterator itr=dbIds.iterator(); itr.hasNext(); ){
                this.dbIds[i++] = (DBId)itr.next();
            }
        }
        this.name = name;
        this.id = GenewaysWidget.nodeId;
        GenewaysWidget.nodeId++;
        swissProtIDs = new HashSet<String>();
    }

    public CutenetProtein(DBId[] dbIds, String name) {
        if(dbIds != null && dbIds.length > 0){
            this.dbIds = new DBId[dbIds.length];
            for(int i=0; i<dbIds.length; i++)
                this.dbIds[i] = dbIds[i];
        }
        this.name = name;
        this.id = GenewaysWidget.nodeId;
        GenewaysWidget.nodeId++;
        swissProtIDs = new HashSet<String>();
    }

    public CutenetProtein(String name) {
        this.name = name;
        this.id = GenewaysWidget.nodeId;
        GenewaysWidget.nodeId++;
        swissProtIDs = new HashSet<String>();
        //swissProtIDs = new HashSet<String>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Set<String> getSwissProtIDs() {
        return swissProtIDs;
    }

    public void addSwissProtID(String swissProtID) {
        swissProtIDs.add(swissProtID);
    }

    public DBId[] getDbIds() {
        return dbIds;
    }

    public void setDbIds(DBId[] dbIds) {
        this.dbIds = dbIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getToActionsCount() {
        return toActionsCount;
    }

    public void setToActionsCount(int toActionsCount) {
        this.toActionsCount = toActionsCount;
    }

    public int getFromActionsCount() {
        return fromActionsCount;
    }

    public void setFromActionsCount(int fromActionsCount) {
        this.fromActionsCount = fromActionsCount;
    }

    public int getActionsCount() {
        return ActionsCount;
    }

    public void setActionsCount(int actionsCount) {
        ActionsCount = actionsCount;
    }

}
