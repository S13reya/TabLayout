package com.ext.tablayout


import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.ext.tab_layout.TabLayoutView
import tabs.HomeFragment
import tabs.ProfileFragment
import tabs.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = android.graphics.Color.parseColor("#000000") // black
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0, // no light icons
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        val fragments = listOf(
            HomeFragment(),
            ProfileFragment(),
            SettingsFragment()
            // Add more fragments if needed
        )

        val tabTitles = listOf(
            "Home",
            "Profile",
            "Settings"
        )

        val tabLayoutView = findViewById<TabLayoutView>(R.id.tabLayoutView)
        tabLayoutView.setTabs(this, fragments, tabTitles)
    }
}
