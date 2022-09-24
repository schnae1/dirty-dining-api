package com.foodies.dirtydining.dao;

import com.foodies.dirtydining.model.Restaurant;

import java.util.List;

public interface IRestaurantRepository {

    List<Restaurant> findRestaurantsNearBy(final double latitude, final double longitude);

    void insertRestaurants(final List<Restaurant> restaurants);
}
