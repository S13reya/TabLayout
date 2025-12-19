package com.ext.tab_layout

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ext.tab_layout.databinding.ViewTabLayoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewTabLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private var tabTextColor: Int = 0xFF000000.toInt()
    private var tabSelectedTextColor: Int = 0xFFFF0000.toInt()
    private var tabTextSizeSp: Float = 14f
    private var tabFontResId: Int = 0
    private var tabIndicatorColor: Int = 0xFFFF0000.toInt()
    private var tabIndicatorHeight: Float = 4f
    private var tabRippleEnabled: Boolean = true

    init {
        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.ExtTabLayoutView)
            tabTextColor = ta.getColor(R.styleable.ExtTabLayoutView_tabTextColor, tabTextColor)
            tabSelectedTextColor = ta.getColor(R.styleable.ExtTabLayoutView_tabSelectedTextColor, tabSelectedTextColor)
            val dimension = ta.getDimension(R.styleable.ExtTabLayoutView_tabTextSize, tabTextSizeSp * resources.displayMetrics.scaledDensity)
            tabTextSizeSp = dimension / resources.displayMetrics.scaledDensity
            tabFontResId = ta.getResourceId(R.styleable.ExtTabLayoutView_tabFontFamily, 0)
            tabIndicatorColor = ta.getColor(R.styleable.ExtTabLayoutView_tabIndicatorColor, tabIndicatorColor)
            tabIndicatorHeight = ta.getDimension(R.styleable.ExtTabLayoutView_tabIndicatorHeight, tabIndicatorHeight)
            tabRippleEnabled = ta.getBoolean(R.styleable.ExtTabLayoutView_tabRippleEnabled, tabRippleEnabled)
            ta.recycle()
        }
    }

    fun setTabs(activity: FragmentActivity, fragments: List<Fragment>, tabTitles: List<String>) {
        require(fragments.size == tabTitles.size) { "Fragments size must match tabTitles size" }

        binding.viewPager.adapter = TabPagerAdapter(activity, fragments)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        applyTabTextStyle()
        applyIndicator()

        // Update text colors on selection
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
                updateTabTextColors(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // Set initial selection color
        updateTabTextColors(binding.tabLayout.selectedTabPosition)
    }

    private fun applyTabTextStyle() {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i) ?: continue

            val container = LinearLayout(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                gravity = Gravity.CENTER
                isClickable = true
                isFocusable = true
                foreground = if (tabRippleEnabled) {
                    context.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground)).getDrawable(0)
                } else null
            }

            val textView = TextView(context).apply {
                text = tab.text
                setTextSize(TypedValue.COMPLEX_UNIT_SP, tabTextSizeSp)
                setTextColor(tabTextColor)
                if (tabFontResId != 0) typeface = ResourcesCompat.getFont(context, tabFontResId)
                gravity = Gravity.CENTER
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }

            container.addView(textView)
            container.setOnClickListener { binding.viewPager.currentItem = i }
            tab.customView = container
        }
    }

    private fun updateTabTextColors(selectedPosition: Int) {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i) ?: continue
            val container = tab.customView as? LinearLayout ?: continue
            val textView = container.getChildAt(0) as? TextView ?: continue
            textView.setTextColor(if (i == selectedPosition) tabSelectedTextColor else tabTextColor)
        }
    }

    private fun applyIndicator() {
        binding.tabLayout.setSelectedTabIndicatorHeight(tabIndicatorHeight.toInt())
        binding.tabLayout.setSelectedTabIndicatorColor(tabIndicatorColor)
    }
}
