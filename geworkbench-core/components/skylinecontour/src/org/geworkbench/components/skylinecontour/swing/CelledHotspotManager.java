package org.geworkbench.components.skylinecontour.swing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import spatialindex.rtree.RTree;
import spatialindex.spatialindex.IData;
import spatialindex.spatialindex.INode;
import spatialindex.spatialindex.IVisitor;
import spatialindex.spatialindex.Region;
import spatialindex.storagemanager.IStorageManager;
import spatialindex.storagemanager.MemoryStorageManager;
import spatialindex.storagemanager.PropertySet;
import edu.columbia.cs.skys.data.Point;
import edu.columbia.cs.skys.skyline.ISkylineQuery;

/**
 * $Id: CelledHotspotManager.java,v 1.3 2009-11-18 16:57:42 jiz Exp $
 *
 *
 * Note: works only on the screen coordinate level (not the real point level)
 *
 * @author William Mee <wjm2107@cs.columbia.edu>
 * Copyright (c) 2008 The Trustees of Columbia University
 */
public class CelledHotspotManager implements Iterable<CelledHotspotManager.Cell> {
	
	private float _cellX, _cellY;
	private RTree _rtree;
	private int _currCellId = 0;
	private List<Cell> _cells = new ArrayList<Cell>();
	
	public CelledHotspotManager(float cellX, float cellY) {
		IStorageManager smanager = new MemoryStorageManager();
		PropertySet ps = new PropertySet();
		_rtree = new RTree(ps, smanager);		
		_cellX = cellX;
		_cellY = cellY;
	}
	
	public void add(Point point) {
		if (! point.hasId()) {
			return;
		}
		Cell cell = getCell(point);
		cell.addPoint(point);
	}

	public void add(Point point, int contour) {
		if (! point.hasId()) {
			return;
		}
		Cell cell = getCell(point);
		cell.addPoint(point, contour);
	}
	
	public Iterator<Cell> iterator() {
		return new CellIterator();		
	}
		
	private Cell getCell(Point point) {
		// look up cell
		Cell cell = null;
		CellVisitor visitor = new CellVisitor();
		_rtree.intersectionQuery(getSIPoint(point), visitor);
		if (visitor.visited().length == 0) {
			// if it doesn't exist, create it
			float x1=_cellX*(int)(point.xToDouble()/_cellX);
			float y1=_cellY*(int)(point.yToDouble()/_cellY);
			float x2=x1+_cellX;
			float y2=y1+_cellY;
			_rtree.insertData(null, new Region(new double[]{x1,y1}, new double[]{x2,y2}), _currCellId);
			cell = new Cell(_currCellId, new Point<Float,Float>(x1,y1), new Point<Float,Float>(x2,y2));
			_cells.add(cell);
			_currCellId++;
		} else {
			cell = _cells.get(visitor.visited()[0]);
		}
		return cell;
	}
			
	public float getCellX() {
		return _cellX;
	}

	public float getCellY() {
		return _cellY;
	}

	private spatialindex.spatialindex.Point getSIPoint(Point point) {
		spatialindex.spatialindex.Point siPoint = new spatialindex.spatialindex.Point(new double [] {point.xToDouble(), point.yToDouble()});
		return siPoint;
	}
	

	public Cell getCell(int idx) {
		return _cells.get(idx);
	}


	private class CellVisitor implements IVisitor {

		private SortedSet<Integer> _visitedData = new TreeSet<Integer>();
		
		@Override
		public void visitData(IData d) {
			int id = d.getIdentifier();
			_visitedData.add(id);
		}

		@Override
		public void visitNode(INode n) {
		}

		public int numVisited() {
			return _visitedData.size();
		}

		public boolean visited(int id) {
			return _visitedData.contains(id);
		}
		
		public int[] visited() {
			int[] visitedArray = new int[_visitedData.size()];
			int idx = 0;
			for (int id: _visitedData) {
				visitedArray[idx++]=id;
			}
			return visitedArray;
		}
		
		public void clear() {
			_visitedData.clear();
		}
		
		
	}
	
	private class CellIterator implements Iterator<Cell> {

		int idx = 0;
		
		@Override
		public boolean hasNext() {
			return idx < _currCellId;
		}

		@Override
		public Cell next() {
			return _cells.get(idx++);
		}

		@Override
		public void remove() {
			throw new IllegalArgumentException("not implemented");
		}
		
	}
	
	public class Cell {
		Point<Float, Float> _bl, _tr;
		int _cellId;
		List<Integer> _pointIds = new ArrayList<Integer>();
		List<Integer> _contours = new ArrayList<Integer>();
		boolean _hasContour = false;
		
		public Cell(int cellId, Point<Float, Float> bl,	Point<Float, Float> tr) {
			super();
			this._bl = bl;
			this._cellId = cellId;
			this._tr = tr;
		}

		public int getId() {
			return _cellId;
		}
		
		protected void addPoint(Point<?,?> point) {
			_pointIds.add(point.getId());
			_contours.add(ISkylineQuery.NO_CONTOUR);
		}

		protected void addPoint(Point<?,?> point, int contour) {
			_pointIds.add(point.getId());
			_contours.add(contour);
			if (contour!=ISkylineQuery.NO_CONTOUR) {
				_hasContour = true;
			}
		}
		
		
		public Point<Float, Float> getBottomLeft() {
			return _bl;
		}
		
		int [] getPointIds() {
			int [] ids = new int[_pointIds.size()];
			for (int i=0; i<_pointIds.size();i++) {
				ids[i]=_pointIds.get(i);
			}
			return ids;
		}

		int [] getContours() {
			int [] contours = new int[_contours.size()];
			for (int i=0; i<_contours.size();i++) {
				contours[i]=_contours.get(i);
			}
			return contours;
		}
		
		/**
		 * @return a flag indicating that this cell has a point with a 
		 * valid contour number.
		 */
		public boolean hasContour() { 
			return _hasContour;			
		}
		
		
	}
	

}
