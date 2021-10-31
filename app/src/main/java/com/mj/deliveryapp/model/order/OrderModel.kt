package com.mj.deliveryapp.model.order

import com.mj.deliveryapp.data.entity.OrderEntity
import com.mj.deliveryapp.data.entity.RestaurantFoodEntity
import com.mj.deliveryapp.model.CellType
import com.mj.deliveryapp.model.Model

data class OrderModel(
    override val id: Long,
    override val type: CellType = CellType.ORDER_CELL,
    val orderId: String,
    val userId: String,
    val restaurantId: Long,
    val foodMenuList: List<RestaurantFoodEntity>
): Model(id, type) {

    fun toEntity() = OrderEntity(
        id = orderId,
        userId = userId,
        restaurantId = restaurantId,
        foodMenuList = foodMenuList
    )

}
