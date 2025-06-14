package com.se.restaurantreview.repository;

import com.se.restaurantreview.model.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant ,String> {

    //Todo : Custom queries

}
