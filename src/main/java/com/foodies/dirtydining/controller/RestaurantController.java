package com.foodies.dirtydining.controller;

import com.foodies.dirtydining.dto.RestaurantResponse;
import com.foodies.dirtydining.dto.RestaurantsResponse;
import com.foodies.dirtydining.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/v1/restaurant")
@RestController
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable(value = "id") String id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/search")
    public RestaurantsResponse searchRestaurants(@RequestParam(value = "query") String query,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size) {
        // TODO: Finish setting up pagination (validate working), clean up code and names and set up more fields to query on **
        // START HERE ****
        return restaurantService.searchRestaurants(query, page, size);
    }

    @GetMapping
    @ResponseBody
    public Map<String, Object> getNearRestaurants(
            @RequestParam(required = true) double longitude,
            @RequestParam(required = true) double latitude,
            @RequestParam(required = true) int distance,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "100") int row_count) {

        return restaurantService.getNearRestaurants(longitude, latitude, distance, offset, row_count);

    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
