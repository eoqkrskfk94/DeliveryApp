package com.mj.deliveryapp.util.event

import com.mj.deliveryapp.screen.main.MainTab
import kotlinx.coroutines.flow.MutableSharedFlow

class MenuChangeEventBus {

    val mainTabMenuFlow = MutableSharedFlow<MainTab>()

    suspend fun changeMenu(menu: MainTab) {
        mainTabMenuFlow.emit(menu)
    }

}