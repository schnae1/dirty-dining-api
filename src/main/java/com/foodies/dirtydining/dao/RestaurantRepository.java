package com.foodies.dirtydining.dao;

import com.foodies.dirtydining.model.Restaurant;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RestaurantRepository implements IRestaurantRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Restaurant> findRestaurantsNearBy(final double latitude, final double longitude) {
        return null;
    }

    @Override
    public void insertRestaurants(final List<Restaurant> restaurants) {
        mongoTemplate.insertAll(restaurants);
    }

    @Override
    public void updateOrInsertRestaurant(Restaurant restaurant) throws IllegalAccessException {

        final Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("permitNumber").is(restaurant.getPermitNumber()));
        final Restaurant existingRestaurant = mongoTemplate.findOne(findQuery, Restaurant.class);

        if (existingRestaurant != null) {
            if (!existingRestaurant.equals(restaurant)) {
                final Query updateQuery = new Query();
                updateQuery.addCriteria(Criteria.where("id").is(existingRestaurant.getId()));
                final Update update = createUpdate(restaurant);
                mongoTemplate.updateFirst(updateQuery, update, Restaurant.class);
            }
        } else {
            final Query insertQuery = new Query();
            insertQuery.addCriteria(Criteria.where("permitNumber").is(restaurant.getPermitNumber()));
            mongoTemplate.insert(restaurant);
        }
    }

    @Override
    public Restaurant getRestaurantById(final String id) {
        return mongoTemplate.findById(id, Restaurant.class);
    }

    @Override
    public Page<Restaurant> searchRestaurants(final String input, final Integer start, final Integer offset) {

        final TextCriteria criteria = TextCriteria.forDefaultLanguage().caseSensitive(false)
                .matching(input);

        final Pageable pageable = PageRequest.of(start, offset, Sort.by("score").descending());

        final Query query = TextQuery.queryText(criteria)
                .with(pageable)
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize());

        final List<Restaurant> restaurants = mongoTemplate.find(query, Restaurant.class);
        final long count = mongoTemplate.count(query.skip(-1).limit(-1), Restaurant.class);

        return new PageImpl<Restaurant>(restaurants, pageable, count);
    }

    @Override
    public boolean doesCollectionExist() {
        return mongoTemplate.collectionExists(COLLECTION_NAME);
    }

    @Override
    public void dropCollection() {
        mongoTemplate.dropCollection(COLLECTION_NAME);
    }

    private Update createUpdate(Restaurant restaurant) {
        final Document document = new Document();
        mongoTemplate.getConverter().write(restaurant, document);
        return Update.fromDocument(document);
    }
}
