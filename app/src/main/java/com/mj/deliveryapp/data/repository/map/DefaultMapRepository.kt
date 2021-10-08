package com.mj.deliveryapp.data.repository.map

import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.network.MapApiService
import com.mj.deliveryapp.data.response.address.AddressInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultMapRepository(
    private val mapApiService: MapApiService,
    private val ioDispatcher: CoroutineDispatcher
): MapRepository {

    override suspend fun getReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity): AddressInfo? = withContext(ioDispatcher) {
        val response = mapApiService.getReverseGeoCode(
            lat = locationLatLngEntity.latitude,
            lon = locationLatLngEntity.longitude
        )

        if(response.isSuccessful) {
            response.body()?.addressInfo
        } else {
            null
        }
    }

}