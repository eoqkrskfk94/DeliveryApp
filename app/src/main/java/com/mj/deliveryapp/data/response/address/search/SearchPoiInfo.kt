package com.mj.deliveryapp.data.response.address.search

import com.mj.deliveryapp.data.response.address.search.Pois

data class SearchPoiInfo(
    val totalCount: String,
    val count: String,
    val page: String,
    val pois: Pois
)
