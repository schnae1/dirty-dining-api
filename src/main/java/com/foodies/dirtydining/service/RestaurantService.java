package com.foodies.dirtydining.service;

import com.foodies.dirtydining.dao.IRestaurantRepository;
import com.foodies.dirtydining.dto.Restaurant;
import com.foodies.dirtydining.dto.RestaurantResponse;
import com.foodies.dirtydining.dto.RestaurantsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Map<String, Object> getNearRestaurants(double longitude, double latitude, int distance, int offset, int row_count) {
        return null;
    }

    public RestaurantResponse getRestaurantById(String id) {
        final RestaurantResponse restaurantResponse = new RestaurantResponse();
        final com.foodies.dirtydining.model.Restaurant restaurant = restaurantRepository.getRestaurantById(id);
        if (restaurant != null) {
            final Restaurant restaurantDto = modelMapper.map(restaurant, Restaurant.class);
            restaurantResponse.setRestaurant(restaurantDto);
        }
        return restaurantResponse;
    }

    public RestaurantsResponse searchRestaurants(String input, Integer page, Integer size) {
        final RestaurantsResponse restaurantsResponse = new RestaurantsResponse();
        final Page<com.foodies.dirtydining.model.Restaurant> pageable = restaurantRepository.searchRestaurants(input, page, size);
        final List<com.foodies.dirtydining.model.Restaurant> restaurantResults = pageable.getContent();

        final List<Restaurant> restaurants = new ArrayList<>();
        for (com.foodies.dirtydining.model.Restaurant restaurant : restaurantResults) {
            restaurants.add(modelMapper.map(restaurant, Restaurant.class));
        }
        restaurantsResponse.setSize(pageable.getSize());
        restaurantsResponse.setTotalResults(pageable.getTotalElements());
        restaurantsResponse.setTotalPages(pageable.getTotalPages());
        restaurantsResponse.setRestaurants(restaurants);

        return restaurantsResponse;
    }

}
