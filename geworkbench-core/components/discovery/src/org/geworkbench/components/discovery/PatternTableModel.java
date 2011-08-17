package org.geworkbench.components.discovery;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.SwingWorker;
import javax.swing.table.AbstractTableModel;

import org.geworkbench.bison.datastructure.complex.pattern.sequence.DSMatchedSeqPattern;

/**
 * <p>Title: Sequence and Pattern Plugin</p>
 * <p>Description: The model holds the current displayed patterns in the table.
 * The model gets its pattern from a PatternSource class.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version $Id: PatternTableModel.java 8202 2011-08-04 20:52:12Z zji $
 */

public class PatternTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 9144326847214513997L;
	/**
     * Column definition
     */
    static final int PTMSupport = 0;
    static final int PTMSeqNo = 1;
    static final int PTMTokNo = 2;
    static final int PTMZScore = 3;
    static final int PTMPattern = 4;
    static final String[] headerName = {"Hits", "Sequences Hit", "# of Tokens", "ZScore", "Motif"};
    /**
     * Pattern array - contains the patterns.
     */
    private ArrayList<DSMatchedSeqPattern> pattern = new ArrayList<DSMatchedSeqPattern>();

    /**
     * Used to format the pValue/zScore field.
     */
    static private DecimalFormat fmt = new DecimalFormat("0.#E0##");

    /**
     * Maximum extension of all the patterns in the model.
     */
    private int maxLen = 0;

    /**
     * The patterns for this model will come from this source.
     */
    private org.geworkbench.util.patterns.SequentialPatternSource patternSource = null;

    /**
     * Number of rows in the model.
     * Note: the actual number of patterns may be smaller than rowCount.
     * This is done so we do no need to get all patterns if they are not "used",
     * i.e. requested from the model.
     */
    private int rowCount;

    /**
     * Will retrieve patterns from a Patterns Source.
     */
    private PatternTableModelWorker worker = null;

    /**
     * Set the pattern source for this model.
     *
     * @param source
     */
    public synchronized void setPatternSource(org.geworkbench.util.patterns.SequentialPatternSource source) {
        patternSource = source;
    }

    /**
     * Sort the patterns in the model on field
     */
    public void sort(int field) {
        if (patternSource != null) {
            //we are sorting outside the model
            //clear what patterns we have now in the model
            clear();
            patternSource.sort(field);
        }
    }

    /**
     * Mask the patterns of this model
     *
     * @param indeces to mask.
     * @param mask    operation
     */
    public void mask(int[] index, boolean maskOperation) {
        patternSource.mask(index, maskOperation);
    }

    /**
     * See  javax.swing.table.TableModel
     *
     * @return number of columns in the model
     */
    public int getColumnCount() {
        return headerName.length;
    }

    /**
     * See  javax.swing.table.TableModel
     *
     * @return number of columns in the model
     */
    public synchronized int getRowCount() {
        return rowCount;
    }

    /**
     * Removes all existing patterns from the model
     */
    public synchronized void clear() {
        pattern.clear();
    }

    /**
     * See  javax.swing.table.TableModel
     *
     * @param columnIndex the index of the column
     * @return the name of the column
     */
    public String getColumnName(int columnIndex) {
        return headerName[columnIndex];
    }

    public synchronized Object getValueAt(int row, int col) {
        DSMatchedSeqPattern pattern = null;
        Object cell = null;
        pattern = getPatternNoBlock(row);
        if (pattern != null) {
            switch (col) {
                case PTMSupport:
                    cell = new Integer(pattern.getSupport());
                    break;
                case PTMSeqNo:
                    cell = new Integer(pattern.getUniqueSupport());
                    break;
                case PTMTokNo:
                    cell = new Integer(pattern.getLength());
                    break;
                case PTMZScore:
                    cell = fmt.format(pattern.getPValue());
                    break;
                case PTMPattern:
                    cell = pattern.getASCII();
                    break;
            }
        } else {
            //the pattern is not in the model, yet.
            cell = (col == PTMSupport) ? "loading" : "...";
        }
        return cell;
    }

    /**
     * Set the number of rows for the model.
     *
     * @param rowNum number of rows.
     */
    public synchronized void setRowCount(int rowNum) {
        rowCount = rowNum;
    }

    /**
     * Get the pattern at the index row. This method will block until
     * the pattern is retrived from the underline source. See setPatternSource.
     *
     * @param row
     * @return
     */
    public synchronized DSMatchedSeqPattern getPattern(int row) {
        //get the pattern from a Source if needed.
        if ((row < 0) || (row > rowCount - 1)) {
            throw new IndexOutOfBoundsException("[row=" + row + ", rowCount=" + rowCount + "]");
        }

        if ((pattern.size() <= row) || (pattern.get(row) == null)) {
            org.geworkbench.util.patterns.CSMatchedSeqPattern p = (org.geworkbench.util.patterns.CSMatchedSeqPattern) patternSource.getPattern(row);
            while (pattern.size() < row) {
                pattern.add(null);
            }
            pattern.add(row, p);
            computeMaxLength(p);
        }
        return (DSMatchedSeqPattern) pattern.get(row);
    }

    private DSMatchedSeqPattern getPatternNoBlock(int row) {
        org.geworkbench.util.patterns.CSMatchedSeqPattern pat = null;
        if (row < pattern.size()) {
            pat = (org.geworkbench.util.patterns.CSMatchedSeqPattern) pattern.get(row);
        }

        if (pat == null) {
            if ((worker == null) || worker.isDone()) {
                //if the initial condition where no patterns are available
                //create a subclass of SwingWorker and let it retrieve the pattern.
                worker = new PatternTableModelWorker(row);
                worker.execute();
            }
        }
        return pat;
    }

    private void computeMaxLength(DSMatchedSeqPattern pattern) {
        maxLen = Math.max(maxLen, pattern.getMaxLength());
    }

    private class PatternTableModelWorker extends SwingWorker<Void, Void> {
        int row;

        public PatternTableModelWorker(int row) {
            this.row = row;
        }

        /**
         * Main work of the SwingWorker is in this method
         * SwingWorker retrieves a new Pattern from the server.
         * new Pattern is also set with the ASCII value of the pattern
         */
    	@Override
    	protected Void doInBackground() throws Exception {
    		getPattern(row);
    		return null;
        }

        /**
         * PatternTableModelWorker will fireTableDataChanged when everything finished
         */
    	@Override
        public void done() {
            fireTableDataChanged();
        }

    }

}
