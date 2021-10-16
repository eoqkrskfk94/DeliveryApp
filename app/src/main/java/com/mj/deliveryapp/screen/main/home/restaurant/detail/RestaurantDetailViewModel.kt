package com.mj.deliveryapp.screen.main.home.restaurant.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.data.repository.restaurant.food.RestaurantFoodRepository
import com.mj.deliveryapp.data.repository.user.UserRepository
import com.mj.deliveryapp.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantDetailViewModel(
    private val restaurantEntity: RestaurantEntity,
    private val restaurantFoodRepository: RestaurantFoodRepository,
    private val userRepository: UserRepository
): BaseViewModel() {

    val restaurantDetailStateLiveData = MutableLiveData<RestaurantDetailState>(RestaurantDetailState.Uninitialized)


    override fun fetchData(): Job = viewModelScope.launch {
        restaurantDetailStateLiveData.value = RestaurantDetailState.Success(
            restaurantEntity = restaurantEntity
        )
        restaurantDetailStateLiveData.value = RestaurantDetailState.Loading

        val foods = restaurantFoodRepository.getFoods(restaurantId = restaurantEntity.restaurantInfoId)
        val isLiked = userRepository.getUserLikedRestaurant(restaurantEntity.restaurantTitle) != null
        restaurantDetailStateLiveData.value = RestaurantDetailState.Success(
            restaurantEntity = restaurantEntity,
            restaurantFoodList = foods,
            isLiked = isLiked
        )
    }

    fun getRestaurantTelNumber(): String? = when(val data = restaurantDetailStateLiveData.value) {
        is RestaurantDetailState.Success -> {
            data.restaurantEntity.restaurantTelNumber
        }
        else -> null
    }

    fun getRestaurantInfo(): RestaurantEntity? = when(val data = restaurantDetailStateLiveData.value) {
        is RestaurantDetailState.Success -> {
            data.restaurantEntity
        }
        else -> null
    }

    fun toggleLikedRestaurant() = viewModelScope.launch {
        when(val data = restaurantDetailStateLiveData.value) {
            is RestaurantDetailState.Success -> {
                userRepository.getUserLikedRestaurant(restaurantEntity.restaurantTitle)?.let {
                    userRepository.deleteUserLikedRestaurant(it.restaurantTitle)
                    restaurantDetailStateLiveData.value = data.copy(
                        isLiked = false
                    )
                } ?: kotlin.run {
                    userRepository.insertUserLikedRestaurant(restaurantEntity)
                    restaurantDetailStateLiveData.value = data.copy(
                        isLiked = true
                    )
                }
            }
        }
    }

}