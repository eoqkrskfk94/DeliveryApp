package com.mj.deliveryapp.data.repository.restaurant.food

import com.mj.deliveryapp.data.entity.RestaurantFoodEntity

interface RestaurantFoodRepository {

    suspend fun getFoods(restaurantId: Long, restaurantTitle: String): List<RestaurantFoodEntity>

    suspend fun getAllFoodMenuListInBasket(): List<RestaurantFoodEntity>

    suspend fun getFoodMenuListInBasket(restaurantId: Long): List<RestaurantFoodEntity>

    suspend fun insertFoodMenuInBasket(restaurantFoodEntity: RestaurantFoodEntity)

    suspend fun removeFoodMenuInBasket(foodId: String)

    suspend fun clearFoodMenuListInBasket()


}