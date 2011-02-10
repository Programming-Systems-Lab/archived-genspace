package org.geworkbench.components.classification.smlr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.algorithms.AbstractTrainingPanel;
import org.geworkbench.bison.algorithm.classification.CSClassifier;
import org.geworkbench.events.listeners.ParameterActionListener;
import org.geworkbench.util.ClassifierException;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class SMLRTrainingPanel extends AbstractTrainingPanel {

    static Log log = LogFactory.getLog(SMLRTrainingPanel.class);

    private static final double DEFAULT_EPSILON_VALUE = 0.001;
    private static final double DEFAULT_GAMMA_VALUE = 1;

    private JFormattedTextField epsilon = new JFormattedTextField();
    private JComboBox priorCombo = new JComboBox(SMLRTraining.PRIORS);
    private JComboBox kernelFunctionCombo = new JComboBox(SMLRTraining.KERNELS);;
    private JFormattedTextField gamma = new JFormattedTextField();

    private SMLRTraining smlrTraining;

	/*
	 * (non-Javadoc)
	 * @see org.geworkbench.analysis.AbstractSaveableParameterPanel#setParameters(java.util.Map)
	 * Set inputed parameters to GUI.
	 */
    public void setParameters(Map<Serializable, Serializable> parameters){
    	if (parameters == null) return;
        Set<Map.Entry<Serializable, Serializable>> set = parameters.entrySet();
        for (Iterator<Map.Entry<Serializable, Serializable>> iterator = set.iterator(); iterator.hasNext();) {
        	Map.Entry<Serializable, Serializable> parameter = iterator.next();
			Object key = parameter.getKey();
			Object value = parameter.getValue();
			if (key.equals("Prior")){
	            this.setPrior((String)value);
			}
			if (key.equals("Epsilon")){
	            this.epsilon.setValue((Number)value);
			}
			if (key.equals("SelectedKernel")){
	            this.setSelectedKernel((String)value);
			}
			if (key.equals("Gamma")){
	            this.gamma.setValue((Number)value);
			}
		}
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.geworkbench.analysis.AbstractSaveableParameterPanel#getParameters()
	 *      Since HierClustPanel only has three parameters, we return metric,
	 *      dimension and method in the format same as getBisonParameters().
	 */
    public Map<Serializable, Serializable> getParameters() {
		Map<Serializable, Serializable> parameters = new HashMap<Serializable, Serializable>();

		parameters.put("Prior", getPrior());
		parameters.put("Epsilon", (Number) epsilon.getValue());
		parameters.put("SelectedKernel", getSelectedKernel());
		parameters.put("Gamma", (Number)gamma.getValue());
		return parameters;
	}
    
    public SMLRTrainingPanel(SMLRTraining smlrTraining) {
        this.smlrTraining = smlrTraining;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initUI() {
        epsilon = new JFormattedTextField(new DecimalFormat("0.#####E0"));
        epsilon.setValue(SMLRTrainingPanel.DEFAULT_EPSILON_VALUE);
        gamma = new JFormattedTextField(new DecimalFormat("0.#####E0"));
        gamma.setEnabled(false);
        gamma.setValue(SMLRTrainingPanel.DEFAULT_GAMMA_VALUE);

        kernelFunctionCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kernelFunctionCombo.getSelectedItem().equals(SMLRTraining.KERNEL_RBF.toString())) {
                    gamma.setEnabled(true);
                } else {
                    gamma.setEnabled(false);
                }
            }
        });
        ParameterActionListener parameterActionListener = new ParameterActionListener(this);
        epsilon.addPropertyChangeListener(parameterActionListener);
        gamma.addPropertyChangeListener(parameterActionListener);
        kernelFunctionCombo.addPropertyChangeListener(parameterActionListener);
        priorCombo.addPropertyChangeListener(parameterActionListener);
    }

    protected CSClassifier trainForValidation(java.util.List<float[]> trainingCaseData, java.util.List<float[]> trainingControlData) throws ClassifierException {
        return smlrTraining.trainClassifier(trainingCaseData, trainingControlData);
    }

    protected void addParameters(DefaultFormBuilder builder) {
        builder.appendSeparator("SMLR Parameters");

        builder.append("Prior", priorCombo);
        builder.append("Epsilon", epsilon);
        builder.append("Kernel Function", kernelFunctionCombo);
        builder.append("Gamma", gamma);
    }

    public float getEpsilon() {
        return ((Number) epsilon.getValue()).floatValue();
    }

    public String getPrior() {
        return SMLRTraining.PRIORS[priorCombo.getSelectedIndex()];
    }

    public void setPrior(String prior) {
        priorCombo.setSelectedItem(prior);
    }

    public float getGamma() {
        return ((Number) gamma.getValue()).floatValue();
    }

    public String getSelectedKernel() {
        return SMLRTraining.KERNELS[kernelFunctionCombo.getSelectedIndex()];
    }

    public void setSelectedKernel(String kernel) {
		kernelFunctionCombo.setSelectedItem(kernel);
    }

	@Override
	public void fillDefaultValues(Map<Serializable, Serializable> parameters) {
		// TODO Auto-generated method stub
		
	}

}
