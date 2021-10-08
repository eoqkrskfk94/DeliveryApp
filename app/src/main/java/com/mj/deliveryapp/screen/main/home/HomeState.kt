package com.mj.deliveryapp.screen.main.home

import androidx.annotation.StringRes
import com.mj.deliveryapp.data.entity.MapSearchInfoEntity

sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity
    ): HomeState()

    data class Error(
        @StringRes val messageId: Int
    ): HomeState()

}
