package com.mj.deliveryapp.widget.adapter.listener.restaurant

import com.mj.deliveryapp.model.restaurant.RestaurantModel

interface RestaurantLikeListListener: RestaurantListListener {

    fun onDislikeItem(model: RestaurantModel)

}