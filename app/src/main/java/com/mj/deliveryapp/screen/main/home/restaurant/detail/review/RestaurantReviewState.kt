package com.mj.deliveryapp.screen.main.home.restaurant.detail.review

import com.mj.deliveryapp.model.restaurant.review.RestaurantReviewModel

sealed class RestaurantReviewState {

    object Uninitialized: RestaurantReviewState()

    object Loading: RestaurantReviewState()

    data class Success(
        val reviewList: List<RestaurantReviewModel>
    ): RestaurantReviewState()


}
