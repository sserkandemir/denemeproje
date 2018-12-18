package com.slctick.slctick.Share

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.slctick.slctick.R
import com.slctick.slctick.utils.SharePagerAdapter
import kotlinx.android.synthetic.main.activity_share.*

class ShareActivity : AppCompatActivity() {

    private val ACTIVITY_NO=2
    private val TAG = "ShareActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        setupShareViewPager()


    }

    private fun setupShareViewPager() {

        var tabAdlari = ArrayList<String>()
        tabAdlari.add("GALERI")
        tabAdlari.add("FOTOĞRAF")
        tabAdlari.add("VIDEO")

        var sharePagerAdapter = SharePagerAdapter(supportFragmentManager, tabAdlari)
        sharePagerAdapter.addFragment(ShareGalleryFragment())
        sharePagerAdapter.addFragment(ShareCameraFragment())
        sharePagerAdapter.addFragment(ShareVideoFragment())

        shareViewPager.adapter = sharePagerAdapter
        sharetabLayout.setupWithViewPager(shareViewPager)

    }

        override fun onBackPressed() {

            anaLayout.visibility = View.GONE
            fragmentContainerLayout.visibility = View.VISIBLE
            super.onBackPressed()



        }



}
