package com.aptiv;

/*
BookTaxi class. taxiId and total_time takes for the taxi to complete the trip
All bookTaxis are unavailable in status. This needs to be updated in Taxi class.
 */
public class BookTaxi {
    private int car_id;
    private int total_time;
    private Location destination;

    public BookTaxi(int car_id, int total_time, Location destination) {
        this.car_id = car_id;
        this.total_time = total_time;
        this.destination = destination;
    }

    public int getCar_id() {
        return this.car_id;
    }

    public int getTotal_time() {
        return this.total_time;
    }

    public Location getDestination() {return this.destination;}

    public void reduceTotalTimeByOne() {
        this.total_time--;
    }

}
