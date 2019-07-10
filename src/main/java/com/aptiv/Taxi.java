package com.aptiv;

public class Taxi {

    private int id;
    private boolean available;
    private Location location;

    public Taxi(int id) {
        this.id = id;
        this.available = true;
        this.location = new Location(0, 0);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }

    public void setStatus(boolean status) {
        this.available = status;
    }

    public void setNextLocation(int x, int y) {
        location.setLocation(x, y);
    }

    public boolean moveUp(int x, int y) {
        if(x <= 0) {
            return false;
        }
        location.setLocation(x - 1 , y);
        return true;
    }

    public boolean moveDown(int x, int y) {
        if(x >= Integer.MAX_VALUE) {
            return false;
        }
        location.setLocation(x + 1, y);
        return true;
    }

    public boolean moveLeft(int x, int y) {
        if(y <= 0) {
            return false;
        }
        location.setLocation(x, y - 1);
        return true;
    }

    public boolean moveRight(int x, int y) {
        if(y >= Integer.MAX_VALUE) {
            return false;
        }
        location.setLocation(x, y + 1);
        return true;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public Location getLocation() {
        return this.location;
    }

}
