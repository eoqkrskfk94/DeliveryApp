package com.mj.deliveryapp.data.repository.restaurant.food

import com.mj.deliveryapp.data.entity.RestaurantFoodEntity

interface RestaurantFoodRepository {

    suspend fun getFoods(restaurantId: Long): List<RestaurantFoodEntity>
}