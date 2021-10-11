package com.mj.deliveryapp.widget.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mj.deliveryapp.data.entity.LocationLatLngEntity
import com.mj.deliveryapp.screen.main.home.restaurant.RestaurantListFragment

class RestaurantListFragmentPagerAdapter(
    fragment: Fragment,
    val fragmentList: List<RestaurantListFragment>,
    var locationLatLngEntity: LocationLatLngEntity
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}