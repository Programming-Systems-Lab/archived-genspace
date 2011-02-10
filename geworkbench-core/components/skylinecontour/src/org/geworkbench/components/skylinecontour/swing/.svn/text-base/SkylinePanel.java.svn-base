package org.geworkbench.components.skylinecontour.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboPopup;

import edu.columbia.cs.skys.Config;
import edu.columbia.cs.skys.data.Point;
import edu.columbia.cs.skys.data.PointUtil;
import org.geworkbench.components.skylinecontour.db.ArticleMetadata;
import org.geworkbench.components.skylinecontour.db.MetadataManager;

import org.geworkbench.components.skylinecontour.db.ArticleMetadata;
import org.geworkbench.components.skylinecontour.db.MetadataManager;

public class SkylinePanel extends JPanel {

	public static int DEFAULT_WIDTH = 1000;
	public static int DEFAULT_HEIGHT = 800;
	public static int DEFAULT_MARGIN = 6;
	public static int DEFAULT_SL_SIZE = 6;
	public static Color NON_SL_COLOR = new Color(0x55,0x00,0x00);

	static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d MMM yy");

	private static final long serialVersionUID = -5938575583591261348L;
	Point<?,?> [] _points;
	Point<?,?> [] _bounds;
	int _internalX, _internalY, _margin;
	int _maxContours;
	double _pntExtentX, _pntExtentY;
	int _nonSkylineSize = 2;
	private CelledHotspotManager _hotspotManager;
	private Logger _logger = Logger.getLogger(SkylinePanel.class.getName());
	private Set<Point<?,?>> _skylineContours = new HashSet<Point<?,?>>();	// set of points which are contoured

	ColorGradient colorGradient;

	public SkylinePanel(int numContours, int width, int height) {
		this(numContours, width, height, DEFAULT_MARGIN);
	}
		
	public SkylinePanel(int numContours, int width, int height, int margin) {
		//Title our frame.
		this._internalX = width;
		this._internalY = height;
		this._margin = margin;
		_maxContours = numContours;
		setLayout(null);
		Dimension size = new Dimension(_internalX + 2*_margin,_internalY+2*_margin);
		setPreferredSize(size);
		Color [] colors = new Color[] {
				new Color(0xff,0xff,0xff),
				new Color(0xff,0xff,0x00),
				new Color(0xff,0x8c,0x00),
				new Color(0xff,0x45,0x00),
				new Color(0xff,0x00,0x00)};
		colorGradient =  new ColorGradient(colors);
	}
		
	public SkylinePanel(Point<?,?> [] points, int numContours, int width, int height, Point<?,?> bounds []) {
		this(points, numContours, width, height, bounds, DEFAULT_MARGIN);
	}
	
	public SkylinePanel(Point<?,?> [] points, int numContours, int width, int height, Point<?,?> bounds [], int margin) {
		this(numContours, width, height, margin);
		showSkyline(points, numContours, bounds);
	}

	public void showSkyline(Point<?,?> [] newpoints, int numContours, Point<?,?> bounds []) {
		_maxContours = numContours;
 		_points = newpoints;
 		_bounds = (bounds==null)?PointUtil.bounds(newpoints):bounds;
 		double startX = _bounds[0].xToDouble();
 		_pntExtentX = _bounds[1].xToDouble() - startX;
 		_pntExtentY = _bounds[1].yToDouble() - _bounds[0].yToDouble();
 		updateSkylineContours(newpoints);
		getHotspotManager(_bounds);
		if (Config.OFF_CONTOUR_HOTSPOTS) {
			placeHotspots(_points, 0);
		} else {
			placeHotspots(_skylineContours);
		}
		layoutHotspots();
	}
	
	public void extendSkyline(Point<?,?> [] extraPoints, Point<?,?> bounds[]) {
		if (_points == null) {
			showSkyline(extraPoints, _maxContours, bounds);
		} else {
			int oldLength = _points.length;
			_points = Arrays.copyOf(_points, _points.length + extraPoints.length);
			System.arraycopy(extraPoints, 0, _points, oldLength, extraPoints.length);
	 		_bounds = bounds==null?PointUtil.extendBounds(extraPoints, _bounds):bounds;
	 		_pntExtentX = _bounds[1].xToDouble() - _bounds[0].xToDouble();
	 		_pntExtentY = _bounds[1].yToDouble() - _bounds[0].yToDouble();
	 		extendSkylineContours(extraPoints);
	 		// get rid of all hotspots
			getHotspotManager(_bounds);
			if (Config.OFF_CONTOUR_HOTSPOTS) {
				placeHotspots(_points, 0);
			} else {
				placeHotspots(_skylineContours);
			}
			layoutHotspots();
		}
	}

	private void placeHotspots(Point<?,?> [] points, int offset) {
		if (points!=null) {
			for (int i=0; i< points.length; i++) {
				Point<?, ?> point = points[i];
				if (point.getId() == 17760725) {
					System.out.println("Breakpoint");
				}
				int globalIdx = i + offset;
				if (_skylineContours != null && _skylineContours.contains(point)) {
						// add to contour
						int contour = point.getContour();
						_hotspotManager.add(point, contour);
				} else {
					_hotspotManager.add(point);					
				}
			}
		}
	}

