package com.mj.deliveryapp.screen.main.like

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mj.deliveryapp.databinding.FragmentLikeBinding
import com.mj.deliveryapp.model.restaurant.RestaurantModel
import com.mj.deliveryapp.screen.base.BaseFragment
import com.mj.deliveryapp.screen.main.home.restaurant.detail.RestaurantDetailActivity
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.ModelRecyclerAdapter
import com.mj.deliveryapp.widget.adapter.listener.restaurant.RestaurantLikeListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantLikeListFragment: BaseFragment<RestaurantLikeListViewModel, FragmentLikeBinding>() {

    override fun getViewBinding(): FragmentLikeBinding = FragmentLikeBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantLikeListViewModel>()

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantLikeListViewModel>(listOf(), viewModel, resourcesProvider, adapterListener = object :
            RestaurantLikeListListener {

            override fun onDislikeItem(model: RestaurantModel) {
                viewModel.dislikeRestaurant(model.toEntity())
            }

            override fun onClickItem(model: RestaurantModel) {
                startActivity(
                    RestaurantDetailActivity.newIntent(requireContext(), model.toEntity())
                )
            }

        })
    }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    override fun observeData() = viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
        checkListEmpty(it)
    }

    private fun checkListEmpty(restaurantList: List<RestaurantModel>) {
        val isEmpty = restaurantList.isEmpty()

        binding.recyclerView.isGone = isEmpty
        binding.emptyResultTextView.isVisible = isEmpty

        if(isEmpty.not()) {
            adapter.submitList(restaurantList)
        }
    }

    companion object {

        const val TAG = "restaurantLikeListFragment"

        fun newInstance() = RestaurantLikeListFragment()


    }
}