package com.mj.deliveryapp.data.repository.map

import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.response.address.AddressInfo

interface MapRepository {

    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo?

}