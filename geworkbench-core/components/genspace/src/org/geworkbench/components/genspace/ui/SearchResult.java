package org.geworkbench.components.genspace.ui;
import org.geworkbench.components.genspace.datamanagement.*;
import org.geworkbench.components.genspace.workflowRepository.WorkflowRepository;

/*
Author: Aditya
*/
import javax.swing.*;  
import java.awt.event.*;  
import java.io.*;
import java.awt.*;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.text.*;





public class SearchResult extends JPanel implements java.io.Serializable
{

  private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

  private static final String ADD_BUTTON_LABEL = "Add >>   ";

  private static final String REMOVE_BUTTON_LABEL = "<< Remove";

  private static final String DEFAULT_SOURCE_CHOICE_LABEL = "AVAILABLE SEARCH";

  private static final String DEFAULT_DEST_CHOICE_LABEL = "YOUR SELECTION";
  
  private int count =0, index = 0;
  
  private static JTextField searchtext;
  
  private JProgressBar progressBar;
  
  private static JComboBox database;

  private JLabel sourceLabel;

  private JList sourceList;

  private SortedListModel sourceListModel;

  private JList destList;

  private SortedListModel destListModel;

  private JLabel destLabel;

  private JButton addButton;

  private JButton removeButton;
  
  private static JButton search;
  
  private static JButton save;
  
  private Fetch c = new Fetch();

  public SearchResult() {
    initScreen();
  }

  public String getSourceChoicesTitle() {
    return sourceLabel.getText();
  }

  public void setSourceChoicesTitle(String newValue) {
    sourceLabel.setText(newValue);
  }

  public String getDestinationChoicesTitle() {
    return destLabel.getText();
  }

  public void setDestinationChoicesTitle(String newValue) {
    destLabel.setText(newValue);
  }

  public void clearSourceListModel() {
    sourceListModel.clear();
  }

  public void clearDestinationListModel() {
    destListModel.clear();
  }

  public void addSourceElements(ListModel newValue) {
    fillListModel(sourceListModel, newValue);
  }

  public void setSourceElements(ListModel newValue) {
    clearSourceListModel();
    addSourceElements(newValue);
  }

  public void addDestinationElements(ListModel newValue) {
    fillListModel(destListModel, newValue);
  }

  private void fillListModel(SortedListModel model, ListModel newValues) {
    int size = newValues.getSize();
    for (int i = 0; i < size; i++) {
      model.add(newValues.getElementAt(i));
    }
  }

  public void addSourceElements(Object newValue[]) {
    fillListModel(sourceListModel, newValue);
  }

  public void setSourceElements(Object newValue[]) {
    clearSourceListModel();
    addSourceElements(newValue);
  }

  public void addDestinationElements(Object newValue[]) {
    fillListModel(destListModel, newValue);
  }

  private void fillListModel(SortedListModel model, Object newValues[]) {
	  	
	  model.addAll(newValues);
  }

  public Iterator sourceIterator() {
    return sourceListModel.iterator();
  }

  public Iterator destinationIterator() {
    return destListModel.iterator();
  }

  public void setSourceCellRenderer(ListCellRenderer newValue) {
    sourceList.setCellRenderer(newValue);
  }

  public ListCellRenderer getSourceCellRenderer() {
    return sourceList.getCellRenderer();
  }

  public void setDestinationCellRenderer(ListCellRenderer newValue) {
    destList.setCellRenderer(newValue);
  }

  public ListCellRenderer getDestinationCellRenderer() {
    return destList.getCellRenderer();
  }

  public void setVisibleRowCount(int newValue) {
    sourceList.setVisibleRowCount(newValue);
    destList.setVisibleRowCount(newValue);
  }

  public int getVisibleRowCount() {
    return sourceList.getVisibleRowCount();
  }

  public void setSelectionBackground(Color newValue) {
    sourceList.setSelectionBackground(newValue);
    destList.setSelectionBackground(newValue);
  }

  public Color getSelectionBackground() {
    return sourceList.getSelectionBackground();
  }

  public void setSelectionForeground(Color newValue) {
    sourceList.setSelectionForeground(newValue);
    destList.setSelectionForeground(newValue);
  }

