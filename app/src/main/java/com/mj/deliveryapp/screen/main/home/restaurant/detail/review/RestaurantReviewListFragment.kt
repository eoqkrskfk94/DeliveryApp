package com.mj.deliveryapp.screen.main.home.restaurant.detail.review

import androidx.core.os.bundleOf
import com.mj.deliveryapp.data.entity.RestaurantFoodEntity
import com.mj.deliveryapp.databinding.FragmentListBinding
import com.mj.deliveryapp.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantReviewListFragment: BaseFragment<RestaurantReviewViewModel, FragmentListBinding>() {

    override val viewModel by viewModel<RestaurantReviewViewModel>()

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    companion object {

        const val RESTAURANT_ID_KEY = "restaurantId"

        fun newInstance(restaurantId: Long): RestaurantReviewListFragment {
            val bundle = bundleOf(
                RESTAURANT_ID_KEY to restaurantId
            )
            return RestaurantReviewListFragment().apply {
                arguments = bundle
            }
        }
    }
}