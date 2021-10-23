package com.mj.deliveryapp.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mj.deliveryapp.databinding.*
import com.mj.deliveryapp.model.CellType
import com.mj.deliveryapp.model.Model
import com.mj.deliveryapp.screen.base.BaseViewModel
import com.mj.deliveryapp.screen.main.like.RestaurantLikeListViewModel
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.viewholder.EmptyViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.ModelViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.food.FoodMenuViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.restaurant.LikeRestaurantViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.restaurant.RestaurantViewHolder
import com.mj.deliveryapp.widget.adapter.viewholder.review.RestaurantReviewViewHolder

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
            CellType.LIKE_RESTAURANT_CELL -> LikeRestaurantViewHolder(
                ViewholderLikeRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.FOOD_CELL -> FoodMenuViewHolder(
                ViewholderFoodMenuBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.REVIEW_CELL -> RestaurantReviewViewHolder(
                ViewholderRestaurantReviewBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
        }
        return viewHolder as ModelViewHolder<M>

    }

}