  public Color getSelectionForeground() {
    return sourceList.getSelectionForeground();
  }

  private void clearSourceSelected() {
    Object selected[] = sourceList.getSelectedValues();
    for (int i = selected.length - 1; i >= 0; --i) {
      sourceListModel.removeElement(selected[i]);
    }
    sourceList.getSelectionModel().clearSelection();
  }

  private void clearDestinationSelected() {
    Object selected[] = destList.getSelectedValues();
    for (int i = selected.length - 1; i >= 0; --i) {
      destListModel.removeElement(selected[i]);
    }
    destList.getSelectionModel().clearSelection();
  }

  
  
  private void initScreen() {
    setBorder(BorderFactory.createEtchedBorder());
    //setLayout(new FlowLayout());
    //add(new JLabel("SEARCH"));
    
    
    setLayout(new GridBagLayout());
    sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
    sourceListModel = new SortedListModel();
    sourceList = new JList(sourceListModel);
    sourceList.addMouseListener(new DoubleClickListener());
    
    
    add(new JLabel("SEARCH NCBI"),new GridBagConstraints(0, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(0, 5, 0, 5), 0, 0) );
    
    searchtext = new JTextField("", 100);
    add(searchtext,new GridBagConstraints(1, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 250, 1) );
    
    database = new JComboBox(new String[]{"PUBMED","POPSET"});
    add(database,new GridBagConstraints(2, 0, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,new Insets(0, 5, 0, 5), 0, 0) );
    
    search = new JButton("SEARCH");    
    add(search,new GridBagConstraints(1, 2, 1, 1, 0, 0.3,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,new Insets(0, 5, 0, 5), 0, 0) );
    search.addActionListener(new SearchListener());
    
    
    
    
    add(sourceLabel, new GridBagConstraints(0, 4, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(0, 5, 0, 5), 0, 0));
    add(new JScrollPane(sourceList), new GridBagConstraints(0, 5, 1, 5, .5, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,new Insets(0, 5, 0, 5), 0, 0));
    
    addButton = new JButton(ADD_BUTTON_LABEL);
    add(addButton, new GridBagConstraints(1, 4, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
    addButton.addActionListener(new AddListener());
    
    removeButton = new JButton(REMOVE_BUTTON_LABEL);
    add(removeButton, new GridBagConstraints(1, 7, 1, 2, 0, .25,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
    removeButton.addActionListener(new RemoveListener());

    destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
    destListModel = new SortedListModel();
    destList = new JList(destListModel);
       
    add(destLabel, new GridBagConstraints(2, 4, 1, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
    add(new JScrollPane(destList), new GridBagConstraints(2, 5, 1, 5, .5,1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,EMPTY_INSETS, 0, 0));
    destList.addMouseListener(new DoubleClickListener());
    save = new JButton("SAVE");
    add(save, new GridBagConstraints(5, 7, 1, 1, 0.5,0,GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
    save.addActionListener(new SaveListener());       
    

    
    
  
    //add(progressBar,);
    
    
  }

  public static void main(String args[]) {
	
	  try {
		  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		    
		  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		  } catch (Exception e) {}  


	
	  
	JFrame f = new JFrame("Search Results");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SearchResult sr = new SearchResult();
    f.getContentPane().add(sr, BorderLayout.CENTER);
    f.setSize(700, 600);
    f.setVisible(true);
  }

  private class SearchListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      
	    	try
	     	{
	    		//WorkflowRepository w = new WorkflowRepository(new JFrame());	
	    	search.setEnabled(false);		
	    		
	    	String searchkey = searchtext.getText();      
	     	String dbname = database.getSelectedItem().toString();
	     	    	
	     	count = c.searchDatabase(dbname,searchkey);     	
	     	clearSourceListModel();
	     	addSourceElements(c.accession);
	     	repaint();     	
	     
	     	search.setEnabled(true);
	     	repaint();
	     	
	    	
	    }catch(Exception e1){System.out.println(e1);}
	    }
	  }
  
  private class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object selected[] = sourceList.getSelectedValues();
      addDestinationElements(selected);
      clearSourceSelected();
    }
  }

  private class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object selected[] = destList.getSelectedValues();
      addSourceElements(selected);
      clearDestinationSelected();
    }
  }
  

  private class SaveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int listlength = destListModel.getSize();
      //System.out.println("List Length: "+listlength);
      //int destindex;
      String[] destvalue=new String[listlength];
      
      for(int i =0;i<listlength;i++)
      {
    	  destvalue[i] = destList.getComponent(i).toString();
    	  for(int j=0;j<count;j++)
          {
        	if(destvalue[i].equals(c.accession[j]))
        	{
        		try
        	      {
        	         FileOutputStream fileOut =  new FileOutputStream("accession."+j);
        	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	         out.writeObject("Definition: \n"+c.definition[j]+
        	   	          "\n\nAccession: \n"+c.accession[j]+
        		          "\n\nOrganism:\n"+c.organism[j]+
        		          "\n\nLocus:\n"+c.locus[j]+
        		          "\n\nSequence:\n"
        		          +c.sequence[j].toUpperCase());
        	         out.close();
        	          fileOut.close();
        	      }catch(IOException ie)
        	      {
        	          ie.printStackTrace();
        	      }

        	}
          }
      }
      
      
      
      
      
      
      
      
    	//Object selected[] = destList.

    }
  }
  
  
  private class DoubleClickListener implements MouseListener
  {
	  String Text="";
	  
