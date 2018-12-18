package com.slctick.slctick.utils

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.slctick.slctick.Home.HomeActivity
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.slctick.slctick.News.NewsActivity
import com.slctick.slctick.Profile.ProfileActivity
import com.slctick.slctick.R
import com.slctick.slctick.Search.SearchActivity
import com.slctick.slctick.Share.ShareActivity

class BottomNavigationViewHelper {

    companion object {
        fun setupBottomNavigationView(bottomNavigationViewEx: BottomNavigationViewEx){

            bottomNavigationViewEx.enableAnimation(false)
            bottomNavigationViewEx.enableItemShiftingMode(false)
            bottomNavigationViewEx.enableShiftingMode(false)
            bottomNavigationViewEx.setTextVisibility(false)

        }

        fun setupNavigation(context: Context, bottomNavigationViewEx: BottomNavigationViewEx){

            bottomNavigationViewEx.onNavigationItemSelectedListener=object :BottomNavigationView.OnNavigationItemSelectedListener{
                override fun onNavigationItemSelected(item: MenuItem): Boolean {

                    when(item.itemId){

                        R.id.tab_home -> {

                            val intent= Intent(context, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true



                        }
                        R.id.tab_search -> {
                            val intent= Intent(context, SearchActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true



                        }
                        R.id.tab_share -> {
                            val intent= Intent(context, ShareActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true



                        }
                        R.id.tab_news -> {
                            val intent= Intent(context, NewsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true



                        }
                        R.id.tab_profile -> {
                            val intent= Intent(context, ProfileActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true



                        }

                    }
                    return false




                }


            }




        }
    }
}