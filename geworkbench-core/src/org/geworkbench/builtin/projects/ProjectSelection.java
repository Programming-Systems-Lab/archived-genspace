package org.geworkbench.builtin.projects;

import org.geworkbench.bison.datastructure.biocollections.DSAncillaryDataSet;
import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.bioobjects.markers.annotationparser.AnnotationParser;
import org.geworkbench.bison.datastructure.bioobjects.structure.CSProteinStructure;
import org.geworkbench.engine.config.rules.GeawConfigObject;
import org.geworkbench.events.ProjectEvent;

/**
 * <p>Title: Sequence and Pattern Plugin</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version $Id: ProjectSelection.java 8348 2011-09-27 20:18:47Z zji $
 */

public class ProjectSelection {
    private ProjectNode selectedProjectNode = null;
    private DataSetNode selectedDataSetNode = null;
    private DataSetSubNode selectedDataSetSubNode = null;
    private ProjectTreeNode selectedNode = null;

    /**
     * Returns whether the selections have all been cleared
     *
     * @return
     */
    public boolean areNodeSelectionsCleared() {
        return (selectedProjectNode == null);
    }

    /**
     * Clears the selections and broadcasts the event
     */
    public void clearNodeSelections() {
        selectedProjectNode = null;
        selectedDataSetNode = null;
        selectedDataSetSubNode = null;
        selectedNode = null;

        throwEvent(ProjectEvent.CLEARED);
    }


    // Access to various selection variables
    public ProjectNode getSelectedProjectNode() {
        return selectedProjectNode;
    }

    public DataSetNode getSelectedDataSetNode() {
        return selectedDataSetNode;
    }

    public DataSetSubNode getSelectedDataSetSubNode() {
        return selectedDataSetSubNode;
    }

    public ProjectTreeNode getSelectedNode() {
        return selectedNode;
    }

    @SuppressWarnings("rawtypes")
	public DSDataSet getDataSet() {
        if (selectedDataSetNode != null) {
            return selectedDataSetNode.getDataset();
        } else {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
	public DSAncillaryDataSet getDataSubSet() {
        if (selectedDataSetSubNode != null) {
            return selectedDataSetSubNode._aDataSet;
        } else {
            return null;
        }
    }

    /**
     * Finds the project node associated with this node of the given class
     *
     * @param parentPath
     * @return
     */
    private ProjectTreeNode getNodeOfClass(ProjectTreeNode node, Class<?> aClass) {
        if (node == null) {
            return null;
        }
        while (!(aClass.isInstance(node))) {
            node = (ProjectTreeNode) node.getParent();
            if (node == null) {
                return null;
            }
        }
        return (ProjectTreeNode) node;
    }

    /**
     * Assigns a new selection node
     *
     * @param pNode
     * @param node
     */
    public void setNodeSelection(ProjectTreeNode node) {
        if (selectedNode != node) {
            selectedNode = node;

            selectedProjectNode = (ProjectNode) getNodeOfClass(node, ProjectNode.class);
           
            if (node instanceof DataSetNode) {
                selectedDataSetNode = (DataSetNode) node;
                AnnotationParser.setCurrentDataSet(selectedDataSetNode.getDataset());
                GeawConfigObject.getGuiWindow().setVisualizationType(selectedDataSetNode.getDataset());
                checkProjectNode();
                if(selectedDataSetNode.getDataset() instanceof CSProteinStructure){
                	throwEvent(ProjectEvent.CLEARED);
                }
                else throwEvent(ProjectEvent.SELECTED);
            } else if (node instanceof DataSetSubNode) {
                selectedDataSetSubNode = (DataSetSubNode) node;
                selectedDataSetNode = (DataSetNode) getNodeOfClass(node, DataSetNode.class);
                AnnotationParser.setCurrentDataSet(selectedDataSetNode.getDataset());//Fix bug 1471
                GeawConfigObject.getGuiWindow().setVisualizationType(selectedDataSetSubNode._aDataSet);
                checkProjectNode();
                throwSubNodeEvent("receiveProjectSelection");
            } else if (node instanceof PendingTreeNode) {
                selectedDataSetNode = (DataSetNode) getNodeOfClass(node, DataSetNode.class);
                AnnotationParser.setCurrentDataSet(selectedDataSetNode.getDataset());
                GeawConfigObject.getGuiWindow().setVisualizationType(null);
                checkProjectNode();
                throwEvent(ProjectEvent.SELECTED);
            } else  {
                selectedDataSetNode = null;
                selectedDataSetSubNode = null;
                GeawConfigObject.getGuiWindow().setVisualizationType(null);                
                checkProjectNode();
                ProjectPanel.getInstance().publishProjectEvent(new ProjectEvent(ProjectEvent.CLEARED, null, null));                
            }            
        }
    }

    /**
     * Checks that the selections are correctly assigned at the project level
     */
    private void checkProjectNode() {
        if (selectedProjectNode == null) {
            selectedDataSetNode = null;
            selectedDataSetSubNode = null;
        } else {
            checkDataSetNode();
        }
    }

    /**
     * Checks that the selections are correctly assigned at the DataSet level
     */
    private void checkDataSetNode() {
        if (selectedDataSetNode == null) {
            selectedDataSetSubNode = null;
        } else {
            if (selectedDataSetNode.getParent() != selectedProjectNode) {
                selectedDataSetNode = null;
                selectedDataSetSubNode = null;
            } else {
                checkDataSetSubNode();
            }
        }
    }

    /**
     * Checks that the selections are correctly assigned at the DataSetSub level
     */
    private void checkDataSetSubNode() {
        if (selectedDataSetSubNode != null) {
            if (selectedDataSetSubNode.getParent() != selectedDataSetNode) {
                selectedDataSetSubNode = null;
            }
        }
    }

    /**
     * Throws the corresponding message event
     *
     * @param message
     */
	private void throwEvent(String message) {
		if (selectedDataSetNode != null) {
			ProjectPanel.getInstance().publishProjectEvent(
					new ProjectEvent(message, selectedDataSetNode.getDataset(),
							selectedDataSetNode));
		}
	}

	@SuppressWarnings("unchecked")
	private void throwSubNodeEvent(String message) {
		if ((selectedDataSetSubNode != null)
				&& (selectedDataSetSubNode._aDataSet != null)) {
			ProjectPanel.getInstance().publishProjectEvent(
					new ProjectEvent(message, selectedDataSetSubNode._aDataSet,
							selectedDataSetSubNode));
		}
	}
}
