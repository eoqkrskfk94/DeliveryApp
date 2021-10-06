package com.mj.deliveryapp.di


import com.mj.deliveryapp.data.repository.DefaultRestaurantRepository
import com.mj.deliveryapp.data.repository.RestaurantRepository
import com.mj.deliveryapp.screen.main.home.HomeViewModel
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantCategory
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantListViewModel
import com.mj.deliveryapp.screen.main.my.MyViewModel
import com.mj.deliveryapp.util.provider.DefaultResourcesProvider
import com.mj.deliveryapp.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel() }
    viewModel { MyViewModel() }
    viewModel { (restaurantCategory: RestaurantCategory) -> RestaurantListViewModel(restaurantCategory, get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get()) }

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }
    single { provideRetrofit(get(), get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }



}