package com.mj.deliveryapp.screen.main.home

import androidx.lifecycle.MutableLiveData
import com.mj.deliveryapp.screen.base.BaseViewModel

class HomeViewModel: BaseViewModel() {

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)



}