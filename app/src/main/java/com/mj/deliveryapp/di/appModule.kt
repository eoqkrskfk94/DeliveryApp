package com.mj.deliveryapp.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.data.entity.MapSearchInfoEntity
import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.data.entity.RestaurantFoodEntity
import com.mj.deliveryapp.data.preference.AppPreferenceManager
import com.mj.deliveryapp.data.repository.map.DefaultMapRepository
import com.mj.deliveryapp.data.repository.map.MapRepository
import com.mj.deliveryapp.data.repository.order.DefaultOrderRepository
import com.mj.deliveryapp.data.repository.order.OrderRepository
import com.mj.deliveryapp.data.repository.restaurant.DefaultRestaurantRepository
import com.mj.deliveryapp.data.repository.restaurant.RestaurantRepository
import com.mj.deliveryapp.data.repository.restaurant.food.DefaultRestaurantFoodRepository
import com.mj.deliveryapp.data.repository.restaurant.food.RestaurantFoodRepository
import com.mj.deliveryapp.data.repository.restaurant.review.DefaultRestaurantReviewRepository
import com.mj.deliveryapp.data.repository.restaurant.review.RestaurantReviewRepository
import com.mj.deliveryapp.data.repository.user.DefaultUserRepository
import com.mj.deliveryapp.data.repository.user.UserRepository
import com.mj.deliveryapp.screen.main.home.HomeViewModel
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantCategory
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantListViewModel
import com.mj.deliveryapp.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import com.mj.deliveryapp.screen.main.home.restaurant.detail.menu.RestaurantMenuViewModel
import com.mj.deliveryapp.screen.main.home.restaurant.detail.review.RestaurantReviewViewModel
import com.mj.deliveryapp.screen.main.like.RestaurantLikeListViewModel
import com.mj.deliveryapp.screen.main.my.MyViewModel
import com.mj.deliveryapp.screen.mylocation.MyLocationViewModel
import com.mj.deliveryapp.screen.order.OrderMenuListViewModel
import com.mj.deliveryapp.util.event.MenuChangeEventBus
import com.mj.deliveryapp.util.provider.DefaultResourcesProvider
import com.mj.deliveryapp.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { MyViewModel(get(), get(), get()) }
    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get())
    }
    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) ->
        MyLocationViewModel(
            mapSearchInfoEntity,
            get(),
            get()
        )
    }
    viewModel { (restaurantEntity: RestaurantEntity) ->
        RestaurantDetailViewModel(
            restaurantEntity,
            get(),
            get()
        )
    }
    viewModel { (restaurantId: Long, restaurantFoodList: List<RestaurantFoodEntity>) -> RestaurantMenuViewModel(restaurantId, restaurantFoodList, get()) }
    viewModel { (restaurantTitle: String) -> RestaurantReviewViewModel(restaurantTitle, get()) }
    viewModel { RestaurantLikeListViewModel(get()) }
    viewModel { OrderMenuListViewModel(get(), get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get(), get()) }
    single<RestaurantFoodRepository> { DefaultRestaurantFoodRepository(get(), get(), get()) }
    single<RestaurantReviewRepository> {DefaultRestaurantReviewRepository(get(), get())}
    single<OrderRepository> {DefaultOrderRepository(get(),get())}

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }
    single(named("map")) { provideMapRetrofit(get(), get()) }
    single(named("food")) { provideFoodRetrofit(get(), get()) }

    single { provideMapApiService(get(qualifier = named("map"))) }
    single { provideFoodApiService(get(qualifier = named("food"))) }

    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
    single { provideRestaurantDao(get()) }
    single { provideFoodMenuBasketDao(get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }
    single { AppPreferenceManager(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single { MenuChangeEventBus() }

    single {FirebaseStorage.getInstance() }
    single {Firebase.firestore }
    single {FirebaseAuth.getInstance() }

}