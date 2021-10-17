package com.mj.deliveryapp.widget.adapter.listener.restaurant

import com.mj.deliveryapp.model.restaurant.food.FoodModel
import com.mj.deliveryapp.widget.adapter.listener.AdapterListener

interface FoodMenuListListener: AdapterListener {

    fun onClickItem(model: FoodModel)

}