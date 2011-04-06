package org.geworkbench.parsers;

import org.geworkbench.bison.datastructure.biocollections.DSDataSet;
import org.geworkbench.bison.datastructure.bioobjects.DSBioObject;

import java.io.InterruptedIOException;

import java.io.File;

/**
 * <p>
 * Title: Sequence and Pattern Plugin
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version $Id: DataSetFileFormat.java 7542 2011-03-04 19:33:50Z zji $
 */

public abstract class DataSetFileFormat extends FileFormat {
	public DataSetFileFormat() {
	}

	abstract public DSDataSet<? extends DSBioObject> getDataFile(File file)
			throws InputFileFormatException, InterruptedIOException;

	public DSDataSet<? extends DSBioObject> getDataFile(File file,
			String compatibilityLabel) throws InputFileFormatException,
			InterruptedIOException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	abstract public DSDataSet<? extends DSBioObject> getDataFile(File[] files)
			throws InputFileFormatException;

}