	  public void mouseClicked(MouseEvent evt) 
	  {
	        JList list = (JList) evt.getSource();
	        if (evt.getClickCount() == 2)
	        { // Double-click
	          int localindex = list.locationToIndex(evt.getPoint());
	          String value = (String)list.getSelectedValue();
	          //System.out.println("Double Click");
	          //System.out.println(list.getSelectedValue());
	          for (int i = 0; i <count;i++)
	          {
	        	  if(c.accession[i].equals(value))
	        	  {  
	        		index = i;
	        		System.out.println("Index=="+index);
	        	  }//if       	   
	          }//for
	          
	          Text = "Definition: \n"+c.definition[index]+
	          "\n\nAccession: \n"+c.accession[index]+
	          "\n\nOrganism:\n"+c.organism[index]+
	          "\n\nLocus:\n"+c.locus[index]+
	          "\n\nSequence:\n"
	          +c.sequence[index].toUpperCase();
	          
	          JFrame frame = new JFrame("Selection Info");
	          frame.setBackground(Color.CYAN);
	          Container content = frame.getContentPane();
	          StyleContext context = new StyleContext();
	          StyledDocument document = new DefaultStyledDocument();
	          Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
	          
	          try {
	              document.insertString(document.getLength(), Text, style);
	              } catch (BadLocationException badLocationException) {
	              System.err.println("Error");
	            }

	          	
	          JTextPane textPane = new JTextPane(document);
	          textPane.setEditable(false);
	          JScrollPane scrollPane = new JScrollPane(textPane);
	          content.add(scrollPane, BorderLayout.CENTER);
	          frame.getContentPane().add(new JScrollPane(textPane));
	          frame.setSize(400, 300);
	          frame.setVisible(true);

	          
	        }//if
	  }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
  
}
}





class SortedListModel extends AbstractListModel {

  SortedSet model;

  public SortedListModel() {
    model = new TreeSet();
  }

  public int getSize() {
    return model.size();
  }

  public Object getElementAt(int index) {
    return model.toArray()[index];
  }

  public void add(Object element) {
    if (model.add(element)) {
      fireContentsChanged(this, 0, getSize());
    }
  }

  public void addAll(Object elements[]) {
    Collection c = Arrays.asList(elements);
    model.addAll(c);
    fireContentsChanged(this, 0, getSize());
  }

  public void clear() {
    model.clear();
    fireContentsChanged(this, 0, getSize());
  }

  public boolean contains(Object element) {
    return model.contains(element);
  }

  public Object firstElement() {
    return model.first();
  }

  public Iterator iterator() {
    return model.iterator();
  }

  public Object lastElement() {
    return model.last();
  }

  public boolean removeElement(Object element) {
    boolean removed = model.remove(element);
    if (removed) {
      fireContentsChanged(this, 0, getSize());
    }
    return removed;
  }
}

