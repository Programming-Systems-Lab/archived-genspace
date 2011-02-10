/*
  The Broad Institute
  SOFTWARE COPYRIGHT NOTICE AGREEMENT
  This software and its documentation are copyright (2003-2010) by the
  Broad Institute/Massachusetts Institute of Technology. All rights are
  reserved.

  This software is supplied without any warranty or guaranteed support
  whatsoever. Neither the Broad Institute nor MIT can be responsible for its
  use, misuse, or functionality.
*/
package org.geworkbench.components.gpmodule.classification.wv;

import org.geworkbench.components.gpmodule.classification.GPTraining;
import org.geworkbench.components.gpmodule.classification.PredictionModel;
import org.geworkbench.components.gpmodule.classification.GPClassificationUtils;
import org.geworkbench.components.gpmodule.GPDataset;
import org.geworkbench.bison.algorithm.classification.CSClassifier;
import org.geworkbench.bison.datastructure.complex.panels.DSPanel;
import org.geworkbench.bison.datastructure.bioobjects.microarray.DSMicroarray;
import org.geworkbench.bison.annotation.DSAnnotationContext;
import org.geworkbench.bison.annotation.CSAnnotationContextManager;
import org.geworkbench.bison.annotation.CSAnnotationContext;
import org.geworkbench.util.ClassifierException;
import org.geworkbench.util.TrainingTask;
import org.geworkbench.util.TrainingProgressListener;
import org.genepattern.matrix.ClassVector;
import org.genepattern.webservice.Parameter;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
 * @author Marc-Danie Nazaire
 */
public class WVTraining extends GPTraining implements TrainingTask
{
    static Log log = LogFactory.getLog(WVTraining.class);

    TrainingProgressListener trainingProgressListener = null;
    private boolean cancelled = false;

    public WVTraining()
    {
        panel = new WVTrainingPanel(this);
        setDefaultPanel(panel);
    }

    protected CSClassifier trainClassifier(List<float[]> caseData, List<float[]> controlData, List<String> featureNames,
                                           List<String> caseArrayNames, List<String> controlArrayNames)
    {
        log.debug("Training classifier.");
       
        WVClassifier wvClassifier = null;

        try
        {
            if(controlData.size() == 0)
                throw new ClassifierException("Control data must be provided");

            if(caseData.size() == 0)
                throw new ClassifierException("Case data must be provided");

            if(trainingProgressListener != null)
                trainingProgressListener.stepUpdate("processing training parameters", 1);

            WVTrainingPanel wvPanel = (WVTrainingPanel)panel;
            DSAnnotationContext<DSMicroarray> context = CSAnnotationContextManager.getInstance().getCurrentContext(panel.getMaSet());
            DSPanel<DSMicroarray> casePanel = context.getActivatedItemsForClass(CSAnnotationContext.CLASS_CASE);

            DSPanel<DSMicroarray> controlPanel = context.getActivatedItemsForClass(CSAnnotationContext.CLASS_CONTROL);

            if(caseArrayNames == null || caseArrayNames.size() == 0)
                caseArrayNames = getArrayNames(casePanel);
            if(controlArrayNames == null || controlArrayNames.size() == 0)
                controlArrayNames = getArrayNames(controlPanel);

            //Create gct file
            GPDataset dataset = createGCTDataset(caseData, controlData, caseArrayNames,
                                                      controlArrayNames);

            File trainingDataFile;

            try
            {
                String fileName = GPClassificationUtils.createGCTFile(dataset, "WV_Data");
                trainingDataFile = new File(fileName);
                trainingDataFile.deleteOnExit();                
            }
            catch(IOException io)
            {
                io.printStackTrace();
                throw new ClassifierException("An error occurred when training SVM classifier");
            }

            //Create cls file
            ClassVector clsVector = createClassVector(caseData, controlData);
            File clsData = GPClassificationUtils.createCLSFile("WV_Cls", clsVector);

            List parameters = new ArrayList();
            parameters.add(new Parameter("train.filename", trainingDataFile.getAbsolutePath()));
            parameters.add(new Parameter("train.class.filename", clsData.getAbsolutePath()));
            parameters.add(new Parameter("model.file", ++modelCount + trainingDataFile.getName()));
            
            if(wvPanel.useFeatureFileMethod())
            {
                String featureFile = wvPanel.getFeatureFile();
                validateFeatureFile(featureFile, Arrays.asList(dataset.getRowNames()));
                parameters.add(new Parameter("feature.list.filename", featureFile));
            }
            else
            {                
                int numFeatures = wvPanel.getNumFeatures();
                String statistic = wvPanel.getStatistic();
                boolean useMedian = wvPanel.useMedian();

                boolean useStdDev = wvPanel.useMinStdDev();
                int stat = getStatistic(statistic, useMedian, useStdDev);

                parameters.add(new Parameter("num.features", numFeatures));
                parameters.add(new Parameter("feature.selection.statistic", stat));

                if(useStdDev)
                    parameters.add(new Parameter("min.std", wvPanel.getMinStdDev()));
            }

            if(trainingProgressListener != null)
                trainingProgressListener.stepUpdate("training classifier", 2);

            File modelFile = trainData("WeightedVoting", (Parameter[])parameters.toArray(new Parameter[0]));
            PredictionModel predModel = createModel(modelFile);

            wvClassifier = new WVClassifier(null, "WV Classifier", new String[]{"Positive", "Negative"}, 
                            predModel, dataset, casePanel, controlPanel);
            wvClassifier.setPassword(((WVTrainingPanel)panel).getPassword());

            if(trainingProgressListener != null)
                trainingProgressListener.stepUpdate("classifier trained", 3);

            trainingProgressListener = null;
        }
        catch(ClassifierException e)
        {
            JOptionPane.showMessageDialog(panel, e.getMessage());
            log.warn(e);
        }
        return wvClassifier;
    }

    public TrainingProgressListener getTrainingProgressListener()
    {
        return trainingProgressListener;
    }

    public void setTrainingProgressListener(TrainingProgressListener trainingProgressListener)
    {
        this.trainingProgressListener = trainingProgressListener;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
}
