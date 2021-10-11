package com.mj.deliveryapp.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mj.deliveryapp.R
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.MapSearchInfoEntity
import com.mj.deliveryapp.data.repository.map.MapRepository
import com.mj.deliveryapp.data.repository.user.UserRepository
import com.mj.deliveryapp.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mapRepository: MapRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)

    fun loadReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity) = viewModelScope.launch {
        homeStateLiveData.value = HomeState.Loading
        val userLocation = userRepository.getUserLocation()
        val currentLocation = userLocation ?: locationLatLngEntity



        val addressInfo = mapRepository.getReverseGeoInformation(currentLocation)

        addressInfo?.let { info ->
            homeStateLiveData.value = HomeState.Success(
                mapSearchInfoEntity = info.toSearchMapInfoEntity(locationLatLngEntity),
                isLocationSame = currentLocation == locationLatLngEntity
            )
        } ?: kotlin.run {
            homeStateLiveData.value = HomeState.Error(
                R.string.can_not_load_address_info
            )
        }
    }

    fun getMapSearchInfo(): MapSearchInfoEntity? {
        when(val data = homeStateLiveData.value) {
            is HomeState.Success -> {
                return data.mapSearchInfoEntity
            }
        }
        return null
    }

    companion object {
        const val MY_LOCATION_KEY = "MyLocation"
    }


}