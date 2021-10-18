package com.mj.deliveryapp.widget.adapter.viewholder.review

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mj.deliveryapp.R
import com.mj.deliveryapp.databinding.ViewholderRestaurantBinding
import com.mj.deliveryapp.databinding.ViewholderRestaurantReviewBinding
import com.mj.deliveryapp.extensions.*
import com.mj.deliveryapp.model.restaurant.RestaurantModel
import com.mj.deliveryapp.model.restaurant.review.RestaurantReviewModel
import com.mj.deliveryapp.screen.base.BaseViewModel
import com.mj.deliveryapp.util.provider.ResourcesProvider
import com.mj.deliveryapp.widget.adapter.listener.AdapterListener
import com.mj.deliveryapp.widget.adapter.listener.restaurant.RestaurantListListener
import com.mj.deliveryapp.widget.adapter.viewholder.ModelViewHolder

class RestaurantReviewViewHolder(
    private val binding: ViewholderRestaurantReviewBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<RestaurantReviewModel>(binding, viewModel, resourcesProvider) {


    override fun reset() = with(binding) {
        reviewThumbnailImage.clear()
        reviewThumbnailImage.isGone = true
    }

    override fun bindData(model: RestaurantReviewModel) {
        super.bindData(model)
        with(binding) {
            if(model.thumbnailImageUri != null){
                reviewThumbnailImage.isVisible = true
                reviewThumbnailImage.load(model.thumbnailImageUri.toString(), 24f)
            } else {
                reviewThumbnailImage.isGone = true
            }
            reviewTitleText.text = model.title
            reviewText.text = model.description
            ratingBar.rating = model.grade.toFloat()
        }

    }

    override fun bindViews(model: RestaurantReviewModel, adapterListener: AdapterListener) = Unit

}