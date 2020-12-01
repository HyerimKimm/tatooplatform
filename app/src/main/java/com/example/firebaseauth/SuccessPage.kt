package com.example.firebaseauth

import android.content.ContentUris
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.example.firebaseauth.databinding.ActivitySuccessPageBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class SuccessPage : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val dataPagename = intent.getStringExtra("pagename")
        val dataId = intent.getStringExtra("id")

        binding.reserveManage.setOnClickListener {
            val nextintent = Intent(this, ReservationManage::class.java)
            nextintent.putExtra("pagename", dataPagename)
            startActivity(nextintent)
        }

        binding.mypage.setOnClickListener {
            val nextIntent = Intent(this, MypageTatooist::class.java)
            nextIntent.putExtra("pagename", dataPagename)
            nextIntent.putExtra("id", dataId)
            startActivity(nextIntent)
        }
    }
}