package com.slctick.slctick.Profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.slctick.slctick.R
import com.slctick.slctick.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_profile_settings2.*

class ProfileSettingsActivity : AppCompatActivity() {
    private val ACTIVITY_NO=4
    private val TAG = "ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings2)

        setupNavigationView()
        setupToolbar()
        fragmentNavigations()

    }

    private fun setupToolbar() {
        imgBack.setOnClickListener {
            onBackPressed()

        }
    }

    override fun onBackPressed() {
        profileSettingsRoot.visibility=View.VISIBLE
        super.onBackPressed()
    }

    private fun fragmentNavigations() {

        tvProfilDuzenleHesapAyarlari.setOnClickListener {
            profileSettingsRoot.visibility=View.GONE
            var transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.profileSettingsContainer, ProfileEditFragment())
            transaction.addToBackStack("editProfileFragmentEklendi")
            transaction.commit()
        }
        tvCikisYap.setOnClickListener {
            var dialog=SignOutFragment()
            dialog.show(supportFragmentManager,"cikisYapDialogGoster")
        }


    }


    fun setupNavigationView(){

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this, bottomNavigationView)
        var menu=bottomNavigationView.menu
        var menuItem = menu.getItem(ACTIVITY_NO)
        menuItem.setChecked(true)

    }
}
