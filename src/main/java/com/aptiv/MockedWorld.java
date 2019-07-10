package com.aptiv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Singleton class which acts as a local storage.
 */
public class MockedWorld {

    private Map<Integer, BookTaxi> bookedTaxis;
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
        bookedTaxis = new HashMap<>();
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

    public void resetTimeStamp() {
        timeStamp = 0;
    }

    public void bookTaxis(BookTaxi bookTaxi) {
        this.bookedTaxis.put(bookTaxi.getCar_id(), bookTaxi);
    }

    public Map<Integer, BookTaxi> getBookedTaxis() {
        return this.bookedTaxis;
    }

    public List<Taxi> getTaxis() {
        return this.taxis;
    }


    public BookTaxiResponse pickVacantTaxi(int x1, int y1, int x2, int y2) {
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
                Location destination = new Location(x2, y2);
                int totalTime = shortestDistance + getManhattanDistance(x1, y1, x2, y2);
                BookTaxi booktaxi = new BookTaxi(pickTaxiId, totalTime, destination);
                bookTaxis(booktaxi);
                return new BookTaxiResponse(pickTaxiId, totalTime);
            }
        }
        return null;
    }

    public List<Taxi> resetTaxis() {
        bookedTaxis = new HashMap<>();
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
        for(int id: bookedTaxis.keySet()) {
            BookTaxi booktaxi = bookedTaxis.get(id);
            booktaxi.reduceTotalTimeByOne();
            if(booktaxi.getTotal_time() == 0) {
                Location dest = booktaxi.getDestination();
                taxis.get(id - 1).setStatus(true);
                taxis.get(id - 1).setNextLocation(dest.getRow(), dest.getCol());
            }
        }
        for(Taxi taxi: taxis) {
            if(taxi.isAvailable()) {
                bookedTaxis.remove(taxi.getId());
            }
        }
    }
}
