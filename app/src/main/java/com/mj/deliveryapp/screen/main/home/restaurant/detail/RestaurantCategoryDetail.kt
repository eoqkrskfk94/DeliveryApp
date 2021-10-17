package com.mj.deliveryapp.screen.main.home.restaurant.detail

import androidx.annotation.StringRes
import com.mj.deliveryapp.R

enum class RestaurantCategoryDetail(
    @StringRes val categoryNameId: Int
) {
    MENU(R.string.menu), REVIEW(R.string.review)
}