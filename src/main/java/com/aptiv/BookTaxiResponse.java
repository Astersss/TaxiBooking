package com.aptiv;

public class BookTaxiResponse {

    private int car_id;
    private int total_time;

    public BookTaxiResponse(int car_id, int total_time) {
        this.car_id = car_id;
        this.total_time = total_time;
    }

    public int getCar_id() {
        return this.car_id;
    }

    public int getTotal_time() {
        return this.total_time;
    }

}
