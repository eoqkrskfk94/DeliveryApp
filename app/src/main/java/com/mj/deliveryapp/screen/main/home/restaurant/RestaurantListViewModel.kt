package com.mj.deliveryapp.screen.main.home.restaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.repository.restaurant.RestaurantRepository
import com.mj.deliveryapp.model.restaurant.RestaurantModel
import com.mj.deliveryapp.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantCategory: RestaurantCategory,
    private var locationLatLngEntity: LocationLatLngEntity,
    private val restaurantRepository: RestaurantRepository,
    private var restaurantOrder: RestaurantOrder = RestaurantOrder.DEAFALUT
) : BaseViewModel() {

    val restaurantListLiveData = MutableLiveData<List<RestaurantModel>>()

    override fun fetchData(): Job = viewModelScope.launch {
        val restaurantList = restaurantRepository.getList(restaurantCategory, locationLatLngEntity)
        val sortedList = when(restaurantOrder) {
            RestaurantOrder.DEAFALUT -> {
                restaurantList
            }
            RestaurantOrder.LOW_DELIVERY_TIP -> {
                restaurantList.sortedBy { it.deliveryTipRange.first }
            }
            RestaurantOrder.FAST_DELIVERY -> {
                restaurantList.sortedBy { it.deliveryTimeRange.first }
            }
            RestaurantOrder.TOP_LATE -> {
                restaurantList.sortedByDescending { it.grade }
            }
        }
        restaurantListLiveData.value = sortedList.map {
            RestaurantModel(
                id = it.id,
                restaurantInfoId = it.restaurantInfoId,
                restaurantCategorys = it.restaurantCategorys,
                restaurantTitle = it.restaurantTitle,
                restaurantImageUrl = it.restaurantImageUrl,
                grade = it.grade,
                reviewCount = it.reviewCount,
                deliveryTimeRange = it.deliveryTimeRange,
                deliveryTipRange = it.deliveryTipRange,
            )
        }
    }

    fun setLocationLatLng(locationLatLngEntity: LocationLatLngEntity) {
        this.locationLatLngEntity = locationLatLngEntity
        fetchData()
    }

    fun setRestaurantOrder(order: RestaurantOrder) {
        this.restaurantOrder = order
        fetchData()
    }

}