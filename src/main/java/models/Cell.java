package models;

public class Cell {
    private int row;
    private int column;
    private CellState cellState;
    private Player player;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.cellState = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellState getCellState() {
        return cellState;
    }

    public boolean isEmpty() {
        return this.cellState == CellState.EMPTY;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
