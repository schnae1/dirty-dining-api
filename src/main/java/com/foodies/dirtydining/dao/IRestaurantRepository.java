package com.foodies.dirtydining.dao;

import com.foodies.dirtydining.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantRepository {

    String COLLECTION_NAME = "restaurants";

    List<Restaurant> findRestaurantsNearBy(final double latitude, final double longitude);

    void insertRestaurants(final List<Restaurant> restaurants);

    void updateOrInsertRestaurant(Restaurant restaurant) throws IllegalAccessException;

    Restaurant getRestaurantById(final String id);

    Page<Restaurant> searchRestaurants(final String input, final Integer start, final Integer offset);

    boolean doesCollectionExist();

    void dropCollection();
}
