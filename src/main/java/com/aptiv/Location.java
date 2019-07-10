package com.aptiv;

/*
Location class which indicates the current position in the 2D grid(row, col)
 */
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRow() {
        return x;
    }

    public int getCol() {
        return y;
    }

}
