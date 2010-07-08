package org.geworkbench.components.genspace.rating;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Star extends JLabel{
	
	private static ImageIcon fullStar = new ImageIcon("full.png");
	private static ImageIcon halfStar = new ImageIcon("half.png");
	private static ImageIcon emptyStar = new ImageIcon("empty.png");
	
	public static final int FULL = 1;
	public static final int HALF = 2;
	public static final int EMPTY = 3;
	
	private int value;
	private StarRatingPanel parent;
	
	public int getValue(){
		return value;
	}
	
	public void setStar(int star){
		switch(star){
		case FULL: setIcon(fullStar); break;
		case HALF: setIcon(halfStar); break;
		case EMPTY: 
		default:
			setIcon(emptyStar); break;
		}
		repaint();
	}
	
	
	public Star(StarRatingPanel panel, int value){
		this.parent = panel;
		addMouseListener(panel);
		this.value = value;
		setStar(EMPTY);
	}
}
