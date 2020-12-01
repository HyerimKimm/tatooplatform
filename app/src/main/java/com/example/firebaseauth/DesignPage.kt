package com.example.firebaseauth

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.firebaseauth.databinding.ActivityDesignPageBinding
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_design_page.*

class DesignPage : AppCompatActivity() {
    lateinit var binding: ActivityDesignPageBinding
    lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesignPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = Firebase.storage
        val compass = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/compass.jpg")
        val dooble = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/dooble.jpg")
        val mini = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/mini.jpg")
        val heart = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/heart.jpg")
        val irezumi= storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/irezumi.jpg")
        val lettering = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/lettering.jpg")
        val school = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/school.jpg")
        val finger = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/finger.jpg")
        val newschool = storage.getReferenceFromUrl("gs://fir-auth-ae144.appspot.com/newschool.jpg")

        displayImageRef(compass,binding.imageview1)
        displayImageRef(dooble,binding.imageview2)
        displayImageRef(mini,binding.imageview3)
        displayImageRef(heart,binding.imageview4)
        displayImageRef(irezumi,binding.imageview5)
        displayImageRef(lettering,binding.imageview6)
        displayImageRef(school,binding.imageview7)
        displayImageRef(finger,binding.imageview8)
        displayImageRef(newschool,binding.imageview9)
    }

    private fun displayImageRef(imageRef: StorageReference?, view: ImageView){
        imageRef?.getBytes(Long.MAX_VALUE)?.addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            view.setImageBitmap(bmp)
        }?.addOnFailureListener {  }
    }

}