package matrix;

import java.util.List;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public List<Coordinate> surrounding(int row, int col) {
        return List.of(
            new Coordinate(row-1, col-1), new Coordinate(row-1, col), new Coordinate(row-1, col+1),
            new Coordinate(row  , col-1),                             new Coordinate(row  , col+1),
            new Coordinate(row+1, col-1), new Coordinate(row+1, col), new Coordinate(row+1, col+1)
        );
    }

    public boolean isInBounds(List<String> lines) {
        return 0 <= row && 0 <= col && row < lines.size() && col < lines.getFirst().length();
    }

    public Coordinate getStartOfNumber(List<String> lines) {
        Coordinate start = new Coordinate(this.row, this.col);
        while (start.isInBounds(lines) && Character.isDigit(lines.get(start.row).charAt(start.col))){
            start.col--;
        }
        start.col++;
        return start;
    }

    public Integer readCoordinateNumber(List<String> lines) {
        Coordinate start = new Coordinate(this.row, this.col);
        System.out.println(start.row + " " + start.col);
        StringBuilder numberAsStr = new StringBuilder();
        while (start.isInBounds(lines) && Character.isDigit(lines.get(start.row).charAt(start.col))){
            numberAsStr.append(lines.get(start.row).charAt(start.col));
            start.col++;
        }
        return Integer.valueOf(numberAsStr.toString());
    }

    @Override
    public String toString() {
        return "Coordinate(" + this.row + ", " + this.col + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (row != other.row) {
            return false;
        }
        if (col != other.col) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return col * row;
    }
}
