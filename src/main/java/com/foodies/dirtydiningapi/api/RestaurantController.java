package com.foodies.dirtydiningapi.api;

import com.foodies.dirtydiningapi.service.DirtyDiningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/v1/restaurants")
@RestController
public class RestaurantController {

    @Autowired
    private DirtyDiningService dirtyDiningService;

    @GetMapping
    @ResponseBody
    public Map<String, Object> getNearRestaurants(
            @RequestParam(required = true) double longitude,
            @RequestParam(required = true) double latitude,
            @RequestParam(required = true) int distance,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "100") int row_count) {

        return dirtyDiningService.getNearRestaurants(longitude, latitude, distance, offset, row_count);

    }

}
