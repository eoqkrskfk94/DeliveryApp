package com.mj.deliveryapp.data.repository.user

import com.mj.deliveryapp.data.entity.LocationLatLngEntity

interface UserRepository {

    suspend fun getUserLocation(): LocationLatLngEntity?

    suspend fun insertUserLocation(locationLatLngEntity: LocationLatLngEntity)

}