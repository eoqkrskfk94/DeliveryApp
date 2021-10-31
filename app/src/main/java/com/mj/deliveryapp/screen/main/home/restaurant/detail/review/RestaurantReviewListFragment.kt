package com.mj.deliveryapp.screen.main.home.restaurant.detail.review

import androidx.core.os.bundleOf
import com.mj.deliveryapp.databinding.FragmentListBinding
import com.mj.deliveryapp.model.restaurant.review.RestaurantReviewModel
import com.mj.deliveryapp.screen.base.BaseFragment
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.ModelRecyclerAdapter
import com.mj.deliveryapp.widget.adapter.listener.AdapterListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantReviewListFragment: BaseFragment<RestaurantReviewViewModel, FragmentListBinding>() {

    override val viewModel by viewModel<RestaurantReviewViewModel>{
        parametersOf(
            arguments?.getString(RESTAURANT_TITLE_KEY)
        )
    }

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantReviewModel, RestaurantReviewViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : AdapterListener { }
        )
    }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.reviewStateLiveData.observe(viewLifecycleOwner) {
        when(it) {
            is RestaurantReviewState.Success -> {
                handleSuccessState(it)
            }
            else -> Unit
        }
    }

    private fun handleSuccessState(state: RestaurantReviewState.Success) {
        adapter.submitList(state.reviewList)
    }

    companion object {

        const val RESTAURANT_TITLE_KEY = "restaurantTitle"

        fun newInstance(restaurantTitle: String): RestaurantReviewListFragment {
            val bundle = bundleOf(
                RESTAURANT_TITLE_KEY to restaurantTitle
            )
            return RestaurantReviewListFragment().apply {
                arguments = bundle
            }
        }
    }
}