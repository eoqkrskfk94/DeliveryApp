package com.mj.deliveryapp.screen.main.home.restaurant.detail.review

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.data.entity.ReviewEntity
import com.mj.deliveryapp.data.repository.restaurant.review.DefaultRestaurantReviewRepository
import com.mj.deliveryapp.data.repository.restaurant.review.RestaurantReviewRepository
import com.mj.deliveryapp.model.restaurant.review.RestaurantReviewModel
import com.mj.deliveryapp.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantReviewViewModel(
    private val restaurantTitle: String,
    private val restaurantReviewRepository: RestaurantReviewRepository
) : BaseViewModel() {

    val reviewStateLiveData =
        MutableLiveData<RestaurantReviewState>(RestaurantReviewState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        reviewStateLiveData.value = RestaurantReviewState.Loading

        when (val result = restaurantReviewRepository.getReviews(restaurantTitle)) {
            is DefaultRestaurantReviewRepository.Result.Success<*> -> {
                val reviews = result.data as List<ReviewEntity>
                reviewStateLiveData.value =
                    RestaurantReviewState.Success(
                        reviews.map {
                            RestaurantReviewModel(
                                id = it.hashCode().toLong(),
                                title = it.title,
                                description = it.content,
                                grade = it.rating,
                                thumbnailImageUri = if(it.imageUrlList.isNullOrEmpty()) {
                                    null
                                } else {
                                    Uri.parse(it.imageUrlList.first())
                                }
                            )
                        }
                    )
            }
            else -> Unit
        }


    }

}