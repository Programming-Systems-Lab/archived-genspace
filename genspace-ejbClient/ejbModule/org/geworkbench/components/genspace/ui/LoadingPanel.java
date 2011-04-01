package org.geworkbench.components.genspace.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4223462006648987555L;
	int time = 0;
	public static void main(String[] args) {
		JFrame f = new JFrame();
		LoadingPanel pnl = new LoadingPanel();
		f.add(pnl);
		f.setSize(new Dimension(640, 480));
		f.setVisible(true);
	}
	
	Timer t;
	public LoadingPanel() {
		t = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				time+=10;
				if(time > 360)
					time = 0;
				repaint();
			}
		});
	}
	public void start()
	{
		t.start();
	}
	public void stop()
	{
		t.stop();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		int w = Math.min(this.getWidth(), this.getHeight());
		int dx = this.getWidth()/2 - w/20 - 2;
		int dy = this.getHeight()/2 - w/20 -2 ;
		for(double i = 0; i < 10; i++)
		{
			double r = w/2 - w/20 - 1;
			int a = (int) (255 - 25* (10-i));

			Color c = new Color(0, 28, 155, a);
			g2.setColor(c);
			g2.fillOval( (int) (dx + Math.sin((i*20+time) * Math.PI/180)*r), (int) (dy + Math.cos((i*20+time) * Math.PI /180 )*r), w/10, w/10);
		}
	}
}
