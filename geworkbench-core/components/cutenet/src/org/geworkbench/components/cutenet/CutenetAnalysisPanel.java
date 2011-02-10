package org.geworkbench.components.cutenet;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.analysis.AbstractSaveableParameterPanel;
import org.geworkbench.bison.datastructure.biocollections.microarrays.DSMicroarraySet;

/**
 * Created by IntelliJ IDEA.
 * User: kk2457
 * Date: Nov 30, 2006
 * Time: 3:59:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CutenetAnalysisPanel extends AbstractSaveableParameterPanel {
	private Log log = LogFactory.getLog(this.getClass());
    private DSMicroarraySet maSet = null;

    public CutenetAnalysisPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        setLayout(new BorderLayout());
        
    }

    public void setMaSet(DSMicroarraySet maSet) {
        this.maSet = maSet;
    }

    /*
     * (non-Javadoc)
     * @see org.geworkbench.analysis.AbstractSaveableParameterPanel#getParameters()
     */
	public Map<Serializable, Serializable> getParameters() {
		// TODO Auto-generated method stub
		log.error(new OperationNotSupportedException("Please implement getParameters()"));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.geworkbench.analysis.AbstractSaveableParameterPanel#setParameters(java.util.Map)
	 */
	public void setParameters(Map<Serializable, Serializable> parameters) {
		// TODO Auto-generated method stub
		log.error(new OperationNotSupportedException("Please implement setParameters()"));
	}

	@Override
	public void fillDefaultValues(Map<Serializable, Serializable> parameters) {
		// TODO Auto-generated method stub
		
	}

}
