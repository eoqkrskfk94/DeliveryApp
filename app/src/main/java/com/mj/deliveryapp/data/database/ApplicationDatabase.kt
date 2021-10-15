package com.mj.deliveryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mj.deliveryapp.data.database.dao.LocationDao
import com.mj.deliveryapp.data.database.dao.RestaurantDao
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.RestaurantEntity

@Database(
    entities = [LocationLatLngEntity::class, RestaurantEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ApplicationDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun LocationDao(): LocationDao

    abstract fun RestaurantDao(): RestaurantDao
}