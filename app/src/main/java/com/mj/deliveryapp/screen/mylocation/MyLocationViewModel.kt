package com.mj.deliveryapp.screen.mylocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.R
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.MapSearchInfoEntity
import com.mj.deliveryapp.data.repository.map.MapRepository
import com.mj.deliveryapp.screen.base.BaseViewModel
import com.mj.deliveryapp.screen.main.home.HomeState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyLocationViewModel(
    private val mapSearchInfoEntity: MapSearchInfoEntity,
    private val mapRepository: MapRepository
) : BaseViewModel() {

    val myLocationStateLiveData = MutableLiveData<MyLocationState>(MyLocationState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        myLocationStateLiveData.value = MyLocationState.Loading
        myLocationStateLiveData.value = MyLocationState.Success(
            mapSearchInfoEntity
        )
    }

    fun changeLocationInfo(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {

        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)

        addressInfo?.let { info ->
            myLocationStateLiveData.value = MyLocationState.Success(
                mapSearchInfoEntity = info.toSearchMapInfoEntity(locationLatLngEntity)
            )
        } ?: kotlin.run {
            myLocationStateLiveData.value = MyLocationState.Error(
                R.string.can_not_load_address_info
            )
        }
    }

    fun confirmSelectLocation() = viewModelScope.launch {
        when(val data = myLocationStateLiveData.value) {
            is MyLocationState.Success -> {

            }
        }
    }

}