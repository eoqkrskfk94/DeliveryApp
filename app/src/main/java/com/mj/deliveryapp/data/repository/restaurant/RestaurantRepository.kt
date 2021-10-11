package com.mj.deliveryapp.data.repository.restaurant

import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantCategory

interface RestaurantRepository {

    suspend fun getList(
        restaurantCategory: RestaurantCategory,
        locationLatLngEntity: LocationLatLngEntity
    ): List<RestaurantEntity>

}