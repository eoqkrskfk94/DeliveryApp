package com.mj.deliveryapp.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.R
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.MapSearchInfoEntity
import com.mj.deliveryapp.data.repository.map.MapRepository
import com.mj.deliveryapp.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mapRepository: MapRepository
) : BaseViewModel() {

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)

    fun loadReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity) = viewModelScope.launch {
        homeStateLiveData.value = HomeState.Loading
        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)

        addressInfo?.let { info ->
            homeStateLiveData.value = HomeState.Success(
                mapSearchInfoEntity = info.toSearchMapInfoEntity(locationLatLngEntity)
            )
        } ?: kotlin.run {
            homeStateLiveData.value = HomeState.Error(
                R.string.can_not_load_address_info
            )
        }
    }


}