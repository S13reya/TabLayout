package com.ext.tab_layout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPagerAdapter(
    activity: FragmentActivity,
    private val fragments: List<Fragment> // Pass any number of fragments
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
