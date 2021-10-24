package com.mj.deliveryapp.widget.adapter.listener.order

import com.mj.deliveryapp.model.restaurant.food.FoodModel
import com.mj.deliveryapp.widget.adapter.listener.AdapterListener

interface OrderMenuListListener: AdapterListener {

    fun onRemoveItem(model: FoodModel)

}