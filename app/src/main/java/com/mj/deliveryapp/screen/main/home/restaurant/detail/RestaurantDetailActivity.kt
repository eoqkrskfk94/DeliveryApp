package com.mj.deliveryapp.screen.main.home.restaurant.detail

import android.content.Context
import android.content.Intent
import com.mj.deliveryapp.data.entity.RestaurantEntity
import com.mj.deliveryapp.databinding.ActivityRestaurantDetailBinding
import com.mj.deliveryapp.screen.base.BaseActivity
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantListFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantDetailActivity :
    BaseActivity<RestaurantDetailViewModel, ActivityRestaurantDetailBinding>() {

    override fun getViewBinding(): ActivityRestaurantDetailBinding =
        ActivityRestaurantDetailBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantDetailViewModel> {
        parametersOf(
            intent.getParcelableExtra<RestaurantEntity>(RestaurantListFragment.RESTAURANT_KEY)
        )
    }


    override fun observeData() = viewModel.restaurantDetailStateLiveData.observe(this) {

    }

    companion object {

        fun newIntent(context: Context, restaurantEntity: RestaurantEntity) =
            Intent(context, RestaurantDetailActivity::class.java).apply {
                putExtra(RestaurantListFragment.RESTAURANT_KEY, restaurantEntity)
            }
    }


}
