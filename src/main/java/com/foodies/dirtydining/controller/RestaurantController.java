package com.foodies.dirtydining.controller;

import com.foodies.dirtydining.dto.RestaurantResponse;
import com.foodies.dirtydining.dto.RestaurantsResponse;
import com.foodies.dirtydining.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("api/v1/restaurant")
@RestController
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @CrossOrigin(origins = "https://ericsportfolio.site")
    @GetMapping("/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable(value = "id") String id) {
        return restaurantService.getRestaurantById(id);
    }

    @CrossOrigin(origins = "https://ericsportfolio.site")
    @GetMapping("/search")
    public RestaurantsResponse searchRestaurants(@RequestParam(value = "query") String query,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size) throws MissingServletRequestParameterException {

        if (StringUtils.isEmpty(query) || StringUtils.isEmpty(query.trim())) {
            throw new MissingServletRequestParameterException("query", "String");
        }
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

    @CrossOrigin(origins = "https://ericsportfolio.site")
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
