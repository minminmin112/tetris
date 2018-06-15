package com.inc.tetris.block;

import java.awt.Color;
import java.awt.Point;

public class Block {
	private Point[] shape;
	private int shapeType;
	private int rotation;
	private Color c;
	public Point blockPoint;

	public Block(int shapeType, Color c) {
		blockPoint = new Point(0, 2);
		this.shapeType = shapeType;
		this.c = c;

		initShape();
	}

	public Block(int shapeType, Color c, int rotation) {
		blockPoint = new Point(0, 2);
		this.shapeType = shapeType;
		this.rotation = rotation;
		this.c = c;

		initShape();
	}

	private void initShape() {
		shape = new Point[4];
		int i = 0;
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (Shape.SHAPE[shapeType][rotation][row][col] == 1) {
					shape[i] = new Point(row, col);
					i++;
					if (i > 4) {
						break;
					}
				}
			}
		}
	}

	public Point[] getShape() {
		return shape;
	}

	public void setShape(Point[] shape) {
		this.shape = shape;
	}

	public int getShapeType() {
		return shapeType;
	}

	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

}
