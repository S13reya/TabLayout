# **TabLayout**

---
TabLayoutView is a **customizable TabLayout for Android** that works with ViewPager2 to display multiple fragments. 
It allows full styling from XML, including text colors, fonts, indicator color/height, and ripple effect.

---

## ✨ **Features**

- Fully XML-driven customization.

- Supports ViewPager2 and fragment switching.

- Customizable text color, selected text color, text size, font.

- Customizable indicator color and height.

- Optional ripple effect on tab click.



  ---

# **Preview**
---
<p align="center">
  <img src="https://github.com/user-attachments/assets/9504273a-4309-438e-9ec1-bd8a2ec5bced"
       alt="Demo GIF"
       width="200">

</p>


## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
    	        implementation 'com.github.Excelsior-Technologies-Community:OtpAutoFill:1.0.0'

}
```
## ⚡ **attrs file**

```

<declare-styleable name="ExtTabLayoutView">
    <attr name="tabTextColor" format="color" />
    <attr name="tabSelectedTextColor" format="color" />
    <attr name="tabTextSize" format="dimension" />
    <attr name="tabFontFamily" format="reference" />
    <attr name="tabIndicatorColor" format="color" />
    <attr name="tabIndicatorHeight" format="dimension" />
    <attr name="tabRippleEnabled" format="boolean" />
</declare-styleable>

```

## ⚡ **Usage**

1. Add in XML

```
<com.ext.tab_layout.TabLayoutView
    android:id="@+id/tabLayoutView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:tabTextSize="16sp"
    app:tabTextColor="#000000"
    app:tabSelectedTextColor="#FF0000"
    app:tabIndicatorColor="#FF0000"
    app:tabIndicatorHeight="4dp"
    app:tabRippleEnabled="true"/>

```

## **2. Setup in Activity**
```
val fragments = listOf(HomeFragment(), ProfileFragment(), SettingsFragment())
val tabTitles = listOf("Home", "Profile", "Settings")

tabLayoutView.setTabs(this, fragments, tabTitles)

```

## **Mainactivity code**

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments = listOf(HomeFragment(), ProfileFragment(), SettingsFragment())
        val titles = listOf("Home", "Profile", "Settings")

        findViewById<TabLayoutView>(R.id.tabLayoutView).setTabs(this, fragments, titles)
    }
}
```

## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
