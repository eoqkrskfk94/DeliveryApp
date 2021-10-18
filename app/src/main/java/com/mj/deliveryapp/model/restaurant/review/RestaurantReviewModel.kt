package com.mj.deliveryapp.model.restaurant.review

import android.net.Uri
import com.mj.deliveryapp.model.CellType
import com.mj.deliveryapp.model.Model

data class RestaurantReviewModel(
    override val id: Long,
    override val type: CellType = CellType.REVIEW_CELL,
    val title: String,
    val description: String,
    val grade: Int,
    val thumbnailImageUri: Uri? = null
): Model(id, type)
