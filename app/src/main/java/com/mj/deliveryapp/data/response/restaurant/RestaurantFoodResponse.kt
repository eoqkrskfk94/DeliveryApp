package com.mj.deliveryapp.data.response.restaurant

import com.mj.deliveryapp.data.entity.RestaurantFoodEntity


data class RestaurantFoodResponse(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
){
    fun toEntity(restaurantId: Long) = RestaurantFoodEntity(
        id, title, description, price.toDouble().toInt(), imageUrl, restaurantId
    )
}
