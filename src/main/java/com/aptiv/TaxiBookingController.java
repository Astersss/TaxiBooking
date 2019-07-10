package com.aptiv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TaxiBookingController {

    MockedWorld mockedWorld = MockedWorld.getInstance();

    @PostMapping("/api/book")
    public @ResponseBody BookTaxiResponse bookTaxi(@RequestBody Map<String, Map<String, String>> body) {
        int sourceX = Integer.parseInt(body.get("source").get("x"));
        int sourceY = Integer.parseInt(body.get("source").get("y"));
        int destX = Integer.parseInt(body.get("destination").get("x"));
        int destY = Integer.parseInt(body.get("destination").get("y"));
        return mockedWorld.pickVacantTaxi(sourceX, sourceY, destX, destY);
    }

    @PostMapping("/api/tick")
    public int increaseTimeStamp() {
        mockedWorld.reachToDest();
        return mockedWorld.tick();
    }

    @PutMapping("/api/reset")
    public List<Taxi> reset() {
        return mockedWorld.resetTaxis();
    }

    @GetMapping("/api/taxi")
    public List<Taxi> index() {
        return mockedWorld.fetchTaxis();
    }

}
