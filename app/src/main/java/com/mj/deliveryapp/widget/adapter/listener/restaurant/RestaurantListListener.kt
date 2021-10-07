package com.mj.deliveryapp.widget.adapter.listener.restaurant
import com.mj.deliveryapp.model.restaurant.RestaurantModel
import com.mj.deliveryapp.widget.adapter.listener.AdapterListener

interface RestaurantListListener: AdapterListener {

    fun onClickItem(model: RestaurantModel)

}