package com.aptiv;

/*
BookTaxi class. taxiId and total_time takes for the taxi to complete the trip
All bookTaxis are unavailable in status. This needs to be updated in Taxi class.
 */
public class BookTaxi {
    private int car_id;
    private int total_time;

    public BookTaxi(int car_id, int total_time) {
        this.car_id = car_id;
        this.total_time = total_time;
    }

    public int getCar_id() {
        return this.car_id;
    }

    public int getTotal_time() {
        return this.total_time;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public void reduceTotalTimeByOne() {
        this.total_time--;
    }


}
