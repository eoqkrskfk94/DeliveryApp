package com.mj.deliveryapp.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mj.deliveryapp.databinding.ViewholderEmptyBinding
import com.mj.deliveryapp.databinding.ViewholderFoodMenuBinding
import com.mj.deliveryapp.databinding.ViewholderRestaurantBinding
import com.mj.deliveryapp.model.CellType
import com.mj.deliveryapp.model.Model
import com.mj.deliveryapp.screen.base.BaseViewModel
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.viewholder.EmptyViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.ModelViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.food.FoodMenuViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.restaurant.RestaurantViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.EMPTY_CELL -> EmptyViewHolder(
                ViewholderEmptyBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.RESTAURANT_CELL -> RestaurantViewHolder(
                ViewholderRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.FOOD_CELL -> FoodMenuViewHolder(
                ViewholderFoodMenuBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
        }
        return viewHolder as ModelViewHolder<M>

    }

}