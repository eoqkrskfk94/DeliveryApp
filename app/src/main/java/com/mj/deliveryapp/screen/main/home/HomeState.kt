package com.mj.deliveryapp.screen.main.home

sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    object Success: HomeState()

}
