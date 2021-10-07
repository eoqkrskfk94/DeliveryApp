package com.mj.deliveryapp.model.restaurant

import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.model.CellType
import com.mj.deliveryapp.model.Model
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantCategory

data class RestaurantModel(
    override val id: Long,
    override val type: CellType = CellType.RESTAURANT_CELL,
    val restaurantInfoId: Long,
    val restaurantCategorys: RestaurantCategory,
    val restaurantTitle: String,
    val restaurantImageUrl: String,
    val grade: Float,
    val reviewCount: Int,
    val deliveryTimeRange: Pair<Int, Int>,
    val deliveryTipRange: Pair<Int, Int>,
) : Model(id, type) {

    fun toEntity() = RestaurantEntity(
        id,
        restaurantInfoId,
        restaurantCategorys,
        restaurantTitle,
        restaurantImageUrl,
        grade,
        reviewCount,
        deliveryTimeRange,
        deliveryTipRange,
    )

}