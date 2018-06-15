package com.inc.tetris.block;

import java.awt.Point;
import java.awt.event.KeyEvent;

import com.inc.tetris.cell.Cell;
import com.inc.tetris.frame.TetrisFrame;

public class BlockTransformer {
	private Block curBlock;
	private Cell[][] cells;

	public BlockTransformer(Cell[][] cells) {
		this.cells = cells;
	}

	public void setBlock(Block block) {
		this.curBlock = block;
	}

	private void fix() {
		for (Point p : curBlock.getShape()) {
			cells[p.x][p.y].setFixed(true);
		}
	}

	public boolean canDown() {
		for (Point p : curBlock.getShape()) {
			if (p.x >= TetrisFrame.ROWS - 1) {
				fix();
				return false;
			}

			if (cells[p.x + 1][p.y].isFixed()) {
				fix();
				return false;
			}
		}
		return true;
	}

	public void move(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
		case KeyEvent.VK_UP:
			rotate();
			break;
		}
	}

	private void rotate() {
		int rotation = curBlock.getRotation();
		if (rotation == 3) {
			rotation = 0;
		} else {
			rotation++;
			// rotation += 1;
		}

		Block tmpBlock = new Block(curBlock.getShapeType(), curBlock.getC(), rotation);
		tmpBlock.blockPoint.y = curBlock.blockPoint.y;
		tmpBlock.blockPoint.x = curBlock.blockPoint.x;

		for (Point p : tmpBlock.getShape()) {
			p.x += tmpBlock.blockPoint.x;
			p.y += tmpBlock.blockPoint.y;
		}

		// 돌릴수 있는지 검사
		for (Point p : tmpBlock.getShape()) {
			if (p.x > 19 || p.y < 0 || p.y > 9 || cells[p.x][p.y].isFixed()) {
				return;
			}
		}

		// 감추기
		hide();

		// 돌리기
		curBlock.setShape(tmpBlock.getShape());
		curBlock.setRotation(tmpBlock.getRotation());

		// 보여주기
		for (Point p : curBlock.getShape()) {
			cells[p.x][p.y].setVisible(true);
			cells[p.x][p.y].setC(curBlock.getC());
		}

	}

	private void moveRight() {
		for (Point p : curBlock.getShape()) {
			if (p.y == TetrisFrame.COLS - 1 || cells[p.x][p.y + 1].isFixed()) {
				return;
			}
		}

		hide();

		for (Point p : curBlock.getShape()) {
			p.y++;
			cells[p.x][p.y].setVisible(true);
			cells[p.x][p.y].setC(curBlock.getC());
		}
		curBlock.blockPoint.y++;
	}

	private void moveLeft() {
		for (Point p : curBlock.getShape()) {
			if (p.y == 0 || cells[p.x][p.y - 1].isFixed()) {
				return;
			}
		}

		hide();

		for (Point p : curBlock.getShape()) {
			p.y--;
			cells[p.x][p.y].setVisible(true);
			cells[p.x][p.y].setC(curBlock.getC());
		}
		curBlock.blockPoint.y--;
	}

	public void down() {
		hide();

		for (Point p : curBlock.getShape()) {
			p.x++;
			cells[p.x][p.y].setVisible(true);
			cells[p.x][p.y].setC(curBlock.getC());
		}
		curBlock.blockPoint.x++;
	}

	private void hide() {
		for (Point p : curBlock.getShape()) {
			cells[p.x][p.y].setVisible(false);
		}
	}

}
