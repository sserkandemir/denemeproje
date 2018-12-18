package com.slctick.slctick.Profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.slctick.slctick.Login.LoginActivity
import com.slctick.slctick.Models.Users
import com.slctick.slctick.R
import com.slctick.slctick.utils.BottomNavigationViewHelper
import com.slctick.slctick.utils.EventbusDataEvents
import com.slctick.slctick.utils.UniversalImageLoader
import kotlinx.android.synthetic.main.activity_profile.*
import org.greenrobot.eventbus.EventBus

class ProfileActivity : AppCompatActivity() {

    private val ACTIVITY_NO = 4
    private val TAG = "ProfileActivity"
    lateinit var mAuth: FirebaseAuth
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    lateinit var mUser: FirebaseUser
    lateinit var mRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupAuthListener()
        mAuth = FirebaseAuth.getInstance()
        mUser=mAuth.currentUser!!
        mRef= FirebaseDatabase.getInstance().reference

        setupToolbar()
        setupNavigationView()
        kullaniciBilgileriniGetir()
    }

    private fun kullaniciBilgileriniGetir() {

        tvProfilDuzenleButonu.isEnabled=false
        imgProfileSettings.isEnabled=false

        mRef.child("users").child(mUser!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.getValue() != null){
                    var okunanKullaniciBilgileri=p0!!.getValue(Users::class.java)


                    EventBus.getDefault().postSticky(EventbusDataEvents.KullaniciBilgileriniGonder(okunanKullaniciBilgileri))
                    tvProfilDuzenleButonu.isEnabled=true
                    imgProfileSettings.isEnabled=true

                    tvProfilAdiToolbar.setText(okunanKullaniciBilgileri!!.user_name)
                    tvProfilGercekAdi.setText(okunanKullaniciBilgileri!!.adi_soyadi)
                    tvFollowerSayisi.setText(okunanKullaniciBilgileri!!.user_detail!!.follower)
                    tvFollowingSayisi.setText(okunanKullaniciBilgileri!!.user_detail!!.following)
                    tvPostSayisi.setText(okunanKullaniciBilgileri!!.user_detail!!.post)


                    var imgUrl:String=okunanKullaniciBilgileri!!.user_detail!!.profile_picture!!
                    UniversalImageLoader.setImage(imgUrl,circleProfileImage,progressBar,"")


                    if(!okunanKullaniciBilgileri!!.user_detail!!.biography!!.isNullOrEmpty()){
                        tvBiyografi.visibility=View.VISIBLE
                        tvBiyografi.setText(okunanKullaniciBilgileri!!.user_detail!!.biography!!)
                    }else{
                        tvBiyografi.visibility=View.GONE
                    }
                    if(!okunanKullaniciBilgileri!!.user_detail!!.web_site!!.isNullOrEmpty()){
                        tvWebSitesi.visibility=View.VISIBLE
                        tvWebSitesi.setText(okunanKullaniciBilgileri!!.user_detail!!.web_site!!)
                    }else{
                        tvWebSitesi.visibility=View.GONE
                    }


                }



            }


        })

    }


    private fun setupToolbar() {

        imgProfileSettings.setOnClickListener {

            var intent = Intent(this, ProfileSettingsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            startActivity(intent)

        }

        tvProfilDuzenleButonu.setOnClickListener {

            profileRoot.visibility = View.GONE
            profileContainer.visibility=View.VISIBLE
            var transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.profileContainer,ProfileEditFragment())
            transaction.addToBackStack("editProfileFragmentEklendi")
            transaction.commit()
        }
    }

    fun setupNavigationView() {

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this, bottomNavigationView)
        var menu = bottomNavigationView.menu
        var menuItem = menu.getItem(ACTIVITY_NO)
        menuItem.setChecked(true)

    }

    override fun onBackPressed() {
        profileRoot.visibility = View.VISIBLE

        super.onBackPressed()
    }

    private fun setupAuthListener() {


        mAuthListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                var user = FirebaseAuth.getInstance().currentUser

                if (user == null) {

                    //Log.e("HATA","Kullanıcı oturum açmamış, ProfileActivitydesin")

                    var intent = Intent(
                        this@ProfileActivity, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                } else {


                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        //Log.e("HATA","ProfileActivitydesin")
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener)
        }
    }
}