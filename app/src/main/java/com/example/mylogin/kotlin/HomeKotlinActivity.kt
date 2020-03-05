package com.example.mylogin.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.mylogin.R

class HomeKotlinActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_kotlin)

        pagerAdapter = PagerAdapter(supportFragmentManager, 3)
        viewPager.adapter = pagerAdapter
        viewPager = findViewById(R.id.viewPager)
    }
}