	public void reset() {
		_points = null;
		_bounds = null;
		_skylineContours.clear();
		_hotspotManager = null;
		removeAll();
	}
	
	/**
	 * Get a new hotspot manager given the bounds
	 * @param bounds the expected limits of the 2-D area we are representing
	 */
	private void getHotspotManager(Point<?,?> [] bounds) {
		double cellX = (bounds[1].xToDouble()-bounds[0].xToDouble())/40;
		double cellY = (bounds[1].yToDouble()-bounds[0].yToDouble())/40;
		 _hotspotManager = new CelledHotspotManager((float)cellX, (float)cellY);
	}
	
	
	private void placeHotspots(Set<Point<?,?>> contours) {
		for (Point point: contours) {
			int contour = point.getContour();
			_hotspotManager.add(point, contour);
		}		
	}
	
	private void layoutHotspots() {
		removeAll();
		int cellX = logical2ScreenX(_hotspotManager.getCellX());
		int cellY = logical2ScreenY(_hotspotManager.getCellY());
		for (CelledHotspotManager.Cell hotspotCell: _hotspotManager) {
			JPanel hotspot = new JPanel();
			hotspot.setBackground(new Color(0,0,255,50));
			Point<?, ?> anchor = logical2Screen(hotspotCell.getBottomLeft());
			hotspot.setBounds((int)(anchor.xToDouble()), (int)(anchor.yToDouble())-cellY, cellX, cellY);
			hotspot.setPreferredSize(new Dimension(cellX, cellY));
			hotspot.addMouseListener(new HotspotMouseListener(hotspotCell.getId(), this.getRootPane(), !hotspotCell.hasContour()));
			//hotspot.setOpaque(false);
			add(hotspot);				
		}
	}
	
	public int getMargin() {
		return _margin;
	}

	public void setMargin(int _margin) {
		this._margin = _margin;
	}

	
	private void updateSkylineContours(Point<?,?> [] points) {
		_skylineContours.clear();
		extendSkylineContours(points);
	}

