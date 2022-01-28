package com.architecture.mvvm.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.architecture.base.BaseFragment
import com.architecture.base.extensions.viewBinding
import com.architecture.mvvm.R
import com.architecture.mvvm.app.assets.AssetsFragment
import com.architecture.mvvm.app.home.HomeFragment
import com.architecture.mvvm.app.me.MeFragment
import com.architecture.mvvm.app.swap.SwapFragment
import com.architecture.mvvm.databinding.HomeFragmentBinding
import com.architecture.mvvm.databinding.MainFragmentBinding
import kotlinx.coroutines.launch

/**
 *     author : wnq
 *     time   : 2022/01/27
 *     desc   :
 */
class MainFragment: BaseFragment() {
    private val homeFragment by lazy { HomeFragment() }
    private val swapFragment by lazy { SwapFragment() }
    private val assetsFragment by lazy { AssetsFragment() }
    private val meFragment by lazy { MeFragment() }

    private val fragmentMap: Map<Int, Fragment> by lazy {
        hashMapOf(
            R.id.main_bottom_nav_home to homeFragment,
            R.id.main_bottom_nav_swap to swapFragment,
            R.id.main_bottom_nav_assets to assetsFragment,
            R.id.main_bottom_nav_me to meFragment
        )
    }

    private val viewModel: MainViewModel by viewModels()

    private val binding: MainFragmentBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainHomeNavigation.itemIconTintList = null
    }

    override fun setupViews() {

    }

    override fun setupListeners() {
        binding.mainHomeNavigation.setOnItemSelectedListener {
            lifecycleScope.launch {
                viewModel.navigationPosition.emit(it.itemId)
            }
            true
        }
    }

    override fun setupObservers() {
        lifecycleScope.launch{
            viewModel.navigationPosition
                .collect {
                    changeFragment(fragmentMap[it]!!)
                }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        //检查是否已经实例化过，已存在则直接添加到ui，否则创建并添加
        transaction.replace(R.id.main_home_container, fragment, tag)
        transaction.commit()
    }
}