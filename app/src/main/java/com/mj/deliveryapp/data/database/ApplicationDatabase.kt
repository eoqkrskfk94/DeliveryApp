package com.mj.deliveryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mj.deliveryapp.data.database.dao.LocationDao
import com.mj.deliveryapp.data.entity.LocationLatLngEntity

@Database(
    entities = [LocationLatLngEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ApplicationDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun LocationDao(): LocationDao

}