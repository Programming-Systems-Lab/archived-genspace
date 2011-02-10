package org.geworkbench.components.simulation.networkGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.HashMap;
import org.geworkbench.util.Util;
import org.systemsbiology.chem.Model;
import org.systemsbiology.chem.sbml.ModelBuilderMarkupLanguage;
import org.systemsbiology.util.IncludeHandler;

/**
 *
 * <p>Title: </p>
 * <p>Description: Generates a network from an SBML file</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Adam Margolin
 * @version 1.0
 */

public class SbmlNetworkGenerator extends NetworkGeneratorBase{
  File sbmlFile = null;

  public SbmlNetworkGenerator() {
  }

    public void setSbmlFile(File sbmlFile) {
        this.sbmlFile = sbmlFile;
    }

    public File getSbmlFile() {
        return sbmlFile;
    }



  /**
   * initialize
   *
   * @param propsMap HashMap
   */

  public void initialize(HashMap propsMap, int one, int two, int three, int four, double five, double six, double seven, double eight, int[] inputs, int[] outputs, double K, double metrop, double actPercent) {
      String sbmlFileName = Util.getString(propsMap, "SbmlFileName");
      this.sbmlFile = new File(sbmlFileName);
    }

  public void initialize(HashMap propsMap) {
    String sbmlFileName = Util.getString(propsMap, "SbmlFileName");
    this.sbmlFile = new File(sbmlFileName);
  }

  /**
   * generateNetwork
   *
   * @return Model
   */
  public Model generateNetwork() {
    return createModelFromSbmlFile(sbmlFile);
  }

  public Model createModelFromSbmlFile(File sbmlFile) {
    Model model = null;
    try {
      // Read the SBML file
      BufferedReader in = new BufferedReader(new FileReader(sbmlFile));
      String read = in.readLine();
      StringBuffer sbml = new StringBuffer();

      while (read != null) {
        sbml.append(read);
        read = in.readLine();
        if (read != null) {
          sbml.append("\n");
        }
      }

      ModelBuilderMarkupLanguage modelBuilder = new ModelBuilderMarkupLanguage();

      IncludeHandler fileIncludeHandler = null;
      StringReader inputStringReader = new StringReader(sbml.toString());
      BufferedReader bufferedInputReader = new BufferedReader(inputStringReader);

      model = modelBuilder.buildModel(bufferedInputReader,
                                      fileIncludeHandler);

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return model;
  }

}
