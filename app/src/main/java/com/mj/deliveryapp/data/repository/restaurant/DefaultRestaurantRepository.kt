package com.mj.deliveryapp.data.repository.restaurant

import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.data.network.MapApiService
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantCategory
import com.mj.deliveryapp.util.provider.ResourcesProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultRestaurantRepository(
    private val mapApiService: MapApiService,
    private val resourcesProvider: ResourcesProvider,
    private val ioDispatcher: CoroutineDispatcher
) : RestaurantRepository {

    override suspend fun getList(
        restaurantCategory: RestaurantCategory,
        locationLatLngEntity: LocationLatLngEntity
    ): List<RestaurantEntity> = withContext(ioDispatcher) {

        println(resourcesProvider.getString(restaurantCategory.categoryTypeId))

        val response = mapApiService.getSearchLocationAround(
            categories = resourcesProvider.getString(restaurantCategory.categoryTypeId),
            centerLat = locationLatLngEntity.latitude.toString(),
            centerLon = locationLatLngEntity.longitude.toString(),
            searchType = "name",
            radius = "1",
            reqCoordType = "WGS84GEO",
            searchtypCd = "A",
            resCoordType = "EPSG3857"
        )

        if(response.isSuccessful) {
            println(response.body()?.searchPoiInfo?.pois?.poi?.size)
            response.body()?.searchPoiInfo?.pois?.poi?.map { poi ->
                RestaurantEntity(
                    restaurantTitle =poi.name ?: "no title",
                    id = hashCode().toLong(),
                    restaurantInfoId = (1..10).random().toLong(),
                    restaurantCategorys = restaurantCategory,
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    deliveryTimeRange = Pair((0..20).random(), (40..60).random()),
                    deliveryTipRange = Pair((0..1000).random(), (2000..4000).random()),
                    restaurantTelNumber = poi.telNo
                )
            } ?: listOf()
        } else{
            listOf()
        }
    }

}