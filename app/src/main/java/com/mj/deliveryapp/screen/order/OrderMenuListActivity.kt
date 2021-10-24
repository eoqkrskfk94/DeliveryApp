package com.mj.deliveryapp.screen.order

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mj.deliveryapp.databinding.ActivityOrderMenuListBinding
import com.mj.deliveryapp.model.restaurant.food.FoodModel
import com.mj.deliveryapp.screen.base.BaseActivity
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.ModelRecyclerAdapter
import com.mj.deliveryapp.widget.adapter.listener.order.OrderMenuListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class OrderMenuListActivity : BaseActivity<OrderMenuListViewModel, ActivityOrderMenuListBinding>() {


    override fun getViewBinding() = ActivityOrderMenuListBinding.inflate(layoutInflater)

    override val viewModel by viewModel<OrderMenuListViewModel>()

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<FoodModel, OrderMenuListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : OrderMenuListListener {
                override fun onRemoveItem(model: FoodModel) {
                    viewModel.removeOrderMenu(model)
                }
            })
    }

    override fun observeData() = viewModel.orderMenuStateLiveData.observe(this) {
        when (it) {
            is OrderMenuState.Loading -> {
                handleLoading()
            }
            is OrderMenuState.Success -> {
                handleSuccess(it)
            }
            is OrderMenuState.Order -> {

            }
            is OrderMenuState.Error -> {

            }
            else -> Unit
        }
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun handleSuccess(state: OrderMenuState.Success) = with(binding) {
        progressBar.isGone = true
        adapter.submitList(state.restaurantFoodModelList)
        val menuOrderIsEmpty = state.restaurantFoodModelList.isNullOrEmpty()
        confirmButton.isEnabled = menuOrderIsEmpty.not()

        if(menuOrderIsEmpty) {
            Toast.makeText(this@OrderMenuListActivity, "주분 메뉴가 없어 화면을 종료합니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
        toolbar.setNavigationOnClickListener { finish() }
        confirmButton.setOnClickListener {
            viewModel.orderMenu()
        }

        orderClearButton.setOnClickListener {
            viewModel.clearOrderMenu()
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, OrderMenuListActivity::class.java)

    }
}