	private void extendSkylineContours(Point<?,?> [] points) {
		for (Point<?,?> point: points) {
			if (point.hasContour() && point.getContour() <= _maxContours) {
				_skylineContours.add(point);
			}
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
//		System.out.println("********* SkylinePanel: paint() ***********");
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(Color.BLACK);
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.drawLine(0,getHeight()-1, getWidth(), getHeight()-1);
		g2d.drawLine(0, 0, 0, getHeight());
		g2d.setColor(NON_SL_COLOR);								
		if (_points!=null) {
			for (int i=0; i< _points.length; i++) {
				Point<?, ?> point = _points[i];
				if (_skylineContours != null && !_skylineContours.contains(point)) {
					Point spoint = logical2Screen(point);
					g2d.fillRect((int)spoint.xToDouble(), (int)spoint.yToDouble(), _nonSkylineSize, _nonSkylineSize);
				}			
			}
			//System.out.println("********* painted "+_points.length+" points ***********");
		}
		if (_skylineContours != null) {
			for (Point<?,?> point: _skylineContours) {
				Point<?,?> spoint = logical2Screen(point);
				Color color = getContourColor(point.getContour());
				g2d.setColor(color);
				g2d.fillRect((int)(spoint.xToDouble()-DEFAULT_SL_SIZE/2), (int)(spoint.yToDouble() - DEFAULT_SL_SIZE/2), DEFAULT_SL_SIZE, DEFAULT_SL_SIZE);
			}
		}
	}
		
		
	private Point<?,?> logical2Screen(Point<?, ?> point) {
		Point spoint = logical2Screen(point.xToDouble(), point.yToDouble());
		spoint.setId(point.getId());
		return spoint;
	}
	
	private Point<?,?> logical2Screen(double x, double y) {
		x = _margin + (x - _bounds[0].xToDouble())*_internalX/_pntExtentX;
		y = _internalY + _margin - (y - _bounds[0].yToDouble()) * _internalY/_pntExtentY;
		return new Point(x, y);
	}

	private int logical2ScreenX(double length) {
		int x = (int)(length*_internalX/_pntExtentX);
		return x;
	}
	
	private int logical2ScreenY(double length) {
		int y = (int)(length*_internalY/_pntExtentY);
		return y;
	}

	private Color getContourColor(int contour) {
		Color color = colorGradient.getColor((float)contour/(float)_maxContours);	
		return color;
	}
	
	class HotspotMouseListener extends MouseAdapter {
		BasicComboPopup _popup;
		private int _cellIdx;
		Component _base;
		private int [] _ids;
		private int [] _contours;
		private boolean _onClickOnly = false;
		
		public HotspotMouseListener(int cellIdx, Component base, boolean onClick) {
			super();
			this._base = base;
			this._cellIdx = cellIdx;
			CelledHotspotManager.Cell cell = _hotspotManager.getCell(cellIdx);
			_ids = cell.getPointIds();
			_contours = cell.getContours();
			_onClickOnly = onClick;
			
		}
		
		public HotspotMouseListener(int cellIdx, Component base) {
			this(cellIdx, base, false);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if (_onClickOnly) {
				return;
			}
			showPopup(e);
			_popup.show(e.getComponent(),
                    e.getX(), e.getY());
		}
		
		private void showPopup(MouseEvent event) {
	       new PopupPane();
		}
		
		public int getFrameX(Component component) {
			int x = 0;
			while(component!=null) {
				x = x + component.getX();
				component = component.getParent();
			}
			return x;
		}

		public int getFrameY(Component component) {
			int y = 0;
			while(component!=null) {
				y = y + component.getY();
				component = component.getParent();
			}
			return y;
		}

		public class PopupPane extends JEditorPane {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -1205108373722084073L;
			private boolean _debug = Config.DEBUG_MODE;
			private Logger _logger = Logger.getLogger(PopupPane.class.getName());
			private static final String URL = "http://www.ncbi.nlm.nih.gov/sites/entrez?Db=pubmed&Cmd=DetailsSearch&Term=";
			private ArrayList<String> list = new ArrayList<String>();
			
			public PopupPane() {
				setContentType("text/html");
				getHTML();		
				setMargin(new Insets(0,0,0,0));
			}
			
			private void getHTML() {
				String title="Unknown";
				Date pubdate = null;
				String pubdateString = null;
				String journal = "Unknown";
				String authors = "Unknown";
				StringBuffer docString = null;
				ArticleMetadata allMetadata[] = null;
				int height = 0;
				
				try {
					allMetadata = MetadataManager.getInstance().getMetadataList(_ids);
				} catch (SQLException e) {
					_logger.warning("Hotspot " + _cellIdx + " returned " + e.getMessage());
				}
				boolean firstDoc = true;
				if (allMetadata != null) {
					int i = 0;
					for (ArticleMetadata metadata: allMetadata) {
						docString = new StringBuffer();
						if (!firstDoc) {
							docString.append("<hr>");
						}
						/*if (i >= Config.MAX_POPUP_ARTICLES) {
							docString.append("... [" + (_ids.length - Config.MAX_POPUP_ARTICLES) + " more ]"); 
							break;
						}*/
						if (metadata != null) {
							int contour = _contours[i];
							Color contourColor = contour==-1?Color.black:getContourColor(contour);
							String colorString = SwingUtil.colorToCssString(contourColor);
							title = metadata.title;
							journal = metadata.journal;
							pubdate = metadata.pubDate;
							boolean first = true;
							StringBuffer authorList = new StringBuffer();
							for (String authorname: metadata.authors) {
								if (!first) {
									authorList.append("; ");
								}
								authorList.append(authorname);
								first = false;
							}
							authors = authorList.toString();
							if (pubdate!=null) {
								pubdateString = DATE_FORMAT.format(pubdate);
							}
							//docString.append("<table width='100%' cellpadding='0' cellspacing='2'><tr><td class='colorbar' style='background-color: "+colorString+";'>&nbsp;</td><td>");
							docString.append("<table width='600' cellpadding='0' cellspacing='2'><tr><td class='colorbar' style='background-color: "+colorString+";'>&nbsp;</td><td>");
							docString.append("<b>" + title + "</b><br>" +
									pubdateString + " in <i>" +	journal + "</i> (" + authors+ ")");
							if (_debug) {
								docString.append("<div class='debug'>PMID:"+_ids[i]+"</div> ");
								docString.append("<div class='debug'>{" + metadata.meshNodesToString() + "}</div>");
							}
							docString.append("</td></tr></table>");
						} else {
							if (_debug) {
								docString.append("<div class='debug'>PMID:"+_ids[i]+"</div> ");
							}
							docString.append("unknown");
						}
						firstDoc = false;		
						
						if( i <= 3) {
							height += 100;
						}
			
						i++;
					
						StringBuffer fullHtml = new StringBuffer("<html>" +
								"<style type='text/css'>" +
								"<!--" +
								"body { font-family:Arial,sans-serif; font-size:small; padding: 1pt }" +
								"td { font-family:Arial,sans-serif; font-size:small;}" +				
								"hr { width: 100%; height: 1px;	color: #C9C9C9;}" +
								".debug {font-size:x-small; font-style:italic;}" +
								".colorbar {width: 2px;}" +
								"--></style><body>");
						fullHtml.append(docString);
						fullHtml.append("</body></html>");
						list.add(fullHtml.toString());
					} 
				} else {
					docString.append("unknown");
				}
				
				JComboBox docList = new JComboBox(list.toArray());
				_popup = new BasicComboPopup(docList);
				_popup.setPopupSize(620,height);
				_popup.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				docList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 try {
							 	String id = e.getSource().toString();
							 	id = id.substring(id.indexOf("PMID:"));
								id = id.substring(5,id.indexOf("</div>"));
								BrowserLauncher.openURL(URL+id);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					}
				});
			}
		}
	}	
}

