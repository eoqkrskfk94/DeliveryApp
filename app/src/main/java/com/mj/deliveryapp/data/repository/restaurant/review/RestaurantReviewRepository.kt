package com.mj.deliveryapp.data.repository.restaurant.review

import com.mj.deliveryapp.data.entity.RestaurantReviewEntity

interface RestaurantReviewRepository {

    suspend fun getReviews(restaurantTitle: String): List<RestaurantReviewEntity>

}