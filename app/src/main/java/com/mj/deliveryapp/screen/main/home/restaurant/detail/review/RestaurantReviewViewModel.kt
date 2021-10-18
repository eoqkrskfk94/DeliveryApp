package com.mj.deliveryapp.screen.main.home.restaurant.detail.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.data.repository.restaurant.review.RestaurantReviewRepository
import com.mj.deliveryapp.model.restaurant.review.RestaurantReviewModel
import com.mj.deliveryapp.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantReviewViewModel(
    private val restaurantTitle: String,
    private val restaurantReviewRepository: RestaurantReviewRepository
): BaseViewModel() {

    val reviewStateLiveData = MutableLiveData<RestaurantReviewState>(RestaurantReviewState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        reviewStateLiveData.value = RestaurantReviewState.Loading
        val reviews = restaurantReviewRepository.getReviews(restaurantTitle)
        reviewStateLiveData.value = RestaurantReviewState.Success(
            reviews.map {
                RestaurantReviewModel(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    grade = it.grade,
                    thumbnailImageUri = it.images?.first()
                )
            }
        )
    }

}