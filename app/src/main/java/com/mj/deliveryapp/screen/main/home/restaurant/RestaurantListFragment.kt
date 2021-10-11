package com.mj.deliveryapp.screen.main.home.restaurant

import android.util.Log
import androidx.core.os.bundleOf
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.databinding.FragmentRestaurantListBinding
import com.mj.deliveryapp.model.restaurant.RestaurantModel
import com.mj.deliveryapp.screen.base.BaseFragment
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.ModelRecyclerAdapter
import com.mj.deliveryapp.widget.adapter.listener.restaurant.RestaurantListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment: BaseFragment<RestaurantListViewModel, FragmentRestaurantListBinding>() {

    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }
    private val locationLatLngEntity by lazy { arguments?.getParcelable<LocationLatLngEntity>(LOCATION_KEY) }

    override val viewModel by viewModel<RestaurantListViewModel> {
        parametersOf(
            restaurantCategory,
            locationLatLngEntity
        )
    }

    override fun getViewBinding(): FragmentRestaurantListBinding = FragmentRestaurantListBinding.inflate(layoutInflater)

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(listOf(), viewModel, resourcesProvider, adapterListener = object : RestaurantListListener {
            override fun onClickItem(model: RestaurantModel) {

            }
        })
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
    }

    override fun observeData() = viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
        adapter.submitList(it)
    }

    companion object {
        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"
        const val LOCATION_KEY = "location"

        fun newInstance(restaurantCategory: RestaurantCategory, locationLatLngEntity: LocationLatLngEntity): RestaurantListFragment {
            return RestaurantListFragment().apply {
                arguments = bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory,
                    LOCATION_KEY to locationLatLngEntity
                )
            }
        }
    }
}