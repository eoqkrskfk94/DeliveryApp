package com.mj.deliveryapp.widget.adapter.viewholder.order

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.mj.deliveryapp.R
import com.mj.deliveryapp.databinding.ViewholderOrderMenuBinding
import com.mj.deliveryapp.extensions.clear
import com.mj.deliveryapp.extensions.load
import com.mj.deliveryapp.model.restaurant.food.FoodModel
import com.mj.deliveryapp.screen.base.BaseViewModel
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.listener.AdapterListener
import com.mj.deliveryapp.widget.adapter.listener.order.OrderMenuListListener
import com.mj.deliveryapp.widget.adapter.listener.restaurant.FoodMenuListListener
import com.mj.deliveryapp.widget.adapter.viewholder.ModelViewHolder

class OrderMenuViewHolder(
    private val binding: ViewholderOrderMenuBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<FoodModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        foodImage.clear()
    }

    override fun bindData(model: FoodModel) {
        super.bindData(model)
        with(binding) {
            foodImage.load(model.imageUrl, 24f, CenterCrop())
            foodTitleText.text = model.title
            foodDescriptionText.text = model.description
            priceText.text = resourcesProvider.getString(R.string.price, model.price)
        }
    }

    override fun bindViews(model: FoodModel, adapterListener: AdapterListener) {
        if(adapterListener is OrderMenuListListener) {
            binding.removeButton.setOnClickListener {
                adapterListener.onRemoveItem(model)
            }
        }
    }
}