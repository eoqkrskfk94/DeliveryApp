package com.mj.deliveryapp.screen.main.home.restaurant.detail

import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.data.entity.RestaurantFoodEntity

sealed class RestaurantDetailState {

    object Uninitialized: RestaurantDetailState()

    object Loading: RestaurantDetailState()

    data class Success(
        val restaurantEntity: RestaurantEntity,
        val isLiked: Boolean? = null
    ): RestaurantDetailState()

}
