package org.geworkbench.components.tprofiler;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Collections;

/**
 * A panel that allows T-Profiler results to be viewed in a table.
 */
public class JProfileTable extends JPanel {

    javax.swing.table.TableModel microarrayTableModel = new AbstractTableModel(){

        public int getRowCount() {
            if (results == null) {
                return 0;
            }
            return results.getNumberOfGroups();
        }

        public int getColumnCount() {
            return 6;
        }


        public Object getValueAt(int rowIndex, int columnIndex) {
            if (results != null) {
                if (rowIndex == 1)
                {
                  getColumnName(columnIndex);
                }
                TProfilerGroup group = results.getGroup(rowIndex);
                if (columnIndex == 0) {
                    return group.getGroupName();
                } else if (columnIndex == 1) {
                    return group.getTValue();
                }
                else if (columnIndex == 2) {
                    return group.getPValue();
                } else if (columnIndex == 3) {
                    return group.getMean();
                }
                else if (columnIndex == 4) {
                    return group.getOrfCount();
                }
                else if (columnIndex == 5) {
                    return group.getOtherCount();
                }
            }
            return null;
        }


        public String getColumnName(int col) {

         if (col == 0) {
            return "GO Category";
                        }
         else if (col == 1) {
            return "t-value";
         }
         else if (col == 2) {
            return "E-value";
         }
         else if (col == 3) {
            return "Mean";
         }
         else if (col == 4) {
            return "ORFs";
         }
                return "";
        }




     };

    private class TableModel extends AbstractTableModel {

        public int getRowCount() {
            if (results == null) {
                return 0;
            }
            return results.getNumberOfGroups();
        }

        public int getColumnCount() {
            return 2;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (results != null) {
                TProfilerGroup group = results.getGroup(rowIndex);
                if (columnIndex == 0) {
                    return group.getGroupName();
                } else if (columnIndex == 1) {
                    return group.getTValue();
                }
            }
            return null;
        }
    }

    private TProfilerResults results;

    private JTable table;

    private TableModel model;

    private JTableHeader header;

    public JProfileTable() {
        super(new BorderLayout());
        model = new TableModel();

        table = new JTable(microarrayTableModel);

        table.setTableHeader(header);
        JScrollPane scrollpane = new JScrollPane(table);
        setColumnWidth(0,300);
        setColumnWidth(1,75);
        setColumnWidth(2,300);
        add(scrollpane, BorderLayout.CENTER);
    }

     public void setColumnWidth(int pColumn, int pWidth){
            //Get the column model.
            TableColumnModel colModel = table.getColumnModel();
            //Get the column at index pColumn, and set its preferred width.
            colModel.getColumn(pColumn).setPreferredWidth(pWidth);
      }

    public void setResults(TProfilerResults results) {
        this.results = results;
        model.fireTableStructureChanged();
    }

    public void sortByTValue() {
        results.sort((Collections.reverseOrder(TProfilerResults.T_VALUE_COMPARATOR)));
        model.fireTableDataChanged();
    }

    public void sortByName() {
        results.sort(TProfilerResults.NAME_COMPARATOR);
        model.fireTableDataChanged();
    }


}
