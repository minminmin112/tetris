package com.inc.tetris.cell;

import java.awt.Color;
import java.awt.Point;

public class Cell {
	// 셀의 위치
	public Point p;

	// 셀의 크기
	public static final int CELL_SIZE = 30;

	// 셀의 색상
	private Color c;

	// 보여져야하는지 여부
	private boolean isVisible;

	// 쌓여져있는지 여부
	private boolean isFixed;

	public Cell(Point p, Color c) {
		this.p = p;
		this.c = c;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

}
