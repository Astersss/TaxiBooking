package com.aptiv;

import java.util.ArrayList;
import java.util.List;

/*
Singleton class which acts as a local storage.
 */
public class MockedWorld {

    private List<BookTaxi> bookedTaxis;
    private List<Taxi> taxis;
    private int timeStamp;

    private static MockedWorld instance = null;
    public static MockedWorld getInstance() {
        if(instance == null) {
            instance = new MockedWorld();
        }
        return instance;
    }

    public MockedWorld() {
        timeStamp = 0;
        taxis = new ArrayList<>();
        bookedTaxis = new ArrayList<>();
        taxis.add(new Taxi(1));
        taxis.add(new Taxi(2));
        taxis.add(new Taxi(3));
    }

    public int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int tick() {
        timeStamp++;
        return timeStamp;
    }


    public BookTaxi pickVacantTaxi(int x1, int y1, int x2, int y2) {
        int shortestDistance = Integer.MAX_VALUE;
        int pickTaxiId = -1;
        for(Taxi taxi: taxis) {
            if(taxi.isAvailable()) {
                Location taxiLocation = taxi.getLocation();
                int distance = getManhattanDistance(taxiLocation.getRow(), taxiLocation.getCol(), x1, y1);
                if(distance < shortestDistance) {
                    shortestDistance = distance;
                    pickTaxiId = taxi.getId();
                }
            }
        }
        if(pickTaxiId == -1) {
            return null;
        }

        for(Taxi taxi: taxis) {
            if(taxi.getId() == pickTaxiId) {
                taxi.setStatus(false);
                BookTaxi booktaxi = new BookTaxi(pickTaxiId, shortestDistance + getManhattanDistance(x1, y1, x2, y2));
                bookedTaxis.add(booktaxi);
                return booktaxi;
            }
        }
        return null;
    }

    public List<Taxi> resetTaxis() {
        for(Taxi taxi: taxis) {
            taxi.setNextLocation(0, 0);
            taxi.setStatus(true);
        }
        return taxis;
    }

    public List<Taxi> fetchTaxis() {
        return taxis;
    }

    public void reachToDest() {
        for(BookTaxi booktaxi: bookedTaxis) {
            booktaxi.reduceTotalTimeByOne();
            if(booktaxi.getTotal_time() == 0) {
                bookedTaxis.remove(booktaxi);
                taxis.get(booktaxi.getCar_id() - 1).setStatus(true);
            }
        }
    }

}
