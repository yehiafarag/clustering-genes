package com.model.scorematrix;

public class Cell {
   private Cell prevCell;
   private double score;
   private int row;
   private int col;

   private Character cellType;//u for up , s for side,  m for main

   private Cell subCell;

    public void setSubCell(Cell subCell) {
        this.subCell = subCell;
    }

    public Cell getSubCell() {
        return subCell;
    }
    public void setCellType(Character cellType) {
        this.cellType = cellType;
    }

    public Character getCellType() {
        return cellType;
    }

public void setPrevCell(Cell prevCell) {
	this.prevCell = prevCell;
}
public Cell getPrevCell() {
	return prevCell;
}
public void setScore(double score) {
	this.score = score;
}
public double getScore() {
	return score;
}
public void setRow(int row) {
	this.row = row;
}
public int getRow() {
	return row;
}
public void setCol(int col) {
	this.col = col;
}
public int getCol() {
	return col;
}
}