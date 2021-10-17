package com.mj.deliveryapp.model.restaurant.food

import com.mj.deliveryapp.model.CellType
import com.mj.deliveryapp.model.Model

data class FoodModel(
    override val id: Long,
    override val type: CellType = CellType.FOOD_CELL,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val restaurantId: Long

): Model(id, type)