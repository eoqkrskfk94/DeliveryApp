package com.mj.deliveryapp.screen.main.home

import com.mj.deliveryapp.databinding.FragmentHomeBinding
import com.mj.deliveryapp.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()


    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun observeData() { }

    companion object {

        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"

    }

}