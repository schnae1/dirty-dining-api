package com.foodies.dirtydining.controller;

import com.foodies.dirtydining.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/v1/restaurants")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

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

}
