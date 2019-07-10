package com.aptiv;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MockedWorldTest {

    MockedWorld testMockedWorld;

    @Before
    public void setUp() {
        testMockedWorld = MockedWorld.getInstance();
    }


    @Test
    public void testGetManhattanDistanceReturnRightAnswer() {
        testMockedWorld.resetTaxis();
        int expectedResponse = 10;
        Assert.assertEquals(expectedResponse, testMockedWorld.getManhattanDistance(1, 2, 8, 5));
    }

    @Test
    public void testTickMethod() {
        testMockedWorld.resetTaxis();
        Assert.assertEquals(1, testMockedWorld.tick());
    }

    @Test
    public void pickVacantTaxiShouldPickRightAvailableTaxi() {
        testMockedWorld.resetTaxis();
        List<Taxi> taxis = testMockedWorld.fetchTaxis();
        Taxi taxi1 = taxis.get(0);
        taxi1.setStatus(false);
        int car_id = 2;
        int total_time = 9;
        BookTaxiResponse response = testMockedWorld.pickVacantTaxi(1, 1, 5,4);
        Assert.assertEquals(car_id, response.getCar_id());
        Assert.assertEquals(total_time, response.getTotal_time());
    }

    @Test
    public void testResetTaxiMethod() {
        testMockedWorld.resetTaxis();
        List<Taxi> taxis = testMockedWorld.fetchTaxis();
        Taxi taxi1 = taxis.get(0);
        taxi1.setStatus(false);
        taxi1.setNextLocation(6, 8);
        testMockedWorld.resetTaxis();
        for(Taxi taxi: taxis) {
            Assert.assertTrue(taxi.isAvailable());
            Assert.assertEquals(0, taxi.getLocation().getRow());
            Assert.assertEquals(0, taxi.getLocation().getCol());
        }
    }

    @Test
    public void testReachToDestMethod() {
        testMockedWorld.resetTaxis();
        testMockedWorld.pickVacantTaxi(0, 0, 1, 2);
        testMockedWorld.pickVacantTaxi(0,0,2, 1);
        for(int i = 0; i < 3; i++) {
            testMockedWorld.reachToDest();
        }
        List<Taxi> taxis = testMockedWorld.getTaxis();
        Assert.assertTrue(testMockedWorld.getBookedTaxis().isEmpty());
        Assert.assertTrue(taxis.get(0).isAvailable());
        Assert.assertTrue(taxis.get(1).isAvailable());
        Assert.assertTrue(taxis.get(2).isAvailable());
        Assert.assertEquals(1, taxis.get(0).getLocation().getRow());
        Assert.assertEquals(2, taxis.get(0).getLocation().getCol());
        Assert.assertEquals(2, taxis.get(1).getLocation().getRow());
        Assert.assertEquals(1, taxis.get(1).getLocation().getCol());
    }

}
