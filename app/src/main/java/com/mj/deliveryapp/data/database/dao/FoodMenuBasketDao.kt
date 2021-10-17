package com.mj.deliveryapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.data.entity.RestaurantFoodEntity

@Dao
interface FoodMenuBasketDao {

    @Query("SELECT * FROM RestaurantFoodEntity WHERE id=:id AND restaurantId=:restaurantId")
    suspend fun get(restaurantId: Long, id: Long): RestaurantFoodEntity?

    @Query("SELECT * FROM RestaurantFoodEntity")
    suspend fun getAll(): List<RestaurantFoodEntity>

    @Query("SELECT * FROM RestaurantFoodEntity WHERE restaurantId=:restaurantId")
    suspend fun getAllByRestaurantId(restaurantId: Long): List<RestaurantFoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantFoodEntity: RestaurantFoodEntity)

    @Query("DELETE FROM restaurantFoodEntity WHERE id=:id")
    suspend fun delete(id: String)

    @Query("DELETE FROM restaurantFoodEntity")
    suspend fun deleteAll()

}

