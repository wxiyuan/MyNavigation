package com.dev.mynavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tabs by lazy {
        arrayOf(tab_message, tab_contact, tab_find, tab_profile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initTabsClickListener()
    }

    private fun initViewPager() {
        view_pager.adapter = PagerAdapter(this)
        view_pager.registerOnPageChangeCallback(MyCallback(tabs))
    }

    private fun initTabsClickListener() {
        tab_message.setOnClickListener { view_pager.setCurrentItem(0, false) }
        tab_contact.setOnClickListener { view_pager.setCurrentItem(1, false) }
        tab_find.setOnClickListener { view_pager.setCurrentItem(2, false) }
        tab_profile.setOnClickListener { view_pager.setCurrentItem(3, false) }
    }

    class MyCallback(private val tabs : Array<TabConstraintLayout>) : ViewPager2.OnPageChangeCallback() {

        private var lastSelected = -1

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (positionOffset == 0f) {
                onPageFullyDisplayed(position)
            } else {
                tabs[position].setFadePercent(positionOffset)
                tabs[position + 1].setFadePercent(1f - positionOffset)
            }
        }

        private fun onPageFullyDisplayed(position : Int) {
            if (lastSelected in 0..3) {
                tabs[lastSelected].switchToNormal()
            }
            tabs[position].switchToSelected()
            lastSelected = position
        }
    }
}
