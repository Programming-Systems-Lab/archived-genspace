package org.geworkbench.bison.model.clusters;

import org.apache.commons.collections15.map.HashedMap;
import org.geworkbench.bison.util.DefaultIdentifiable;

import java.lang.reflect.Array;
import java.util.*;
import java.io.Serializable;

/**
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: First Genetic Trust Inc.</p>
 * <p/>
 * Default implementation of the <code>Cluster</code> interface.
 *
 * @author First Genetic Trust
 * @version $Id: AbstractCluster.java 7365 2010-12-22 15:21:22Z zji $
 */
public abstract class AbstractCluster implements Cluster, Serializable {

	private static final long serialVersionUID = 2957512324725164458L;
	
	/**
     * Holds all children of this <code>Cluster</code>
     */
    protected ArrayList<Cluster> children = new ArrayList<Cluster>();
    /**
     * Reference to <code>Cluster</code> which contains this <code>Cluster</code>
     * as a child
     */
    protected Cluster parent = null;

    /**
     * Unique ID acting as a key for this <code>Cluster</code>
     */
    protected org.geworkbench.bison.util.DefaultIdentifiable clusterID = new DefaultIdentifiable();

    /**
     * Gets the <code>Cluster</code> which contains this <code>Cluster</code> as
     * a child. Returns <code>null</code> is this node is the root.
     *
     * @return parent
     */
    public Cluster getParent() {
        return parent;
    }

    /**
     * Adds a <code>Cluster</code> node as a child of this <code>Cluster</code>
     *
     * @param newCluster added as child
     */
    public void addNode(Cluster newCluster) {
        if (newCluster != null) {
            children.add(newCluster);
            ((AbstractCluster) newCluster).parent = this;
        }

    }

    /**
     * Specifes if this <code>Cluster</code> is a leaf. i.e. this <code>Cluster
     * </code> does not contain any children
     *
     * @return if this node is a leaf
     */
    public boolean isLeaf() {
        return (children.size() == 0 ? true : false);
    }

    /**
     * Gets all the children <code>Cluster</code> nodes of this node
     *
     * @return children nodes as an array
     */
    public Cluster[] getChildrenNodes() {
        Cluster[] toBeReturned = (Cluster[]) Array.newInstance(this.getClass(), children.size());
        if (children.size() == 0)
            return null;
        children.toArray(toBeReturned);
        return toBeReturned;
    }

    /**
     * Gets the number of children of this <code>Cluster</code>
     *
     * @return number of children
     */
    public int getNodesCount() {
        return children.size();
    }

    /**
     * Gets all the children of this <code>Cluster</code> that are also leaves
     *
     * @return all the leaf children as an array
     */
    public List<Cluster> getLeafChildren() {
        List<Cluster> leafList = new ArrayList<Cluster>();
        Set<Cluster> visited = new HashSet<Cluster>();
        Stack<Cluster> stack = new Stack<Cluster>();

        Cluster end = new DefaultHierCluster();
        stack.push(end);
        Cluster thisnode = this;
        while (thisnode != end) {
            List<Cluster> children = ((AbstractCluster) thisnode).children;
            if (children.size() == 0) {
                // Is a leaf
                //                System.out.println("Leaf node: "+thisnode.getID());
                leafList.add(thisnode);
            }

            // decide what node to work on now
            boolean found = false;
            for (Cluster child : children) {
                if (!visited.contains(child)) {
                    stack.push(thisnode);
                    visited.add(thisnode);
                    thisnode = child;
                    found = true;
                    break;
                }
            }
            if (!found) {
                visited.add(thisnode);
                thisnode = stack.pop();
            }
        }
        return leafList;
    }

    /**
     * Gets the number of children of this <code>Cluster</code> that are also
     * leaves
     *
     * @return number of leaf children
     */
    public int getLeafChildrenCount() {
        int count = getLeafChildren().size();
        if (count == 0) {
            return 1;
        } else {
            return count;
        }
    }

    public Map<Cluster, Integer> getLeafChildrenCountMap() {
        Map<Cluster, Integer> map = new HashedMap<Cluster, Integer>();
        Set<Cluster> visited = new HashSet<Cluster>();
        Stack<Cluster> stack = new Stack<Cluster>();

        Cluster end = new DefaultHierCluster();
        stack.push(end);
        Cluster thisnode = this;
        while (thisnode != end) {
            List<Cluster> children = ((AbstractCluster) thisnode).children;
            if (children.size() == 0) {
                // Is a leaf
//                System.out.println("Leaf node: "+thisnode.getID());
                map.put(thisnode, 0);
            } else if (visited.containsAll(children)) {
                // Total children scores, add children then add to map
                int total = 0;
                for (Cluster cluster : children) {
                    if (((AbstractCluster) cluster).children.size() == 0) {
                        // Only add a child to the count if it is a leaf
                        total++;
                    }
                    total += map.get(cluster);
                }
//                System.out.println("Node "+thisnode.getID()+" subtotal "+total);
                map.put(thisnode, total);
            }

            // decide what node to work on now
            boolean found = false;
            for (Cluster child : children) {
                if (!visited.contains(child)) {
                    stack.push(thisnode);
                    visited.add(thisnode);
                    thisnode = child;
                    found = true;
                    break;
                }
            }
            if (!found) {
                visited.add(thisnode);
                thisnode = stack.pop();
            }
        }
        return map;
    }

    /**
     * Gets the Unique ID of this <code>Cluster</code>
     *
     * @return unique ID
     */
    public String getID() {
        return clusterID.getID();
    }

    /**
     * Sets the Unique ID of this <code>Cluster</code>
     *
     * @param id unique ID
     */
    public void setID(String id) {
        clusterID.setID(id, "Cluster");
    }

}
