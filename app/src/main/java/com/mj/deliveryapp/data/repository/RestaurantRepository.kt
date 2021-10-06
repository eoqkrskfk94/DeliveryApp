package com.mj.deliveryapp.data.repository

import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantCategory

interface RestaurantRepository {

    suspend fun getList(
        restaurantCategory: RestaurantCategory
    ): List<RestaurantEntity>

}