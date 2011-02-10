package org.geworkbench.components.classification.svm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geworkbench.algorithms.AbstractTrainingPanel;
import org.geworkbench.bison.algorithm.classification.CSClassifier;
import org.geworkbench.util.ClassifierException;
import org.geworkbench.util.svm.KernelFunction;
import org.geworkbench.util.svm.SupportVectorMachine;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class SVMTrainingPanel extends AbstractTrainingPanel {

    static Log log = LogFactory.getLog(SVMTrainingPanel.class);

    private static final double DEFAULT_EPSILON_VALUE = 0.001;
    private static final double DEFAULT_C_VALUE = 1;
    private static final double DEFAULT_GAMMA_VALUE = 1;

    private JFormattedTextField epsilon = new JFormattedTextField();
    private JFormattedTextField c = new JFormattedTextField();
    private JComboBox kernelFunctionCombo = new JComboBox();
    private JFormattedTextField gamma = new JFormattedTextField();

    public SVMTrainingPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initUI() {
        epsilon = new JFormattedTextField(new DecimalFormat("0.#####E0"));
        epsilon.setValue(DEFAULT_EPSILON_VALUE);
        c = new JFormattedTextField(new DecimalFormat());
        c.setValue(DEFAULT_C_VALUE);
        gamma = new JFormattedTextField(new DecimalFormat("0.#####E0"));
        gamma.setEnabled(false);
        gamma.setValue(DEFAULT_GAMMA_VALUE);
        numberFolds = new JFormattedTextField(3);
        kernelFunctionCombo.addItem(SupportVectorMachine.LINEAR_KERNAL_FUNCTION.toString());
        kernelFunctionCombo.addItem(SupportVectorMachine.LINEAR_KERNAL_FUNCTION_2ND_POWER.toString());
        kernelFunctionCombo.addItem(SupportVectorMachine.RADIAL_BASIS_KERNEL.toString());

        kernelFunctionCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kernelFunctionCombo.getSelectedItem().equals(SupportVectorMachine.RADIAL_BASIS_KERNEL.toString())) {
                    gamma.setEnabled(true);
                } else {
                    gamma.setEnabled(false);
                }
            }
        });
    }

    protected CSClassifier trainForValidation(java.util.List<float[]> trainingCaseData, java.util.List<float[]> trainingControlData) throws ClassifierException {
        SupportVectorMachine svm = new SupportVectorMachine(trainingCaseData, trainingControlData, getSelectedKernel(), 0.1f);
        setTrainingTask(svm);
        // Non-SMO
        // svm.buildSupportVectors(1000, 1e-6);
        // SMO
        svm.buildSupportVectorsSMO(((Number) c.getValue()).floatValue(), ((Number) epsilon.getValue()).floatValue());
        CSClassifier classifier = svm.getClassifier(null, "");
        return classifier;
    }

    protected void addParameters(DefaultFormBuilder builder) {
        builder.appendSeparator("Support Vector Machine Parameters");

        builder.append("Epsilon", epsilon);
        builder.append("Alpha Bound (C)", c);
        builder.append("Kernel Function", kernelFunctionCombo);
        builder.append("Gamma", gamma);
    }

    public float getEpsilon() {
        return ((Number) epsilon.getValue()).floatValue();
    }

    public float getC() {
        return ((Number) c.getValue()).floatValue();
    }

    public KernelFunction getSelectedKernel() {
        if (kernelFunctionCombo.getSelectedItem().equals(SupportVectorMachine.LINEAR_KERNAL_FUNCTION.toString())) {
            return SupportVectorMachine.LINEAR_KERNAL_FUNCTION;
        } else if (kernelFunctionCombo.getSelectedItem().equals(SupportVectorMachine.LINEAR_KERNAL_FUNCTION_2ND_POWER.toString())) {
            return SupportVectorMachine.LINEAR_KERNAL_FUNCTION_2ND_POWER;
        } else if (kernelFunctionCombo.getSelectedItem().equals(SupportVectorMachine.RADIAL_BASIS_KERNEL.toString())) {
            return SupportVectorMachine.RADIAL_BASIS_KERNEL;
        }
        return null;
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

