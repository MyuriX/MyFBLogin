package com.example.mylogin.kotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mylogin.kotlin.OverViewFragment
import com.example.mylogin.kotlin.PhotoFragment
import com.example.mylogin.kotlin.ReviewsFragment

class PagerAdapter(fm: FragmentManager, internal var mNumOfTabs: Int) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> {
                return OverViewFragment()
            }
            1 -> {
                return PhotoFragment()
            }
            3 ->
                return ReviewsFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}